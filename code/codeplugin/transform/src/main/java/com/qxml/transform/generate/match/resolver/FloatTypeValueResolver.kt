package com.qxml.transform.generate.match.resolver

import com.qxml.constant.Constants
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.value.ValueInfo
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.tools.SizeTool
import java.lang.StringBuilder

class FloatTypeValueResolver: ValueResolver {

    override fun type(): String = "float"

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
        var value = valueInfo.value

        val funcBody = attrFuncInfoModel.funcBodyContent.substring(1)

        val stringBuilder = StringBuilder()
        stringBuilder.append(attrFuncInfoModel.getMethodContentHead(viewFieldName))

        if (valueInfo.isAttrReference) {
            stringBuilder.append(getAttrReferenceResolveState(contextName, valueInfo))
            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${callAttrFloatReferenceMethod(contextName, value)};\n")
            return makeResolverSucStateInfo(stringBuilder, funcBody)
        } else {
            if (valueType == ValueType.NULL) {
                stringBuilder.append("${attrFuncInfoModel.valueParamName} = 0f;\n")
                return makeResolverSucStateInfo(stringBuilder, funcBody)
            }
            if (attrInfoModel.isReference()) {
                if (valueType == ValueType.REFERENCE_DIMEN) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${Constants.GEN_FIELD_RESOURCE}.getDimension(${value}f);\n")
                    return makeResolverSucStateInfo(stringBuilder, funcBody)
                }
            }
            if (attrInfoModel.isDimension() || attrInfoModel.isFloat() || attrInfoModel.isFraction()) {
                if (valueType == ValueType.REFERENCE_DIMEN) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${Constants.GEN_FIELD_RESOURCE}.getDimension(${value});\n")
                    return makeResolverSucStateInfo(stringBuilder, funcBody)
                } else if (valueType == ValueType.SOURCE_STRING) {
                    if (value.endsWith(Constants.PX)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${value}f;\n")
                            return makeResolverSucStateInfo(stringBuilder, funcBody)
                        }
                    } else if (value.endsWith(Constants.DP) || value.endsWith(Constants.DIP)) {
                        val endSuffixLength = if (value.endsWith(Constants.DP)) 2 else 3
                        value = value.substring(0, value.length - endSuffixLength)
                        value.toFloatOrNull()?.also {
                            val dpFiledFloat = SizeTool.makeDpFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(dpFiledFloat, SizeTool.makeDpValueFloat(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${dpFiledFloat};\n")
                            return makeResolverSucStateInfo(stringBuilder, funcBody)
                        }
                    } else if (value.endsWith(Constants.SP)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val spFiledFloat = SizeTool.makeSpFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(spFiledFloat, SizeTool.makeSpValueFloat(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${spFiledFloat};\n")
                            return makeResolverSucStateInfo(stringBuilder, funcBody)
                        }
                    } else if (value.endsWith(Constants.MM)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val mmFiledFloat = SizeTool.makeMMFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(mmFiledFloat, SizeTool.makeMMValueFloat(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${mmFiledFloat};\n")
                            return makeResolverSucStateInfo(stringBuilder, funcBody)
                        }
                    } else if (value.endsWith(Constants.PT)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val ptFiledFloat = SizeTool.makePTFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(ptFiledFloat, SizeTool.makePTValueFloat(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${ptFiledFloat};\n")
                            return makeResolverSucStateInfo(stringBuilder, funcBody)
                        }
                    } else if (value.endsWith(Constants.IN)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val inFiledFloat = SizeTool.makeINFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(inFiledFloat, SizeTool.makeINValueFloat(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${inFiledFloat};\n")
                            return makeResolverSucStateInfo(stringBuilder, funcBody)
                        }
                    } else {
                        value.toFloatOrNull()?.also {
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${value}f;\n")
                            return makeResolverSucStateInfo(stringBuilder, funcBody)
                        }
                    }
                }
            }
        }

        return makeResolverErrInfo(layoutName, layoutType, attrFuncInfoModel, valueInfo)
    }
}