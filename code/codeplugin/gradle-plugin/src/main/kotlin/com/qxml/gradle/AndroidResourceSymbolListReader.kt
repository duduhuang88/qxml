package com.qxml.gradle

import com.squareup.javapoet.CodeBlock
import javassist.ClassPool
import java.io.File

internal const val ANDROID_R_ATTR_CLASS = "android.R\$attr"

class AndroidResourceSymbolListReader(private val builder: FinalRClassBuilder) {

    fun readAndroidSymbol(androidJarFile: File) {
        ClassPool.cacheOpenedJarFile = false
        val ctPool = ClassPool.getDefault()
        val cp = ctPool.insertClassPath(androidJarFile.absolutePath)

        val androidRCt = ctPool.get(ANDROID_R_ATTR_CLASS)

        androidRCt.fields.forEach {
            val name = it.name
            val value = CodeBlock.of("\$S", "android:$name")
            builder.addResourceField(ATTR, name, value, "{@link android.R.attr.$name}")
        }
        ctPool.removeClassPath(cp)
    }

}