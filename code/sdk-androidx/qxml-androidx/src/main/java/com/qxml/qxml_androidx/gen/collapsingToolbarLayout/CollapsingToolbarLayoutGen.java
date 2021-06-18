package com.qxml.qxml_androidx.gen.collapsingToolbarLayout;


import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.qxml.gen.frameLayout.FrameLayoutGen;
import com.qxml.qxml_androidx.RS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = CollapsingToolbarLayout.class, layoutParamInit = "new com.google.android.material.appbar.CollapsingToolbarLayout.LayoutParams(-1, -1)")
public class CollapsingToolbarLayoutGen extends FrameLayoutGen {

    public static class $$CollapsingToolbarLocalVariable {
        public int expandedMarginStart = 0;
        public int expandedMarginTop = 0;
        public int expandedMarginEnd = 0;
        public int expandedMarginBottom = 0;
        public boolean expandedMarginStartSet = false;
        public boolean expandedMarginTopSet = false;
        public boolean expandedMarginEndSet = false;
        public boolean expandedMarginBottomSet = false;
    }

    @LocalVar
    public $$CollapsingToolbarLocalVariable __collapsingToolbarLocalVar = new $$CollapsingToolbarLocalVariable();


    @Attr(RS.attr.expandedTitleGravity)
    public void collapsingToolbarLayoutExpandedTitleGravity(CollapsingToolbarLayout collapsingToolbarLayout, int expandedTitleGravity) {
        collapsingToolbarLayout.setExpandedTitleGravity(expandedTitleGravity);
    }

    @Attr(RS.attr.collapsedTitleGravity)
    public void collapsingToolbarLayoutCollapsedTitleGravity(CollapsingToolbarLayout collapsingToolbarLayout, int collapsedTitleGravity) {
        collapsingToolbarLayout.setCollapsedTitleGravity(collapsedTitleGravity);
    }

    @Attr(RS.attr.expandedTitleMargin)
    public void collapsingToolbarLayoutExpandedTitleMargin(CollapsingToolbarLayout collapsingToolbarLayout, int expandedTitleMargin) {
        if (!__collapsingToolbarLocalVar.expandedMarginStartSet) {
            collapsingToolbarLayout.setExpandedTitleMarginStart(expandedTitleMargin);
        }
        if (!__collapsingToolbarLocalVar.expandedMarginTopSet) {
            collapsingToolbarLayout.setExpandedTitleMarginTop(expandedTitleMargin);
        }
        if (!__collapsingToolbarLocalVar.expandedMarginEndSet) {
            collapsingToolbarLayout.setExpandedTitleMarginEnd(expandedTitleMargin);
        }
        if (!__collapsingToolbarLocalVar.expandedMarginBottomSet) {
            collapsingToolbarLayout.setExpandedTitleMarginBottom(expandedTitleMargin);
        }
    }

    @Attr(RS.attr.expandedTitleMarginStart)
    public void collapsingToolbarLayoutExpandedTitleMarginStart(CollapsingToolbarLayout collapsingToolbarLayout, int expandedTitleMarginStart) {
        __collapsingToolbarLocalVar.expandedMarginStartSet = true;
        collapsingToolbarLayout.setExpandedTitleMarginStart(expandedTitleMarginStart);
    }

    @Attr(RS.attr.expandedTitleMarginTop)
    public void collapsingToolbarLayoutExpandedTitleMarginTop(CollapsingToolbarLayout collapsingToolbarLayout, int expandedTitleMarginTop) {
        __collapsingToolbarLocalVar.expandedMarginTopSet = true;
        collapsingToolbarLayout.setExpandedTitleMarginTop(expandedTitleMarginTop);
    }

    @Attr(RS.attr.expandedTitleMarginEnd)
    public void collapsingToolbarLayoutExpandedTitleMarginEnd(CollapsingToolbarLayout collapsingToolbarLayout, int expandedTitleMarginEnd) {
        __collapsingToolbarLocalVar.expandedMarginEndSet = true;
        collapsingToolbarLayout.setExpandedTitleMarginEnd(expandedTitleMarginEnd);
    }

    @Attr(RS.attr.expandedTitleMarginBottom)
    public void collapsingToolbarLayoutExpandedTitleMarginBottom(CollapsingToolbarLayout collapsingToolbarLayout, int expandedTitleMarginBottom) {
        __collapsingToolbarLocalVar.expandedMarginBottomSet = true;
        collapsingToolbarLayout.setExpandedTitleMarginBottom(expandedTitleMarginBottom);
    }

    @Attr(RS.attr.titleEnabled)
    public void collapsingToolbarLayoutTitleEnabled(CollapsingToolbarLayout collapsingToolbarLayout, boolean titleEnabled) {
        if (!titleEnabled) {
            collapsingToolbarLayout.setTitleEnabled(false);
        }
    }

    @Attr(RS.attr.title)
    public void collapsingToolbarLayoutTitle(CollapsingToolbarLayout collapsingToolbarLayout, String title) {
        collapsingToolbarLayout.setTitle(title);
    }

    @Attr(RS.attr.expandedTitleTextAppearance)
    public void collapsingToolbarLayoutExpandedTitleTextAppearance(CollapsingToolbarLayout collapsingToolbarLayout, int expandedTitleTextAppearance) {
        collapsingToolbarLayout.setExpandedTitleTextAppearance(expandedTitleTextAppearance);
    }

    @Attr(RS.attr.collapsedTitleTextAppearance)
    public void collapsingToolbarLayoutCollapsedTitleTextAppearance(CollapsingToolbarLayout collapsingToolbarLayout, int collapsedTitleTextAppearance) {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(collapsedTitleTextAppearance);
    }

    @Attr(RS.attr.scrimVisibleHeightTrigger)
    public void collapsingToolbarLayoutScrimVisibleHeightTrigger(CollapsingToolbarLayout collapsingToolbarLayout, int scrimVisibleHeightTrigger) {
        collapsingToolbarLayout.setScrimVisibleHeightTrigger(scrimVisibleHeightTrigger);
    }

    @Attr(RS.attr.scrimAnimationDuration)
    public void collapsingToolbarLayoutScrimAnimationDuration(CollapsingToolbarLayout collapsingToolbarLayout, int scrimAnimationDuration) {
        collapsingToolbarLayout.setScrimAnimationDuration(((long)scrimAnimationDuration));
    }

    @Attr(RS.attr.contentScrim)
    public void collapsingToolbarLayoutContentScrim(CollapsingToolbarLayout collapsingToolbarLayout, ValueInfo contentScrim) {
        collapsingToolbarLayout.setContentScrim(contentScrim.getReferenceDrawable(__context, ___resources));
    }

    @Attr(RS.attr.statusBarScrim)
    public void collapsingToolbarLayoutStatusBarScrim(CollapsingToolbarLayout collapsingToolbarLayout, ValueInfo statusBarScrim) {
        collapsingToolbarLayout.setStatusBarScrim(statusBarScrim.getReferenceDrawable(__context, ___resources));
    }

    @Attr(RS.attr.toolbarId)
    public void collapsingToolbarLayoutToolbarId(CollapsingToolbarLayout collapsingToolbarLayout, int toolbarId) {
        com.qxml.qxml_androidx.gen.collapsingToolbarLayout.CollapsingToolbarLayoutHelper.setToolbarId(collapsingToolbarLayout, toolbarId);
    }

}
