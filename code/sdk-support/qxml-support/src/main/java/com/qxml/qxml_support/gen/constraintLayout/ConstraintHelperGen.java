package com.qxml.qxml_support.gen.constraintLayout;

import android.support.constraint.ConstraintHelper;

import com.qxml.gen.view.ViewGen;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(ConstraintHelper.class)
public class ConstraintHelperGen extends ViewGen {

    @Attr(RS.attr.constraint_referenced_ids)
    public void constraintHelperConstraint_referenced_ids(ConstraintHelper constraintHelper, String constraint_referenced_ids) {
        com.qxml.qxml_support.gen.constraintLayout.tools.ConstraintHelperTool.setIds(constraintHelper, constraintHelper.getContext(), constraint_referenced_ids);
    }

}
