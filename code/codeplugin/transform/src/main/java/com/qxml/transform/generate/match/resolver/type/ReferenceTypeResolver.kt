package com.qxml.transform.generate.match.resolver.type

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.match.resolver.MatchResult
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo
import java.lang.StringBuilder

class ReferenceTypeResolver: AttrTypeResolver {
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
            if (valueType < ValueType.ENUM) {
                var type = Constants.FORMAT_REFERENCE
                if (valueType == ValueType.REFERENCE_COLOR) {
                    type = type or Constants.FORMAT_COLOR
                    stringBuilder.append(makeColorById(value))
                } else if (valueType == ValueType.REFERENCE_DIMEN) {
                    type = type or Constants.FORMAT_DIMENSION or Constants.FORMAT_FLOAT
                    stringBuilder.append(makeDimenById(value))
                } else if (valueType == ValueType.REFERENCE_STRING) {
                    type = type or Constants.FORMAT_STRING
                    stringBuilder.append(makeStringById(value))
                } else if (valueType == ValueType.REFERENCE_BOOLEAN) {
                    type = type or Constants.FORMAT_BOOLEAN
                    stringBuilder.append(makeBooleanById(value))
                }
                stringBuilder.append(makeValueInfoType(type))
                stringBuilder.append(makeReferenceType(valueType))
                stringBuilder.append(makeResourceId(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            }
        }
        return null
    }
}