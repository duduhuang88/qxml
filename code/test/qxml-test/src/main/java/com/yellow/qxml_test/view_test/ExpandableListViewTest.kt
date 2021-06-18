package com.yellow.qxml_test.view_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import com.qxml.QxmlInflater
import com.qxml.helper.QxmlHelper
import com.yellow.qxml_test.R

object ExpandableListViewTest {

    @JvmStatic
    fun setUI(view: View) {
        val testData = mutableListOf<ExpandableMode>()
        val childList = mutableListOf<ChildDataBean>().apply {
            add(ChildDataBean("香蕉", 1))
            add(ChildDataBean("苹果", 2))
            add(ChildDataBean("西瓜", 3))
        }
        val mode1 = ExpandableMode("水果", childList)
        testData.add(mode1)
        val childList2 = mutableListOf<ChildDataBean>().apply {
            add(ChildDataBean("马铃薯", 1))
            add(ChildDataBean("生菜", 2))
        }
        val mode2 = ExpandableMode("蔬菜", childList2)
        testData.add(mode2)
        view.findViewById<ExpandableListView>(R.id.expandable_list_view)?.also { expandableListView ->
            expandableListView.setAdapter(object : BaseExpandableListAdapter() {
                override fun getGroupCount(): Int = testData.size

                override fun getChildrenCount(groupPosition: Int): Int = testData[groupPosition].child.size

                override fun getGroup(groupPosition: Int): Any = testData[groupPosition]

                override fun getChild(groupPosition: Int, childPosition: Int): Any = testData[groupPosition].child[childPosition]

                override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

                override fun getChildId(groupPosition: Int, childPosition: Int): Long = 0

                override fun hasStableIds(): Boolean = false

                override fun getGroupView(
                    groupPosition: Int,
                    isExpanded: Boolean,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    QxmlHelper.showDebug = false
                    val tv = (convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.item_list_test, parent, false)) as TextView
                    tv.text = "Title: ${testData[groupPosition].groupName}"
                    return tv
                }

                override fun getChildView(
                    groupPosition: Int,
                    childPosition: Int,
                    isLastChild: Boolean,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    QxmlHelper.showDebug = false
                    val tv = (convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.item_list_test, parent, false)) as TextView
                    tv.text = " child: ${testData[groupPosition].child[childPosition].childName}"
                    return tv
                }

                override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
                    return true
                }
            })
        }
    }

}

private data class ExpandableMode(val groupName: String, val child: List<ChildDataBean>)

private data class ChildDataBean(val childName: String, val Number: Int)