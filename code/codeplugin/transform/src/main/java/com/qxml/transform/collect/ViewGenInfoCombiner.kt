package com.qxml.transform.collect

import com.google.gson.Gson
import com.qxml.constant.Constants
import com.qxml.tools.log.LogUtil
import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.tools.model.GenClassInfoModel
import com.qxml.tools.model.ViewGenClassModel

object ViewGenInfoCombiner {

    private val genInfoHeadNode = GenInfoHeadNode()

    fun viewGenInfoCombine(genClassInfoModel: GenClassInfoModel): Map<String, ViewGenClassModel> {
        genInfoHeadNode.createTree(genClassInfoModel)
        return genInfoHeadNode.combine()
    }
}

/**
 *        ViewGen
 *          ⬤ ———————
 *         /  \   \   \
 *       ⬤    ⭘  ⭘  ⬤
 *      /     / \
 *    ⭘    ⬤   ⭘
 *         /
 *       ⭘
 *
 *   ⭘ : viewReplace注解类
 *   ⬤ : viewParse注解类
 *   这是一棵以com.qxml.gen.view.ViewGen类为根的双向多叉分类树
 */
private class GenInfoTreeNode(val genClassName: String) {

    /**
     * viewReplace注解类子叶
     */
    val replaceTreeNodeList = mutableListOf<GenInfoTreeNode>()

    /**
     * viewParse注解类子叶
     */
    val genTreeNodeList = mutableListOf<GenInfoTreeNode>()

    /**
     * 父节点
     */
    var parentTreeNode: GenInfoTreeNode? = null

    override fun toString(): String {
        return "GenInfoTreeNode(genClassName='$genClassName', replaceTreeNodeList=$replaceTreeNodeList, genTreeNodeList=$genTreeNodeList)"
    }

}

private class GenInfoHeadNode {

    val viewGenRootNode by lazy { treeNodeMap[Constants.VIEW_GEN_CLASS_NAME]!! }

    private val treeNodeMap = hashMapOf<String, GenInfoTreeNode>()
    private val nodeTypeMap = hashMapOf<String, Boolean>()

    private lateinit var genClassInfoModel: GenClassInfoModel
    private val viewTypeNameMap: MutableMap<String, String> = mutableMapOf()
    private val viewParseList: MutableList<String> = mutableListOf()
    private val viewReplaceList: MutableList<String> = mutableListOf()
    private val resultViewGenClassModelMap by lazy { HashMap<String, ViewGenClassModel>() }

    private val genInfoCacheMap = hashMapOf<String, ViewGenClassModel>()

    fun createTree(genClassInfoModel: GenClassInfoModel) {

        this.genClassInfoModel = genClassInfoModel
        viewTypeNameMap.clear()
        viewTypeNameMap.putAll(genClassInfoModel.genClassNameMap)
        viewParseList.clear()
        viewParseList.addAll(genClassInfoModel.viewParseList)
        viewReplaceList.clear()
        viewReplaceList.addAll(genClassInfoModel.viewReplaceList)

        resultViewGenClassModelMap.clear()
        resultViewGenClassModelMap.putAll(genClassInfoModel.viewGenClassModelMap)
        viewReplaceList.forEach {
            nodeTypeMap[it] = false
        }
        viewParseList.forEach {
            nodeTypeMap[it] = true
        }

        genClassInfoModel.parentClassMap.forEach { (curName, parentName) ->
            val curNode = getTreeNodeByName(curName)!!
            val parentNode = getTreeNodeByName(parentName)!!
            curNode.parentTreeNode = parentNode
            val isParseType = nodeTypeMap[curName] ?: false
            if (isParseType) {
                parentNode.genTreeNodeList.add(curNode)
            } else {
                parentNode.replaceTreeNodeList.add(curNode)
            }
        }
    }

    private fun getTreeNodeByName(name: String?): GenInfoTreeNode? {
        if (name == null) {
            return null
        }
        var result = treeNodeMap[name]
        if (result == null) {
            result = GenInfoTreeNode(name)
            treeNodeMap[name] = result
        }
        return result
    }

    fun combine(): Map<String, ViewGenClassModel> {
        genInfoCacheMap.clear()
        viewParseList.forEach { parseClassName ->
            if (viewTypeNameMap[parseClassName]!= null) {
                combineGen(parseClassName, true)
            }
        }
        viewReplaceList.forEach {
            //resultViewGenClassModelMap.remove(it)
        }

        resultViewGenClassModelMap.forEach { (genName, viewGenClassModel) ->
            viewGenClassModel.overrideFuncInfoModelList = null
            /*viewGenClassModel.funcInfoModelHashMap.forEach { (_, attrFuncInfoModel) ->
                LogUtil.pl("gen key "+attrFuncInfoModel)
                generateAttrFuncKey(attrFuncInfoModel)
            }*/
        }


        return hashMapOf<String, ViewGenClassModel>().also { resultMap ->
            resultViewGenClassModelMap.forEach { (name, viewGenClassModel) ->
                val attrFuncMap = hashMapOf<String, AttrFuncInfoModel>()
                viewGenClassModel.funcInfoModelHashMap.forEach { (attrName, attrFuncInfoModel) ->
                    attrFuncInfoModel.attrName = attrName
                    if (viewTypeNameMap[name] == null) {
                        LogUtil.pl("viewTypeNameMap "+Gson().toJson(viewTypeNameMap))
                        LogUtil.pl("missing name "+name)
                    }

                    attrFuncInfoModel.belongViewName = viewTypeNameMap[name]!!
                    attrFuncInfoModel.generateKey()
                    attrFuncMap[attrName] = attrFuncInfoModel.clone()
                }
                viewGenClassModel.funcInfoModelHashMap = attrFuncMap

                val onEndFuncMap = hashMapOf<String, AttrFuncInfoModel>()
                viewGenClassModel.onEndFuncInfoModelMap.forEach { (funcSign, attrFuncInfoModel) ->
                    attrFuncInfoModel.belongViewName = viewTypeNameMap[name]!!
                    onEndFuncMap[funcSign] = attrFuncInfoModel.clone().apply {
                        //onEndCondition = attrFuncInfoModel.onEndCondition
                    }
                }
                viewGenClassModel.onEndFuncInfoModelMap = onEndFuncMap

                resultMap[viewTypeNameMap[name]!!] = viewGenClassModel
            }
            //LogUtil.d("combine result  " + Gson().toJson(resultMap))
        }
    }

    private fun generateAttrFuncKey(attrFuncInfoModel: AttrFuncInfoModel) {
        attrFuncInfoModel.generateKey()
    }

    /**
     * 从根节点获取viewParse注解的attr函数信息，并且合并viewReplace注解类的attr函数信息，包含override的函数
     */
    private fun combineGen(parseClassName: String, isParseType: Boolean): ViewGenClassModel {
        val curGenInfo = genInfoCacheMap[parseClassName]
        if (curGenInfo != null) {
            return curGenInfo
        }

        val parentClassName = genClassInfoModel.parentClassMap[parseClassName]

        val parentGenInfo = if (parentClassName != null) {
            genInfoCacheMap[parentClassName] ?: combineGen(
                parentClassName,
                nodeTypeMap[parentClassName] ?: false
            )
        } else {
            null
        }

        if (parentClassName != null && parentGenInfo != null) {
            genInfoCacheMap[parentClassName] = parentGenInfo
        }

        val sourceInfo = resultViewGenClassModelMap[parseClassName]!!
        if (parentGenInfo != null) {
            resolveOverrideAndCombineFunc(parentGenInfo, sourceInfo, parseClassName, isParseType)
        } else {
            resolveOverrideAndCombineFunc(sourceInfo, sourceInfo, parseClassName, isParseType)
        }
        genInfoCacheMap[parseClassName] = sourceInfo

        return sourceInfo
    }

    /**
     * 处理override函数，合并函数信息
     */
    private fun resolveOverrideAndCombineFunc(
        parentGenInfo: ViewGenClassModel,
        curGenInfo: ViewGenClassModel,
        curName: String,
        isParseType: Boolean
    ) {
        if (curGenInfo.overrideFuncInfoModelList != null) {
            for (attrFuncInfoModel in curGenInfo.overrideFuncInfoModelList) {
                for (mutableEntry in parentGenInfo.funcInfoModelHashMap) {
                    val parentAttrFuncInfo = mutableEntry.value
                    if (parentAttrFuncInfo.funcName == attrFuncInfoModel.funcName
                        && parentAttrFuncInfo.viewParamType == attrFuncInfoModel.viewParamType
                        && parentAttrFuncInfo.valueParamType == attrFuncInfoModel.valueParamType) {
                        //LogUtil.pl("resolveOverrideAndCombineFunc match func "+attrFuncInfoModel+" "+curName+" "+isParseType)
                        val parentAttrName = parentAttrFuncInfo.attrName
                        val split = parentAttrName.split(".attr.").toTypedArray()
                        var attrName = split.last()
                        if (parentAttrName.startsWith("com.qxml.AndroidRS") || parentAttrName.startsWith("AndroidRS")) {
                            attrName = "android:" + split.last()
                        }
                        attrFuncInfoModel.attrName = attrName
                        attrFuncInfoModel.type = AttrFuncInfoModel.ATTR_TYPE
                        if (isParseType) {
                            curGenInfo.funcInfoModelHashMap[attrFuncInfoModel.attrName] = attrFuncInfoModel
                        } else {
                            parentGenInfo.funcInfoModelHashMap[attrFuncInfoModel.attrName] = attrFuncInfoModel
                        }
                        break
                    }
                }
                for (mutableEntry in parentGenInfo.onEndFuncInfoModelMap) {
                    val parentAttrFuncInfo = mutableEntry.value
                    if (parentAttrFuncInfo.funcName == attrFuncInfoModel.funcName
                        && parentAttrFuncInfo.viewParamType == attrFuncInfoModel.viewParamType
                        && parentAttrFuncInfo.valueParamType == null) {
                        attrFuncInfoModel.type = AttrFuncInfoModel.ON_END_TYPE
                        attrFuncInfoModel.onEndCondition = parentAttrFuncInfo.onEndCondition
                        if (isParseType) {
                            curGenInfo.onEndFuncInfoModelMap[attrFuncInfoModel.funcSignInfo] = attrFuncInfoModel
                        } else {
                            parentGenInfo.onEndFuncInfoModelMap[attrFuncInfoModel.funcSignInfo] = attrFuncInfoModel
                        }
                        break
                    }
                }
            }
            curGenInfo.overrideFuncInfoModelList.clear()
        }

        if (!isParseType) {
            val curFuncInfoMap = curGenInfo.funcInfoModelHashMap
            // 将viewReplace类的funcInfoModelHashMap替换成父类的funcInfoModelHashMap，
            // 相当于把起始viewParse的funcInfoModelHashMap向下传递，后续递归修改时始终修改的是起始viewParse的funcInfoModelHashMap
            curGenInfo.funcInfoModelHashMap = parentGenInfo.funcInfoModelHashMap
            // viewReplace类的方法向上覆盖
            curGenInfo.funcInfoModelHashMap.putAll(curFuncInfoMap)

            val curOnEndInfoMap = curGenInfo.onEndFuncInfoModelMap
            curGenInfo.onEndFuncInfoModelMap = parentGenInfo.onEndFuncInfoModelMap
            curGenInfo.onEndFuncInfoModelMap.putAll(curOnEndInfoMap)
        } else {
            val parentInfoMap = hashMapOf<String, AttrFuncInfoModel>()
            parentInfoMap.putAll(parentGenInfo.funcInfoModelHashMap)
            parentInfoMap.putAll(curGenInfo.funcInfoModelHashMap)

            val parentOnEndInfoMap = hashMapOf<String, AttrFuncInfoModel>()
            parentOnEndInfoMap.putAll(parentGenInfo.onEndFuncInfoModelMap)
            parentOnEndInfoMap.putAll(curGenInfo.onEndFuncInfoModelMap)

            // 获得了父类的所有funcInfoModelHashMap，并添加自身attr信息
            curGenInfo.funcInfoModelHashMap = parentInfoMap
            curGenInfo.onEndFuncInfoModelMap = parentOnEndInfoMap
        }

        val curNode = getTreeNodeByName(curName)!!
        //将节点下的所有viewReplace类都合并进当前节点
        curNode.replaceTreeNodeList.forEach {
            resolveOverrideAndCombineFunc(
                curGenInfo,
                resultViewGenClassModelMap[it.genClassName]!!,
                it.genClassName,
                false
            )
        }
    }
}