package com.qxml.tools

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import com.qxml.AndroidRS
import com.qxml.RS
import com.qxml.constant.ValueType
import com.qxml.value.ValueInfo

class DrawableTools {

    companion object {

        private val sLock = Any()
        private var sTempValue: TypedValue? = null

        /**
         * 获取引用的drawable，可以是drawable mipmap color
         */
        @JvmStatic
        fun getReferenceDrawable(context: Context, resources: Resources, valueInfo: ValueInfo): Drawable? {
            return when (valueInfo.referenceType) {
                ValueType.REFERENCE_DRAWABLE -> {
                    return getDrawable(context, resources, valueInfo.resourceId)
                }
                ValueType.REFERENCE_COLOR -> {
                    ColorDrawable(valueInfo.colorValue)
                }
                else -> null
            }
        }

        @JvmStatic
        fun getDrawable(context: Context, resources: Resources, resourceId: Int): Drawable? {
            if (Build.VERSION.SDK_INT >= 21) {
                return resources.getDrawable(resourceId, context.theme)
            } else if (Build.VERSION.SDK_INT >= 16) {
                return context.resources.getDrawable(resourceId)
            }
            var resolvedId: Int
            synchronized(sLock) {
                if (sTempValue == null) {
                    sTempValue = TypedValue()
                }
                context.resources.getValue(resourceId, sTempValue, true)
                resolvedId = sTempValue!!.resourceId
            }

            return context.resources.getDrawable(resolvedId)
        }

    }

}