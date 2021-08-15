package com.qxml.qxml_support.gen.swipeRefreshLayout;

import android.support.v4.widget.SwipeRefreshLayout;

import com.qxml.AndroidRS;
import com.qxml.gen.viewGroup.ViewGroupGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(SwipeRefreshLayout.class)
public class SwipeRefreshLayoutGen extends ViewGroupGen {


    @Attr(AndroidRS.attr.enabled)
    public void swipeRefreshLayoutEnabled(SwipeRefreshLayout swipeRefreshLayout, boolean enabled) {
        swipeRefreshLayout.setEnabled(enabled);
    }

}
