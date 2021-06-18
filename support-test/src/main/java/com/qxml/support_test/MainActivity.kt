package com.qxml.support_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onUiTest(v: View?) {
        SupportUiTestActivity.start(this)
    }

    fun onDataBindingTest(v: View) {
        SupportDataBindingTestActivity.start(this)
    }

    fun onFragmentTest(v: View) {
        FragmentTestActivity.start(this)
    }

    fun onPerformanceTest(v: View) {
        val times = findViewById<EditText>(R.id.et_times).text.toString().toIntOrNull()?:50
        SupportPerformanceTestActivity.start(this, times)
    }

}