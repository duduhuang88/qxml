package com.qxml.qxml_support.gen.fresco;

import android.view.View;

import com.facebook.drawee.view.DraweeView;
import com.qxml.gen.imageView.ImageViewGen;
import com.qxml.qxml_support.RS;
import com.qxml.qxml_support.gen.view.attr.ConstrainAttr;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(DraweeView.class)
public class DraweeViewGen extends ImageViewGen implements ConstrainAttr {

    public static class $$DraweeViewLocalVar {

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

        public com.facebook.drawee.generic.GenericDraweeHierarchyBuilder builder = null;
    }

    @LocalVar
    public $$DraweeViewLocalVar __draweeViewLocalVar  = new $$DraweeViewLocalVar();

    @Override
    public void viewStart(View view, boolean useless) {
        if (___cur_layout_param instanceof android.widget.RelativeLayout.LayoutParams) {
            __relativeLocalVar.rlLp = (android.widget.RelativeLayout.LayoutParams) ___cur_layout_param;
        } else if (___cur_layout_param instanceof android.support.constraint.ConstraintLayout.LayoutParams) {
            __constrainLocalVar.constraintLp = (android.support.constraint.ConstraintLayout.LayoutParams) ___cur_layout_param;
        }
        __draweeViewLocalVar.builder = new com.facebook.drawee.generic.GenericDraweeHierarchyBuilder(___resources);
    }

    @Attr(RS.attr.fadeDuration)
    public void draweeViewFadeDuration(DraweeView draweeView, int fadeDuration) {
        __draweeViewLocalVar.builder.setFadeDuration(fadeDuration);
    }

    @Attr(RS.attr.viewAspectRatio)
    public void draweeViewViewAspectRatio(DraweeView draweeView, float viewAspectRatio) {
        __draweeViewLocalVar.builder.setDesiredAspectRatio(viewAspectRatio);
    }

    @Attr(RS.attr.placeholderImage)
    public void draweeViewPlaceholderImage(DraweeView draweeView, int placeholderImage) {
        __draweeViewLocalVar.builder.setPlaceholderImage(placeholderImage);
    }

    @Attr(RS.attr.placeholderImageScaleType)
    public void draweeViewPlaceholderImageScaleType(DraweeView draweeView, int placeholderImageScaleType) {
        __draweeViewLocalVar.builder.setPlaceholderImageScaleType(com.qxml.qxml_support.gen.fresco.FrescoHelper.getScaleTypeFromXml(placeholderImageScaleType));
    }

    @Attr(RS.attr.retryImage)
    public void draweeViewRetryImage(DraweeView draweeView, int retryImage) {
        __draweeViewLocalVar.builder.setRetryImage(retryImage);
    }

    @Attr(RS.attr.retryImageScaleType)
    public void draweeViewRetryImageScaleType(DraweeView draweeView, int retryImageScaleType) {
        __draweeViewLocalVar.builder.setRetryImageScaleType(com.qxml.qxml_support.gen.fresco.FrescoHelper.getScaleTypeFromXml(retryImageScaleType));
    }

    @Attr(RS.attr.failureImage)
    public void draweeViewFailureImage(DraweeView draweeView, int failureImage) {
        __draweeViewLocalVar.builder.setFailureImage(failureImage);
    }

    @Attr(RS.attr.failureImageScaleType)
    public void draweeViewFailureImageScaleType(DraweeView draweeView, int failureImageScaleType) {
        __draweeViewLocalVar.builder.setFailureImageScaleType(com.qxml.qxml_support.gen.fresco.FrescoHelper.getScaleTypeFromXml(failureImageScaleType));
    }

    @Attr(RS.attr.progressBarImage)
    public void draweeViewProgressBarImage(DraweeView draweeView, int progressBarImage) {
        __draweeViewLocalVar.builder.setProgressBarImage(progressBarImage);
    }

    @Attr(RS.attr.progressBarImageScaleType)
    public void draweeViewProgressBarImageScaleType(DraweeView draweeView, int progressBarImageScaleType) {
        __draweeViewLocalVar.builder.setProgressBarImageScaleType(com.qxml.qxml_support.gen.fresco.FrescoHelper.getScaleTypeFromXml(progressBarImageScaleType));
    }

    @Attr(RS.attr.progressBarAutoRotateInterval)
    public void draweeViewProgressBarAutoRotateInterval(DraweeView draweeView, int progressBarAutoRotateInterval) {
        if (progressBarAutoRotateInterval > 0 && __draweeViewLocalVar.builder.getProgressBarImage() != null) {
            __draweeViewLocalVar.builder.setProgressBarImage(new com.facebook.drawee.drawable.AutoRotateDrawable(__draweeViewLocalVar.builder.getProgressBarImage(), progressBarAutoRotateInterval));
        }
    }

    @Attr(RS.attr.actualImageScaleType)
    public void draweeViewActualImageScaleType(DraweeView draweeView, int actualImageScaleType) {
        __draweeViewLocalVar.builder.setActualImageScaleType(com.qxml.qxml_support.gen.fresco.FrescoHelper.getScaleTypeFromXml(actualImageScaleType));
    }

    @Attr(RS.attr.backgroundImage)
    public void draweeViewBackgroundImage(DraweeView draweeView, int backgroundImage) {
        __draweeViewLocalVar.builder.setBackground(com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, backgroundImage));
    }

    @Attr(RS.attr.overlayImage)
    public void draweeViewOverlayImage(DraweeView draweeView, int overlayImage) {
        __draweeViewLocalVar.builder.setOverlay(com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, overlayImage));
    }

    @Attr(RS.attr.pressedStateOverlayImage)
    public void draweeViewPressedStateOverlayImage(DraweeView draweeView, int pressedStateOverlayImage) {
        __draweeViewLocalVar.builder.setPressedStateOverlay(com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, pressedStateOverlayImage));
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
            RS.attr.roundAsCircle
            , RS.attr.roundedCornerRadius, RS.attr.roundTopLeft, RS.attr.roundTopRight, RS.attr.roundBottomRight
            , RS.attr.roundBottomLeft, RS.attr.roundTopStart, RS.attr.roundTopEnd, RS.attr.roundBottomStart
            , RS.attr.roundBottomEnd, RS.attr.roundWithOverlayColor, RS.attr.roundingBorderWidth, RS.attr.roundingBorderColor
            , RS.attr.roundingBorderPadding
    })
    public void onDraweeViewEnd(DraweeView draweeView) {
        {
            com.facebook.drawee.generic.RoundingParams roundingParams = new com.facebook.drawee.generic.RoundingParams();
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
        }
    }

    @OnEnd
    public void onDraweeViewSetEnd(DraweeView draweeView) {
        draweeView.setHierarchy(__draweeViewLocalVar.builder.build());
    }

}
