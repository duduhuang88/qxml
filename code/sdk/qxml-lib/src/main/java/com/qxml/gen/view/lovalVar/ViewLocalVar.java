package com.qxml.gen.view.lovalVar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.yellow.qxml_annotions.LocalVar;

public interface ViewLocalVar {
    /**
     * 仅提供引用
     */
    @SuppressLint("StaticFieldLeak")
    Context __context = new ContextWrapper(null);
    /**
     * 仅提供引用
     */
    TypedValue ___typedValue = new TypedValue();
    /**
     * 仅提供引用
     */
    Resources ___resources = new Resources(null, null, null);
    /**
     * 仅提供引用
     */
    float ___scaledDensity = 0f;
    /**
     * 仅提供引用
     */
    ViewGroup.LayoutParams ___cur_layout_param = new ViewGroup.LayoutParams(-2, -2);

}
