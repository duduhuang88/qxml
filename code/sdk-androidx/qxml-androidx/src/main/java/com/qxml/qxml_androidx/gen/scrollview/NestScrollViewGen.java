package com.qxml.qxml_androidx.gen.scrollview;


import androidx.core.widget.NestedScrollView;

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
