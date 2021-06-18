package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;

public interface ViewMarginAttr extends ViewLocalVar {

    class $$MarginLocalVariable {
        public int marginTag = 0;
        public int marginLeft = 0x80000000;
        public int marginTop = 0x80000000;
        public int marginRight = 0x80000000;
        public int marginBottom = 0x80000000;
    }

    @LocalVar
    $$MarginLocalVariable __marginLocalVar = new $$MarginLocalVariable();

    int MARGIN_SET = 1;
    int MARGIN_START_SET = 1 << 1;
    int MARGIN_END_SET = 1 << 2;
    int MARGIN_VERTICAL_SET = 1 << 3;
    int MARGIN_HORIZONTAL_SET = 1 << 4;

    @Attr(AndroidRS.attr.layout_margin)
    default void viewLayoutMargin(View view, int margin) {
        __marginLocalVar.marginTag = __marginLocalVar.marginTag | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_SET;
        __marginLocalVar.marginLeft = margin;
        __marginLocalVar.marginTop = margin;
        __marginLocalVar.marginRight = margin;
        __marginLocalVar.marginBottom = margin;
    }

    @Attr(AndroidRS.attr.layout_marginLeft)
    default void viewLayoutMarginLeft(View view, int marginLeft) {
        if ((__marginLocalVar.marginTag & (com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_SET | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_START_SET | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_HORIZONTAL_SET)) == 0) {
            __marginLocalVar.marginLeft = marginLeft;
        }
    }

    @Attr(AndroidRS.attr.layout_marginStart)
    default void viewLayoutMarginStart(View view, int marginStart) {
        if ((__marginLocalVar.marginTag & com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_SET) == 0) {
            __marginLocalVar.marginTag = __marginLocalVar.marginTag | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_START_SET;
            __marginLocalVar.marginLeft = marginStart;
        }
    }

    @Attr(AndroidRS.attr.layout_marginRight)
    default void viewLayoutMarginRight(View view, int marginRight) {
        if ((__marginLocalVar.marginTag & (com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_SET | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_END_SET  | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_HORIZONTAL_SET)) == 0) {
            __marginLocalVar.marginRight = marginRight;
        }
    }

    @Attr(AndroidRS.attr.layout_marginEnd)
    default void viewLayoutMarginEnd(View view, int marginEnd) {
        if ((__marginLocalVar.marginTag & com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_SET) == 0) {
            __marginLocalVar.marginTag = __marginLocalVar.marginTag | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_END_SET;
            __marginLocalVar.marginRight = marginEnd;
        }
    }

    @Attr(AndroidRS.attr.layout_marginTop)
    default void viewLayoutMarginTop(View view, int marginTop) {
        if ((__marginLocalVar.marginTag & (com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_SET | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_VERTICAL_SET)) == 0) {
            __marginLocalVar.marginTop = marginTop;
        }
    }

    @Attr(AndroidRS.attr.layout_marginBottom)
    default void viewLayoutMarginBottom(View view, int marginBottom) {
        if ((__marginLocalVar.marginTag & (com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_SET | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_VERTICAL_SET)) == 0) {
            __marginLocalVar.marginBottom = marginBottom;
        }
    }

    @Attr(AndroidRS.attr.layout_marginHorizontal)
    default void viewLayoutMarginHorizontal(View view, int marginHorizontal) {
        if ((__marginLocalVar.marginTag & (com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_SET | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_START_SET)) == 0) {
            __marginLocalVar.marginTag = __marginLocalVar.marginTag | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_HORIZONTAL_SET;
            __marginLocalVar.marginLeft = marginHorizontal;
            __marginLocalVar.marginRight = marginHorizontal;
        }
    }

    @Attr(AndroidRS.attr.layout_marginVertical)
    default void viewLayoutMarginVertical(View view, int marginVertical) {
        if ((__marginLocalVar.marginTag & com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_SET) == 0) {
            __marginLocalVar.marginTag = __marginLocalVar.marginTag | com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_VERTICAL_SET;
            __marginLocalVar.marginTop = marginVertical;
            __marginLocalVar.marginBottom = marginVertical;
        }
    }

    @OnEnd({AndroidRS.attr.layout_margin, AndroidRS.attr.layout_marginLeft
            , AndroidRS.attr.layout_marginStart, AndroidRS.attr.layout_marginRight
            , AndroidRS.attr.layout_marginEnd, AndroidRS.attr.layout_marginTop
            , AndroidRS.attr.layout_marginBottom, AndroidRS.attr.layout_marginVertical
            , AndroidRS.attr.layout_marginHorizontal

            , AndroidRS.attr.layout_width, AndroidRS.attr.layout_height
    })
    default void onMarginEnd(View view) {
        if (___cur_layout_param != null) {
            if (__viewLocalVar.layoutWidthSet) {
                ___cur_layout_param.width = __viewLocalVar.layoutWidth;
            }
            if (__viewLocalVar.layoutHeightSet) {
                ___cur_layout_param.height = __viewLocalVar.layoutHeight;
            }
            if (___cur_layout_param instanceof android.view.ViewGroup.MarginLayoutParams) {
                android.view.ViewGroup.MarginLayoutParams mLp = (android.view.ViewGroup.MarginLayoutParams) ___cur_layout_param;
                if (__marginLocalVar.marginLeft == 0x80000000) {
                    __marginLocalVar.marginLeft = mLp.leftMargin;
                }
                if (__marginLocalVar.marginTop == 0x80000000) {
                    __marginLocalVar.marginTop = mLp.topMargin;
                }
                if (__marginLocalVar.marginRight == 0x80000000) {
                    __marginLocalVar.marginRight = mLp.rightMargin;
                }
                if (__marginLocalVar.marginBottom == 0x80000000) {
                    __marginLocalVar.marginBottom = mLp.bottomMargin;
                }
                if (android.os.Build.VERSION.SDK_INT > 17) {
                    if ((__marginLocalVar.marginTag & com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_START_SET) != 0) {
                        mLp.setMarginStart(__marginLocalVar.marginLeft);
                    }
                    if ((__marginLocalVar.marginTag & com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_END_SET) != 0) {
                        mLp.setMarginEnd(__marginLocalVar.marginRight);
                    }
                }
                mLp.setMargins(__marginLocalVar.marginLeft, __marginLocalVar.marginTop, __marginLocalVar.marginRight, __marginLocalVar.marginBottom);
            }
        }
    }
}