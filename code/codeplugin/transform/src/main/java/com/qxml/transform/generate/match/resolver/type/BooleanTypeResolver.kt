package com.qxml.transform.generate.match.resolver.type

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.match.resolver.MatchResult
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo
import java.lang.StringBuilder

class BooleanTypeResolver: AttrTypeResolver {
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
        if (attrInfoModel.isBoolean()) {
            if (valueType == ValueType.REFERENCE_STRING) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_BOOLEAN))
                stringBuilder.append(makeBooleanByStringId(value))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.SOURCE_STRING) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_BOOLEAN))
                stringBuilder.append(makeBooleanByString(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.REFERENCE_BOOLEAN) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_BOOLEAN or Constants.FORMAT_REFERENCE))
                stringBuilder.append(makeBooleanById(value))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            }
        }
        return null
    }
}