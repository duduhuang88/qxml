package com.qxml.transform.collect

import com.android.ide.common.internal.WaitableExecutor
import com.google.common.io.Files
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.qxml.QxmlConfigExtension
import com.qxml.tools.log.LogUtil
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.tools.model.GenClassInfoModel
import com.qxml.tools.model.LocalVarInfoModel
import com.qxml.tools.model.ViewGenClassModel
import com.qxml.transform.AttrInfoModel
import com.qxml.transform.collect.reset_ttr.*
import com.qxml.transform.generate.model.GenerateClassInfo
import com.qxml.transform.generate.model.StyleInfo
import java.io.File
import java.lang.StringBuilder
import java.util.*
import java.util.zip.CRC32
import kotlin.collections.HashMap


interface ViewGenInfoHolder {
    fun hasViewGenClass(viewName: String): Boolean
    fun hasAttr(attrName: String): Boolean
    fun getAttrMethodInfoMap(viewName: String): Map<String, AttrFuncInfoModel>?
    fun getOnEndMethodInfoMap(viewName: String): Map<String, AttrFuncInfoModel>?
    fun cacheLocalVarDefContent()
    fun shouldStyleAttrReset(styleAttrName: String): Boolean
    fun shouldCallOnFinishInflate(viewName: String): Boolean

    fun getImportPackage(viewClassName: String): Map<String, String>?

    fun localVarDefContent(usedLocalVarMap: HashMap<String, String>): String
    fun localVarResetContent(usedLocalVarMap: HashMap<String, String>): String

    fun getLayoutParamInitBloc(parentViewClassName: String): String?

    fun anyChange(generateClassInfo: GenerateClassInfo): Boolean
}

class ViewGenInfoHolderImpl(private val viewGenInfoMap: HashMap<String, ViewGenClassModel>
                            , private val genClassInfoModel: GenClassInfoModel
                            , private val cacheFile: File
                            , private val newQxmlExtension: QxmlConfigExtension
                            , private val WaitableExecutor: WaitableExecutor
                            , val styleInfoMap: Map<String, Map<String, StyleInfo>>
                            , private val genClassInfoModelJsonStr: String
                            , private val cacheDir: File
                            , private val gson: Gson)
    : ViewGenInfoHolder {

    // ??????new?????????view??????????????????ViewGroup????????????attr???????????????
    // ????????????style???????????????????????????style????????????attr
    // ????????????????????????
    // TODO ????????????
    private val styleResetAttrMap = mutableMapOf<String, String>().apply {
        putAll(CommonResetAttr.map)
        putAll(LinearLayoutResetAttr.map)
        putAll(RelativeLayoutResetAttr.map)
        putAll(ConstraintLayoutResetAttr.map)
        putAll(TextViewResetAttr.map)
    }

    private val viewGenMap by lazy { hashMapOf<String, String>().apply {
        genClassInfoModel.genClassNameMap.forEach { (gen, view) ->
            if (genClassInfoModel.viewParseList.contains(gen)) {
                put(view, gen)
            }
        }
    } }

    //view ??? view parent
    private val viewClassParentMap by lazy { hashMapOf<String, String>().apply {
        genClassInfoModel.layoutParamInitMap.forEach { (view, initBlock) ->
            if (initBlock.isEmpty() && viewGenMap.contains(view)) {
                viewGenMap[view]?.also { genName ->
                    genClassInfoModel.parentClassMap[genName]?.also { parentGenName ->
                        genClassInfoModel.genClassNameMap[parentGenName]?.also { parentViewName ->
                            put(view, parentViewName)
                        }
                    }
                }
            }
        }
    } }

    private val finalLayoutParamInitBlocMap by lazy { hashMapOf<String, String>().apply {
        genClassInfoModel.layoutParamInitMap.forEach { (viewClassName, layoutParamInitBloc) ->
            if (!layoutParamInitBloc.isNullOrEmpty()) {
                put(viewClassName, layoutParamInitBloc)
            } else {
                var parent = viewClassParentMap[viewClassName]
                while (parent != null) {
                    val parentLpInitBloc = genClassInfoModel.layoutParamInitMap[parent]
                    if (parentLpInitBloc != null && parentLpInitBloc.isNotEmpty()) {
                        put(viewClassName, parentLpInitBloc)
                        break
                    }
                    parent = viewClassParentMap[parent!!]
                }
            }
        }
    } }

    private val finalCallOnFinishInflateMap by lazy { hashMapOf<String, Boolean>().apply {
        viewGenInfoMap.forEach { (viewClassName, _) ->
            val call = genClassInfoModel.callOnFinishInflateMap[viewClassName]
            if (call != null) {
                put(viewClassName, call)
            } else {
                var parent = viewClassParentMap[viewClassName]
                val stackList = mutableListOf<String>()
                var callResult: Boolean = false
                stackList.add(viewClassName)
                while (parent != null) {
                    val parentCall = genClassInfoModel.callOnFinishInflateMap[parent]
                    if (parentCall != null) {
                        callResult = parentCall
                        break
                    } else {
                        stackList.add(parent!!)
                        parent = viewClassParentMap[parent!!]
                    }
                }
                stackList.forEach {
                    put(it, callResult)
                }
            }
        }
    } }

    //???????????????
    private val localVarDefContent by lazy {
        val stringBuilder = StringBuilder()
        var index = 0
        genClassInfoModel.localVarMap.forEach { (_, localVarInfoModel) ->
            stringBuilder.append(localVarInfoModel.initBlock)
            index++
            if (index != genClassInfoModel.localVarMap.size) {
                stringBuilder.append(";").append("\n")
            }
        }
        stringBuilder.toString()
    }

    /**
     * attr ????????????
     */
    private val attrExistMap = hashMapOf<String, Boolean>().also { existMap ->
        viewGenInfoMap.forEach { (_, viewGenClassModel) ->
            viewGenClassModel.funcInfoModelHashMap.forEach { (attrName, _) ->
                existMap[attrName] = true
            }
        }
    }

    //import package map
    private val finalImportPackageMap by lazy { hashMapOf<String, Map<String, String>>().apply {
        viewGenInfoMap.forEach { (viewClassName, viewGenClassModel) ->
            put(viewClassName, viewGenClassModel.importPackageMap)
        }
    } }

    init {
        val crc32 = CRC32()
        crc32.update(genClassInfoModelJsonStr.toByteArray())
        val genClassInfoModelJsonCrc32 = java.lang.Long.toHexString(crc32.value)
        val modelInfoCacheFile = cacheDir.resolve("${genClassInfoModelJsonStr.length}_${genClassInfoModelJsonCrc32}.txt")
        if (modelInfoCacheFile.exists()) {
            val cacheViewGenInfoMap: Map<String, ViewGenClassModel> = gson.fromJson(modelInfoCacheFile.readText(), object : TypeToken<Map<String, ViewGenClassModel>>() {}.type)
            viewGenInfoMap.clear()
            viewGenInfoMap.putAll(cacheViewGenInfoMap)
        } else {
            cacheDir.delete()
            //??????????????????
            viewGenInfoMap.forEach { (_, viewGenClassModel) ->
                WaitableExecutor.execute {
                    resolveLocalVar(viewGenClassModel.onEndFuncInfoModelMap, genClassInfoModel.localVarMap)
                    resolveLocalVar(viewGenClassModel.funcInfoModelHashMap, genClassInfoModel.localVarMap)
                    viewGenClassModel.overrideFuncInfoModelList?.forEach { attrFuncInfoModel ->
                        genClassInfoModel.localVarMap.forEach { (_, localVarInfoModel) ->
                            if (attrFuncInfoModel.funcBodyContent.contains(localVarInfoModel.changeStr)) {
                                attrFuncInfoModel.funcBodyContent = attrFuncInfoModel.funcBodyContent?.replace(localVarInfoModel.changeStr, localVarInfoModel.replaceStr)
                            }
                            if (attrFuncInfoModel.funcBodyContent.contains(localVarInfoModel.fullVarName)) {
                                attrFuncInfoModel.usedLocalVarMap[localVarInfoModel.fullVarName] = ""
                            }
                        }
                    }
                }
            }
            WaitableExecutor.waitForTasksWithQuickFail<Any>(true)

            Files.createParentDirs(modelInfoCacheFile)
            modelInfoCacheFile.createNewFile()
            modelInfoCacheFile.writeText(gson.toJson(viewGenInfoMap))
        }
    }

    private fun resolveLocalVar(attrFuncInfoModelMap: Map<String, AttrFuncInfoModel>?, localVarInfoModelMap: Map<String, LocalVarInfoModel>) {
        attrFuncInfoModelMap?.forEach { (_, attrFuncInfoModel) ->
            localVarInfoModelMap.forEach { (_, localVarInfoModel) ->
                if (attrFuncInfoModel.funcBodyContent.contains(localVarInfoModel.changeStr)) {
                    attrFuncInfoModel.funcBodyContent = attrFuncInfoModel.funcBodyContent?.replace(localVarInfoModel.changeStr, localVarInfoModel.replaceStr)
                }
                if (attrFuncInfoModel.funcBodyContent.contains(localVarInfoModel.fullVarName)) {
                    if (attrFuncInfoModel.usedLocalVarMap == null) {
                        attrFuncInfoModel.usedLocalVarMap = hashMapOf()
                    }
                    attrFuncInfoModel.usedLocalVarMap[localVarInfoModel.fullVarName] = ""
                }
            }
        }
    }

    override fun hasViewGenClass(viewName: String): Boolean = viewGenInfoMap[viewName] != null

    override fun hasAttr(attrName: String): Boolean {
        return attrExistMap[attrName] ?: false
    }

    override fun getAttrMethodInfoMap(viewName: String): Map<String, AttrFuncInfoModel>? {
        return viewGenInfoMap[viewName]?.funcInfoModelHashMap
    }

    override fun getOnEndMethodInfoMap(viewName: String): Map<String, AttrFuncInfoModel>? {
        return viewGenInfoMap[viewName]?.onEndFuncInfoModelMap
    }

    override fun localVarDefContent(usedLocalVarMap: HashMap<String, String>): String {
        val stringBuilder = StringBuilder()
        var index = usedLocalVarMap.size
        usedLocalVarMap.forEach { (fullName, _) ->
            genClassInfoModel.localVarMap[fullName]?.apply {
                stringBuilder.append(initBlock)
                index--
                if (index != 0) {
                    stringBuilder.append(";\n")
                }
            }
        }
        return stringBuilder.toString()
    }

    override fun localVarResetContent(usedLocalVarMap: HashMap<String, String>): String {
        val stringBuilder = StringBuilder()
        var index = usedLocalVarMap.size
        usedLocalVarMap.forEach { (fullName, _) ->
            genClassInfoModel.localVarMap[fullName]?.apply {
                stringBuilder.append(resetBlock)
                index--
                if (index != 0) {
                    stringBuilder.append(";\n")
                }
            }
        }
        return stringBuilder.toString()
    }

    override fun cacheLocalVarDefContent() {
        if (!cacheFile.exists()) {
            cacheFile.createNewFile()
        }
        cacheFile.writeText(localVarDefContent)
    }

    override fun shouldStyleAttrReset(styleAttrName: String): Boolean {
        //return true
        return styleResetAttrMap[styleAttrName] != null
    }

    override fun shouldCallOnFinishInflate(viewName: String): Boolean {
        return finalCallOnFinishInflateMap[viewName] ?: false
    }

    override fun getImportPackage(viewClassName: String): Map<String, String>? {
        return finalImportPackageMap[viewClassName]
    }

    override fun getLayoutParamInitBloc(parentViewClassName: String): String? {
        return finalLayoutParamInitBlocMap[parentViewClassName]
    }

    override fun anyChange(generateClassInfo: GenerateClassInfo): Boolean {

        //??????????????????
        /*if (isLocalVarDefChange) {
            LogUtil.pl("anyChange true by LocalVarDefChange ")
            return true
        }*/
        //????????????attr????????????
        generateClassInfo.invalidGenInfoMap.forEach { (viewClassName, attrMap) ->
            viewGenInfoMap[viewClassName]?.funcInfoModelHashMap?.also { map ->
                attrMap.forEach { (attrName, _) ->
                    if (map[attrName] != null) {
                        LogUtil.pl("anyChange true by invalidGenInfoMap "+viewClassName+" "+attrName)
                        return true
                    }
                }
            }
        }

        //?????????attr????????????
        generateClassInfo.usedGenInfoMap.forEach { (viewClassName, attrFuncMap) ->
            val viewTypeInfoMap = viewGenInfoMap[viewClassName]
            attrFuncMap.forEach { (attrName, attrFuncInfoModel) ->
                //???????????????
                if ((viewTypeInfoMap?.funcInfoModelHashMap?.get(attrName)?.isChange(attrFuncInfoModel)) != false) {
                    LogUtil.pl("anyChange true by usedGenInfoMap "+viewClassName+" "+attrName)
                    return true
                }
            }
        }

        //style??????
        for (entry in generateClassInfo.usedStyleInfoMap) {
            val typeCacheMap = entry.value
            var styleName = entry.key

            for (cacheEntry in typeCacheMap) {
                val styleType = cacheEntry.key
                val typeStyleMap = styleInfoMap[styleType]
                val styleInfo = typeStyleMap?.get(styleName)
                if (typeStyleMap == null || styleInfo == null) {
                    LogUtil.pl("anyChange true by style(${styleName} ${styleType}) no exist ")
                    return true
                }

                val cacheStyleInfo = cacheEntry.value

                if (styleInfo.itemMap.size != cacheStyleInfo.itemMap.size) {
                    LogUtil.pl("anyChange true by usedStyleInfoMap size "+styleInfo.itemMap.size+" "+cacheStyleInfo.itemMap.size)
                    return true
                }
                for (styleEntry in styleInfo.itemMap) {
                    if (cacheStyleInfo.itemMap[styleEntry.key] != styleEntry.value) {
                        LogUtil.pl("anyChange true by usedStyleInfoMap change "+cacheStyleInfo.itemMap[styleEntry.key]+" "+styleEntry.value)
                        return true
                    }
                }
            }
            /*val styleInfo = entry.value
            val styleName = styleInfo.styleName //entry.key???????????????style???
            if (styleInfoMap[styleName] == null) {
                LogUtil.pl("anyChange true by usedStyleInfoMap style dismiss "+styleName)
            }
            val usedStyleInfo = styleInfoMap[styleName] ?: return true
            if (styleInfo.itemMap.size != usedStyleInfo.itemMap.size) {
                LogUtil.pl("anyChange true by usedStyleInfoMap size "+styleInfo.itemMap.size+" "+usedStyleInfo.itemMap.size)
                return true
            }
            for (styleEntry in styleInfo.itemMap) {
                if (usedStyleInfo.itemMap[styleEntry.key] != styleEntry.value) {
                    LogUtil.pl("anyChange true by usedStyleInfoMap change "+usedStyleInfo.itemMap[styleEntry.key]+" "+styleEntry.value)
                    return true
                }
            }*/
        }

        //?????????onEnd??????
        generateClassInfo.usedOnEndInfoMap.forEach { (viewClassName, onEndFuncMap) ->
            val viewTypeInfoMap = viewGenInfoMap[viewClassName]
            onEndFuncMap.forEach { (signInfo, attrFuncInfoModel) ->
                //???????????????
                if ((viewTypeInfoMap?.onEndFuncInfoModelMap?.get(signInfo)?.isChange(attrFuncInfoModel)) != false) {
                    LogUtil.pl("anyChange true by usedOnEndInfoMap "+viewClassName+" "+signInfo)
                    return true
                }
            }
        }
        return false
    }

}