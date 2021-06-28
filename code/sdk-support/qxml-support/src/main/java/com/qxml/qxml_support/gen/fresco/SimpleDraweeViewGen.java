package com.qxml.qxml_support.gen.fresco;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(SimpleDraweeView.class)
public class SimpleDraweeViewGen extends DraweeViewGen {

    public static class $$SimpleDraweeViewLocalVar {
        public String actualImageUri = null;
        public int actualImageResource = -1;
    }

    @LocalVar
    public $$SimpleDraweeViewLocalVar __simpleDraweeViewLocalVar = new $$SimpleDraweeViewLocalVar();

    @Attr(RS.attr.actualImageUri)
    public void simpleDraweeViewActualImageUri(SimpleDraweeView simpleDraweeView, String actualImageUri) {
        __simpleDraweeViewLocalVar.actualImageUri = actualImageUri;
    }

    @Attr(RS.attr.actualImageResource)
    public void simpleDraweeViewActualImageResource(SimpleDraweeView simpleDraweeView, int actualImageResource) {
        __simpleDraweeViewLocalVar.actualImageResource = actualImageResource;
    }

    @OnEnd({RS.attr.actualImageUri, RS.attr.actualImageResource})
    public void onSimpleDraweeViewEnd(SimpleDraweeView simpleDraweeView) {
        if (__simpleDraweeViewLocalVar.actualImageUri != null) {
            simpleDraweeView.setImageURI(Uri.parse(__simpleDraweeViewLocalVar.actualImageUri), null);
        } else if (__simpleDraweeViewLocalVar.actualImageResource != -1) {
            simpleDraweeView.setActualImageResource(__simpleDraweeViewLocalVar.actualImageResource);
        }
    }

}
