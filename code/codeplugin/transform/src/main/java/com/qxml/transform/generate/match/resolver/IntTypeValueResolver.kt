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
                         contextName: String): MatchResult {

        val valueType = valueInfo.valueType
        var value = valueInfo.value
        val funcBody = attrFuncInfoModel.funcBodyContent.substring(1).replace(Constants.PARAM_NAME_TEMP, viewFieldName)

        val stringBuilder = StringBuilder()

        stringBuilder.append(attrFuncInfoModel.getMethodContentHead(viewFieldName))

        if (valueInfo.isAttrReference) {
            stringBuilder.append(getAttrReferenceResolveState(contextName, valueInfo))
            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${callAttrIntReferenceMethod(contextName, value)};\n")
            stringBuilder.append(funcBody)
            return makeResolverSucStateInfo(stringBuilder)
        } else {
            if (valueType == ValueType.NULL) {
                stringBuilder.append("${attrFuncInfoModel.valueParamName} = 0;\n")
                stringBuilder.append(funcBody)
                return makeResolverSucStateInfo(stringBuilder)
            }
            if (attrInfoModel.isReference()) {
                //should use ValueInfo
                if (valueType < ValueType.ENUM) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = $value;\n")
                    stringBuilder.append(funcBody)
                    return makeResolverSucStateInfo(stringBuilder)
                }
            }
            if (attrInfoModel.isDimension()) {
                if (valueType == ValueType.REFERENCE_DIMEN) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${Constants.GEN_PARAM_CONTEXT_NAME}.getResources().getDimensionPixelSize($value);\n")
                    stringBuilder.append(funcBody)
                    return makeResolverSucStateInfo(stringBuilder)
                } else if (valueType == ValueType.SOURCE_STRING) {
                    if (value.endsWith(Constants.PX)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            //转成Int 可能溢出
                            val result = it.roundToInt()
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = $result;\n")
                            stringBuilder.append(funcBody)
                            return makeResolverSucStateInfo(stringBuilder)
                        }
                    } else if (value.endsWith(Constants.DP) || value.endsWith(Constants.DIP)) {
                        val endSuffixLength = if (value.endsWith(Constants.DP)) 2 else 3
                        value = value.substring(0, value.length - endSuffixLength)
                        value.toFloatOrNull()?.also {
                            val dpFiledInt = SizeTool.makeDpFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(dpFiledInt, SizeTool.makeDpValueInt(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${dpFiledInt};\n")
                            stringBuilder.append(funcBody)
                            return makeResolverSucStateInfo(stringBuilder)
                        }
                    } else if (value.endsWith(Constants.SP)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val spFiledInt = SizeTool.makeSpFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(spFiledInt, SizeTool.makeSpValueInt(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${spFiledInt};\n")
                            stringBuilder.append(funcBody)
                            return makeResolverSucStateInfo(stringBuilder)
                        }
                    } else if (value.endsWith(Constants.MM)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val mmFiledInt = SizeTool.makeMMFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(mmFiledInt, SizeTool.makeMMValueInt(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${mmFiledInt};\n")
                            stringBuilder.append(funcBody)
                            return makeResolverSucStateInfo(stringBuilder)
                        }
                    } else if (value.endsWith(Constants.PT)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val ptFiledInt = SizeTool.makePTFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(ptFiledInt, SizeTool.makePTValueInt(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${ptFiledInt};\n")
                            stringBuilder.append(funcBody)
                            return makeResolverSucStateInfo(stringBuilder)
                        }
                    } else if (value.endsWith(Constants.IN)) {
                        value = value.substring(0, value.length - 2)
                        value.toFloatOrNull()?.also {
                            val inFiledInt = SizeTool.makeINFieldInt(value)
                            fieldInfo.sizeMap.putIfAbsent(inFiledInt, SizeTool.makeINValueInt(value))
                            stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${inFiledInt};\n")
                            stringBuilder.append(funcBody)
                            return makeResolverSucStateInfo(stringBuilder)
                        }
                    }
                } else if (valueType == ValueType.ENUM || valueType == ValueType.FLAG) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${value};\n")
                    stringBuilder.append(funcBody)
                    return makeResolverSucStateInfo(stringBuilder)
                }
            }
            if (attrInfoModel.isInteger()) {
                if (valueType == ValueType.SOURCE_STRING) {
                    value.toIntOrNull()?.also {
                        stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${value};\n")
                        stringBuilder.append(funcBody)
                        return makeResolverSucStateInfo(stringBuilder)
                    }
                } else if (valueType < ValueType.ENUM) {//当资源是引用时
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = $value;\n")
                    stringBuilder.append(funcBody)
                    return makeResolverSucStateInfo(stringBuilder)
                }
            }
            if (attrInfoModel.isEnum() || attrInfoModel.isFlag()) {
                if (valueType == ValueType.ENUM || valueType == ValueType.FLAG) {
                    stringBuilder.append("${attrFuncInfoModel.valueParamName} = ${value};\n")
                    stringBuilder.append(funcBody)
                    return makeResolverSucStateInfo(stringBuilder)
                }
            }
        }

        return makeResolverErrInfo(layoutName, layoutType, attrFuncInfoModel, valueInfo)
    }
}