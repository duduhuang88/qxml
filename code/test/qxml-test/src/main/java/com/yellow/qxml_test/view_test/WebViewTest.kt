package com.yellow.qxml_test.view_test

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.yellow.qxml_test.R

object WebViewTest {

    @JvmStatic
    fun setUI(view: View) {
        view.findViewById<WebView>(R.id.webView)?.apply {
            loadUrl("http://www.baidu.com")
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            }
        }

    }

}