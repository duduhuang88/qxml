package com.qxml.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

@CacheableTask
abstract class StyleWatchTask: DefaultTask() {

    @get:OutputDirectory
    lateinit var outputDir: File

    @get:Input
    lateinit var buildType: String

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    lateinit var mergeXmlFile: File

    @TaskAction
    fun watch() {
        StyleCollector(project, buildType, mergeXmlFile, outputDir).collect()
    }

}