package com.qxml.gen.viewStub;

import android.view.ViewStub;

import com.qxml.AndroidRS;
import com.qxml.gen.view.ViewGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(ViewStub.class)
public class ViewStubGen extends ViewGen {

    @Attr(AndroidRS.attr.inflatedId)
    public void onViewStubInflatedId(ViewStub viewStub, int inflatedId) {
        viewStub.setInflatedId(inflatedId);
    }

    @Attr(AndroidRS.attr.layout)
    public void onViewStubLayout(ViewStub viewStub, int layout) {
        viewStub.setLayoutResource(layout);
    }

}
