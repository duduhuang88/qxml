package com.qxml.transform.generate.match.resolver

import com.qxml.constant.Constants
import com.qxml.transform.*
import com.qxml.transform.generate.value.ValueInfo
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.tools.SizeTool
import java.lang.StringBuilder
import kotlin.math.roundToInt

class IntTypeValueResolver: ValueResolver {

    override fun type(): String = "int"

    override fun resolve(viewTypeName: String,
                         viewFieldName: String,
                         layoutName: String,
                         layoutType: String,
                         attrFuncInfoModel: AttrFuncInfoModel,
                         valueInfo: ValueInfo,
                         attrInfoModel: AttrInfoModel,
                         fieldInfo: FieldInfo,
                         contextName: String,
                         usedTempVarMap: HashMap<String, String>): MatchResult {

        val valueType = valueInfo.valueType
        var value = valueInfo.value
        val funcBody = attrFuncInfoModel.funcBodyContent.substring(3).replace(Constants.VIEW_PARAM_NAME_TEMP, viewFieldName)

        val stringBuilder = StringBuilder()

        if (valueInfo.isAttrReference) {
            stringBuilder.append(getAttrReferenceResolveState(contextName, valueInfo))
            stringBuilder.append(makeParamInit(attrFuncInfoModel, callAttrIntReferenceMethod(contextName, value), usedTempVarMap))
            stringBuilder.append(funcBody.toParamNameFuncBody(attrFuncInfoModel))
            return makeResolverSucStateInfo(stringBuilder, false)
        } else {
            if (valueType == ValueType.NULL) {
                //stringBuilder.append(makeParamInit(attrFuncInfoModel, "0", usedTempVarMap))
                stringBuilder.append(funcBody.toBaseTypeFuncBody("0"))
                return makeResolverSucStateInfo(stringBuilder, true)
            }
            if (attrInfoModel.isReference()) {
                //should use ValueInfo
                if (valueType < ValueType.ENUM) {
                    //stringBuilder.append(makeParamInit(attrFuncInfoModel, value, usedTempVarMap))
                    stringBuilder.append(funcBody.toBaseTypeFuncBody(value))
                    return makeResolverSucStateInfo(stringBuilder, true)
                }
            }
            if (attrInfoModel.isDimension()) {
                if (valueType == ValueType.REFERENCE_DIMEN) {
                    stringBuilder.append(makeParamInit(attrFuncInfoModel, "${Constants.GEN_PARAM_CONTEXT_NAME}.getResources().getDimensionPixelSize($value)", usedTempVarMap))
                    stringBuilder.append(funcBody.toParamNameFuncBody(attrFuncInfoModel))
                    return makeResolverSucStateInfo(stringBuilder, false)
                } else if (valueType == ValueType.SOURCE_STRING) {
                    if (value.endsWith(Constants.PX)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            //转成Int 可能溢出
                            val result = it.roundToInt()
                            //stringBuilder.append(makeParamInit(attrFuncInfoModel, result.toString(), usedTempVarMap))
                            stringBuilder.append(funcBody.toBaseTypeFuncBody(result.toString()))
                            return makeResolverSucStateInfo(stringBuilder, true)
                        }
                    } else if (value.endsWith(Constants.DP) || value.endsWith(Constants.DIP)) {
                        val endSuffixLength = if (value.endsWith(Constants.DP)) 2 else 3
                        value = value.substring(0, value.length - endSuffixLength)
                        value.toFloatOrNull()?.also {
                            val dpFiledInt = SizeTool.makeDpFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(dpFiledInt, SizeTool.makeDpValueInt(value))
                            //stringBuilder.append(makeParamInit(attrFuncInfoModel, dpFiledInt, usedTempVarMap))
                            stringBuilder.append(funcBody.toBaseTypeFuncBody(dpFiledInt))
                            return makeResolverSucStateInfo(stringBuilder, true)
                        }
                    } else if (value.endsWith(Constants.SP)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val spFiledInt = SizeTool.makeSpFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(spFiledInt, SizeTool.makeSpValueInt(value))
                            //stringBuilder.append(makeParamInit(attrFuncInfoModel, spFiledInt, usedTempVarMap))
                            stringBuilder.append(funcBody.toBaseTypeFuncBody(spFiledInt))
                            return makeResolverSucStateInfo(stringBuilder, false)
                        }
                    } else if (value.endsWith(Constants.MM)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val mmFiledInt = SizeTool.makeMMFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(mmFiledInt, SizeTool.makeMMValueInt(value))
                            //stringBuilder.append(makeParamInit(attrFuncInfoModel, mmFiledInt, usedTempVarMap))
                            stringBuilder.append(funcBody.toBaseTypeFuncBody(mmFiledInt))
                            return makeResolverSucStateInfo(stringBuilder, false)
                        }
                    } else if (value.endsWith(Constants.PT)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val ptFiledInt = SizeTool.makePTFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(ptFiledInt, SizeTool.makePTValueInt(value))
                            //stringBuilder.append(makeParamInit(attrFuncInfoModel, ptFiledInt, usedTempVarMap))
                            stringBuilder.append(funcBody.toBaseTypeFuncBody(ptFiledInt))
                            return makeResolverSucStateInfo(stringBuilder, true)
                        }
                    } else if (value.endsWith(Constants.IN)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val inFiledInt = SizeTool.makeINFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(inFiledInt, SizeTool.makeINValueInt(value))
                            //stringBuilder.append(makeParamInit(attrFuncInfoModel, inFiledInt, usedTempVarMap))
                            stringBuilder.append(funcBody.toBaseTypeFuncBody(inFiledInt))
                            return makeResolverSucStateInfo(stringBuilder, true)
                        }
                    }
                } else if (valueType == ValueType.ENUM || valueType == ValueType.FLAG) {
                    //stringBuilder.append(makeParamInit(attrFuncInfoModel, value, usedTempVarMap))
                    stringBuilder.append(funcBody.toBaseTypeFuncBody(value))
                    return makeResolverSucStateInfo(stringBuilder, true)
                }
            }
            if (attrInfoModel.isInteger()) {
                if (valueType == ValueType.SOURCE_STRING) {
                    value.toIntOrNull()?.also {
                        //stringBuilder.append(makeParamInit(attrFuncInfoModel, value, usedTempVarMap))
                        stringBuilder.append(funcBody.toBaseTypeFuncBody(value))
                        return makeResolverSucStateInfo(stringBuilder, true)
                    }
                } else if (valueType < ValueType.ENUM) {//当资源是引用时
                    //stringBuilder.append(makeParamInit(attrFuncInfoModel, value, usedTempVarMap))
                    stringBuilder.append(funcBody.toBaseTypeFuncBody(value))
                    return makeResolverSucStateInfo(stringBuilder, true)
                }
            }
            if (attrInfoModel.isEnum() || attrInfoModel.isFlag()) {
                if (valueType == ValueType.ENUM || valueType == ValueType.FLAG) {
                    //stringBuilder.append(makeParamInit(attrFuncInfoModel, value, usedTempVarMap))
                    stringBuilder.append(funcBody.toBaseTypeFuncBody(value))
                    return makeResolverSucStateInfo(stringBuilder, true)
                }
            }
        }

        return makeResolverErrInfo(layoutName, layoutType, attrFuncInfoModel, valueInfo)
    }
}