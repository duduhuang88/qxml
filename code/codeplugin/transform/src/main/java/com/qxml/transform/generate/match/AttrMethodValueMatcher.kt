package com.qxml.transform.generate.match

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.GenResult
import com.qxml.transform.generate.ViewGenResultInfo
import com.qxml.transform.generate.match.resolver.*
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo
import com.qxml.transform.generate.value.ValueParser


class AttrMethodValueMatcher(private val packageName: String, private val attrInfoMap: HashMap<String, AttrInfoModel>) {

    private val typeMap by lazy { hashMapOf<String, ParamType>().apply {
        put("int", ParamType.INT)
        put("boolean", ParamType.BOOLEAN)
        put("float", ParamType.FLOAT)
        put("java.lang.String", ParamType.STRING)
        put(Constants.QXML_VALUE_INFO_CLASS_NAME, ParamType.VALUE_INFO)
    } }

    private val valueParser = ValueParser(packageName, attrInfoMap)

    private val intTypeValueResolver = IntTypeValueResolver()
    private val floatTypeValueResolver = FloatTypeValueResolver()
    private val booleanTypeValueResolver = BooleanTypeValueResolver()
    private val stringTypeValueResolver = StringTypeValueResolver()
    private val typedValueTypeResolver = ValueInfoTypeResolver()

    /**
     * 解析并匹配view的属性
     */
    fun match(viewTypeName: String, viewFieldName: String, layoutName: String, layoutType: String
              , attrFuncInfoModel: AttrFuncInfoModel, attrValue: String, fieldInfo: FieldInfo
              , contextName: String, usedReferenceRMap: HashMap<String, String>
              , idMap: Map<String, Int>): MatchResult {

        val paramType = getMethodParamType(attrFuncInfoModel.valueParamType)
        val valueInfo = valueParser.getValueInfo(attrFuncInfoModel.attrName, attrValue)
            ?: return MatchResult(MatchType.FAILED, "", ViewGenResultInfo("$layoutName $layoutType", GenResult.VALUE_MATCH_ERROR, "暂不支持@*资源引用"))
        val attrInfoModel = attrInfoMap[attrFuncInfoModel.attrName] ?: return MatchResult(MatchType.FAILED, "",
            ViewGenResultInfo("$layoutName $layoutType", GenResult.VALUE_MATCH_ERROR
                , "${viewTypeName}-${attrFuncInfoModel.attrName} the value(${valueInfo.sourceValue})" +
                        " can't parse to ${attrFuncInfoModel.valueParamType} "))

        if (valueInfo.valueType <= ValueType.REFERENCE_ATTR) {
            usedReferenceRMap.putIfAbsent(valueInfo.valueWithoutPackageName, "")
            //valueInfo.value = idMap[valueInfo.valueWithoutPackageName]?.toString() ?: valueInfo.value
        }

        when(paramType) {
            ParamType.INT -> {
                return intTypeValueResolver.resolve(viewTypeName, viewFieldName, layoutName, layoutType, attrFuncInfoModel, valueInfo, attrInfoModel, fieldInfo, contextName)
            }
            ParamType.FLOAT -> {
                return floatTypeValueResolver.resolve(viewTypeName, viewFieldName, layoutName, layoutType, attrFuncInfoModel, valueInfo, attrInfoModel, fieldInfo, contextName)
            }
            ParamType.BOOLEAN -> {
                return booleanTypeValueResolver.resolve(viewTypeName, viewFieldName, layoutName, layoutType, attrFuncInfoModel, valueInfo, attrInfoModel, fieldInfo, contextName)
            }
            ParamType.STRING -> {
                return stringTypeValueResolver.resolve(viewTypeName, viewFieldName, layoutName, layoutType, attrFuncInfoModel, valueInfo, attrInfoModel, fieldInfo, contextName)
            }
            ParamType.VALUE_INFO -> {
                return typedValueTypeResolver.resolve(viewTypeName, viewFieldName, layoutName, layoutType, attrFuncInfoModel, valueInfo, attrInfoModel, fieldInfo, contextName)
            }
            else -> {}
        }
        return MatchResult(MatchType.FAILED, "", ViewGenResultInfo("$layoutName $layoutType", GenResult.VALUE_MATCH_ERROR, "${attrFuncInfoModel.viewParamType}-${attrFuncInfoModel.attrName} the value(${valueInfo.sourceValue}) can't parse to ${attrFuncInfoModel.valueParamType}"))
    }

    fun getValueInfo(attrName: String, attrValue: String
                     , usedReferenceRMap: HashMap<String, String>, idMap: Map<String, Int>?): ValueInfo {
        val valueInfo = valueParser.getValueInfo(attrName, attrValue)!!
        if (valueInfo.valueType <= ValueType.REFERENCE_ATTR) {
            usedReferenceRMap.putIfAbsent(valueInfo.valueWithoutPackageName, "")
            /*idMap?.apply {
                valueInfo.value = idMap[valueInfo.valueWithoutPackageName]?.toString() ?: valueInfo.value
            }*/
        }
        return valueInfo
    }

    private fun getMethodParamType(paramType: String): ParamType {
        return typeMap[paramType] ?: ParamType.UN_SUPPORT
    }

}

enum class ParamType {
    UN_SUPPORT, INT, FLOAT, BOOLEAN, STRING, VALUE_INFO
}