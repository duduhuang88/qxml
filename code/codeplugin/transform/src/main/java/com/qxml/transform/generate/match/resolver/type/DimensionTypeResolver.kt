package com.qxml.transform.generate.match.resolver.type

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.log.LogUtil
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.match.resolver.MatchResult
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.tools.SizeTool
import com.qxml.transform.generate.value.ValueInfo
import java.lang.StringBuilder

class DimensionTypeResolver: AttrTypeResolver {
    override fun resolve(
        layoutName: String,
        layoutType: String,
        attrFuncInfoModel: AttrFuncInfoModel,
        valueInfo: ValueInfo,
        attrInfoModel: AttrInfoModel,
        valueType: ValueType,
        value: String,
        lastFuncBodyContent: String,
        fieldInfo: FieldInfo,
        contextName: String,
        stringBuilder: StringBuilder
    ): MatchResult? {
        var valueSource = value
        if (attrInfoModel.isDimension()) {
            if (valueType == ValueType.REFERENCE_DIMEN) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_REFERENCE or Constants.FORMAT_DIMENSION or Constants.FORMAT_FLOAT))
                stringBuilder.append(makeDimenById(valueSource))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(valueSource))
                stringBuilder.append(makeIntFromFloat())
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.SOURCE_STRING) {
                if (valueSource.endsWith(Constants.PX)) {
                    valueSource = valueSource.substring(0, valueSource.length - 2)
                    valueSource.toFloatOrNull()?.also {
                        stringBuilder.append(makeValueInfoType(Constants.FORMAT_DIMENSION or Constants.FORMAT_FLOAT))
                        stringBuilder.append(makeReferenceType(valueType))
                        stringBuilder.append(makeFloatByString(valueSource))
                        stringBuilder.append(makeIntFromFloat())
                        return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
                    }
                } else if (valueSource.endsWith(Constants.DP) || valueSource.endsWith(Constants.DIP)) {
                    val endSuffixLength = if (valueSource.endsWith(Constants.DP)) 2 else 3
                    valueSource = valueSource.substring(0, valueSource.length - endSuffixLength)
                    valueSource.toFloatOrNull()?.also {
                        val dpFiledFloat = SizeTool.makeDpFieldFloat(valueSource)
                        fieldInfo.sizeMap.putIfAbsent(dpFiledFloat, SizeTool.makeDpValueFloat(valueSource))
                        stringBuilder.append(makeFloatByString(dpFiledFloat))
                        stringBuilder.append(makeIntFromFloat())
                        return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
                    }
                } else if (valueSource.endsWith(Constants.SP)) {
                    valueSource = valueSource.substring(0, valueSource.length - 2)
                    valueSource.toFloatOrNull()?.also {
                        val spFiledFloat = SizeTool.makeSpFieldFloat(valueSource)
                        fieldInfo.sizeMap.putIfAbsent(spFiledFloat, SizeTool.makeSpValueFloat(valueSource))
                        stringBuilder.append(makeFloatByString(spFiledFloat))
                        stringBuilder.append(makeIntFromFloat())
                        return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
                    }
                } else if (valueSource.endsWith(Constants.MM)) {
                    valueSource = valueSource.substring(0, valueSource.length - 2)
                    valueSource.toFloatOrNull()?.also {
                        val mmFiledFloat = SizeTool.makeMMFieldFloat(valueSource)
                        fieldInfo.sizeMap.putIfAbsent(mmFiledFloat, SizeTool.makeMMValueFloat(valueSource))
                        stringBuilder.append(makeFloatByString(mmFiledFloat))
                        stringBuilder.append(makeIntFromFloat())
                        return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
                    }
                } else if (valueSource.endsWith(Constants.PT)) {
                    valueSource = valueSource.substring(0, valueSource.length - 2)
                    valueSource.toFloatOrNull()?.also {
                        val ptFiledFloat = SizeTool.makePTFieldFloat(valueSource)
                        fieldInfo.sizeMap.putIfAbsent(ptFiledFloat, SizeTool.makePTValueFloat(valueSource))
                        stringBuilder.append(makeFloatByString(ptFiledFloat))
                        stringBuilder.append(makeIntFromFloat())
                        return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
                    }
                } else if (valueSource.endsWith(Constants.IN)) {
                    valueSource = valueSource.substring(0, valueSource.length - 2)
                    valueSource.toFloatOrNull()?.also {
                        val inFiledFloat = SizeTool.makeINFieldFloat(valueSource)
                        fieldInfo.sizeMap.putIfAbsent(inFiledFloat, SizeTool.makeINValueFloat(valueSource))
                        stringBuilder.append(makeFloatByString(inFiledFloat))
                        stringBuilder.append(makeIntFromFloat())
                        return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
                    }
                }
            }
        }
        return null
    }
}