package com.qxml.gen.listView;

import android.widget.ListView;

import com.qxml.AndroidRS;
import com.qxml.gen.listView.absListView.AbsListViewGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = ListView.class)
public class ListViewGen extends AbsListViewGen {

    @Attr(AndroidRS.attr.entries)
    public void listViewEntries(ListView listView, int entriesId) {
        com.qxml.gen.listView.ListViewHelper.setDefaultAdapter(listView, __context, entriesId);
    }

    @Attr(AndroidRS.attr.divider)
    public void listViewDivider(ListView listView, int drawableId) {
        //listView.setDivider(android.support.v4.content.ContextCompat.getDrawable(listView.getContext(), drawableId));
        listView.setDivider(com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, drawableId));
    }

    @Attr(AndroidRS.attr.dividerHeight)
    public void listViewDividerHeight(ListView listView, int dividerHeight) {
        listView.setDividerHeight(dividerHeight);
    }

    @Attr(AndroidRS.attr.footerDividersEnabled)
    public void listViewFooterDividersEnabled(ListView listView, boolean footerDividersEnabled) {
        listView.setFooterDividersEnabled(footerDividersEnabled);
    }

    @Attr(AndroidRS.attr.headerDividersEnabled)
    public void listViewHeaderDividersEnabled(ListView listView, boolean headerDividersEnabled) {
        listView.setHeaderDividersEnabled(headerDividersEnabled);
    }
}
