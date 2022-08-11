package com.qxml.transform.generate.match.resolver

import com.qxml.constant.Constants
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.GenResult
import com.qxml.transform.generate.ViewGenResultInfo
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo
import java.lang.StringBuilder

interface ValueResolver {

    fun resolve(viewTypeName: String, viewFieldName: String, layoutName: String, layoutType: String
                , attrFuncInfoModel: AttrFuncInfoModel, valueInfo: ValueInfo, attrInfoModel: AttrInfoModel
                , fieldInfo: FieldInfo, contextName: String, usedTempVarMap: HashMap<String, String>
    ): MatchResult
    fun type(): String

    fun makeResolverErrInfo(layoutName: String, layoutType: String, attrFuncInfoModel: AttrFuncInfoModel, valueInfo: ValueInfo) = MatchResult(MatchType.FAILED, "", ViewGenResultInfo("$layoutName $layoutType", GenResult.VALUE_MATCH_ERROR, "${attrFuncInfoModel.viewParamType}-${attrFuncInfoModel.attrName} the value(${valueInfo.sourceValue}) can't parse to ${type()}"))
    fun makeResolverSucInfo(result: String) = MatchResult(MatchType.SUCCESS_VALUE, result)
    fun makeResolverSucStateInfo(stringBuilder: StringBuilder, useBaseTypeValue: Boolean): MatchResult {
        val result = stringBuilder.toString()
        return MatchResult(MatchType.SUCCESS_ATTR_REFERENCE, result.substring(0, result.length - 1), null, useBaseTypeValue)
    }

    fun makeResolverSucStateInfo(stringBuilder: StringBuilder, lastBodyContent: String, useBaseTypeValue: Boolean): MatchResult {
        return MatchResult(MatchType.SUCCESS_ATTR_REFERENCE, stringBuilder.append(lastBodyContent.substring(0, lastBodyContent.length - 1)).toString(), null, useBaseTypeValue)
    }

    fun makeResolverNullInfo(stringBuilder: StringBuilder): MatchResult{
        val result = stringBuilder.toString()
        return MatchResult(MatchType.SUCCESS_NULL, result.substring(0, result.length - 1))
    }

    fun getAttrReferenceResolveState(contextName: String, valueInfo: ValueInfo): String {
        return "${contextName}.getTheme().resolveAttribute(${valueInfo.value}, ${Constants.GEN_FIELD_TYPED_VALUE_NAME}, true);\n"
    }

    fun callAttrIntReferenceMethod(contextName: String, attrId: String): String {
        return callAttrReferenceMethod(contextName, attrId, Constants.QXML_REFERENCE_RESOLVE_INT_METHOD_NAME)
    }

    fun callAttrBooleanReferenceMethod(contextName: String, attrId: String): String {
        return callAttrReferenceMethod(contextName, attrId, Constants.QXML_REFERENCE_RESOLVE_BOOLEAN_METHOD_NAME)
    }

    fun callAttrFloatReferenceMethod(contextName: String, attrId: String): String {
        return callAttrReferenceMethod(contextName, attrId, Constants.QXML_REFERENCE_RESOLVE_FLOAT_METHOD_NAME)
    }

    fun callAttrStringReferenceMethod(contextName: String, attrId: String): String {
        return callAttrReferenceMethod(contextName, attrId, Constants.QXML_REFERENCE_RESOLVE_STRING_METHOD_NAME)
    }

    private fun callAttrReferenceMethod(contextName: String, attrId: String, methodName: String): String {
        return "${Constants.REFERENCE_ATTR_RESOLVER}.${methodName}(${contextName}, ${Constants.GEN_FIELD_TYPED_VALUE_NAME}, ${attrId})"
    }

    fun makeParamInit(attrFuncInfoModel: AttrFuncInfoModel, value: String, usedTempVarMap: HashMap<String, String>): String {
        /*if (usedTempVarMap[attrFuncInfoModel.valueParamName] == attrFuncInfoModel.valueParamType) {
            return "${attrFuncInfoModel.valueParamName} = $value;"
        }
        usedTempVarMap[attrFuncInfoModel.valueParamName] = attrFuncInfoModel.valueParamType*/
        return "${attrFuncInfoModel.valueParamType} ${attrFuncInfoModel.valueParamName} = $value;"
    }

    fun replaceToParamName(funcBody: String, attrFuncInfoModel: AttrFuncInfoModel): String {
        return funcBody.replace(Constants.BASE_TYPE_PARAM_NAME_TEMP, attrFuncInfoModel.valueParamName)
    }

    fun replaceToBaseType(funcBody: String, baseTypeValue: String): String {
        return funcBody.replace(Constants.BASE_TYPE_PARAM_NAME_TEMP, baseTypeValue)
    }

    fun String.toParamNameFuncBody(attrFuncInfoModel: AttrFuncInfoModel): String {
        return replaceToParamName(this, attrFuncInfoModel)
    }

    fun String.toBaseTypeFuncBody(baseTypeValue: String): String {
        return replaceToBaseType(this, baseTypeValue)
    }
}

data class MatchResult(val matchType: MatchType, val result: String, val ignoreInfo: ViewGenResultInfo? = null, val useBaseTypeValue: Boolean = true)

enum class MatchType {
    FAILED, SUCCESS_VALUE, SUCCESS_TYPE_VALUE, SUCCESS_ATTR_REFERENCE, SUCCESS_NULL
}
