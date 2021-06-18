package com.qxml.gen.textView.attr;

import android.os.Build;
import android.widget.TextView;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.helper.AttrHelperKt;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;


public interface TextViewDrawableAttr extends ViewLocalVar {

    class $$TextViewDrawableLocalVariable {
        public int drawableTag = 0;
        public int drawableTopId = 0;
        public int drawableBottomId = 0;
        public int drawableRightId = 0;
        public int drawableLeftId = 0;
        public int drawableTintModeEnum = -1;
    }

    int DRAWABLE_START_SET = 1;
    int DRAWABLE_END_SET = 1 << 1;

    @LocalVar
    $$TextViewDrawableLocalVariable __textViewDrawableLocalVar = new $$TextViewDrawableLocalVariable();

    @Attr(AndroidRS.attr.drawableTop)
    default void textViewDrawableTop(TextView textView, int drawableTopId) {
        __textViewDrawableLocalVar.drawableTopId = drawableTopId;
    }

    @Attr(AndroidRS.attr.drawableBottom)
    default void textViewDrawableBottom(TextView textView, int drawableBottomId) {
        __textViewDrawableLocalVar.drawableBottomId = drawableBottomId;
    }

    @Attr(AndroidRS.attr.drawableRight)
    default void textViewDrawableRight(TextView textView, int drawableRightId) {
        if ((__textViewDrawableLocalVar.drawableTag & com.qxml.gen.textView.attr.TextViewDrawableAttr.DRAWABLE_END_SET) == 0) {
            __textViewDrawableLocalVar.drawableRightId = drawableRightId;
        }
    }

    @Attr(AndroidRS.attr.drawableLeft)
    default void textViewDrawableLeft(TextView textView, int drawableLeftId) {
        if ((__textViewDrawableLocalVar.drawableTag & com.qxml.gen.textView.attr.TextViewDrawableAttr.DRAWABLE_START_SET) == 0) {
            __textViewDrawableLocalVar.drawableLeftId = drawableLeftId;
        }
    }

    @Attr(AndroidRS.attr.drawableStart)
    default void textViewDrawableStart(TextView textView, int drawableStartId) {
        __textViewDrawableLocalVar.drawableTag = __textViewDrawableLocalVar.drawableTag | com.qxml.gen.textView.attr.TextViewDrawableAttr.DRAWABLE_START_SET;
        __textViewDrawableLocalVar.drawableLeftId = drawableStartId;
    }

    @Attr(AndroidRS.attr.drawableEnd)
    default void textViewDrawableEnd(TextView textView, int drawableEndId) {
        __textViewDrawableLocalVar.drawableTag = __textViewDrawableLocalVar.drawableTag | com.qxml.gen.textView.attr.TextViewDrawableAttr.DRAWABLE_END_SET;
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

    @Attr(AndroidRS.attr.drawableTintMode)
    default void textViewDrawableTintMode(TextView textView, int drawableTintModeEnum) {
        __textViewDrawableLocalVar.drawableTintModeEnum = drawableTintModeEnum;
    }

    @OnEnd({AndroidRS.attr.drawableTop, AndroidRS.attr.drawableLeft
            , AndroidRS.attr.drawableStart, AndroidRS.attr.drawableRight
            , AndroidRS.attr.drawableEnd, AndroidRS.attr.drawableBottom})
    default void onTextViewDrawableEnd(TextView textView) {
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
                /*if (tintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(drawableLeft, tintMode);
                }*/
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
                /*if (tintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(drawableRight, tintMode);
                }*/
                if (Build.VERSION.SDK_INT >= 21) {
                    drawableRight.setTintMode(tintMode);
                }
                drawableRight.setBounds(0, 0, drawableRight.getIntrinsicWidth(), drawableRight.getIntrinsicHeight());
            }
        }

        android.graphics.drawable.Drawable drawableTop = null;
        if (__textViewDrawableLocalVar.drawableTopId != 0) {
            //drawableTop  = android.support.v4.content.ContextCompat.getDrawable(context, __textViewDrawableLocalVar.drawableTopId);
            drawableTop = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableTopId);
            if (drawableTop != null) {
                /*if (tintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(drawableTop, tintMode);
                }*/
                if (Build.VERSION.SDK_INT >= 21) {
                    drawableTop.setTintMode(tintMode);
                }
                drawableTop.setBounds(0, 0, drawableTop.getIntrinsicWidth(), drawableTop.getIntrinsicHeight());
            }
        }

        android.graphics.drawable.Drawable drawableBottom = null;
        if (__textViewDrawableLocalVar.drawableBottomId != 0) {
            //drawableBottom  = android.support.v4.content.ContextCompat.getDrawable(context, __textViewDrawableLocalVar.drawableBottomId);
            drawableBottom = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __textViewDrawableLocalVar.drawableBottomId);
            if (drawableBottom != null) {
                /*if (tintMode != null) {
                    android.support.v4.graphics.drawable.DrawableCompat.setTintMode(drawableBottom, tintMode);
                }*/
                if (Build.VERSION.SDK_INT >= 21) {
                    drawableBottom.setTintMode(tintMode);
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
