package com.qxml.constant

import java.io.File

class Constants {

    companion object {
        const val GEN_PARAM_INFLATE_NAME = "__layoutInflater"
        const val GEN_PARAM_CONTEXT_NAME = "__context"
        const val GEN_PARAM_VIEW_GROUP_ROOT_NAME = "__root"
        const val GEN_PARAM_ATTACH_TO_NAME = "__attachTo"
        const val GEN_PARAM_INCLUDE_ID = "__includeId"
        const val GEN_PARAM_INCLUDE_TAG = "__includeTag"
        const val GEN_FIELD_LAYOUT_PARAMS_ROOT = "___root_layout_param"
        const val GEN_FIELD_LAYOUT_PARAMS = "___cur_layout_param"
        const val GEN_FIELD_CONTEXT_PREFIX = "___wrap_theme_context_"
        const val GEN_FIELD_VALUE_INFO_NAME = "___valueInfo"
        const val GEN_FIELD_TYPED_VALUE_NAME = "___typedValue"
        const val GEN_FIELD_LAYOUT_TYPE_STRING_NAME = "___layoutTypeStr"
        const val GEN_FIELD_LAYOUT_TYPE_HASHCODE_NAME = "___layoutTypeHashCode"
        const val GEN_FIELD_STYLE_REFERENCE_RES_ID_PRE = "___style_reference_res_id_"
        const val GEN_FIELD_RESOURCE = "___resources"

        const val GEN_FIELD_FACTORY = "____factory"
        const val GEN_FIELD_FACTORY_2 = "____factory2"
        const val GEN_FIELD_CUR_CONTEXT_ACT = "____curContextAct"


        const val GEN_FIELD_DENSITY = "___density"
        const val GEN_FIELD_SCALE_DENSITY = "___scaledDensity"
        const val GEN_FIELD_X_DPI = "___xdpi"


        const val GEN_FILED_SIZE_TYPE_DP = "__dp_"
        const val GEN_FILED_SIZE_TYPE_SP = "__sp_"
        const val GEN_FILED_SIZE_TYPE_MM = "__mm_"
        const val GEN_FILED_SIZE_TYPE_PT = "__pt_"
        const val GEN_FILED_SIZE_TYPE_IN = "__in_"

        const val DP = "dp"
        const val DIP = "dip"
        const val SP = "sp"
        const val PX = "px"
        const val PT = "pt"
        const val MM = "mm"
        const val IN = "in"

        const val QXML_VALUE_INFO_CLASS_NAME = "com.qxml.value.ValueInfo"


        const val RES_AUTO_NAME_SPACE_URI = "http://schemas.android.com/apk/res-auto"
        const val ANDROID_NAME_SPACE_URI = "http://schemas.android.com/apk/res/android"
        const val ATTR_NAME_LAYOUT_WIDTH = "layout_width"
        const val ATTR_NAME_LAYOUT_HEIGHT = "layout_height"
        const val ATTR_NAME_ID = "id"
        const val ATTR_NAME_NAME = "name"
        const val ATTR_NAME_TAG = "tag"
        const val ATTR_PREFIX_ANDROID = "android"
        const val ATTR_TAG = "android:tag"


        const val ANDROID_LAYOUT_INFLATE_FACTORY_CLASS_NAME = "android.view.LayoutInflater.Factory"
        const val ANDROID_LAYOUT_INFLATE_FACTORY2_CLASS_NAME = "android.view.LayoutInflater.Factory2"

        const val ANDROID_RESOURCE_CLASS_NAME = "android.content.res.Resources"
        const val ANDROID_CONTEXT_THEME_WRAPPER_CLASS_NAME = "android.view.ContextThemeWrapper"
        const val ANDROID_CONTEXT_COMPAT_CLASS_NAME = "android.support.v4.content.ContextCompat"
        const val ANDROID_TYPED_VALUE_CLASS_NAME = "android.util.TypedValue"
        const val ANDROID_CONTEXT_CLASS_NAME = "android.content.Context"
        const val ANDROID_VIEW_CLASS_NAME = "android.view.View"
        const val ANDROID_VIEW_CLASS_SIMPLE_NAME = "View"
        const val ANDROID_VIEW_GROUP_CLASS_NAME = "android.view.ViewGroup"
        const val ANDROID_VIEW_GROUP_LAYOUT_PARAM_CLASS_NAME = "android.view.ViewGroup.LayoutParams"
        const val ANDROID_ACTIVITY_CLASS_NAME = "android.app.Activity"
        const val ANDROID_DIALOG_CLASS_NAME = "android.app.Dialog"
        const val ANDROID_INFLATER_CLASS_NAME = "android.view.LayoutInflater"
        const val INFLATE_METHOD_NAME = "inflate"
        const val SET_CONTENT_VIEW_METHOD_NAME = "setContentView"

        const val QXML_INFLATER_CLASS_NAME = "com.qxml.QxmlInflater"
        const val QXML_INFLATER_CLASS_INSTANT = "com.qxml.QxmlInflater.INSTANCE"
        const val QXML_INFLATER_CREATE_VIEW_LISTENER_NAME = "createViewListener"
        const val REFERENCE_ATTR_RESOLVER = "com.qxml.value.ReferenceAttrResolver"
        const val QXML_REFERENCE_RESOLVE_INT_METHOD_NAME= "resolveInt"
        const val QXML_REFERENCE_RESOLVE_BOOLEAN_METHOD_NAME= "resolveBoolean"
        const val QXML_REFERENCE_RESOLVE_FLOAT_METHOD_NAME= "resolveFloat"
        const val QXML_REFERENCE_RESOLVE_STRING_METHOD_NAME= "resolveString"

        const val QXML_CONFIG_ATTR_NAME = "qxml"
        const val QXML_CONFIG_IGNORE = "genIgnore"
        const val QXML_CONFIG_WITH_UN_IMPLEMENT_ATTR = "genWithUnImplementAttr"
        const val QXML_CONFIG_WITHOUT_UN_IMPLEMENT_ATTR = "genWithoutUnImplementAttr"
        const val QXML_USE_FACTORY_CONFIG_ATTR_NAME = "qxml_use_factory"
        const val QXML_VIEW_DEBUG_CONFIG_ATTR_NAME = "qxml_debug"
        const val QXML_USE_COMPAT_CONFIG_ATTR_NAME = "qxml_compat"


        const val TAG_MERGE = "merge"
        const val TAG_INCLUDE = "include"
        const val TAG_FRAGMENT = "fragment"
        const val LAYOUT_PREFIX = "layout"

        const val LAYOUT_CLASS = "class"
        const val LAYOUT_LAYOUT = "layout"
        const val LAYOUT_STYLE = "style"
        const val LAYOUT_THEME = "android:theme"

        const val DATA_BINDING_LAYOUT = LAYOUT_LAYOUT
        const val DATA_BINDING_DATA = "data"
        const val DATA_BINDING_DATA_REFERENCE_PREFIX = "@{"
        const val DATA_BINDING_DATA_TWO_WAY_REFERENCE_PREFIX = "@={"
        const val DATA_BINDING_DATA_REFERENCE_SUFFIX = "}"

        const val QXML_CONIFG_PATH = "qxml_config"
        const val QXML_CLASS_ENTRY_NAME = "com/qxml/QxmlInflater.class"

        val QXML_CACHE_PATH = "qxml${File.separator}cache"
        const val QXML_DIR_NAME = "qxml"

        val GENERATE_RS_PATH = "qxml${File.separator}cache${File.separator}rs${File.separator}"
        val LAYOUT_ID_COLLECT_PATH = "qxml${File.separator}cache${File.separator}"
        const val LAYOUT_ID_COLLECT_FILE_NAME = "layoutIdCollect.txt"
        const val ID_COLLECT_FILE_NAME = "idCollect.txt"
        const val ID_COLLECT_CACHE_FILE_NAME = "idCollectCache.txt"
        const val CUR_BUILD_TYPE_FILE_NAME = "curBuildType.txt"
        const val LOCAL_VAR_DEF_CONTENT_CACHE_FILE_NAME = "localVarDefContentCache.txt"
        const val LAYOUTS_FILE_NAME = "layouts.txt"
        const val GEN_CLASS_CACHE_DIR = "genClassInfo"
        const val QXML_PARSE_CONFIG_FILE_NAME = "qxml_parse.config"
        const val QXML_PARSE_FINAL_CONFIG_FILE_NAME = "qxml_parse_final.config"
        const val QXML_CONFIG_CACHE_FILE_NAME = "qxml_config.txt"
        const val QXML_STYLE_CACHE_FILE_NAME = "qxml_style_info.txt"
        const val QXML_VIEW_GEN_INFO_CACHE_FILE_NAME = "qxml_view_gen_info.txt"
        const val QXML_METHOD_CONTENT_CACHE_FILE_NAME = "methodContent.txt"

        const val OBJ_CLASS_NAME = "java.lang.Object"
        const val VIEW_GEN_CLASS_NAME = "com.qxml.gen.view.ViewGen"

        const val ON_END_ANNOTATION_CLASS_NAME: String = "com.yellow.qxml_annotions.OnEnd"
        const val ATTR_ANNOTATION_CLASS_NAME: String = "com.yellow.qxml_annotions.Attr"
        const val VIEW_PARSE_ANNOTATION_CLASS_NAME: String = "com.yellow.qxml_annotions.ViewParse"
        const val VIEW_REPLACE_ANNOTATION_CLASS_NAME: String = "com.yellow.qxml_annotions.ViewReplace"

        const val GEN_CLASS_PATH_PREFIX = "com.qxml_${Constants.QXML_VERSION_CODE}._qxml__layout_gen."
        const val GENERATE_METHOD_NAME = "generate"

        const val QXML_VALID_CODE = "QXML_VALID_CODE"
        const val QXML_LOG_ENABLE = "QXML_LOG_ENABLE"
        const val QXML_PROJECT_BUILD_TEMP_RES_PATH = "tempRes"


        const val SUPPORT_FRAGMENT_HELPER = "com.qxml.qxml_support.helper.FragmentHelper"
        const val ANDROIDX_FRAGMENT_HELPER = "com.qxml.qxml_androidx.helper.FragmentHelper"

        const val QXML_VERSION_CODE = 1

        const val FORMAT_UN_KNOW = 0
        const val FORMAT_BOOLEAN = 1
        const val FORMAT_STRING = 1 shl 1
        const val FORMAT_REFERENCE = 1 shl 2
        const val FORMAT_COLOR = 1 shl 3
        const val FORMAT_INTEGER = 1 shl 4
        const val FORMAT_DIMENSION = 1 shl 5
        const val FORMAT_ENUM = 1 shl 6
        const val FORMAT_FLAGS = 1 shl 7
        const val FORMAT_FLOAT = 1 shl 8
        const val FORMAT_FRACTION = 1 shl 9
        const val FORMAT_NULL = 1 shl 10
    }

}