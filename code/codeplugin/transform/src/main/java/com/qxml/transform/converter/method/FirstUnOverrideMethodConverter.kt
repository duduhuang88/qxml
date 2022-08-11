package com.qxml.transform.converter.method

import com.qxml.tools.log.LogUtil
import com.qxml.transform.pool.PoolManager
import javassist.CtClass
import javassist.CtMethod
import javassist.expr.MethodCall
import java.util.concurrent.ConcurrentHashMap

/**
 * convert未被Override过的方法
 */
abstract class FirstUnOverrideMethodConverter(
    private val rootClassName: String,
    methodName: String,
    methodSignature: String
) : MethodConverter(null, methodName, methodSignature) {

    private val methodParams by lazy { methodParams() }

    private val overrideMethodClassMap: ConcurrentHashMap<String, OverrideState> =
        ConcurrentHashMap()
    private val isRootSuperClassMap: ConcurrentHashMap<String, Boolean> = ConcurrentHashMap()
    private val rootClass by lazy { PoolManager.pool.get(rootClassName) }

    private fun isClassOverrideSetContentViewMethod(className: String): OverrideState? {
        return overrideMethodClassMap[className]
    }

    private fun setClassOverrideSetContentViewMethod(
        className: String,
        overrideState: OverrideState
    ) {
        overrideMethodClassMap.putIfAbsent(className, overrideState)
    }

    private fun isSubClassOfRoot(ctClass: CtClass): Boolean {
        isRootSuperClassMap[ctClass.name]?.let {
            return it
        }
        val isSubClassOfActivity = ctClass.subclassOf(rootClass)
        isRootSuperClassMap[ctClass.name] = isSubClassOfActivity
        return isSubClassOfActivity
    }

    override fun match(m: MethodCall, belongClass: CtClass): Boolean {
        try {
            if ((methodCallClassName == null || m.className == methodCallClassName)
                && (methodName == null || m.methodName == methodName)
                && (methodSignature == null || m.method.signature == methodSignature)
                && isSubClassOfRoot(PoolManager.pool.get(m.className))
            ) {
                return true
            }
        } catch (e: javassist.NotFoundException) {
            LogUtil.pl("can not found super method of ${m.methodName} in ${belongClass.name}, ignore")
        }
        return false
    }

    @Synchronized
    override fun methodConvert(m: MethodCall, belongMethod: CtMethod?, belongClass: CtClass) {
        if (m.className == rootClassName) {
            replace(m, belongClass)
            return
        }
        val methodCallClass = PoolManager.pool.get(m.className)

        if (!isSubClassOfRoot(methodCallClass)) {
            return
        }

        try {
            when (findClassOverrideState(methodCallClass)) {
                OverrideState.SUP_CLASS_OVERRIDE, OverrideState.CUR_CLASS_OVERRIDE -> {
                    return
                }
                OverrideState.UN_OVERRIDE -> {
                    replace(m, belongClass)
                }
            }
        } catch (e: Exception) {
            LogUtil.pl("FirstUnOverrideMethodConverter err : ${belongClass.name}(${m.methodName})")
            e.printStackTrace()
            throw e
        }
    }

    private fun findClassOverrideState(curClass: CtClass): OverrideState {
        val curClassOverrideState = isClassOverrideSetContentViewMethod(curClass.name)
        if (curClassOverrideState != null) {
            return curClassOverrideState
        }
        val curSetContentViewMethod = getReplaceMethod(curClass)
        var supClass = curClass.superclass
        var supClassOverrideState: OverrideState = OverrideState.UN_OVERRIDE
        while (supClass.name != rootClassName) {
            supClassOverrideState = findClassOverrideState(supClass)
            if (supClassOverrideState == OverrideState.SUP_CLASS_OVERRIDE || supClassOverrideState == OverrideState.CUR_CLASS_OVERRIDE) {
                break
            }
            supClass = supClass.superclass
        }
        val result = when (supClassOverrideState) {
            OverrideState.UN_OVERRIDE -> {
                if (curSetContentViewMethod == null) OverrideState.UN_OVERRIDE else OverrideState.CUR_CLASS_OVERRIDE
            }
            OverrideState.SUP_CLASS_OVERRIDE, OverrideState.CUR_CLASS_OVERRIDE -> {
                OverrideState.SUP_CLASS_OVERRIDE
            }
        }
        setClassOverrideSetContentViewMethod(curClass.name, result)
        return result
    }

    private fun getReplaceMethod(ctClass: CtClass): CtMethod? {
        return try {
            ctClass.getDeclaredMethod(methodName, methodParams)
        } catch (e: Exception) {
            null
        }
    }

    abstract fun replace(m: MethodCall, belongClass: CtClass)
    abstract fun methodParams(): Array<CtClass>
}

enum class OverrideState {
    CUR_CLASS_OVERRIDE, //自己重写了方法，所有父类都没有重写
    UN_OVERRIDE,        //自己和所有父类都没有重写方法
    SUP_CLASS_OVERRIDE, //其中一个父类重写了方法，不用管自己有没有
}
