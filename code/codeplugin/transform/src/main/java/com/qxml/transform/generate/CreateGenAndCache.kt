@file:Suppress("UnstableApiUsage")

package com.qxml.transform.generate

import com.android.SdkConstants
import com.android.ide.common.internal.WaitableExecutor
import com.google.common.io.Files
import com.google.gson.Gson
import com.qxml.QxmlConfigExtension
import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.AndroidViewNameCorrect
import com.qxml.tools.LayoutTypeNameCorrect
import com.qxml.tools.log.LogUtil
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.tools.model.CompatViewInfoModel
import com.qxml.transform.TransformConfig
import com.qxml.transform.collect.ViewGenInfoHolderImpl
import com.qxml.transform.generate.match.AttrMethodValueMatcher
import com.qxml.transform.generate.model.*
import com.squareup.javapoet.CodeBlock
import groovy.util.Node
import groovy.util.XmlParser
import java.io.File
import java.lang.StringBuilder
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap

private const val FIELD_VIEW_NAME_PREFIX = "___view_"
private const val DEFAULT_LAYOUT_TYPE = "layout"
private val ANDROID_LAYOUT_QNAME = groovy.xml.QName(Constants.ANDROID_NAME_SPACE_URI, Constants.ATTR_NAME_LAYOUT, Constants.ATTR_PREFIX_ANDROID)


interface CreateGenAndCache: CreateView, CreateClassFile {

    /**
     * 生成gen的内容与结果
     * @param finalGenCacheInfoMap <layoutName, ClassGenInfo>: layout的生成信息
     * @param layoutInfoMap: layout信息
     * @param unGenLayoutNameList: 未处理的layout信息
     * @param genClassCacheInfoList: 已处理的信息
     *
     * @return 未解决的layoutName
     */
     fun createGenContentAndGenInfoResult(isAndroidx: Boolean, finalGenCacheInfoMap: ConcurrentHashMap<String, ClassGenCacheInfo>
                                          , layoutInfoMap: HashMap<String, HashMap<String, LayoutFileInfo>>
                                          , unGenLayoutNameList: List<String>
                                          , genClassCacheInfoList: MutableList<ClassGenCacheInfo>
                                          , waitableExecutor: WaitableExecutor, layoutGenStateMap: ConcurrentHashMap<String, Boolean>
                                          , genClassInfoCacheDir: File, packageName: String, gson: Gson
                                          , finalGenResultMap: ConcurrentHashMap<String, HashMap<String, ViewGenResultInfo>>
                                          , qxmlExtension: QxmlConfigExtension
                                          , transformConfig: TransformConfig
                                          , attrMethodValueMatcher: AttrMethodValueMatcher
                                          , viewGenInfoHolder: ViewGenInfoHolderImpl
                                          , compatViewInfoMap: Map<String, CompatViewInfoModel>
                                          , styleInfoMap: Map<String, Map<String, StyleInfo>>
                                          , layoutTypeInfoMap: Map<String, Map<String, String>>
                                          , idMap: Map<String, Int>
    ): MutableList<String> {

        val includeReferenceLayoutNameMap = ConcurrentHashMap<String, String>()
        val waitToGenLayoutNameList = Collections.synchronizedList(mutableListOf<String>())
        unGenLayoutNameList.forEach xmlLoopContinue@{ layoutName ->
            layoutInfoMap[layoutName]?.let { layoutFileInfoMap ->
                val layoutFileInfoList = mutableListOf<LayoutFileInfo>()
                layoutFileInfoMap.forEach { (_, layoutFileInfo) ->
                    layoutFileInfoList.add(layoutFileInfo)
                }
                layoutFileInfoList.sort()
                val layoutClassCacheDir = genClassInfoCacheDir.resolve(layoutName)

                val cacheFileName = getGenClassCacheFileName("${layoutName}${makeQxmlConfigVerifyKey(qxmlExtension, transformConfig)}")
                val cacheInfoFile = layoutClassCacheDir.resolve(cacheFileName)
                val className = makeGenClassName(layoutName)
                val cacheClassFile = layoutClassCacheDir.resolve(className.replace(".", File.separator) + SdkConstants.DOT_CLASS)

                //waitableExecutor.execute {
                run execute@{
                    val parser = getXmlParser()
                    var viewIndex = 0

                    val getViewFieldNameFun = {
                        "$FIELD_VIEW_NAME_PREFIX${viewIndex++}"
                    }

                    //不能转换成代码的layout版本
                    val failedLayoutTypeGenInfoList = mutableListOf<LayoutTypeGenInfo>()
                    //能够转换成代码的layout版本
                    val successLayoutTypeGenInfoList = mutableListOf<LayoutTypeGenInfo>()
                    val genTypeInfoMap = hashMapOf<String, CodeBlock.Builder>()
                    //已经用到的gen信息
                    val usedGenInfoMap = hashMapOf<String, HashMap<String, AttrFuncInfoModel>>()
                    //已经用到的onEnd信息
                    val usedOnEndInfoMap = hashMapOf<String, HashMap<String, AttrFuncInfoModel>>()
                    //没有对应实现的attr
                    val usedStyleInfoMap = hashMapOf<String, HashMap<String, StyleInfo>>()
                    //没有对应实现的attr
                    val invalidGenInfoMap = hashMapOf<String, HashMap<String, Int>>()
                    //layout单个type的共享变量
                    val generateFieldInfo = GenerateFieldInfoMap(hashMapOf(), LinkedHashMap())
                    //关联的include layout
                    val relativeIncludeLayoutMap = hashMapOf<String, String>()
                    //使用的R
                    val usedReferenceRMap = hashMapOf<String, Int>()
                    //使用的import
                    val usedImportPackageMap = hashMapOf<String, String>()
                    //用到的localVar
                    val finalUsedLocalVarMap = hashMapOf<String, String>()

                    val qxmlConfigMap = hashMapOf<String, QxmlConfigExtension>()


                    var jumpByInclude = false

                    run layoutTypeLoopBreak@{
                        val key = "R.layout.$layoutName"
                        usedReferenceRMap[key] = idMap[key] ?: 0
                        layoutFileInfoList.forEach layoutTypeLoopContinue@{ xmlTypeInfo ->
                            //用到的临时变量
                            val usedTempVarMap = hashMapOf<String, String>()
                            val qxmlConfig = QxmlConfigExtension("")
                            qxmlConfig.ignoreUnImplementAttr = qxmlExtension.ignoreUnImplementAttr
                            qxmlConfig.useFactory = qxmlExtension.useFactory
                            qxmlConfig.compatMode = qxmlExtension.compatMode
                            qxmlConfig.viewDebug = qxmlExtension.viewDebug
                            qxmlConfig.acceptReferenceStyle = qxmlExtension.acceptReferenceStyle
                            qxmlConfig.useCreateViewListener = qxmlExtension.useCreateViewListener
                            qxmlConfigMap[xmlTypeInfo.type] = qxmlConfig
                            val layoutFile = File(xmlTypeInfo.filePath)
                            if (!layoutFile.exists()) {
                                error("${xmlTypeInfo.filePath}不存在")
                            }
                            val node = parser.parse(layoutFile)

                            val codeBlockBuilder = CodeBlock.builder()
                            val genClassFieldCacheMap = hashMapOf<String, String>()

                            val fieldInfo = FieldInfo(hashMapOf(), LinkedHashMap(), hashMapOf(), mutableListOf())
                            val layoutIsMerge = node.name().toString().equals(Constants.TAG_MERGE, true)

                            methodCreateView(isAndroidx, xmlTypeInfo.name, xmlTypeInfo.type, codeBlockBuilder
                                , "", ""
                                , node, getViewFieldNameFun, genClassFieldCacheMap, usedOnEndInfoMap, usedGenInfoMap
                                , usedStyleInfoMap, invalidGenInfoMap, fieldInfo
                                , includeReferenceLayoutNameMap, layoutIsMerge, qxmlConfig
                                , attrMethodValueMatcher, layoutGenStateMap, relativeIncludeLayoutMap
                                , viewGenInfoHolder, compatViewInfoMap, styleInfoMap, usedReferenceRMap
                                , usedImportPackageMap, finalUsedLocalVarMap, idMap, usedTempVarMap)?.also {
                                viewIndex = 0
                                if (it.result != GenResult.WAIT_INCLUDE) {
                                    collectIncludeLayoutInfo(node, relativeIncludeLayoutMap, attrMethodValueMatcher, usedReferenceRMap, idMap)
                                    failedLayoutTypeGenInfoList.add(LayoutTypeGenInfo(xmlTypeInfo.type, layoutIsMerge))
                                    finalUsedLocalVarMap.clear()
                                    getLayoutGenResultMapFromFinalResultMap(xmlTypeInfo.name, finalGenResultMap)[xmlTypeInfo.type] = it
                                } else { //等待include生成的不算入结果中
                                    jumpByInclude = true
                                    return@layoutTypeLoopBreak
                                }
                                return@layoutTypeLoopContinue
                            }
                            viewIndex = 0

                            //未实现的attr
                            if ((!qxmlExtension.ignoreUnImplementAttr && !qxmlConfig.ignoreUnImplementAttr) || !qxmlConfig.ignoreUnImplementAttr) {
                                if (invalidGenInfoMap.isNotEmpty()) {
                                    failedLayoutTypeGenInfoList.add(LayoutTypeGenInfo(xmlTypeInfo.type, layoutIsMerge))
                                    val errInfoSb = StringBuilder()
                                    errInfoSb.append("未实现的属性:\n")
                                    invalidGenInfoMap.forEach { (viewTypeName, hashMap) ->
                                        errInfoSb.append(" $viewTypeName:\n")
                                        hashMap.forEach { (attrName, _) ->
                                            errInfoSb.append("  $attrName")
                                        }
                                    }
                                    getLayoutGenResultMapFromFinalResultMap(xmlTypeInfo.name, finalGenResultMap)[xmlTypeInfo.type] = makeViewGenResult(layoutName, GenResult.ATTR_UN_IMPLEMENT, errInfoSb.toString())
                                    return@layoutTypeLoopContinue
                                }
                            }

                            val returnViewName = "${FIELD_VIEW_NAME_PREFIX}$viewIndex"

                            if (layoutIsMerge) {
                                //当layout是merge时固定返回null
                                codeBlockBuilder.addStatement("return null")
                            } else {
                                if (qxmlConfig.viewDebug) {
                                    codeBlockBuilder.addStatement("com.qxml.helper.QxmlHelper.showQxmlDebug($returnViewName)")
                                }
                                //添加到rootView
                                codeBlockBuilder.beginControlFlow("if(${Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME} != null && ${Constants.GEN_PARAM_ATTACH_TO_NAME})")
                                codeBlockBuilder.addStatement("${Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME}.addView(${FIELD_VIEW_NAME_PREFIX}0, -1, ${Constants.GEN_FIELD_LAYOUT_PARAMS_ROOT})")
                                codeBlockBuilder.endControlFlow()
                                //attachTo时返回parentView
                                codeBlockBuilder.beginControlFlow("if(${Constants.GEN_PARAM_ATTACH_TO_NAME})")
                                codeBlockBuilder.addStatement("return ${Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME}")
                                codeBlockBuilder.endControlFlow()
                                codeBlockBuilder.addStatement("return $returnViewName")
                            }

                            //LogUtil.pl("codeBlockBuilder \n"+codeBlockBuilder.build())
                            successLayoutTypeGenInfoList.add(LayoutTypeGenInfo(xmlTypeInfo.type, layoutIsMerge))
                            genTypeInfoMap[xmlTypeInfo.type] = codeBlockBuilder
                            generateFieldInfo.add(xmlTypeInfo.type, fieldInfo)
                            getLayoutGenResultMapFromFinalResultMap(xmlTypeInfo.name, finalGenResultMap)[xmlTypeInfo.type] = makeViewGenResult(layoutName, GenResult.SUCCESS, "success")

                        }
                    }

                    releaseXmlParser(parser)

                    if (jumpByInclude) {
                        return@execute
                    }

                    //successLayoutTypes.sort()

                    val genCodeBlockBuilder = CodeBlock.builder()
                    if (layoutFileInfoList.size == 1) {
                        if (failedLayoutTypeGenInfoList.isNotEmpty()) {
                            if (failedLayoutTypeGenInfoList[0].isMerge) {
                                genCodeBlockBuilder.addStatement(makeDefaultInflate(layoutName, packageName))
                                genCodeBlockBuilder.addStatement("return null")
                            } else {
                                genCodeBlockBuilder.addStatement(makeReturnDefaultInflate(layoutName, packageName))
                            }
                        } else {
                            val singleLayoutType = successLayoutTypeGenInfoList[0].layoutType
                            addFieldInfo(generateFieldInfo, singleLayoutType, genCodeBlockBuilder)
                            genCodeBlockBuilder.addStatement(genTypeInfoMap[singleLayoutType]!!.toClearContent())
                        }
                    } else {
                        var isDefaultLayoutTypeGenSuc: Boolean? = true
                        if (failedLayoutTypeGenInfoList.isNotEmpty()) {
                            val sb = StringBuilder()
                            val commentSb = StringBuilder()
                            //失败的merge
                            val failedMergeTypes = mutableListOf<String>()
                            val failedNormalTypes = mutableListOf<String>()
                            failedLayoutTypeGenInfoList.forEach { typeGenInfo ->
                                if (isDefaultLayoutTypeGenSuc != null && isDefaultLayoutTypeGenSuc!! && DEFAULT_LAYOUT_TYPE == LayoutTypeNameCorrect.toDisplayText(typeGenInfo.layoutType)) {
                                    isDefaultLayoutTypeGenSuc = if (typeGenInfo.isMerge) {
                                        null
                                    } else {
                                        false
                                    }
                                } else {
                                    if (typeGenInfo.isMerge) {
                                        failedMergeTypes.add(typeGenInfo.layoutType)
                                    } else {
                                        failedNormalTypes.add(typeGenInfo.layoutType)
                                    }
                                }
                            }
                            if (failedNormalTypes.isNotEmpty()) {
                                failedNormalTypes.forEachIndexed { index, failedType ->
                                    val correctType = LayoutTypeNameCorrect.toDisplayText(failedType)
                                    val typedValueString = layoutTypeInfoMap[layoutName]?.get(correctType) ?: throw RuntimeException("在arsc中未找到${layoutName}的${correctType}类型信息")
                                    sb.append("${Constants.GEN_FIELD_LAYOUT_TYPE_STRING_NAME}.equals(\"$typedValueString\"))")
                                    commentSb.append(" $correctType ")
                                    if (index < failedNormalTypes.size - 1) {
                                        sb.append(" || ")
                                    }
                                }
                                genCodeBlockBuilder.addStatement("//there is normal layout")
                                genCodeBlockBuilder.addStatement("//failed type: $commentSb")
                                genCodeBlockBuilder.beginControlFlow("if($sb)")
                                genCodeBlockBuilder.addStatement(makeReturnDefaultInflate(layoutName, packageName))
                                genCodeBlockBuilder.endControlFlow()
                            }

                            if (failedMergeTypes.isNotEmpty()) {
                                commentSb.clear()
                                sb.clear()
                                failedMergeTypes.forEachIndexed { index, failedType ->
                                    val correctType = LayoutTypeNameCorrect.toDisplayText(failedType)
                                    val typedValueString = layoutTypeInfoMap[layoutName]?.get(correctType) ?: throw RuntimeException("在arsc中未找到${layoutName}的${correctType}类型信息")
                                    sb.append("${Constants.GEN_FIELD_LAYOUT_TYPE_STRING_NAME}.equals(\"$typedValueString\"))")
                                    commentSb.append(" $correctType ")
                                    if (index < failedMergeTypes.size - 1) {
                                        sb.append(" || ")
                                    }
                                }
                                genCodeBlockBuilder.addStatement("//there is merge layout")
                                genCodeBlockBuilder.addStatement("//failed type: $commentSb")
                                genCodeBlockBuilder.beginControlFlow("if($sb)")
                                genCodeBlockBuilder.addStatement(makeDefaultInflate(layoutName, packageName))
                                genCodeBlockBuilder.addStatement("return null")
                                genCodeBlockBuilder.endControlFlow()
                            }
                        }

                        if (successLayoutTypeGenInfoList.isNotEmpty()) {
                            successLayoutTypeGenInfoList.forEach { genInfo ->
                                val type = genInfo.layoutType
                                val correctType = LayoutTypeNameCorrect.toDisplayText(type)

                                if (isDefaultLayoutTypeGenSuc != null && isDefaultLayoutTypeGenSuc!!) {
                                    if (DEFAULT_LAYOUT_TYPE != correctType) {
                                        val typedValueString = layoutTypeInfoMap[layoutName]?.get(correctType) ?: throw RuntimeException("在arsc中未找到${layoutName}的${correctType}类型信息")

                                        genCodeBlockBuilder.addStatement("//type: $correctType")
                                        genCodeBlockBuilder.beginControlFlow("if(${Constants.GEN_FIELD_LAYOUT_TYPE_STRING_NAME}.equals(\"$typedValueString\"))")

                                        addFieldInfo(generateFieldInfo, type, genCodeBlockBuilder)
                                        genCodeBlockBuilder.addStatement(genTypeInfoMap[type]!!.toClearContent())
                                        genCodeBlockBuilder.endControlFlow()
                                    }
                                }
                            }
                        }
                        if (isDefaultLayoutTypeGenSuc == null || !isDefaultLayoutTypeGenSuc!!) {
                            genCodeBlockBuilder.addStatement("//type: $DEFAULT_LAYOUT_TYPE gen failed")
                            genCodeBlockBuilder.addStatement(makeReturnDefaultInflate(layoutName, packageName))
                        } else {
                            val defaultType = ""
                            genCodeBlockBuilder.addStatement("//type: $DEFAULT_LAYOUT_TYPE")
                            addFieldInfo(generateFieldInfo, defaultType, genCodeBlockBuilder)
                            genCodeBlockBuilder.addStatement(genTypeInfoMap[defaultType]!!.toClearContent())
                        }
                    }
                    val methodContent = genCodeBlockBuilder.toClearContent()
                    //LogUtil.pl("final code "+layoutName+" \n"+methodContent)
                    val classGenInfo = ClassGenCacheInfo(false, className, layoutName, layoutClassCacheDir, cacheClassFile, cacheInfoFile
                        , GenerateClassInfo(usedGenInfoMap, usedOnEndInfoMap, usedStyleInfoMap, invalidGenInfoMap
                            , relativeIncludeLayoutMap, usedReferenceRMap, usedImportPackageMap, getLayoutGenResultMapFromFinalResultMap(layoutName, finalGenResultMap), getCacheVerifyKey(layoutFileInfoList, qxmlExtension, qxmlConfigMap, transformConfig), layoutTypeInfoMap, methodContent)
                        , generateFieldInfo, successLayoutTypeGenInfoList, failedLayoutTypeGenInfoList, layoutFileInfoList.size)

                    try {
                        createClassFile(layoutName, classGenInfo, packageName, finalUsedLocalVarMap, usedImportPackageMap, viewGenInfoHolder, qxmlExtension, gson)
                        finalGenCacheInfoMap[layoutName] = classGenInfo
                        genClassCacheInfoList.add(classGenInfo)
                    } catch (e: Exception) {
                        val layoutResultMap = getLayoutGenResultMapFromFinalResultMap(layoutName, finalGenResultMap)
                        layoutResultMap.forEach { (type, _) ->
                            val errInfoFile = layoutClassCacheDir.resolve("genFailedInfo.txt")
                            Files.createParentDirs(errInfoFile)
                            if (!errInfoFile.exists()) {
                                errInfoFile.createNewFile()
                            }
                            errInfoFile.writeText(classGenInfo.generateClassInfo.methodContent)
                            layoutResultMap[type] = makeViewGenResult(layoutName, GenResult.GEN_FAILED, "$e，请打开${errInfoFile.absolutePath.substring(errInfoFile.absolutePath.indexOf("build${File.separator}qxml"))}查看")
                        }
                    } finally {
                        layoutGenStateMap[layoutName] = true
                    }
                }
            }
        }
        //waitableExecutor.waitForTasksWithQuickFail<Any>(true)
        //没有做排序
        includeReferenceLayoutNameMap.forEach { (layoutName, _) ->
            waitToGenLayoutNameList.add(layoutName)
        }
        if (waitToGenLayoutNameList.isNotEmpty()) {
            LogUtil.pl("un resolve layout $waitToGenLayoutNameList")
        }
        return waitToGenLayoutNameList
    }

    //生成失败时也收集关联的layout，用于删除已转换layout文件时过滤不能删除的文件，目前仅收集include和viewStub关联的layout
    private fun collectIncludeLayoutInfo(node: Node, includeLayoutInfoMap: HashMap<String, String>
                                         , attrMethodValueMatcher: AttrMethodValueMatcher, usedReferenceRMap: HashMap<String, Int>
                                         , idMap: Map<String, Int>) {
        val originName = node.name().toString()
        if (originName == Constants.TAG_INCLUDE) {
            val attributes = node.attributes()
            val layoutIdStr = attributes[Constants.LAYOUT_LAYOUT] as? String
            if (layoutIdStr != null) {
                val valueInfo = attrMethodValueMatcher.getValueInfo("", layoutIdStr, usedReferenceRMap, idMap)
                if (valueInfo.valueType == ValueType.REFERENCE_LAYOUT) {//只处理layout
                    val includeLayoutFullName = valueInfo.value
                    val includeLayoutName = includeLayoutFullName.substring(includeLayoutFullName.lastIndexOf(".") + 1)
                    includeLayoutInfoMap[includeLayoutName] = ""
                }
            }
        } else if (originName == AndroidViewNameCorrect.VIEW_STUB_FULL_NAME || originName == AndroidViewNameCorrect.VIEW_STUB) {
            val attributes = node.attributes()
            val layoutIdStr = attributes[ANDROID_LAYOUT_QNAME]?.toString()
            if (layoutIdStr != null) {
                val valueInfo = attrMethodValueMatcher.getValueInfo("", layoutIdStr, usedReferenceRMap, idMap)
                if (valueInfo.valueType == ValueType.REFERENCE_LAYOUT) {//只处理layout
                    val includeLayoutFullName = valueInfo.value
                    val includeLayoutName = includeLayoutFullName.substring(includeLayoutFullName.lastIndexOf(".") + 1)
                    includeLayoutInfoMap[includeLayoutName] = ""
                }
            }
        }
        node.children().forEach {
            (it as? Node)?.also { childNode ->
                collectIncludeLayoutInfo(childNode, includeLayoutInfoMap, attrMethodValueMatcher, usedReferenceRMap, idMap)
            }
        }
    }

    private fun addFieldInfo(generateFieldInfo: GenerateFieldInfoMap, type: String, genCodeBlockBuilder: CodeBlock.Builder) {
        generateFieldInfo.allSizeMap[type]?.forEach { (_, initBlock) ->
            genCodeBlockBuilder.addStatement(initBlock)
        }
        generateFieldInfo.allContextThemeWrapMap[type]?.forEach { (_, initBlock) ->
            genCodeBlockBuilder.addStatement(initBlock)
        }
        generateFieldInfo.allNewViewMap[type]?.forEach { (_, newViewBlocList) ->
            newViewBlocList.forEach { newViewBloc ->
                genCodeBlockBuilder.addStatement(newViewBloc)
            }
        }
        generateFieldInfo.allCreateViewInfoList[type]?.apply {
            if (isNotEmpty()) {
                genCodeBlockBuilder.beginControlFlow("if (${Constants.QXML_INFLATER_CLASS_NAME}.${Constants.QXML_INFLATER_CREATE_VIEW_LISTENER_NAME} != null)")
                forEach { createViewInfo ->
                    genCodeBlockBuilder.addStatement("${Constants.QXML_INFLATER_CLASS_NAME}.${Constants.QXML_INFLATER_CREATE_VIEW_LISTENER_NAME}.onCreateView(${if (createViewInfo.parentViewName.isEmpty()) "null" else createViewInfo.parentViewName}, ${createViewInfo.viewFieldName}, ${createViewInfo.contextName}, \"${createViewInfo.viewClassName}\", \"${createViewInfo.originViewClassName}\")")
                }
                genCodeBlockBuilder.endControlFlow()
            }
        }
    }

    fun getLayoutGenResultMapFromFinalResultMap(layoutName: String, finalGenResultMap: ConcurrentHashMap<String, HashMap<String, ViewGenResultInfo>>): HashMap<String, ViewGenResultInfo> {
        var layoutGenResultMap = finalGenResultMap[layoutName]
        if (layoutGenResultMap == null) {
            layoutGenResultMap = hashMapOf()
            finalGenResultMap[layoutName] = layoutGenResultMap
        }
        return layoutGenResultMap
    }

    private fun makeReturnDefaultInflate(layoutName: String, packageName: String): String {
        return "return ${makeDefaultInflate(layoutName, packageName)}"
    }

    private fun makeDefaultInflate(layoutName: String, packageName: String): String {
        return "${Constants.GEN_PARAM_INFLATE_NAME}.inflate(${packageName}.R.layout.${layoutName}, ${Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME}, ${Constants.GEN_PARAM_ATTACH_TO_NAME})"
    }

    private fun CodeBlock.Builder.toClearContent(): String {
        val methodContent = build().toString()
        return methodContent.substring(0, methodContent.length - 2)
    }

    fun getCacheVerifyKey(xmlTypeInfoList: MutableList<LayoutFileInfo>, qxmlExtension: QxmlConfigExtension, qxmlConfigMap: HashMap<String, QxmlConfigExtension>? = null, transformConfig: TransformConfig): String {
        val stringBuilder = StringBuilder()
        xmlTypeInfoList.forEach { typeInfo ->
            stringBuilder.append("_${typeInfo.type}_${File(typeInfo.filePath).lastModified()}")
            if (qxmlConfigMap != null) {
                qxmlConfigMap[typeInfo.type]?.also {
                    stringBuilder.append(makeQxmlConfigVerifyKey(it, transformConfig))
                }
            } else {
                stringBuilder.append(makeQxmlConfigVerifyKey(qxmlExtension, transformConfig))
            }
        }
        return stringBuilder.toString()
    }


    fun getGenClassCacheFileName(name: String): String = "${name}_genClass.txt"
    fun getXmlParser(): XmlParser
    fun releaseXmlParser(xmlParser: XmlParser)

    fun makeQxmlConfigVerifyKey(qxmlConfig: QxmlConfigExtension,
                                transformConfig: TransformConfig): String {
        return "_useFactory_${qxmlConfig.useFactory}_viewDebug_${qxmlConfig.viewDebug}_compatMode_${qxmlConfig.compatMode}_useCreateViewListener_${qxmlConfig.useCreateViewListener}"
    }

}