package com.qxml.transform

import com.android.build.gradle.BaseExtension
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.qxml.constant.Constants
import com.qxml.tools.CommonUtils
import com.qxml.tools.log.LogUtil
import groovy.util.Node
import groovy.util.XmlParser
import org.gradle.api.Project
import java.io.File

private val SEP = File.separator
class AttrsXmlParser(private val project: Project, private val curBuildType: String) {

    companion object {
        private const val TAG_STYLEABLE = "declare-styleable"
        private const val TAG_ATTR = "attr"
        private const val TAG_FORMAT = "format"
        private const val TAG_NAME = "name"
        private const val TAG_VALUE = "value"
        private const val TAG_ANDROID_PREFIX = "android:"

        private const val TAG_ENUM = "enum"
        private const val TAG_FLAG = "flag"

        private const val TAG_TYPE = "type"
        private const val TAG_LAYOUT = Constants.LAYOUT_PREFIX
        private const val TAG_PATH = "path"
        private const val TAG_QUALIFIERS = "qualifiers"

        private const val TAG_PROJECT_DATA_SET = "dataSet"
        private const val TAG_PROJECT_SOURCE = "source"
        private const val TAG_PROJECT_FILE = "file"

        private const val IS_ENUM_OR_FLAG = Constants.FORMAT_ENUM and Constants.FORMAT_FLAGS
    }

    private val cacheDirPath = "intermediates${SEP}xml_enum_flag_cache"

    private val formatMap = hashMapOf<String, Int>().apply {
        put("boolean", Constants.FORMAT_BOOLEAN)
        put("string", Constants.FORMAT_STRING)
        put("reference", Constants.FORMAT_REFERENCE)
        put("color", Constants.FORMAT_COLOR)
        put("integer", Constants.FORMAT_INTEGER)
        put("dimension", Constants.FORMAT_DIMENSION)
        put("enum", Constants.FORMAT_ENUM)
        put("flags", Constants.FORMAT_FLAGS)
        put("float", Constants.FORMAT_FLOAT)
        put("fraction", Constants.FORMAT_FRACTION)
    }

    private val mGson by lazy { GsonBuilder().disableHtmlEscaping()
        //.registerTypeAdapter(File::class.java)
    .create() }

    private val extension by lazy { project.extensions.getByName("android") as BaseExtension }

    private val sdkDirectory by lazy { extension.sdkDirectory }

    val androidJar by lazy {
        File(sdkDirectory, "platforms${SEP}${extension.compileSdkVersion}${SEP}android.jar")
    }

    private val androidAttrsXml by lazy {
        val androidAttrsPath = "platforms${SEP}${extension.compileSdkVersion}${SEP}data${SEP}res${SEP}values${SEP}attrs.xml"
        File(sdkDirectory, androidAttrsPath)
    }

    private val androidAttrsManifestXml by lazy {
        val androidAttrsPath = "platforms${SEP}${extension.compileSdkVersion}${SEP}data${SEP}res${SEP}values${SEP}attrs_manifest.xml"
        File(sdkDirectory, androidAttrsPath)
    }

    private val mergerXml by lazy {
        CommonUtils.getMergerXmlFile(project.buildDir, curBuildType)
    }

    private val xmlParser: XmlParser by lazy { XmlParser() }

    fun parse(): HashMap<String, AttrInfoModel> {
        val attrInfoMap = hashMapOf<String, AttrInfoModel>()

        val cacheDir = project.buildDir.resolve(cacheDirPath)
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }

        parseWithCache(cacheDir.resolve("${extension.compileSdkVersion}_android_attr_manifest_${androidAttrsManifestXml.lastModified()}.txt")
            , attrInfoMap, androidAttrsManifestXml, true)

        parseWithCache(cacheDir.resolve("${extension.compileSdkVersion}_android_attr_${androidAttrsXml.lastModified()}.txt")
            , attrInfoMap, androidAttrsXml, true)

        parseWithCache(cacheDir.resolve("${mergerXml.length()}_${mergerXml.lastModified()}.txt")
            , attrInfoMap, mergerXml, false)

        /*mergeLayoutList.forEach {
            LogUtil.pl("merge layout "+it)
        }*/

        /*attrInfoMap.forEach { s, attrInfoModel ->
            LogUtil.pl("attrinfo "+s+" "+attrInfoModel)
        }*/
        return attrInfoMap
    }

    private fun parseWithCache(cacheFile: File, resMap: HashMap<String, AttrInfoModel>, xmlFile: File, isAndroid: Boolean) {
        if (cacheFile.exists()) {
            val cache: HashMap<String, AttrInfoModel> = mGson.fromJson(cacheFile.readText(), object : TypeToken<HashMap<String, AttrInfoModel>>() {}.type)
            resMap.putAll(cache)
        } else {
            xmlParser.parse(xmlFile).children().forEach {
                (it as? Node)?.let { node ->
                    processNode(node, resMap, isAndroid)
                }
            }
            cacheFile.createNewFile()
            cacheFile.writeText(mGson.toJson(resMap))
        }
    }

    private fun processNode(node: Node, attrMap: HashMap<String, AttrInfoModel>, isAndroid: Boolean) {
        val nodeName = node.name().toString()
        if (nodeName == TAG_ATTR) {
            val attrName = if (isAndroid) TAG_ANDROID_PREFIX+node.attribute(TAG_NAME).toString() else node.attribute(TAG_NAME).toString()
            var attrInfoModel = attrMap[attrName]
            val formatString = node.attribute(TAG_FORMAT)
            if (attrInfoModel == null) {
                attrInfoModel = AttrInfoModel(hashMapOf(), Constants.FORMAT_UN_KNOW)
                attrMap[attrName] = attrInfoModel
            }
            if (formatString != null) {
                attrInfoModel.attrFormat = getFormatInt(formatString as String)
            } else {
                //如果没有format并且不在Android里，尝试在Android中找
                if (attrInfoModel.attrFormat == Constants.FORMAT_UN_KNOW && !isAndroid) {
                    val androidAttrName = TAG_ANDROID_PREFIX+node.attribute(TAG_NAME).toString()
                    attrInfoModel.attrFormat = (attrMap[androidAttrName]?.attrFormat) ?: Constants.FORMAT_UN_KNOW
                }
            }

            val nodeChildren = node.children()
            //会有
            //<attr name="textStyle">
            //    <flag name="normal" value="0" />
            //    <flag name="bold" value="1" />
            //    <flag name="italic" value="2" />
            //</attr>
            //或者
            //<attr name="eg" format="reference">
            //    <flag name="eg1" value="101" />
            //</attr>
            //的情况，当attr不是enum或flag并且Node有children时检查一遍，最后会有少量无关要紧的仍然为Constants.FORMAT_UN_KNOW
            if ((attrInfoModel.attrFormat and IS_ENUM_OR_FLAG) == 0 && !nodeChildren.isNullOrEmpty()) {
                (nodeChildren.first() as? Node)?.let { childNode ->
                    val name = childNode.name().toString()
                    if (name == TAG_ENUM) {
                        attrInfoModel.attrFormat = attrInfoModel.attrFormat or Constants.FORMAT_ENUM
                    } else if (name == TAG_FLAG) {
                        attrInfoModel.attrFormat = attrInfoModel.attrFormat or Constants.FORMAT_FLAGS
                    }
                }
            }
            nodeChildren?.forEach {
                (it as? Node)?.let { enumOrFlag ->
                    val enumOrFlagName = enumOrFlag.attribute(TAG_NAME).toString()
                    val enumOrFlagValue = enumOrFlag.attribute(TAG_VALUE).toString()
                    //LogUtil.pl("ef "+enumOrFlagName+" "+enumOrFlagValue)
                    try {
                        //if (attrInfoModel == null) attrInfoModel = AttrInfoModel(hashMapOf(), 0)
                        attrInfoModel.attrEnumOrFlagMap[enumOrFlagName] = if (enumOrFlagValue.startsWith("0x", true)) {
                            enumOrFlagValue.substring(2, enumOrFlagValue.length).toLong(16)
                        } else {
                            enumOrFlagValue.toLong()
                        }
                    } catch (e: Exception) {
                        LogUtil.pl("enumOrFlagName err $e")
                    }

                    //LogUtil.pl("e or f "+enumOrFlag.name()+" "+enumOrFlagName+" "+enumOrFlagValue)
                }
            }
            attrInfoModel.let { attrMap[attrName] = it }
        } else {
            if (nodeName == TAG_PROJECT_SOURCE || nodeName == TAG_PROJECT_FILE || nodeName == TAG_PROJECT_DATA_SET || nodeName == TAG_STYLEABLE) {
                node.children()?.forEach {
                    (it as? Node)?.let { childNode ->
                        processNode(childNode, attrMap, isAndroid)
                    }
                }
            }
        }
    }

    private fun getFormatInt(formatString: String): Int {
        var res = 0
        formatString.split("|").forEach {
            if (it.isNotEmpty()) {
                formatMap[it]?.also { flag ->
                    res = res or flag
                }
            }
        }
        return res
    }
}

data class AttrInfoModel(val attrEnumOrFlagMap: HashMap<String, Long>, var attrFormat: Int) {
    fun isBoolean() = attrFormat and Constants.FORMAT_BOOLEAN != Constants.FORMAT_UN_KNOW
    fun isString() = attrFormat and Constants.FORMAT_STRING != Constants.FORMAT_UN_KNOW
    fun isReference() = attrFormat and Constants.FORMAT_REFERENCE != Constants.FORMAT_UN_KNOW
    fun isColor() = attrFormat and Constants.FORMAT_COLOR != Constants.FORMAT_UN_KNOW
    fun isInteger() = attrFormat and Constants.FORMAT_INTEGER != Constants.FORMAT_UN_KNOW
    fun isDimension() = attrFormat and Constants.FORMAT_DIMENSION != Constants.FORMAT_UN_KNOW
    fun isEnum() = attrFormat and Constants.FORMAT_ENUM != Constants.FORMAT_UN_KNOW
    fun isFlag() = attrFormat and Constants.FORMAT_FLAGS != Constants.FORMAT_UN_KNOW
    fun isFloat() = attrFormat and Constants.FORMAT_FLOAT != Constants.FORMAT_UN_KNOW
    fun isFraction() = attrFormat and Constants.FORMAT_FRACTION != Constants.FORMAT_UN_KNOW
}