package com.qxml.qxml_androidx.gen.textview;

import android.widget.TextView;

import androidx.core.widget.TextViewCompat;

import com.qxml.gen.textView.TextViewGen;
import com.qxml.helper.AttrHelperKt;
import com.qxml.qxml_androidx.RS;
import com.qxml.qxml_androidx.gen.textview.attr.TextViewAutoSizeAttr;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewReplace;

@ViewReplace
public class TextViewCompatGen extends TextViewGen implements TextViewAutoSizeAttr {

    @Attr(RS.attr.lastBaselineToBottomHeight)
    public void textViewAppLastBaselineToTopHeight(TextView textView, int lastBaselineToBottomHeight) {
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            textView.setLastBaselineToBottomHeight(lastBaselineToBottomHeight);
        }
    }

    @Attr(RS.attr.firstBaselineToTopHeight)
    public void textViewAppFirstBaselineToTopHeight(TextView textView, int firstBaselineToTopHeight) {
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(firstBaselineToTopHeight);
        }
    }

    @Override
    public void onTextViewDrawableEnd(TextView textView) {
        android.graphics.PorterDuff.Mode tintMode = null;
        if (__textViewDrawableLocalVar.drawableTintModeEnum != -1) {
            tintMode = AttrHelperKt.intToMode(__textViewDrawableLocalVar.drawableTintModeEnum, null);
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                //请使用androidx.appcompat:appcompat:1.1.0以上版本
                androidx.core.widget.TextViewCompat.setCompoundDrawableTintMode(textView, tintMode);
                tintMode = null;
            }
        }

        if (__textViewDrawableLocalVar.drawableLeftId != 0) {
            __textViewDrawableLocalVar.drawableLeft = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableLeftId);
            if (__textViewDrawableLocalVar.drawableLeft != null) {
                if (tintMode != null) {
                    androidx.core.graphics.drawable.DrawableCompat.setTintMode(__textViewDrawableLocalVar.drawableLeft, tintMode);
                }
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    __textViewDrawableLocalVar.drawableLeft.setTintMode(tintMode);
                }
                __textViewDrawableLocalVar.drawableLeft.setBounds(0, 0, __textViewDrawableLocalVar.drawableLeft.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableLeft.getIntrinsicHeight());
            }
        }

        if (__textViewDrawableLocalVar.drawableRightId != 0) {
            __textViewDrawableLocalVar.drawableRight = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableRightId);
            if (__textViewDrawableLocalVar.drawableRight != null) {
                if (tintMode != null) {
                    androidx.core.graphics.drawable.DrawableCompat.setTintMode(__textViewDrawableLocalVar.drawableRight, tintMode);
                }
                __textViewDrawableLocalVar.drawableRight.setBounds(0, 0, __textViewDrawableLocalVar.drawableRight.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableRight.getIntrinsicHeight());
            }
        }

        if (__textViewDrawableLocalVar.drawableTop != null) {
            if (tintMode != null) {
                androidx.core.graphics.drawable.DrawableCompat.setTintMode(__textViewDrawableLocalVar.drawableTop, tintMode);
            }
            __textViewDrawableLocalVar.drawableTop.setBounds(0, 0, __textViewDrawableLocalVar.drawableTop.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableTop.getIntrinsicHeight());
        }

        if (__textViewDrawableLocalVar.drawableBottom != null) {
            if (tintMode != null) {
                androidx.core.graphics.drawable.DrawableCompat.setTintMode(__textViewDrawableLocalVar.drawableBottom, tintMode);
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
