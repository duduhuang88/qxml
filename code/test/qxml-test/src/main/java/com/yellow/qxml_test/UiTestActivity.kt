package com.yellow.qxml_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yellow.qxml_test.presenter.MainPresenter
import com.yellow.qxml_test.presenter.MainView
import com.yellow.qxml_test.view_test.*

class UiTestActivity: BaseActivity(), MainView {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, UiTestActivity::class.java))
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
        }
    }

}