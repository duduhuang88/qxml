package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.yellow.qxml_annotions.Attr;

public interface ViewLocationAttr extends ViewLocalVar {

    @Attr(AndroidRS.attr.gravity)
    default void viewGravity(View view, int gravityFlag) {
        //需要的view请重写实现
    }

    @Attr(AndroidRS.attr.layout_gravity)
    default void viewLayoutGravity(View view, int gravityFlag) {
        if (___cur_layout_param instanceof android.widget.LinearLayout.LayoutParams) {
            ((android.widget.LinearLayout.LayoutParams) ___cur_layout_param).gravity = gravityFlag;
        } else if (___cur_layout_param instanceof android.widget.FrameLayout.LayoutParams) {
            ((android.widget.FrameLayout.LayoutParams) ___cur_layout_param).gravity = gravityFlag;
        }
    }
    
}
