package com.qxml.qxml_support.gen.view.attr;

import android.view.View;

import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;

public interface CollapsingToolbarAttr extends ViewLocalVar {

    @Attr(RS.attr.layout_collapseMode)
    default void viewLayoutCollapseMode(View view, int layout_collapseMode) {
        if (___cur_layout_param instanceof android.support.design.widget.CollapsingToolbarLayout.LayoutParams) {
            ((android.support.design.widget.CollapsingToolbarLayout.LayoutParams) ___cur_layout_param).setCollapseMode(layout_collapseMode);
        }
    }

    @Attr(RS.attr.layout_collapseParallaxMultiplier)
    default void viewLayoutCollapseParallaxMultiplier(View view, float layout_collapseParallaxMultiplier) {
        if (___cur_layout_param instanceof android.support.design.widget.CollapsingToolbarLayout.LayoutParams) {
            ((android.support.design.widget.CollapsingToolbarLayout.LayoutParams) ___cur_layout_param).setParallaxMultiplier(layout_collapseParallaxMultiplier);
        }
    }

}
