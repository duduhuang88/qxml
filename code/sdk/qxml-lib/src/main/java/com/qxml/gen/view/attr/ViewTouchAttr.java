package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.yellow.qxml_annotions.Attr;

public interface ViewTouchAttr {

    @Attr(AndroidRS.attr.clickable)
    default void viewClickable(View view, boolean clickable) {
        view.setClickable(clickable);
    }

    @Attr(AndroidRS.attr.longClickable)
    default void viewLongClickable(View view, boolean longClickable) {
        view.setLongClickable(longClickable);
    }

    @Attr(AndroidRS.attr.onClick)
    default void viewOnclick(View view, String clickFuncName) {
        view.setOnClickListener(new com.qxml.gen.view.helper.DeclaredOnClickListener(view, clickFuncName));
    }

    @Attr(AndroidRS.attr.filterTouchesWhenObscured)
    default void viewFilterTouchesWhenObscured(View view, boolean filter) {
        view.setFilterTouchesWhenObscured(filter);
    }
    
}
