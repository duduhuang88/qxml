package com.qxml.transform.generate.match.resolver.type

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.match.resolver.MatchResult
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo
import java.lang.StringBuilder

class IntegerTypeResolver: AttrTypeResolver {
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
        if (attrInfoModel.isInteger()) {
            if (valueType == ValueType.REFERENCE_INTEGER) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_INTEGER or Constants.FORMAT_REFERENCE))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                stringBuilder.append(makeIntegerById(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.REFERENCE_STRING) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_INTEGER or Constants.FORMAT_STRING or Constants.FORMAT_REFERENCE))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                stringBuilder.append(makeIntegerByStringId(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.SOURCE_STRING) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_INTEGER))
                stringBuilder.append(makeIntegerByString(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            }
        }
        return null
    }
}