package com.qxml.tools.log

object LogUtil {

    @JvmStatic
    var debug = false

    @JvmStatic
    var enable = false

    @JvmStatic
    fun pl(msg: String) {
        if (enable) {
            println("qxml: $msg")
        }
    }

    @JvmStatic
    fun d(msg: String) {
        if (enable && debug) {
            println("qxml: $msg")
        }
    }

}