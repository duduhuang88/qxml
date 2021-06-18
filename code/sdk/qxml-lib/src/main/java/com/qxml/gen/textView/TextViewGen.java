package com.qxml.gen.textView;

import android.view.View;
import android.widget.TextView;

import com.qxml.AndroidRS;
import com.qxml.gen.textView.attr.*;
import com.qxml.gen.view.ViewGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(TextView.class)
public class TextViewGen extends ViewGen implements TextViewTextAttr, /*TextViewAutoSizeAttr,*/ TextViewDrawableAttr {

    @Attr(AndroidRS.attr.gravity)
    public void textViewGravity(TextView textView, int gravityFlag) {
        textView.setGravity(gravityFlag);
    }

    @Attr(AndroidRS.attr.minWidth)
    public void textViewMinWidth(TextView textView, int minWidth) {
        textView.setMinimumWidth(minWidth);
        textView.setMinWidth(minWidth);
    }

    @Attr(AndroidRS.attr.minHeight)
    public void textViewMinHeight(TextView textView, int minHeight) {
        textView.setMinimumHeight(minHeight);
        textView.setMinHeight(minHeight);
    }

    @Attr(AndroidRS.attr.enabled)
    public void textViewEnabled(TextView textView, boolean enabled) {
        textView.setEnabled(enabled);
    }
}
