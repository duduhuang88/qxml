package com.qxml.qxml_androidx.gen.tabLayout;


import com.google.android.material.tabs.TabItem;
import com.qxml.AndroidRS;
import com.qxml.gen.view.ViewGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(TabItem.class)
public class TabItemGen extends ViewGen {

    @Attr(AndroidRS.attr.text)
    public void onTabItemText(TabItem tabItem, String text) {
        com.qxml.qxml_androidx.gen.tabLayout.TabLayoutHelper.setTabItemText(tabItem, text);
    }

    @Attr(AndroidRS.attr.icon)
    public void onTabItemIcon(TabItem tabItem, int icon) {
        com.qxml.qxml_androidx.gen.tabLayout.TabLayoutHelper.setTabItemIcon(tabItem, com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, icon));
    }

    @Attr(AndroidRS.attr.layout)
    public void onTabItemLayout(TabItem tabItem, int layout) {
        com.qxml.qxml_androidx.gen.tabLayout.TabLayoutHelper.setTabItemCustomLayout(tabItem, layout);
    }

}
