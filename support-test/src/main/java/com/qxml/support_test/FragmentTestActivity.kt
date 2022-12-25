package com.qxml.support_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.qxml.QxmlInflater
import com.qxml.qxml_support.helper.FragmentHelper
import com.yellow.qxml_test.LogUtil
import kotlinx.android.synthetic.main.activity_fragment_test.*

class FragmentTestActivity: AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, FragmentTestActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FragmentHelper.toString()
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