package com.qxml.androidx_test.view_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qxml.androidx_test.R
import com.qxml.helper.QxmlHelper

object RecyclerViewTest {

    @JvmStatic
    fun setUI(view: View) {
        view.findViewById<RecyclerView>(R.id.rv).also { rv ->
            val dataList = mutableListOf<String>().apply {
                for (i in 0 .. 100) {
                    add("$i")
                }
            }
            rv.setHasFixedSize(true)
            QxmlHelper.showDebug = false
            rv.adapter = object : RecyclerView.Adapter<MyHolder>() {
                override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
                    return MyHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_list_test, p0, false))
                }

                override fun onBindViewHolder(p0: MyHolder, p1: Int) {
                    p0.tv.text = dataList[p1]
                }

                override fun getItemCount(): Int = dataList.size

            }
        }
    }

    private class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv by lazy { itemView as TextView }
    }

}