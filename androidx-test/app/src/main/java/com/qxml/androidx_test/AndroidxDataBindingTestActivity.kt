package com.qxml.androidx_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.qxml.androidx_test.databinding.DataBindingTestBinding
import com.qxml.helper.QxmlHelper
import com.yellow.qxml_test.TestDataBindingInfo

class AndroidxDataBindingTestActivity: AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, AndroidxDataBindingTestActivity::class.java))
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