package com.qxml.transform.generate.tools

import com.qxml.constant.Constants

class ContextWrapTool {
    companion object {
        @JvmStatic
        fun makeContextField(styleValue: String, isReference: Boolean = false): String {
            return Constants.GEN_FIELD_CONTEXT_PREFIX + styleValue.replace(".", "_") + if (isReference) "_reference" else ""
        }

        @JvmStatic
        fun makeContextReferenceResIdField(styleValue: String): String {
            return Constants.GEN_FIELD_STYLE_REFERENCE_RES_ID_PRE + styleValue.replace(".", "_")
        }

        @JvmStatic
        fun makeContextValue(styleValue: String, parentContextName: String): String {
            //return "android.content.Context ${makeContextField(styleValue)} = ${Constants.GEN_PARAM_CONTEXT_NAME}"
            return "android.content.Context ${makeContextField(styleValue)} = new ${Constants.ANDROID_CONTEXT_THEME_WRAPPER_CLASS_NAME}(${parentContextName}, ${styleValue})"
        }

        //Constants.GEN_PARAM_CONTEXT_NAME
        @JvmStatic
        fun makeContextValueByReference(styleValue: String, parentContextName: String): String {
            val resId = makeContextReferenceResIdField(styleValue)
            return "${Constants.GEN_PARAM_CONTEXT_NAME}.getTheme().resolveAttribute(${styleValue}, ${Constants.GEN_FIELD_TYPED_VALUE_NAME}, true);\n" +
                    "int $resId = ${Constants.GEN_FIELD_TYPED_VALUE_NAME}.resourceId;\n" +
                    "android.content.Context ${makeContextField(styleValue, true)} = new ${Constants.ANDROID_CONTEXT_THEME_WRAPPER_CLASS_NAME}(${parentContextName}, $resId)"
        }
    }
}
