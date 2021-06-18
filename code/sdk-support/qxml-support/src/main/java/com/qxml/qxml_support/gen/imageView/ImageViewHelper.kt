package com.qxml.qxml_support.gen.imageView

import android.graphics.drawable.Drawable
import com.qxml.tools.ReflectUtils
import java.lang.Exception

object ImageViewHelper {

    private val fixDrawableMethod = ReflectUtils.getDeclaredMethodOrNull(android.support.v7.widget.DrawableUtils::class.java, "fixDrawable", Drawable::class.java)

    @JvmStatic
    fun fixDrawable(drawable: Drawable?) {
        try {
            fixDrawableMethod?.also {
                if (drawable != null) {
                    it.invoke(null, drawable)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}