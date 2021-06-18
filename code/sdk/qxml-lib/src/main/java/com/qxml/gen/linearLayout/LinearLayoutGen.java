package com.qxml.gen.linearLayout;

import android.view.View;
import android.widget.LinearLayout;

import com.qxml.AndroidRS;
import com.qxml.gen.viewGroup.ViewGroupGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = LinearLayout.class, layoutParamInit = "new android.widget.LinearLayout.LayoutParams(-2, -2)")
public class LinearLayoutGen extends ViewGroupGen {

    @Attr(AndroidRS.attr.baselineAligned)
    public void linearLayoutBaselineAligned(LinearLayout linearLayout, boolean baselineAligned) {
        linearLayout.setBaselineAligned(baselineAligned);
    }

    @Attr(AndroidRS.attr.baselineAlignedChildIndex)
    public void linearLayoutBaselineAlignedChildIndex(LinearLayout linearLayout, int baselineAlignedChildIndex) {
        linearLayout.setBaselineAlignedChildIndex(baselineAlignedChildIndex);
    }

    @Override
    public void viewGravity(View view, int gravityFlag) {
        ((android.widget.LinearLayout)view).setGravity(gravityFlag);
    }

    @Attr(AndroidRS.attr.measureWithLargestChild)
    public void linearLayoutMeasureWithLargestChild(LinearLayout linearLayout, boolean measureWithLargestChild) {
        linearLayout.setMeasureWithLargestChildEnabled(measureWithLargestChild);
    }

    @Attr(AndroidRS.attr.orientation)
    public void linearLayoutOrientation(LinearLayout linearLayout, int orientation) {
        linearLayout.setOrientation(orientation);
    }

    @Attr(AndroidRS.attr.weightSum)
    public void linearLayoutWeightSum(LinearLayout linearLayout, float weightSum) {
        linearLayout.setWeightSum(weightSum);
    }

}
