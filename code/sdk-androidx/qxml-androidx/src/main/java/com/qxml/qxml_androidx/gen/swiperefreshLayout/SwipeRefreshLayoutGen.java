package com.qxml.qxml_androidx.gen.swiperefreshLayout;

import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
