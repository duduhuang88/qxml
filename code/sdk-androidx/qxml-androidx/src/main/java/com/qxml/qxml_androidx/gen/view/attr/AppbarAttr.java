package com.qxml.qxml_androidx.gen.view.attr;

import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.qxml_androidx.RS;
import com.yellow.qxml_annotions.Attr;

public interface AppbarAttr extends ViewLocalVar {

    @Attr(RS.attr.layout_scrollFlags)
    default void viewLayoutScrollFlags(View v, int layout_scrollFlags) {
        if (___cur_layout_param instanceof com.google.android.material.appbar.AppBarLayout.LayoutParams) {
            ((com.google.android.material.appbar.AppBarLayout.LayoutParams) ___cur_layout_param).setScrollFlags(layout_scrollFlags);
        }
    }

    @Attr(RS.attr.layout_scrollInterpolator)
    default void viewLayoutScrollInterpolator(View v, int layout_scrollInterpolator) {
        if (___cur_layout_param instanceof com.google.android.material.appbar.AppBarLayout.LayoutParams) {
            ((com.google.android.material.appbar.AppBarLayout.LayoutParams) ___cur_layout_param).setScrollInterpolator(android.view.animation.AnimationUtils.loadInterpolator(__context, layout_scrollInterpolator));
        }
    }

}
