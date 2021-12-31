package com.qxml.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

@CacheableTask
open class StyleWatchTask: DefaultTask() {

    @get:OutputDirectory
    var outputDir: File? = null

    @get:Input
    var buildType: String? = null

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    var mergeXmlFile: File? = null

    @TaskAction
    fun watch() {
        StyleCollector(project, buildType!!, mergeXmlFile!!, outputDir!!).collect()
    }

}