package com.qxml.transform.converter.method

import com.qxml.constant.Constants
import com.qxml.transform.converter.qxml.QxmlInitConverter
import javassist.*
import javassist.bytecode.*
import javassist.expr.MethodCall

class ActivitySetContentViewMethodConverter : FirstUnOverrideMethodConverter(
    Constants.ANDROID_ACTIVITY_CLASS_NAME,
    Constants.SET_CONTENT_VIEW_METHOD_NAME,
    Descriptor.ofMethod(CtClass.voidType, arrayOf(CtClass.intType))
) {
    override fun replace(m: MethodCall, belongClass: CtClass) {
        m.replace("{ \$0.setContentView(${Constants.QXML_INFLATER_CLASS_NAME}.generate(\$0.getLayoutInflater(), \$1, null, false)); }")
    }

    override fun methodParams(): Array<CtClass> = arrayOf(CtClass.intType)

}