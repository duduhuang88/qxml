package com.qxml.value

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.DrawableTools

class ValueInfo {

    @JvmField
    var type: Int = Constants.FORMAT_UN_KNOW
    @JvmField
    var stringValue: String? = null
    @JvmField
    var floatValue: Float = 0f
    @JvmField
    var resourceId: Int = 0
    @JvmField
    var intValue: Int = 0
    @JvmField
    var longValue: Long = 0
    @JvmField
    var colorValue: Int = 0
    @JvmField
    var booleanValue: Boolean = false

    @JvmField
    var referenceType: ValueType = ValueType.SOURCE_STRING

    fun clear() {
        stringValue = null
        type = 0
        floatValue = 0f
        resourceId = 0
        intValue = 0
        colorValue = 0
        referenceType = ValueType.SOURCE_STRING
    }

    fun isBoolean() = type and Constants.FORMAT_BOOLEAN != Constants.FORMAT_UN_KNOW
    fun isString() = type and Constants.FORMAT_STRING != Constants.FORMAT_UN_KNOW
    fun isReference() = type and Constants.FORMAT_REFERENCE != Constants.FORMAT_UN_KNOW
    fun isColor() = type and Constants.FORMAT_COLOR != Constants.FORMAT_UN_KNOW
    fun isInteger() = type and Constants.FORMAT_INTEGER != Constants.FORMAT_UN_KNOW
    fun isDimension() = type and Constants.FORMAT_DIMENSION != Constants.FORMAT_UN_KNOW
    fun isEnum() = type and Constants.FORMAT_ENUM != Constants.FORMAT_UN_KNOW
    fun isFlag() = type and Constants.FORMAT_FLAGS != Constants.FORMAT_UN_KNOW
    fun isFloat() = type and Constants.FORMAT_FLOAT != Constants.FORMAT_UN_KNOW
    fun isFraction() = type and Constants.FORMAT_FRACTION != Constants.FORMAT_UN_KNOW
    fun isNull() = type and Constants.FORMAT_NULL != Constants.FORMAT_UN_KNOW

    /*override fun toString(): String {
        return "ValueInfo(type=$type, stringValue=$stringValue, floatValue=$floatValue, resourceId=$resourceId, intValue=$intValue, longValue=$longValue, colorValue=$colorValue, booleanValue=$booleanValue, referenceType=$referenceType)"
    }*/

    fun getColorStateList(context: Context): ColorStateList? {
        if (isColor()) {
            return ColorStateList.valueOf(colorValue)
        }
        return if (Build.VERSION.SDK_INT >= 23) context.getColorStateList(resourceId) else context.resources
            .getColorStateList(resourceId)
    }

    fun getReferenceDrawable(context: Context, resources: Resources): Drawable? {
        return DrawableTools.getReferenceDrawable(context, resources, this)
    }
}