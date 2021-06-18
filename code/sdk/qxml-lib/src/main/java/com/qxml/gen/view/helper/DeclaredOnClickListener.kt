package com.qxml.gen.view.helper

import android.content.Context
import android.content.ContextWrapper
import android.view.View
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class DeclaredOnClickListener(
    private val mHostView: View,
    private val mMethodName: String
) :
    View.OnClickListener {
    private var mResolvedMethod: Method? = null
    private var mResolvedContext: Context? = null
    override fun onClick(v: View) {
        if (mResolvedMethod == null) {
            resolveMethod(mHostView.context, mMethodName)
        }
        try {
            mResolvedMethod!!.invoke(mResolvedContext, v)
        } catch (e: IllegalAccessException) {
            throw IllegalStateException(
                "Could not execute non-public method for android:onClick", e
            )
        } catch (e: InvocationTargetException) {
            throw IllegalStateException(
                "Could not execute method for android:onClick", e
            )
        }
    }

    private fun resolveMethod(contextParam: Context?, name: String) {
        var context = contextParam
        while (context != null) {
            try {
                if (!context.isRestricted) {
                    val method = context.javaClass.getMethod(
                        mMethodName,
                        View::class.java
                    )
                    if (method != null) {
                        mResolvedMethod = method
                        mResolvedContext = context
                        return
                    }
                }
            } catch (e: NoSuchMethodException) {
                // Failed to find method, keep searching up the hierarchy.
            }
            context = if (context is ContextWrapper) {
                context.baseContext
            } else {
                // Can't search up the hierarchy, null out and fail.
                null
            }
        }
        val id = mHostView.id
        val idText = if (id == View.NO_ID) "" else " with id '"+mHostView.context.resources.getResourceEntryName(id) + "'"
        throw IllegalStateException(
            "Could not find method " + mMethodName
                    + "(View) in attr_reference_test parent or ancestor Context for android:onClick "
                    + "attribute defined on view " + mHostView.javaClass + idText
        )
    }
}