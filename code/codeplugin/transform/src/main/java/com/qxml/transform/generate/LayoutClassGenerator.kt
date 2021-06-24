package com.qxml.transform.generate

import com.android.SdkConstants
import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.TransformOutputProvider
import com.android.ide.common.internal.WaitableExecutor
import com.google.gson.GsonBuilder
import com.qxml.QxmlConfigExtension
import com.qxml.constant.Constants
import com.qxml.tools.log.LogUtil
import com.qxml.tools.model.CompatViewInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.collect.ViewGenInfoHolderImpl
import com.qxml.transform.generate.match.AttrMethodValueMatcher
import com.qxml.transform.generate.model.*
import com.qxml.transform.pool.PoolManager
import groovy.util.XmlParser
import org.gradle.internal.impldep.com.google.common.collect.ImmutableSet
import java.io.File
import java.lang.StringBuilder
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap

private const val INCLUDE_LOOP_COUNT_LIMIT = 2000

class LayoutClassGenerator(
    private val isAndroidx: Boolean,
    private val transformOutputProvide: TransformOutputProvider,
    private val layoutInfoMap: HashMap<String, HashMap<String, LayoutFileInfo>>,// Map<name, Map<type, info>>
    private val attrInfoMap: HashMap<String, AttrInfoModel>,
    private val viewGenInfoHolder: ViewGenInfoHolderImpl,
    private val packageName: String,
    private val waitableExecutor: WaitableExecutor,
    private val genClassInfoCacheDir: File,
    private val qxmlExtension: QxmlConfigExtension,
    private val compatViewInfoMap: Map<String, CompatViewInfoModel>,
    private val idMap: Map<String, Int>,
    private val idCacheMap: Map<String, Int>
): ResolveAttr, MakeGenJar, CreateClassFile, CreateGenAndCache {

    private val xmlParsers = mutableListOf<XmlParser>()
    private val attrMethodValueMatcher by lazy { AttrMethodValueMatcher(packageName, attrInfoMap) }
    private val gson by lazy { GsonBuilder().disableHtmlEscaping().create() }

    private val idChangeMap by lazy {
        hashMapOf<String, String>().apply {
            idCacheMap.forEach { (name, value) ->
                val curId = idMap[name]
                if (curId != null && curId != value) {
                    LogUtil.d("id change: $name old($value} new(${idMap[name]})")
                    putIfAbsent(name, "")
                }
            }
        }
    }

    //layout生成情况, key:layoutName, value: null:还未生成, 反则已生成
    private val layoutGenStateMap = ConcurrentHashMap<String, Boolean>()
    //layout的生成结果 Map<layoutName, Map<layoutType, ViewGenResultInfo>>
    private val finalGenResultMap = ConcurrentHashMap<String, HashMap<String, ViewGenResultInfo>>()

    private val styleInfoMap by lazy { viewGenInfoHolder.styleInfoMap }

    private val cacheQueue = mutableListOf<ClassGenCacheInfo>()

    @Synchronized
    override fun getXmlParser(): XmlParser {
        if (xmlParsers.isNotEmpty()) {
            return xmlParsers.removeAt(0)
        }
        return XmlParser()
    }

    @Synchronized
    override fun releaseXmlParser(xmlParser: XmlParser){
        xmlParsers.add(xmlParser)
    }

    fun generate(): QxmlGenResultProvider {
        //存放gen class的jar
        val layoutGenJarOutputFile = transformOutputProvide.getContentLocation(
            "\$\$qxml_${Constants.QXML_VERSION_CODE}_layout_gen_jar",
            setOf(QualifiedContent.DefaultContentType.CLASSES),
            mutableSetOf(QualifiedContent.Scope.EXTERNAL_LIBRARIES),
            //ImmutableSet.of(QualifiedContent.Scope.EXTERNAL_LIBRARIES),
            Format.JAR
        )

        if (!genClassInfoCacheDir.exists()) {
            genClassInfoCacheDir.mkdirs()
        }

        layoutGenJarOutputFile.delete()

        //LogUtil.pl("layout gen file " + layoutGenJarOutputFile.absolutePath+" "+qxmlExtension.compatMode)

        //layout name 对应的生成信息
        val finalGenInfoMap = ConcurrentHashMap<String, ClassGenCacheInfo>()

        var timeStart = System.currentTimeMillis()
        var spend = 0L

        //成功生成class的信息集合
        val classGenInfoList = Collections.synchronizedList(mutableListOf<ClassGenCacheInfo>())


        val unGenLayoutNameList = mutableListOf<String>()
        layoutInfoMap.forEach { (layoutName, _) ->
            unGenLayoutNameList.add(layoutName)
        }

        //先检查缓存
        val cacheLayoutNameList = checkGenCache(finalGenInfoMap, unGenLayoutNameList, layoutInfoMap, classGenInfoList)
        LogUtil.pl("use cache layouts "+cacheLayoutNameList)
        cacheLayoutNameList.forEach {
            unGenLayoutNameList.remove(it)
        }
        LogUtil.pl("unuse cache layouts "+unGenLayoutNameList)

        //waitableExecutor.waitForTasksWithQuickFail<Any>(true)

        cacheQueue.forEach {
            createClassFileWithCache(it.layoutName, it)
        }

        /*val content = gson.toJson(viewGenInfoHolder.viewGenInfoMap)
        LogUtil.pl("genInfoMap combine:  "+content)*/

        //暂时用循环次数判断循环引用
        var loopTimes = 0
        while (unGenLayoutNameList.isNotEmpty()) {
            if (loopTimes >= INCLUDE_LOOP_COUNT_LIMIT) {
                val stringBuilder = StringBuilder()
                unGenLayoutNameList.forEach {
                    stringBuilder.append("$it ")
                }
                stringBuilder.append("有循环的include引用")
                error(stringBuilder.toString())
            }
            loopTimes++
            val unResolveLayoutNameList = createGenContentAndGenInfoResult(isAndroidx, finalGenInfoMap, layoutInfoMap
                , unGenLayoutNameList, classGenInfoList, waitableExecutor, layoutGenStateMap, genClassInfoCacheDir
                , packageName, gson, finalGenResultMap, qxmlExtension, attrMethodValueMatcher, viewGenInfoHolder
                , compatViewInfoMap, styleInfoMap)
            unGenLayoutNameList.clear()
            unGenLayoutNameList.addAll(unResolveLayoutNameList)
        }

        /*layoutGenStateMap.forEach { (layoutName, viewGenResultInfo) ->
            LogUtil.pl("gen result "+layoutName+" "+viewGenResultInfo)
        }*/
        spend = System.currentTimeMillis() - timeStart
        LogUtil.pl("layout parse and generate code time cost: " + spend +"ms")
        timeStart = System.currentTimeMillis()

        makeGenJar(layoutGenJarOutputFile, classGenInfoList)

        spend = System.currentTimeMillis() - timeStart
        LogUtil.pl("wirte class time cost: " + spend + "ms")
        timeStart = System.currentTimeMillis()

        val genClassInfoList2 = mutableListOf<String>()
        finalGenInfoMap.forEach { (layoutName, classGenInfo) ->
            genClassInfoList2.add(layoutName)
        }

        viewGenInfoHolder.cacheLocalVarDefContent()

        return object : QxmlGenResultProvider {
            override fun getGenInfoList(): List<String> = genClassInfoList2
            override fun getGenReport(): Map<String, HashMap<String, ViewGenResultInfo>> = finalGenResultMap
        }
    }

    private fun checkGenCache(finalGenCacheInfoMap: ConcurrentHashMap<String, ClassGenCacheInfo>
                              , unGenLayoutNameList: List<String>
                              , layoutInfoMap: HashMap<String, HashMap<String, LayoutFileInfo>>
                              , genClassCacheInfoList: MutableList<ClassGenCacheInfo>, preIncludeLayoutSize: Int = 0): List<String> {

        val useCacheLayoutNameList = mutableListOf<String>()
        val hasIncludeLayoutNameMap = hashMapOf<String, String>()
        unGenLayoutNameList.forEach xmlLoopContinue@{ layoutName ->
            layoutInfoMap[layoutName]?.let { xmlTypeInfoMap ->
                val xmlTypeInfoList = mutableListOf<LayoutFileInfo>()
                xmlTypeInfoMap.forEach { (_, xmlTypeInfo) ->
                    xmlTypeInfoList.add(xmlTypeInfo)
                }
                xmlTypeInfoList.sort()
                val layoutClassCacheDir = genClassInfoCacheDir.resolve(layoutName)

                val cacheFileName = getGenClassCacheFileName("${layoutName}${makeQxmlConfigVerifyKey(qxmlExtension)}")
                val cacheInfoFile = layoutClassCacheDir.resolve(cacheFileName)
                val methodContentCacheFile = layoutClassCacheDir.resolve(Constants.QXML_METHOD_CONTENT_CACHE_FILE_NAME)
                val className = makeGenClassName(layoutName)
                val cacheClassFile = layoutClassCacheDir.resolve(className.replace(".", File.separator) + SdkConstants.DOT_CLASS)
                if (cacheInfoFile.exists() && cacheClassFile.exists() && methodContentCacheFile.exists()) {
                    val generateClassInfo = gson.fromJson(cacheInfoFile.readText(), GenerateClassInfo::class.java)
                    var isGenFailed = false
                    generateClassInfo.genReportInfo.forEach { (_, viewGenResultInfo) ->
                        if (viewGenResultInfo.result == GenResult.NO_GEN_CLASS) {
                            if (viewGenResultInfo.noGenViewClassName.isNotEmpty() && viewGenInfoHolder.hasViewGenClass(viewGenResultInfo.noGenViewClassName)) {
                                LogUtil.pl("regen by gen view implement: ${viewGenResultInfo.noGenViewClassName}")
                                isGenFailed = true
                                return@forEach
                            }
                        }
                        if (/*viewGenResultInfo.result == GenResult.NO_GEN_CLASS
                            ||*/ viewGenResultInfo.result == GenResult.VALUE_MATCH_ERROR
                            || viewGenResultInfo.result == GenResult.ATTR_UN_IMPLEMENT
                            || viewGenResultInfo.result == GenResult.GEN_FAILED) {
                            isGenFailed = true

                            return@forEach
                        }
                    }
                    if (!isGenFailed && generateClassInfo.verifyKey == getCacheVerifyKey(xmlTypeInfoList, qxmlExtension) && !viewGenInfoHolder.anyChange(generateClassInfo)) {
                        //LogUtil.pl("no change "+className+" "+cacheInfoFile.absolutePath)
                        var hasIncludeUnGen = false
                        val hasInclude = generateClassInfo.relativeIncludeLayoutMap.isNotEmpty()
                        generateClassInfo.relativeIncludeLayoutMap.forEach { (includeLayoutName, _) ->
                            if (layoutGenStateMap[includeLayoutName] == null) {
                                hasIncludeUnGen = true
                                hasIncludeLayoutNameMap.putIfAbsent(layoutName, "")
                                return@forEach
                            }
                        }

                        if (!hasIncludeUnGen) {
                            generateClassInfo.methodContent = methodContentCacheFile.readText()
                            val classGenInfo = ClassGenCacheInfo(true, className, layoutName, layoutClassCacheDir, cacheClassFile, cacheInfoFile, generateClassInfo, GenerateFieldInfoMap(), listOf(), listOf(), xmlTypeInfoList.size)
                            finalGenCacheInfoMap[layoutName] = classGenInfo
                            layoutGenStateMap[layoutName] = true
                            genClassCacheInfoList.add(classGenInfo)
                            useCacheLayoutNameList.add(layoutName)

                            if (!hasInclude) {
                                var idChange = false
                                if (generateClassInfo.usedReferenceRMap.isNotEmpty()) {
                                    run loopBreak@{
                                        generateClassInfo.usedReferenceRMap.forEach { (rName, _) ->
                                            if (idChangeMap[rName] != null) {
                                                LogUtil.pl("$layoutName regen by R change $rName")
                                                idChange = true
                                                return@loopBreak
                                            }
                                        }
                                    }
                                }
                                if (idChange) {
                                    /*waitableExecutor.execute {
                                        createClassFileWithCache(layoutName, classGenInfo)
                                    }*/
                                    createClassFileWithCache(layoutName, classGenInfo)
                                } else {
                                    PoolManager.pool.appendClassPath(classGenInfo.cacheRootDir.absolutePath)
                                }
                            } else {
                                cacheQueue.add(classGenInfo)
                            }
                            getLayoutGenResultMapFromFinalResultMap(layoutName).putAll(generateClassInfo.genReportInfo)
                        }
                        return@xmlLoopContinue
                    } else {
                        if (isGenFailed) {
                            LogUtil.pl("regen: $className")
                        } else {
                            LogUtil.pl("change: $className")
                        }
                        layoutClassCacheDir.delete()
                    }
                } else {
                    layoutClassCacheDir.delete()
                }
            }
        }
        if (hasIncludeLayoutNameMap.isNotEmpty()) {
            if (hasIncludeLayoutNameMap.size != preIncludeLayoutSize) {
                val hasIncludeLayoutNameList = mutableListOf<String>().apply {
                    addAll(hasIncludeLayoutNameMap.keys)
                }
                val resolvedIncludeLayoutNameList = checkGenCache(finalGenCacheInfoMap, hasIncludeLayoutNameList, layoutInfoMap, genClassCacheInfoList, hasIncludeLayoutNameList.size)
                useCacheLayoutNameList.addAll(resolvedIncludeLayoutNameList)
            } else {
                hasIncludeLayoutNameMap.forEach { (hasIncludeLayoutName, _) ->
                    LogUtil.pl("regen by include: $hasIncludeLayoutName")
                    val layoutClassCacheDir = genClassInfoCacheDir.resolve(hasIncludeLayoutName)
                    layoutClassCacheDir.delete()
                }
            }
        }
        return useCacheLayoutNameList
    }

    private fun getLayoutGenResultMapFromFinalResultMap(layoutName: String): HashMap<String, ViewGenResultInfo> {
        var layoutGenResultMap = finalGenResultMap[layoutName]
        if (layoutGenResultMap == null) {
            layoutGenResultMap = hashMapOf()
            finalGenResultMap[layoutName] = layoutGenResultMap
        }
        return layoutGenResultMap
    }
}

//classFile cache文件
data class ClassGenCacheInfo(val cacheValid: Boolean,  //cache是否有效
                             val name: String,         //class name
                             val layoutName: String,         //class name
                             val cacheRootDir: File,   //cache根目录
                             val classCacheFile: File, //生成的class缓存文件
                             val cacheInfoFile: File,  //生成结果的缓存文件
                             val generateClassInfo: GenerateClassInfo, //生成结果的信息
                             val generateFieldInfoMap: GenerateFieldInfoMap, //生成时用到的共享变量信息
                             val successLayoutTypeGenInfoList: List<LayoutTypeGenInfo>,   //生成成功的type, eg: layout, layout-v20
                             val failedLayoutTypeGenInfoList: List<LayoutTypeGenInfo>,    //生成失败的type, eg: layout, layout-v20
                             val layoutTypeAmount: Int)              //总共的type数量

data class ViewGenResultInfo(val layoutType: String, val result: GenResult, val info: String, val noGenViewClassName: String = "")
enum class GenResult {
    NO_GEN_CLASS, STYLE_UN_SUPPORT, REFERENCE_STYLE_UN_SUPPORT, VALUE_MATCH_ERROR, IGNORE, ATTR_UN_IMPLEMENT, GEN_FAILED, WAIT_INCLUDE, SUCCESS
}

interface QxmlGenResultProvider {
    fun getGenInfoList(): List<String>
    fun getGenReport(): Map<String, HashMap<String, ViewGenResultInfo>>
}

data class LayoutTypeGenInfo(val layoutType: String, val isMerge: Boolean)

data class DataBindingAttrResolveInfo(var dataBindingCount: Int, var tagNode: groovy.xml.QName? = null, var tagValue: String? = null, var useDataBinding: Boolean = false) {
    fun clearDataBinding() {
        tagNode = null
        tagValue = null
        useDataBinding = false
    }
}