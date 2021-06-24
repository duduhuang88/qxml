package com.qxml.transform.collect

class IdCollector {

    companion object {
        private val typeMap = hashMapOf<String, String>().apply {
            put("color", "color")
            put("attr", "attr")
            put("string", "string")
            put("dimen", "dimen")
            put("style", "array")
            put("string-array", "array")
            put("integer-array", "array")
            put("anim", "anim")
        }
    }

}