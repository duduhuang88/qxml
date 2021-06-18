package com.qxml.qxml_support.gen.imageView;

import android.widget.ImageView;

import com.qxml.gen.imageView.ImageViewGen;
import com.qxml.helper.AttrHelperKt;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.ViewReplace;

@ViewReplace
public class ImageViewCompatGen extends ImageViewGen {

    @Override
    public void imageViewTint(ImageView imageView, ValueInfo valueInfo) {
        android.content.Context context = imageView.getContext();
        android.content.res.ColorStateList colorStateList;
        colorStateList = valueInfo.getColorStateList(__context);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            imageView.setImageTintList(colorStateList);
        } else {
            android.support.v4.view.ViewCompat.setBackgroundTintList(imageView, colorStateList);
        }
        __imageViewLocalVar.tintMode = 9;
    }

    @Override
    public void onImageViewTintModeEnd(ImageView imageView) {
        if (__imageViewLocalVar.tintMode != -1) {
            android.graphics.PorterDuff.Mode mode = com.qxml.helper.AttrHelperKt.intToMode(__imageViewLocalVar.tintMode, null);
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                imageView.setImageTintMode(mode);
            } else {
                android.support.v4.view.ViewCompat.setBackgroundTintMode(imageView, mode);
            }
        }
    }
}
