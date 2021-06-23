package com.qxml.transform.generate.match.resolver

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.generate.value.ValueInfo
import com.qxml.transform.*
import com.qxml.transform.generate.match.resolver.type.*
import com.qxml.transform.generate.model.FieldInfo

private const val VALUE_INFO_CLEAR = "${Constants.GEN_FIELD_VALUE_INFO_NAME}.clear();\n"

private const val VALUE_INFO_CLEAR_SIMPLE = "${Constants.GEN_FIELD_VALUE_INFO_NAME}.stringValue = null;\n" +
        "${Constants.GEN_FIELD_VALUE_INFO_NAME}.type = 0;\n" +
        "${Constants.GEN_FIELD_VALUE_INFO_NAME}.floatValue = 0f;\n" +
        "${Constants.GEN_FIELD_VALUE_INFO_NAME}.resourceId = 0;\n" +
        "${Constants.GEN_FIELD_VALUE_INFO_NAME}.intValue = 0;\n" +
        "${Constants.GEN_FIELD_VALUE_INFO_NAME}.colorValue = 0;\n" +
        "${Constants.GEN_FIELD_VALUE_INFO_NAME}.referenceType = com.qxml.constant.ValueType.SOURCE_STRING;\n"

class ValueInfoTypeResolver: ValueResolver {

    private val typeResolverArray = arrayOf(ReferenceTypeResolver()
        , DimensionTypeResolver(), FloatTypeResolver(), StringTypeResolver(), BooleanTypeResolver()
        , ColorTypeResolver(), IntegerTypeResolver(), FractionTypeResolver(), EnumTypeResolver()
        , FlagTypeResolver())

    override fun resolve(
        viewTypeName: String, viewFieldName: String,
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
        val stringBuilder = StringBuilder()
        val lastFuncBody = attrFuncInfoModel.funcBodyContent.substring(1)
        stringBuilder.append("{\n")
        stringBuilder.append("${attrFuncInfoModel.viewParamType} ${attrFuncInfoModel.viewParamName} = $viewFieldName;\n")
        stringBuilder.append("${attrFuncInfoModel.valueParamType} ${attrFuncInfoModel.valueParamName} = ${Constants.GEN_FIELD_VALUE_INFO_NAME};\n")

        stringBuilder.append(VALUE_INFO_CLEAR)
        stringBuilder.append(makeString(valueInfo.sourceValue))


        if (valueType == ValueType.NULL) {
            stringBuilder.append(typeResolverArray[0].makeValueInfoType(Constants.FORMAT_NULL))
            stringBuilder.append(lastFuncBody)
            return makeResolverNullInfo(stringBuilder)
        }

        if (valueInfo.isAttrReference) {
            stringBuilder.append(getAttrReferenceResolveState(contextName, valueInfo))
            stringBuilder.append("${Constants.REFERENCE_ATTR_RESOLVER}.resolveValueInfo(${Constants.GEN_PARAM_CONTEXT_NAME}, ${Constants.GEN_FIELD_TYPED_VALUE_NAME}, ${Constants.GEN_FIELD_VALUE_INFO_NAME}, ${value});\n")
            stringBuilder.append(lastFuncBody)
            return makeResolverSucStateInfo(stringBuilder)
        }

        typeResolverArray.forEach {  resolver->
            resolver.resolve(layoutName, layoutType, attrFuncInfoModel, valueInfo
                , attrInfoModel, valueType, value, lastFuncBody, fieldInfo, contextName, stringBuilder)?.also { return it }
        }

        return makeResolverErrInfo(layoutName, layoutType, attrFuncInfoModel, valueInfo)
    }

    private fun makeString(value: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.stringValue = \"$value\";\n"
    }

    override fun type(): String = "ValueInfo"
}