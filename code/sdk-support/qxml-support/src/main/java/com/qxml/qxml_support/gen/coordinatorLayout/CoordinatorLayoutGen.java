package com.qxml.qxml_support.gen.coordinatorLayout;

import android.support.design.widget.CoordinatorLayout;

import com.qxml.gen.viewGroup.ViewGroupGen;
import com.qxml.qxml_support.RS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = CoordinatorLayout.class, layoutParamInit = "new android.support.design.widget.CoordinatorLayout.LayoutParams(0, 0)")
public class CoordinatorLayoutGen extends ViewGroupGen {

    @Attr(RS.attr.keylines)
    public void coordinatorLayoutKeyLines(CoordinatorLayout coordinatorLayout, int res) {
        com.qxml.qxml_support.gen.coordinatorLayout.CoordinatorLayoutHelper.setMKeylines(coordinatorLayout, ___resources, res);
    }

    @Attr(RS.attr.statusBarBackground)
    public void coordinatorLayoutStatusBarBackground(CoordinatorLayout coordinatorLayout, ValueInfo valueInfo) {
        coordinatorLayout.setStatusBarBackground(valueInfo.getReferenceDrawable(__context, ___resources));
    }

}
