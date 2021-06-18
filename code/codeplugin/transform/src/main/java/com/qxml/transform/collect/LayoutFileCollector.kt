package com.qxml.transform.collect

import com.qxml.constant.Constants
import com.qxml.transform.generate.model.LayoutFileInfo
import groovy.util.Node
import groovy.util.XmlParser
import org.gradle.api.Project
import java.io.File


/**
 * 收集所有可代码依赖project的layout文件信息
 * 对于子module间的同名layout文件会按project顺序选择
 */
class LayoutFileCollector(private val project: Project, private val curBuildType: String) {

    companion object {
        private const val TAG_NAME = "name"

        private const val TAG_TYPE = "type"
        private const val TAG_LAYOUT = Constants.LAYOUT_PREFIX
        private const val TAG_PATH = "path"
        private const val TAG_CONFIG = "config"
        private const val TAG_QUALIFIERS = "qualifiers"
        private const val TAG_FROM_DEPENDENCY = "from-dependency"

        private const val TAG_PROJECT_MERGER_ROOT = "merger"
        private const val TAG_PROJECT_DATA_SET = "dataSet"
        private const val TAG_PROJECT_SOURCE = "source"
        private const val TAG_PROJECT_FILE = "file"

        private const val INTERMIDIATES = "intermediates"
        private const val INCREMENTAL = "incremental"
        private const val MERGER_XML = "merger.xml"
    }

    private val xmlParser: XmlParser by lazy { XmlParser() }

    private val layoutFileList = mutableListOf<LayoutFileInfo>()

    fun collect(): MutableList<LayoutFileInfo> {
        val resultLayoutMap = hashMapOf<String, HashMap<String, LayoutFileInfo>>()

        val incrementalDir = project.buildDir.resolve(INTERMIDIATES).resolve(INCREMENTAL)
        val resourceDir = incrementalDir.resolve("merge${curBuildType}Resources")
        val mergerXmlFile = resourceDir.resolve(MERGER_XML)

        val mergerXmlFilePathList = mutableListOf<String>()
        val node = xmlParser.parse(mergerXmlFile)
        findProjectIntermediatesDir(mergerXmlFilePathList, node)
        mergerXmlFilePathList.add(mergerXmlFile.absolutePath)

        mergerXmlFilePathList.forEach { path ->
            parse(File(path), resultLayoutMap)
        }

        resultLayoutMap.forEach { (_, hashMap) ->
            hashMap.forEach { (_, xmlTypeInfo) ->
                layoutFileList.add(xmlTypeInfo)
            }
        }
        return layoutFileList
    }

    private fun findProjectIntermediatesDir(mergerXmlFilePathList: MutableList<String>, node: Node) {
        val nodeName = node.name().toString()
        if (nodeName == TAG_PROJECT_MERGER_ROOT) {
            node.children()?.forEach {
                (it as? Node)?.let { childNode ->
                    findProjectIntermediatesDir(mergerXmlFilePathList, childNode)
                }
            }
        } else if (nodeName == TAG_PROJECT_DATA_SET) {
            val config = node.attribute(TAG_CONFIG)?.toString()
            if (config != null && config.startsWith(":") && !config.contains("$")) {
                run childrenBreak@{
                    node.children()?.forEach { it ->
                        val childNode = it as? Node
                        childNode?.name()?.toString()?.also { childNodeName ->
                            if (childNodeName == TAG_PROJECT_SOURCE) {
                                val path = childNode.attribute(TAG_PATH)?.toString()
                                path?.also {
                                    try {
                                        var debugDir: File? = File(path)
                                        while (debugDir != null) {
                                            if (debugDir.name == INTERMIDIATES) {
                                                val packageResourceDir = debugDir.resolve(INCREMENTAL).resolve("package${curBuildType}Resources").resolve(MERGER_XML)
                                                if (packageResourceDir.exists()) {
                                                    mergerXmlFilePathList.add(packageResourceDir.absolutePath)
                                                    return@childrenBreak
                                                }
                                            }
                                            debugDir = debugDir.parentFile
                                        }
                                    } catch (e: Exception) {

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun parse(mergerXmlFile: File, layoutFileMap: HashMap<String, HashMap<String, LayoutFileInfo>>) {
        if (mergerXmlFile.exists()) {
            xmlParser.parse(mergerXmlFile).children().forEach {
                (it as? Node)?.let { node ->
                    processNode(node, layoutFileMap)
                }
            }
        }
    }

    private fun processNode(node: Node, layoutMap: HashMap<String, HashMap<String, LayoutFileInfo>>) {
        val nodeName = node.name().toString()
        if (nodeName == TAG_PROJECT_SOURCE || nodeName == TAG_PROJECT_FILE || nodeName == TAG_PROJECT_DATA_SET) {
            if (nodeName == TAG_PROJECT_DATA_SET) {
                val fromDependency = node.attribute(TAG_FROM_DEPENDENCY)?.toString()
                if (fromDependency != null && fromDependency.equals("true", true)) {
                    return
                }
            } else if (nodeName == TAG_PROJECT_FILE) {
                val type = node.attribute(TAG_TYPE)
                if (type != null && type == TAG_LAYOUT) {
                    val name = node.attribute(TAG_NAME) as? String
                    val path = node.attribute(TAG_PATH) as? String
                    val qualifiers = node.attribute(TAG_QUALIFIERS) as? String
                    val layoutType = if (qualifiers.isNullOrEmpty()) {
                        ""
                    } else {
                        qualifiers
                    }
                    if (name != null && path != null) {
                        var xmlTypeMap = layoutMap[layoutType]
                        if (xmlTypeMap == null) {
                            xmlTypeMap = hashMapOf()
                            layoutMap[layoutType] = xmlTypeMap
                        }
                        xmlTypeMap[name] = LayoutFileInfo(name, layoutType, path)
                        return
                    }
                }
            }
            node.children()?.forEach {
                (it as? Node)?.let { childNode ->
                    processNode(childNode, layoutMap)
                }
            }
        }
    }

}