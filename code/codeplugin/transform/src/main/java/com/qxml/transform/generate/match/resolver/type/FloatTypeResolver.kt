package com.qxml.transform.generate.match.resolver.type

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.match.resolver.MatchResult
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo
import java.lang.StringBuilder

class FloatTypeResolver: AttrTypeResolver {
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
        if (attrInfoModel.isReference()) {
            if (valueType == ValueType.REFERENCE_DIMEN) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_FLOAT or Constants.FORMAT_REFERENCE))
                stringBuilder.append(makeFloatByDimenId(value))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.REFERENCE_STRING) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_FLOAT or Constants.FORMAT_REFERENCE))
                stringBuilder.append(makeFloatByStringId(value))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            }
        }
        if (attrInfoModel.isFloat()) {
            if (valueType == ValueType.SOURCE_STRING) {
                value.toFloatOrNull()?.also {
                    stringBuilder.append(makeValueInfoType(Constants.FORMAT_FLOAT))
                    stringBuilder.append(makeFloatByString(value))
                    return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
                }
            } else if (valueType == ValueType.REFERENCE_DIMEN) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_FLOAT or Constants.FORMAT_REFERENCE))
                stringBuilder.append(makeFloatByDimenId(value))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.REFERENCE_STRING) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_FLOAT or Constants.FORMAT_REFERENCE))
                stringBuilder.append(makeFloatByStringId(value))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            }
        }
        return null
    }
}