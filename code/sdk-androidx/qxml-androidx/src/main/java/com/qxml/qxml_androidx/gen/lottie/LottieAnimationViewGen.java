package com.qxml.qxml_androidx.gen.lottie;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.qxml.qxml_androidx.RS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;
import com.qxml.qxml_androidx.gen.imageView.AppCompatImageViewGen;

@ViewParse(LottieAnimationView.class)
public class LottieAnimationViewGen extends AppCompatImageViewGen {

    public static class $$LottieAnimationViewLocalVariable {
        public int lottie_rawRes = 0;
        public String fileName = null;
        public String url = null;
        public boolean autoPlay = false;
        public boolean loop = false;
        public int repeatCount = -2;
    }

    @LocalVar
    public $$LottieAnimationViewLocalVariable __lottieAnimationViewLocalVar = new $$LottieAnimationViewLocalVariable();

    @Attr(RS.attr.lottie_rawRes)
    public void lottieImageViewLottieRawRes(LottieAnimationView lottieAnimationView, int res) {
        __lottieAnimationViewLocalVar.lottie_rawRes = res;
    }

    @Attr(RS.attr.lottie_fileName)
    public void lottieImageViewLottieFileName(LottieAnimationView lottieAnimationView, String fileName) {
        __lottieAnimationViewLocalVar.fileName = fileName;
    }

    @Attr(RS.attr.lottie_url)
    public void lottieImageViewLottieUrl(LottieAnimationView lottieAnimationView, String url) {
        __lottieAnimationViewLocalVar.url = url;
    }

    @Attr(RS.attr.lottie_autoPlay)
    public void lottieImageViewLottieAutoPlay(LottieAnimationView lottieAnimationView, boolean autoPlay) {
        __lottieAnimationViewLocalVar.autoPlay = autoPlay;
    }

    @Attr(RS.attr.lottie_loop)
    public void lottieImageViewLottieLoop(LottieAnimationView lottieAnimationView, boolean loop) {
        __lottieAnimationViewLocalVar.loop = loop;
    }

    @Attr(RS.attr.lottie_repeatMode)
    public void lottieImageViewLottieRepeatMode(LottieAnimationView lottieAnimationView, int repeatMode) {
        lottieAnimationView.setRepeatMode(repeatMode);
    }

    @Attr(RS.attr.lottie_repeatCount)
    public void lottieImageViewLottieRepeatCount(LottieAnimationView lottieAnimationView, int repeatCount) {
        __lottieAnimationViewLocalVar.repeatCount = repeatCount;
    }

    @Attr(RS.attr.lottie_imageAssetsFolder)
    public void lottieImageViewLottieImageAssetsFolder(LottieAnimationView lottieAnimationView, String imageAssetsFolder) {
        lottieAnimationView.setImageAssetsFolder(imageAssetsFolder);
    }

    @Attr(RS.attr.lottie_progress)
    public void lottieImageViewLottieProgress(LottieAnimationView lottieAnimationView, float progress) {
        lottieAnimationView.setProgress(progress);
    }

    @Attr(RS.attr.lottie_enableMergePathsForKitKatAndAbove)
    public void lottieImageViewLottieEnableMergePathsForKitKatAndAbove(LottieAnimationView lottieAnimationView, boolean enableMergePathsForKitKatAndAbove) {
        lottieAnimationView.enableMergePathsForKitKatAndAbove(enableMergePathsForKitKatAndAbove);
    }

    @Attr(RS.attr.lottie_colorFilter)
    public void lottieImageViewLottieColorFilter(LottieAnimationView lottieAnimationView, ValueInfo colorFilter) {
        com.qxml.qxml_androidx.gen.lottie.LottieHelper.setLottieImageViewLottieColorFilter(lottieAnimationView, colorFilter.colorValue);
    }

    @Attr(RS.attr.lottie_scale)
    public void lottieImageViewLottieScale(LottieAnimationView lottieAnimationView, float scale) {
        lottieAnimationView.setScale(scale);
    }

    @Attr(RS.attr.lottie_speed)
    public void lottieImageViewLottieSpeed(LottieAnimationView lottieAnimationView, float speed) {
        lottieAnimationView.setSpeed(speed);
    }

    @Attr(RS.attr.lottie_cacheComposition)
    public void lottieImageViewLottieCacheComposition(LottieAnimationView lottieAnimationView, boolean cacheComposition) {
        lottieAnimationView.setCacheComposition(cacheComposition);
    }

    @Attr(RS.attr.lottie_ignoreDisabledSystemAnimations)
    public void lottieImageViewLottieIgnoreDisabledSystemAnimations(LottieAnimationView lottieAnimationView, boolean ignoreDisabledSystemAnimations) {
        lottieAnimationView.setIgnoreDisabledSystemAnimations(ignoreDisabledSystemAnimations);
    }

    @Attr(RS.attr.lottie_renderMode)
    public void lottieImageViewLottieRenderMode(LottieAnimationView lottieAnimationView, int renderMode) {
        lottieAnimationView.setRenderMode(com.airbnb.lottie.RenderMode.values()[renderMode]);
    }

    @OnEnd({RS.attr.lottie_rawRes, RS.attr.lottie_fileName
            , RS.attr.lottie_url, RS.attr.lottie_autoPlay
            , RS.attr.lottie_loop, RS.attr.lottie_repeatCount})
    public void onLottieEnd(LottieAnimationView lottieAnimationView) {
        boolean hasRawRes = __lottieAnimationViewLocalVar.lottie_rawRes != 0;
        boolean hasFileName = __lottieAnimationViewLocalVar.fileName != null;
        boolean hasUrl = __lottieAnimationViewLocalVar.url != null;
        if (hasRawRes && hasFileName) {
            throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at " +
                    "the same time. Please use only one at once.");
        } else if (hasRawRes) {
            lottieAnimationView.setAnimation(__lottieAnimationViewLocalVar.lottie_rawRes);
        } else if (hasFileName) {
            lottieAnimationView.setAnimation(__lottieAnimationViewLocalVar.fileName);
        } else if (hasUrl) {
            lottieAnimationView.setAnimationFromUrl(__lottieAnimationViewLocalVar.url);
        }
        if (__lottieAnimationViewLocalVar.loop) {
            lottieAnimationView.setRepeatCount(com.airbnb.lottie.LottieDrawable.INFINITE);
        }
        if (__lottieAnimationViewLocalVar.repeatCount != -2) {
            lottieAnimationView.setRepeatCount(__lottieAnimationViewLocalVar.repeatCount);
        }
        if (__lottieAnimationViewLocalVar.autoPlay) {
            lottieAnimationView.playAnimation();
        }
    }

}
