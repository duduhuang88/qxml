package com.qxml.androidx_test

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.yellow.qxml_test.TestLayoutRes

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        //TestLayoutRes.TEST_LAYOUT_RES.clear()
        TestLayoutRes.TEST_LAYOUT_RES.addAll(AndroidxTestLayoutRes.TEST_LAYOUT_RES)
        Fresco.initialize(this)
    }

}