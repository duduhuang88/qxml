package com.qxml.transform.collect

import com.qxml.transform.generate.model.LayoutFileInfo

/**
 * 收集merger.xml 的 layout信息
 */
class LayoutFileInfoCollector(private val layoutPaths: List<String>) {

    /**
     * key: layout name
     * value: map<type, info>
     */
    private val layoutInfoMap = hashMapOf<String, HashMap<String, LayoutFileInfo>>()

    fun collect(mergeLayoutList: List<LayoutFileInfo>): HashMap<String, HashMap<String, LayoutFileInfo>> {

        layoutInfoMap.clear()
        //build\intermediates\incremental\mergeDebugResources&packageDebugResource\merger.xml收集的layout信息
        mergeLayoutList.forEach { xmlTypeInfo ->
            //LogUtil.pl("mergeLayout "+xmlTypeInfo)
            val infoMap = layoutInfoMap[xmlTypeInfo.name]
            if (infoMap != null) {
                infoMap[xmlTypeInfo.type] = xmlTypeInfo
            } else {
                val map = hashMapOf<String, LayoutFileInfo>()
                map[xmlTypeInfo.type] = xmlTypeInfo
                layoutInfoMap[xmlTypeInfo.name] = map
            }
        }

        /*layoutInfoMap.forEach { s, map ->
            LogUtil.pl("name " + s)
            map.forEach { s, xmlTypeInfo ->
                println("    f type " + s + " " + xmlTypeInfo)
            }
        }*/
        return layoutInfoMap
    }

}