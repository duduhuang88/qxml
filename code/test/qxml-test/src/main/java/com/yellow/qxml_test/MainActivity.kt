package com.yellow.qxml_test

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TableLayout
import com.qxml.helper.QxmlHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Field

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QxmlHelper.showDebug = true
        setContentView(R.layout.activity_main)
    }

    fun onUiTest(v: View?) {
        UiTestActivity.start(this)
    }

    fun onDataBindingTest(v: View) {

    }

    fun onFragmentTest(v: View) {

    }

    fun onPerformanceTest(v: View) {
        val times = et_times.text.toString().toIntOrNull()?:50
        PerformanceTestActivity.start(this, times)
    }

    fun printAllFields(obj: Any) {
        val cls: Class<*> = obj.javaClass
        val fields: Array<Field> = cls.declaredFields
        LogUtil.e("f ${obj.javaClass} 共有 " + fields.size + "个属性")
        for (field in fields) {
            field.isAccessible = true
            try {
                LogUtil.e("f private: " + field.name + ":" + field.get(obj))
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

}