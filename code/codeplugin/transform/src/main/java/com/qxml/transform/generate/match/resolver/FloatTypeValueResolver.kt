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
        contextName: String,
        usedTempVarMap: HashMap<String, String>,
        shouldWrapScope: Boolean
    ): MatchResult {
        val valueType = valueInfo.valueType
        var value = valueInfo.value

        val funcBody = attrFuncInfoModel.funcBodyContent.substring(1).replace(Constants.VIEW_PARAM_NAME_TEMP, viewFieldName)

        val stringBuilder = StringBuilder()

        if (valueInfo.isAttrReference) {
            stringBuilder.append(getAttrReferenceResolveState(contextName, valueInfo))
            stringBuilder.append(makeParamInit(attrFuncInfoModel, callAttrFloatReferenceMethod(contextName, value), usedTempVarMap))
            return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
        } else {
            if (valueType == ValueType.NULL) {
                stringBuilder.append(makeParamInit(attrFuncInfoModel, "0f", usedTempVarMap))
                return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
            }
            if (attrInfoModel.isReference()) {
                if (valueType == ValueType.REFERENCE_DIMEN) {
                    stringBuilder.append(makeParamInit(attrFuncInfoModel, "${Constants.GEN_FIELD_RESOURCE}.getDimension(${value}f)", usedTempVarMap))
                    return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                }
            }
            if (attrInfoModel.isDimension() || attrInfoModel.isFloat() || attrInfoModel.isFraction()) {
                if (valueType == ValueType.REFERENCE_DIMEN) {
                    stringBuilder.append(makeParamInit(attrFuncInfoModel, "${Constants.GEN_FIELD_RESOURCE}.getDimension(${value})", usedTempVarMap))
                    return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                } else if (valueType == ValueType.SOURCE_STRING) {
                    if (value.endsWith(Constants.PX)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            stringBuilder.append(makeParamInit(attrFuncInfoModel, "${value}f", usedTempVarMap))
                            return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                        }
                    } else if (value.endsWith(Constants.DP) || value.endsWith(Constants.DIP)) {
                        val endSuffixLength = if (value.endsWith(Constants.DP)) 2 else 3
                        value = value.substring(0, value.length - endSuffixLength)
                        value.toFloatOrNull()?.also {
                            val dpFiledFloat = SizeTool.makeDpFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(dpFiledFloat, SizeTool.makeDpValueFloat(value))
                            stringBuilder.append(makeParamInit(attrFuncInfoModel, dpFiledFloat, usedTempVarMap))
                            return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                        }
                    } else if (value.endsWith(Constants.SP)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val spFiledFloat = SizeTool.makeSpFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(spFiledFloat, SizeTool.makeSpValueFloat(value))
                            stringBuilder.append(makeParamInit(attrFuncInfoModel, spFiledFloat, usedTempVarMap))
                            return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                        }
                    } else if (value.endsWith(Constants.MM)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val mmFiledFloat = SizeTool.makeMMFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(mmFiledFloat, SizeTool.makeMMValueFloat(value))
                            stringBuilder.append(makeParamInit(attrFuncInfoModel, mmFiledFloat, usedTempVarMap))
                            return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                        }
                    } else if (value.endsWith(Constants.PT)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val ptFiledFloat = SizeTool.makePTFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(ptFiledFloat, SizeTool.makePTValueFloat(value))
                            stringBuilder.append(makeParamInit(attrFuncInfoModel, ptFiledFloat, usedTempVarMap))
                            return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                        }
                    } else if (value.endsWith(Constants.IN)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val inFiledFloat = SizeTool.makeINFieldFloat(value)
                            fieldInfo.sizeMap.putIfAbsent(inFiledFloat, SizeTool.makeINValueFloat(value))
                            stringBuilder.append(makeParamInit(attrFuncInfoModel, inFiledFloat, usedTempVarMap))
                            return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                        }
                    } else {
                        value.toFloatOrNull()?.also {
                            stringBuilder.append(makeParamInit(attrFuncInfoModel, "${value}f", usedTempVarMap))
                            return makeResolverSucStateInfo(stringBuilder, funcBody, shouldWrapScope)
                        }
                    }
                }
            }
        }

        return makeResolverErrInfo(layoutName, layoutType, attrFuncInfoModel, valueInfo)
    }
}