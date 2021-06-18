package com.qxml.gradle

import com.android.build.gradle.*
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.api.DefaultAndroidSourceSet
import com.android.build.gradle.internal.res.GenerateLibraryRFileTask
import com.android.build.gradle.internal.res.LinkApplicationAndroidResourcesTask
import com.android.ide.common.internal.WaitableExecutor
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
import java.io.File
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.jar.JarFile

private const val PUBLIC_KEY_STR = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAkWb8VzyhkCzkiznSRjAZE8ALwop17CN00nzBvGLbajpvtxMCeriMCWTeDpitD83C4wxxjRGlVBitj6fVVH8xIm1hOdQ5lrDxGQ9T6mvk7q/aYl4uEdFZlIrFxVlIwsjcdHWJ2xdI9RyoHakwGBMjUT8w1o6NB1fgTSHuY83DpwIDAQAB"

class CodePlugin: Plugin<Project> {

    private lateinit var qxmlValidCode: String
    private lateinit var qxmlLogEnable: String

    private val waitableExecutor = WaitableExecutor.useGlobalSharedThreadPool()
    private val gson by lazy { GsonBuilder().disableHtmlEscaping().create() }
    private lateinit var qxmlConfig: QxmlExtension

    override fun apply(project: Project) {
        qxmlValidCode = project.properties[Constants.QXML_VALID_CODE]?.toString() ?: ""
        qxmlLogEnable = project.properties[Constants.QXML_LOG_ENABLE]?.toString() ?: "false"
        qxmlConfig = project.extensions.create("qxml", QxmlExtension::class.java, project)

        try {
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
                                project.tasks.findByName("transformClassesAndResourcesWithQXmlTransformFor${v.name.capitalize()}")?.doFirst {
                                    transform.packageName = getPackageName(v)
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

            if (!System.getenv().containsKey("ANDROID_HOME")) {
                throw java.lang.RuntimeException("请先设置ANDROID_HOME环境变量")
            }

            configureSourceSet(project)

            project.afterEvaluate {

                qxmlConfig.fixBuildTypeUnSetParam()

                project.plugins.all {
                    when (it) {
                        is LibraryPlugin -> {
                            val library = project.extensions.findByType(LibraryExtension::class.java)
                            library?.packagingOptions?.pickFirst(Constants.QXML_PARSE_CONFIG_FILE_NAME)
                            library?.libraryVariants?.forEach { v ->
                                addProcessorOption(v, Constants.QXML_VALID_CODE, qxmlValidCode)
                                addProcessorOption(v, Constants.QXML_LOG_ENABLE, if (qxmlConfig.getConfigByBuildType(v.name.capitalize()).logEnable) "true" else "false")

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

                                val task = project.tasks.create("generate${type}XmlCode", XmlCodeBuilder::class.java) { t->
                                    t.packageName = getPackageName(v)
                                    t.outputDir = outputDir
                                    t.buildType = v.name
                                    t.allRawAndroidResourcesFiles = v.allRawAndroidResources.files
                                }

                                v.assembleProvider.get().dependsOn(task)
                                task.mustRunAfter(v.mergeResourcesProvider.get())
                                //v.registerJavaGeneratingTask(task, outputDir)

                                setPreBuildConfig(project, v)
                                mergeQxmlConfig(project, type, qxmlValidCode)
                                changeTaskOrder(project, v)
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {

        }
    }

    private fun setPreBuildConfig(project: Project, v: BaseVariant) {
        val type = v.name
        project.tasks.getByName("pre${type.capitalize()}Build").doFirst {
            val curQxmlConfig = qxmlConfig.getConfigByBuildType(type)
            LogUtil.enable = curQxmlConfig.logEnable
        }
    }

    //获取依赖的project
    /*private fun resolveDependencyProject(project: Project, dependencyProjectNameMap: MutableMap<String, String>
                                         , projectList: MutableList<String>, buildType: String) {
        project.configurations.all {
            it.dependencies.forEach { dependency ->
                if (dependency.group == project.group.toString()
                    && !it.name.endsWith("compileOnly")
                    && (it.name == "implementation"
                            || it.name == "api"
                            || it.name == "${buildType}Api"
                            || it.name == "${buildType}Implementation")
                ) {
                    //logUtil.pl("de $project "+dependency.name+" "+dependency.group)
                    val dependencyProjectName = dependency.name
                    if (dependencyProjectNameMap[dependencyProjectName] == null) {
                        dependencyProjectNameMap[dependencyProjectName] = dependencyProjectName
                        val dependencyProject = project.rootProject.findProject(":$dependencyProjectName")
                        if (dependencyProject != null) {
                            resolveDependencyProject(dependencyProject, dependencyProjectNameMap, projectList, buildType)
                        }
                        projectList.add(dependencyProjectName)
                    }
                }
            }
        }
    }*/

    /**
     * application时合并所有qxml_config资源，在transform中解析
     */
    private fun mergeQxmlConfig(project: Project, type: String, qxmlValidCode: String) {
        var configFile: File? = null
        var finalConfigFile: File? = null
        val mergerJavaResourceTask = project.tasks.getByName("merge${type}JavaResource")
        //总是运行
        mergerJavaResourceTask.outputs.upToDateWhen { false }
        mergerJavaResourceTask.doFirst { mergeJavaResourceTask ->
            val allViewParseClassInfoModels = mutableListOf<GenClassInfoModel>()
            mergeJavaResourceTask.inputs.files.forEach { f ->
                if (f.exists() && f.name.endsWith(".jar", true)) {
                    val jarFile = JarFile(f.absolutePath)
                    val entry = jarFile.getJarEntry(Constants.QXML_PARSE_CONFIG_FILE_NAME)
                    //LogUtil.pl("j entry "+f.absolutePath+" "+entry)
                    if (entry != null) {
                        val ins = jarFile.getInputStream(entry)
                        val content = ins.reader().readText()
                        val genClassInfoModel = gson.fromJson(content, GenClassInfoModel::class.java)

                        if (!genClassInfoModel.sign.isNullOrEmpty()) {
                            val sign = genClassInfoModel.sign
                            genClassInfoModel.sign = ""
                            genClassInfoModel.validCode = ""
                            val infoModelInfoWithoutSignJsonStr = gson.toJson(genClassInfoModel)
                            val decryptSign = RSAUtils.decryptByPublicKey(sign, PUBLIC_KEY_STR)
                            if (infoModelInfoWithoutSignJsonStr != decryptSign) {
                                throw IllegalStateException("远程依赖的qxml配置错误，请确定依赖来源是否正确")
                            }
                            LogUtil.pl("found qxml remote config file in "+f.absolutePath)
                        } else {
                            if (genClassInfoModel.validCode != qxmlValidCode) {
                                throw IllegalStateException("qxml临时验签错误，请确定该配置来源正确：${f.absolutePath}")
                            }
                            LogUtil.pl("found qxml local config file in "+f.absolutePath)
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
                    LogUtil.pl("current project config file is "+configFile!!.absolutePath)
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
    }

    private fun addProcessorOption(variant : BaseVariant, key: String, value: String) {
        variant.javaCompileOptions.annotationProcessorOptions.arguments.putIfAbsent(key, value)
    }

    //将processJavaResTask依赖apt
    private fun changeTaskOrder(project: Project, variant : BaseVariant) {
        val type = variant.name
        val typeCap = type.capitalize()

        /*val copyTask = project.tasks.create("copy${typeCap}ProcessQxmlConfigFile") {
            println("f start ssss")
        }.doLast {

            val aptConfigFile = project.buildDir.resolve("generated${File.separator}ap_generated_sources${File.separator}${type}${File.separator}out").resolve(Constants.QXML_CONIFG_PATH).resolve(Constants.QXML_PARSE_CONFIG_FILE_NAME)
            val kaptConfigFile = project.buildDir.resolve("generated${File.separator}source${File.separator}kapt${File.separator}${type}").resolve(Constants.QXML_CONIFG_PATH).resolve(Constants.QXML_PARSE_CONFIG_FILE_NAME)
            println("f xxxxxxx "+" "+kaptConfigFile+" "+kaptConfigFile.exists())
        }*/

        val outputDir = project.buildDir.resolve(Constants.QXML_DIR_NAME).resolve(Constants.QXML_PROJECT_BUILD_TEMP_RES_PATH)

        val copyTask = project.tasks.create("copy${typeCap}ProcessQxmlConfigFile", CopyConfigFileTask::class.java) {
            it.outputDir = outputDir
            it.aptConfigFile = project.buildDir.resolve("generated${File.separator}ap_generated_sources${File.separator}${type}${File.separator}out").resolve(Constants.QXML_CONIFG_PATH).resolve(Constants.QXML_PARSE_CONFIG_FILE_NAME)
            it.kaptConfigFile = project.buildDir.resolve("generated${File.separator}source${File.separator}kapt${File.separator}${type}").resolve(Constants.QXML_CONIFG_PATH).resolve(Constants.QXML_PARSE_CONFIG_FILE_NAME)
        }

        val processJavaResTask = project.tasks.getByName("process${typeCap}JavaRes")
        processJavaResTask.dependsOn(copyTask)

        val kaptTask = project.tasks.findByName("kapt${typeCap}Kotlin")
        kaptTask?.let {
            processJavaResTask.dependsOn(it)
            copyTask.dependsOn(kaptTask)
        }
        val compileJavacTask = project.tasks.findByName("compile${typeCap}JavaWithJavac")
        compileJavacTask?.let {
            processJavaResTask.dependsOn(it)
            copyTask.dependsOn(compileJavacTask)
        }
    }

    private fun deleteQxmlConfig(project: Project, variant : BaseVariant) {
        val type = variant.name
        project.buildDir.resolve("generated${File.separator}ap_generated_sources${File.separator}${type}${File.separator}out").resolve(Constants.QXML_CONIFG_PATH).delete()
        project.buildDir.resolve("generated${File.separator}source${File.separator}kapt${File.separator}${type}").resolve(Constants.QXML_CONIFG_PATH).delete()
    }

    // from butter knife
    // Parse the variant's main manifest file in order to get the package id which is used to create
    // R.java in the right place.
    private fun getPackageName(variant : BaseVariant) : String {
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
    private fun configureSourceSet(project: Project, variants: DomainObjectSet<out BaseVariant>, isApp: Boolean) {

        variants.all { variant ->
            variant.sourceSets.forEach { sourceProvider ->
                val type = variant.name
                val sourceSet = (sourceProvider as DefaultAndroidSourceSet).resources
                //sourceSet.srcDir(project.buildDir.resolve("generated${File.separator}ap_generated_sources${File.separator}${type}${File.separator}out").resolve(Constants.QXML_CONIFG_PATH))
                //sourceSet.srcDir(project.buildDir.resolve("generated${File.separator}source${File.separator}kapt${File.separator}${type}").resolve(Constants.QXML_CONIFG_PATH))
                if (isApp) {
                    val finalConfigFile = project.buildDir.resolve(Constants.QXML_DIR_NAME).resolve(Constants.QXML_PROJECT_BUILD_TEMP_RES_PATH).resolve(Constants.QXML_PARSE_FINAL_CONFIG_FILE_NAME)
                    Files.createParentDirs(finalConfigFile)
                    if (!finalConfigFile.exists()) {
                        finalConfigFile.createNewFile()
                    }
                    finalConfigFile.writeText("{\"versionCode\":${Constants.QXML_VERSION_CODE},\"viewParseList\":[],\"viewReplaceList\":[],\"viewGenClassModelMap\":{},\"interfaceModelMap\":{},\"genClassNameMap\":{},\"parentClassMap\":{},\"localVarMap\":{},\"compatViewInfoModelMap\":{},\"layoutParamInitMap\":{},\"validCode\":\"${qxmlValidCode}\"}")
                    //sourceSet.srcDir(finalConfigFile.parentFile)
                }
                sourceSet.srcDir(project.buildDir.resolve(Constants.QXML_DIR_NAME).resolve(Constants.QXML_PROJECT_BUILD_TEMP_RES_PATH).absolutePath)
            }
        }
    }

    //from butter knife
    //生成RS, application收集layout id值
    private fun generateRsAndLayoutId(project: Project, variants: DomainObjectSet<out BaseVariant>, isApplication: Boolean) {
        variants.all { variant ->
            val outputDir = project.buildDir.resolve("${Constants.GENERATE_RS_PATH}${variant.dirName}")

            val rPackage = getPackageName(variant)
            val once = AtomicBoolean()
            variant.outputs.all { output ->
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

                    val extension by lazy { project.extensions.getByName("android") as BaseExtension }

                    val androidJar by lazy {
                        File(extension.sdkDirectory, "platforms${File.separator}${extension.compileSdkVersion}${File.separator}android.jar")
                    }

                    val generate = project.tasks.create("generate${variant.name.capitalize()}RS", RSGenerator::class.java) {
                        it.outputDir = outputDir
                        it.rFile = rFile
                        it.packageName = rPackage
                        it.className = "RS"
                        it.androidJarFile = androidJar
                    }

                    variant.registerJavaGeneratingTask(generate, outputDir)

                    if (isApplication) {
                        //application时收集layout对应的id值
                        val layoutIdOutputFile = project.buildDir.resolve("${Constants.LAYOUT_ID_COLLECT_PATH}${variant.dirName}").resolve(Constants.LAYOUT_ID_COLLECT_FILE_NAME)
                        Files.createParentDirs(layoutIdOutputFile)
                        if (!layoutIdOutputFile.exists()) {
                            layoutIdOutputFile.createNewFile()
                        }
                        val layoutIdCollectorTask = project.tasks.create("layoutId${variant.name.capitalize()}Collect", LayoutIdCollector::class.java) { t->
                            t.outputFile = layoutIdOutputFile
                            t.rFile = rFile
                        }
                        variant.assembleProvider.get().dependsOn(layoutIdCollectorTask)
                        layoutIdCollectorTask.mustRunAfter(variant.mergeResourcesProvider.get())

                        val idOutputFile = project.buildDir.resolve("${Constants.LAYOUT_ID_COLLECT_PATH}${variant.dirName}").resolve(Constants.ID_COLLECT_FILE_NAME)
                        Files.createParentDirs(idOutputFile)
                        if (!idOutputFile.exists()) {
                            idOutputFile.createNewFile()
                        }
                        val idCollectorTask = project.tasks.create("id${variant.name.capitalize()}Collect", IdCollector::class.java) { t->
                            t.outputFile = idOutputFile
                            t.rFile = rFile
                        }
                        variant.assembleProvider.get().dependsOn(idCollectorTask)
                        idCollectorTask.mustRunAfter(variant.mergeResourcesProvider.get())
                    }
                }
            }
        }
    }
}
