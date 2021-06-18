package com.qxml.gen.imageView;

import android.widget.ImageView;

import com.qxml.AndroidRS;
import com.qxml.gen.view.ViewGen;
import com.qxml.helper.AttrHelperKt;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(ImageView.class)
public class ImageViewGen extends ViewGen {

    public static class $$ImageViewLocalVariable {
        public int tintMode = -1;
        public int scaleType = -1;
        public boolean adjustViewBounds = false;
    }

    @LocalVar
    public $$ImageViewLocalVariable __imageViewLocalVar = new $$ImageViewLocalVariable();

    @Attr(AndroidRS.attr.adjustViewBounds)
    public void imageViewAdjustViewBounds(ImageView imageView, boolean adjustViewBounds) {
        __imageViewLocalVar.adjustViewBounds = adjustViewBounds;
    }

    @Attr(AndroidRS.attr.src)
    public void imageViewSrc(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }

    @Attr(AndroidRS.attr.maxWidth)
    public void imageViewMaxWidth(ImageView imageView, int maxWidth) {
        imageView.setMaxWidth(maxWidth);
    }

    @Attr(AndroidRS.attr.maxHeight)
    public void imageViewMaxHeight(ImageView imageView, int maxHeight) {
        imageView.setMaxHeight(maxHeight);
    }

    @Attr(AndroidRS.attr.tint)
    public void imageViewTint(ImageView imageView, ValueInfo valueInfo) {
        android.content.Context context = imageView.getContext();
        android.content.res.ColorStateList colorStateList;
        colorStateList = valueInfo.getColorStateList(__context);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            imageView.setImageTintList(colorStateList);
        } else {
            //android.support.v4.view.ViewCompat.setBackgroundTintList(imageView, colorStateList);
        }
        __imageViewLocalVar.tintMode = 9;
    }

    @Attr(AndroidRS.attr.tintMode)
    public void imageViewTintMode(ImageView imageView, int tintMode) {
        __imageViewLocalVar.tintMode = tintMode;
    }

    @Attr(AndroidRS.attr.scaleType)
    public void imageViewScaleType(ImageView imageView, int type) {
        __imageViewLocalVar.scaleType = type;
    }

    @Attr(AndroidRS.attr.cropToPadding)
    public void imageViewCropToPadding(ImageView imageView, boolean cropToPadding) {
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            imageView.setCropToPadding(cropToPadding);
        }
    }

    @OnEnd({AndroidRS.attr.tint, AndroidRS.attr.tintMode})
    public void onImageViewTintModeEnd(ImageView imageView) {
        if (__imageViewLocalVar.tintMode != -1) {
            android.graphics.PorterDuff.Mode mode = com.qxml.helper.AttrHelperKt.intToMode(__imageViewLocalVar.tintMode, null);
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                imageView.setImageTintMode(mode);
            } else {
                //android.support.v4.view.ViewCompat.setBackgroundTintMode(imageView, mode);
            }
        }
    }

    @OnEnd({AndroidRS.attr.scaleType, AndroidRS.attr.adjustViewBounds})
    public void onImageViewScaleTypeEnd(ImageView imageView) {
        if (__imageViewLocalVar.adjustViewBounds) {
            imageView.setAdjustViewBounds(true);
        }
        imageView.setScaleType(com.qxml.helper.AttrHelperKt.intToScaleType(__imageViewLocalVar.scaleType));
    }

}
