package com.qxml.gradle

import com.google.common.io.Files
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
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
class LayoutFileCollector(private val project: Project, private val mainProjectVariantType: String,
                          private val mergeXmlFiles: Set<File>, private val outputDir: File,
                          private val libProjectVariantInfoMap: Map<String, String>) {

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

    private val xmlParser: XmlParser by lazy(LazyThreadSafetyMode.NONE) { XmlParser() }

    private val layoutFileList = mutableListOf<LayoutFileInfo>()

    private val gson by lazy(LazyThreadSafetyMode.NONE) { GsonBuilder().disableHtmlEscaping().create() }

    fun collect(): MutableList<LayoutFileInfo> {
        val resultLayoutMap = hashMapOf<String, HashMap<String, LayoutFileInfo>>()

        val applicationProjectMergerXmlFile = mergeXmlFiles.last()

        val mergerXmlFilePathList = mutableListOf<ProjectMergeFileInfo>()
        val node = xmlParser.parse(applicationProjectMergerXmlFile)
        findProjectIntermediatesDir(mergerXmlFilePathList, node)

        mergerXmlFilePathList.forEach {
            parse(it.projectName, File(it.path), resultLayoutMap)
        }
        parse(project.name, applicationProjectMergerXmlFile, resultLayoutMap, node)

        resultLayoutMap.forEach { (_, hashMap) ->
            hashMap.forEach { (_, xmlTypeInfo) ->
                layoutFileList.add(xmlTypeInfo)
            }
        }

        val outputFile = outputDir.resolve(Constants.QXML_LAYOUT_CACHE_FILE_NAME)
        Files.createParentDirs(outputFile)
        if (!outputFile.exists()) {
            outputFile.createNewFile()
        }
        outputFile.writeText(gson.toJson(layoutFileList))

        return layoutFileList
    }

    private fun parse(projectName: String, mergeFile: File, layoutFileMap: HashMap<String, HashMap<String, LayoutFileInfo>>, node: Node?= null) {
        if (mergeFile.exists()) {
            val gson = GsonBuilder().disableHtmlEscaping().create()
            val cacheLayoutFileMap = hashMapOf<String, HashMap<String, LayoutFileInfo>>()
            val cacheFile = outputDir.resolve(Constants.MERGE_XML_CACHE_DIR_NAME).resolve("${projectName}_${mainProjectVariantType}.txt")
            var cacheValid = false
            val mergerXmlFileKey = "${mergeFile.lastModified()}_${mergeFile.length()}"
            if (cacheFile.exists()) {
                cacheFile.readLines().forEachIndexed { index, s ->
                    if (index == 0) {
                        if (mergerXmlFileKey == s) {
                            cacheValid = true
                        }
                    } else {
                        if (cacheValid) {
                            cacheLayoutFileMap.putAll(gson.fromJson(s, object : TypeToken<HashMap<String, HashMap<String, LayoutFileInfo>>>() {}.type))
                        }
                        return@forEachIndexed
                    }
                }
            }
            if (!cacheValid) {
                (node ?: xmlParser.parse(mergeFile)).children().forEach {
                    (it as? Node)?.let { node ->
                        processNode(node, cacheLayoutFileMap)
                    }
                }
                Files.createParentDirs(cacheFile)
                if (!cacheFile.exists()) {
                    cacheFile.createNewFile()
                }
                cacheFile.writeText("${mergerXmlFileKey}\n${gson.toJson(cacheLayoutFileMap)}")
            }
            cacheLayoutFileMap.forEach { (type, hashMap) ->
                val map = layoutFileMap[type]?: hashMapOf()
                map.putAll(hashMap)
                layoutFileMap[type] = map
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
                        val layoutFile = File(path)
                        xmlTypeMap[name] = LayoutFileInfo(name, if (layoutType.isEmpty()) layoutType else layoutFile.parentFile.name, path)
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

    /**
     * 按依赖顺序搜索module的merger.xml
     * @param mergerXmlFilePathList MutableList<String>
     * @param node Node
     */
    private fun findProjectIntermediatesDir(mergerXmlFilePathList: MutableList<ProjectMergeFileInfo>, node: Node) {
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
                                                val buildDir = debugDir.parentFile
                                                if (buildDir?.name == Constants.BUILD) {
                                                    val variantType = libProjectVariantInfoMap[buildDir.absolutePath] ?: mainProjectVariantType
                                                    val packageResourceDir = debugDir.resolve(INCREMENTAL).resolve("package${variantType}Resources").resolve(MERGER_XML)
                                                    if (packageResourceDir.exists()) {
                                                        mergerXmlFilePathList.add(ProjectMergeFileInfo(config.substring(1), packageResourceDir.absolutePath))
                                                        return@childrenBreak
                                                    }
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

    private data class ProjectMergeFileInfo(val projectName: String, val path: String)
}