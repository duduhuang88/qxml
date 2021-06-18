package com.qxml.qxml_androidx.gen.collapsingToolbarLayout;


import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.qxml.tools.ReflectUtils;

import java.lang.reflect.Field;

public class CollapsingToolbarLayoutHelper {

    private static final Field toolbarIdField = ReflectUtils.getDeclaredFieldOrNull(CollapsingToolbarLayout.class, "toolbarId");

    public static void setToolbarId(CollapsingToolbarLayout collapsingToolbarLayout, int toolbarId) {
        try {
            if (toolbarIdField != null) {
                toolbarIdField.setInt(collapsingToolbarLayout, toolbarId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
