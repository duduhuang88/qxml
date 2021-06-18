package com.qxml.tools

import com.qxml.constant.Constants

class GenClassNameTool {
    
    companion object {
        @JvmStatic
        fun genClassName(layoutName: String): String {
            return Constants.GEN_CLASS_PATH_PREFIX + layoutName
        }
    }
    
}