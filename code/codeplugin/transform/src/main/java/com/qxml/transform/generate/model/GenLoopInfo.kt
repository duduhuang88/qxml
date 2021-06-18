package com.qxml.transform.generate.model

/**
 * 解析layout时view数量和层级的信息
 * @param viewCount 当前view的数量
 * @param loopLevel 当前view的层级
 */
data class GenLoopInfo(var viewCount: Int = 0, var loopLevel: Int = 0) {

    fun loopLevelUp() {
        loopLevel++
    }

    fun loopLevelDown() {
        loopLevel--
    }

    fun viewCountUp() {
        viewCount++
    }

}