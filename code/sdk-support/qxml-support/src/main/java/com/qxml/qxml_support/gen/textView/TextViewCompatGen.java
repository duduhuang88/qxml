package com.qxml.qxml_support.gen.textView;

import android.widget.TextView;

import com.qxml.gen.textView.TextViewGen;
import com.qxml.qxml_support.RS;
import com.qxml.qxml_support.gen.textView.attr.TextViewAutoSizeAttr;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewReplace;

@ViewReplace
public class TextViewCompatGen extends TextViewGen implements TextViewAutoSizeAttr {

    @Attr(RS.attr.lastBaselineToBottomHeight)
    public void textViewAppLastBaselineToTopHeight(TextView textView, int lastBaselineToBottomHeight) {
        if (android.os.Build.VERSION.SDK_INT >= 28) textView.setLastBaselineToBottomHeight(lastBaselineToBottomHeight);
    }

    @Attr(RS.attr.firstBaselineToTopHeight)
    public void textViewAppFirstBaselineToTopHeight(TextView textView, int firstBaselineToTopHeight) {
        if (android.os.Build.VERSION.SDK_INT >= 28) textView.setFirstBaselineToTopHeight(firstBaselineToTopHeight);
    }

    @Override
    public void onTextViewDrawableEnd(TextView textView) {
        if (__textViewDrawableLocalVar.drawableTintMode != null && android.os.Build.VERSION.SDK_INT >= 23) {
            textView.setCompoundDrawableTintMode(__textViewDrawableLocalVar.drawableTintMode);
        }
        if (__textViewDrawableLocalVar.drawableLeftId != 0) {
            __textViewDrawableLocalVar.drawableLeft = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableLeftId);
            if (__textViewDrawableLocalVar.drawableLeft != null) {
                if (__textViewDrawableLocalVar.drawableTintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(__textViewDrawableLocalVar.drawableLeft, __textViewDrawableLocalVar.drawableTintMode);
                }
                __textViewDrawableLocalVar.drawableLeft.setBounds(0, 0, __textViewDrawableLocalVar.drawableLeft.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableLeft.getIntrinsicHeight());
            }
        }

        if (__textViewDrawableLocalVar.drawableRightId != 0) {
            __textViewDrawableLocalVar.drawableRight = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableRightId);
            if (__textViewDrawableLocalVar.drawableRight != null) {
                if (__textViewDrawableLocalVar.drawableTintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(__textViewDrawableLocalVar.drawableRight, __textViewDrawableLocalVar.drawableTintMode);
                }
                __textViewDrawableLocalVar.drawableRight.setBounds(0, 0, __textViewDrawableLocalVar.drawableRight.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableRight.getIntrinsicHeight());
            }
        }

        if (__textViewDrawableLocalVar.drawableTop != null) {
            if (__textViewDrawableLocalVar.drawableTintMode != null) {
                android.support.v4.graphics.drawable.DrawableCompat.setTintMode(__textViewDrawableLocalVar.drawableTop, __textViewDrawableLocalVar.drawableTintMode);
            }
            __textViewDrawableLocalVar.drawableTop.setBounds(0, 0, __textViewDrawableLocalVar.drawableTop.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableTop.getIntrinsicHeight());
        }

        if (__textViewDrawableLocalVar.drawableBottom != null) {
            if (__textViewDrawableLocalVar.drawableTintMode != null) {
                android.support.v4.graphics.drawable.DrawableCompat.setTintMode(__textViewDrawableLocalVar.drawableBottom, __textViewDrawableLocalVar.drawableTintMode);
            }
            __textViewDrawableLocalVar.drawableBottom.setBounds(0, 0, __textViewDrawableLocalVar.drawableBottom.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableBottom.getIntrinsicHeight());
        }

        if (android.os.Build.VERSION.SDK_INT >= 18) {
            textView.setCompoundDrawablesRelative(__textViewDrawableLocalVar.drawableLeft, __textViewDrawableLocalVar.drawableTop, __textViewDrawableLocalVar.drawableRight, __textViewDrawableLocalVar.drawableBottom);
        } else if (android.os.Build.VERSION.SDK_INT >= 17) {
            boolean rtl = textView.getLayoutDirection() == android.view.View.LAYOUT_DIRECTION_RTL;
            textView.setCompoundDrawables(rtl ? __textViewDrawableLocalVar.drawableRight : __textViewDrawableLocalVar.drawableLeft, __textViewDrawableLocalVar.drawableTop, rtl ? __textViewDrawableLocalVar.drawableLeft : __textViewDrawableLocalVar.drawableRight, __textViewDrawableLocalVar.drawableBottom);
        } else {
            textView.setCompoundDrawables(__textViewDrawableLocalVar.drawableLeft, __textViewDrawableLocalVar.drawableTop, __textViewDrawableLocalVar.drawableRight, __textViewDrawableLocalVar.drawableBottom);
        }
    }
}
