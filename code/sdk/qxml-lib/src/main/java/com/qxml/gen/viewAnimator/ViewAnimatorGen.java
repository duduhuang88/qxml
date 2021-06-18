package com.qxml.gen.viewAnimator;

import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;

import com.qxml.AndroidRS;
import com.qxml.gen.frameLayout.FrameLayoutGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(ViewAnimator.class)
public class ViewAnimatorGen extends FrameLayoutGen {

    @Attr(AndroidRS.attr.inAnimation)
    public void viewAnimatorInAnimation(ViewAnimator viewAnimator, int inAnimation) {
        viewAnimator.setInAnimation(android.view.animation.AnimationUtils.loadAnimation(__context, inAnimation));
    }

    @Attr(AndroidRS.attr.outAnimation)
    public void viewAnimatorOutAnimation(ViewAnimator viewAnimator, int outAnimation) {
        viewAnimator.setOutAnimation(android.view.animation.AnimationUtils.loadAnimation(__context, outAnimation));
    }

    @Attr(AndroidRS.attr.animateFirstView)
    public void viewAnimatorAnimateFirstView(ViewAnimator viewAnimator, boolean animateFirstView) {
        viewAnimator.setAnimateFirstView(animateFirstView);
    }

}
