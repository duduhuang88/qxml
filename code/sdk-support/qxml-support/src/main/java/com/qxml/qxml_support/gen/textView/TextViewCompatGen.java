package com.qxml.qxml_support.gen.textView;

import android.os.Build;
import android.support.v4.widget.TextViewCompat;
import android.widget.TextView;

import com.qxml.gen.textView.TextViewGen;
import com.qxml.helper.AttrHelperKt;
import com.qxml.qxml_support.RS;
import com.qxml.qxml_support.gen.textView.attr.TextViewAutoSizeAttr;
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
        android.graphics.drawable.Drawable drawableLeft = null;
        android.graphics.PorterDuff.Mode tintMode = null;
        if (__textViewDrawableLocalVar.drawableTintModeEnum != -1) {
            tintMode = com.qxml.helper.AttrHelperKt.intToMode(__textViewDrawableLocalVar.drawableTintModeEnum, null);
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                textView.setCompoundDrawableTintMode(tintMode);
                tintMode = null;
            }
        }
        if (__textViewDrawableLocalVar.drawableLeftId != 0) {
            //drawableLeft = android.support.v4.content.ContextCompat.getDrawable(context, __textViewDrawableLocalVar.drawableLeftId);
            drawableLeft = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableLeftId);
            if (drawableLeft != null) {
                if (tintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(drawableLeft, tintMode);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    drawableLeft.setTintMode(tintMode);
                }
                drawableLeft.setBounds(0, 0, drawableLeft.getIntrinsicWidth(), drawableLeft.getIntrinsicHeight());
            }
        }

        android.graphics.drawable.Drawable drawableRight = null;
        if (__textViewDrawableLocalVar.drawableRightId != 0) {
            //drawableRight  = android.support.v4.content.ContextCompat.getDrawable(context, __textViewDrawableLocalVar.drawableRightId);
            drawableRight = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableRightId);
            if (drawableRight != null) {
                if (tintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(drawableRight, tintMode);
                }
                drawableRight.setBounds(0, 0, drawableRight.getIntrinsicWidth(), drawableRight.getIntrinsicHeight());
            }
        }

        android.graphics.drawable.Drawable drawableTop = null;
        if (__textViewDrawableLocalVar.drawableTopId != 0) {
            //drawableTop  = android.support.v4.content.ContextCompat.getDrawable(context, __textViewDrawableLocalVar.drawableTopId);
            drawableTop = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableTopId);
            if (drawableTop != null) {
                if (tintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(drawableTop, tintMode);
                }
                drawableTop.setBounds(0, 0, drawableTop.getIntrinsicWidth(), drawableTop.getIntrinsicHeight());
            }
        }

        android.graphics.drawable.Drawable drawableBottom = null;
        if (__textViewDrawableLocalVar.drawableBottomId != 0) {
            //drawableBottom  = android.support.v4.content.ContextCompat.getDrawable(context, __textViewDrawableLocalVar.drawableBottomId);
            drawableBottom = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableBottomId);
            if (drawableBottom != null) {
                if (tintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(drawableBottom, tintMode);
                }
                drawableBottom.setBounds(0, 0, drawableBottom.getIntrinsicWidth(), drawableBottom.getIntrinsicHeight());
            }
        }

        if (android.os.Build.VERSION.SDK_INT >= 18) {
            textView.setCompoundDrawablesRelative(drawableLeft, drawableTop, drawableRight, drawableBottom);
        } else if (android.os.Build.VERSION.SDK_INT >= 17) {
            boolean rtl = textView.getLayoutDirection() == android.view.View.LAYOUT_DIRECTION_RTL;
            textView.setCompoundDrawables(rtl ? drawableRight : drawableLeft, drawableTop, rtl ? drawableLeft : drawableRight, drawableBottom);
        } else {
            textView.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
        }
    }
}
