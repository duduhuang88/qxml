package com.qxml.qxml_androidx.gen.fresco;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qxml.qxml_androidx.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(SimpleDraweeView.class)
public class SimpleDraweeViewGen extends DraweeViewGen {

    public static class $$SimpleDraweeViewLocalVar {
        public String actualImageUri = null;
    }

    @LocalVar
    public $$SimpleDraweeViewLocalVar __simpleDraweeViewLocalVar = new $$SimpleDraweeViewLocalVar();

    @Attr(RS.attr.actualImageUri)
    public void simpleDraweeViewActualImageUri(SimpleDraweeView simpleDraweeView, String actualImageUri) {
        __simpleDraweeViewLocalVar.actualImageUri = actualImageUri;
        simpleDraweeView.setImageURI(android.net.Uri.parse(__simpleDraweeViewLocalVar.actualImageUri), null);
    }

    @Attr(RS.attr.actualImageResource)
    public void simpleDraweeViewActualImageResource(SimpleDraweeView simpleDraweeView, int actualImageResource) {
        if (__simpleDraweeViewLocalVar.actualImageUri != null) simpleDraweeView.setActualImageResource(actualImageResource);
    }

    @Override
    public void onDraweeViewSetEnd(DraweeView draweeView) {
        draweeView.setHierarchy(__draweeViewLocalVar.builder.build());
    }

}
