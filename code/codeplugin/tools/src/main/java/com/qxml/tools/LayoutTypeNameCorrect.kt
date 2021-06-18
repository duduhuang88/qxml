package com.qxml.tools

object LayoutTypeNameCorrect {

    fun toDisplayText(layoutType: String): String {
        return if (layoutType.isEmpty()) "layout" else "layout-$layoutType"
    }

}