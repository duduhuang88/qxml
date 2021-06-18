package com.qxml.qxml_support.gen.scrollview;

import android.support.v4.widget.NestedScrollView;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.qxml.AndroidRS;
import com.qxml.gen.frameLayout.FrameLayoutGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(NestedScrollView.class)
public class NestScrollViewGen extends FrameLayoutGen {

    @Attr(AndroidRS.attr.fillViewport)
    public void onScrollViewFillViewport(NestedScrollView scrollView, boolean fillViewport) {
        scrollView.setFillViewport(fillViewport);
    }

}
