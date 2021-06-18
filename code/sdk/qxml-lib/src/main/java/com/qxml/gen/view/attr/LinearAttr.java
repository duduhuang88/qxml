package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.yellow.qxml_annotions.Attr;

public interface LinearAttr extends ViewLocalVar {

    @Attr(AndroidRS.attr.layout_weight)
    default void viewLayoutWeight(View view, float weight) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof android.widget.LinearLayout.LayoutParams) {
            android.widget.LinearLayout.LayoutParams llp = (android.widget.LinearLayout.LayoutParams) lp;
            llp.weight = weight;
        }
    }

}
