package com.qxml.gen.view.attr;

import android.view.View;
import android.view.ViewGroup;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;

public interface ViewMarginAttr extends ViewLocalVar {

    class $$MarginLocalVariable {
        public int marginLeft = 0;
        public int marginTop = 0;
        public int marginRight = 0;
        public int marginBottom = 0;
        public int marginStart = 0x80000000;//android.view.ViewGroup.MarginLayoutParams.DEFAULT_MARGIN_RELATIVE
        public int marginEnd = 0x80000000;
    }

    @LocalVar
    $$MarginLocalVariable __marginLocalVar = new $$MarginLocalVariable();

    @Attr(AndroidRS.attr.layout_marginLeft)
    default void viewLayoutMarginLeft(View view, int marginLeft) {
        __marginLocalVar.marginLeft = marginLeft;
    }

    @Attr(AndroidRS.attr.layout_marginRight)
    default void viewLayoutMarginRight(View view, int marginRight) {
        __marginLocalVar.marginRight = marginRight;
    }

    @Attr(AndroidRS.attr.layout_marginHorizontal)
    default void viewLayoutMarginHorizontal(View view, int marginHorizontal) {
        __marginLocalVar.marginLeft = marginHorizontal;
        __marginLocalVar.marginRight = marginHorizontal;
    }

    @Attr(AndroidRS.attr.layout_marginStart)
    default void viewLayoutMarginStart(View view, int marginStart) {
        __marginLocalVar.marginLeft = marginStart;
        __marginLocalVar.marginStart = marginStart;
    }

    @Attr(AndroidRS.attr.layout_marginEnd)
    default void viewLayoutMarginEnd(View view, int marginEnd) {
        __marginLocalVar.marginRight = marginEnd;
        __marginLocalVar.marginEnd = marginEnd;
    }

    @Attr(AndroidRS.attr.layout_marginTop)
    default void viewLayoutMarginTop(View view, int marginTop) {
        __marginLocalVar.marginTop = marginTop;
    }

    @Attr(AndroidRS.attr.layout_marginBottom)
    default void viewLayoutMarginBottom(View view, int marginBottom) {
        __marginLocalVar.marginBottom = marginBottom;
    }

    @Attr(AndroidRS.attr.layout_marginVertical)
    default void viewLayoutMarginVertical(View view, int marginVertical) {
        __marginLocalVar.marginTop = marginVertical;
        __marginLocalVar.marginBottom = marginVertical;
    }

    @Attr(AndroidRS.attr.layout_margin)
    default void viewLayoutMargin(View view, int margin) {
        __marginLocalVar.marginLeft = margin;
        __marginLocalVar.marginTop = margin;
        __marginLocalVar.marginRight = margin;
        __marginLocalVar.marginBottom = margin;
    }

    @OnEnd({AndroidRS.attr.layout_margin, AndroidRS.attr.layout_marginLeft
            , AndroidRS.attr.layout_marginStart, AndroidRS.attr.layout_marginRight
            , AndroidRS.attr.layout_marginEnd, AndroidRS.attr.layout_marginTop
            , AndroidRS.attr.layout_marginBottom, AndroidRS.attr.layout_marginVertical
            , AndroidRS.attr.layout_marginHorizontal
    })
    default void onMarginEnd(View view) {
        if (___cur_layout_param instanceof android.view.ViewGroup.MarginLayoutParams) {
            android.view.ViewGroup.MarginLayoutParams mLp = (android.view.ViewGroup.MarginLayoutParams) ___cur_layout_param;
            if (android.os.Build.VERSION.SDK_INT > 17) {
                if (__marginLocalVar.marginStart != 0x80000000) mLp.setMarginStart(__marginLocalVar.marginStart);
                if (__marginLocalVar.marginEnd != 0x8000000) mLp.setMarginEnd(__marginLocalVar.marginEnd);
            }
            mLp.setMargins(__marginLocalVar.marginLeft, __marginLocalVar.marginTop, __marginLocalVar.marginRight, __marginLocalVar.marginBottom);
        }
    }
}