package com.qxml.gen.tableLayout;

import android.widget.TableRow;

import com.qxml.gen.linearLayout.LinearLayoutGen;
import com.yellow.qxml_annotions.ViewParse;

//属性在TableRowAttr中
@ViewParse(value = TableRow.class, layoutParamInit = "new android.widget.TableRow.LayoutParams(-1, -2)")
public class TableRowGen extends LinearLayoutGen {


}
