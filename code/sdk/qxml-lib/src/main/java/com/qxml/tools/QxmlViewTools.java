package com.qxml.tools;

import android.view.ViewGroup;

import java.lang.reflect.Method;

public class QxmlViewTools {

    private static final Method generateDefaultLayoutParamsMethod = ReflectUtils.getDeclaredMethodOrNull(ViewGroup.class, "generateDefaultLayoutParams");

    public static ViewGroup.LayoutParams generateDefaultLayoutParams(ViewGroup viewGroup) {
        try {
            if (generateDefaultLayoutParamsMethod == null) return null;
            return (ViewGroup.LayoutParams) generateDefaultLayoutParamsMethod.invoke(viewGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
