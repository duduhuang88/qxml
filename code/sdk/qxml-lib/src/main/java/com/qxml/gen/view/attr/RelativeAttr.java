package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;

public interface RelativeAttr extends ViewLocalVar {

    class $$RelativeLocalVariable {
        public int tag = 0;
        public boolean alignWithParent = false;
        public int layout_toLeftOf = 0;
        public int layout_toRightOf = 0;
        public int layout_above = 0;
        public int layout_below = 0;
        public int layout_alignBaseline = 0;
        public int layout_alignLeft = 0;
        public int layout_alignTop = 0;
        public int layout_alignRight = 0;
        public int layout_alignBottom = 0;
        public boolean layout_alignParentLeft = false;
        public boolean layout_alignParentTop = false;
        public boolean layout_alignParentRight = false;
        public boolean layout_alignParentBottom = false;
        public boolean layout_centerInParent = false;
        public boolean layout_centerHorizontal = false;
        public boolean layout_centerVertical = false;
        public int layout_toStartOf = 0;
        public int layout_toEndOf = 0;
        public int layout_alignStart = 0;
        public int layout_alignEnd = 0;
        public boolean layout_alignParentStart = false;
        public boolean layout_alignParentEnd = false;
    }

    @LocalVar
    $$RelativeLocalVariable __relativeLocalVar = new $$RelativeLocalVariable();

    @Attr(AndroidRS.attr.layout_alignWithParentIfMissing)
    default void relativeLayout_alignWithParentIfMissing(View view, boolean layout_alignWithParentIfMissing) {
        __relativeLocalVar.alignWithParent = layout_alignWithParentIfMissing;
    }

    @Attr(AndroidRS.attr.layout_toLeftOf)
    default void relativeLayout_toLeftOf(View view, int layout_toLeftOf) {
        __relativeLocalVar.layout_toLeftOf = layout_toLeftOf;
    }

    @Attr(AndroidRS.attr.layout_toRightOf)
    default void relativeLayout_toRightOf(View view, int layout_toRightOf) {
        __relativeLocalVar.layout_toRightOf = layout_toRightOf;
    }

    @Attr(AndroidRS.attr.layout_above)
    default void relativeLayout_above(View view, int layout_above) {
        __relativeLocalVar.layout_above = layout_above;
    }

    @Attr(AndroidRS.attr.layout_below)
    default void relativeLayout_below(View view, int layout_below) {
        __relativeLocalVar.layout_below = layout_below;
    }

    @Attr(AndroidRS.attr.layout_alignBaseline)
    default void relativeLayout_alignBaseline(View view, int layout_alignBaseline) {
        __relativeLocalVar.layout_alignBaseline = layout_alignBaseline;
    }

    @Attr(AndroidRS.attr.layout_alignLeft)
    default void relativeLayout_alignLeft(View view, int layout_alignLeft) {
        __relativeLocalVar.layout_alignLeft = layout_alignLeft;
    }

    @Attr(AndroidRS.attr.layout_alignTop)
    default void relativeLayout_alignTop(View view, int layout_alignTop) {
        __relativeLocalVar.layout_alignTop = layout_alignTop;
    }

    @Attr(AndroidRS.attr.layout_alignRight)
    default void relativeLayout_alignRight(View view, int layout_alignRight) {
        __relativeLocalVar.layout_alignRight = layout_alignRight;
    }

    @Attr(AndroidRS.attr.layout_alignBottom)
    default void relativeLayout_alignBottom(View view, int layout_alignBottom) {
        __relativeLocalVar.layout_alignBottom = layout_alignBottom;
    }

    @Attr(AndroidRS.attr.layout_alignParentLeft)
    default void relativeLayout_alignParentLeft(View view, boolean layout_alignParentLeft) {
        __relativeLocalVar.layout_alignParentLeft = layout_alignParentLeft;
    }

    @Attr(AndroidRS.attr.layout_alignParentTop)
    default void relativeLayout_alignParentTop(View view, boolean layout_alignParentTop) {
        __relativeLocalVar.layout_alignParentTop = layout_alignParentTop;
    }

    @Attr(AndroidRS.attr.layout_alignParentRight)
    default void relativeLayout_alignParentRight(View view, boolean layout_alignParentRight) {
        __relativeLocalVar.layout_alignParentRight = layout_alignParentRight;
    }

    @Attr(AndroidRS.attr.layout_alignParentBottom)
    default void relativeLayout_alignParentBottom(View view, boolean layout_alignParentBottom) {
        __relativeLocalVar.layout_alignParentBottom = layout_alignParentBottom;
    }

    @Attr(AndroidRS.attr.layout_centerInParent)
    default void relativeLayout_centerInParent(View view, boolean layout_centerInParent) {
        __relativeLocalVar.layout_centerInParent = layout_centerInParent;
    }

    @Attr(AndroidRS.attr.layout_centerHorizontal)
    default void relativeLayout_centerHorizontal(View view, boolean layout_centerHorizontal) {
        __relativeLocalVar.layout_centerHorizontal = layout_centerHorizontal;
    }

    @Attr(AndroidRS.attr.layout_centerVertical)
    default void relativeLayout_centerVertical(View view, boolean layout_centerVertical) {
        __relativeLocalVar.layout_centerVertical = layout_centerVertical;
    }

    @Attr(AndroidRS.attr.layout_toStartOf)
    default void relativeLayout_toStartOf(View view, int layout_toStartOf) {
        __relativeLocalVar.layout_toStartOf = layout_toStartOf;
    }

    @Attr(AndroidRS.attr.layout_toEndOf)
    default void relativeLayout_toEndOf(View view, int layout_toEndOf) {
        __relativeLocalVar.layout_toEndOf = layout_toEndOf;
    }

    @Attr(AndroidRS.attr.layout_alignStart)
    default void relativeLayout_alignStart(View view, int layout_alignStart) {
        __relativeLocalVar.layout_alignStart = layout_alignStart;
    }

    @Attr(AndroidRS.attr.layout_alignEnd)
    default void relativeLayout_alignEnd(View view, int layout_alignEnd) {
        __relativeLocalVar.layout_alignEnd = layout_alignEnd;
    }

    @Attr(AndroidRS.attr.layout_alignParentStart)
    default void relativeLayout_alignParentStart(View view, boolean layout_alignParentStart) {
        __relativeLocalVar.layout_alignParentStart = layout_alignParentStart;
    }

    @Attr(AndroidRS.attr.layout_alignParentEnd)
    default void relativeLayout_alignParentEnd(View view, boolean layout_alignParentEnd) {
        __relativeLocalVar.layout_alignParentEnd = layout_alignParentEnd;
    }

    @OnEnd({AndroidRS.attr.layout_alignWithParentIfMissing, AndroidRS.attr.layout_toLeftOf,
            AndroidRS.attr.layout_toRightOf, AndroidRS.attr.layout_above,
            AndroidRS.attr.layout_below, AndroidRS.attr.layout_alignBaseline,
            AndroidRS.attr.layout_alignLeft, AndroidRS.attr.layout_alignTop,
            AndroidRS.attr.layout_alignRight, AndroidRS.attr.layout_alignBottom,
            AndroidRS.attr.layout_alignParentLeft, AndroidRS.attr.layout_alignParentTop,
            AndroidRS.attr.layout_alignParentRight, AndroidRS.attr.layout_alignParentBottom,
            AndroidRS.attr.layout_centerInParent, AndroidRS.attr.layout_centerHorizontal,
            AndroidRS.attr.layout_toStartOf, AndroidRS.attr.layout_toEndOf,
            AndroidRS.attr.layout_alignStart, AndroidRS.attr.layout_alignEnd,
            AndroidRS.attr.layout_alignParentStart, AndroidRS.attr.layout_alignParentEnd,
            AndroidRS.attr.layout_centerVertical})
    default void onViewRelativeEnd(View view) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof android.widget.RelativeLayout.LayoutParams) {
            android.widget.RelativeLayout.LayoutParams rlp = (android.widget.RelativeLayout.LayoutParams) lp;
            if (__relativeLocalVar.alignWithParent) {
                rlp.alignWithParent = true;
            }
            if (__relativeLocalVar.layout_toLeftOf != 0) {
                rlp.addRule(android.widget.RelativeLayout.LEFT_OF, __relativeLocalVar.layout_toLeftOf);
            }
            if (__relativeLocalVar.layout_toRightOf != 0) {
                rlp.addRule(android.widget.RelativeLayout.RIGHT_OF, __relativeLocalVar.layout_toRightOf);
            }
            if (__relativeLocalVar.layout_above != 0) {
                rlp.addRule(android.widget.RelativeLayout.ABOVE, __relativeLocalVar.layout_above);
            }
            if (__relativeLocalVar.layout_below != 0) {
                rlp.addRule(android.widget.RelativeLayout.BELOW, __relativeLocalVar.layout_below);
            }
            if (__relativeLocalVar.layout_alignBaseline != 0) {
                rlp.addRule(android.widget.RelativeLayout.ALIGN_BASELINE, __relativeLocalVar.layout_alignBaseline);
            }
            if (__relativeLocalVar.layout_alignLeft != 0) {
                rlp.addRule(android.widget.RelativeLayout.ALIGN_LEFT, __relativeLocalVar.layout_alignLeft);
            }
            if (__relativeLocalVar.layout_alignTop != 0) {
                rlp.addRule(android.widget.RelativeLayout.ALIGN_TOP, __relativeLocalVar.layout_alignTop);
            }
            if (__relativeLocalVar.layout_alignRight != 0) {
                rlp.addRule(android.widget.RelativeLayout.ALIGN_RIGHT, __relativeLocalVar.layout_alignRight);
            }
            if (__relativeLocalVar.layout_alignBottom != 0) {
                rlp.addRule(android.widget.RelativeLayout.ALIGN_BOTTOM, __relativeLocalVar.layout_alignBottom);
            }
            if (__relativeLocalVar.layout_alignParentLeft) {
                rlp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_LEFT);
            }
            if (__relativeLocalVar.layout_alignParentTop) {
                rlp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_TOP);
            }
            if (__relativeLocalVar.layout_alignParentRight) {
                rlp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_RIGHT);
            }
            if (__relativeLocalVar.layout_alignParentBottom) {
                rlp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM);
            }
            if (__relativeLocalVar.layout_centerInParent) {
                rlp.addRule(android.widget.RelativeLayout.CENTER_IN_PARENT);
            }
            if (__relativeLocalVar.layout_centerHorizontal) {
                rlp.addRule(android.widget.RelativeLayout.CENTER_HORIZONTAL);
            }
            if (__relativeLocalVar.layout_centerVertical) {
                rlp.addRule(android.widget.RelativeLayout.CENTER_VERTICAL);
            }

            if (android.os.Build.VERSION.SDK_INT >= 17) {
                if (__relativeLocalVar.layout_toStartOf != 0) {
                    rlp.addRule(android.widget.RelativeLayout.START_OF, __relativeLocalVar.layout_toStartOf);
                }
                if (__relativeLocalVar.layout_toEndOf != 0) {
                    rlp.addRule(android.widget.RelativeLayout.END_OF, __relativeLocalVar.layout_toEndOf);
                }
                if (__relativeLocalVar.layout_alignStart != 0) {
                    rlp.addRule(android.widget.RelativeLayout.ALIGN_START, __relativeLocalVar.layout_alignStart);
                }
                if (__relativeLocalVar.layout_alignEnd != 0) {
                    rlp.addRule(android.widget.RelativeLayout.ALIGN_END, __relativeLocalVar.layout_alignEnd);
                }
                if (__relativeLocalVar.layout_alignParentStart) {
                    rlp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_START);
                }
                if (__relativeLocalVar.layout_alignParentEnd) {
                    rlp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_END);
                }
            }
        }
    }
    
}
