package com.yellow.qxml_test.presenter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.qxml.QxmlInflater
import com.qxml.helper.QxmlHelper
import com.yellow.qxml_test.R
import com.yellow.qxml_test.TestLayoutRes
import kotlinx.android.synthetic.main.activity_performance_test.*

class PerformancePresenter(private val activity: Activity, private val testTimes: Int, private val block: Boolean = false, private val performanceView: PerformanceView? = null) {

    var loopTime = 0
    var index = 0
    private var curGenerateTotalTime = 0L
    private var curInflateTotalTime = 0L
    private var generateTimes = 1
    private var inflateTimes = 1

    private val lf by lazy { activity.layoutInflater }
    private val c by lazy { activity as Context }

    private val performanceResultList = mutableListOf<PerformanceResult>()

    private var handler: Handler? = Handler()

    private var isGenerate = false

    private val timeCountRunnable = Runnable {
        doTimeCount()
    }

    init {
        QxmlHelper.showDebug = false
        activity.setContentView(R.layout.activity_performance_test)
        val screenWidth = activity.resources.displayMetrics.widthPixels
        activity.cl_left.layoutParams.width = screenWidth/2
        activity.cl_right.layoutParams.width = screenWidth/2
        activity.cl_left.layoutParams = activity.cl_left.layoutParams
        activity.cl_right.layoutParams = activity.cl_right.layoutParams

        if (block) {
            blockStart()
        } else {
            delayStart()
        }
    }

    private fun blockStart() {
        if (index >= TestLayoutRes.TEST_LAYOUT_RES.size) {
            val tvResult = TextView(c)
            tvResult.setPadding(0, 20, 0, 20)
            tvResult.gravity = Gravity.CENTER
            var totalPercent: Float = 0f
            var totalGenAvgTime = 0f
            var totalInflateAvgTime = 0f
            performanceResultList.forEach {
                totalPercent += it.percent
                totalGenAvgTime += it.generateAvgTime
                totalInflateAvgTime += it.inflateAvgTime
            }

            tvResult.text = "result : g(${totalGenAvgTime/performanceResultList.size}) i(${totalInflateAvgTime/performanceResultList.size}) ${(totalPercent/performanceResultList.size) * 100}%"
            activity.ll_result.addView(tvResult, 0, LinearLayout.LayoutParams(-1, -2))
            return
        }
        val layoutId = TestLayoutRes.TEST_LAYOUT_RES[index]

        QxmlInflater.inflateDirect(lf, layoutId, null, false)
        lf.inflate(layoutId, null, false)

        var i = 0
        var time = System.nanoTime()
        i = 0
        time = System.nanoTime()
        while (i < testTimes) {
            QxmlInflater.inflateDirect(lf, layoutId, null, false)
            i++
        }
        val inflateAvgTime = (System.nanoTime() - time)/testTimes
        i = 0
        time = System.nanoTime()
        while (i < testTimes) {
            lf.inflate(layoutId, null, false)
            i++
        }
        val genAvgTime = (System.nanoTime() - time)/testTimes

        val tvResult = TextView(c)
        tvResult.setPadding(0, 20, 0, 20)
        tvResult.gravity = Gravity.CENTER

        val performanceResult = PerformanceResult(layoutId, activity.resources.getResourceEntryName(layoutId), inflateAvgTime, genAvgTime, (inflateAvgTime - genAvgTime)/(inflateAvgTime.toFloat()))
        tvResult.text = "${performanceResult.layoutName}: g(${performanceResult.generateAvgTime}), i(${performanceResult.inflateAvgTime}), ${performanceResult.percent * 100}%"
        activity.ll_result.addView(tvResult, 0, LinearLayout.LayoutParams(-1, -2))
        performanceResultList.add(performanceResult)
        index++
        handler?.postDelayed({
            blockStart()
        }, 500)
    }

    private fun doTimeCount() {
        var time = System.nanoTime()
        val layoutId = TestLayoutRes.TEST_LAYOUT_RES[index]
        if (isGenerate) {
            //activity.cl_left.removeAllViews()
            time = System.nanoTime()
            lf.inflate(layoutId, null, false)

            val generateTime = System.nanoTime() - time
            curGenerateTotalTime += generateTime
            generateTimes++
            //tv_left.text = (curGenerateTotalTime / generateTimes).toString()
        } else {
            //activity.cl_right.removeAllViews()
            time = System.nanoTime()
            QxmlInflater.inflateDirect(lf, layoutId, null, false)
            val inflateTime = System.nanoTime() - time
            curInflateTotalTime += inflateTime
            inflateTimes++
            //tv_right.text = (curInflateTotalTime / inflateTimes).toString()
        }

        isGenerate = !isGenerate

        if (generateTimes >= testTimes && inflateTimes >= testTimes) {
            val tvResult = TextView(c)
            tvResult.setPadding(0, 20, 0, 20)
            tvResult.gravity = Gravity.CENTER
            val inflateAvgTime = curInflateTotalTime / inflateTimes
            val generateAvgTime = curGenerateTotalTime / generateTimes

            val performanceResult = PerformanceResult(layoutId, activity.resources.getResourceEntryName(layoutId), inflateAvgTime, generateAvgTime, (inflateAvgTime - generateAvgTime)/(inflateAvgTime.toFloat()))
            tvResult.text = "${performanceResult.layoutName}: g(${performanceResult.generateAvgTime}), i(${performanceResult.inflateAvgTime}), ${performanceResult.percent * 100}%"
            activity.ll_result.addView(tvResult, 0, LinearLayout.LayoutParams(-1, -2))
            performanceResultList.add(performanceResult)
            index++
            if (index < TestLayoutRes.TEST_LAYOUT_RES.size) {
                QxmlInflater.inflateDirect(lf, layoutId, null, false)
                lf.inflate(layoutId, null, false)
            }
            handler?.postDelayed({
                delayStart()
            }, 100)
        } else {
            handler?.postDelayed(timeCountRunnable, 16)
        }
    }

    private fun delayStart() {
        if (index >= TestLayoutRes.TEST_LAYOUT_RES.size) {
            val tvResult = TextView(c)
            tvResult.setPadding(0, 20, 0, 20)
            tvResult.gravity = Gravity.CENTER
            var totalPercent: Float = 0f
            var totalGenAvgTime = 0f
            var totalInflateAvgTime = 0f
            performanceResultList.forEach {
                totalPercent += it.percent
                totalGenAvgTime += it.generateAvgTime
                totalInflateAvgTime += it.inflateAvgTime
            }

            tvResult.text = "result : g(${totalGenAvgTime/performanceResultList.size}) i(${totalInflateAvgTime/performanceResultList.size}) ${(totalPercent/performanceResultList.size) * 100}%"
            activity.ll_result.addView(tvResult, 0, LinearLayout.LayoutParams(-1, -2))
            return
        }
        loopTime = 0
        curInflateTotalTime = 0
        inflateTimes = 0
        curGenerateTotalTime = 0
        generateTimes = 0
        doTimeCount()
    }

    fun destroy() {
        handler?.removeCallbacksAndMessages(null)
        handler = null
    }
}

data class PerformanceResult(val layoutId: Int, val layoutName: String
                             , val inflateAvgTime: Long, val generateAvgTime: Long
                             , val percent: Float)