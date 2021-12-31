package com.qxml.qxml_androidx.gen.textInputLayout;


import android.annotation.SuppressLint;

import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.textfield.TextInputLayout;
import com.qxml.tools.ReflectUtils;

import java.lang.reflect.Field;

public class TextInputLayoutHelper {

    private static final Field boxCollapsedPaddingTopField = ReflectUtils.getDeclaredFieldOrNull(TextInputLayout.class, "boxCollapsedPaddingTopPx");

    private static final Field collapsingTextHelperField = ReflectUtils.getDeclaredFieldOrNull(TextInputLayout.class, "collapsingTextHelper");

    public static void setBoxCollapsedPaddingTop(TextInputLayout textInputLayout, int boxCollapsedPaddingTop) {
        if (boxCollapsedPaddingTop != 0 && boxCollapsedPaddingTopField != null) {
            try {
                boxCollapsedPaddingTopField.set(textInputLayout, boxCollapsedPaddingTop);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("RestrictedApi")
    public static void resetDefaultCollapsedTextSize(TextInputLayout textInputLayout) {
        if (collapsingTextHelperField != null) {
            try {
                CollapsingTextHelper collapsingTextHelper = (CollapsingTextHelper) collapsingTextHelperField.get(textInputLayout);
                if (collapsingTextHelper != null) {
                    collapsingTextHelper.setCollapsedTextSize(15F);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
