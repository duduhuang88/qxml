package com.qxml.transform.generate.model

import com.qxml.transform.generate.CreateViewInfo

/**
 * 生成时用到的共享变量信息
 */
class GenerateFieldInfoMap(val allSizeMap: HashMap<String, Map<String, String>> = hashMapOf()      //size信息
                           , val allContextThemeWrapMap: LinkedHashMap<String, Map<String, String>> = LinkedHashMap() //包装主题context类信息
                           , val allNewViewMap: HashMap<String, HashMap<String, MutableList<String>>> = hashMapOf()
                           , val allCreateViewInfoList: HashMap<String, MutableList<CreateViewInfo>> = hashMapOf()
) {

    fun add(layoutType: String, fieldInfo: FieldInfo) {
        if (fieldInfo.sizeMap.isNotEmpty()) {
            allSizeMap[layoutType] = fieldInfo.sizeMap
        }
        if (fieldInfo.contextThemeWrapMap.isNotEmpty()) {
            allContextThemeWrapMap[layoutType] = fieldInfo.contextThemeWrapMap
        }
        if (fieldInfo.newViewMap.isNotEmpty()) {
            allNewViewMap[layoutType] = fieldInfo.newViewMap
        }
        if (fieldInfo.createViewInfoList.isNotEmpty()) {
            allCreateViewInfoList[layoutType] = fieldInfo.createViewInfoList
        }
    }

}

class FieldInfo(val sizeMap: HashMap<String, String>
                , val contextThemeWrapMap: LinkedHashMap<String, String>
                //new View的map, key: viewClassName, value: initBloc list, 集中创建View
                , val newViewMap: HashMap<String, MutableList<String>>
                , val createViewInfoList: MutableList<CreateViewInfo>)