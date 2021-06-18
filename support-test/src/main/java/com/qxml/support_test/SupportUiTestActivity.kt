package com.qxml.support_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.qxml.support_test.view_test.RecyclerViewTest
import com.yellow.qxml_test.presenter.MainPresenter
import com.yellow.qxml_test.presenter.MainView
import com.yellow.qxml_test.view_test.*

class SupportUiTestActivity: AppCompatActivity(), MainView {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, SupportUiTestActivity::class.java))
        }
    }

    private val mainPresenter by lazy { MainPresenter(this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPresenter
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.destroy()
    }

    override fun setUI(view: View, layoutId: Int) {
        when(layoutId) {
            R.layout.listview_test -> {
                GridViewTest.setUI(view)
                ExpandableListViewTest.setUI(view)
            }
            R.layout.viewstub_test -> {
                ViewStubTest.setUI(view)
            }
            R.layout.webview_test -> {
                WebViewTest.setUI(view)
            }
            R.layout.recyclerview_test -> {
                RecyclerViewTest.setUI(view)
            }
        }
    }
}