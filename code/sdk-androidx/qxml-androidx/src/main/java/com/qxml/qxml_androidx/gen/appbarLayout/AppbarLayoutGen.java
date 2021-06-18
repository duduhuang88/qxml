package com.qxml.qxml_androidx.gen.appbarLayout;

import android.view.View;


import com.google.android.material.appbar.AppBarLayout;
import com.qxml.gen.linearLayout.LinearLayoutGen;
import com.qxml.qxml_androidx.AndroidRS;
import com.qxml.qxml_androidx.RS;
import com.qxml.qxml_androidx.gen.view.attr.CoordinatorAttr;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = AppBarLayout.class, layoutParamInit = "new com.google.android.material.appbar.AppBarLayout.LayoutParams(-1, -2)")
public class AppbarLayoutGen extends LinearLayoutGen implements CoordinatorAttr {

    @Attr(RS.attr.expanded)
    public void appBarLayoutExpanded(AppBarLayout appBarLayout, boolean expanded) {
        appBarLayout.setExpanded(expanded);
    }

    @Attr(AndroidRS.attr.touchscreenBlocksFocus)
    public void appBarLayoutTouchscreenBlocksFocus(AppBarLayout appBarLayout, boolean touchscreenBlocksFocus) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            appBarLayout.setTouchscreenBlocksFocus(touchscreenBlocksFocus);
        }
    }

    @Attr(RS.attr.liftOnScroll)
    public void appBarLayoutLiftOnScroll(AppBarLayout appBarLayout, boolean liftOnScroll) {
        appBarLayout.setLiftOnScroll(liftOnScroll);
    }

    @Override
    public void onCoordinatorLayoutEnd(View v) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) {
            androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams layoutParams = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) lp;
            layoutParams.gravity = __coordinatorLocalVar.gravity;
            layoutParams.setAnchorId(__coordinatorLocalVar.layout_anchor);
            layoutParams.anchorGravity = __coordinatorLocalVar.layout_anchorGravity;
            layoutParams.keyline = __coordinatorLocalVar.layout_keyline;
            layoutParams.insetEdge = __coordinatorLocalVar.layout_insetEdge;
            layoutParams.dodgeInsetEdges = __coordinatorLocalVar.layout_dodgeInsetEdges;
            if (__coordinatorLocalVar.layout_behavior != null) {
                layoutParams.setBehavior(com.qxml.qxml_androidx.gen.coordinatorLayout.CoordinatorLayoutHelper.parseBehavior(__context, null, __coordinatorLocalVar.layout_behavior));
            } else {
                layoutParams.setBehavior(new com.google.android.material.appbar.AppBarLayout.Behavior());
            }
        }
    }

    @Attr(RS.attr.elevation)
    public void appBarLayoutElevation(AppBarLayout appBarLayout, float elevation) {
        appBarLayout.setTargetElevation(elevation);
    }
}
