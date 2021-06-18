package com.qxml.gen.tableLayout;

import android.widget.TableLayout;

import com.qxml.AndroidRS;
import com.qxml.gen.linearLayout.LinearLayoutGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = TableLayout.class, layoutParamInit = "new android.widget.TableLayout.LayoutParams(-1, -2)")
public class TableLayoutGen extends LinearLayoutGen {

    @Attr(AndroidRS.attr.stretchColumns)
    public void onTableLayoutStretchColumns(TableLayout tableLayout, String stretchColumns) {
        com.qxml.gen.tableLayout.TableLayoutHelper.setStretchedColumns(tableLayout, stretchColumns);
    }

    @Attr(AndroidRS.attr.shrinkColumns)
    public void onTableLayoutShrinkColumns(TableLayout tableLayout, String shrinkColumns) {
        com.qxml.gen.tableLayout.TableLayoutHelper.setShrinkColumns(tableLayout, shrinkColumns);
    }

    @Attr(AndroidRS.attr.collapseColumns)
    public void onTableLayoutCollapseColumns(TableLayout tableLayout, String collapseColumns) {
        com.qxml.gen.tableLayout.TableLayoutHelper.setCollapseColumns(tableLayout, collapseColumns);
    }

}
