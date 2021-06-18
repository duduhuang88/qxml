package com.qxml.qxml_support.gen.toolbar;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;

import com.qxml.tools.ReflectUtils;

import java.lang.reflect.Field;

public class ToolbarHelper {

    private static final Field mGravityField = ReflectUtils.getDeclaredFieldOrNull(Toolbar.class, "mGravity");
    private static final Field mButtonGravityField = ReflectUtils.getDeclaredFieldOrNull(Toolbar.class, "mButtonGravity");
    private static final Field mMaxButtonHeightField = ReflectUtils.getDeclaredFieldOrNull(Toolbar.class, "mMaxButtonHeight");
    private static final Field mCollapseIconField = ReflectUtils.getDeclaredFieldOrNull(Toolbar.class, "mCollapseIcon");
    private static final Field mCollapseDescriptionField = ReflectUtils.getDeclaredFieldOrNull(Toolbar.class, "mCollapseDescription");


    public static void setMGravity(Toolbar toolbar, int gravity) {
        try {
            if (mGravityField != null) {
                mGravityField.setInt(toolbar, gravity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMButtonGravityField(Toolbar toolbar, int gravity) {
        try {
            if (mButtonGravityField != null) {
                mButtonGravityField.setInt(toolbar, gravity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMMaxButtonHeightField(Toolbar toolbar, int gravity) {
        try {
            if (mMaxButtonHeightField != null) {
                mMaxButtonHeightField.setInt(toolbar, gravity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMCollapseIconField(Toolbar toolbar, Drawable drawable) {
        try {
            if (mCollapseIconField != null) {
                mCollapseIconField.set(toolbar, drawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMCollapseDescriptionField(Toolbar toolbar, String desc) {
        try {
            if (mCollapseDescriptionField != null) {
                mCollapseDescriptionField.set(toolbar, desc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
