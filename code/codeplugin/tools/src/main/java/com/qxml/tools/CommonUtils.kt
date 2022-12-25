package com.qxml.tools

import com.qxml.constant.Constants
import java.io.File
import java.util.*

object CommonUtils {

    //gradle7.4以上位置改变，这里没有做太多判断，gradle版本修改后需要clean删除缓存目录
    fun getMergerXmlFile(buildDir: File, type: String): File {
        val lowGradleVersionMergerXmlFile = getLowGradleVersionMergerXmlFile(buildDir, type)
        return if (lowGradleVersionMergerXmlFile.exists()) lowGradleVersionMergerXmlFile else {
            getHighGradleVersionMergerXmlFile(buildDir, type)
        }
    }

    private fun getLowGradleVersionMergerXmlFile(buildDir: File, type: String): File {
        return buildDir.resolve(Constants.INTERMEDIATES)
            .resolve(Constants.INCREMENTAL).resolve("merge${type.capitalize()}Resources")
            .resolve(Constants.MERGER_XML)
    }

    private fun getHighGradleVersionMergerXmlFile(buildDir: File, type: String): File {
        return buildDir.resolve(Constants.INTERMEDIATES)
            .resolve(Constants.INCREMENTAL).resolve(type).resolve("merge${type.capitalize()}Resources")
            .resolve(Constants.MERGER_XML)
    }

}