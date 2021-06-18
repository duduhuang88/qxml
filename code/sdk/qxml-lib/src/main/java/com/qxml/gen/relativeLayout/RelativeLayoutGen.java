package com.qxml.gen.relativeLayout;

import android.view.View;
import android.widget.RelativeLayout;

import com.qxml.AndroidRS;
import com.qxml.gen.viewGroup.ViewGroupGen;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = RelativeLayout.class, layoutParamInit = "new android.widget.RelativeLayout.LayoutParams(-2, -2)")
public class RelativeLayoutGen extends ViewGroupGen {

    @Override
    public void viewGravity(View view, int gravityFlag) {
        ((android.widget.RelativeLayout)view).setGravity(gravityFlag);
    }

    @Attr(AndroidRS.attr.ignoreGravity)
    public void relativeLayoutIgnoreGravity(RelativeLayout relativeLayout, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ID) {
            relativeLayout.setIgnoreGravity(valueInfo.resourceId);
        }
    }

}
