package com.qxml.qxml_support.gen.recyclerView

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.support.v7.widget.RecyclerView
import com.qxml.tools.DrawableTools
import com.qxml.tools.ReflectUtils
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException

object RecyclerViewHelper {

    private val initFastScrollerMethod = ReflectUtils.getDeclaredMethodOrNull(RecyclerView::class.java, "initFastScroller",
        StateListDrawable::class.java,
        Drawable::class.java, StateListDrawable::class.java, Drawable::class.java)

    private val LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = arrayOf<java.lang.Class<*>?>(
        android.content.Context::class.java, android.util.AttributeSet::class.java
        , java.lang.Integer.TYPE, java.lang.Integer.TYPE
    )

    @JvmStatic
    fun initFastScroller(
        recyclerView: RecyclerView,
        context: Context,
        resources: Resources,
        fastScrollHorizontalThumbDrawable: Int,
        fastScrollHorizontalTrackDrawable: Int,
        fastScrollVerticalThumbDrawable: Int,
        fastScrollVerticalTrackDrawable: Int
    ) {

        try {
            val verticalThumbDrawable = if (fastScrollHorizontalThumbDrawable == 0) null else
                DrawableTools.getDrawable(context, resources, fastScrollHorizontalThumbDrawable) as? StateListDrawable
            val verticalTrackDrawable: Drawable? = if (fastScrollHorizontalTrackDrawable == 0) null else
                DrawableTools.getDrawable(context, resources, fastScrollHorizontalTrackDrawable)
            val horizontalThumbDrawable = if (fastScrollVerticalThumbDrawable == 0) null else
                DrawableTools.getDrawable(context, resources, fastScrollVerticalThumbDrawable) as? StateListDrawable
            val horizontalTrackDrawable: Drawable? =  if (fastScrollVerticalTrackDrawable == 0) null else
                DrawableTools.getDrawable(context, resources, fastScrollVerticalTrackDrawable)
            initFastScrollerMethod?.invoke(
                recyclerView,
                verticalThumbDrawable,
                verticalTrackDrawable,
                horizontalThumbDrawable,
                horizontalTrackDrawable
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun createLayoutManager(
        context: Context,
        className: String
    ): RecyclerView.LayoutManager? {
        var className: String? = className
        if (className != null) {
            className = className.trim { it <= ' ' }
            if (!className.isEmpty()) {
                className = getFullClassName(context, className)
                try {
                    val classLoader: ClassLoader = context.classLoader
                    val layoutManagerClass = classLoader.loadClass(className).asSubclass(
                        RecyclerView.LayoutManager::class.java
                    )
                    var constructorArgs: Array<Any?>? = null
                    var constructor: Constructor<*>
                    try {
                        constructor =
                            layoutManagerClass.getConstructor(*LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE)
                        constructorArgs = arrayOf(context, null, 0, 0)
                    } catch (var13: NoSuchMethodException) {
                        constructor = try {
                            layoutManagerClass.getConstructor()
                        } catch (var12: NoSuchMethodException) {
                            var12.initCause(var13)
                            throw IllegalStateException(
                                "Error creating LayoutManager " + className,
                                var12
                            )
                        }
                    }
                    constructor.isAccessible = true
                    return constructor.newInstance(constructorArgs) as RecyclerView.LayoutManager
                } catch (var14: ClassNotFoundException) {
                    throw IllegalStateException(
                        "Unable to find LayoutManager " + className,
                        var14
                    )
                } catch (var15: InvocationTargetException) {
                    throw IllegalStateException(
                        "Could not instantiate the LayoutManager: " + className,
                        var15
                    )
                } catch (var16: InstantiationException) {
                    throw IllegalStateException(
                        "Could not instantiate the LayoutManager: " + className,
                        var16
                    )
                } catch (var17: IllegalAccessException) {
                    throw IllegalStateException(
                        "Cannot access non-public constructor " + className,
                        var17
                    )
                } catch (var18: ClassCastException) {
                    throw IllegalStateException(
                        "Class is not a LayoutManager " + className,
                        var18
                    )
                }
            }
        }
        return null
    }

    @JvmStatic
    private fun getFullClassName(context: Context, className: String): String? {
        return if (className[0] == '.') {
            context.packageName + className
        } else {
            if (className.contains(".")) className else RecyclerView::class.java.getPackage()!!.name + '.' + className
        }
    }
}