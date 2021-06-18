package com.qxml.qxml_androidx.gen.view;

import android.view.View;

import com.qxml.gen.view.ViewGen;
import com.qxml.helper.AttrHelperKt;
import com.qxml.qxml_androidx.gen.view.attr.AppbarAttr;
import com.qxml.qxml_androidx.gen.view.attr.CollapsingToolbarAttr;
import com.qxml.qxml_androidx.gen.view.attr.ConstrainAttr;
import com.qxml.qxml_androidx.gen.view.attr.CoordinatorAttr;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.ViewReplace;

@ViewReplace
public class ViewCompatGen extends ViewGen implements ConstrainAttr, AppbarAttr, CollapsingToolbarAttr, CoordinatorAttr {

    @Override
    public void viewBackgroundTint(View view, ValueInfo valueInfo) {
        android.content.Context context = view.getContext();
        android.content.res.ColorStateList colorStateList;
        if (valueInfo.isColor()) {
            colorStateList = android.content.res.ColorStateList.valueOf(valueInfo.colorValue);
        } else {
            colorStateList = android.os.Build.VERSION.SDK_INT >= 23 ? context.getColorStateList(valueInfo.resourceId) : context.getResources().getColorStateList(valueInfo.resourceId);
        }
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(colorStateList);
        } else {
            androidx.core.view.ViewCompat.setBackgroundTintList(view, colorStateList);
        }
    }

    @Override
    public void viewBackgroundTintMode(View view, int mode) {
        android.graphics.PorterDuff.Mode tintMode = AttrHelperKt.intToMode(mode, null);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(tintMode);
        } else {
            androidx.core.view.ViewCompat.setBackgroundTintMode(view, tintMode);
        }
    }
}
