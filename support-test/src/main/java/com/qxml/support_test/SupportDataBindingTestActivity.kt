package com.qxml.support_test

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.qxml.QxmlInflater
import com.qxml.helper.QxmlHelper
import com.qxml.support_test.databinding.DataBindingTestBinding
import com.yellow.qxml_test.TestDataBindingInfo

class SupportDataBindingTestActivity: AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, SupportDataBindingTestActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        QxmlHelper.showDebug = true
        val binding = DataBindingUtil.setContentView<DataBindingTestBinding>(this, R.layout.data_binding_test)
        binding.listener = View.OnClickListener {
            Toast.makeText(this, "click", Toast.LENGTH_LONG).show()
            it.isEnabled = false
        }
        binding.testB = true
        binding.top = 200
        binding.info = TestDataBindingInfo("info")
    }

}