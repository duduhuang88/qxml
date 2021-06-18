package com.qxml.transform.generate.match.resolver.type

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.match.resolver.MatchResult
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo
import java.lang.StringBuilder

class FractionTypeResolver: AttrTypeResolver {
    override fun resolve(
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
    ): MatchResult? {
        if (attrInfoModel.isFraction()) {
            if (valueType == ValueType.SOURCE_STRING) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_FRACTION))
                stringBuilder.append(makeFractionByString(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.REFERENCE_STRING) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_FRACTION or Constants.FORMAT_REFERENCE or Constants.FORMAT_STRING))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                stringBuilder.append(makeFractionByStringId(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.REFERENCE_FRACTION) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_FRACTION or Constants.FORMAT_REFERENCE))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                stringBuilder.append(makeFractionById(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            }
        }
        return null
    }

    private fun makeFractionByString(value: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.floatValue = com.qxml.tools.SizeTool.string2Fraction($value));\n"
    }

    private fun makeFractionByStringId(value: String): String {
        return makeFractionByString("${Constants.GEN_PARAM_CONTEXT_NAME}.getString(value)")
    }

    private fun makeFractionById(valueId: String): String {
        return "getResources().getValue($valueId, ${Constants.GEN_FIELD_TYPED_VALUE_NAME}, true);\n"+"${Constants.GEN_FIELD_VALUE_INFO_NAME}.floatValue = ${Constants.GEN_FIELD_TYPED_VALUE_NAME}.getFraction(1f, 1f);\n"
    }
}