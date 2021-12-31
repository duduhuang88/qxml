package com.qxml.gradle

import com.android.build.gradle.*
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.api.DefaultAndroidSourceSet
import com.android.build.gradle.internal.res.GenerateLibraryRFileTask
import com.android.build.gradle.internal.res.LinkApplicationAndroidResourcesTask
import com.google.common.io.Files
import com.google.gson.GsonBuilder
import com.qxml.QxmlExtension
import com.qxml.constant.Constants
import com.qxml.tools.encrypt.RSAUtils
import com.qxml.tools.log.LogUtil
import com.qxml.tools.model.GenClassInfoModel
import com.qxml.transform.QXmlTransform
import groovy.util.XmlSlurper
import org.gradle.api.DomainObjectSet
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import java.io.File
import java.security.MessageDigest
import java.util.concurrent.atomic.AtomicBoolean
import java.util.jar.JarFile
import java.util.zip.CRC32

private const val PUBLIC_KEY_STR = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAkWb8VzyhkCzkiznSRjAZE8ALwop17CN00nzBvGLbajpvtxMCeriMCWTeDpitD83C4wxxjRGlVBitj6fVVH8xIm1hOdQ5lrDxGQ9T6mvk7q/aYl4uEdFZlIrFxVlIwsjcdHWJ2xdI9RyoHakwGBMjUT8w1o6NB1fgTSHuY83DpwIDAQAB"

class CodePlugin: Plugin<Project> {

    private lateinit var qxmlValidCode: String
    private lateinit var qxmlLogEnable: String
    private lateinit var qxmlUsingStableId: String

    private val gson = GsonBuilder().disableHtmlEscaping().create()
    private lateinit var qxmlConfig: QxmlExtension
    private lateinit var publicROutputFile: File

    override fun apply(project: Project) {
        qxmlValidCode = project.properties[Constants.QXML_VALID_CODE]?.toString() ?: ""
        qxmlLogEnable = project.properties[Constants.QXML_LOG_ENABLE]?.toString() ?: "false"
        qxmlUsingStableId = project.properties[Constants.QXML_USING_STABLE_ID]?.toString() ?: "true"
        qxmlConfig = project.extensions.create("qxml", QxmlExtension::class.java, project)
        publicROutputFile = project.buildDir.resolve(Constants.QXML_CACHE_DIR).parentFile.resolve(
            Constants.PUBLIC_FILE_NAME
        )

        project.plugins.all {
            when (it) {
                is AppPlugin -> {
                    val extension = project.extensions.getByName("android") as BaseExtension
                    extension.packagingOptions.pickFirst(Constants.QXML_PARSE_CONFIG_FILE_NAME)
                    val transform = QXmlTransform(project)
                    extension.registerTransform(transform)
                    val app = project.extensions.findByType(AppExtension::class.java)!!
                    app.run {
                        generateRsAndLayoutId(project, applicationVariants, true)
                    }

                    app.defaultConfig.javaCompileOptions.annotationProcessorOptions.arguments.apply {
                        putIfAbsent(Constants.QXML_VALID_CODE, qxmlValidCode)
                        putIfAbsent(Constants.QXML_LOG_ENABLE, qxmlLogEnable)
                    }

                    project.afterEvaluate {
                        app.applicationVariants.forEach { v ->
                            project.tasks.findByName("transformClassesAndResourcesWithQXmlTransformFor${v.name.capitalize()}")
                                ?.doFirst {
                                    transform.packageName = getPackageName(v)
                                    transform.curBuildType = v.name
                                }
                        }
                    }
                }
                is LibraryPlugin -> {
                    project.extensions.findByType(LibraryExtension::class.java)?.run {
                        defaultConfig.javaCompileOptions.annotationProcessorOptions.arguments.apply {
                            putIfAbsent(Constants.QXML_VALID_CODE, qxmlValidCode)
                            putIfAbsent(Constants.QXML_LOG_ENABLE, qxmlLogEnable)
                        }
                        packagingOptions.pickFirst(Constants.QXML_PARSE_CONFIG_FILE_NAME)
                        generateRsAndLayoutId(project, libraryVariants, false)
                    }
                }
            }
        }

        configureSourceSet(project)

        if (qxmlUsingStableId.equals("true", true)) {
            val androidApp = project.extensions.findByType(AppExtension::class.java)
            androidApp?.apply {
                val aaptParams = androidApp.aaptOptions.additionalParameters ?: mutableListOf()
                val publicFilePath = project.buildDir.resolve("qxml${File.separator}public.txt").absolutePath
                if (!aaptParams.contains(publicFilePath)) {
                    aaptParams.add("--stable-ids")
                    aaptParams.add(publicFilePath)
                    androidApp.aaptOptions.additionalParameters = aaptParams
                }
            }
        }

        project.afterEvaluate {

            qxmlConfig.fixBuildTypeUnSetParam()

            project.plugins.all {
                when (it) {
                    is LibraryPlugin -> {
                        val library = project.extensions.findByType(LibraryExtension::class.java)
                        library?.packagingOptions?.pickFirst(Constants.QXML_PARSE_CONFIG_FILE_NAME)
                        library?.libraryVariants?.forEach { v ->
                            addProcessorOption(v, Constants.QXML_VALID_CODE, qxmlValidCode)
                            addProcessorOption(
                                v, Constants.QXML_LOG_ENABLE, if (qxmlConfig.getConfigByBuildType(
                                        v.name.capitalize()
                                    ).logEnable
                                ) "true" else "false"
                            )

                            setPreBuildConfig(project, v)
                            deleteQxmlConfig(project, v)
                            changeTaskOrder(project, v)
                        }
                    }
                    is AppPlugin -> {
                        val androidApp = project.extensions.findByType(AppExtension::class.java)!!

                        androidApp.packagingOptions.pickFirst(Constants.QXML_PARSE_CONFIG_FILE_NAME)
                        androidApp.applicationVariants.all { v ->

                            deleteQxmlConfig(project, v)
                            val type = v.name.capitalize()
                            val outputDir = project.buildDir.resolve(Constants.QXML_CACHE_PATH)
                            if (!outputDir.exists()) {
                                outputDir.mkdirs()
                            }

                            val curProjectMergerXmlFile =
                                project.buildDir.resolve(Constants.INTERMEDIATES)
                                    .resolve(Constants.INCREMENTAL).resolve("merge${type}Resources")
                                    .resolve(Constants.MERGER_XML)

                            val styleWatchTask = project.tasks.create(
                                "qxmlWatch${type}Style",
                                StyleWatchTask::class.java
                            ) { t ->
                                t.outputDir = outputDir.resolve(Constants.QXML_STYLE_CACHE_DIR_NAME)
                                t.buildType = v.name
                                t.mergeXmlFile = curProjectMergerXmlFile
                            }

                            val mergeXmlFiles = getMergerXmlFiles(
                                project,
                                v.allRawAndroidResources.files,
                                curProjectMergerXmlFile,
                                type
                            )

                            val layoutWatchTask = project.tasks.create(
                                "qxmlWatch${type}Layout",
                                LayoutWatchTask::class.java
                            ) { t ->
                                t.outputDir =
                                    outputDir.resolve(Constants.QXML_LAYOUT_CACHE_DIR_NAME)
                                t.buildType = v.name
                                t.mergeXmlFiles = mergeXmlFiles
                            }

                            v.mergeAssetsProvider.get().dependsOn(styleWatchTask)
                            styleWatchTask.mustRunAfter(v.mergeResourcesProvider.get())

                            v.mergeAssetsProvider.get().dependsOn(layoutWatchTask)
                            layoutWatchTask.mustRunAfter(v.mergeResourcesProvider.get())

                            //v.registerJavaGeneratingTask(task, outputDir)

                            setPreBuildConfig(project, v)
                            mergeQxmlConfig(project, type)
                            changeTaskOrder(project, v)
                        }
                    }
                }
            }
        }
    }

    private fun getMergerXmlFiles(project: Project, files: Set<File>,
                                  curProjectMergerXmlFile: File, type: String): Set<File> {
        val mergeXmlFiles = mutableSetOf<File>()
        files.forEach { dir ->
            val path = dir.absolutePath
            if (path.contains(Constants.INTERMEDIATES) && path.contains(Constants.BUILD)) {
                var curDir: File? = dir
                while (curDir != null && curDir.name != Constants.INTERMEDIATES) {
                    curDir = curDir.parentFile
                }
                if (curDir != null) {
                    val parentDir = curDir.parentFile
                    if (parentDir != null && parentDir.name == Constants.BUILD && parentDir.parentFile != null) {
                        val projectName = parentDir.parentFile.name
                        if (projectName != project.name) {
                            mergeXmlFiles.add(
                                curDir.resolve(Constants.INCREMENTAL)
                                    .resolve("package${type}Resources")
                                    .resolve(Constants.MERGER_XML)
                            )
                        }
                    }
                }
            }
        }
        mergeXmlFiles.add(curProjectMergerXmlFile)
        return mergeXmlFiles
    }

    private fun setPreBuildConfig(project: Project, v: BaseVariant) {
        val type = v.name
        project.tasks.getByName("pre${type.capitalize()}Build").doFirst {
            val curQxmlConfig = qxmlConfig.getConfigByBuildType(type)
            LogUtil.enable = curQxmlConfig.logEnable
            LogUtil.debug = curQxmlConfig.debugEnable
        }
    }

    /**
     * application时合并所有qxml_config资源，在transform中解析
     */
    private fun mergeQxmlConfig(project: Project, type: String) {
        val mergerJavaResourceTask = project.tasks.getByName("merge${type}JavaResource")

        mergerJavaResourceTask.outputs.upToDateWhen {
            processConfigFile(it)
            true
        }
    }

    private fun processConfigFile(mergeJavaResourceTask: Task) {
        var configFile: File? = null
        var finalConfigFile: File? = null
        val allViewParseClassInfoModels = mutableListOf<GenClassInfoModel>()
        mergeJavaResourceTask.inputs.files.forEach { f ->
            if (f.exists() && f.name.endsWith(".jar", true)) {
                val jarFile = JarFile(f.absolutePath)
                val entry = jarFile.getJarEntry(Constants.QXML_PARSE_CONFIG_FILE_NAME)
                if (entry != null) {
                    val ins = jarFile.getInputStream(entry)
                    val content = ins.reader().readText()
                    val genClassInfoModel = gson.fromJson(content, GenClassInfoModel::class.java)

                    if (!genClassInfoModel.sign.isNullOrEmpty()) {
                        val sign = genClassInfoModel.sign
                        genClassInfoModel.sign = ""
                        genClassInfoModel.validCode = ""
                        val infoModelInfoWithoutSignJsonStr = gson.toJson(genClassInfoModel)
                        val crc32 = CRC32()
                        val infoBytes: ByteArray = infoModelInfoWithoutSignJsonStr.toByteArray()
                        crc32.update(infoBytes)
                        val crc32Str = java.lang.Long.toHexString(crc32.value)
                        val md5Str = String(MessageDigest.getInstance("md5").digest(infoBytes))
                        val signStr = crc32Str + "_" + md5Str + "_" + infoModelInfoWithoutSignJsonStr.length
                        val decryptSign = RSAUtils.decryptByPublicKey(sign, PUBLIC_KEY_STR)
                        if (signStr != decryptSign) {
                            throw IllegalStateException("远程依赖的qxml配置错误，请确定依赖来源是否正确:${f.absolutePath}")
                        }
                        LogUtil.pl("found qxml remote config file in " + f.absolutePath)
                    } else {
                        if (genClassInfoModel.validCode != qxmlValidCode) {
                            throw IllegalStateException("qxml临时验签错误，请确定该配置来源正确：${f.absolutePath}")
                        }
                        LogUtil.pl("found qxml local config file in " + f.absolutePath)
                    }

                    allViewParseClassInfoModels.add(genClassInfoModel)

                    ins.close()
                }
                jarFile.close()
            }

            if (f.absolutePath.endsWith(Constants.QXML_PARSE_FINAL_CONFIG_FILE_NAME, true)) {
                finalConfigFile = f
            }

            if (f.absolutePath.endsWith(Constants.QXML_PARSE_CONFIG_FILE_NAME, true)) {
                configFile = f
            }
        }
        finalConfigFile?.apply {
            if (configFile != null) {
                LogUtil.pl("current project config file is " + configFile!!.absolutePath)
                var content = configFile!!.readText()
                if (content.isEmpty()) {
                    content = "{\"versionCode\":${Constants.QXML_VERSION_CODE},\"viewParseList\":[],\"viewReplaceList\":[],\"viewGenClassModelMap\":{},\"interfaceModelMap\":{},\"genClassNameMap\":{},\"parentClassMap\":{},\"localVarMap\":{},\"compatViewInfoModelMap\":{},\"layoutParamInitMap\":{},\"validCode\":\"${qxmlValidCode}\"}"
                }
                val thisGenClassInfoModel = gson.fromJson(content, GenClassInfoModel::class.java)
                if (thisGenClassInfoModel.validCode != qxmlValidCode) {
                    throw IllegalStateException("qxml临时验签错误，请确定该配置来源正确：${configFile!!.absolutePath}")
                }
                allViewParseClassInfoModels.add(thisGenClassInfoModel)
            }
            val finalGenClassInfoModel = GenClassInfoModel()
            finalGenClassInfoModel.versionCode = Constants.QXML_VERSION_CODE
            finalGenClassInfoModel.viewParseList = mutableListOf()
            finalGenClassInfoModel.viewReplaceList = mutableListOf()
            finalGenClassInfoModel.genClassNameMap = hashMapOf()
            finalGenClassInfoModel.interfaceModelMap = hashMapOf()
            finalGenClassInfoModel.viewGenClassModelMap = hashMapOf()
            finalGenClassInfoModel.parentClassMap = hashMapOf()
            finalGenClassInfoModel.localVarMap = hashMapOf()
            finalGenClassInfoModel.compatViewInfoModelMap = hashMapOf()
            finalGenClassInfoModel.layoutParamInitMap = hashMapOf()
            finalGenClassInfoModel.callOnFinishInflateMap = hashMapOf()
            finalGenClassInfoModel.sign = ""
            finalGenClassInfoModel.validCode = ""

            allViewParseClassInfoModels.sort()
            finalGenClassInfoModel.versionCode = allViewParseClassInfoModels.lastOrNull()?.versionCode ?: 1

            val viewParseMap = hashMapOf<String, String>()
            val viewReplaceMap = hashMapOf<String, String>()

            allViewParseClassInfoModels.forEach { infoModel ->
                infoModel.viewParseList.forEach { viewParse ->
                    if (!viewParseMap.containsKey(viewParse)) {
                        viewParseMap[viewParse] = ""
                        finalGenClassInfoModel.viewParseList.add(viewParse)
                    }
                }
                infoModel.viewReplaceList.forEach { viewReplace ->
                    if (!viewReplaceMap.containsKey(viewReplace)) {
                        viewReplaceMap[viewReplace] = ""
                        finalGenClassInfoModel.viewReplaceList.add(viewReplace)
                    }
                }
                finalGenClassInfoModel.genClassNameMap.putAll(infoModel.genClassNameMap)
                finalGenClassInfoModel.interfaceModelMap.putAll(infoModel.interfaceModelMap)
                finalGenClassInfoModel.viewGenClassModelMap.putAll(infoModel.viewGenClassModelMap)
                finalGenClassInfoModel.parentClassMap.putAll(infoModel.parentClassMap)
                finalGenClassInfoModel.compatViewInfoModelMap.putAll(infoModel.compatViewInfoModelMap)
                finalGenClassInfoModel.layoutParamInitMap.putAll(infoModel.layoutParamInitMap)
                if (infoModel.callOnFinishInflateMap != null) {
                    finalGenClassInfoModel.callOnFinishInflateMap.putAll(infoModel.callOnFinishInflateMap)
                }
                infoModel.localVarMap.forEach { (varName, localVarInfoModel) ->
                    if (finalGenClassInfoModel.localVarMap[varName] != null) {
                        error("有相同的变量名${varName}")
                    }
                    finalGenClassInfoModel.localVarMap[varName] = localVarInfoModel
                }
            }
            val finalGenInfoJsonStr = gson.toJson(finalGenClassInfoModel)
            //LogUtil.pl("final viewParse class $finalGenInfoJsonStr")
            writeText(finalGenInfoJsonStr)
        }
    }

    private fun addProcessorOption(variant: BaseVariant, key: String, value: String) {
        variant.javaCompileOptions.annotationProcessorOptions.arguments.putIfAbsent(key, value)
    }

    //将processJavaResTask依赖apt
    private fun changeTaskOrder(project: Project, variant: BaseVariant) {
        val type = variant.name
        val typeCap = type.capitalize()

        val outputDir = project.buildDir.resolve(Constants.QXML_DIR_NAME).resolve(Constants.QXML_PROJECT_BUILD_TEMP_RES_PATH)

        val aptConfigFile = project.buildDir.resolve("generated${File.separator}ap_generated_sources${File.separator}${type}${File.separator}out").resolve(
            Constants.QXML_CONIFG_PATH
        ).resolve(Constants.QXML_PARSE_CONFIG_FILE_NAME)
        val kaptConfigFile = project.buildDir.resolve("generated${File.separator}source${File.separator}kapt${File.separator}${type}").resolve(
            Constants.QXML_CONIFG_PATH
        ).resolve(Constants.QXML_PARSE_CONFIG_FILE_NAME)

        val copyKaptTask = project.tasks.create(
            "copy${typeCap}KaptProcessQxmlConfigFile",
            CopyConfigFileTask::class.java
        ) {
            it.outputDir = outputDir
            it.configFile = kaptConfigFile
        }
        copyKaptTask.onlyIf { kaptConfigFile.exists() }

        val copyAptTask = project.tasks.create(
            "copy${typeCap}AptProcessQxmlConfigFile",
            CopyConfigFileTask::class.java
        ) {
            it.outputDir = outputDir
            it.configFile = aptConfigFile
        }
        copyAptTask.onlyIf { aptConfigFile.exists() }

        val processJavaResTask = project.tasks.getByName("process${typeCap}JavaRes")
        processJavaResTask.dependsOn(copyKaptTask)
        processJavaResTask.dependsOn(copyAptTask)

        val kaptTask = project.tasks.findByName("kapt${typeCap}Kotlin")
        kaptTask?.let {
            processJavaResTask.dependsOn(it)
            copyKaptTask.dependsOn(kaptTask)
            copyKaptTask.mustRunAfter(kaptTask)
        }
        val compileJavacTask = project.tasks.findByName("compile${typeCap}JavaWithJavac")
        compileJavacTask?.let {
            processJavaResTask.dependsOn(it)
            copyAptTask.dependsOn(compileJavacTask)
            copyAptTask.mustRunAfter(compileJavacTask)
        }
    }

    private fun deleteQxmlConfig(project: Project, variant: BaseVariant) {
        val type = variant.name
        project.buildDir.resolve("generated${File.separator}ap_generated_sources${File.separator}${type}${File.separator}out").resolve(
            Constants.QXML_CONIFG_PATH
        ).delete()
        project.buildDir.resolve("generated${File.separator}source${File.separator}kapt${File.separator}${type}").resolve(
            Constants.QXML_CONIFG_PATH
        ).delete()
    }

    // from butter knife
    // Parse the variant's main manifest file in order to get the package id which is used to create
    // R.java in the right place.
    private fun getPackageName(variant: BaseVariant) : String {
        val slurper = XmlSlurper(false, false)
        val list = variant.sourceSets.map { it.manifestFile }

        // According to the documentation, the earlier files in the list are meant to be overridden by the later ones.
        // So the first file in the sourceSets list should be main.
        val result = slurper.parse(list[0])
        return result.getProperty("@package").toString()
    }

    private fun configureSourceSet(project: Project) {
        project.plugins.all {
            when (it) {
                is LibraryPlugin -> {
                    project.extensions.findByType(LibraryExtension::class.java)?.run {
                        configureSourceSet(project, libraryVariants, false)
                    }
                }
                is AppPlugin -> {
                    project.extensions.findByType(AppExtension::class.java)?.run {
                        configureSourceSet(project, applicationVariants, true)
                    }
                }
            }
        }
    }

    /**
     * 依赖apt生成的qxml_config文件作为res资源
     */
    private fun configureSourceSet(
        project: Project,
        variants: DomainObjectSet<out BaseVariant>,
        isApp: Boolean
    ) {
        val once = AtomicBoolean()
        variants.all { variant ->
            variant.sourceSets.forEach { sourceProvider ->
                val type = variant.name
                val sourceSet = (sourceProvider as DefaultAndroidSourceSet).resources
                //sourceSet.srcDir(project.buildDir.resolve("generated${File.separator}ap_generated_sources${File.separator}${type}${File.separator}out").resolve(Constants.QXML_CONIFG_PATH))
                //sourceSet.srcDir(project.buildDir.resolve("generated${File.separator}source${File.separator}kapt${File.separator}${type}").resolve(Constants.QXML_CONIFG_PATH))
                if (isApp) {
                    createFileWhenBuildStart(once, project, type, variant)
                    //sourceSet.srcDir(finalConfigFile.parentFile)
                }
                sourceSet.srcDir(
                    project.buildDir.resolve(Constants.QXML_DIR_NAME)
                        .resolve(Constants.QXML_PROJECT_BUILD_TEMP_RES_PATH).absolutePath
                )
            }
        }
    }

    /**
     * 构建开始时再创建需要的文件， 避免运行 gradlew clean assembleDebug 这种命令时文件被删除
     * @param once AtomicBoolean
     * @param project Project
     * @param type String
     * @param variant BaseVariant
     */
    private fun createFileWhenBuildStart(
        once: AtomicBoolean,
        project: Project,
        type: String,
        variant: BaseVariant
    ) {
        project.tasks.findByName("pre${type.capitalize()}Build")?.doFirst {
            if (once.compareAndSet(false, true)) {
                val finalConfigFile = project.buildDir.resolve(Constants.QXML_DIR_NAME).resolve(
                    Constants.QXML_PROJECT_BUILD_TEMP_RES_PATH
                ).resolve(Constants.QXML_PARSE_FINAL_CONFIG_FILE_NAME)
                Files.createParentDirs(finalConfigFile)
                if (!finalConfigFile.exists()) {
                    finalConfigFile.createNewFile()
                }
                finalConfigFile.writeText("{\"versionCode\":${Constants.QXML_VERSION_CODE},\"viewParseList\":[],\"viewReplaceList\":[],\"viewGenClassModelMap\":{},\"interfaceModelMap\":{},\"genClassNameMap\":{},\"parentClassMap\":{},\"localVarMap\":{},\"compatViewInfoModelMap\":{},\"layoutParamInitMap\":{},\"validCode\":\"${qxmlValidCode}\"}")
                val idOutputFile = project.buildDir.resolve("${Constants.QXML_CACHE_DIR}${variant.dirName}").resolve(
                    Constants.ID_COLLECT_FILE_NAME
                )
                publicROutputFile
                Files.createParentDirs(idOutputFile)
                if (!idOutputFile.exists()) {
                    idOutputFile.createNewFile()
                }
                if (!publicROutputFile.exists()) {
                    publicROutputFile.createNewFile()
                }
                val layoutIdOutputFile = project.buildDir.resolve("${Constants.QXML_CACHE_DIR}${variant.dirName}").resolve(
                    Constants.LAYOUT_ID_COLLECT_FILE_NAME
                )
                Files.createParentDirs(layoutIdOutputFile)
                if (!layoutIdOutputFile.exists()) {
                    layoutIdOutputFile.createNewFile()
                }
            }
        }
    }

    //from butter knife
    //生成RS, application收集layout id值
    private fun generateRsAndLayoutId(
        project: Project,
        variants: DomainObjectSet<out BaseVariant>,
        isApplication: Boolean
    ) {
        variants.all { variant ->
            val outputDir = project.buildDir.resolve("${Constants.GENERATE_RS_PATH}${variant.dirName}")

            val rPackage = getPackageName(variant)
            val once = AtomicBoolean()
            variant.outputs.all { output ->

                val curBuildType = variant.name.capitalize()

                // Though there might be multiple outputs, their R files are all the same. Thus, we only
                // need to configure the task once with the R.java input and action.
                if (once.compareAndSet(false, true)) {
                    val processResources = output.processResourcesProvider.get() // TODO lazy

                    // TODO: switch to better API once exists in AGP (https://issuetracker.google.com/118668005)
                    val rFile =
                        project.files(
                            when (processResources) {
                                is GenerateLibraryRFileTask -> processResources.getTextSymbolOutputFile()
                                is LinkApplicationAndroidResourcesTask -> processResources.getTextSymbolOutputFile()
                                else -> throw RuntimeException(
                                    "Minimum supported Android Gradle Plugin is 3.3.0"
                                )
                            }
                        )
                            .builtBy(processResources)

                    val extension = project.extensions.getByName("android") as BaseExtension

                    val androidJar = File(
                        extension.sdkDirectory,
                        "platforms${File.separator}${extension.compileSdkVersion}${File.separator}android.jar"
                    )

                    val generate = project.tasks.create(
                        "generate${curBuildType}RS",
                        RSGenerator::class.java
                    ) {
                        it.outputDir = outputDir
                        it.rFile = rFile
                        it.packageName = rPackage
                        it.className = "RS"
                        it.androidJarFile = androidJar
                    }

                    variant.registerJavaGeneratingTask(generate, outputDir)

                    if (isApplication) {
                        //application时收集layout对应的id值
                        val layoutIdOutputFile = project.buildDir.resolve("${Constants.QXML_CACHE_DIR}${variant.dirName}").resolve(
                            Constants.LAYOUT_ID_COLLECT_FILE_NAME
                        )
                        val layoutIdCollectorTask = project.tasks.create(
                            "layoutId${curBuildType}Collect",
                            LayoutIdCollector::class.java
                        ) { t->
                            t.outputFile = layoutIdOutputFile
                            t.rFile = rFile
                        }
                        variant.assembleProvider.get().dependsOn(layoutIdCollectorTask)
                        layoutIdCollectorTask.mustRunAfter(variant.mergeResourcesProvider.get())

                        val idOutputFile = project.buildDir.resolve("${Constants.QXML_CACHE_DIR}${variant.dirName}").resolve(
                            Constants.ID_COLLECT_FILE_NAME
                        )
                        val publicROutputFile = project.buildDir.resolve(Constants.QXML_CACHE_DIR).parentFile.resolve(
                            Constants.PUBLIC_FILE_NAME
                        )
                        val idCollectorTask = project.tasks.create(
                            "id${curBuildType}Collect",
                            IdCollector::class.java
                        ) { t->
                            t.publicROutputFile = publicROutputFile
                            t.outputFile = idOutputFile
                            t.rFile = rFile
                            t.packageName = rPackage
                            t.curBuildType = curBuildType
                        }
                        variant.assembleProvider.get().dependsOn(idCollectorTask)
                        idCollectorTask.mustRunAfter(variant.mergeResourcesProvider.get())
                    }
                }
            }
        }
    }
}