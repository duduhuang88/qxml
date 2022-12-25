package com.qxml.gen.viewStub;

import android.os.Build;
import android.view.View;
import android.view.ViewStub;
import android.widget.RelativeLayout;

import com.qxml.AndroidRS;
import com.qxml.gen.view.ViewGen;
import com.qxml.helper.ViewStubLayoutInflaterWrapper;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(ViewStub.class)
public class ViewStubGen extends ViewGen {

    @Override
    public void viewStart(View view, boolean useless) {
        if (___cur_layout_param instanceof RelativeLayout.LayoutParams) {
            __relativeLocalVar.rlLp = (RelativeLayout.LayoutParams) ___cur_layout_param;
        }
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            ((ViewStub) view).setLayoutInflater(new com.qxml.helper.ViewStubLayoutInflaterWrapper(__context));
        }
    }

    @Attr(AndroidRS.attr.inflatedId)
    public void onViewStubInflatedId(ViewStub viewStub, int inflatedId) {
        viewStub.setInflatedId(inflatedId);
    }

    @Attr(AndroidRS.attr.layout)
    public void onViewStubLayout(ViewStub viewStub, int layout) {
        viewStub.setLayoutResource(layout);
    }

}
