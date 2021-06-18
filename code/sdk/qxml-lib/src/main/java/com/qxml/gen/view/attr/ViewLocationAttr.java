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
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof android.widget.LinearLayout.LayoutParams) {
            ((android.widget.LinearLayout.LayoutParams) lp).gravity = gravityFlag;
        } else if (lp instanceof android.widget.FrameLayout.LayoutParams) {
            ((android.widget.FrameLayout.LayoutParams) lp).gravity = gravityFlag;
        }
    }
    
}
