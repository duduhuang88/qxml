package com.qxml.gen.frameLayout;

import android.widget.FrameLayout;

import com.qxml.AndroidRS;
import com.qxml.gen.viewGroup.ViewGroupGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;


@ViewParse(value = FrameLayout.class, layoutParamInit = "new android.widget.FrameLayout.LayoutParams(-1, -1)")
public class FrameLayoutGen extends ViewGroupGen {

    @Attr(AndroidRS.attr.measureAllChildren)
    public void frameLayoutMeasureAllChildren(FrameLayout frameLayout, boolean measureAllChildren) {
        frameLayout.setMeasureAllChildren(measureAllChildren);
    }

}
