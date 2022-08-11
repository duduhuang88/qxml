package com.qxml.qxml_androidx.gen.imageView;

import android.widget.ImageView;

import androidx.core.widget.ImageViewCompat;

import com.qxml.gen.imageView.ImageViewGen;
import com.qxml.helper.AttrHelperKt;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.ViewReplace;

@ViewReplace
public class ImageViewCompatGen extends ImageViewGen {

    @Override
    public void imageViewTint(ImageView imageView, ValueInfo valueInfo) {
        androidx.core.widget.ImageViewCompat.setImageTintList(imageView, valueInfo.getColorStateList(__context));
        __imageViewLocalVar.tintMode = 9;
    }

    @Override
    public void imageViewTintMode(ImageView imageView, int tintMode) {
        __imageViewLocalVar.tintMode = tintMode;
        androidx.core.widget.ImageViewCompat.setImageTintMode(imageView, com.qxml.helper.AttrHelperKt.intToMode(__imageViewLocalVar.tintMode, null));
    }
}
