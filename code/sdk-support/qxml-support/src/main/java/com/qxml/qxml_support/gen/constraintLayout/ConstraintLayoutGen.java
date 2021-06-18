package com.qxml.qxml_support.gen.constraintLayout;

import android.support.constraint.ConstraintLayout;

import com.qxml.gen.viewGroup.ViewGroupGen;
import com.qxml.qxml_support.AndroidRS;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = ConstraintLayout.class, layoutParamInit = "new android.support.constraint.ConstraintLayout.LayoutParams(-2, -2)")
public class ConstraintLayoutGen extends ViewGroupGen {

    @Attr(RS.attr.layout_optimizationLevel)
    public void constraintLayoutOptimizationLevel(ConstraintLayout constraintLayout, int layout_optimizationLevel) {
        constraintLayout.setOptimizationLevel(layout_optimizationLevel);
    }

    @Attr(RS.attr.constraintSet)
    public void constraintLayoutConstraintSet(ConstraintLayout constraintLayout, int resId) {
        android.support.constraint.ConstraintSet constraintSet = new android.support.constraint.ConstraintSet();
        constraintSet.load(constraintLayout.getContext(), resId);
        constraintLayout.setConstraintSet(constraintSet);
    }

    @OnEnd({AndroidRS.attr.minWidth, AndroidRS.attr.minHeight, AndroidRS.attr.maxWidth, AndroidRS.attr.maxHeight, })
    public void onConstraintLayoutSizeEnd(ConstraintLayout constraintLayout) {
        if (__viewSizeLocalVar.minHeight != 0) {
            constraintLayout.setMinHeight(__viewSizeLocalVar.minHeight);
        }
        if (__viewSizeLocalVar.minWidth != 0) {
            constraintLayout.setMinWidth(__viewSizeLocalVar.minWidth);
        }
        if (__viewSizeLocalVar.maxHeight != 2147483647) {
            constraintLayout.setMaxHeight(__viewSizeLocalVar.maxHeight);
        }
        if (__viewSizeLocalVar.maxWidth != 2147483647) {
            constraintLayout.setMaxWidth(__viewSizeLocalVar.maxWidth);
        }
    }
}
