package com.qxml.transform.generate.match.resolver.type

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.match.resolver.MatchResult
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo
import java.lang.StringBuilder

class StringTypeResolver: AttrTypeResolver {
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
            if (valueType == ValueType.REFERENCE_STRING) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_STRING or Constants.FORMAT_REFERENCE))
                stringBuilder.append(makeStringById(value))
                stringBuilder.append(makeResourceId(value))
                stringBuilder.append(makeReferenceType(valueType))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            }
        }
        if (attrInfoModel.isString()) {
            stringBuilder.append(makeValueInfoType(Constants.FORMAT_STRING))
            stringBuilder.append(makeStringBySource(value))
            return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
        }
        return null
    }
}