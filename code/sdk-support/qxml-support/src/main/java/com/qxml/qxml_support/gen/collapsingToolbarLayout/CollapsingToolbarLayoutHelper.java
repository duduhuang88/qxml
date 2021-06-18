package com.qxml.qxml_support.gen.collapsingToolbarLayout;

import android.support.design.widget.CollapsingToolbarLayout;

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
