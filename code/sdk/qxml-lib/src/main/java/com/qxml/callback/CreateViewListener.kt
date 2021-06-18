package com.qxml.callback

import android.content.Context
import android.view.View

interface CreateViewListener {
    fun onCreateView(parentView: View?, view: View, context: Context, viewClassName: String, originViewClassName: String)
}