package com.qxml.qxml_androidx.gen.constraintLayout;


import androidx.constraintlayout.widget.Barrier;

import com.qxml.qxml_androidx.RS;
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
