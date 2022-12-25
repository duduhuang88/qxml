package com.qxml.gradle

import com.qxml.constant.Constants
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

/**
 * 将processor生成的配置文件挪到tempRes文件夹
 * @property outputDir File?
 * @property configFile File?
 * see https://github.com/gradle/gradle/issues/2919
 */
@CacheableTask
abstract class CopyConfigFileTask : DefaultTask() {

    @get:OutputDirectory
    lateinit var outputDir: File

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    lateinit var configFile: File

    @TaskAction
    fun copy() {
        val configOutputFile = outputDir.resolve(Constants.QXML_PARSE_CONFIG_FILE_NAME)
        if (!configOutputFile.parentFile.exists()) {
            configOutputFile.parentFile.mkdirs()
        }
        if (!configOutputFile.exists()) {
            configOutputFile.createNewFile()
        }
        configFile.apply {
            if (exists()) {
                configOutputFile.writeText(readText())
            }
        }
    }

}