package com.qxml.qxml_androidx.gen.lottie;

import android.graphics.ColorFilter;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;

public class LottieHelper {

    public static void setLottieImageViewLottieColorFilter(LottieAnimationView lottieImageView, int color) {
        SimpleColorFilter filter = new SimpleColorFilter(color);
        KeyPath keyPath = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filter);
        lottieImageView.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);
    }

}
