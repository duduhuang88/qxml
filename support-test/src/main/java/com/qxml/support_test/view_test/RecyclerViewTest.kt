package com.qxml.support_test.view_test

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.qxml.helper.QxmlHelper
import com.qxml.support_test.R

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