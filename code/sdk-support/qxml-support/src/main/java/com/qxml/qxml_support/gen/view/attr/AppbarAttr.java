package com.qxml.qxml_support.gen.view.attr;

import android.view.View;

import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;

public interface AppbarAttr extends ViewLocalVar {

    @Attr(RS.attr.layout_scrollFlags)
    default void viewLayoutScrollFlags(View v, int layout_scrollFlags) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof android.support.design.widget.AppBarLayout.LayoutParams) {
            android.support.design.widget.AppBarLayout.LayoutParams layoutParams = (android.support.design.widget.AppBarLayout.LayoutParams) lp;
            layoutParams.setScrollFlags(layout_scrollFlags);
        }
    }

    @Attr(RS.attr.layout_scrollInterpolator)
    default void viewLayoutScrollInterpolator(View v, int layout_scrollInterpolator) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof android.support.design.widget.AppBarLayout.LayoutParams) {
            android.support.design.widget.AppBarLayout.LayoutParams layoutParams = (android.support.design.widget.AppBarLayout.LayoutParams) lp;
            layoutParams.setScrollInterpolator(android.view.animation.AnimationUtils.loadInterpolator(__context, layout_scrollInterpolator));
        }
    }

}
