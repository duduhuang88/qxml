package com.qxml.gradle

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.*
import java.io.File

@CacheableTask
open class LayoutIdCollector : DefaultTask() {

    @get:OutputFile
    var outputFile: File? = null

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    var rFile: FileCollection? = null

    @Suppress("unused")
    @TaskAction
    fun collect() {
        val content = LayoutIdCollectSymbolListReader().readSymbolTable(rFile!!.singleFile)
        outputFile!!.writeText(content)
    }

}

internal const val LAYOUT = "layout"
internal class LayoutIdCollectSymbolListReader {

    private val layoutIdMap: HashMap<String, Int> = hashMapOf()

    fun readSymbolTable(symbolTable: File): String {
        symbolTable.forEachLine { processLine(it) }
        return GsonBuilder().disableHtmlEscaping().create().toJson(layoutIdMap)
    }

    private fun processLine(line: String) {
        val values = line.split(' ')
        if (values.size < 4) {
            return
        }
        val javaType = values[0]
        if (javaType != "int") {
            return
        }
        val symbolType = values[1]
        if (symbolType != LAYOUT) {
            return
        }
        val name = values[2]
        val id = values[3]
        layoutIdMap[name] = id.substring(2).toInt(16)
    }
}