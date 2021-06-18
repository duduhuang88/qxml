package com.qxml.value

import android.content.Context
import android.util.TypedValue
import com.qxml.constant.Constants

class ReferenceAttrResolver {

    companion object {
        @JvmStatic
        fun resolveBoolean(context: Context, typedValue: TypedValue, attrId: Int): Boolean {
            val type = typedValue.type
            if (type == TypedValue.TYPE_INT_BOOLEAN) {
                return typedValue.data != 0
            }
            val typedArray = context.obtainStyledAttributes(intArrayOf(attrId))
            val res = when (type) {
                TypedValue.TYPE_FLOAT -> {
                    typedArray.getFloat(0, 0f) == 0f
                }
                TypedValue.TYPE_REFERENCE -> {
                    val resId = typedArray.getResourceId(0, 0)
                    if (resId == 0) {
                        false
                    } else {
                        context.resources.getBoolean(resId)
                    }
                }
                TypedValue.TYPE_STRING -> {
                    val string = typedArray.getString(0)
                    if (string == null) {
                        false
                    } else {
                        "true".equals(string, true)
                    }
                }
                else -> {
                    false
                }
            }
            typedArray.recycle()
            return res
        }

        @JvmStatic
        fun resolveInt(context: Context, typedValue: TypedValue, attrId: Int): Int {
            val type = typedValue.type
            if (type >= TypedValue.TYPE_FIRST_INT && type <= TypedValue.TYPE_LAST_INT) {
                return typedValue.data
            }
            val typedArray = context.obtainStyledAttributes(intArrayOf(attrId))
            val res = when (type) {
                TypedValue.TYPE_DIMENSION -> {
                    typedArray.getDimensionPixelSize(0, 0)
                }
                TypedValue.TYPE_FLOAT -> {
                    typedArray.getFloat(0, 0f).toInt()
                }
                TypedValue.TYPE_FRACTION -> {
                    typedArray.getFraction(0, 0, 0, 0f).toInt() //?
                }
                TypedValue.TYPE_REFERENCE -> {
                    typedArray.getResourceId(0, 0)
                }
                TypedValue.TYPE_STRING -> {
                    typedArray.getString(0)?.toIntOrNull() ?: 0
                }
                else -> {
                    if (type >= TypedValue.TYPE_FIRST_INT && type <= TypedValue.TYPE_LAST_INT) {
                        typedArray.getInt(0, 0)
                    } else {
                        0
                    }
                }
            }
            typedArray.recycle()
            return res
        }

        @JvmStatic
        fun resolveString(context: Context, typedValue: TypedValue, attrId: Int): String {
            if (typedValue.resourceId == 0) {
                if (typedValue.string != null) {
                    return typedValue.string.toString()
                }
            }
            val typedArray = context.obtainStyledAttributes(intArrayOf(attrId))
            val res = typedArray.getString(0) ?: ""
            typedArray.recycle()
            return res
        }

        @JvmStatic
        fun resolveFloat(context: Context, typedValue: TypedValue, attrId: Int): Float {
            val type = typedValue.type
            if (type == TypedValue.TYPE_DIMENSION) {
                return typedValue.getDimension(context.resources.displayMetrics)
            } else if (type == TypedValue.TYPE_FLOAT) {
                return typedValue.float
            }
            val typedArray = context.obtainStyledAttributes(intArrayOf(attrId))
            val res = if (type == TypedValue.TYPE_STRING) {
                typedArray.getString(0)?.toFloatOrNull() ?: 0f
            } else if (type >= TypedValue.TYPE_FIRST_INT && type <= TypedValue.TYPE_LAST_INT) {
                typedArray.getInt(0, 0).toFloat()
            } else if (type == TypedValue.TYPE_FRACTION) {
                typedArray.getFraction(0, 0, 0, 0f) //?
            } else {
                0f
            }
            typedArray.recycle()
            return res
        }

        @JvmStatic
        fun resolveValueInfo(context: Context, typedValue: TypedValue, valueInfo: ValueInfo, attrId: Int) {
            if (typedValue.resourceId != 0) {
                //没有确定引用的类型
                valueInfo.type = valueInfo.type or Constants.FORMAT_REFERENCE
                valueInfo.resourceId = typedValue.resourceId
                return
            }
            val typedArray = context.obtainStyledAttributes(intArrayOf(attrId))
            val type = typedValue.type
            if (type == TypedValue.TYPE_REFERENCE) {
                //没有确定引用的类型
                valueInfo.type = valueInfo.type or Constants.FORMAT_REFERENCE
                valueInfo.resourceId = typedArray.getResourceId(0, 0)
            } else if (type >= TypedValue.TYPE_INT_DEC && type <= TypedValue.TYPE_INT_HEX) {
                valueInfo.type = valueInfo.type or Constants.FORMAT_INTEGER
                valueInfo.intValue = typedArray.getInt(0, 0)
            } else if (type == TypedValue.TYPE_STRING) {
                valueInfo.type = valueInfo.type or Constants.FORMAT_STRING
                valueInfo.stringValue = typedArray.getString(0) ?: ""
            } else if ((type >= TypedValue.TYPE_FIRST_COLOR_INT && type <= TypedValue.TYPE_LAST_COLOR_INT)) {
                valueInfo.type = valueInfo.type or Constants.FORMAT_COLOR
                valueInfo.colorValue = typedArray.getColor(0, 0)
            } else if (type == TypedValue.TYPE_DIMENSION) {
                valueInfo.type = valueInfo.type or Constants.FORMAT_DIMENSION
                valueInfo.floatValue = typedArray.getDimension(0, 0f)
            } else if (type == TypedValue.TYPE_INT_BOOLEAN) {
                valueInfo.type = valueInfo.type or Constants.FORMAT_BOOLEAN
                valueInfo.booleanValue = typedArray.getBoolean(0, false)
            } else if (type == TypedValue.TYPE_NULL) {
                valueInfo.type = valueInfo.type or Constants.FORMAT_NULL
            }
            typedArray.recycle()
        }
    }

}