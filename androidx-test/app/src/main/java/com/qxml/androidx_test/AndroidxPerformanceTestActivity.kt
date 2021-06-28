package com.qxml.androidx_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yellow.qxml_test.TestLayoutRes
import com.yellow.qxml_test.presenter.PerformancePresenter

class AndroidxPerformanceTestActivity: AppCompatActivity() {

    companion object {
        private const val KEY_TEST_TIME = "KEY_TEST_TIME"
        private const val DEFAULT_TEST_COUNT = 50
        @JvmStatic
        fun start(context: Context, testTimes: Int) {
            var hasFrescoLayout = false
            TestLayoutRes.TEST_LAYOUT_RES.forEach {
                if (it == R.layout.fresco_test) {
                    hasFrescoLayout = true
                }
            }
            if (!hasFrescoLayout) {
                TestLayoutRes.TEST_LAYOUT_RES.add(R.layout.fresco_test)
            }
            context.startActivity(Intent(context, AndroidxPerformanceTestActivity::class.java).apply {
                putExtra(KEY_TEST_TIME, testTimes)
            })
        }
    }

    private val testTimes by lazy { intent.getIntExtra(KEY_TEST_TIME, DEFAULT_TEST_COUNT) }

    private val performancePresenter by lazy { PerformancePresenter(this, testTimes) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performancePresenter
    }

    override fun onDestroy() {
        super.onDestroy()
        performancePresenter.destroy()
    }
    
}