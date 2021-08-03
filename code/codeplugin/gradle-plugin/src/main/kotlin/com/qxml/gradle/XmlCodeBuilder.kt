package com.qxml.gradle

import com.qxml.constant.Constants
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

/**
 * 将所有layout文件信息保存给到 QxmlTransform，layout文件改动时触发 QxmlTransform
 */
@CacheableTask
open class XmlCodeBuilder: DefaultTask() {

    @get:OutputDirectory
    var outputDir: File? = null

    @get:Input
    var packageName: String? = null

    @get:Input
    var buildType: String? = null

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    var allRawAndroidResourcesFiles: Set<File>? = null

    //过滤一些Android lib
    private val disableLayoutDirList = mutableListOf<String>().apply {
        add("appcompat-v7.*")
        add("support-compat.*")
    }
    private val disableLayoutDirRegexList = mutableListOf<Regex>()

    init {
        disableLayoutDirRegexList.clear()
        disableLayoutDirList.forEach {
            disableLayoutDirRegexList.add(Regex(it))
        }
    }

    @TaskAction
    fun generateXmlCode() {
        val layoutFiles = mutableListOf<File>()
        val layoutParentDirs = mutableListOf<File>()
        allRawAndroidResourcesFiles?.forEach { resFile->
            //LogUtil.pl("res  "+resFile.absolutePath)
            var disableLayoutDir = false
            for (regex in disableLayoutDirRegexList) {
                if (regex.matches(resFile.parentFile.name)) {
                    disableLayoutDir = true
                    break
                }
            }
            if (!disableLayoutDir) {
                layoutParentDirs.add(resFile)
            }
        }
        layoutParentDirs.forEach { layoutDir ->
            //LogUtil.pl("layoutdir "+layoutDir+" "+layoutDir.exists())
            if (layoutDir.exists()) {
                layoutDir.listFiles { _, dirName ->
                    //LogUtil.pl("name "+dirName)
                    dirName.startsWith("layout", true)
                }?.forEach { file ->
                    file.listFiles { _, fileName ->
                        fileName.endsWith(".xml", true)
                    }?.apply {
                        layoutFiles.addAll(this)
                    }
                }

                /*layoutFiles.forEach { layoutFile ->
                    LogUtil.pl("layout "+layoutFile)
                }*/
            }
        }
        val layoutInfoFile = outputDir!!.resolve(Constants.LAYOUTS_FILE_NAME)
        layoutInfoFile.createNewFile()
        val bufferWriter = layoutInfoFile.bufferedWriter()
        layoutFiles.forEach {
            bufferWriter.write("${it.parentFile.name} ${it.name.substring(0, it.name.length-4)} ${it.name} ${it.absolutePath} ${it.lastModified()}")
            bufferWriter.write("\n")
        }
        bufferWriter.flush()
        bufferWriter.close()
    }

}