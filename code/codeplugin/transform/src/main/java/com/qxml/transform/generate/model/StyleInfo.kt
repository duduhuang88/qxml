package com.qxml.transform.generate.model

import java.util.*

class StyleInfo(var styleName: String, var parentStyle: String?, var itemMap: TreeMap<String, String>
                , var hasParent: Boolean = false, var valid: Boolean = true, var parentValid: Boolean = true) {

    override fun toString(): String {
        return styleName
    }

}