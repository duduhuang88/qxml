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
        contextName: String,
        usedTempVarMap: HashMap<String, String>,
        shouldWrapScope: Boolean
    ): MatchResult {
        val valueType = valueInfo.valueType
        val value = valueInfo.value
        val funcBody = attrFuncInfoModel.funcBodyContent.substring(1).replace(Constants.VIEW_PARAM_NAME_TEMP, viewFieldName)

        val stringBuilder = StringBuilder()

        if (attrInfoModel.isBoolean()) {
            if (valueInfo.isAttrReference) {
                stringBuilder.append(getAttrReferenceResolveState(contextName, valueInfo))
                stringBuilder.append(makeParamInit(attrFuncInfoModel, callAttrBooleanReferenceMethod(contextName, value), usedTempVarMap))
                return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
            } else {
                if (valueType == ValueType.REFERENCE_STRING) {
                    stringBuilder.append(makeParamInit(attrFuncInfoModel, "\"true\".equals(${Constants.GEN_PARAM_CONTEXT_NAME}.getString($value))", usedTempVarMap))
                    return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                } else if (valueType == ValueType.SOURCE_STRING) {
                    stringBuilder.append(makeParamInit(attrFuncInfoModel, "${value.equals("true", true)}", usedTempVarMap))
                    return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                } else if (valueType == ValueType.REFERENCE_BOOLEAN) {
                    stringBuilder.append(makeParamInit(attrFuncInfoModel, "${Constants.GEN_FIELD_RESOURCE}.getBoolean($value)", usedTempVarMap))
                    return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                } else if (valueType == ValueType.NULL) {
                    stringBuilder.append(makeParamInit(attrFuncInfoModel, "true", usedTempVarMap))
                    return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                }
            }
        }
        return makeResolverErrInfo(layoutName, layoutType, attrFuncInfoModel, valueInfo)
    }

    override fun type(): String = "boolean"
}