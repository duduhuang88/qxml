package com.qxml.gen.view.attr;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.RelativeLayout;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;

@SuppressLint("InlinedApi")
public interface RelativeAttr extends ViewLocalVar {

    class $$RelativeLocalVariable {
        public android.widget.RelativeLayout.LayoutParams rlLp = null;
    }

    @LocalVar
    $$RelativeLocalVariable __relativeLocalVar = new $$RelativeLocalVariable();

    @Attr(value = AndroidRS.attr.layout_alignWithParentIfMissing, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignWithParentIfMissing(View view, boolean layout_alignWithParentIfMissing) {
        __relativeLocalVar.rlLp.alignWithParent = layout_alignWithParentIfMissing;
    }

    @Attr(value = AndroidRS.attr.layout_toLeftOf,requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_toLeftOf(View view, int layout_toLeftOf) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.LEFT_OF, layout_toLeftOf);
    }

    @Attr(value = AndroidRS.attr.layout_toRightOf, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_toRightOf(View view, int layout_toRightOf) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.RIGHT_OF, layout_toRightOf);
    }

    @Attr(value = AndroidRS.attr.layout_above, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_above(View view, int layout_above) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ABOVE, layout_above);
    }

    @Attr(value = AndroidRS.attr.layout_below, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_below(View view, int layout_below) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.BELOW, layout_below);
    }

    @Attr(value = AndroidRS.attr.layout_alignBaseline, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignBaseline(View view, int layout_alignBaseline) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_BASELINE, layout_alignBaseline);
    }

    @Attr(value = AndroidRS.attr.layout_alignLeft, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignLeft(View view, int layout_alignLeft) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_LEFT, layout_alignLeft);
    }

    @Attr(value = AndroidRS.attr.layout_alignTop, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignTop(View view, int layout_alignTop) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_TOP, layout_alignTop);
    }

    @Attr(value = AndroidRS.attr.layout_alignRight, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignRight(View view, int layout_alignRight) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_RIGHT, layout_alignRight);
    }

    @Attr(value = AndroidRS.attr.layout_alignBottom, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignBottom(View view, int layout_alignBottom) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_BOTTOM, layout_alignBottom);
    }

    @Attr(value = AndroidRS.attr.layout_alignParentLeft, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignParentLeft(View view, boolean layout_alignParentLeft) {
        if (layout_alignParentLeft) __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_LEFT);
    }

    @Attr(value = AndroidRS.attr.layout_alignParentTop, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignParentTop(View view, boolean layout_alignParentTop) {
        if (layout_alignParentTop) __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_TOP);
    }

    @Attr(value = AndroidRS.attr.layout_alignParentRight, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignParentRight(View view, boolean layout_alignParentRight) {
        if (layout_alignParentRight) __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_RIGHT);
    }

    @Attr(value = AndroidRS.attr.layout_alignParentBottom, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_alignParentBottom(View view, boolean layout_alignParentBottom) {
        if (layout_alignParentBottom) __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM);
    }

    @Attr(value = AndroidRS.attr.layout_centerInParent, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_centerInParent(View view, boolean layout_centerInParent) {
        if (layout_centerInParent) __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.CENTER_IN_PARENT);
    }

    @Attr(value = AndroidRS.attr.layout_centerHorizontal, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_centerHorizontal(View view, boolean layout_centerHorizontal) {
        if (layout_centerHorizontal) __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.CENTER_HORIZONTAL);
    }

    @Attr(value = AndroidRS.attr.layout_centerVertical, requiredCondition = "__relativeLocalVar_rlLp != null")
    default void relativeLayout_centerVertical(View view, boolean layout_centerVertical) {
        if (layout_centerVertical) __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.CENTER_VERTICAL);
    }

    @Attr(value = AndroidRS.attr.layout_toStartOf, requiredCondition = "android.os.Build.VERSION.SDK_INT >= 17 && __relativeLocalVar_rlLp != null")
    default void relativeLayout_toStartOf(View view, int layout_toStartOf) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.START_OF, layout_toStartOf);
    }

    @Attr(value = AndroidRS.attr.layout_toEndOf, requiredCondition = "android.os.Build.VERSION.SDK_INT >= 17 && __relativeLocalVar_rlLp != null")
    default void relativeLayout_toEndOf(View view, int layout_toEndOf) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.END_OF, layout_toEndOf);
    }

    @Attr(value = AndroidRS.attr.layout_alignStart, requiredCondition = "android.os.Build.VERSION.SDK_INT >= 17 && __relativeLocalVar_rlLp != null")
    default void relativeLayout_alignStart(View view, int layout_alignStart) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_START, layout_alignStart);
    }

    @Attr(value = AndroidRS.attr.layout_alignEnd, requiredCondition = "android.os.Build.VERSION.SDK_INT >= 17 && __relativeLocalVar_rlLp != null")
    default void relativeLayout_alignEnd(View view, int layout_alignEnd) {
        __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_END, layout_alignEnd);
    }

    @Attr(value = AndroidRS.attr.layout_alignParentStart, requiredCondition = "android.os.Build.VERSION.SDK_INT >= 17 && __relativeLocalVar_rlLp != null")
    default void relativeLayout_alignParentStart(View view, boolean layout_alignParentStart) {
        if (layout_alignParentStart) __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_START);
    }

    @Attr(value = AndroidRS.attr.layout_alignParentEnd, requiredCondition = "android.os.Build.VERSION.SDK_INT >= 17 && __relativeLocalVar_rlLp != null")
    default void relativeLayout_alignParentEnd(View view, boolean layout_alignParentEnd) {
        if (layout_alignParentEnd) __relativeLocalVar.rlLp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_END);
    }
}
