package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.helper.AttrHelperKt;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;

public interface ViewTintAttr {

    @Attr(AndroidRS.attr.backgroundTint)
    default void viewBackgroundTint(View view, ValueInfo valueInfo) {
        android.content.Context context = view.getContext();
        android.content.res.ColorStateList colorStateList;
        if (valueInfo.isColor()) {
            colorStateList = android.content.res.ColorStateList.valueOf(valueInfo.colorValue);
        } else {
            colorStateList = android.os.Build.VERSION.SDK_INT >= 23 ? context.getColorStateList(valueInfo.resourceId) : context.getResources().getColorStateList(valueInfo.resourceId);
        }
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(colorStateList);
        } /*else {
            android.support.v4.view.ViewCompat.setBackgroundTintList(view, colorStateList);
        }*/
    }

    @Attr(AndroidRS.attr.foregroundTint)
    default void viewForegroundTint(View view, ValueInfo valueInfo) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            android.content.Context context = view.getContext();
            android.content.res.ColorStateList colorStateList;
            if (valueInfo.isColor()) {
                colorStateList = android.content.res.ColorStateList.valueOf(valueInfo.colorValue);
            } else {
                colorStateList = context.getColorStateList(valueInfo.resourceId);
            }
            view.setForegroundTintList(colorStateList);
        }
    }

    @Attr(AndroidRS.attr.backgroundTintMode)
    default void viewBackgroundTintMode(View view, int mode) {
        android.graphics.PorterDuff.Mode tintMode = com.qxml.helper.AttrHelperKt.intToMode(mode, null);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(tintMode);
        } /*else {
            android.support.v4.view.ViewCompat.setBackgroundTintMode(view, tintMode);
        }*/
    }

    @Attr(AndroidRS.attr.foregroundTintMode)
    default void viewForegroundTintMode(View view, int mode) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            android.graphics.PorterDuff.Mode tintMode = com.qxml.helper.AttrHelperKt.intToMode(mode, null);
            view.setForegroundTintMode(tintMode);
        }
    }

}
