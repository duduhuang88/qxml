package com.yellow.qxml_test.view_test

import android.view.View
import android.widget.ArrayAdapter
import android.widget.GridView
import com.qxml.helper.QxmlHelper
import com.yellow.qxml_test.R

object GridViewTest {

    @JvmStatic
    fun setUI(view: View) {
        QxmlHelper.showDebug = false
        view.findViewById<GridView>(R.id.grid_view)?.also { gridView ->
            val stringList = mutableListOf<String>().apply {
                addAll(gridView.context.resources.getStringArray(R.array.sports_test))
            }
            gridView.adapter = object : ArrayAdapter<String>(gridView.context,
                R.layout.item_list_test, stringList) {
            }
        }
    }

}