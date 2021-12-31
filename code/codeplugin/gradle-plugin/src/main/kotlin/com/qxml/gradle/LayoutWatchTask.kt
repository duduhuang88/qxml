package com.qxml.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

@CacheableTask
open class LayoutWatchTask: DefaultTask() {

    @get:OutputDirectory
    var outputDir: File? = null

    @get:Input
    var buildType: String? = null

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    var mergeXmlFiles: Set<File>? = null

    @TaskAction
    fun watch() {
        LayoutFileCollector(project, buildType!!, mergeXmlFiles!!, outputDir!!).collect()
    }

}