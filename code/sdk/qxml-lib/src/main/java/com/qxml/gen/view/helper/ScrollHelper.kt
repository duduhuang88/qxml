package com.qxml.gen.view.helper

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import com.qxml.tools.ReflectUtils
import java.lang.reflect.Field
import java.lang.reflect.Method

object ScrollHelper {

    private val getScrollCacheMethod = ReflectUtils.getDeclaredMethodOrNull(View::class.java, "getScrollCache")

    private var scrollBarField: Field? = null
    private var setHorizontalThumbDrawableMethod: Method? = null
    private var setVerticalThumbDrawableMethod: Method? = null
    private var setHorizontalTrackDrawableMethod: Method? = null
    private var setVerticalTrackDrawableMethod: Method? = null
    private var setAlwaysDrawVerticalTrackMethod: Method? = null
    private var setAlwaysDrawHorizontalTrackMethod: Method? = null

    @JvmStatic
    fun setScrollbarThumb(view: View, drawable: Drawable?, isVertical: Boolean) {
        try {
            if (getScrollCacheMethod != null) {
                val scrollabilityCacheObj: Any = getScrollCacheMethod.invoke(view)!!
                initField(scrollabilityCacheObj.javaClass)
                val scrollbarObj: Any = scrollBarField!!.get(scrollabilityCacheObj)!!
                initMethod(scrollbarObj.javaClass)
                if (isVertical) {
                    setVerticalThumbDrawableMethod?.invoke(scrollbarObj, drawable)
                } else {
                    setHorizontalThumbDrawableMethod?.invoke(scrollbarObj, drawable)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setScrollbarTrack(view: View, drawable: Drawable?, isVertical: Boolean) {
        try {
            if (getScrollCacheMethod != null) {
                val scrollabilityCacheObj: Any = getScrollCacheMethod.invoke(view)!!
                initField(scrollabilityCacheObj.javaClass)
                val scrollbarObj: Any = scrollBarField!!.get(scrollabilityCacheObj)!!
                initMethod(scrollbarObj.javaClass)
                if (isVertical) {
                    setVerticalTrackDrawableMethod?.invoke(scrollbarObj, drawable)
                } else {
                    setHorizontalTrackDrawableMethod?.invoke(scrollbarObj, drawable)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setAlwaysDrawVerticalTrack(view: View, always: Boolean, isVertical: Boolean) {
        try {
            if (getScrollCacheMethod != null) {
                val scrollabilityCacheObj: Any = getScrollCacheMethod.invoke(view)!!
                initField(scrollabilityCacheObj.javaClass)
                val scrollbarObj: Any = scrollBarField!!.get(scrollabilityCacheObj)!!
                initMethod(scrollbarObj.javaClass)
                if (isVertical) {
                    setAlwaysDrawVerticalTrackMethod?.invoke(scrollbarObj, always)
                } else {
                    setAlwaysDrawHorizontalTrackMethod?.invoke(scrollbarObj, always)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initField(scrollabilityCacheObjClazz: Class<*>) {
        if (scrollBarField == null) {
            synchronized(this) {
                if (scrollBarField == null) {
                    scrollBarField = scrollabilityCacheObjClazz.getField("scrollBar")
                }
            }
        }
    }

    private fun initMethod(scrollbarClazz: Class<*>) {
        if (setHorizontalThumbDrawableMethod == null) {
            synchronized(this) {
                if (setHorizontalThumbDrawableMethod == null) {
                    setHorizontalThumbDrawableMethod = scrollbarClazz.getDeclaredMethod("setHorizontalThumbDrawable", Drawable::class.java)
                    setVerticalThumbDrawableMethod = scrollbarClazz.getDeclaredMethod("setVerticalThumbDrawable", Drawable::class.java)
                    setHorizontalThumbDrawableMethod?.isAccessible = true
                    setVerticalThumbDrawableMethod?.isAccessible = true
                    setHorizontalTrackDrawableMethod = scrollbarClazz.getDeclaredMethod("setHorizontalTrackDrawable", Drawable::class.java)
                    setVerticalTrackDrawableMethod = scrollbarClazz.getDeclaredMethod("setVerticalTrackDrawable", Drawable::class.java)
                    setHorizontalTrackDrawableMethod?.isAccessible = true
                    setVerticalTrackDrawableMethod?.isAccessible = true
                    setAlwaysDrawVerticalTrackMethod = scrollbarClazz.getDeclaredMethod("setAlwaysDrawVerticalTrack", Boolean::class.java)
                    setAlwaysDrawHorizontalTrackMethod = scrollbarClazz.getDeclaredMethod("setAlwaysDrawHorizontalTrack", Boolean::class.java)
                    setAlwaysDrawHorizontalTrackMethod?.isAccessible = true
                    setAlwaysDrawVerticalTrackMethod?.isAccessible = true
                }
            }
        }
    }

}