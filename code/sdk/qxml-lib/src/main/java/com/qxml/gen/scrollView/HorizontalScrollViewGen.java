package com.qxml.gen.scrollView;

import android.widget.HorizontalScrollView;

import com.qxml.AndroidRS;
import com.qxml.gen.frameLayout.FrameLayoutGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(HorizontalScrollView.class)
public class HorizontalScrollViewGen extends FrameLayoutGen {

    @Attr(AndroidRS.attr.fillViewport)
    public void onScrollViewFillViewport(HorizontalScrollView horizontalScrollView, boolean fillViewport) {
        horizontalScrollView.setFillViewport(fillViewport);
    }

}
