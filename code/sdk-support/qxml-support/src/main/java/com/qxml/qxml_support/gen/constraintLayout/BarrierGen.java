package com.qxml.qxml_support.gen.constraintLayout;

import android.support.constraint.Barrier;

import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(Barrier.class)
public class BarrierGen extends ConstraintHelperGen {

    @Attr(RS.attr.barrierAllowsGoneWidgets)
    public void barrierAllowsGoneWidgets(Barrier barrier, boolean barrierAllowsGoneWidgets) {
        barrier.setAllowsGoneWidget(barrierAllowsGoneWidgets);
    }

    @Attr(RS.attr.barrierDirection)
    public void barrierDirection(Barrier barrier, int barrierDirection) {
        barrier.setType(barrierDirection);
    }

}
