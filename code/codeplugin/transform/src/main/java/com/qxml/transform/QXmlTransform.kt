package com.qxml.transform

import com.android.build.api.transform.*
import com.google.common.collect.ImmutableList
import com.google.common.io.Files
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.qxml.QxmlConfigExtension
import com.qxml.QxmlExtension
import com.qxml.constant.Constants
import com.qxml.tools.log.LogUtil
import com.qxml.tools.model.GenClassInfoModel
import com.qxml.transform.collect.*
import com.qxml.transform.generate.LayoutClassGenerator
import com.qxml.transform.generate.model.LayoutFileInfo
import com.qxml.transform.generate.model.StyleInfo
import com.qxml.transform.generate.tools.GenReportTool
import com.qxml.transform.pool.PoolManager
import com.qxml.transform.transform.BaseTransform
import com.qxml.transform.transform.CodeTransformer
import javassist.*
import org.gradle.api.Project
import java.io.*
import java.util.*
import java.util.function.BiConsumer
import java.util.jar.JarFile
import java.util.zip.ZipEntry
import kotlin.collections.HashMap

data class TransformConfig(var projectDebuggable: Boolean = true)

@Suppress("UnstableApiUsage")
class QXmlTransform(private val project: Project): BaseTransform() {

    var packageName: String = ""
    var curBuildType: String = ""
    val transformConfig = TransformConfig()

    private lateinit var curBuildTypeCapitalize: String

    private val layoutFileInfoCollector by lazy { LayoutFileInfoCollector() }

    private val genClassInfoCacheDir by lazy { project.buildDir.resolve(Constants.QXML_DIR_NAME).resolve(
        Constants.GEN_CLASS_CACHE_DIR) }

    private lateinit var layoutFileInfoMap: HashMap<String, HashMap<String, LayoutFileInfo>>
    private val genClassInfoList = mutableListOf<String>()

    private lateinit var layoutIdMap: Map<String, Int>
    private lateinit var idMap: Map<String, Int>

    private val gson by lazy { GsonBuilder().disableHtmlEscaping().create() }

    private var reTransformQxmlJarFound = false

    private val cpList = mutableListOf<ClassPath>()

    private lateinit var qxmlConfig: QxmlConfigExtension

    private var layoutFileInfoList: MutableList<LayoutFileInfo>? = null

    private val cacheDir by lazy { project.buildDir.resolve(Constants.QXML_CACHE_PATH) }

    private val layoutInfoFile by lazy {
        cacheDir.resolve(Constants.QXML_LAYOUT_CACHE_DIR_NAME)
            .resolve(Constants.QXML_LAYOUT_CACHE_FILE_NAME)
    }

    private val styleInfoFile by lazy {
        cacheDir.resolve(Constants.QXML_STYLE_CACHE_DIR_NAME)
            .resolve(Constants.QXML_STYLE_CACHE_FILE_NAME)
    }

    override fun provideFunction(): BiConsumer<InputStream, OutputStream> {
        return BiConsumer<InputStream, OutputStream> { input, output ->
            if (qxmlConfig.enable) {
                CodeTransformer.transform(input, output, genClassInfoList, packageName, layoutIdMap)
            } else {
                output.write(input.readBytes())
            }
        }
    }

    override fun initTransform(transformInvocation: TransformInvocation) {
        qxmlConfig = project.extensions.getByType(QxmlExtension::class.java).getConfigByBuildType(curBuildType)
        LogUtil.enable = qxmlConfig.logEnable
        LogUtil.debug = qxmlConfig.debugEnable

        CodeTransformer.qxmlInitContentCacheFile = cacheDir.resolve(Constants.QXML_INIT_CONTENT_DIR).resolve(Constants.QXML_INIT_CONTENT_CACHE_FILE)

        curBuildTypeCapitalize = curBuildType.capitalize(Locale.ROOT)

        LogUtil.pl("transform start $packageName")

        ClassPool.cacheOpenedJarFile = false
        PoolManager.pool = PoolManager.initPool()

        val layoutIdMapFile = project.buildDir.resolve("${Constants.QXML_CACHE_DIR}${curBuildType}").resolve(Constants.LAYOUT_ID_COLLECT_FILE_NAME)
        val layoutIdMapJsonStr = if (layoutIdMapFile.exists()) layoutIdMapFile.readText() else ""
        layoutIdMap = if (layoutIdMapJsonStr.isEmpty()) hashMapOf() else gson.fromJson(layoutIdMapJsonStr, object : TypeToken<HashMap<String, Int>>() {}.type)

        val idMapFile = project.buildDir.resolve("${Constants.QXML_CACHE_DIR}${curBuildType}").resolve(Constants.ID_COLLECT_FILE_NAME)
        val idMapJsonStr = if (idMapFile.exists()) idMapFile.readText() else ""
        idMap = if (idMapJsonStr.isEmpty()) hashMapOf() else gson.fromJson(idMapJsonStr, object : TypeToken<HashMap<String, Int>>() {}.type)

        var time = System.currentTimeMillis()

        //收集attr信息
        val attrsXmlParser = AttrsXmlParser(project, curBuildTypeCapitalize)
        val attrInfoMap = attrsXmlParser.parse()
        LogUtil.pl("attr collect time cost: " + (System.currentTimeMillis() - time) + "ms")
        time = System.currentTimeMillis()

        //layout信息
        layoutFileInfoList = gson.fromJson(layoutInfoFile.readText(), object : TypeToken<List<LayoutFileInfo>>() {}.type)

        val processOrOptimizedResInfoFile = cacheDir.resolve(curBuildType).resolve(Constants.RES_PROCESS_OR_OPTIMIZED_INFO_COLLECT_FILE_NAME)

        //layout type信息
        val layoutTypeInfoMap: Map<String, Map<String, String>> = gson.fromJson(processOrOptimizedResInfoFile.readText(), object : TypeToken<Map<String, Map<String, String>>>() {}.type)

        //style信息
        val styleInfoMap: TreeMap<String, TreeMap<String, StyleInfo>> = gson.fromJson(styleInfoFile.readText(), object : TypeToken<TreeMap<String, TreeMap<String, StyleInfo>>>() {}.type)

        layoutFileInfoMap = layoutFileInfoCollector.collect(layoutFileInfoList!!, layoutTypeInfoMap)
        layoutFileInfoList!!.forEach {
            LogUtil.d("layout: "+it)
        }
        LogUtil.pl("style and layout collect time cost: " + (System.currentTimeMillis() - time) + "ms")
        time = System.currentTimeMillis()

        val inputs = transformInvocation.inputs
        val classPathList = mutableListOf<String>()

        cpList.add(PoolManager.pool.insertClassPath(attrsXmlParser.androidJar.absolutePath))

        reTransformQxmlJarFound = false

        val isAndroidx = (project.properties["android.useAndroidX"] as? String)?.toBoolean() ?: false

        if (qxmlConfig.enable) {

            val genClassInfoModelJsonStr = loadClassPathAndViewParseList(inputs, classPathList)

            if (LogUtil.debug) {
                val configFile = project.buildDir.resolve(Constants.QXML_CACHE_PATH).resolve(Constants.QXML_CONFIG_CACHE_FILE_NAME)
                Files.createParentDirs(configFile)
                if (!configFile.exists()) {
                    configFile.createNewFile()
                }
                configFile.writeText(genClassInfoModelJsonStr)
            }

            val genClassInfoModel = gson.fromJson(genClassInfoModelJsonStr, GenClassInfoModel::class.java)
            val genInfoMap = ViewGenInfoCombiner.viewGenInfoCombine(genClassInfoModel)

            var spend = (System.currentTimeMillis() - time)
            LogUtil.pl("qxml config combine time cost: " + spend + "ms")
            time = System.currentTimeMillis()

            val genInfoHolder = ViewGenInfoHolderImpl(genInfoMap, genClassInfoModel
                , cacheDir.resolve(Constants.LOCAL_VAR_DEF_CONTENT_CACHE_FILE_NAME)
                , qxmlConfig, waitableExecutor, styleInfoMap, genClassInfoModelJsonStr
                , layoutTypeInfoMap, cacheDir.resolve(Constants.GEN_MODEL_CACHE_DIR), gson)

            if (LogUtil.debug) {
                val content = gson.toJson(genInfoMap)
                val cache = project.buildDir.resolve(Constants.QXML_CACHE_PATH).resolve(Constants.QXML_VIEW_GEN_INFO_CACHE_FILE_NAME)
                Files.createParentDirs(cache)
                if (!cache.exists()) {
                    cache.createNewFile()
                }
                cache.writeText(content)
            }

            spend = (System.currentTimeMillis() - time)
            LogUtil.pl("qxml config holder time cost: " + spend + "ms")
            time = System.currentTimeMillis()

            classPathList.forEach {
                PoolManager.pool.appendClassPath(it)
                //cpList.add(PoolManager.pool.appendClassPath(it))
            }
            time = System.currentTimeMillis()

            val idMapCacheFile = project.buildDir.resolve("${Constants.QXML_CACHE_DIR}${curBuildType}").resolve(Constants.ID_COLLECT_CACHE_FILE_NAME)
            val idMapCacheJsonStr = if (idMapCacheFile.exists()) idMapCacheFile.readText() else ""
            val idCacheMap: Map<String, Int> = if (idMapCacheJsonStr.isEmpty()) hashMapOf() else gson.fromJson(idMapCacheJsonStr, object : TypeToken<HashMap<String, Int>>() {}.type)

            val result = LayoutClassGenerator(isAndroidx, transformInvocation.outputProvider
                , layoutFileInfoMap, layoutTypeInfoMap, attrInfoMap, genInfoHolder, packageName
                , waitableExecutor, genClassInfoCacheDir, qxmlConfig, transformConfig
                , genClassInfoModel.compatViewInfoModelMap, idMap, idCacheMap).generate()


            LogUtil.pl("code generate time cost: " + (System.currentTimeMillis() - time) + "ms")

            genClassInfoList.clear()
            genClassInfoList.addAll(result.getGenInfoList())

            GenReportTool.genReport(project.buildDir.resolve("qxml").resolve("report.html"), result.getGenReport())

            if (!idMapCacheFile.exists()) {
                Files.createParentDirs(idMapCacheFile)
                idMapCacheFile.createNewFile()
            }
            idMapCacheFile.writeText(idMapJsonStr)
        }
    }

    override fun finishTransform() {
        /*cpList.forEach {
            PoolManager.pool.removeClassPath(it)
        }*/
    }

    override fun shouldJarEntryPackage(entry: ZipEntry): Boolean {
        //不打包config文件
        return entry.name != Constants.QXML_PARSE_FINAL_CONFIG_FILE_NAME && entry.name != Constants.QXML_PARSE_CONFIG_FILE_NAME
    }

    override fun shouldProcessNotChangeJar(inputJar: JarInput): Boolean {
        if (reTransformQxmlJarFound) {
            return false
        }
        val inputFile = inputJar.file
        val name = inputJar.name

        //qxmlInflater需要transform
        if (name.equals(":qxml-lib", true) || (name.startsWith("com.qxml.lib:base:"))) {
            val jarFile = JarFile(inputFile)
            val qxmlEntry = jarFile.getJarEntry(Constants.QXML_CLASS_ENTRY_NAME)
            if (qxmlEntry != null) {
                reTransformQxmlJarFound = true
            }
            jarFile.close()
        }
        return reTransformQxmlJarFound
    }

    /**
     * 获取qxml_config
     */
    private fun loadClassPathAndViewParseList(
        inputs: Collection<TransformInput>,
        classPathList: MutableList<String>
    ): String {
        var genClassInfoModelStr: String? = null
        var configFileFound = false
        for (input in inputs) {
            for (jarInput in input.jarInputs) {
                classPathList.add(jarInput.file.absolutePath)
                if (!configFileFound && jarInput.contentTypes.contains(QualifiedContent.DefaultContentType.RESOURCES)) {
                    val jarFile = JarFile(jarInput.file.absolutePath)
                    val entry = jarFile.getJarEntry(Constants.QXML_PARSE_FINAL_CONFIG_FILE_NAME)
                    if (entry != null) {
                        val ins = jarFile.getInputStream(entry)
                        genClassInfoModelStr = ins.reader().readText()
                        ins.close()
                        configFileFound = true
                    }
                    jarFile.close()
                }
            }
            for (directoryInput in input.directoryInputs) {
                classPathList.add(directoryInput.file.absolutePath)
            }
        }
        return genClassInfoModelStr ?: error("请配置qxml依赖")
    }

    /**
     * layout和style变化时触发transform
     */
    override fun getSecondaryFiles(): MutableCollection<SecondaryFile> {
        //from tx shadow
        val transformJar = File(this::class.java.protectionDomain.codeSource.location.toURI())
        val transformKitJar = File(QXmlTransform::class.java.protectionDomain.codeSource.location.toURI())

        return ImmutableList.of(
            //SecondaryFile.incremental(project.files(processOrOptimizedResInfoFile)),
            SecondaryFile.incremental(project.files(layoutInfoFile)),
            SecondaryFile.incremental(project.files(styleInfoFile)),
            SecondaryFile.nonIncremental(project.files(transformJar)),
            SecondaryFile.nonIncremental(project.files(transformKitJar)))
    }

    override fun isCacheable() = true
}