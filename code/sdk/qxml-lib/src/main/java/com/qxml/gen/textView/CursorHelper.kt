package com.qxml.gen.textView

import android.os.Build
import android.widget.TextView
import com.qxml.tools.ReflectUtils

object CursorHelper {

    private val mCursorDrawableResField = ReflectUtils.getDeclaredFieldOrNull(
        TextView::class.java,
        "mCursorDrawableRes"
    )

    @JvmStatic
    fun setCursorDrawable(textView: TextView, resId: Int) {
        if (Build.VERSION.SDK_INT >= 29) {
            textView.setTextCursorDrawable(resId)
        } else {
            mCursorDrawableResField?.set(textView, resId)
        }
    }

}