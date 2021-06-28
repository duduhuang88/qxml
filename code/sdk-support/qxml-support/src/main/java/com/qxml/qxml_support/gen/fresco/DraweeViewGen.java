package com.qxml.qxml_support.gen.fresco;

import android.view.View;

import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.DraweeView;
import com.qxml.gen.imageView.ImageViewGen;
import com.qxml.qxml_support.RS;
import com.qxml.tools.DrawableTools;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(DraweeView.class)
public class DraweeViewGen extends ImageViewGen {

    public static class $$DraweeViewLocalVar {
        public int fadeDuration = 0;
        public float viewAspectRatio = 0f;
        public int placeholderImage = 0;
        public int placeholderImageScaleType = -2;
        public int retryImage = 0;
        public int retryImageScaleType = -2;
        public int failureImage = 0;
        public int failureImageScaleType = -2;
        public int progressBarImage = 0;
        public int progressBarImageScaleType = -2;
        public int progressBarAutoRotateInterval = 0;
        public int actualImageScaleType = -2;
        public int backgroundImage = 0;
        public int overlayImage = 0;
        public int pressedStateOverlayImage = 0;

        public boolean roundAsCircle = false;
        public int roundedCornerRadius = 0;
        public boolean roundTopLeft = true;
        public boolean roundTopRight = true;
        public boolean roundBottomRight = true;
        public boolean roundBottomLeft = true;
        public boolean roundTopStart = true;
        public boolean roundTopEnd = true;
        public boolean roundBottomStart = true;
        public boolean roundBottomEnd = true;
        public int roundWithOverlayColor = Integer.MIN_VALUE;
        public int roundingBorderWidth = 0;
        public int roundingBorderColor = Integer.MIN_VALUE;
        public int roundingBorderPadding = 0;
        public com.facebook.drawee.generic.GenericDraweeHierarchyBuilder builder = new com.facebook.drawee.generic.GenericDraweeHierarchyBuilder(___resources);
    }

    @LocalVar
    public $$DraweeViewLocalVar __draweeViewLocalVar  = new $$DraweeViewLocalVar();

    @Attr(RS.attr.fadeDuration)
    public void draweeViewFadeDuration(DraweeView draweeView, int fadeDuration) {
        __draweeViewLocalVar.fadeDuration = fadeDuration;
    }

    @Attr(RS.attr.viewAspectRatio)
    public void draweeViewViewAspectRatio(DraweeView draweeView, float viewAspectRatio) {
        __draweeViewLocalVar.viewAspectRatio = viewAspectRatio;
    }

    @Attr(RS.attr.placeholderImage)
    public void draweeViewPlaceholderImage(DraweeView draweeView, int placeholderImage) {
        __draweeViewLocalVar.placeholderImage = placeholderImage;
    }

    @Attr(RS.attr.placeholderImageScaleType)
    public void draweeViewPlaceholderImageScaleType(DraweeView draweeView, int placeholderImageScaleType) {
        __draweeViewLocalVar.placeholderImageScaleType = placeholderImageScaleType;
    }

    @Attr(RS.attr.retryImage)
    public void draweeViewRetryImage(DraweeView draweeView, int retryImage) {
        __draweeViewLocalVar.retryImage = retryImage;
    }

    @Attr(RS.attr.retryImageScaleType)
    public void draweeViewRetryImageScaleType(DraweeView draweeView, int retryImageScaleType) {
        __draweeViewLocalVar.retryImageScaleType = retryImageScaleType;
    }

    @Attr(RS.attr.failureImage)
    public void draweeViewFailureImage(DraweeView draweeView, int failureImage) {
        __draweeViewLocalVar.failureImage = failureImage;
    }

    @Attr(RS.attr.failureImageScaleType)
    public void draweeViewFailureImageScaleType(DraweeView draweeView, int failureImageScaleType) {
        __draweeViewLocalVar.failureImageScaleType = failureImageScaleType;
    }

    @Attr(RS.attr.progressBarImage)
    public void draweeViewProgressBarImage(DraweeView draweeView, int progressBarImage) {
        __draweeViewLocalVar.progressBarImage = progressBarImage;
    }

    @Attr(RS.attr.progressBarImageScaleType)
    public void draweeViewProgressBarImageScaleType(DraweeView draweeView, int progressBarImageScaleType) {
        __draweeViewLocalVar.progressBarImageScaleType = progressBarImageScaleType;
    }

    @Attr(RS.attr.progressBarAutoRotateInterval)
    public void draweeViewProgressBarAutoRotateInterval(DraweeView draweeView, int progressBarAutoRotateInterval) {
        __draweeViewLocalVar.progressBarAutoRotateInterval = progressBarAutoRotateInterval;
    }

    @Attr(RS.attr.actualImageScaleType)
    public void draweeViewActualImageScaleType(DraweeView draweeView, int actualImageScaleType) {
        __draweeViewLocalVar.actualImageScaleType = actualImageScaleType;
    }

    @Attr(RS.attr.backgroundImage)
    public void draweeViewBackgroundImage(DraweeView draweeView, int backgroundImage) {
        __draweeViewLocalVar.backgroundImage = backgroundImage;
    }

    @Attr(RS.attr.overlayImage)
    public void draweeViewOverlayImage(DraweeView draweeView, int overlayImage) {
        __draweeViewLocalVar.overlayImage = overlayImage;
    }

    @Attr(RS.attr.pressedStateOverlayImage)
    public void draweeViewPressedStateOverlayImage(DraweeView draweeView, int pressedStateOverlayImage) {
        __draweeViewLocalVar.pressedStateOverlayImage = pressedStateOverlayImage;
    }

    @Attr(RS.attr.roundAsCircle)
    public void draweeViewRoundAsCircle(DraweeView draweeView, boolean roundAsCircle) {
        __draweeViewLocalVar.roundAsCircle = roundAsCircle;
    }

    @Attr(RS.attr.roundedCornerRadius)
    public void draweeViewRoundedCornerRadius(DraweeView draweeView, int roundedCornerRadius) {
        __draweeViewLocalVar.roundedCornerRadius = roundedCornerRadius;
    }

    @Attr(RS.attr.roundTopLeft)
    public void draweeViewRoundTopLeft(DraweeView draweeView, boolean roundTopLeft) {
        __draweeViewLocalVar.roundTopLeft = roundTopLeft;
    }

    @Attr(RS.attr.roundTopRight)
    public void draweeViewRoundTopRight(DraweeView draweeView, boolean roundTopRight) {
        __draweeViewLocalVar.roundTopRight = roundTopRight;
    }

    @Attr(RS.attr.roundBottomRight)
    public void draweeViewRoundBottomRight(DraweeView draweeView, boolean roundBottomRight) {
        __draweeViewLocalVar.roundBottomRight = roundBottomRight;
    }

    @Attr(RS.attr.roundBottomLeft)
    public void draweeViewRoundBottomLeft(DraweeView draweeView, boolean roundBottomLeft) {
        __draweeViewLocalVar.roundBottomLeft = roundBottomLeft;
    }

    @Attr(RS.attr.roundTopStart)
    public void draweeViewRoundTopStart(DraweeView draweeView, boolean roundTopStart) {
        __draweeViewLocalVar.roundTopStart = roundTopStart;
    }

    @Attr(RS.attr.roundTopEnd)
    public void draweeViewRoundTopEnd(DraweeView draweeView, boolean roundTopEnd) {
        __draweeViewLocalVar.roundTopEnd = roundTopEnd;
    }

    @Attr(RS.attr.roundBottomStart)
    public void draweeViewRoundBottomStart(DraweeView draweeView, boolean roundBottomStart) {
        __draweeViewLocalVar.roundBottomStart = roundBottomStart;
    }

    @Attr(RS.attr.roundBottomEnd)
    public void draweeViewRoundBottomEnd(DraweeView draweeView, boolean roundBottomEnd) {
        __draweeViewLocalVar.roundBottomEnd = roundBottomEnd;
    }

    @Attr(RS.attr.roundWithOverlayColor)
    public void draweeViewRoundWithOverlayColor(DraweeView draweeView, ValueInfo roundWithOverlayColor) {
        __draweeViewLocalVar.roundWithOverlayColor = roundWithOverlayColor.colorValue;
    }

    @Attr(RS.attr.roundingBorderWidth)
    public void draweeViewRoundingBorderWidth(DraweeView draweeView, int roundingBorderWidth) {
        __draweeViewLocalVar.roundingBorderWidth = roundingBorderWidth;
    }

    @Attr(RS.attr.roundingBorderColor)
    public void draweeViewRoundingBorderColor(DraweeView draweeView, ValueInfo roundingBorderColor) {
        __draweeViewLocalVar.roundingBorderColor = roundingBorderColor.colorValue;
    }

    @Attr(RS.attr.roundingBorderPadding)
    public void draweeViewRoundingBorderPadding(DraweeView draweeView, int roundingBorderPadding) {
        __draweeViewLocalVar.roundingBorderPadding = roundingBorderPadding;
    }

    @OnEnd({
            RS.attr.fadeDuration, RS.attr.viewAspectRatio, RS.attr.placeholderImage, RS.attr.placeholderImageScaleType
            , RS.attr.retryImage, RS.attr.retryImageScaleType, RS.attr.failureImage, RS.attr.failureImageScaleType
            , RS.attr.progressBarImage, RS.attr.progressBarImageScaleType, RS.attr.progressBarAutoRotateInterval, RS.attr.actualImageScaleType
            , RS.attr.backgroundImage, RS.attr.overlayImage, RS.attr.pressedStateOverlayImage, RS.attr.roundAsCircle
            , RS.attr.roundedCornerRadius, RS.attr.roundTopLeft, RS.attr.roundTopRight, RS.attr.roundBottomRight
            , RS.attr.roundBottomLeft, RS.attr.roundTopStart, RS.attr.roundTopEnd, RS.attr.roundBottomStart
            , RS.attr.roundBottomEnd, RS.attr.roundWithOverlayColor, RS.attr.roundingBorderWidth, RS.attr.roundingBorderColor
            , RS.attr.roundingBorderPadding
    })
    public void onDraweeViewEnd(DraweeView draweeView) {
        if (__draweeViewLocalVar.fadeDuration != 0) {
            __draweeViewLocalVar.builder.setFadeDuration(__draweeViewLocalVar.fadeDuration);
        }
        if (__draweeViewLocalVar.viewAspectRatio != 0f) {
            __draweeViewLocalVar.builder.setDesiredAspectRatio(__draweeViewLocalVar.viewAspectRatio);
        }
        if (__draweeViewLocalVar.placeholderImage != 0) {
            __draweeViewLocalVar.builder.setPlaceholderImage(__draweeViewLocalVar.placeholderImage);
        }
        if (__draweeViewLocalVar.placeholderImageScaleType != -2) {
            __draweeViewLocalVar.builder.setPlaceholderImageScaleType(FrescoHelper.getScaleTypeFromXml(__draweeViewLocalVar.placeholderImageScaleType));
        }

        if (__draweeViewLocalVar.retryImage != 0) {
            __draweeViewLocalVar.builder.setRetryImage(__draweeViewLocalVar.retryImage);
        }
        if (__draweeViewLocalVar.retryImageScaleType != -2) {
            __draweeViewLocalVar.builder.setRetryImageScaleType(FrescoHelper.getScaleTypeFromXml(__draweeViewLocalVar.retryImageScaleType));
        }
        if (__draweeViewLocalVar.failureImage != 0) {
            __draweeViewLocalVar.builder.setFailureImage(__draweeViewLocalVar.failureImage);
        }
        if (__draweeViewLocalVar.failureImageScaleType != -2) {
            __draweeViewLocalVar.builder.setFailureImageScaleType(FrescoHelper.getScaleTypeFromXml(__draweeViewLocalVar.failureImageScaleType));
        }

        if (__draweeViewLocalVar.progressBarImage != 0) {
            __draweeViewLocalVar.builder.setProgressBarImage(__draweeViewLocalVar.progressBarImage);
        }
        if (__draweeViewLocalVar.progressBarImageScaleType != -2) {
            __draweeViewLocalVar.builder.setProgressBarImageScaleType(FrescoHelper.getScaleTypeFromXml(__draweeViewLocalVar.progressBarImageScaleType));
        }
        if (__draweeViewLocalVar.progressBarAutoRotateInterval > 0 && __draweeViewLocalVar.builder.getProgressBarImage() != null) {
            __draweeViewLocalVar.builder.setProgressBarImage(new AutoRotateDrawable(__draweeViewLocalVar.builder.getProgressBarImage(), __draweeViewLocalVar.progressBarAutoRotateInterval));
        }
        if (__draweeViewLocalVar.actualImageScaleType != -2) {
            __draweeViewLocalVar.builder.setActualImageScaleType(FrescoHelper.getScaleTypeFromXml(__draweeViewLocalVar.actualImageScaleType));
        }

        if (__draweeViewLocalVar.backgroundImage != 0) {
            __draweeViewLocalVar.builder.setBackground(DrawableTools.getDrawable(__context, ___resources, __draweeViewLocalVar.backgroundImage));
        }
        if (__draweeViewLocalVar.overlayImage != -2) {
            __draweeViewLocalVar.builder.setOverlay(DrawableTools.getDrawable(__context, ___resources, __draweeViewLocalVar.overlayImage));
        }
        if (__draweeViewLocalVar.pressedStateOverlayImage != 0) {
            __draweeViewLocalVar.builder.setPressedStateOverlay(DrawableTools.getDrawable(__context, ___resources, __draweeViewLocalVar.pressedStateOverlayImage));
        }

        RoundingParams roundingParams = new RoundingParams();
        __draweeViewLocalVar.builder.setRoundingParams(roundingParams);
        if (__draweeViewLocalVar.roundAsCircle) {
            roundingParams.setRoundAsCircle(true);
        }

        if (__draweeViewLocalVar.roundWithOverlayColor != Integer.MIN_VALUE) {
            roundingParams.setOverlayColor(__draweeViewLocalVar.roundWithOverlayColor);
        }
        if (__draweeViewLocalVar.roundingBorderWidth != 0) {
            roundingParams.setBorderWidth((float)__draweeViewLocalVar.roundingBorderWidth);
        }
        if (__draweeViewLocalVar.roundingBorderColor != Integer.MIN_VALUE) {
            roundingParams.setBorderColor(__draweeViewLocalVar.roundingBorderColor);
        }
        if (__draweeViewLocalVar.roundingBorderPadding != 0) {
            roundingParams.setPadding((float)__draweeViewLocalVar.roundingBorderPadding);
        }

        if (android.os.Build.VERSION.SDK_INT >= 17 && ___resources.getConfiguration()
                .getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            __draweeViewLocalVar.roundTopLeft = __draweeViewLocalVar.roundTopLeft && __draweeViewLocalVar.roundTopEnd;
            __draweeViewLocalVar.roundTopRight = __draweeViewLocalVar.roundTopRight && __draweeViewLocalVar.roundTopStart;
            __draweeViewLocalVar.roundBottomRight = __draweeViewLocalVar.roundBottomRight && __draweeViewLocalVar.roundBottomStart;
            __draweeViewLocalVar.roundBottomLeft = __draweeViewLocalVar.roundBottomLeft && __draweeViewLocalVar.roundBottomEnd;
        } else {
            __draweeViewLocalVar.roundTopLeft = __draweeViewLocalVar.roundTopLeft && __draweeViewLocalVar.roundTopStart;
            __draweeViewLocalVar.roundTopRight = __draweeViewLocalVar.roundTopRight && __draweeViewLocalVar.roundTopEnd;
            __draweeViewLocalVar.roundBottomRight = __draweeViewLocalVar.roundBottomRight && __draweeViewLocalVar.roundBottomEnd;
            __draweeViewLocalVar.roundBottomLeft = __draweeViewLocalVar.roundBottomLeft && __draweeViewLocalVar.roundBottomStart;
        }

        if (__draweeViewLocalVar.roundedCornerRadius > 0) {
            roundingParams.setCornersRadii(
                    __draweeViewLocalVar.roundTopLeft ? (float)__draweeViewLocalVar.roundedCornerRadius : 0f,
                    __draweeViewLocalVar.roundTopRight ? (float)__draweeViewLocalVar.roundedCornerRadius : 0f,
                    __draweeViewLocalVar.roundBottomRight ? (float)__draweeViewLocalVar.roundedCornerRadius : 0f,
                    __draweeViewLocalVar.roundBottomLeft ? (float)__draweeViewLocalVar.roundedCornerRadius : 0f);
        }

        draweeView.setHierarchy(__draweeViewLocalVar.builder.build());
    }

}
