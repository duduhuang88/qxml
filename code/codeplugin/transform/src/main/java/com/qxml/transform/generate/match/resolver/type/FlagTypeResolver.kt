package com.qxml.transform.generate.match.resolver.type

import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.generate.match.resolver.MatchResult
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.value.ValueInfo
import java.lang.StringBuilder

class FlagTypeResolver: AttrTypeResolver {
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

        if (attrInfoModel.isEnum()) {
            if (valueType == ValueType.FLAG) {
                stringBuilder.append(makeValueInfoType(Constants.FORMAT_FLAGS))
                stringBuilder.append(makeLongByString(value))
                return makeResolverSucStateInfo(stringBuilder, lastFuncBodyContent)
            } else if (valueType == ValueType.REFERENCE_STRING) {
                //stringBuilder.append(makeValueInfoType(Constants.FORMAT_ENUM and Constants.FORMAT_REFERENCE))
                //暂时不支持
            }
        }

        return null
    }

    private fun makeLongByString(value: String): String {
        return "${Constants.GEN_FIELD_VALUE_INFO_NAME}.longValue = $value;\n"
    }
}