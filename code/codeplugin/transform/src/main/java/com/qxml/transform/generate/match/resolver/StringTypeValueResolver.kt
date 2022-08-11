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
        contextName: String,
        usedTempVarMap: HashMap<String, String>
    ): MatchResult {
        val valueType = valueInfo.valueType
        val value = valueInfo.value
        val funcBody = attrFuncInfoModel.funcBodyContent.substring(3).replace(Constants.VIEW_PARAM_NAME_TEMP, viewFieldName)

        val stringBuilder = StringBuilder()

        if (valueInfo.isAttrReference) {
            stringBuilder.append(getAttrReferenceResolveState(contextName, valueInfo))
            stringBuilder.append(makeParamInit(attrFuncInfoModel, callAttrStringReferenceMethod(contextName, value), usedTempVarMap))
            return makeResolverSucStateInfo(stringBuilder, funcBody.toParamNameFuncBody(attrFuncInfoModel), false)
        } else {
            if (valueType == ValueType.NULL) {
                //stringBuilder.append(makeParamInit(attrFuncInfoModel, "\"\"", usedTempVarMap))
                return makeResolverSucStateInfo(stringBuilder, funcBody.toBaseTypeFuncBody("\"\""), true)
            }
            if (attrInfoModel.isReference()) {//necessary?
                if (valueType == ValueType.REFERENCE_STRING) {
                    stringBuilder.append(makeParamInit(attrFuncInfoModel, "${Constants.GEN_PARAM_CONTEXT_NAME}.getString($value)", usedTempVarMap))
                    return makeResolverSucStateInfo(stringBuilder, funcBody.toParamNameFuncBody(attrFuncInfoModel), false)
                }
            } else if (attrInfoModel.isString()) {
                if (valueType == ValueType.REFERENCE_STRING) {
                    stringBuilder.append(makeParamInit(attrFuncInfoModel, "${Constants.GEN_PARAM_CONTEXT_NAME}.getString($value)", usedTempVarMap))
                    return makeResolverSucStateInfo(stringBuilder, funcBody.toParamNameFuncBody(attrFuncInfoModel), false)
                } else {
                    //stringBuilder.append(makeParamInit(attrFuncInfoModel, "\"${valueInfo.sourceValue}\"", usedTempVarMap))
                    return makeResolverSucStateInfo(stringBuilder, funcBody.toBaseTypeFuncBody("\"${valueInfo.sourceValue}\""), true)
                }
            }
        }

        return makeResolverErrInfo(layoutName, layoutType, attrFuncInfoModel, valueInfo)
    }

    override fun type(): String = "string"
}