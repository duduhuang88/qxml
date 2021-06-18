package com.qxml.gen.scrollView;

import android.widget.ScrollView;

import com.qxml.AndroidRS;
import com.qxml.gen.frameLayout.FrameLayoutGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = ScrollView.class)
public class ScrollViewGen extends FrameLayoutGen {

    @Attr(AndroidRS.attr.fillViewport)
    public void onScrollViewFillViewport(ScrollView scrollView, boolean fillViewport) {
        scrollView.setFillViewport(fillViewport);
    }

}
