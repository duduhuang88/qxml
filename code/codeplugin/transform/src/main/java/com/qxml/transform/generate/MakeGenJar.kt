package com.qxml.transform.generate

import com.android.SdkConstants
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

interface MakeGenJar {

    fun makeGenJar(layoutGenJarOutputFile: File, classGenCacheInfoList: List<ClassGenCacheInfo>) {
        FileOutputStream(layoutGenJarOutputFile).use { fos ->
            ZipOutputStream(fos).use { zos ->
                classGenCacheInfoList.forEach { classGenResult ->
                    val entry = ZipEntry(classGenResult.name.replace(".", File.separator) + SdkConstants.DOT_CLASS)
                    entry.time = -1L
                    zos.putNextEntry(entry)
                    zos.write(classGenResult.classCacheFile.readBytes())
                }
            }
        }
    }

}