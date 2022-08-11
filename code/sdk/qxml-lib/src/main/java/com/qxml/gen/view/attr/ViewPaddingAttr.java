package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;

public interface ViewPaddingAttr {

    class $$PaddingLocalVariable {
        public int paddingLeft = 0;
        public int paddingRight = 0;
        public int paddingTop = 0;
        public int paddingBottom = 0;
    }

    @LocalVar
    $$PaddingLocalVariable __paddingLocalVar = new $$PaddingLocalVariable();

    @Attr(AndroidRS.attr.paddingLeft)
    default void viewPaddingLeft(View view, int paddingLeft) {
        __paddingLocalVar.paddingLeft = paddingLeft;
    }

    @Attr(AndroidRS.attr.paddingRight)
    default void viewPaddingRight(View view, int paddingRight) {
        __paddingLocalVar.paddingRight = paddingRight;
    }

    @Attr(AndroidRS.attr.paddingHorizontal)
    default void viewPaddingHorizontal(View view, int paddingHorizontal) {
        __paddingLocalVar.paddingLeft = paddingHorizontal;
        __paddingLocalVar.paddingRight = paddingHorizontal;
    }

    @Attr(AndroidRS.attr.paddingStart)
    default void viewPaddingStart(View view, int paddingStart) {
        __paddingLocalVar.paddingLeft = paddingStart;
    }

    @Attr(AndroidRS.attr.paddingEnd)
    default void viewPaddingEnd(View view, int paddingEnd) {
        __paddingLocalVar.paddingRight = paddingEnd;
    }

    @Attr(AndroidRS.attr.paddingTop)
    default void viewPaddingTop(View view, int paddingTop) {
        __paddingLocalVar.paddingTop = paddingTop;
    }

    @Attr(AndroidRS.attr.paddingBottom)
    default void viewPaddingBottom(View view, int paddingBottom) {
        __paddingLocalVar.paddingBottom = paddingBottom;
    }

    @Attr(AndroidRS.attr.paddingVertical)
    default void viewPaddingVertical(View view, int paddingVertical) {
        __paddingLocalVar.paddingTop = paddingVertical;
        __paddingLocalVar.paddingBottom = paddingVertical;
    }

    @Attr(AndroidRS.attr.padding)
    default void viewPadding(View view, int padding) {
        __paddingLocalVar.paddingLeft = padding;
        __paddingLocalVar.paddingRight = padding;
        __paddingLocalVar.paddingTop = padding;
        __paddingLocalVar.paddingBottom = padding;
    }

    @OnEnd({AndroidRS.attr.paddingVertical, AndroidRS.attr.paddingHorizontal
            , AndroidRS.attr.paddingLeft, AndroidRS.attr.paddingTop
            , AndroidRS.attr.paddingRight, AndroidRS.attr.paddingBottom
            , AndroidRS.attr.paddingStart, AndroidRS.attr.paddingEnd
            , AndroidRS.attr.padding})
    default void onPaddingEnd(View view) {
        view.setPadding(__paddingLocalVar.paddingLeft, __paddingLocalVar.paddingTop, __paddingLocalVar.paddingRight, __paddingLocalVar.paddingBottom);
    }
}
