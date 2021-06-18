package com.qxml.transform.converter.method

import javassist.CtClass
import javassist.CtMethod
import javassist.expr.ExprEditor
import javassist.expr.MethodCall

object MethodCallManager {

    private val converterList = mutableListOf<MethodConverter>()

    fun register(vararg converters: MethodConverter) {
        converterList.addAll(converters)
    }

    fun convert(ctClass: CtClass) {
        var belongMethod: CtMethod? = null
        val editor = object : ExprEditor() {
            override fun edit(m: MethodCall) {
                super.edit(m)
                converterList.forEach { converter ->
                    if (converter.match(m, ctClass)) {
                        converter.methodConvert(m, belongMethod, ctClass)
                        return
                    }
                }
            }
        }
        ctClass.declaredMethods.forEach { method ->
            belongMethod = method
            method.instrument(editor)
        }
        belongMethod = null
        ctClass.constructors.forEach { ctConstructor ->
            ctConstructor.instrument(editor)
        }
    }
}

abstract class MethodConverter(val methodCallClassName: String?, val methodName: String?, val methodSignature: String?) {

    open fun match(m: MethodCall, belongClass: CtClass): Boolean {
        if ((methodCallClassName == null || m.className == methodCallClassName)
            && (methodName == null || m.methodName == methodName)
            && (methodSignature == null || m.method.signature == methodSignature)
        ) {
            return true
        }
        return false
    }

    abstract fun methodConvert(m: MethodCall, belongMethod: CtMethod?, belongClass: CtClass)
}