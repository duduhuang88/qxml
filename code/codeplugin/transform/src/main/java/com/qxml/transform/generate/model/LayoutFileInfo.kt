package com.qxml.transform.generate.model

class LayoutFileInfoContent(val layoutFileInfoList: MutableList<LayoutFileInfo>, val viewDebug: Boolean, val useFactory: Boolean, val compatMode: Int, val ignoreUnImplementAttr: Boolean)

class LayoutFileInfo(val name: String, val type: String, val filePath: String, val fileLastModify: Long, var resourceTypeName: String = ""): Comparable<LayoutFileInfo> {
    override fun toString(): String {
        return "XmlTypeInfo(name='$name', type='$type', filePath='$filePath')"
    }

    override fun compareTo(other: LayoutFileInfo): Int {
        return type.compareTo(other.type)
    }
}