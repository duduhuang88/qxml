package com.qxml.gen.viewAnimator;

import android.widget.ViewFlipper;

import com.qxml.AndroidRS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(ViewFlipper.class)
public class ViewFlipperGen extends ViewAnimatorGen {

    @Attr(AndroidRS.attr.flipInterval)
    public void viewFlipperFlipInterval(ViewFlipper viewFlipper, int flipInterval) {
        viewFlipper.setFlipInterval(flipInterval);
    }

    @Attr(AndroidRS.attr.autoStart)
    public void viewFlipperAutoStart(ViewFlipper viewFlipper, boolean autoStart) {
        viewFlipper.setAutoStart(autoStart);
    }

}
