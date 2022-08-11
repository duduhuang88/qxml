package com.qxml.gen.textView.attr;

import android.widget.TextView;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;


public interface TextViewDrawableAttr extends ViewLocalVar {

    class $$TextViewDrawableLocalVariable {
        public int drawableTopId = 0;
        public int drawableBottomId = 0;
        public int drawableRightId = 0;
        public int drawableLeftId = 0;
        public int drawableTintModeEnum = -1;

        public android.graphics.drawable.Drawable drawableLeft = null;
        public android.graphics.drawable.Drawable drawableRight = null;
        public android.graphics.drawable.Drawable drawableTop = null;
        public android.graphics.drawable.Drawable drawableBottom = null;
        public android.graphics.PorterDuff.Mode drawableTintMode = null;
    }

    @LocalVar
    $$TextViewDrawableLocalVariable __textViewDrawableLocalVar = new $$TextViewDrawableLocalVariable();

    @Attr(AndroidRS.attr.drawableTintMode)
    default void textViewDrawableTintMode(TextView textView, int drawableTintModeEnum) {
        __textViewDrawableLocalVar.drawableTintMode = com.qxml.helper.AttrHelperKt.intToMode(drawableTintModeEnum, null);
    }

    @Attr(AndroidRS.attr.drawableTop)
    default void textViewDrawableTop(TextView textView, int drawableTopId) {
        __textViewDrawableLocalVar.drawableTop = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, drawableTopId);
    }

    @Attr(AndroidRS.attr.drawableBottom)
    default void textViewDrawableBottom(TextView textView, int drawableBottomId) {
        __textViewDrawableLocalVar.drawableBottom = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, drawableBottomId);
    }

    @Attr(AndroidRS.attr.drawableRight)
    default void textViewDrawableRight(TextView textView, int drawableRightId) {
        __textViewDrawableLocalVar.drawableRightId = drawableRightId;
    }

    @Attr(AndroidRS.attr.drawableLeft)
    default void textViewDrawableLeft(TextView textView, int drawableLeftId) {
        __textViewDrawableLocalVar.drawableLeftId = drawableLeftId;
    }

    @Attr(AndroidRS.attr.drawableStart)
    default void textViewDrawableStart(TextView textView, int drawableStartId) {
        __textViewDrawableLocalVar.drawableLeftId = drawableStartId;
    }

    @Attr(AndroidRS.attr.drawableEnd)
    default void textViewDrawableEnd(TextView textView, int drawableEndId) {
        __textViewDrawableLocalVar.drawableRightId = drawableEndId;
    }

    @Attr(AndroidRS.attr.drawablePadding)
    default void textViewDrawablePadding(TextView textView, int drawablePadding) {
        textView.setCompoundDrawablePadding(drawablePadding);
    }

    @Attr(AndroidRS.attr.drawableTint)
    default void textViewDrawableTint(TextView textView, ValueInfo valueInfo) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (valueInfo.isColor()) {
                textView.setCompoundDrawableTintList(android.content.res.ColorStateList.valueOf(valueInfo.colorValue));
            } else if (valueInfo.isReference()) {
                textView.setCompoundDrawableTintList(__context.getColorStateList(valueInfo.resourceId));
            }
        }
    }

    @OnEnd({AndroidRS.attr.drawableTop, AndroidRS.attr.drawableLeft
            , AndroidRS.attr.drawableStart, AndroidRS.attr.drawableRight
            , AndroidRS.attr.drawableEnd, AndroidRS.attr.drawableBottom})
    default void onTextViewDrawableEnd(TextView textView) {
        if (__textViewDrawableLocalVar.drawableTintMode != null && android.os.Build.VERSION.SDK_INT >= 23) {
            textView.setCompoundDrawableTintMode(__textViewDrawableLocalVar.drawableTintMode);
        }
        if (__textViewDrawableLocalVar.drawableLeftId != 0) {
            __textViewDrawableLocalVar.drawableLeft = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableLeftId);
            if (__textViewDrawableLocalVar.drawableLeft != null) {
                if (android.os.Build.VERSION.SDK_INT >= 21 && __textViewDrawableLocalVar.drawableTintMode != null) {
                    __textViewDrawableLocalVar.drawableLeft.setTintMode(__textViewDrawableLocalVar.drawableTintMode);
                }
                __textViewDrawableLocalVar.drawableLeft.setBounds(0, 0, __textViewDrawableLocalVar.drawableLeft.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableLeft.getIntrinsicHeight());
            }
        }

        if (__textViewDrawableLocalVar.drawableRightId != 0) {
            __textViewDrawableLocalVar.drawableRight = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableRightId);
            if (__textViewDrawableLocalVar.drawableRight != null && __textViewDrawableLocalVar.drawableTintMode != null) {
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    __textViewDrawableLocalVar.drawableRight.setTintMode(__textViewDrawableLocalVar.drawableTintMode);
                }
                __textViewDrawableLocalVar.drawableRight.setBounds(0, 0, __textViewDrawableLocalVar.drawableRight.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableRight.getIntrinsicHeight());
            }
        }

        if (__textViewDrawableLocalVar.drawableTop != null) {
            if (android.os.Build.VERSION.SDK_INT >= 21 && __textViewDrawableLocalVar.drawableTintMode != null) __textViewDrawableLocalVar.drawableTop.setTintMode(__textViewDrawableLocalVar.drawableTintMode);
            __textViewDrawableLocalVar.drawableTop.setBounds(0, 0, __textViewDrawableLocalVar.drawableTop.getIntrinsicWidth(), __textViewDrawableLocalVar.drawableTop.getIntrinsicHeight());
        }

        if (__textViewDrawableLocalVar.drawableBottom != null) {
            if (android.os.Build.VERSION.SDK_INT >= 21 && __textViewDrawableLocalVar.drawableTintMode != null) __textViewDrawableLocalVar.drawableBottom.setTintMode(__textViewDrawableLocalVar.drawableTintMode);
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
