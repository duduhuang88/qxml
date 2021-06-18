package com.qxml.helper

import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import com.qxml.QxmlInflater

fun intToMode(tintMode: Int, defaultMode: android.graphics.PorterDuff.Mode? = null): android.graphics.PorterDuff.Mode? {
    var mode = defaultMode
    when (tintMode) {
        0 -> {
            mode = PorterDuff.Mode.CLEAR
        }
        1 -> {
            mode = PorterDuff.Mode.SRC
        }
        2 -> {
            mode = PorterDuff.Mode.DST
        }
        3 -> {
            mode = PorterDuff.Mode.SRC_OVER
        }
        4 -> {
            mode = PorterDuff.Mode.DST_OVER
        }
        5 -> {
            mode = PorterDuff.Mode.SRC_IN
        }
        6 -> {
            mode = PorterDuff.Mode.DST_IN
        }
        7 -> {
            mode = PorterDuff.Mode.SRC_OUT
        }
        8 -> {
            mode = PorterDuff.Mode.DST_OUT
        }
        9 -> {
            mode = PorterDuff.Mode.SRC_ATOP
        }
        10 -> {
            mode = PorterDuff.Mode.DST_ATOP
        }
        11 -> {
            mode = PorterDuff.Mode.XOR
        }
        16 -> {
            mode = PorterDuff.Mode.ADD
        }
        17 -> {
            mode = PorterDuff.Mode.LIGHTEN
        }
        14 -> {
            mode = PorterDuff.Mode.MULTIPLY
        }
        13 -> {
            mode = PorterDuff.Mode.SCREEN
        }
        12 -> {
            mode = PorterDuff.Mode.ADD
        }
        15 -> {
            mode = PorterDuff.Mode.SCREEN
        }
    }
    return mode
}

fun intToScaleType(type: Int): ImageView.ScaleType {
    return when (type) {
        0 -> ScaleType.MATRIX
        1 -> ScaleType.FIT_XY
        2 -> ScaleType.FIT_START
        3 -> ScaleType.FIT_CENTER
        4 -> ScaleType.FIT_END
        5 -> ScaleType.CENTER
        6 -> ScaleType.CENTER_CROP
        7 -> ScaleType.CENTER_INSIDE
        else -> ScaleType.MATRIX
    }
}