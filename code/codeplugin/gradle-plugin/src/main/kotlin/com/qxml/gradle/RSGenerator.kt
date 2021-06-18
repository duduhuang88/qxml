package com.qxml.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.*
import java.io.File

@CacheableTask
open class RSGenerator : DefaultTask() {
    @get:OutputDirectory
    var outputDir: File? = null

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    var rFile: FileCollection? = null

    @get:Input
    var packageName: String? = null

    @get:Input
    var className: String? = null

    @get:Input
    var androidJarFile: File? = null

    @TaskAction
    fun generateRS() {
        try {
            FinalRClassBuilder(packageName!!, className!!)
                .also { ResourceSymbolListReader(it).readSymbolTable(rFile!!.singleFile) }
                .build()
                .writeTo(outputDir)

            FinalRClassBuilder(packageName!!, "Android"+className!!)
                .also { AndroidResourceSymbolListReader(it).readAndroidSymbol(androidJarFile!!) }
                .build()
                .writeTo(outputDir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}