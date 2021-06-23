package com.qxml.transform.generate

import com.qxml.QxmlConfigExtension
import com.qxml.constant.Constants
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.generate.match.AttrMethodValueMatcher
import com.qxml.transform.generate.match.resolver.MatchType
import com.qxml.transform.generate.model.FieldInfo
import com.squareup.javapoet.CodeBlock

interface ResolveAttr {

    fun resolveAttr(nameNode: groovy.xml.QName, attrValue: String, viewCount: Int
                    , layoutName: String, layoutType: String, contextName: String
                    , rootIsDataBinding: Boolean
                    , viewGenInfoMap: Map<String, AttrFuncInfoModel>
                    , invalidGenInfoMap: HashMap<String, HashMap<String, Int>>
                    , viewClassName: String, viewFieldName: String
                    , codeBlockBuilder: CodeBlock.Builder
                    , qxmlConfig: QxmlConfigExtension
                    , usedGenInfoMap: HashMap<String, HashMap<String, AttrFuncInfoModel>>
                    , fieldInfo: FieldInfo, dataBindingAttrResolveInfo: DataBindingAttrResolveInfo
                    , attrMethodValueMatcher: AttrMethodValueMatcher
                    , usedLocalVarMap: HashMap<String, String>
                    , usedReferenceRMap: HashMap<String, String>
                    , keepAndroidTag : Boolean = true
    ): ViewGenResultInfo? {

        if (nameNode.namespaceURI == Constants.ANDROID_NAME_SPACE_URI || nameNode.namespaceURI == Constants.RES_AUTO_NAME_SPACE_URI) {
            if (viewCount == 0) {
                if (nameNode.localPart == Constants.QXML_CONFIG_ATTR_NAME) {
                    if (attrValue == Constants.QXML_CONFIG_IGNORE) {
                        return makeViewGenResult(layoutName, GenResult.IGNORE, "忽略")
                    } else qxmlConfig.ignoreUnImplementAttr = attrValue == Constants.QXML_CONFIG_WITH_UN_IMPLEMENT_ATTR
                }
            }
            //忽略dataBinding属性引用
            if (rootIsDataBinding
                && (attrValue.startsWith(Constants.DATA_BINDING_DATA_REFERENCE_PREFIX) || attrValue.startsWith(Constants.DATA_BINDING_DATA_TWO_WAY_REFERENCE_PREFIX))
                && attrValue.endsWith(Constants.DATA_BINDING_DATA_REFERENCE_SUFFIX)) {
                if (!dataBindingAttrResolveInfo.useDataBinding) {
                    dataBindingAttrResolveInfo.useDataBinding = true
                    if (viewCount != 0) {
                        codeBlockBuilder.addStatement("${viewFieldName}.setTag(\"binding_${dataBindingAttrResolveInfo.dataBindingCount}\")")
                        dataBindingAttrResolveInfo.dataBindingCount++
                    }
                }
                return null
            }
            if (nameNode.prefix.isEmpty()) {
                return null
            }
            val finalAttrName = if (nameNode.prefix == Constants.ATTR_PREFIX_ANDROID) nameNode.qualifiedName else nameNode.localPart
            val attrFuncInfo = viewGenInfoMap[finalAttrName] //?: return makeViewGenIgnore(layoutName, IgnoreReason.NO_ATTR_METHOD, "${layoutName}_${layoutType}找不到${viewName}_${finalAttrName}对应的生成函数")

            //tag放在最后处理，dataBinding会在生成类里赋值
            if (finalAttrName == "android:tag" && keepAndroidTag) {
                dataBindingAttrResolveInfo.tagNode = nameNode
                dataBindingAttrResolveInfo.tagValue = attrValue
                return null
            }
            if (attrFuncInfo == null) {
                //没有实现的attr方法
                if (finalAttrName != "qxml") {
                    var viewTypeMap = invalidGenInfoMap[viewClassName]
                    if (viewTypeMap == null) {
                        viewTypeMap = hashMapOf()
                        invalidGenInfoMap[viewClassName] = viewTypeMap
                    }
                    viewTypeMap.putIfAbsent(finalAttrName, 0)
                }
                return null
            }

            val matchResult = attrMethodValueMatcher.match(viewClassName, viewFieldName, layoutName, layoutType, attrFuncInfo, attrValue, fieldInfo, contextName, usedReferenceRMap)
            when (matchResult.matchType) {
                MatchType.FAILED -> {
                    return matchResult.ignoreInfo
                }
                else -> codeBlockBuilder.addStatement(matchResult.result)
            }
            var viewTypeGenInfoMap = usedGenInfoMap[viewClassName]
            if (viewTypeGenInfoMap == null) {
                viewTypeGenInfoMap = hashMapOf()
                usedGenInfoMap[viewClassName] = viewTypeGenInfoMap
            }
            viewTypeGenInfoMap.putIfAbsent(attrFuncInfo.attrName, attrFuncInfo)
            attrFuncInfo.usedLocalVarMap?.forEach { fullName, _ ->
                usedLocalVarMap.putIfAbsent(fullName, "")
            }
        }
        return null
    }

    fun resolveStyleAttr(attrName: String, attrValue: String, viewCount: Int
                         , layoutName: String, layoutType: String, contextName: String
                         , rootIsDataBinding: Boolean
                         , viewGenInfoMap: Map<String, AttrFuncInfoModel>
                         , invalidGenInfoMap: HashMap<String, HashMap<String, Int>>
                         , viewClassName: String, viewFieldName: String
                         , codeBlockBuilder: CodeBlock.Builder
                         , qxmlConfig: QxmlConfigExtension
                         , usedGenInfoMap: HashMap<String, HashMap<String, AttrFuncInfoModel>>
                         , fieldInfo: FieldInfo, dataBindingAttrResolveInfo: DataBindingAttrResolveInfo
                         , attrMethodValueMatcher: AttrMethodValueMatcher
                         , usedReferenceRMap: HashMap<String, String>
    ): ViewGenResultInfo? {
        //忽略dataBinding属性引用
        if (rootIsDataBinding
            && (attrValue.startsWith(Constants.DATA_BINDING_DATA_REFERENCE_PREFIX) || attrValue.startsWith(Constants.DATA_BINDING_DATA_TWO_WAY_REFERENCE_PREFIX))
            && attrValue.endsWith(Constants.DATA_BINDING_DATA_REFERENCE_SUFFIX)) {
            if (!dataBindingAttrResolveInfo.useDataBinding) {
                dataBindingAttrResolveInfo.useDataBinding = true
                if (viewCount != 0) {
                    codeBlockBuilder.addStatement("${viewFieldName}.setTag(\"binding_${dataBindingAttrResolveInfo.dataBindingCount}\")")
                    dataBindingAttrResolveInfo.dataBindingCount++
                }
            }
            return null
        }

        val attrFuncInfo = viewGenInfoMap[attrName] //?: return makeViewGenIgnore(layoutName, IgnoreReason.NO_ATTR_METHOD, "${layoutName}_${layoutType}找不到${viewName}_${finalAttrName}对应的生成函数")

        //tag放在最后处理，dataBinding会在生成类里赋值
        if (attrName == Constants.ATTR_TAG) {
            //未设置过
            if (dataBindingAttrResolveInfo.tagNode == null) {
                dataBindingAttrResolveInfo.tagNode = groovy.xml.QName(Constants.ANDROID_NAME_SPACE_URI, "tag", Constants.ATTR_PREFIX_ANDROID)
                dataBindingAttrResolveInfo.tagValue = attrValue
            }
            return null
        }
        if (attrFuncInfo == null) {
            //没有实现的attr方法, 但不放入invalidGenInfoMap中
            return null
        }

        val matchResult = attrMethodValueMatcher.match(viewClassName, viewFieldName, layoutName, layoutType, attrFuncInfo, attrValue, fieldInfo, contextName, usedReferenceRMap)
        when (matchResult.matchType) {
            MatchType.FAILED -> {
                return matchResult.ignoreInfo
            }
            else -> codeBlockBuilder.addStatement(matchResult.result)
        }
        var viewTypeGenInfoMap = usedGenInfoMap[viewClassName]
        if (viewTypeGenInfoMap == null) {
            viewTypeGenInfoMap = hashMapOf()
            usedGenInfoMap[viewClassName] = viewTypeGenInfoMap
        }
        viewTypeGenInfoMap.putIfAbsent(attrFuncInfo.attrName, attrFuncInfo)
        return null
    }

    fun makeViewGenResult(layoutName: String, reason: GenResult, info: String, noGenViewClassName: String = "") = ViewGenResultInfo(layoutName, reason, info, noGenViewClassName)

}