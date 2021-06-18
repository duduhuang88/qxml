package com.qxml.qxml_support.gen.view.attr;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.view.attr.ViewLocationAttr;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;

public interface CoordinatorAttr extends ViewLocationAttr {

    class $$CoordinatorLocalVariable {
        public int gravity = 0;
        public int layout_anchor = -1;
        public int layout_anchorGravity = 0;
        public int layout_keyline = -1;
        public int layout_insetEdge = 0;
        public int layout_dodgeInsetEdges = 0;
        public String layout_behavior = null;
    }

    @LocalVar
    $$CoordinatorLocalVariable __coordinatorLocalVar = new $$CoordinatorLocalVariable();

    @Attr(RS.attr.layout_anchor)
    default void viewLayoutCoordinatorLayout_anchor(View view, int who) {
        __coordinatorLocalVar.layout_anchor = who;
    }

    @Attr(RS.attr.layout_anchorGravity)
    default void viewLayoutCoordinatorLayout_anchorGravity(View view, int anchorGravity) {
        __coordinatorLocalVar.layout_anchorGravity = anchorGravity;
    }

    @Attr(RS.attr.layout_keyline)
    default void viewLayoutCoordinatorLayout_keyline(View view, int keyline) {
        __coordinatorLocalVar.layout_keyline = keyline;
    }

    @Attr(RS.attr.layout_insetEdge)
    default void viewLayoutCoordinatorLayout_insetEdge(View view, int layout_insetEdge) {
        __coordinatorLocalVar.layout_insetEdge = layout_insetEdge;
    }

    @Attr(RS.attr.layout_dodgeInsetEdges)
    default void viewLayoutCoordinatorLayout_dodgeInsetEdges(View view, int layout_dodgeInsetEdges) {
        __coordinatorLocalVar.layout_dodgeInsetEdges = layout_dodgeInsetEdges;
    }

    @Attr(RS.attr.layout_behavior)
    default void viewLayoutCoordinatorLayout_behavior(View view, String layout_behavior) {
        __coordinatorLocalVar.layout_behavior = layout_behavior;
    }

    @Override
    default void viewLayoutGravity(View view, int gravityFlag) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof android.widget.LinearLayout.LayoutParams) {
            ((android.widget.LinearLayout.LayoutParams) lp).gravity = gravityFlag;
        } else if (lp instanceof android.widget.FrameLayout.LayoutParams) {
            ((android.widget.FrameLayout.LayoutParams) lp).gravity = gravityFlag;
        } else if (lp instanceof android.support.design.widget.CoordinatorLayout.LayoutParams) {
            __coordinatorLocalVar.gravity = gravityFlag;
        } else if (lp instanceof android.support.v4.widget.DrawerLayout.LayoutParams) {
            ((android.support.v4.widget.DrawerLayout.LayoutParams) lp).gravity = gravityFlag;
        }
    }

    @OnEnd({RS.attr.layout_anchor, RS.attr.layout_anchorGravity
            , RS.attr.layout_keyline, RS.attr.layout_insetEdge
            , RS.attr.layout_dodgeInsetEdges, RS.attr.layout_behavior
            , AndroidRS.attr.layout_gravity})
    default void onCoordinatorLayoutEnd(View v) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof android.support.design.widget.CoordinatorLayout.LayoutParams) {
            android.support.design.widget.CoordinatorLayout.LayoutParams layoutParams = (android.support.design.widget.CoordinatorLayout.LayoutParams) lp;
            layoutParams.gravity = __coordinatorLocalVar.gravity;
            layoutParams.setAnchorId(__coordinatorLocalVar.layout_anchor);
            layoutParams.anchorGravity = __coordinatorLocalVar.layout_anchorGravity;
            layoutParams.keyline = __coordinatorLocalVar.layout_keyline;
            layoutParams.insetEdge = __coordinatorLocalVar.layout_insetEdge;
            layoutParams.dodgeInsetEdges = __coordinatorLocalVar.layout_dodgeInsetEdges;
            if (__coordinatorLocalVar.layout_behavior != null) {
                layoutParams.setBehavior(com.qxml.qxml_support.gen.coordinatorLayout.CoordinatorLayoutHelper.parseBehavior(__context, null, __coordinatorLocalVar.layout_behavior));
            }
        }
    }

}
