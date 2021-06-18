package com.qxml.qxml_support.gen.imageView;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.qxml.AndroidRS;
import com.qxml.gen.imageView.ImageViewGen;
import com.qxml.qxml_support.RS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = AppCompatImageView.class, compatOf = ImageView.class, compatCondition = {
        RS.attr.srcCompat, RS.attr.tint, RS.attr.tintMode
})
public class AppCompatImageViewGen extends ImageViewGen {

    public static class $$AppCompatImageViewLocalVariable {
        public int srcCompat = 0;
        public int src = 0;
        public int tint = 0;
        public int tintColor = Integer.MAX_VALUE;
        public int tintMode = -1;
    }

    @LocalVar
    public $$AppCompatImageViewLocalVariable __appCompatImageViewLocalVar = new $$AppCompatImageViewLocalVariable();

    @Override
    public void imageViewSrc(ImageView imageView, int resId) {
        __appCompatImageViewLocalVar.src = resId;
    }

    @Attr(RS.attr.srcCompat)
    public void appCompatImageViewSrcCompat(AppCompatImageView appCompatImageView, int srcCompat) {
        __appCompatImageViewLocalVar.srcCompat = srcCompat;
    }

    @Attr(RS.attr.tint)
    public void appCompatImageViewTint(AppCompatImageView appCompatImageView, ValueInfo valueInfo) {
        if (valueInfo.isColor()) {
            __appCompatImageViewLocalVar.tintColor = valueInfo.colorValue;
        } else {
            __appCompatImageViewLocalVar.tint = valueInfo.resourceId;
        }
    }

    @Attr(RS.attr.tintMode)
    public void appCompatImageViewTintMode(AppCompatImageView appCompatImageView, int tintMode) {
        __appCompatImageViewLocalVar.tintMode = tintMode;
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({RS.attr.srcCompat, AndroidRS.attr.src, RS.attr.tint, RS.attr.tintMode})
    public void onAppCompatSrcEnd(AppCompatImageView appCompatImageView) {
        if (__appCompatImageViewLocalVar.src != 0) {
            appCompatImageView.setImageResource(__appCompatImageViewLocalVar.src);
        } else if (__appCompatImageViewLocalVar.srcCompat != 0) {
            appCompatImageView.setImageDrawable(android.support.v7.content.res.AppCompatResources.getDrawable(__context, __appCompatImageViewLocalVar.srcCompat));
        }
        com.qxml.qxml_support.gen.imageView.ImageViewHelper.fixDrawable(appCompatImageView.getDrawable());

        android.content.res.ColorStateList colorStateList = null;
        if (__appCompatImageViewLocalVar.tint != 0) {
            colorStateList = android.support.v7.content.res.AppCompatResources.getColorStateList(__context, __appCompatImageViewLocalVar.tint);
        } else if (__appCompatImageViewLocalVar.tintColor != Integer.MAX_VALUE) {
            colorStateList = android.content.res.ColorStateList.valueOf(__appCompatImageViewLocalVar.tintColor);
        }

        if (colorStateList != null) {
            android.support.v4.widget.ImageViewCompat.setImageTintList(appCompatImageView, colorStateList);
        }

        if (__appCompatImageViewLocalVar.tintMode != -1) {
            android.support.v4.widget.ImageViewCompat.setImageTintMode(appCompatImageView, android.support.v7.widget.DrawableUtils.parseTintMode(__appCompatImageViewLocalVar.tintMode, null));
        }
    }

}
