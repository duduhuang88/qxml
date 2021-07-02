package com.qxml.transform.generate

import com.qxml.constant.Constants
import java.io.File

interface MakeGenJar {

    fun makeGenJar(layoutGenJarOutputFile: File, classGenCacheInfoList: List<ClassGenCacheInfo>) {
        if (layoutGenJarOutputFile.exists()) {
            layoutGenJarOutputFile.delete()
        }
        layoutGenJarOutputFile.mkdirs()
        val layoutGenParentDir = File(layoutGenJarOutputFile, "com${File.separator}qxml_${Constants.QXML_VERSION_CODE}${File.separator}_qxml__layout_gen")
        layoutGenParentDir.mkdirs()
        classGenCacheInfoList.forEach { classGenResult ->
            val classFile = File(layoutGenParentDir, classGenResult.classCacheFile.name)
            classFile.createNewFile()
            classFile.writeBytes(classGenResult.classCacheFile.readBytes())
        }
    }

}