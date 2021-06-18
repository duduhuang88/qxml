package com.qxml.support_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yellow.qxml_test.LogUtil
import com.yellow.qxml_test.presenter.PerformancePresenter
import com.yellow.qxml_test.presenter.PerformanceView

class SupportPerformanceTestActivity: AppCompatActivity(), PerformanceView {

    companion object {
        private const val KEY_TEST_TIME = "KEY_TEST_TIME"
        private const val DEFAULT_TEST_COUNT = 50
        @JvmStatic
        fun start(context: Context, testTimes: Int) {
            context.startActivity(Intent(context, SupportPerformanceTestActivity::class.java).apply {
                putExtra(KEY_TEST_TIME, testTimes)
            })
        }
    }

    private val testTimes by lazy { intent.getIntExtra(KEY_TEST_TIME, DEFAULT_TEST_COUNT) }

    private val performancePresenter by lazy { PerformancePresenter(this, testTimes, false/*, this*/) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performancePresenter
    }

    override fun onDestroy() {
        super.onDestroy()
        performancePresenter.destroy()
    }

    /*override fun addLeftView(viewGroup: ViewGroup, layoutInflater: LayoutInflater, layoutId: Int) {
        //super.addLeftView(viewGroup, layoutInflater, layoutId)
        Test.generate(layoutInflater, this, viewGroup, true)
    }*/

}