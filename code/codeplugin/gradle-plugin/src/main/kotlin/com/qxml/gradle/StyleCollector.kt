package com.qxml.gradle

import com.google.common.io.Files
import com.google.gson.GsonBuilder
import com.qxml.constant.Constants
import com.qxml.transform.generate.model.StyleInfo
import groovy.util.Node
import groovy.util.XmlParser
import org.gradle.api.Project
import java.io.File
import java.util.*

/**
 * 收集merger.xml中的style
 * 由于私有资源无法引用，暂不支持原生style
 */
class StyleCollector(private val project: Project, private val curBuildType: String, private val mergeXmlFile: File, private val outputDir: File) {

    companion object {
        private const val TAG_NAME = "name"
        private const val TAG_PARENT = "parent"

        private const val TAG_STYLE = "style"
        private const val TAG_ITEM = "item"

        private const val TAG_QUALIFIERS = "qualifiers"
        private const val TAG_PROJECT_DATA_SET = "dataSet"
        private const val TAG_PROJECT_SOURCE = "source"
        private const val TAG_PROJECT_FILE = "file"

    }

    private val styleExistTypeMap by lazy(LazyThreadSafetyMode.NONE) { hashMapOf<String, MutableList<String>>() }

    private val xmlParser: XmlParser by lazy(LazyThreadSafetyMode.NONE) { XmlParser() }
    private val gson by lazy(LazyThreadSafetyMode.NONE) { GsonBuilder().disableHtmlEscaping().create() }

    /**
     * Map<type, Map<styleName, StyleInfo>>
     */
    fun collect(): Map<String, Map<String, StyleInfo>> {
        styleExistTypeMap.clear()
        val resultStyleMap: TreeMap<String, TreeMap<String, StyleInfo>> = TreeMap()

        parse(mergeXmlFile, resultStyleMap)

        styleExistTypeMap.forEach { (styleName, typeList) ->
            typeList.sort()
        }

        val stackList = mutableListOf<StyleInfo>()

        //处理.继承
        resultStyleMap.forEach { (type, styleMap) ->
            styleMap.forEach { (styleName, styleInfo) ->
                if (styleInfo.valid) {
                    var curStyle: StyleInfo? = styleInfo
                    val extendItemMap = TreeMap<String, String>()
                    while (curStyle != null) {
                        stackList.add(curStyle)

                        if (curStyle.hasParent || !curStyle.styleName.contains(".")) {
                            extendItemMap.putAll(curStyle.itemMap)
                            curStyle = null
                        } else {
                            val childStyle = curStyle
                            childStyle.hasParent = true
                            val parentStyleName = curStyle.styleName.substring(0, curStyle.styleName.lastIndexOf("."))
                            curStyle = styleMap[parentStyleName]
                            if (curStyle != null && curStyle.valid) {
                                childStyle.parentStyle = curStyle.styleName
                            } else {
                                //这里不检查跨type继承了
                                stackList.forEach {
                                    it.valid = false
                                    it.hasParent = false
                                    it.parentStyle = null
                                }
                                stackList.clear()
                            }
                        }
                    }
                    if (stackList.size > 1) {
                        while (stackList.isNotEmpty()) {
                            val parentStyleInfo = stackList.removeAt(stackList.size - 1)
                            if (extendItemMap.isNotEmpty()) {
                                extendItemMap.forEach { (itemName, value) ->
                                    parentStyleInfo.itemMap.putIfAbsent(itemName, value)
                                }
                            }
                            parentStyleInfo.itemMap.forEach { (itemName, value) ->
                                extendItemMap.putIfAbsent(itemName, value)
                            }
                        }
                    }
                }
                stackList.clear()
            }
        }

        //处理parent
        resultStyleMap.forEach { (type, styleMap) ->
            styleMap.forEach { (styleName, styleInfo) ->
                if (styleInfo.valid) {
                    var curStyle: StyleInfo? = styleInfo
                    val extendItemMap = TreeMap<String, String>()
                    while (curStyle?.parentStyle != null) {
                        stackList.add(curStyle!!)
                        val parentStyleName = curStyle!!.parentStyle!!
                        curStyle!!.parentStyle = null
                        curStyle!!.hasParent = true
                        curStyle = resultStyleMap[type]?.get(parentStyleName)
                        if (curStyle == null) {//当前type中的parentStyleName不存在，尝试查找其他type中的parentStyleName
                            styleExistTypeMap[parentStyleName]?.also { typeList->
                                for (i in typeList.size - 1 downTo 0) {
                                    val existType = typeList[i]
                                    if (existType != type) {
                                        curStyle = resultStyleMap[existType]?.get(parentStyleName)
                                        if (curStyle != null) {
                                            break
                                        }
                                    }
                                }
                            }
                        }
                        if (curStyle != null && curStyle!!.parentStyle == null && curStyle!!.valid) {
                            extendItemMap.putAll(curStyle!!.itemMap)
                        } else {
                            if (curStyle == null || !curStyle!!.valid) {
                                curStyle = null
                                stackList.forEach {
                                    it.valid = false
                                }
                                stackList.clear()
                            }
                        }
                    }
                    if (stackList.isNotEmpty()) {
                        while (stackList.isNotEmpty()) {
                            val parentStyleInfo = stackList.removeAt(stackList.size - 1)
                            if (extendItemMap.isNotEmpty()) {
                                extendItemMap.forEach { (itemName, value) ->
                                    parentStyleInfo.itemMap.putIfAbsent(itemName, value)
                                }
                            }
                            parentStyleInfo.itemMap.forEach { (itemName, value) ->
                                extendItemMap.putIfAbsent(itemName, value)
                            }
                        }
                    }
                }
            }
        }

        val newResultMap: TreeMap<String, TreeMap<String, StyleInfo>> = TreeMap()
        resultStyleMap.forEach { (type, typeMap) ->
            val resultTypeMap = TreeMap<String, StyleInfo>()
            newResultMap[type] = resultTypeMap
            typeMap.forEach { (styleName, styleInfo) ->
                if (styleInfo.valid) {
                    resultTypeMap[styleName] = styleInfo
                }
            }
        }

        val outputFile = outputDir.resolve(Constants.QXML_STYLE_CACHE_FILE_NAME)
        Files.createParentDirs(outputFile)
        if (!outputFile.exists()) {
            outputFile.createNewFile()
        }
        outputFile.writeText(gson.toJson(newResultMap))
        return newResultMap
    }

    private fun parse(mergerXmlFile: File, resultStyleMap: TreeMap<String, TreeMap<String, StyleInfo>>) {
        if (mergerXmlFile.exists()) {
            xmlParser.parse(mergerXmlFile).children().forEach {
                (it as? Node)?.let { node ->
                    processNode(node, resultStyleMap)
                }
            }
        }
    }

    private fun processNode(node: Node, styleTypeMap: TreeMap<String, TreeMap<String, StyleInfo>>, type: String? = null) {
        val nodeName = node.name().toString()
        var curType = type
        if (nodeName == TAG_PROJECT_SOURCE || nodeName == TAG_PROJECT_FILE || nodeName == TAG_PROJECT_DATA_SET || nodeName == TAG_STYLE) {
            if (nodeName == TAG_PROJECT_FILE) {
                //当前类型 eg: v21 v23
                curType = node.attribute(TAG_QUALIFIERS) as? String
            } else if (nodeName == TAG_STYLE /*&& curType != null*/) {
                if (curType == null) {
                    curType = ""
                }
                val name = node.attribute(TAG_NAME) as? String
                val parent = node.attribute(TAG_PARENT) as? String
                if (name != null) {
                    var styleMap = styleTypeMap[curType]
                    if (styleMap == null) {
                        styleMap = TreeMap()
                        styleTypeMap[curType] = styleMap
                    }

                    val styleInfo = styleMap[name] ?: StyleInfo(name, parent, TreeMap(), parent != null)
                    val itemMap = styleInfo.itemMap
                    node.children()?.forEach {
                        (it as? Node)?.let { item ->
                            if (item.name().toString() == TAG_ITEM) {
                                val itemName = item.attribute(TAG_NAME) as? String
                                val itemValue = item.text()
                                if (itemName != null) {
                                    itemMap[itemName] = itemValue
                                }
                            }
                        }
                    }
                    var typeList = styleExistTypeMap[name]
                    if (typeList == null) {
                        typeList = mutableListOf()
                        styleExistTypeMap[name] = typeList
                    }
                    typeList.add(curType)
                    styleMap[name] = styleInfo
                }
                return
            }
            node.children()?.forEach {
                (it as? Node)?.let { childNode ->
                    processNode(childNode, styleTypeMap, curType)
                }
            }
        }
    }
}