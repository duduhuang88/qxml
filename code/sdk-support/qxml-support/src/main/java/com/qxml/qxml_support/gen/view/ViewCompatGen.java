package com.qxml.qxml_support.gen.view;

import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;

import com.qxml.gen.view.ViewGen;
import com.qxml.qxml_support.gen.view.attr.AppbarAttr;
import com.qxml.qxml_support.gen.view.attr.CollapsingToolbarAttr;
import com.qxml.qxml_support.gen.view.attr.ConstrainAttr;
import com.qxml.qxml_support.gen.view.attr.CoordinatorAttr;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.ViewReplace;

@ViewReplace
public class ViewCompatGen extends ViewGen implements ConstrainAttr, CoordinatorAttr, AppbarAttr, CollapsingToolbarAttr {

    @Override
    public void viewStart(View view, boolean useless) {
        if (___cur_layout_param instanceof android.widget.RelativeLayout.LayoutParams) {
            __relativeLocalVar.rlLp = (android.widget.RelativeLayout.LayoutParams) ___cur_layout_param;
        } else if (___cur_layout_param instanceof android.support.constraint.ConstraintLayout.LayoutParams) {
            __constrainLocalVar.constraintLp = (android.support.constraint.ConstraintLayout.LayoutParams) ___cur_layout_param;
        }
    }

    @Override
    public void viewBackgroundTint(View view, ValueInfo valueInfo) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(valueInfo.getColorStateList(__context));
        } else {
            android.support.v4.view.ViewCompat.setBackgroundTintList(view, valueInfo.getColorStateList(__context));
        }
    }

    @Override
    public void viewBackgroundTintMode(View view, int mode) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(com.qxml.helper.AttrHelperKt.intToMode(mode, null));
        } else {
            android.support.v4.view.ViewCompat.setBackgroundTintMode(view, com.qxml.helper.AttrHelperKt.intToMode(mode, null));
        }
    }
}
