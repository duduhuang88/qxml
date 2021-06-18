package com.qxml.androidx_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.yellow.qxml_test.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onUiTest(v: View) {
        AndroidxUiTestActivity.start(this)
    }

    fun onDataBindingTest(v: View) {
        AndroidxDataBindingTestActivity.start(this)
    }

    fun onFragmentTest(v: View) {
        AndroidxFragmentTestActivity.start(this)
    }

    fun onPerformanceTest(v: View) {
        val times = findViewById<EditText>(R.id.et_times).text.toString().toIntOrNull()?:50
        AndroidxPerformanceTestActivity.start(this, times)
    }
}