package com.qxml.gradle

import com.google.gson.GsonBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.*
import java.io.File
import java.lang.StringBuilder

@CacheableTask
open class IdCollector: DefaultTask() {

    @get:OutputFile
    var publicROutputFile: File? = null

    @get:OutputFile
    var outputFile: File? = null

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    var rFile: FileCollection? = null

    @get:Input
    var packageName: String? = null

    @TaskAction
    fun collect() {
        val reader = IdCollectSymbolListReader(packageName!!)
        val content = reader.readSymbolTable(rFile!!.singleFile)
        outputFile!!.writeText(content)
        val stringBuilder = StringBuilder()
        reader.publicIdList.forEach {
            stringBuilder.append(it).append("\n")
        }
        publicROutputFile!!.writeText(stringBuilder.toString())
    }
}

internal class IdCollectSymbolListReader(private val packageName: String) {

    private val layoutIdMap: HashMap<String, Int> = hashMapOf()
    val publicIdList: MutableList<String> = mutableListOf()

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

        val name = values[2]
        val id = values[3]

        if (!id.startsWith("0x")) {
            return
        }
        //todo 这里的.会被替换为_ 所以有一些ID会不准确
        publicIdList.add("$packageName:$symbolType/$name = $id")

        layoutIdMap["R.$symbolType.${name}"] = id.substring(2).toInt(16)
    }
}