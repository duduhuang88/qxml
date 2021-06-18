package com.qxml.qxml_androidx.gen.view.attr;

import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.qxml_androidx.RS;
import com.yellow.qxml_annotions.Attr;

public interface AppbarAttr extends ViewLocalVar {

    @Attr(RS.attr.layout_scrollFlags)
    default void viewLayoutScrollFlags(View v, int layout_scrollFlags) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof com.google.android.material.appbar.AppBarLayout.LayoutParams) {
            com.google.android.material.appbar.AppBarLayout.LayoutParams layoutParams = (com.google.android.material.appbar.AppBarLayout.LayoutParams) lp;
            layoutParams.setScrollFlags(layout_scrollFlags);
        }
    }

    @Attr(RS.attr.layout_scrollInterpolator)
    default void viewLayoutScrollInterpolator(View v, int layout_scrollInterpolator) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof com.google.android.material.appbar.AppBarLayout.LayoutParams) {
            com.google.android.material.appbar.AppBarLayout.LayoutParams layoutParams = (com.google.android.material.appbar.AppBarLayout.LayoutParams) lp;
            layoutParams.setScrollInterpolator(android.view.animation.AnimationUtils.loadInterpolator(__context, layout_scrollInterpolator));
        }
    }

}
