package com.qxml.transform.generate.model

class LayoutFileInfo(val name: String, val type: String, val filePath: String, var resourceTypeName: String = ""): Comparable<LayoutFileInfo> {
    override fun toString(): String {
        return "XmlTypeInfo(name='$name', type='$type', filePath='$filePath')"
    }

    override fun compareTo(other: LayoutFileInfo): Int {
        return type.compareTo(other.type)
    }
}