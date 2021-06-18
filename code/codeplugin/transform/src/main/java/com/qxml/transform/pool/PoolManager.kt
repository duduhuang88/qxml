package com.qxml.transform.pool

import javassist.ClassPool

object PoolManager {

    lateinit var pool: ClassPool

    @JvmStatic
    fun initPool(): ClassPool {
        return ClassPool(null).apply {
            appendSystemPath()
            importPackage("android.app")
            importPackage("android.content")
            importPackage("android.util")
            importPackage("android.os")
            importPackage("android.graphics.drawable")
            importPackage("android.view")
            importPackage("android.widget")
            importPackage("android.content.res")
            importPackage("com.qxml.value")
            importPackage("com.qxml.tools")
            importPackage("com.qxml.helper")

            importPackage("android.support.v4.content")
            importPackage("android.support.v4.widget")
            importPackage("android.support.v4.graphics.drawable")
            importPackage("android.support.v4.content.res")
        }
    }

}