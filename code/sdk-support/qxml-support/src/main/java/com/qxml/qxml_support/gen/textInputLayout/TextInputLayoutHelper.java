package com.qxml.qxml_support.gen.textInputLayout;


import android.annotation.SuppressLint;
import android.support.design.widget.CollapsingTextHelper;
import android.support.design.widget.TextInputLayout;

import com.qxml.tools.ReflectUtils;

import java.lang.reflect.Field;

public class TextInputLayoutHelper {

    private static final Field counterTextAppearanceField = ReflectUtils.getDeclaredFieldOrNull(TextInputLayout.class, "counterTextAppearance");
    private static final Field counterOverflowTextAppearanceField = ReflectUtils.getDeclaredFieldOrNull(TextInputLayout.class, "counterOverflowTextAppearance");

    private static final Field boxCollapsedPaddingTopField = ReflectUtils.getDeclaredFieldOrNull(TextInputLayout.class, "boxCollapsedPaddingTopPx");

    private static final Field collapsingTextHelperField = ReflectUtils.getDeclaredFieldOrNull(TextInputLayout.class, "collapsingTextHelper");

    public static void setCounterTextAppearance(TextInputLayout textInputLayout, int counterTextAppearance) {
        if (counterTextAppearance != 0 && counterTextAppearanceField != null) {
            try {
                counterTextAppearanceField.set(textInputLayout, counterTextAppearance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setBoxCollapsedPaddingTop(TextInputLayout textInputLayout, int boxCollapsedPaddingTop) {
        if (boxCollapsedPaddingTop != 0 && boxCollapsedPaddingTopField != null) {
            try {
                boxCollapsedPaddingTopField.set(textInputLayout, boxCollapsedPaddingTop);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setCounterOverflowTextAppearance(TextInputLayout textInputLayout, int counterOverflowTextAppearance) {
        if (counterOverflowTextAppearance != 0 && counterOverflowTextAppearanceField != null) {
            try {
                counterOverflowTextAppearanceField.set(textInputLayout, counterOverflowTextAppearance);
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
