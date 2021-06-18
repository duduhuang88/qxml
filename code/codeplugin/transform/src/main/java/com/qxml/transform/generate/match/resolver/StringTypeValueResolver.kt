package com.qxml.transform.generate.match.resolver

import com.qxml.constant.Constants
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.value.ValueInfo
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.generate.model.FieldInfo
import java.lang.StringBuilder

class StringTypeValueResolver: ValueResolver {
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
        val funcBody = attrFuncInfoModel.funcBodyContent.substring(1)

        val stringBuilder = StringBuilder()
        stringBuilder.append(attrFuncInfoModel.getMethodContentHead(viewFieldName))

        if (valueInfo.isAttrReference) {
            stringBuilder.append(getAttrReferenceResolveState(contextName, valueInfo))
            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${callAttrStringReferenceMethod(contextName, value)};\n")
            return makeResolverSucStateInfo(stringBuilder, funcBody)
        } else {
            if (valueType == ValueType.NULL) {
                stringBuilder.append("${attrFuncInfoModel.valueParamName} = \"\";\n")
                return makeResolverSucStateInfo(stringBuilder, funcBody)
            }
            if (attrInfoModel.isReference()) {//necessary?
                if (valueType == ValueType.REFERENCE_STRING) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${Constants.GEN_PARAM_CONTEXT_NAME}.getString($value);\n")
                    return makeResolverSucStateInfo(stringBuilder, funcBody)
                }
            } else if (attrInfoModel.isString()) {
                if (valueType == ValueType.REFERENCE_STRING) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${Constants.GEN_PARAM_CONTEXT_NAME}.getString($value);\n")
                    return makeResolverSucStateInfo(stringBuilder, funcBody)
                } else {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = \"${valueInfo.sourceValue}\";\n")
                    return makeResolverSucStateInfo(stringBuilder, funcBody)
                }
            }
        }

        return makeResolverErrInfo(layoutName, layoutType, attrFuncInfoModel, valueInfo)
    }

    override fun type(): String = "string"
}