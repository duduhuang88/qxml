package com.qxml.gradle

import com.google.gson.GsonBuilder
import com.qxml.constant.Constants
import com.qxml.tools.log.LogUtil
import groovy.util.Node
import groovy.util.XmlParser
import org.gradle.api.DefaultTask
import org.gradle.api.Project
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

    @get:Input
    var curBuildType: String? = null

    @TaskAction
    fun collect() {
        val exactIdMap = ExactIdCollector(project, curBuildType!!).exactCollect()
        val reader = IdCollectSymbolListReader(packageName!!, exactIdMap)
        val content = reader.readSymbolTable(rFile!!.singleFile)
        outputFile!!.writeText(content)
        val stringBuilder = StringBuilder()
        reader.publicIdList.forEach {
            stringBuilder.append(it).append("\n")
        }
        publicROutputFile!!.writeText(stringBuilder.toString())
    }
}

internal class IdCollectSymbolListReader(private val packageName: String, private val exactIdMap: Map<String, String>) {

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

        val idName = "$symbolType/$name"
        val finalIdName = exactIdMap[idName] ?: idName
        publicIdList.add("$packageName:$finalIdName = $id")

        layoutIdMap["R.$symbolType.${name}"] = id.substring(2).toInt(16)
    }
}

internal class ExactIdCollector(private val project: Project, private val curBuildType: String) {

    companion object {
        private const val TAG_NAME = "name"

        private const val TAG_TYPE = "type"

        private const val TAG_ITEM = "item"

        private const val TAG_PROJECT_MERGER_ROOT = "merger"
        private const val TAG_PROJECT_DATA_SET = "dataSet"
        private const val TAG_PROJECT_SOURCE = "source"
        private const val TAG_PROJECT_FILE = "file"
        private const val TAG_DECLARE_STYLEABLE = "declare-styleable"
    }

    private val transferMap = hashMapOf<String, String>().apply {
        put(TAG_ITEM, "")
        put("integer", "")
        put("dimen", "")
        put("string", "")
        put("fraction", "")
        put("color", "")
        put("bool", "")
        put("attr", "")
        put("drawable", "")
        put("style", "")

        put("declare-styleable", "styleable")
        put("array", "array")
        put("string-array", "array")
        put("integer-array", "array")
    }

    private val rootNode by lazy {
        XmlParser().parse(
            project.buildDir.resolve(Constants.INTERMEDIATES)
                .resolve(Constants.INCREMENTAL).resolve("merge${curBuildType}Resources")
                .resolve(Constants.MERGER_XML)
        )
    }

    fun exactCollect(): Map<String, String> {
        val exactIdMap = hashMapOf<String, String>()
        processNode(rootNode, exactIdMap)
        return exactIdMap
    }

    private fun processNode(node: Node, exactIdMap: HashMap<String, String>) {
        val nodeName = node.name().toString()
        if (nodeName == TAG_PROJECT_MERGER_ROOT || nodeName == TAG_PROJECT_SOURCE
            || nodeName == TAG_PROJECT_FILE || nodeName == TAG_PROJECT_DATA_SET) {
            node.children().forEach {
                (it as? Node)?.let { node ->
                    processNode(node, exactIdMap)
                }
            }
        } else {
            transferMap[nodeName]?.let { transferMiddleName ->
                val name = node.attribute(TAG_NAME) as? String
                if (name != null && name.contains(".")) {
                    val symbolType =
                        if (nodeName == TAG_ITEM) node.attribute(TAG_TYPE) as String else {
                            if (transferMiddleName.isEmpty()) nodeName else transferMiddleName
                        }
                    exactIdMap["$symbolType/${name.replace(".", "_")}"] = "$symbolType/$name"
                }
            }
            if (nodeName == TAG_DECLARE_STYLEABLE) {
                node.children().forEach {
                    (it as? Node)?.let { node ->
                        processNode(node, exactIdMap)
                    }
                }
            }
        }
    }
}