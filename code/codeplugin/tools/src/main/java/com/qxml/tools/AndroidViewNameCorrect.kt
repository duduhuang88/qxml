package com.qxml.tools

import java.util.*

object AndroidViewNameCorrect {

    private const val VIEW = "View"
    const val VIEW_STUB = "ViewStub"
    private const val SURFACE_VIEW = "SurfaceView"
    private const val TEXTURE_VIEW = "TextureView"
    private const val WEB_VIEW = "WebView"

    private val CORRECT_ANDROID_NAMES = arrayOf(
        VIEW,
        VIEW_STUB,
        SURFACE_VIEW,
        TEXTURE_VIEW
    )

    private const val CORRECT_PRE_FIX_WIDGET = "android.widget."
    private const val CORRECT_PRE_FIX_VIEW = "android.view."
    private const val CORRECT_PRE_FIX_WEBKIT = "android.webkit."

    const val VIEW_STUB_FULL_NAME = "android.view.ViewStub"


    private val correctMap = HashMap<String, String>()

    @JvmStatic
    fun correct(origName: String): String {
        initCorrectMap()
        if (origName.contains(".")) {
            return origName
        }
        val correctName = correctMap[origName]
        if (correctName != null) {
            return correctName
        }
        if (origName == "fragment") {
            return "android.support.v4.app.Fragment"
        }
        return CORRECT_PRE_FIX_WIDGET + origName
    }

    private fun initCorrectMap() {
        if (correctMap.isEmpty()) {
            CORRECT_ANDROID_NAMES.forEach { name ->
                addCorrectView(name)
            }
            correctMap[WEB_VIEW] = CORRECT_PRE_FIX_WEBKIT + WEB_VIEW
        }
    }

    private fun addCorrectView(name: String) {
        correctMap[name] = CORRECT_PRE_FIX_VIEW + name
    }
}