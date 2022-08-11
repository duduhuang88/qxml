package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.helper.AttrHelperKt;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;

public interface ViewTintAttr extends ViewLocalVar {

    @Attr(AndroidRS.attr.backgroundTint)
    default void viewBackgroundTint(View view, ValueInfo valueInfo) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(valueInfo.getColorStateList(__context));
        } /*else {
            android.support.v4.view.ViewCompat.setBackgroundTintList(view, colorStateList);
        }*/
    }

    @Attr(AndroidRS.attr.foregroundTint)
    default void viewForegroundTint(View view, ValueInfo valueInfo) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            view.setForegroundTintList(valueInfo.getColorStateList(__context));
        }
    }

    @Attr(AndroidRS.attr.backgroundTintMode)
    default void viewBackgroundTintMode(View view, int mode) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(com.qxml.helper.AttrHelperKt.intToMode(mode, null));
        } /*else {
            android.support.v4.view.ViewCompat.setBackgroundTintMode(view, tintMode);
        }*/
    }

    @Attr(AndroidRS.attr.foregroundTintMode)
    default void viewForegroundTintMode(View view, int mode) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            view.setForegroundTintMode(com.qxml.helper.AttrHelperKt.intToMode(mode, null));
        }
    }

}
