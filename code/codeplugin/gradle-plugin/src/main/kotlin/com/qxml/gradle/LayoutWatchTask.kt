package com.qxml.gradle

import com.qxml.tools.log.LogUtil
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

@CacheableTask
open class LayoutWatchTask: DefaultTask() {

    @get:OutputDirectory
    lateinit var outputDir: File

    @get:Input
    lateinit var buildType: String

    @get:Input
    var viewDebug: Boolean = false

    @get:Input
    var useFactory: Boolean = false

    @get:Input
    var compatMode: Int = 1

    @get:Input
    var ignoreUnImplementAttr: Boolean = true

    @get:Input
    lateinit var libProjectVariantInfoMap: Map<String, String>

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    lateinit var mergeXmlFiles: Set<File>

    @TaskAction
    fun watch() {
        LayoutFileCollector(project, buildType, mergeXmlFiles, outputDir, libProjectVariantInfoMap, viewDebug, useFactory, compatMode, ignoreUnImplementAttr).collect()
    }

}