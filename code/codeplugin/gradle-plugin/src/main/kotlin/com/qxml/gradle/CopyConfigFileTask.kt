package com.qxml.gradle

import com.qxml.constant.Constants
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * 将processor生成的配置文件挪到tempRes文件夹
 * @property outputDir File?
 * @property aptConfigFile File?
 * @property kaptConfigFile File?
 */
//@CacheableTask
open class CopyConfigFileTask : DefaultTask() {

    //@get:OutputDirectory
    var outputDir: File? = null

    //@get:Input
    var aptConfigFile: File? = null

    //@get:Input
    var kaptConfigFile: File? = null

    @TaskAction
    fun copy() {
        val configFile = outputDir!!.resolve(Constants.QXML_PARSE_CONFIG_FILE_NAME)
        if (!configFile.parentFile.exists()) {
            configFile.parentFile.mkdirs()
        }
        if (!configFile.exists()) {
            configFile.createNewFile()
        }
        aptConfigFile?.apply {
            if (exists()) {
                configFile.writeText(readText())
            }
        }
        kaptConfigFile?.apply {
            if (exists()) {
                configFile.writeText(readText())
            }
        }
    }

}