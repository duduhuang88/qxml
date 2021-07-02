package com.qxml.transform.generate.match.resolver

import com.qxml.constant.Constants
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.value.ValueInfo
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.generate.model.FieldInfo
import java.lang.StringBuilder

class BooleanTypeValueResolver: ValueResolver {
    override fun resolve(
        viewTypeName: String,
        viewFieldName: String,
        layoutName: String,
        layoutType: String,
        attrFuncInfoModel: AttrFuncInfoModel,
        valueInfo: ValueInfo,
        attrInfoModel: AttrInfoModel,
        fieldInfo: FieldInfo,
        contextName: String
    ): MatchResult {
        val valueType = valueInfo.valueType
        val value = valueInfo.value
        val funcBody = attrFuncInfoModel.funcBodyContent.substring(1).replace(Constants.PARAM_NAME_TEMP, viewFieldName)

        val stringBuilder = StringBuilder()
        stringBuilder.append(attrFuncInfoModel.getMethodContentHead(viewFieldName))

        if (attrInfoModel.isBoolean()) {
            if (valueInfo.isAttrReference) {
                stringBuilder.append(getAttrReferenceResolveState(contextName, valueInfo))
                stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${callAttrBooleanReferenceMethod(contextName, value)};\n")
                return makeResolverSucStateInfo(stringBuilder, funcBody)
            } else {
                if (valueType == ValueType.REFERENCE_STRING) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = \"true\".equals(${Constants.GEN_PARAM_CONTEXT_NAME}.getString($value));\n")
                    return makeResolverSucStateInfo(stringBuilder, funcBody)
                } else if (valueType == ValueType.SOURCE_STRING) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${value.equals("true", true)};\n")
                    return makeResolverSucStateInfo(stringBuilder, funcBody)
                } else if (valueType == ValueType.REFERENCE_BOOLEAN) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${Constants.GEN_FIELD_RESOURCE}.getBoolean($value);\n")
                    return makeResolverSucStateInfo(stringBuilder, funcBody)
                } else if (valueType == ValueType.NULL) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = true;\n")
                    return makeResolverSucStateInfo(stringBuilder, funcBody)
                }
            }
        }
        return makeResolverErrInfo(layoutName, layoutType, attrFuncInfoModel, valueInfo)
    }

    override fun type(): String = "boolean"
}