package com.qxml.transform.collect

import com.android.build.gradle.BaseExtension
import com.google.common.io.Files
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.qxml.constant.Constants
import com.qxml.tools.log.LogUtil
import com.qxml.transform.generate.model.StyleInfo
import groovy.util.Node
import groovy.util.XmlParser
import org.gradle.api.Project
import java.io.File

/**
 * 收集merger.xml中的style
 * 由于私有资源无法引用，暂不支持原生style
 */
private val SEP = File.separator
class StyleCollector(private val project: Project, private val curBuildType: String) {

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

    private val extension by lazy { project.extensions.getByName("android") as BaseExtension }

    private val sdkDirectory by lazy { extension.sdkDirectory }

    private val styleExistTypeMap by lazy { hashMapOf<String, MutableList<String>>() }


    private val androidStylesMaterialXml by lazy {
        val androidAttrsPath = "platforms$SEP${extension.compileSdkVersion}${SEP}data${SEP}res${SEP}values${SEP}styles_material.xml"
        File(sdkDirectory, androidAttrsPath)
    }

    private val androidThemesHoloXml by lazy {
        val androidAttrsPath = "platforms$SEP${extension.compileSdkVersion}${SEP}data${SEP}res${SEP}values${SEP}themes_holo.xml"
        File(sdkDirectory, androidAttrsPath)
    }

    private val androidThemeXml by lazy {
        val androidAttrsPath = "platforms$SEP${extension.compileSdkVersion}${SEP}data${SEP}res${SEP}values${SEP}themes.xml"
        File(sdkDirectory, androidAttrsPath)
    }

    private val xmlParser: XmlParser by lazy { XmlParser() }
    private val gson by lazy { GsonBuilder().disableHtmlEscaping().create() }

    /**
     * Map<type, Map<styleName, StyleInfo>>
     */
    fun collect(): Map<String, Map<String, StyleInfo>> {
        styleExistTypeMap.clear()
        val resultStyleMap: HashMap<String, HashMap<String, StyleInfo>> = hashMapOf()

        /*if (androidThemeXml.exists()) {
            val cacheFile = project.buildDir.resolve("intermediates").resolve("android_themes_cache_${androidThemeXml.length()}_${androidStylesMaterialXml.lastModified()}.txt")
            processNodeWithCache(androidThemeXml, cacheFile, resultStyleMap)
        }
        if (androidStylesMaterialXml.exists()) {
            val cacheFile = project.buildDir.resolve("intermediates").resolve("android_style_material_cache_${androidStylesMaterialXml.length()}_${androidStylesMaterialXml.lastModified()}.txt")
            processNodeWithCache(androidStylesMaterialXml, cacheFile, resultStyleMap)
        }
        if (androidThemesHoloXml.exists()) {
            val cacheFile = project.buildDir.resolve("intermediates").resolve("android_themes_holo_cache_${androidThemesHoloXml.length()}_${androidThemesHoloXml.lastModified()}.txt")
            processNodeWithCache(androidThemesHoloXml, cacheFile, resultStyleMap)
        }*/

        project.subprojects.forEach { subProject ->
            parse(subProject, false, resultStyleMap)
        }
        parse(project, true, resultStyleMap)

        styleExistTypeMap.forEach { (styleName, typeList) ->
            typeList.sort()
        }

        val stackList = mutableListOf<StyleInfo>()

        //处理.继承
        /*resultStyleMap.forEach { (type, styleMap) ->
            styleMap.forEach { (styleName, styleInfo) ->
                if (styleInfo.valid) {
                    var curStyle: StyleInfo? = styleInfo
                    val extendItemMap = hashMapOf<String, String>()
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
                                    it.hasParent = true
                                    //it.parentStyle = null
                                    it.parentValid = false
                                }
                                //stackList.clear()
                                break
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
        }*/

        //处理parent
        /*resultStyleMap.forEach { (type, styleMap) ->
            styleMap.forEach { (styleName, styleInfo) ->
                if (styleInfo.valid) {
                    var curStyle: StyleInfo? = styleInfo
                    val extendItemMap = hashMapOf<String, String>()
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
                                    it.parentValid = false
                                }
                                //stackList.clear()
                                break
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
        }*/

        //处理.继承
        resultStyleMap.forEach { (type, styleMap) ->
            styleMap.forEach { (styleName, styleInfo) ->
                if (styleInfo.valid) {
                    var curStyle: StyleInfo? = styleInfo
                    val extendItemMap = hashMapOf<String, String>()
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
                    val extendItemMap = hashMapOf<String, String>()
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

        val newResultMap: HashMap<String, HashMap<String, StyleInfo>> = hashMapOf()
        resultStyleMap.forEach { (type, typeMap) ->
            val resultTypeMap = hashMapOf<String, StyleInfo>()
            newResultMap[type] = resultTypeMap
            typeMap.forEach { (styleName, styleInfo) ->
                if (styleInfo.valid) {
                    resultTypeMap[styleName] = styleInfo
                }
            }
        }

        val saveFile = project.buildDir.resolve(Constants.QXML_CACHE_PATH).resolve(Constants.QXML_STYLE_CACHE_FILE_NAME)
        Files.createParentDirs(saveFile)
        if (!saveFile.exists()) {
            saveFile.createNewFile()
        }
        saveFile.writeText(GsonBuilder().disableHtmlEscaping().create().toJson(newResultMap))
        return newResultMap
    }

    private fun parse(project: Project, isApplication: Boolean, styleMap: HashMap<String, HashMap<String, StyleInfo>>) {
        val incrementalDir = project.buildDir.resolve("intermediates").resolve("incremental")
        val resourceDir = incrementalDir.resolve(if (isApplication) "merge${curBuildType}Resources" else "package${curBuildType}Resources")
        val mergerXmlFile = resourceDir.resolve("merger.xml")

        if (mergerXmlFile.exists()) {
            xmlParser.parse(mergerXmlFile).children().forEach {
                (it as? Node)?.let { node ->
                    processNode(node, styleMap)
                }
            }
        }
    }

    private fun processNodeWithCache(xmlFile: File, cacheFile: File, styleMap: HashMap<String, HashMap<String, StyleInfo>>) {
        if (cacheFile.exists()) {
            styleMap.putAll(gson.fromJson(cacheFile.readText(), object : TypeToken<HashMap<String, StyleInfo>>() {}.type))
        } else {
            xmlParser.parse(xmlFile).children().forEach {
                (it as? Node)?.let { node ->
                    processNode(node, styleMap)
                }
            }
            if (!cacheFile.parentFile.exists()) {
                cacheFile.parentFile.mkdirs()
            }
            cacheFile.createNewFile()
            cacheFile.writeText(gson.toJson(styleMap))
        }
    }

    private fun processNode(node: Node, styleTypeMap: HashMap<String, HashMap<String, StyleInfo>>, type: String? = null) {
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
                        styleMap = hashMapOf()
                        styleTypeMap[curType] = styleMap
                    }

                    val styleInfo = styleMap[name] ?: StyleInfo(name, parent, hashMapOf(), parent != null)
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