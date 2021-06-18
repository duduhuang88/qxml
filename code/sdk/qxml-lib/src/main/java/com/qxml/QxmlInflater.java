package com.qxml;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxml.callback.CreateViewListener;
import com.qxml.helper.QxmlHelper;
import com.qxml.tools.ReflectUtils;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;


public class QxmlInflater {

    private static final Method VIEW_ON_FINISH_INFLATE_METHOD = ReflectUtils.getDeclaredMethodOrNull(View.class, "onFinishInflate");
    public static CreateViewListener createViewListener = null;

    public static View generate(LayoutInflater inflater, int layoutId, @Nullable ViewGroup root, boolean attachTo) {
        return inflater.inflate(layoutId, root, attachTo);
    }

    public static void setShowDebug(boolean showDebug) {
        QxmlHelper.showDebug = showDebug;
    }

    public static View inflateDirect(Context context , int layoutId)  {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    public static View inflateDirect(Context context, int layoutId, @Nullable ViewGroup parentView) {
        return LayoutInflater.from(context).inflate(layoutId, parentView, parentView != null);
    }

    public static View inflateDirect(Context context, int layoutId, @Nullable ViewGroup parentView, boolean attachTo) {
        return LayoutInflater.from(context).inflate(layoutId, parentView, attachTo);
    }

    public static View inflateDirect(LayoutInflater layoutInflater, int layoutId) {
        return layoutInflater.inflate(layoutId, null, false);
    }

    public static View inflateDirect(LayoutInflater layoutInflater, int layoutId, @Nullable ViewGroup parentView) {
        return layoutInflater.inflate(layoutId, parentView, parentView != null);
    }

    public static View inflateDirect(LayoutInflater layoutInflater, int layoutId, @Nullable ViewGroup parentView, boolean attachTo) {
        return layoutInflater.inflate(layoutId, parentView, attachTo);
    }

    public static View inflate(LayoutInflater layoutInflater, int layoutId, @Nullable ViewGroup parentView, boolean attachTo) {
        return layoutInflater.inflate(layoutId, parentView, attachTo);
    }

    public static void callViewOnFinishInflate(View view) {
        try {
            if (VIEW_ON_FINISH_INFLATE_METHOD != null) {
                VIEW_ON_FINISH_INFLATE_METHOD.invoke(view);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
