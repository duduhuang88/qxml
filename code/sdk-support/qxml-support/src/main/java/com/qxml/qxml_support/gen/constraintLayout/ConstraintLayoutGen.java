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
        {
            android.support.constraint.ConstraintSet constraintSet = new android.support.constraint.ConstraintSet();
            constraintSet.load(constraintLayout.getContext(), resId);
            constraintLayout.setConstraintSet(constraintSet);
        }
    }

    @OnEnd({AndroidRS.attr.minWidth})
    public void onConstraintLayoutMinWidthEnd(ConstraintLayout constraintLayout) {
        constraintLayout.setMinWidth(__viewSizeLocalVar.minWidth);
    }

    @OnEnd({AndroidRS.attr.minHeight})
    public void onConstraintLayoutMinHeightEnd(ConstraintLayout constraintLayout) {
        constraintLayout.setMinHeight(__viewSizeLocalVar.minHeight);
    }

    @OnEnd({AndroidRS.attr.maxWidth})
    public void onConstraintLayoutMaxWidthEnd(ConstraintLayout constraintLayout) {
        constraintLayout.setMaxWidth(__viewSizeLocalVar.maxWidth);
    }

    @OnEnd({AndroidRS.attr.maxHeight})
    public void onConstraintLayoutMaxHeightEnd(ConstraintLayout constraintLayout) {
        constraintLayout.setMaxHeight(__viewSizeLocalVar.maxHeight);
    }
}
