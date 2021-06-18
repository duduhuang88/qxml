package com.qxml.qxml_support.gen.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.viewGroup.ViewGroupGen;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(RecyclerView.class)
public class RecyclerViewGen extends ViewGroupGen {

    public static class $$RecyclerViewLocalVariable {
        public boolean fastScrollEnabled = false;
        public int fastScrollHorizontalThumbDrawable = 0;
        public int fastScrollHorizontalTrackDrawable = 0;
        public int fastScrollVerticalThumbDrawable = 0;
        public int fastScrollVerticalTrackDrawable = 0;

        public String layoutManager = null;
        public boolean stackFromEnd = false;
        public int spanCount = 1;
        public boolean reverseLayout = false;
        public int managerOrientation = 1;
    }

    @LocalVar
    public $$RecyclerViewLocalVariable __recyclerViewLocalVar = new $$RecyclerViewLocalVariable();

    @Attr(RS.attr.fastScrollEnabled)
    public void onRecyclerViewFastScrollEnabled(RecyclerView recyclerView, boolean fastScrollEnabled) {
        __recyclerViewLocalVar.fastScrollEnabled = fastScrollEnabled;
    }

    @Attr(RS.attr.fastScrollHorizontalThumbDrawable)
    public void onRecyclerViewFastScrollHorizontalThumbDrawable(RecyclerView recyclerView, int fastScrollHorizontalThumbDrawable) {
        __recyclerViewLocalVar.fastScrollHorizontalThumbDrawable = fastScrollHorizontalThumbDrawable;
    }

    @Attr(RS.attr.fastScrollHorizontalTrackDrawable)
    public void onRecyclerViewFastScrollHorizontalTrackDrawable(RecyclerView recyclerView, int fastScrollHorizontalTrackDrawable) {
        __recyclerViewLocalVar.fastScrollHorizontalTrackDrawable = fastScrollHorizontalTrackDrawable;
    }

    @Attr(RS.attr.fastScrollVerticalThumbDrawable)
    public void onRecyclerViewFastScrollVerticalThumbDrawable(RecyclerView recyclerView, int fastScrollVerticalThumbDrawable) {
        __recyclerViewLocalVar.fastScrollVerticalThumbDrawable = fastScrollVerticalThumbDrawable;
    }

    @Attr(RS.attr.fastScrollVerticalTrackDrawable)
    public void onRecyclerViewFastScrollVerticalTrackDrawable(RecyclerView recyclerView, int fastScrollVerticalTrackDrawable) {
        __recyclerViewLocalVar.fastScrollVerticalTrackDrawable = fastScrollVerticalTrackDrawable;
    }

    @Attr(RS.attr.stackFromEnd)
    public void onRecyclerViewStackFromEnd(RecyclerView recyclerView, boolean stackFromEnd) {
        __recyclerViewLocalVar.stackFromEnd = stackFromEnd;
    }

    @Attr(RS.attr.spanCount)
    public void onRecyclerViewSpanCount(RecyclerView recyclerView, int spanCount) {
        __recyclerViewLocalVar.spanCount = spanCount;
    }

    @Attr(RS.attr.reverseLayout)
    public void onRecyclerViewReverseLayout(RecyclerView recyclerView, boolean reverseLayout) {
        __recyclerViewLocalVar.reverseLayout = reverseLayout;
    }

    @Attr(RS.attr.layoutManager)
    public void onRecyclerViewLayoutManager(RecyclerView recyclerView, String layoutManager) {
        __recyclerViewLocalVar.layoutManager = layoutManager;
    }

    @Attr(AndroidRS.attr.orientation)
    public void viewLayoutOrientation(View view, int orientation) {
        __recyclerViewLocalVar.managerOrientation = orientation;
    }


    @OnEnd({RS.attr.stackFromEnd, RS.attr.spanCount, RS.attr.reverseLayout, RS.attr.layoutManager, AndroidRS.attr.orientation})
    public void onRecyclerViewManagerEnd(RecyclerView recyclerView) {
        if (__recyclerViewLocalVar.layoutManager != null) {
            if ("android.support.v7.widget.GridLayoutManager".equals(__recyclerViewLocalVar.layoutManager) || "GridLayoutManager".equals(__recyclerViewLocalVar.layoutManager)) {
                android.support.v7.widget.GridLayoutManager gridLayoutManager = new android.support.v7.widget.GridLayoutManager(__context, __recyclerViewLocalVar.spanCount, __recyclerViewLocalVar.managerOrientation, __recyclerViewLocalVar.reverseLayout);
                recyclerView.setLayoutManager(gridLayoutManager);
            } else if ("android.support.v7.widget.LinearLayoutManager".equals(__recyclerViewLocalVar.layoutManager) || "LinearLayoutManager".equals(__recyclerViewLocalVar.layoutManager)) {
                android.support.v7.widget.LinearLayoutManager linearLayoutManager = new android.support.v7.widget.LinearLayoutManager(__context, __recyclerViewLocalVar.managerOrientation, __recyclerViewLocalVar.reverseLayout);
                linearLayoutManager.setStackFromEnd(__recyclerViewLocalVar.stackFromEnd);
                recyclerView.setLayoutManager(linearLayoutManager);
            } else if ("android.support.v7.widget.StaggeredGridLayoutManager".equals(__recyclerViewLocalVar.layoutManager) || "StaggeredGridLayoutManager".equals(__recyclerViewLocalVar.layoutManager)) {
                android.support.v7.widget.StaggeredGridLayoutManager staggeredGridLayoutManager = new android.support.v7.widget.StaggeredGridLayoutManager(__recyclerViewLocalVar.spanCount, __recyclerViewLocalVar.managerOrientation);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
            } else {
                android.support.v7.widget.RecyclerView.LayoutManager layoutManager = com.qxml.qxml_support.gen.recyclerView.RecyclerViewHelper.createLayoutManager(__context, __recyclerViewLocalVar.layoutManager);
                recyclerView.setLayoutManager(layoutManager);
            }
        }
    }

    @OnEnd({RS.attr.fastScrollEnabled, RS.attr.fastScrollHorizontalThumbDrawable, RS.attr.fastScrollHorizontalTrackDrawable
            , RS.attr.fastScrollVerticalThumbDrawable, RS.attr.fastScrollVerticalTrackDrawable})
    public void onRecyclerViewFastScrollEnd(RecyclerView recyclerView) {
        if (__recyclerViewLocalVar.fastScrollEnabled) {
            com.qxml.qxml_support.gen.recyclerView.RecyclerViewHelper.initFastScroller(recyclerView, __context, ___resources
                    , __recyclerViewLocalVar.fastScrollHorizontalThumbDrawable, __recyclerViewLocalVar.fastScrollHorizontalTrackDrawable
                    , __recyclerViewLocalVar.fastScrollVerticalThumbDrawable, __recyclerViewLocalVar.fastScrollVerticalTrackDrawable);
        }
    }

}
