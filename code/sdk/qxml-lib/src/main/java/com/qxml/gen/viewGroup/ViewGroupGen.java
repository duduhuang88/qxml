package com.qxml.gen.viewGroup;

import android.view.ViewGroup;

import com.qxml.AndroidRS;
import com.qxml.gen.view.ViewGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = ViewGroup.class, layoutParamInit = "new android.view.ViewGroup.LayoutParams(-2, -2)")
public class ViewGroupGen extends ViewGen {

    @Attr(AndroidRS.attr.clipChildren)
    public void viewGroupClipChildren(ViewGroup viewGroup, boolean clipChildren) {
        viewGroup.setClipChildren(clipChildren);
    }

    @Attr(AndroidRS.attr.clipToPadding)
    public void viewGroupClipToPadding(ViewGroup viewGroup, boolean clipToPadding) {
        viewGroup.setClipToPadding(clipToPadding);
    }

    @Attr(AndroidRS.attr.layoutAnimation)
    public void viewGroupLayoutAnimation(ViewGroup viewGroup, int resId) {
        viewGroup.setLayoutAnimation(android.view.animation.AnimationUtils.loadLayoutAnimation(viewGroup.getContext(), resId));
    }

    @Attr(AndroidRS.attr.animationCache)
    public void viewGroupAnimationCache(ViewGroup viewGroup, boolean animationCache) {
        viewGroup.setAnimationCacheEnabled(animationCache);
    }

    @Attr(AndroidRS.attr.persistentDrawingCache)
    public void viewGroupPersistentDrawingCache(ViewGroup viewGroup, int persistentDrawingCache) {
        viewGroup.setPersistentDrawingCache(persistentDrawingCache);
    }

    @Attr(AndroidRS.attr.alwaysDrawnWithCache)
    public void viewGroupAlwaysDrawnWithCache(ViewGroup viewGroup, boolean alwaysDrawnWithCache) {
        viewGroup.setAlwaysDrawnWithCacheEnabled(alwaysDrawnWithCache);
    }

    @Attr(AndroidRS.attr.addStatesFromChildren)
    public void viewGroupAddStatesFromChildren(ViewGroup viewGroup, boolean addStatesFromChildren) {
        viewGroup.setAddStatesFromChildren(addStatesFromChildren);
    }

    @Attr(AndroidRS.attr.descendantFocusability)
    public void viewGroupDescendantFocusability(ViewGroup viewGroup, int descendantFocusability) {
        if (descendantFocusability == 0) {
            viewGroup.setDescendantFocusability(android.view.ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        } else if (descendantFocusability == 1) {
            viewGroup.setDescendantFocusability(android.view.ViewGroup.FOCUS_AFTER_DESCENDANTS);
        } else {
            viewGroup.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        }
    }

    @Attr(AndroidRS.attr.animateLayoutChanges)
    public void viewGroupAnimateLayoutChanges(ViewGroup viewGroup, boolean animateLayoutChanges) {
        if (animateLayoutChanges) {
            viewGroup.setLayoutTransition(new android.animation.LayoutTransition());
        }
    }

    @Attr(AndroidRS.attr.splitMotionEvents)
    public void viewGroupSplitMotionEvents(ViewGroup viewGroup, boolean splitMotionEvents) {
        viewGroup.setMotionEventSplittingEnabled(splitMotionEvents);
    }

    @Attr(AndroidRS.attr.layoutMode)
    public void viewGroupLayoutMode(ViewGroup viewGroup, int layoutMode) {
        if (android.os.Build.VERSION.SDK_INT >= 18) {
            viewGroup.setLayoutMode(layoutMode);
        }
    }

}
