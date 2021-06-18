package com.qxml.transform.converter.method

import com.qxml.constant.Constants
import javassist.CtClass
import javassist.bytecode.Descriptor
import javassist.expr.MethodCall

class DialogSetContentViewMethodConverter : FirstUnOverrideMethodConverter(
    Constants.ANDROID_DIALOG_CLASS_NAME,
    Constants.SET_CONTENT_VIEW_METHOD_NAME,
    Descriptor.ofMethod(CtClass.voidType, arrayOf(CtClass.intType))
) {
    override fun replace(m: MethodCall, belongClass: CtClass) {
        //m.replace("{ \$0.setContentView(${Constants.QXML_INFLATER_CLASS_INSTANT}.inflate(android.view.LayoutInflater.from(\$0.getContext()), \$1, null, false));}")
        m.replace("{ \$0.setContentView(${Constants.QXML_INFLATER_CLASS_NAME}.generate(\$0.getWindow() != null ? \$0.getWindow().getLayoutInflater() : android.view.LayoutInflater.from(\$0.getContext()), \$1, null, false));}")
    }

    override fun methodParams(): Array<CtClass> = arrayOf(CtClass.intType)
}