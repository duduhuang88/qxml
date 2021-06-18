package com.yellow.qxml_test.presenter

import android.view.LayoutInflater
import android.view.ViewGroup

interface PerformanceView {

    fun addLeftView(viewGroup: ViewGroup, layoutInflater: LayoutInflater, layoutId: Int) {
        layoutInflater.inflate(layoutId, viewGroup, true)
    }

}