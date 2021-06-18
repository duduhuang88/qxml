package com.qxml.gen.listView;

import android.view.View;
import android.widget.GridView;

import com.qxml.AndroidRS;
import com.qxml.gen.listView.absListView.AbsListViewGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(GridView.class)
public class GridViewGen extends AbsListViewGen {

    @Attr(AndroidRS.attr.horizontalSpacing)
    public void onGridViewHorizontalSpacing(GridView gridView, int horizontalSpacing) {
        gridView.setHorizontalSpacing(horizontalSpacing);
    }

    @Attr(AndroidRS.attr.verticalSpacing)
    public void onGridViewVerticalSpacing(GridView gridView, int verticalSpacing) {
        gridView.setVerticalSpacing(verticalSpacing);
    }

    @Attr(AndroidRS.attr.stretchMode)
    public void onGridViewStretchMode(GridView gridView, int stretchModeEnum) {
        gridView.setStretchMode(stretchModeEnum);
    }

    @Attr(AndroidRS.attr.columnWidth)
    public void onGridViewColumnWidth(GridView gridView, int columnWidth) {
        gridView.setColumnWidth(columnWidth);
    }

    @Attr(AndroidRS.attr.numColumns)
    public void onGridViewNumColumns(GridView gridView, int numColumns) {
        gridView.setNumColumns(numColumns);
    }

    @Override
    public void viewGravity(View view, int gravityFlag) {
        ((android.widget.GridView)view).setGravity(gravityFlag);
    }
}
