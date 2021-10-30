package com.qxml.gen.button;

import android.widget.ToggleButton;

import com.qxml.tools.ReflectUtils;

import java.lang.reflect.Field;

public class ToggleButtonHelper {

    private static final Field mDisabledAlphaField = ReflectUtils.getDeclaredFieldOrNull(ToggleButton.class, "mDisabledAlpha");

    public static void setDisabledAlpha(ToggleButton toggleButton, float mDisabledAlpha) {
        try {
            if (mDisabledAlphaField != null) {
                mDisabledAlphaField.set(toggleButton, mDisabledAlpha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
