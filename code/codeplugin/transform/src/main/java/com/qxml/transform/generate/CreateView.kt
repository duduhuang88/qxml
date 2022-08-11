package com.qxml.transform.generate

import com.qxml.QxmlConfigExtension
import com.qxml.constant.Constants
import com.qxml.constant.ValueType
import com.qxml.tools.AndroidViewNameCorrect
import com.qxml.tools.LayoutTypeNameCorrect
import com.qxml.CompatMode
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.tools.model.CompatViewInfoModel
import com.qxml.transform.collect.ViewGenInfoHolderImpl
import com.qxml.transform.generate.match.AttrMethodValueMatcher
import com.qxml.transform.generate.model.FieldInfo
import com.qxml.transform.generate.model.GenLoopInfo
import com.qxml.transform.generate.model.StyleInfo
import com.qxml.transform.generate.tools.ContextWrapTool
import com.squareup.javapoet.CodeBlock
import groovy.util.Node
import groovy.xml.QName
import java.util.concurrent.ConcurrentHashMap

private val idQName = groovy.xml.QName(Constants.ANDROID_NAME_SPACE_URI, Constants.ATTR_NAME_ID, Constants.ATTR_PREFIX_ANDROID)
private val widthQName = groovy.xml.QName(Constants.ANDROID_NAME_SPACE_URI, Constants.ATTR_NAME_LAYOUT_WIDTH, Constants.ATTR_PREFIX_ANDROID)
private val heightQName = groovy.xml.QName(Constants.ANDROID_NAME_SPACE_URI, Constants.ATTR_NAME_LAYOUT_HEIGHT, Constants.ATTR_PREFIX_ANDROID)
private val nameQName = groovy.xml.QName(Constants.ANDROID_NAME_SPACE_URI, Constants.ATTR_NAME_NAME, Constants.ATTR_PREFIX_ANDROID)
private val tagQName = groovy.xml.QName(Constants.ANDROID_NAME_SPACE_URI, Constants.ATTR_NAME_TAG, Constants.ATTR_PREFIX_ANDROID)

private val qxmlUseFactoryQName = groovy.xml.QName(Constants.RES_AUTO_NAME_SPACE_URI, Constants.QXML_USE_FACTORY_CONFIG_ATTR_NAME)
private val qxmlUseCompatQName = groovy.xml.QName(Constants.RES_AUTO_NAME_SPACE_URI, Constants.QXML_USE_COMPAT_CONFIG_ATTR_NAME)
private val qxmlViewDebugQName = groovy.xml.QName(Constants.RES_AUTO_NAME_SPACE_URI, Constants.QXML_VIEW_DEBUG_CONFIG_ATTR_NAME)
private val qxmlStartQName = groovy.xml.QName(Constants.RES_AUTO_NAME_SPACE_URI, Constants.QXML_START_ATTR_NAME, "app")

interface CreateView: ResolveAttr {


    /**
     * 生成view的内容
     * @param layoutName layout名
     * @param layoutType layout类型， eg: v24 sw250dp
     * @param codeBlockBuilder
     * @param parentViewFieldName 父View变量名
     * @param node
     * @param viewFieldNameFunc 当前View名生成
     * @param genClassFieldCacheMap unUse
     * @param usedOnEndInfoMap 用到的onEnd
     * @param usedGenInfoMap 用到的gen func
     * @param invalidGenInfoMap 没有实现的attr合集
     * @param fieldInfo 单个layout type使用到的共享变量数据
     * @param includeReferenceLayoutNameMap 被include引用到并且尚未生成的layout
     * @param rootIsMerge 当前layout根是否merge
     * @param genLoopInfo 当前生成过程的信息
     * @param rootIsDataBinding 当前layout根是否dataBinding
     * @param dataBindingAttrResolveInfo 当前生成过程的dataBinding信息
     */
    fun methodCreateView(isAndroidx: Boolean, layoutName: String, layoutType: String
                         , codeBlockBuilder: CodeBlock.Builder, parentViewFieldName: String
                         , parentViewClassName: String
                         , node: Node, viewFieldNameFunc: () -> String
                         , genClassFieldCacheMap: HashMap<String, String>
                         , usedOnEndInfoMap: HashMap<String, HashMap<String, AttrFuncInfoModel>>
                         , usedGenInfoMap: HashMap<String, HashMap<String, AttrFuncInfoModel>>
                         , usedStyleInfoMap: HashMap<String, HashMap<String, StyleInfo>>
                         , invalidGenInfoMap: HashMap<String, HashMap<String, Int>>
                         , fieldInfo: FieldInfo
                         , includeReferenceLayoutNameMap: ConcurrentHashMap<String, String>
                         , rootIsMerge: Boolean
                         , qxmlConfig: QxmlConfigExtension
                         , attrMethodValueMatcher: AttrMethodValueMatcher
                         , layoutGenStateMap: ConcurrentHashMap<String, Boolean>
                         , relativeIncludeLayoutMap: HashMap<String, String>
                         , viewGenInfoHolder: ViewGenInfoHolderImpl
                         , compatViewInfoMap: Map<String, CompatViewInfoModel>
                         , styleInfoMap: Map<String, Map<String, StyleInfo>>
                         , usedReferenceRMap: HashMap<String, Int>
                         , usedImportPackageMap: HashMap<String, String>
                         , finalUsedLocalVarMap: HashMap<String, String>
                         , idMap: Map<String, Int>
                         , usedTempVarMap: HashMap<String, String>
                         , parentThemeItemMap: Map<String, String> = hashMapOf()
                         , parentContextName: String = Constants.GEN_PARAM_CONTEXT_NAME
                         , genLoopInfo: GenLoopInfo = GenLoopInfo()
                         , rootIsDataBinding: Boolean = node.name().toString().equals(Constants.DATA_BINDING_LAYOUT, true)
                         , dataBindingAttrResolveInfo: DataBindingAttrResolveInfo = DataBindingAttrResolveInfo(0)
    ): ViewGenResultInfo? {
        var originName = node.name().toString()
        dataBindingAttrResolveInfo.clearDataBinding()
        if (originName == Constants.DATA_BINDING_DATA) {
            return null
        }

        val nextNodeParentThemeItemMap = hashMapOf<String, String>().apply { putAll(parentThemeItemMap) }
        val curNodeIsDataBindingLayoutLabel = originName == Constants.DATA_BINDING_LAYOUT
        val curNodeIsMergeLabel = originName == Constants.TAG_MERGE
        val curNodeIsFragment = originName == Constants.TAG_FRAGMENT
        var viewFieldName = parentViewFieldName
        var nextParentClassName = parentViewFieldName
        var nextNodeParentContextName = parentContextName
        var viewClassName = ""
        if (!curNodeIsDataBindingLayoutLabel && !curNodeIsMergeLabel) {
            val isInclude: Boolean
            var includeViewCreateBloc = ""
            if (originName == Constants.TAG_INCLUDE) { //include特殊处理
                originName = Constants.ANDROID_VIEW_CLASS_SIMPLE_NAME //include是一个普通view
                isInclude = true
                val attributes = node.attributes()
                val layoutIdStr = attributes[Constants.LAYOUT_LAYOUT] as? String ?: return null
                val valueInfo = attrMethodValueMatcher.getValueInfo("", layoutIdStr, usedReferenceRMap, idMap)
                if (valueInfo.valueType != ValueType.REFERENCE_LAYOUT) {//只处理layout
                    return null
                }
                val includeLayoutFullName = valueInfo.value
                val includeLayoutName = includeLayoutFullName.substring(includeLayoutFullName.lastIndexOf(".") + 1)
                val includeLayoutGenResult = layoutGenStateMap[includeLayoutName]
                relativeIncludeLayoutMap.putIfAbsent(includeLayoutName, "")
                if (includeLayoutGenResult == null) {
                    //依赖的include布局还没gen结果,留到下次生成
                    includeReferenceLayoutNameMap[layoutName] = includeLayoutName
                    return makeViewGenResult(layoutName, GenResult.WAIT_INCLUDE, "")
                } else {
                    includeViewCreateBloc = "${makeGenClassName(includeLayoutName)}.${Constants.GENERATE_METHOD_NAME}(${Constants.GEN_PARAM_INFLATE_NAME}, ${Constants.GEN_PARAM_CONTEXT_NAME}, ${parentViewFieldName}, true)"
                }
            } else {
                isInclude = false
            }

            if (curNodeIsFragment) {
                originName = Constants.ANDROID_VIEW_CLASS_SIMPLE_NAME //fragment是一个普通view
            }

            viewClassName = AndroidViewNameCorrect.correct(originName)
            var viewGenInfoMap = viewGenInfoHolder.getAttrMethodInfoMap(viewClassName)
                ?: return makeViewGenResult(layoutName, GenResult.NO_GEN_CLASS, "找不到${viewClassName}的实现类", viewClassName)

            viewFieldName = viewFieldNameFunc()

            nextParentClassName = viewClassName

            viewGenInfoHolder.getImportPackage(viewClassName)?.let {
                usedImportPackageMap.putAll(it)
            }

            val attributes = node.attributes()

            //处理fragment
            if (curNodeIsFragment) {
                if (isAndroidx) {
                    return makeViewGenResult(layoutName, GenResult.ANDROIDX_FRAGMENT_UN_SUPPORT, "Androidx项目暂不支持在layout文件中使用fragment标签")
                }
                var fragmentClassName = attributes[Constants.LAYOUT_CLASS] as? String ?: ""
                if (fragmentClassName.isEmpty()) {
                    fragmentClassName = attributes[nameQName]?.toString() ?: return null
                }
                val idQName = attributes[idQName]
                var idValue = "-1"
                var tagValue = attributes[tagQName]?.toString()
                if (tagValue == null) {
                    tagValue = "null"
                } else {
                    tagValue = "\"$tagValue\""
                }
                if (idQName != null) {
                    idValue = attrMethodValueMatcher.getValueInfo("", idQName.toString(), usedReferenceRMap, idMap).value
                }
                codeBlockBuilder.addStatement("${Constants.ANDROID_VIEW_CLASS_NAME} $viewFieldName = null")
                codeBlockBuilder.beginControlFlow("")
                val fragmentName = fragmentClassName.replace(".", "_")
                val parentViewGroup = if (parentViewFieldName.isNotEmpty()) parentViewFieldName else {
                    "${Constants.GEN_PARAM_ATTACH_TO_NAME} ? ${Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME} : null"
                }
                codeBlockBuilder.addStatement("$fragmentClassName $fragmentName = new $fragmentClassName()")
                codeBlockBuilder.addStatement("${if (isAndroidx) Constants.ANDROIDX_FRAGMENT_HELPER else Constants.SUPPORT_FRAGMENT_HELPER}.add(${Constants.GEN_PARAM_CONTEXT_NAME}, $fragmentName, $idValue, $tagValue, $parentViewGroup)")
                codeBlockBuilder.addStatement("$viewFieldName = $fragmentName.getView()")
                codeBlockBuilder.endControlFlow()
            }

            if (genLoopInfo.viewCount == 0) {
                attributes[qxmlUseFactoryQName]?.also {
                    qxmlConfig.useFactory = it.toString().equals("true", true)
                }
                attributes[qxmlUseCompatQName]?.also {
                    qxmlConfig.compatMode = when(it.toString()) {
                        "never" -> CompatMode.NEVER
                        "auto" -> CompatMode.AUTO
                        else -> CompatMode.FORCE
                    }
                }
                attributes[qxmlViewDebugQName]?.also {
                    qxmlConfig.viewDebug = it.toString().equals("true", true)
                }
            }

            var styleIsReference = false
            var styleRName = ""
            var styleName = ""
            val styleAttr = attributes[Constants.LAYOUT_STYLE]

            var themeName: Any? = null
            run loopBreak@{
                attributes.forEach { (name, value) ->
                    (name as? groovy.xml.QName)?.also { qName ->
                        if (qName.qualifiedName == Constants.LAYOUT_THEME) {
                            themeName = value
                            return@loopBreak
                        }
                    }
                }
            }
            var contextName = parentContextName
            var themeStyleInfo: StyleInfo? = null

            if (themeName != null) {
                val themeValueInfo = attrMethodValueMatcher.getValueInfo("", themeName.toString(), usedReferenceRMap, idMap)
                val themeWrapField = ContextWrapTool.makeContextField(themeValueInfo.value, themeValueInfo.valueType == ValueType.REFERENCE_ATTR)

                if (themeValueInfo.valueType == ValueType.REFERENCE_ATTR && !qxmlConfig.acceptReferenceStyle) {
                    return makeViewGenResult(layoutName, GenResult.REFERENCE_STYLE_UN_SUPPORT, "暂不支持style引用(${themeName})")
                }

                fieldInfo.contextThemeWrapMap.putIfAbsent(themeWrapField, if (themeValueInfo.valueType == ValueType.REFERENCE_ATTR) ContextWrapTool.makeContextValueByReference(themeValueInfo.value, parentContextName) else ContextWrapTool.makeContextValue(themeValueInfo.value, parentContextName))
                nextNodeParentContextName = themeWrapField
                contextName = themeWrapField

                //只支持直接的theme引用
                if (themeValueInfo.valueType != ValueType.REFERENCE_ATTR) {
                    themeStyleInfo = styleInfoMap[layoutType]?.get(themeValueInfo.referenceSourceValue)
                    themeStyleInfo?.itemMap?.forEach { n, v ->
                        nextNodeParentThemeItemMap[n] = v
                    }
                }
            }

            if (styleAttr != null && !isInclude && !curNodeIsFragment) {//include 忽略 style
                val valueInfo = attrMethodValueMatcher.getValueInfo("", styleAttr.toString(), usedReferenceRMap, idMap)
                styleIsReference = valueInfo.valueType == ValueType.REFERENCE_ATTR
                if (styleIsReference) {
                    val contextThemeWrapField = ContextWrapTool.makeContextField(valueInfo.value, true)
                    //fieldInfo.contextThemeWrapMap.putIfAbsent(contextThemeWrapField, if (styleIsReference) ContextWrapTool.makeContextValueByReference(valueInfo.value) else ContextWrapTool.makeContextValue(valueInfo.value))
                    fieldInfo.contextThemeWrapMap.putIfAbsent(contextThemeWrapField, ContextWrapTool.makeContextValueByReference(valueInfo.value, parentContextName))
                    contextName = contextThemeWrapField
                }
                styleName = valueInfo.referenceSourceValue
                styleRName = valueInfo.value
            }

            if (styleIsReference && !qxmlConfig.acceptReferenceStyle) {
                return makeViewGenResult(layoutName, GenResult.REFERENCE_STYLE_UN_SUPPORT, "暂不支持style引用(${styleName})")
            }

            if (!isInclude) {
                var useCompat = false
                if (qxmlConfig.compatMode != CompatMode.NEVER && !curNodeIsFragment) {
                    if (qxmlConfig.compatMode == CompatMode.FORCE) {
                        useCompat = true
                    } else {
                        val compatViewInfoModel = compatViewInfoMap[viewClassName]
                        if (compatViewInfoModel != null) {
                            if (compatViewInfoModel.compatCondition.isNullOrEmpty()) {
                                useCompat = true
                            } else {
                                val compatConditionMap = hashMapOf<String, String>().apply {
                                    compatViewInfoModel.compatCondition.forEach { condition ->
                                        put(condition, "")
                                    }
                                }
                                run attrBreak@{
                                    attributes.forEach attrContinue@{ attrs ->
                                        if (attrs.key is String) {
                                            return@attrContinue
                                        }
                                        val nameNode = attrs.key as groovy.xml.QName
                                        val finalAttrName = if (nameNode.prefix == Constants.ATTR_PREFIX_ANDROID) nameNode.qualifiedName else nameNode.localPart
                                        compatConditionMap[finalAttrName]?.also {
                                            useCompat = true
                                            return@attrBreak
                                        }
                                    }
                                }
                                if (!useCompat) {
                                    if (styleName.isNotEmpty()) {
                                        if (styleIsReference) {
                                            //style引用时使用compat
                                            useCompat = true
                                        } else {
                                            val styleTypeMap = styleInfoMap[layoutType]
                                            if (!styleTypeMap.isNullOrEmpty()) {
                                                styleTypeMap[styleName]?.also { styleInfo ->
                                                    val itemMap = styleInfo.itemMap
                                                    for (entry in itemMap) {
                                                        val itemName = entry.key
                                                        if (compatConditionMap[itemName] != null) {
                                                            useCompat = true
                                                            break
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (useCompat) {
                    viewClassName = compatViewInfoMap[viewClassName]?.viewClassName ?: viewClassName
                    nextParentClassName = viewClassName
                    viewGenInfoMap = viewGenInfoHolder.getAttrMethodInfoMap(viewClassName)
                        ?: return makeViewGenResult(layoutName, GenResult.NO_GEN_CLASS, "找不到${viewClassName}的实现类", viewClassName)
                }

                //fragment已经在前面添加
                if (!curNodeIsFragment) {

                    //添加view的初始化语句，放到开头统一创建
                    val stringBuilder = java.lang.StringBuilder()
                    if (qxmlConfig.useFactory) {
                        //stringBuilder.append("$viewClassName $viewFieldName = ((${viewClassName})${Constants.QXML_INFLATER_CLASS_NAME}.createView(${Constants.GEN_PARAM_INFLATE_NAME}, \"${originName}\", ${contextName}));\n")

                        stringBuilder.append("$viewClassName $viewFieldName = null;\n")
                        stringBuilder.append("if (${Constants.GEN_FIELD_FACTORY_2} != null) {  $viewFieldName = ((${viewClassName}) ${Constants.GEN_FIELD_FACTORY_2}.onCreateView(null, \"${originName}\", ${contextName}, null)); }\n")
                        stringBuilder.append("else if (${Constants.GEN_FIELD_FACTORY} != null) {  $viewFieldName = ((${viewClassName}) ${Constants.GEN_FIELD_FACTORY}.onCreateView(\"${originName}\", ${contextName}, null)); }\n")
                        stringBuilder.append("else if (${Constants.GEN_FIELD_CUR_CONTEXT_ACT} != null) {  $viewFieldName = ((${viewClassName}) ${Constants.GEN_FIELD_CUR_CONTEXT_ACT}.onCreateView(null, \"${originName}\", ${contextName}, null)); }\n")
                        stringBuilder.append("if ($viewFieldName == null) {\n")
                    }

                    stringBuilder.append("${if (qxmlConfig.useFactory) "" else "$viewClassName "}$viewFieldName = new ${viewClassName}($contextName, (android.util.AttributeSet) null${if (styleIsReference && styleRName.isNotEmpty()) ", $styleRName" else ""})")

                    if (qxmlConfig.useFactory) {
                        stringBuilder.append(";\n}\n")
                    }

                    if (qxmlConfig.useCreateViewListener) {
                        fieldInfo.createViewInfoList.add(CreateViewInfo(viewFieldName, contextName, parentViewFieldName, viewClassName, originName))
                    }

                    var newViewList = fieldInfo.newViewMap[viewClassName]
                    if (newViewList == null) {
                        newViewList = mutableListOf()
                        fieldInfo.newViewMap[viewClassName] = newViewList
                    }
                    newViewList.add(stringBuilder.toString())
                }

                //设置dataBinding的tag
                if (rootIsDataBinding && genLoopInfo.viewCount == 0) {
                    codeBlockBuilder.addStatement("${viewFieldName}.setTag(\"${LayoutTypeNameCorrect.toDisplayText(layoutType)}/${layoutName}_${dataBindingAttrResolveInfo.dataBindingCount}\")")
                    dataBindingAttrResolveInfo.dataBindingCount++
                }

                if (rootIsMerge) {
                    if (genLoopInfo.loopLevel == 0) {//merge第一层
                        codeBlockBuilder.addStatement(getLayoutParam(Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME, parentViewClassName, viewFieldName, viewGenInfoHolder))
                    } else {
                        codeBlockBuilder.addStatement(getLayoutParam(parentViewFieldName, parentViewClassName, viewFieldName, viewGenInfoHolder))
                    }
                } else {
                    if (parentViewFieldName.isNotEmpty()) {
                        codeBlockBuilder.addStatement(getLayoutParam(parentViewFieldName, parentViewClassName, viewFieldName, viewGenInfoHolder))
                    } else {//第一层的parentViewName为""
                        codeBlockBuilder.beginControlFlow("if (${Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME} != null)")

                        codeBlockBuilder.addStatement(getLayoutParam(Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME, parentViewClassName, viewFieldName, viewGenInfoHolder))
                        codeBlockBuilder.beginControlFlow("if (!${Constants.GEN_PARAM_ATTACH_TO_NAME})")

                        codeBlockBuilder.addStatement("$viewFieldName.setLayoutParams(${Constants.GEN_FIELD_LAYOUT_PARAMS_ROOT})")

                        codeBlockBuilder.endControlFlow()

                        codeBlockBuilder.endControlFlow()

                        /*codeBlockBuilder.beginControlFlow("if (${Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME} != null && ${Constants.GEN_PARAM_ATTACH_TO_NAME})")
                        codeBlockBuilder.addStatement(getLayoutParam(Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME, parentViewClassName, viewFieldName, viewGenInfoHolder))
                        codeBlockBuilder.endControlFlow()*/
                    }
                }
            } else { // include 不需要 new view
                codeBlockBuilder.addStatement("$viewClassName $viewFieldName = $includeViewCreateBloc")
            }

            /*if (genLoopInfo.viewCount == 0) {
                codeBlockBuilder.addStatement(viewGenInfoHolder.localVarDefContent())
            } else {
                codeBlockBuilder.addStatement(viewGenInfoHolder.localVarResetContent())
            }*/

            val methodCodeBlockBuilder = CodeBlock.builder()
            val usedLocalVarMap = hashMapOf<String, String>()

            if (isInclude) {
                //如果include的layout是merge会返回空，忽略属性设置
                methodCodeBlockBuilder.beginControlFlow("if($viewFieldName != null)")
                //如果不为空，则取最后一个子View
                methodCodeBlockBuilder.addStatement("$viewFieldName = ${parentViewFieldName}.getChildAt(${parentViewFieldName}.getChildCount() - 1)")
                methodCodeBlockBuilder.addStatement("${Constants.GEN_FIELD_LAYOUT_PARAMS} = $viewFieldName.getLayoutParams()")

                val idValue = attributes[idQName]
                if (idValue != null) {
                    //设置id
                    resolveAttr(idQName, idValue as String, genLoopInfo.viewCount
                        , layoutName, layoutType, contextName
                        , rootIsDataBinding, viewGenInfoMap, invalidGenInfoMap
                        , viewClassName, viewFieldName, methodCodeBlockBuilder, qxmlConfig
                        , usedGenInfoMap, fieldInfo, dataBindingAttrResolveInfo, attrMethodValueMatcher
                        , usedLocalVarMap, finalUsedLocalVarMap, usedReferenceRMap, idMap, usedTempVarMap)?.also {
                        return it
                    }
                    attributes.remove(idQName)
                }
                if (attributes[widthQName] == null || attributes[heightQName] == null) {
                    //layout_width与layout_height没有同时存在则忽略其他属性
                    methodCodeBlockBuilder.endControlFlow()
                    codeBlockBuilder.addStatement(viewGenInfoHolder.localVarResetContent(usedLocalVarMap))
                    codeBlockBuilder.add(methodCodeBlockBuilder.build())
                    return null
                }
            }

            val useAttrs = hashMapOf<String, Boolean>()

            val sortedAttrQNameList = mutableListOf<groovy.xml.QName>()
            val sortedAttrValueList = mutableListOf<String>()

            val attributesMap = mutableMapOf<String, Map.Entry<Any?, Any?>>()
            attributes.forEach attrContinue@{ attrs ->
                if (attrs.key is String) {
                    return@attrContinue
                }
                val nameNode = attrs.key as groovy.xml.QName
                val finalAttrName = if (nameNode.prefix == Constants.ATTR_PREFIX_ANDROID) nameNode.qualifiedName else nameNode.localPart
                if (finalAttrName != Constants.QXML_START_ATTR_NAME) {
                    attributesMap[finalAttrName] = attrs
                }
            }

            //每次都调用
            sortedAttrQNameList.add(qxmlStartQName)
            sortedAttrValueList.add("false")

            viewGenInfoMap.forEach { (attrName, _) ->
                attributesMap[attrName]?.let {
                    val nameNode = it.key as groovy.xml.QName
                    val attrValue = it.value as String
                    sortedAttrQNameList.add(nameNode)
                    sortedAttrValueList.add(attrValue)
                }
            }

            val attrSize = sortedAttrQNameList.size
            var preRequiredCondition = ""
            sortedAttrQNameList.forEachIndexed { index, qName ->
                val attrValue = sortedAttrValueList[index]
                val finalAttrName = if (qName.prefix == Constants.ATTR_PREFIX_ANDROID) qName.qualifiedName else qName.localPart

                val attrFucInfo = viewGenInfoMap[finalAttrName]

                val curRequiredCondition = attrFucInfo?.requiredCondition ?: ""

                val nextQName = if (index + 1 < attrSize) sortedAttrQNameList[index + 1] else null
                val nextFinalAttrName = if (nextQName == null) null else if (nextQName.prefix == Constants.ATTR_PREFIX_ANDROID) nextQName.qualifiedName else nextQName.localPart
                val nextAttrFucInfo = if (nextFinalAttrName == null) null else viewGenInfoMap[nextFinalAttrName]
                val nextRequiredCondition = nextAttrFucInfo?.requiredCondition ?: ""

                resolveAttr(qName, attrValue, genLoopInfo.viewCount
                    , layoutName, layoutType, contextName
                    , rootIsDataBinding, viewGenInfoMap, invalidGenInfoMap
                    , viewClassName, viewFieldName, methodCodeBlockBuilder, qxmlConfig
                    , usedGenInfoMap, fieldInfo, dataBindingAttrResolveInfo, attrMethodValueMatcher
                    , usedLocalVarMap, finalUsedLocalVarMap, usedReferenceRMap, idMap, usedTempVarMap
                    , false, preRequiredCondition, curRequiredCondition, nextRequiredCondition)?.also {
                    return it
                }
                if (Constants.ATTR_TAG != finalAttrName) {//tag放在后面
                    useAttrs[finalAttrName] = true
                }
                preRequiredCondition = curRequiredCondition
            }

            //设置style的attr
            if (styleName.isNotEmpty() && !curNodeIsFragment) {
                //暂不支持style引用资源里的位置属性
                if (styleIsReference) {
                    //如果是引用，则尝试所有可能的style，会增加生成的代码量
                    /*val mayUseStyleNameValueMap = mutableMapOf<String, Boolean>()
                    val referenceId = ContextWrapTool.makeContextReferenceResIdField(styleRName)
                    var index = 0
                    styleInfoMap[layoutType]?.forEach infoContinue@{ (_, styleInfo) ->
                        styleInfo.itemMap.forEach { (item, value) ->
                            if (item == styleName) {
                                //禁止套娃
                                if (!value.startsWith("?")) {
                                    mayUseStyleNameValueMap.putIfAbsent(value, true)
                                    return@infoContinue
                                }
                            }
                        }
                    }
                    mayUseStyleNameValueMap.forEach { (styleValue, _) ->
                        val styleValueInfo = attrMethodValueMatcher.getValueInfo("", styleValue)
                        codeBlockBuilder.beginControlFlow((if (index == 0) "if" else "else if") + "($referenceId == ${styleValueInfo.value})")

                        val styleInfo = styleInfoMap[layoutType]?.get(styleValueInfo.referenceSourceValue)
                        val itemMap = styleInfo?.itemMap
                        *//*if (styleInfo != null) {
                            usedStyleInfoMap.putIfAbsent(styleName, styleInfo)
                        }*//*
                        if (itemMap != null) {
                            for (entry in itemMap) {
                                val itemName = entry.key
                                val value = entry.value
                                //未被覆盖
                                if (useAttrs[itemName] == null && viewGenInfoHolder.shouldStyleAttrReset(itemName)) {
                                    resolveStyleAttr(itemName, value, genLoopInfo.viewCount
                                        , layoutName, layoutType, contextName
                                        , rootIsDataBinding, viewGenInfoMap, invalidGenInfoMap
                                        , viewClassName, viewFieldName, codeBlockBuilder, qxmlConfig
                                        , usedGenInfoMap, fieldInfo, dataBindingAttrResolveInfo, attrMethodValueMatcher)?.also {
                                        return it
                                    }
                                    if (Constants.ATTR_TAG != itemName) {//tag放在后面
                                        useAttrs[itemName] = true
                                    }
                                }
                            }
                        }
                        codeBlockBuilder.endControlFlow()
                        index++
                    }*/
                } else {
                    val styleTypeMap = styleInfoMap[layoutType]
                    if (!styleTypeMap.isNullOrEmpty()) {
                        val usingStyleInfo = styleTypeMap[styleName]
                        if (usingStyleInfo != null) {
                            usingStyleInfo.also { styleInfo ->
                                resolveStyleAttrAndSetUsedStyle(styleInfo.itemMap, useAttrs, viewGenInfoHolder, genLoopInfo, layoutName
                                    , layoutType, contextName, rootIsDataBinding, viewGenInfoMap, invalidGenInfoMap
                                    , fieldInfo, qxmlConfig, attrMethodValueMatcher, viewClassName, viewFieldName
                                    , methodCodeBlockBuilder, usedGenInfoMap, dataBindingAttrResolveInfo, styleName
                                    , layoutType, styleInfo, usedStyleInfoMap, false, usedReferenceRMap
                                    , usedLocalVarMap, finalUsedLocalVarMap, idMap, usedTempVarMap)?.also { errInfo ->
                                    return errInfo
                                }
                            }
                        } else {
                            return makeViewGenResult(layoutName, GenResult.STYLE_UN_SUPPORT, "不支持的style(${styleName})")
                        }
                    }
                }
            }

            //设置theme
            if (parentThemeItemMap.isNotEmpty() && !curNodeIsFragment) {
                resolveStyleAttrAndSetUsedStyle(parentThemeItemMap, useAttrs, viewGenInfoHolder, genLoopInfo, layoutName
                    , layoutType, contextName, rootIsDataBinding, viewGenInfoMap, invalidGenInfoMap
                    , fieldInfo, qxmlConfig, attrMethodValueMatcher, viewClassName, viewFieldName
                    , methodCodeBlockBuilder, usedGenInfoMap, dataBindingAttrResolveInfo, styleName
                    , layoutType, themeStyleInfo, usedStyleInfoMap, true, usedReferenceRMap
                    , usedLocalVarMap, finalUsedLocalVarMap, idMap, usedTempVarMap)?.also { errInfo ->
                    return errInfo
                }
            }

            //如果不是dataBinding，设置tag
            if (!dataBindingAttrResolveInfo.useDataBinding && dataBindingAttrResolveInfo.tagValue != null && dataBindingAttrResolveInfo.tagNode != null) {
                resolveAttr(dataBindingAttrResolveInfo.tagNode!!, dataBindingAttrResolveInfo.tagValue!!, genLoopInfo.viewCount
                    , layoutName, layoutType, contextName
                    , rootIsDataBinding, viewGenInfoMap, invalidGenInfoMap
                    , viewClassName, viewFieldName, methodCodeBlockBuilder, qxmlConfig
                    , usedGenInfoMap, fieldInfo, dataBindingAttrResolveInfo, attrMethodValueMatcher
                    , usedLocalVarMap, finalUsedLocalVarMap, usedReferenceRMap, idMap, usedTempVarMap)?.also {
                    return it
                }
                useAttrs[dataBindingAttrResolveInfo.tagNode!!.qualifiedName] = true
            }

            val onEndAttrBeforeAddList = mutableListOf<AttrFuncInfoModel>()
            val onEndAttrAfterAddList = mutableListOf<AttrFuncInfoModel>()

            viewGenInfoHolder.getOnEndMethodInfoMap(viewClassName)?.also { onEndMethodMap ->
                onEndMethodMap.forEach onEndContinue@{ (funcSign, attrFuncInfoModel) ->
                    if (attrFuncInfoModel.onEndCondition == null || attrFuncInfoModel.onEndCondition.isEmpty()) {
                        if (attrFuncInfoModel.isAfterAdd) {
                            onEndAttrAfterAddList.add(attrFuncInfoModel)
                        } else {
                            onEndAttrBeforeAddList.add(attrFuncInfoModel)
                        }
                        return@onEndContinue
                    } else {
                        attrFuncInfoModel.onEndCondition.forEach {
                            if (useAttrs[it] != null) {
                                if (attrFuncInfoModel.isAfterAdd) {
                                    onEndAttrAfterAddList.add(attrFuncInfoModel)
                                } else {
                                    onEndAttrBeforeAddList.add(attrFuncInfoModel)
                                }
                                return@onEndContinue
                            }
                        }
                    }
                }
            }

            preRequiredCondition = ""
            for (index in 0 until onEndAttrBeforeAddList.size) {
                val curOnEndAttr = onEndAttrBeforeAddList[index]
                val nextOnEndAttr = if (index + 1 < onEndAttrBeforeAddList.size) onEndAttrBeforeAddList[index + 1] else null
                val curRequiredCondition = curOnEndAttr.requiredCondition ?: ""
                val nextRequiredCondition = nextOnEndAttr?.requiredCondition ?: ""
                callOnEndMethod(curOnEndAttr, viewFieldName, methodCodeBlockBuilder, usedOnEndInfoMap, viewClassName, curOnEndAttr.funcSignInfo, usedLocalVarMap, finalUsedLocalVarMap, preRequiredCondition, curRequiredCondition, nextRequiredCondition)
                preRequiredCondition = curRequiredCondition
            }

            if (isInclude) {
                methodCodeBlockBuilder.endControlFlow()
            }

            if (!isInclude) {
                if (rootIsMerge) {
                    if (genLoopInfo.loopLevel == 0) {//merge第一层
                        methodCodeBlockBuilder.addStatement("${Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME}.addView($viewFieldName, -1, ${Constants.GEN_FIELD_LAYOUT_PARAMS})")
                    } else {
                        methodCodeBlockBuilder.addStatement("$parentViewFieldName.addView($viewFieldName, -1, ${Constants.GEN_FIELD_LAYOUT_PARAMS})")
                    }
                } else {
                    if (parentViewFieldName.isNotEmpty()) {
                        methodCodeBlockBuilder.addStatement("$parentViewFieldName.addView($viewFieldName, -1, ${Constants.GEN_FIELD_LAYOUT_PARAMS})")
                    } else {//第一层的parentViewName为""
                        //__view_0最后再添加到rootView
                    }
                }
            }

            onEndAttrAfterAddList.forEach {
                callOnEndMethod(it, viewFieldName, methodCodeBlockBuilder, usedOnEndInfoMap, viewClassName, it.funcSignInfo, usedLocalVarMap, finalUsedLocalVarMap)
            }

            if (genLoopInfo.viewCount == 0) {
                //codeBlockBuilder.addStatement(viewGenInfoHolder.localVarDefContent())
            } else {
                codeBlockBuilder.addStatement(viewGenInfoHolder.localVarResetContent(usedLocalVarMap))
            }
            genLoopInfo.viewCountUp()
            codeBlockBuilder.add(methodCodeBlockBuilder.build())
        }

        val shouldLevelUp = !curNodeIsDataBindingLayoutLabel && !curNodeIsMergeLabel
        if (shouldLevelUp) {
            genLoopInfo.loopLevelUp()
        }
        node.children().forEach {
            (it as? Node)?.also { childNode ->
                methodCreateView(isAndroidx, layoutName, layoutType, codeBlockBuilder, viewFieldName, nextParentClassName, childNode
                    , viewFieldNameFunc, genClassFieldCacheMap, usedOnEndInfoMap, usedGenInfoMap
                    , usedStyleInfoMap, invalidGenInfoMap, fieldInfo, includeReferenceLayoutNameMap
                    , rootIsMerge, qxmlConfig, attrMethodValueMatcher, layoutGenStateMap, relativeIncludeLayoutMap
                    , viewGenInfoHolder, compatViewInfoMap, styleInfoMap, usedReferenceRMap, usedImportPackageMap
                    , finalUsedLocalVarMap, idMap, usedTempVarMap, nextNodeParentThemeItemMap, nextNodeParentContextName, genLoopInfo
                    , rootIsDataBinding, dataBindingAttrResolveInfo)?.let { ignoreInfo ->
                    return ignoreInfo
                }
            }
        }
        if (viewClassName.isNotEmpty() && viewGenInfoHolder.shouldCallOnFinishInflate(viewClassName)) {
            codeBlockBuilder.addStatement("${Constants.QXML_INFLATER_CLASS_NAME}.callViewOnFinishInflate(${viewFieldName})")
        }
        if (shouldLevelUp) {
            genLoopInfo.loopLevelDown()
        }
        return null
    }

    fun makeGenClassName(layoutName: String) = Constants.GEN_CLASS_PATH_PREFIX + layoutName

    //设置style和theme的属性，只支持非framework引用的style
    private fun resolveStyleAttrAndSetUsedStyle(itemMap: Map<String, String>, useAttrs: HashMap<String, Boolean>
                                 , viewGenInfoHolder: ViewGenInfoHolderImpl, genLoopInfo: GenLoopInfo
                                 , layoutName: String, layoutType: String, contextName: String
                                 , rootIsDataBinding: Boolean, viewGenInfoMap: Map<String, AttrFuncInfoModel>
                                 , invalidGenInfoMap: HashMap<String, HashMap<String, Int>>
                                 , fieldInfo: FieldInfo
                                 , qxmlConfig: QxmlConfigExtension
                                 , attrMethodValueMatcher: AttrMethodValueMatcher, viewClassName: String, viewFieldName: String
                                 , codeBlockBuilder: CodeBlock.Builder
                                 , usedGenInfoMap: HashMap<String, HashMap<String, AttrFuncInfoModel>>
                                 , dataBindingAttrResolveInfo: DataBindingAttrResolveInfo, styleName: String
                                 , finalType: String, styleInfo: StyleInfo?
                                 , usedStyleInfoMap: HashMap<String, HashMap<String, StyleInfo>>
                                 , isTheme: Boolean, usedReferenceRMap: HashMap<String, Int>
                                 , usedLocalVarMap: HashMap<String, String>
                                 , finalUsedLocalVarMap: HashMap<String, String>
                                 , idMap: Map<String, Int>
                                 , usedTempVarMap: HashMap<String, String>): ViewGenResultInfo?  {
        for (entry in itemMap) {
            val itemName = entry.key
            val value = entry.value
            //未被覆盖
            if (useAttrs[itemName] == null) {
                //theme属性的设置过滤其他非位置属性，因为已经设置过了
                //style时会全部设置所有未设置过的属性
                if (isTheme && !viewGenInfoHolder.shouldStyleAttrReset(itemName)) {
                    continue
                }
                resolveStyleAttr(itemName, value, genLoopInfo.viewCount
                    , layoutName, layoutType, contextName
                    , rootIsDataBinding, viewGenInfoMap, invalidGenInfoMap
                    , viewClassName, viewFieldName, codeBlockBuilder, qxmlConfig
                    , usedGenInfoMap, fieldInfo, dataBindingAttrResolveInfo, attrMethodValueMatcher, usedReferenceRMap
                    , usedLocalVarMap, finalUsedLocalVarMap, idMap, usedTempVarMap)?.also {
                    return it
                }
                if (Constants.ATTR_TAG != itemName) {//tag放在后面
                    useAttrs[itemName] = true
                }
            }
        }
        if (styleInfo != null && styleName.isNotEmpty()) {
            var styleTypeMap = usedStyleInfoMap[styleName]
            if (styleTypeMap == null) {
                styleTypeMap = hashMapOf()
                usedStyleInfoMap[styleName] = styleTypeMap
            }
            styleTypeMap[finalType] = styleInfo
        }
        return null
    }

    private fun callOnEndMethod(attrFuncInfoModel: AttrFuncInfoModel, viewFieldName: String, codeBlockBuilder: CodeBlock.Builder
                                , usedOnEndInfoMap: HashMap<String, HashMap<String, AttrFuncInfoModel>>, viewClassName: String
                                , funcSign: String, usedLocalVarMap: HashMap<String, String>, finalUsedLocalVarMap: HashMap<String, String>
                                , preRequiredCondition: String = "", curRequiredCondition: String = "", nextRequiredCondition: String = "") {
        val shouldUseRequiredCondition = curRequiredCondition.isNotEmpty() && curRequiredCondition != preRequiredCondition
        if (shouldUseRequiredCondition) {
            codeBlockBuilder.beginControlFlow("if (${curRequiredCondition})")
        }
        val funcBody = attrFuncInfoModel.funcBodyContent.substring(3).replace(Constants.VIEW_PARAM_NAME_TEMP, viewFieldName)
        val stringBuilder = StringBuilder()
        //stringBuilder.append("{")
        //stringBuilder.append("${attrFuncInfoModel.viewParamType} ${attrFuncInfoModel.viewParamName} = $viewFieldName;\n")
        stringBuilder.append(funcBody.substring(0, funcBody.length - 1))//.append("\n")
        codeBlockBuilder.add(stringBuilder.toString())
        if (curRequiredCondition.isNotEmpty() && curRequiredCondition != nextRequiredCondition) {
            codeBlockBuilder.endControlFlow()
        }
        var viewTypeOnEndInfoMap = usedOnEndInfoMap[viewClassName]
        if (viewTypeOnEndInfoMap == null) {
            viewTypeOnEndInfoMap = hashMapOf()
            usedOnEndInfoMap[viewClassName] = viewTypeOnEndInfoMap
        }
        viewTypeOnEndInfoMap.putIfAbsent(funcSign, attrFuncInfoModel)
        attrFuncInfoModel.usedLocalVarMap?.forEach { (fullName, _) ->
            usedLocalVarMap.putIfAbsent(fullName, "")
        }
        finalUsedLocalVarMap.putAll(usedLocalVarMap)
    }

    private fun getLayoutParam(parentView: String, parentViewClassName: String, viewFieldName: String
                               , viewGenInfoHolder: ViewGenInfoHolderImpl): String {
        viewGenInfoHolder.getLayoutParamInitBloc(parentViewClassName)?.also {
            return "${Constants.GEN_FIELD_LAYOUT_PARAMS} = $it"
        }
        return if (parentViewClassName.isNotEmpty()) {
            "${Constants.GEN_FIELD_LAYOUT_PARAMS} = com.qxml.tools.QxmlViewTools.generateDefaultLayoutParams($parentView)"
        } else {
            //第一个View的lp
            "${Constants.GEN_FIELD_LAYOUT_PARAMS_ROOT} = com.qxml.tools.QxmlViewTools.generateDefaultLayoutParams($parentView);"+"\n${Constants.GEN_FIELD_LAYOUT_PARAMS} = ${Constants.GEN_FIELD_LAYOUT_PARAMS_ROOT};"
        }
        //"\nif(${Constants.GEN_FIELD_LAYOUT_PARAMS} != null) {${viewFieldName}.setLayoutParams(${Constants.GEN_FIELD_LAYOUT_PARAMS});}"
    }
}

data class CreateViewInfo(val viewFieldName: String, val contextName: String, val parentViewName: String, val viewClassName: String, val originViewClassName: String)