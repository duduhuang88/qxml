package com.qxml.qxml_androidx.gen.imageView

import android.graphics.drawable.Drawable
import com.qxml.tools.ReflectUtils

object ImageViewHelper {

    private val fixDrawableMethod = ReflectUtils.getDeclaredMethodOrNull(androidx.appcompat.widget.DrawableUtils::class.java, "fixDrawable", Drawable::class.java)

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