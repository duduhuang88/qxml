package com.yellow.qxml_test

import android.util.Log

class LogUtil {

    companion object {

        @JvmStatic
        fun e(info: String) {
            Log.e("touch", info)
        }

    }

}