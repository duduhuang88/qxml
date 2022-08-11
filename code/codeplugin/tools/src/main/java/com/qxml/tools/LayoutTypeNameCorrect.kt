package com.qxml.tools

object LayoutTypeNameCorrect {

    fun toDisplayText(layoutType: String): String {
        return if (layoutType.isEmpty()) "layout" else if (!layoutType.startsWith("layout", true)) "layout-$layoutType" else layoutType
    }

}