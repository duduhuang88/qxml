package com.qxml.qxml_androidx.gen.tabLayout

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import com.google.android.material.internal.ViewUtils
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.qxml.tools.ReflectUtils
import java.lang.reflect.Field

object TabLayoutHelper {

    private val contentInsetStartField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "contentInsetStart")
    private val tabBackgroundResIdField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "tabBackgroundResId")
    private val requestedTabMinWidthField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "requestedTabMinWidth")
    private val requestedTabMaxWidthField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "requestedTabMaxWidth")

    private val tabPaddingStartField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "tabPaddingStart")
    private val tabPaddingTopField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "tabPaddingTop")
    private val tabPaddingEndField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "tabPaddingEnd")
    private val tabPaddingBottomField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "tabPaddingBottom")

    private val tabTextSizeField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "tabTextSize")
    private val tabIconTintModeField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "tabIconTintMode")

    private val tabIndicatorAnimationDurationField = ReflectUtils.getDeclaredFieldOrNull(TabLayout::class.java, "tabIndicatorAnimationDuration")

    private val tabItemTextField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabItem::class.java, "text")
    private val tabItemIconField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabItem::class.java, "icon")
    private val tabItemCustomLayoutField: Field? = ReflectUtils.getDeclaredFieldOrNull(TabItem::class.java, "customLayout")

    @JvmStatic
    fun setContentInsetStart(tabLayout: TabLayout, contentInsetStart: Int) {
        try {
            contentInsetStartField?.setInt(tabLayout, contentInsetStart)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabBackgroundResId(tabLayout: TabLayout, tabBackgroundResId: Int) {
        try {
            tabBackgroundResIdField?.setInt(tabLayout, tabBackgroundResId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabItemText(tabItem: TabItem, text: String) {
        try {
            tabItemTextField?.set(tabItem, text)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabItemIcon(tabItem: TabItem, icon: Drawable?) {
        try {
            tabItemIconField?.set(tabItem, icon)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabItemCustomLayout(tabItem: TabItem, customLayout: Int) {
        try {
            tabItemCustomLayoutField?.setInt(tabItem, customLayout)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setRequestedTabMinWidthField(tabLayout: TabLayout, requestedTabMinWidth: Int) {
        try {
            requestedTabMinWidthField?.setInt(tabLayout, requestedTabMinWidth)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setRequestedTabMaxWidthField(tabLayout: TabLayout, requestedTabMaxWidth: Int) {
        try {
            requestedTabMaxWidthField?.setInt(tabLayout, requestedTabMaxWidth)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabPaddingStartField(tabLayout: TabLayout, tabPaddingStart: Int) {
        try {
            tabPaddingStartField?.setInt(tabLayout, tabPaddingStart)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabPaddingTopField(tabLayout: TabLayout, tabPaddingTop: Int) {
        try {
            tabPaddingTopField?.setInt(tabLayout, tabPaddingTop)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabPaddingEndField(tabLayout: TabLayout, tabPaddingEnd: Int) {
        try {
            tabPaddingEndField?.setInt(tabLayout, tabPaddingEnd)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabPaddingBottomField(tabLayout: TabLayout, tabPaddingBottom: Int) {
        try {
            tabPaddingBottomField?.setInt(tabLayout, tabPaddingBottom)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabTextSizeField(tabLayout: TabLayout, tabTextSize: Float) {
        try {
            tabTextSizeField?.setFloat(tabLayout, tabTextSize)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("RestrictedApi")
    @JvmStatic
    fun setTabIconTintModeFieldField(tabLayout: TabLayout, tabIconTintMode: Int) {
        try {
            ViewUtils.parseTintMode(tabIconTintMode, null)?.also {
                tabIconTintModeField?.set(tabLayout, it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun setTabIndicatorAnimationDurationField(tabLayout: TabLayout, tabIndicatorAnimationDuration: Int) {
        try {
            tabIndicatorAnimationDurationField?.setInt(tabLayout, tabIndicatorAnimationDuration)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}