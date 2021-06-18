package com.qxml.support_test

import android.app.Application
import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.view.View
import com.qxml.QxmlInflater
import com.qxml.callback.CreateViewListener
import com.yellow.qxml_test.TestLayoutRes

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        //TestLayoutRes.TEST_LAYOUT_RES.clear()
        TestLayoutRes.TEST_LAYOUT_RES.addAll(TestSupportLayoutRes.TEST_LAYOUT_RES)
        QxmlInflater.createViewListener = object : CreateViewListener {
            override fun onCreateView(
                parentView: View?,
                view: View,
                context: Context,
                viewClassName: String,
                originViewClassName: String
            ) {

            }
        }
    }

}