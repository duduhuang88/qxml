package com.qxml.transform.generate.value

import com.qxml.constant.ValueType
import com.qxml.transform.AttrInfoModel
import java.lang.StringBuilder

private const val COLOR = "color"
private const val ID = "id"
private const val STRING = "string"
private const val DRAWABLE = "drawable"
private const val MIPMAP = "mipmap"
private const val DIMEN = "dimen"
private const val STYLE = "style"
private const val LAYOUT = "layout"
private const val BOOL = "bool"
private const val INTEGER = "integer"
private const val FRACTION = "fraction"
private const val ANIM = "anim"
private const val ANIMATOR = "animator"
private const val ARRAY = "array"
private const val XML = "xml"

/**
 * value 解析
 */
class ValueParser(private val packageName: String, private val attrInfoMap: HashMap<String, AttrInfoModel>) {

    private val rId = "$packageName.R.id."

    private val rIds = arrayOf("$packageName.R.id.", "android.R.id.")
    private val rColors = arrayOf("$packageName.R.color.", "android.R.color.")
    private val rStrings = arrayOf("$packageName.R.string.", "android.R.string.")
    private val rStyles = arrayOf("$packageName.R.style.", "android.R.style.")
    private val rDimens = arrayOf("$packageName.R.dimen.", "android.R.dimen.")
    private val rLayouts = arrayOf("$packageName.R.layout.", "android.R.layout.")
    private val rDrawables = arrayOf("$packageName.R.drawable.", "android.R.drawable.")
    private val rMipmaps = arrayOf("$packageName.R.mipmap.", "android.R.mipmap.")
    private val rBooleans = arrayOf("$packageName.R.bool.", "android.R.bool.")
    private val rIntegers = arrayOf("$packageName.R.integer.", "android.R.integer.")
    private val rFractions = arrayOf("$packageName.R.fraction.", "android.R.fraction.")
    private val rAnims = arrayOf("$packageName.R.anim.", "android.R.anim.")
    private val rAnimators = arrayOf("$packageName.R.animator.", "android.R.animator.")
    private val rArrays = arrayOf("$packageName.R.array.", "android.R.array.")
    private val rXmls = arrayOf("$packageName.R.xml.", "android.R.xml.")


    fun getValueInfo(attrName: String, attrValue: String): ValueInfo? {
        var valueWithoutPackageName = ""
        var value: String = attrValue
        var valueType = ValueType.SOURCE_STRING
        var referenceSourceValue = ""
        if (attrValue == "@null") {
            return ValueInfo(ValueType.NULL, value, valueWithoutPackageName, attrValue)
        }
        if (attrValue.startsWith("@")) {
            val indexSplit = attrValue.indexOf("/")
            var type = attrValue.substring(1, indexSplit)
            var valueSource = attrValue.substring(indexSplit+1)
            var typeIsAndroid = false
            if (type.startsWith("android:")) {
                type = type.substring(8)//android:
                typeIsAndroid = true
            }
            if (valueSource.startsWith("android:")) {
                valueSource = valueSource.substring(8)//android:
                typeIsAndroid = true
            }
            referenceSourceValue = valueSource
            if (type.startsWith("+")) {
                value = "$rId${valueSource.replace(".", "_")}"
                valueType = ValueType.REFERENCE_ID
            } else if (type.startsWith("*")) {//暂不支持私有引用
                return null
            } else {
                val preFixIndex: Int
                val finalType = if (typeIsAndroid) {
                    preFixIndex = 1
                    type
                } else {
                    preFixIndex = 0
                    type
                }
                when(finalType) {
                    COLOR -> {
                        value = "${rColors[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_COLOR
                    }
                    ID -> {
                        value = "${rIds[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_ID
                    }
                    DIMEN -> {
                        value = "${rDimens[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_DIMEN
                    }
                    STRING -> {
                        value = "${rStrings[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_STRING
                    }
                    STYLE -> {
                        value = "${rStyles[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_STYLE
                    }
                    LAYOUT -> {
                        value = "${rLayouts[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_LAYOUT
                    }
                    MIPMAP -> {
                        value = "${rMipmaps[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_DRAWABLE
                    }
                    DRAWABLE -> {
                        value = "${rDrawables[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_DRAWABLE
                    }
                    BOOL -> {
                        value = "${rBooleans[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_BOOLEAN
                    }
                    INTEGER -> {
                        value = "${rIntegers[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_INTEGER
                    }
                    FRACTION -> {
                        value = "${rFractions[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_FRACTION
                    }
                    ANIM -> {
                        value = "${rAnims[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_ANIM
                    }
                    ANIMATOR -> {
                        value = "${rAnimators[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_ANIMATOR
                    }
                    ARRAY -> {
                        value = "${rArrays[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_ARRAY
                    }
                    XML -> {
                        value = "${rXmls[preFixIndex]}${valueSource.replace(".", "_")}"
                        valueType = ValueType.REFERENCE_XML
                    }
                    else -> {}
                }
            }
        } else if (attrValue.startsWith("?")) {
            if (attrValue.contains("/")) {
                if (attrValue.startsWith("?android:attr/", true)) {
                    val valueSource = attrValue.substring(14)
                    value = "android.R.attr.$valueSource"
                    referenceSourceValue = attrValue.substring(14)
                } else {
                    referenceSourceValue = attrValue.substring(6)
                    value = "$packageName.R.attr.$referenceSourceValue"
                }
            } else {
                referenceSourceValue = attrValue.substring(1)
                if (attrValue.contains("android:")) {
                    val valueSource = attrValue.substring(9)
                    value = "android.R.attr.$valueSource"
                } else {
                    value = "$packageName.R.attr.$referenceSourceValue"
                }
            }
            valueType = ValueType.REFERENCE_ATTR
            valueWithoutPackageName = value.substring(value.indexOf(".R.") + 1)
            return ValueInfo(valueType, value, valueWithoutPackageName, attrValue, true, referenceSourceValue)
        } else {
            attrInfoMap[attrName]?.also { attrInfoModel ->
                if (attrInfoModel.isEnum() || attrInfoModel.isFlag()) {
                    val split = value.split("|")
                    val values = mutableListOf<Long>()
                    var isEnumOrFlag = false
                    split.forEach { str ->
                        if (str.isNotEmpty()) {
                            val mapValue = attrInfoModel.attrEnumOrFlagMap[str]
                            if (mapValue != null) {
                                isEnumOrFlag = true
                                values.add(mapValue)
                            } else {
                                return ValueInfo(valueType, value, valueWithoutPackageName, attrValue)
                            }
                        }
                    }
                    if (isEnumOrFlag) {
                        val stringBuilder = StringBuilder()
                        values.forEachIndexed { index, l ->
                            stringBuilder.append("$l")
                            if (index != values.size - 1) {
                                stringBuilder.append(" | ")
                            }
                        }
                        valueType = if (attrInfoModel.isEnum()) ValueType.ENUM else ValueType.FLAG
                        value = stringBuilder.toString()
                    }
                }
            }
        }
        if (value.contains(".R.", false)) {
            valueWithoutPackageName = value.substring(value.indexOf(".R.") + 1)
        }
        return ValueInfo(valueType, value, valueWithoutPackageName, attrValue, false, referenceSourceValue)
    }

}

/**
 * attr 值的信息
 */
data class ValueInfo(val valueType: ValueType
                     , val value: String    //补全后的值 eg: R.layout.xxx
                     , val valueWithoutPackageName: String
                     , val sourceValue: String  //源值
                     , val isAttrReference: Boolean = false
                     , val referenceSourceValue: String = "" //为引用时，去除@XX/ 或 ？ 的值
)