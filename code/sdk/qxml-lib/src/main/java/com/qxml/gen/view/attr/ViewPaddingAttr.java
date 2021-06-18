package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;

public interface ViewPaddingAttr {

    class $$PaddingLocalVariable {
        public int paddingTag = 0;
        public int paddingLeft = 0x80000000;
        public int paddingRight = 0x80000000;
        public int paddingTop = 0x80000000;
        public int paddingBottom = 0x80000000;
    }

    @LocalVar
    $$PaddingLocalVariable __paddingLocalVar = new $$PaddingLocalVariable();

    int PADDING_SET = 1 << 5;
    int PADDING_START_SET = 1 << 6;
    int PADDING_END_SET = 1 << 7;
    int PADDING_VERTICAL_SET = 1 << 8;
    int PADDING_HORIZONTAL_SET = 1 << 9;

    @Attr(AndroidRS.attr.padding)
    default void viewPadding(View view, int padding) {
        __paddingLocalVar.paddingTag = __paddingLocalVar.paddingTag | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_SET;
        __paddingLocalVar.paddingLeft = padding;
        __paddingLocalVar.paddingRight = padding;
        __paddingLocalVar.paddingTop = padding;
        __paddingLocalVar.paddingBottom = padding;
    }

    @Attr(AndroidRS.attr.paddingLeft)
    default void viewPaddingLeft(View view, int paddingLeft) {
        if ((__paddingLocalVar.paddingTag & (com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_SET | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_START_SET | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_HORIZONTAL_SET)) == 0) {
            __paddingLocalVar.paddingLeft = paddingLeft;
        }
    }

    @Attr(AndroidRS.attr.paddingStart)
    default void viewPaddingStart(View view, int paddingStart) {
        if ((__paddingLocalVar.paddingTag & com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_SET) == 0) {
            __paddingLocalVar.paddingTag = __paddingLocalVar.paddingTag | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_START_SET;
            __paddingLocalVar.paddingLeft = paddingStart;
        }
    }

    @Attr(AndroidRS.attr.paddingRight)
    default void viewPaddingRight(View view, int paddingRight) {
        if ((__paddingLocalVar.paddingTag & (com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_SET | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_END_SET | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_HORIZONTAL_SET)) == 0) {
            __paddingLocalVar.paddingRight = paddingRight;
        }
    }

    @Attr(AndroidRS.attr.paddingEnd)
    default void viewPaddingEnd(View view, int paddingEnd) {
        if ((__paddingLocalVar.paddingTag & com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_SET) == 0) {
            __paddingLocalVar.paddingTag = __paddingLocalVar.paddingTag | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_START_SET;
            __paddingLocalVar.paddingRight = paddingEnd;
        }
    }

    @Attr(AndroidRS.attr.paddingTop)
    default void viewPaddingTop(View view, int paddingTop) {
        if ((__paddingLocalVar.paddingTag & (com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_SET | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_VERTICAL_SET)) == 0) {
            __paddingLocalVar.paddingTop = paddingTop;
        }
    }

    @Attr(AndroidRS.attr.paddingBottom)
    default void viewPaddingBottom(View view, int paddingBottom) {
        if ((__paddingLocalVar.paddingTag & (com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_SET | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_VERTICAL_SET)) == 0) {
            __paddingLocalVar.paddingBottom = paddingBottom;
        }
    }

    @Attr(AndroidRS.attr.paddingHorizontal)
    default void viewPaddingHorizontal(View view, int paddingHorizontal) {
        if ((__paddingLocalVar.paddingTag & (com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_SET | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_START_SET)) == 0) {
            __paddingLocalVar.paddingLeft = paddingHorizontal;
            __paddingLocalVar.paddingRight = paddingHorizontal;
        }
    }

    @Attr(AndroidRS.attr.paddingVertical)
    default void viewPaddingVertical(View view, int paddingVertical) {
        if ((__paddingLocalVar.paddingTag & com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_SET) == 0) {
            __paddingLocalVar.paddingTag = __paddingLocalVar.paddingTag | com.qxml.gen.view.attr.ViewPaddingAttr.PADDING_VERTICAL_SET;
            __paddingLocalVar.paddingTop = paddingVertical;
            __paddingLocalVar.paddingBottom = paddingVertical;
        }
    }

    @OnEnd({AndroidRS.attr.paddingVertical, AndroidRS.attr.paddingHorizontal
            , AndroidRS.attr.paddingLeft, AndroidRS.attr.paddingTop
            , AndroidRS.attr.paddingRight, AndroidRS.attr.paddingBottom
            , AndroidRS.attr.paddingStart, AndroidRS.attr.paddingEnd
            , AndroidRS.attr.padding})
    default void onPaddingEnd(View view) {
        if (__paddingLocalVar.paddingLeft == 0x80000000) {
            __paddingLocalVar.paddingLeft = view.getPaddingLeft();
        }
        if (__paddingLocalVar.paddingRight == 0x80000000) {
            __paddingLocalVar.paddingRight = view.getPaddingRight();
        }
        if (__paddingLocalVar.paddingTop == 0x80000000) {
            __paddingLocalVar.paddingTop = view.getPaddingTop();
        }
        if (__paddingLocalVar.paddingBottom == 0x80000000) {
            __paddingLocalVar.paddingBottom = view.getPaddingBottom();
        }
        view.setPadding(__paddingLocalVar.paddingLeft, __paddingLocalVar.paddingTop, __paddingLocalVar.paddingRight, __paddingLocalVar.paddingBottom);
    }
}
