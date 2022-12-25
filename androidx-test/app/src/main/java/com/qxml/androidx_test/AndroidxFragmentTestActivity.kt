package com.qxml.androidx_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.qxml.QxmlInflater
import com.qxml.qxml_androidx.helper.FragmentHelper
import com.yellow.qxml_test.LogUtil

class AndroidxFragmentTestActivity: FragmentActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, AndroidxFragmentTestActivity::class.java))
        }
    }

    private val tv_top_time by lazy { findViewById<TextView>(R.id.tv_top_time) }
    private val tv_bottom_time by lazy { findViewById<TextView>(R.id.tv_bottom_time) }
    private val fl_top by lazy { findViewById<FrameLayout>(R.id.fl_top) }
    private val fl_bottom by lazy { findViewById<FrameLayout>(R.id.fl_bottom) }

    override fun onCreate(savedInstanceState: Bundle?) {
        //FragmentHelper.toString()
        //先初始化下
        TestFragment()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)
        var time = 0L
        var spend = 0L

        time = System.nanoTime()
        QxmlInflater.inflateDirect(this, R.layout.fragment_test_top, fl_top, true)
        spend = System.nanoTime() - time
        tv_top_time.text = spend.toString()

        time = System.nanoTime()
        layoutInflater.inflate(R.layout.fragment_test_bottom, fl_bottom, true)
        spend = System.nanoTime() - time
        tv_bottom_time.text = spend.toString()

        val fragmentTopTv1 = (fl_top.getChildAt(0) as ViewGroup).getChildAt(0)
        val fragmentTopTv2 = (fl_top.getChildAt(0) as ViewGroup).getChildAt(1)

        LogUtil.e("top "+fragmentTopTv1+" "+fragmentTopTv1.tag+" "+fragmentTopTv1.id+" "+ R.id.fragment_out_top)
        LogUtil.e("top "+fragmentTopTv2+" "+fragmentTopTv2.tag+" "+fragmentTopTv2.id+" "+ R.id.fragment_include_top2)
        val fragmentBottomTv1 = (fl_bottom.getChildAt(0) as ViewGroup).getChildAt(0)
        val fragmentBottomTv2 = (fl_bottom.getChildAt(0) as ViewGroup).getChildAt(1)

        //LogUtil.e("fragments "+supportFragmentManager.fragments)
    }

}