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
import com.qxml.transform.generate.tools.GenReportTool
import com.qxml.transform.pool.PoolManager
import com.qxml.transform.transform.BaseTransform
import com.qxml.transform.transform.CodeTransformer
import javassist.*
import org.gradle.api.Project
import java.io.*
import java.util.function.BiConsumer
import java.util.jar.JarFile
import java.util.zip.ZipEntry

class QXmlTransform(private val project: Project): BaseTransform() {

    var packageName: String = ""
    var curBuildType: String = ""
    private lateinit var curBuildTypeCapitalize: String

    private val layoutFileInfoCollector by lazy { LayoutFileInfoCollector(layoutPaths) }

    private val layoutFile by lazy { project.buildDir.resolve(Constants.QXML_CACHE_PATH).resolve(
        Constants.LAYOUTS_FILE_NAME) }

    private val layoutPaths by lazy { layoutFile.readLines() }

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

    override fun provideFunction(): BiConsumer<InputStream, OutputStream>? {
        return BiConsumer<InputStream, OutputStream> { input, output ->
            if (qxmlConfig.enable) {
                CodeTransformer.transform(input, output, genClassInfoList, packageName, layoutIdMap)
            } else {
                output.write(input.readBytes())
            }
        }
    }

    override fun initTransform(transformInvocation: TransformInvocation) {
        ClassPool.cacheOpenedJarFile = false
        PoolManager.pool = PoolManager.initPool()

        qxmlConfig = project.extensions.getByType(QxmlExtension::class.java).getConfigByBuildType(curBuildType)
        curBuildTypeCapitalize = curBuildType.capitalize()

        val layoutIdMapFile = project.buildDir.resolve("${Constants.LAYOUT_ID_COLLECT_PATH}${curBuildType}").resolve(Constants.LAYOUT_ID_COLLECT_FILE_NAME)
        val layoutIdMapJsonStr = if (layoutIdMapFile.exists()) layoutIdMapFile.readText() else ""
        layoutIdMap = if (layoutIdMapJsonStr.isEmpty()) hashMapOf() else gson.fromJson(layoutIdMapJsonStr, object : TypeToken<HashMap<String, Int>>() {}.type)

        val idMapFile = project.buildDir.resolve("${Constants.LAYOUT_ID_COLLECT_PATH}${curBuildType}").resolve(Constants.ID_COLLECT_FILE_NAME)
        val idMapJsonStr = if (idMapFile.exists()) idMapFile.readText() else ""
        idMap = if (idMapJsonStr.isEmpty()) hashMapOf() else gson.fromJson(idMapJsonStr, object : TypeToken<HashMap<String, Int>>() {}.type)


        LogUtil.enable = qxmlConfig.logEnable
        var time = System.currentTimeMillis()

        LogUtil.pl("transform start $packageName")

        //收集attr信息
        val attrsXmlParser = AttrsXmlParser(project, curBuildTypeCapitalize)
        val attrInfoMap = attrsXmlParser.parse()
        LogUtil.pl("attr collect time cost: " + (System.currentTimeMillis() - time) + "ms")
        time = System.currentTimeMillis()

        //收集layout信息
        val layoutFileInfoList = LayoutFileCollector(project, curBuildTypeCapitalize).collect()

        //收集style信息
        val styleInfoMap = StyleCollector(project, curBuildTypeCapitalize).collect()

        layoutFileInfoMap = layoutFileInfoCollector.collect(layoutFileInfoList)
        layoutFileInfoList.forEach {
            LogUtil.d("layout: "+it)
        }
        LogUtil.pl("style and layout collect time cost: " + (System.currentTimeMillis() - time) + "ms")
        time = System.currentTimeMillis()

        val inputs = transformInvocation.inputs
        val classPathList = mutableListOf<String>()

        cpList.add(PoolManager.pool.insertClassPath(attrsXmlParser.androidJar.absolutePath))

        val genClassInfoModel = loadClassPathAndViewParseList(inputs, classPathList)
        val genInfoMap = ViewGenInfoCombiner.viewGenInfoCombine(genClassInfoModel)

        var spend = (System.currentTimeMillis() - time)
        LogUtil.pl("qxml config combine time cost: " + spend + "ms")
        time = System.currentTimeMillis()

        val genInfoHolder = ViewGenInfoHolderImpl(genInfoMap, genClassInfoModel
            , project.buildDir.resolve(Constants.QXML_CACHE_PATH).resolve(Constants.LOCAL_VAR_DEF_CONTENT_CACHE_FILE_NAME)
            , qxmlConfig, waitableExecutor, styleInfoMap)


        spend = (System.currentTimeMillis() - time)
        LogUtil.pl("qxml config holder time cost: " + spend + "ms")
        time = System.currentTimeMillis()

        val content = gson.toJson(genInfoMap)
        val cache = project.buildDir.resolve(Constants.QXML_CACHE_PATH).resolve(Constants.QXML_VIEW_GEN_INFO_CACHE_FILE_NAME)
        Files.createParentDirs(cache)
        if (!cache.exists()) {
            cache.createNewFile()
        }
        cache.writeText(content)

        classPathList.forEach {
            PoolManager.pool.appendClassPath(it)
            //cpList.add(PoolManager.pool.appendClassPath(it))
        }
        time = System.currentTimeMillis()

        reTransformQxmlJarFound = false


        val isAndroidx = (project.properties["android.useAndroidX"] as? String)?.toBoolean() ?: false

        if (qxmlConfig.enable) {

            val idMapCacheFile = project.buildDir.resolve("${Constants.LAYOUT_ID_COLLECT_PATH}${curBuildType}").resolve(Constants.ID_COLLECT_CACHE_FILE_NAME)
            val idMapCacheJsonStr = if (idMapCacheFile.exists()) idMapCacheFile.readText() else ""
            val idCacheMap: Map<String, Int> = if (idMapCacheJsonStr.isEmpty()) hashMapOf() else gson.fromJson(idMapCacheJsonStr, object : TypeToken<HashMap<String, Int>>() {}.type)

            val result = LayoutClassGenerator(isAndroidx, transformInvocation.outputProvider
                , layoutFileInfoMap, attrInfoMap, genInfoHolder, packageName
                , waitableExecutor, genClassInfoCacheDir, qxmlConfig
                , genClassInfoModel.compatViewInfoModelMap, idMap, idCacheMap).generate()


            LogUtil.pl("code generate time cost:" + (System.currentTimeMillis() - time) + "ms")

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
    ): GenClassInfoModel {
        var result: GenClassInfoModel? = null
        for (input in inputs) {
            for (jarInput in input.jarInputs) {
                //LogUtil.pl("jarinput "+jarInput.file.absolutePath+" "+jarInput.contentTypes)
                classPathList.add(jarInput.file.absolutePath)
                if (jarInput.contentTypes.contains(QualifiedContent.DefaultContentType.RESOURCES)) {
                    val jarFile = JarFile(jarInput.file.absolutePath)
                    val entry = jarFile.getJarEntry(Constants.QXML_PARSE_FINAL_CONFIG_FILE_NAME)
                    //LogUtil.pl("entry  "+jarInput.file.absolutePath+" "+entry)
                    if (entry != null) {
                        val ins = jarFile.getInputStream(entry)
                        val content = ins.reader().readText()
                        //LogUtil.pl("transform qxml config content: "+content)
                        result = gson.fromJson(content, GenClassInfoModel::class.java)
                        val configFile = project.buildDir.resolve(Constants.QXML_CACHE_PATH).resolve(Constants.QXML_CONFIG_CACHE_FILE_NAME)
                        Files.createParentDirs(configFile)
                        if (!configFile.exists()) {
                            configFile.createNewFile()
                        }
                        configFile.writeText(content)
                        ins.close()
                    }
                    jarFile.close()
                }
            }
            for (directoryInput in input.directoryInputs) {
                classPathList.add(directoryInput.file.absolutePath)
            }
        }
        return result ?: error("请配置qxml依赖")
    }

    /**
     * layout文件变化时触发transform
     */
    override fun getSecondaryFiles(): MutableCollection<SecondaryFile> {
        //from tx shadow
        val transformJar = File(this::class.java.protectionDomain.codeSource.location.toURI())
        val transformKitJar = File(QXmlTransform::class.java.protectionDomain.codeSource.location.toURI())
        val incrementalDir = project.buildDir.resolve("intermediates").resolve("incremental")
        val mergerXmlFilePathList = mutableListOf<String>()
        if (incrementalDir.exists()) {
            incrementalDir.listFiles()?.forEach {
                if (it.name.startsWith("merge") && it.name.endsWith("Resources")) {
                    val mergerXmlFile = it.resolve("merger.xml")
                    if (mergerXmlFile.exists()) {
                        mergerXmlFilePathList.add(mergerXmlFile.absolutePath)
                    }
                }
            }
        }

        val layoutFilePath = mutableListOf<String>()
        if (layoutFile.exists()) {
            layoutPaths.forEach {
                if (it.isNotEmpty()) {
                    val split = it.split(" ")
                    if (split.size == 5) {
                        layoutFilePath.add(split[3])
                    }
                }
            }
        }

        return ImmutableList.of(
            SecondaryFile.nonIncremental(project.files(arrayOf(mergerXmlFilePathList))),
            SecondaryFile.nonIncremental(project.files(arrayOf(layoutFilePath))),
            SecondaryFile.nonIncremental(project.files(transformJar)),
            SecondaryFile.nonIncremental(project.files(transformKitJar)))
    }
}