package com.yellow.qxml_test.view_test

import android.view.View
import android.view.ViewStub
import com.yellow.qxml_test.R

object ViewStubTest {

    @JvmStatic
    fun setUI(view: View) {
        view.findViewById<ViewStub>(R.id.vs_1)?.apply {
            inflate()
        }
    }

}