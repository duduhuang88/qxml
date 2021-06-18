package com.yellow.qxml_test.presenter

import android.app.Activity
import android.os.Handler
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.qxml.QxmlInflater
import com.qxml.helper.QxmlHelper
import com.yellow.qxml_test.LogUtil
import com.yellow.qxml_test.R
import com.yellow.qxml_test.TestLayoutRes
import kotlinx.android.synthetic.main.activity_compare_root.*

class MainPresenter(private val activity: Activity, private val mainView: MainView) {

    private val layoutInflater by lazy { activity.layoutInflater }

    private var handler: Handler? = Handler()

    private var index = 0
    private val DELAY_TIME = 500L

    init {
        QxmlHelper.showDebug = false
        activity.setContentView(R.layout.activity_compare_root)
        val screenWidth = activity.resources.displayMetrics.widthPixels
        activity.cl_left.layoutParams.width = screenWidth/2
        activity.cl_right.layoutParams.width = screenWidth/2
        activity.cl_left.layoutParams = activity.cl_left.layoutParams
        activity.cl_right.layoutParams = activity.cl_right.layoutParams
        start()
    }

    private fun start() {
        if (index >= TestLayoutRes.TEST_LAYOUT_RES.size) {
            activity.tv_state.text = "state: 匹配完成"
            return
        }
        val layoutId = TestLayoutRes.TEST_LAYOUT_RES[index]
        QxmlHelper.showDebug = true
        val rightView = addRightView(layoutId)
        //if (true) return
        val leftView = addLeftView(layoutId)

        mainView.setUI(activity.cl_right, layoutId)
        handler?.post {
            var pass = true
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                if (leftView.foreground == null) {
                    pass = false
                    activity.tv_state.text = "state: err ("+activity.resources.getResourceName(layoutId)+" 没有转换)"
                }
            }
            if (pass) {
                compareUI(null, rightView, layoutId)
                /*handler?.postDelayed({
                    index++
                    start()
                }, DELAY_TIME)*/
            }
        }
    }

    private fun compareUI(leftView: View?, rightView: View, layoutId: Int) {
        QxmlHelper.showDebug = false
        val finalLeftView = leftView ?: addLeftView(layoutId)
        if (leftView == null) {
            mainView.setUI(activity.cl_left, layoutId)
        }
        handler?.post {
            finalLeftView.isDrawingCacheEnabled = true
            rightView.isDrawingCacheEnabled = true
            finalLeftView.buildDrawingCache()
            rightView.buildDrawingCache()
            val leftBitmap = finalLeftView.drawingCache
            val rightBitmap = rightView.drawingCache
            if (leftBitmap.sameAs(rightBitmap)) {
                val leftViewScrollable = finalLeftView.canScrollVertically(1)
                val rightViewScrollable = rightView.canScrollVertically(1)
                if (leftViewScrollable == rightViewScrollable) {
                    if (leftViewScrollable) {
                        finalLeftView.scrollBy(0, finalLeftView.measuredHeight)
                        rightView.scrollBy(0, rightView.measuredHeight)
                        compareUI(finalLeftView, rightView, layoutId)
                    } else {
                        activity.tv_state.text = "pass ("+activity.resources.getResourceName(layoutId)+")"
                        handler?.postDelayed({
                            index++
                            start()
                        }, DELAY_TIME)
                    }
                } else {
                    reportErr(layoutId)
                }
            } else {
                reportErr(layoutId)
            }
        }
    }

    private fun reportErr(layoutId: Int) {
        activity.tv_state.text = "err ("+activity.resources.getResourceName(layoutId)+"不匹配)"
        activity.tv_state.setOnClickListener {
            if (activity.cl_right.alpha == 0.5f) {
                activity.cl_right.alpha = 1f
                activity.cl_right.translationX = 0f
            } else {
                activity.cl_right.alpha = 0.5f
                activity.cl_right.translationX = (-activity.cl_right.measuredWidth).toFloat()
            }
        }
    }

    private fun addLeftView(layoutId: Int): View {
        activity.cl_left.removeAllViews()
        layoutInflater.inflate(layoutId, activity.cl_left, true)
        return activity.cl_left.getChildAt(0)
    }

    private fun addRightView(layoutId: Int): View {
        activity.cl_right.removeAllViews()
        QxmlInflater.inflateDirect(layoutInflater, layoutId, activity.cl_right, true)
        return activity.cl_right.getChildAt(0)
    }

    fun destroy() {
        handler?.removeCallbacksAndMessages(null)
        handler = null
    }
}