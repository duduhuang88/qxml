package com.qxml.helper

import android.os.Build
import android.util.Log
import android.view.View
import com.qxml.R
import com.qxml.tools.DrawableTools.Companion.getDrawable

class QxmlHelper {

    companion object {

        @JvmField
        var showDebug = true

        @JvmStatic
        fun showQxmlDebug(view: View) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && showDebug /*&& false*/) {
                view.foreground = com.qxml.tools.DrawableTools.getDrawable(view.context, view.context.resources, R.drawable.qxml_lib_debug_foreground)
            }
        }
    }

}