package com.qxml.transform.converter.method

import com.qxml.constant.Constants
import com.qxml.tools.log.LogUtil
import javassist.CtClass
import javassist.CtMethod
import javassist.expr.MethodCall

class InflateMethodConverter: MethodConverter(Constants.ANDROID_INFLATER_CLASS_NAME, Constants.INFLATE_METHOD_NAME, null) {

    @Synchronized
    override fun methodConvert(m: MethodCall, belongMethod: CtMethod?, belongClass: CtClass) {
        if (m.method.parameterTypes[0] != CtClass.intType) {
            return
        }
        var methodContent = ""
        try {
            var attachToRootParam = "\$2 != null"
            if (m.method.parameterTypes.size > 2) {
                attachToRootParam = "\$3"
            }
            methodContent = m.method.toString()
            try {
                m.replace("{ \$_ = ${Constants.QXML_INFLATER_CLASS_NAME}.generate(\$0, \$1, \$2, $attachToRootParam); }")
            } catch (e: Exception) {
                //try again
                Thread.sleep(50)
                m.replace("{ \$_ = ${Constants.QXML_INFLATER_CLASS_NAME}.generate(\$0, \$1, \$2, $attachToRootParam); }")
            }
        } catch (e: Exception) {
            LogUtil.pl("inflate err "+e+" "+methodContent+" "+belongClass+" "+belongMethod)
            e.printStackTrace()
            throw e
        }
    }
}