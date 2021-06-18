package com.qxml.transform.generate.match.resolver.type

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.match.resolver.MatchResult
import com.qxml.transform.generate.match.resolver.MatchType
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo

interface AttrTypeResolver {

    fun resolve(
        layoutName: String,
        layoutType: String,
        attrFuncInfoModel: AttrFuncInfoModel,
        valueInfo: ValueInfo,
        attrInfoModel: AttrInfoModel,
        valueType: ValueType,
        value: String,
        lastFuncBodyContent: String,
        fieldInfo: FieldInfo,
        contextName: String,
        stringBuilder: StringBuilder
    ): MatchResult?

    fun makeValueInfoType(type: Int): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.type = ${Constants.GEN_FIELD_VALUE_INFO_NAME}.type | $type;\n"
    }

    fun makeReferenceType(valueType: ValueType): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.referenceType = com.qxml.constant.ValueType.$valueType;\n"
    }

    fun makeResourceId(value: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.resourceId = $value;\n"
    }

    fun makeColorById(colorId: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.colorValue = ${Constants.GEN_FIELD_RESOURCE}.getColor($colorId);\n"
    }

    fun makeDimenById(dimenId: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.floatValue = ${Constants.GEN_FIELD_RESOURCE}.getDimension($dimenId);\n"
    }

    fun makeBooleanById(valueId: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.booleanValue = ${Constants.GEN_FIELD_RESOURCE}.getBoolean($valueId);\n"
    }

    fun makeStringById(valueId: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.stringValue = ${Constants.GEN_PARAM_CONTEXT_NAME}.getString($valueId);\n"
    }

    fun makeStringBySource(value: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.stringValue = \"$value\";\n"
    }

    fun makeFloatByString(value: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.floatValue = $value;\n"
    }

    fun makeIntFromFloat(): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.intValue = (int)(${Constants.GEN_FIELD_VALUE_INFO_NAME}.floatValue);\n"
    }

    fun makeFloatByDimenId(valueId: String): String {
        return "${Constants.GEN_FIELD_RESOURCE}.getValue($valueId, ${Constants.GEN_FIELD_TYPED_VALUE_NAME}, true);\n"+"${Constants.GEN_FIELD_VALUE_INFO_NAME}.floatValue = ${Constants.GEN_FIELD_TYPED_VALUE_NAME}.getFloat();\n"
    }

    fun makeFloatByStringId(valueId: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.floatValue = Float.parseFloat(${Constants.GEN_PARAM_CONTEXT_NAME}.getString($valueId));\n"
    }

    fun makeBooleanByStringId(valueId: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.booleanValue = ${Constants.GEN_PARAM_CONTEXT_NAME}.getString($valueId).equals(\"true\");\n"
    }

    fun makeBooleanByString(value: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.booleanValue = ${value.equals("true", true)};\n"
    }

    fun makeColorByStringId(stringId: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.colorValue = android.graphics.Color.parseColor(${Constants.GEN_PARAM_CONTEXT_NAME}.getString($stringId));\n"
    }

    fun makeColorByString(string: String): String {
        var finalColorString = ""
        if (string.startsWith("#")) {
            finalColorString = string.substring(1)
            if (finalColorString.length == 3) { //#fff
                finalColorString = java.lang.StringBuilder().append(finalColorString[0]).append(finalColorString[0])
                    .append(finalColorString[1]).append(finalColorString[1])
                    .append(finalColorString[2]).append(finalColorString[2]).toString()
            } else if (finalColorString.length == 4) { //#ffff
                finalColorString = java.lang.StringBuilder().append(finalColorString[0]).append(finalColorString[0])
                    .append(finalColorString[1]).append(finalColorString[1])
                    .append(finalColorString[2]).append(finalColorString[2])
                    .append(finalColorString[3]).append(finalColorString[3]).toString()
            }
            try {
                var color = finalColorString.toLong(16)
                if (finalColorString.length == 6) {
                    // Set the alpha value
                    color = color or -0x1000000
                } else if (finalColorString.length != 8) {
                    color = 0
                }
                val colorResult = color.toInt()
                return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.colorValue = ${colorResult};\n"
            } catch (e: Exception) {
            }
        }
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.colorValue = 0;\n"
    }

    fun makeIntegerById(valueId: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.intValue = ${Constants.GEN_FIELD_RESOURCE}.getInteger($valueId);\n"
    }

    fun makeIntegerByStringId(valueId: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.intValue = java.lang.Integer.parseInt(${Constants.GEN_PARAM_CONTEXT_NAME}.getString($valueId));\n"
    }

    fun makeIntegerByString(value: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.intValue = $value;\n"
    }

    fun makeResolverSucStateInfo(stringBuilder: StringBuilder, lastFuncBodyContent: String): MatchResult{
        return MatchResult(
            MatchType.SUCCESS_TYPE_VALUE,
            stringBuilder.append(lastFuncBodyContent).toString()
        )
    }

}