package com.qxml.gen.progressBar;

import android.view.View;
import android.widget.ProgressBar;

import com.qxml.AndroidRS;
import com.qxml.gen.view.ViewGen;
import com.qxml.helper.AttrHelperKt;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;


@ViewParse(ProgressBar.class)
public class ProgressBarGen extends ViewGen {

    public static class $$ProgressLocalVariable {
        public int mMinWidth = 24;
        public int mMaxWidth = 48;
        public int mMinHeight = 24;
        public int mMaxHeight = 48;
        public boolean indeterminateOnly = false;
        public boolean indeterminate = false;
        public int min = -1;
        public int max = -1;
        public int progress = -1;
        public int secondaryProgress = -1;
    }

    @LocalVar
    public $$ProgressLocalVariable __progressLocalVar = new $$ProgressLocalVariable();


    @Attr(AndroidRS.attr.animationResolution)
    public void onProgressBarAnimationResolution(ProgressBar progressBar, int animationResolution) {

    }

    @Attr(AndroidRS.attr.interpolator)
    public void onProgressBarInterpolator(ProgressBar progressBar, int interpolator) {
        progressBar.setInterpolator(__context, interpolator);
    }


    @Attr(AndroidRS.attr.progressDrawable)
    public void onProgressBarProgressDrawable(ProgressBar progressBar, int progressDrawable) {
        progressBar.setProgressDrawable(com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, progressDrawable));
    }

    @Override
    public void viewMinWidth(View view, int minWidth) {
        __progressLocalVar.mMinWidth = minWidth;
    }

    @Override
    public void viewMinHeight(View view, int minHeight) {
        __progressLocalVar.mMinHeight = minHeight;
    }

    @Attr(AndroidRS.attr.maxWidth)
    public void onProgressMaxWidth(ProgressBar progressBar, int maxWidth) {
        __progressLocalVar.mMaxWidth = maxWidth;
    }

    @Attr(AndroidRS.attr.maxHeight)
    public void onProgressMaxHeight(ProgressBar progressBar, int maxHeight) {
        __progressLocalVar.mMaxHeight = maxHeight;
    }

    @Attr(AndroidRS.attr.indeterminateDuration)
    public void onProgressBarIndeterminateDuration(ProgressBar progressBar, int indeterminateDuration) {
        com.qxml.gen.progressBar.ProgressBarHelper.setMDurationField(progressBar, indeterminateDuration);
    }

    @Attr(AndroidRS.attr.indeterminateBehavior)
    public void onProgressIndeterminateBehavior(ProgressBar progressBar, int indeterminateBehavior) {
        if (indeterminateBehavior != 1) {
            com.qxml.gen.progressBar.ProgressBarHelper.setMBehaviorField(progressBar, indeterminateBehavior);
        }
    }

    @Attr(AndroidRS.attr.progress)
    public void onProgressProgress(ProgressBar progressBar, int progress) {
        __progressLocalVar.progress = progress;
    }

    @Attr(AndroidRS.attr.secondaryProgress)
    public void onProgressSecondaryProgress(ProgressBar progressBar, int secondaryProgress) {
        __progressLocalVar.secondaryProgress = secondaryProgress;
    }

    @Attr(AndroidRS.attr.indeterminateOnly)
    public void onProgressIndeterminateOnly(ProgressBar progressBar, boolean indeterminateOnly) {
        __progressLocalVar.indeterminateOnly = indeterminateOnly;
    }

    @Attr(AndroidRS.attr.indeterminate)
    public void onProgressIndeterminate(ProgressBar progressBar, boolean indeterminate) {
        __progressLocalVar.indeterminate = indeterminate;
    }

    @Attr(AndroidRS.attr.mirrorForRtl)
    public void onProgressMirrorForRtl(ProgressBar progressBar, boolean mirrorForRtl) {
        com.qxml.gen.progressBar.ProgressBarHelper.setMMirrorForRtlField(progressBar, mirrorForRtl);
    }

    @Attr(AndroidRS.attr.progressTintMode)
    public void onProgressBarProgressTintMode(ProgressBar progressBar, int progressTintMode) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            progressBar.setProgressTintMode(com.qxml.helper.AttrHelperKt.intToMode(progressTintMode, null));
        }
    }

    @Attr(AndroidRS.attr.progressBackgroundTintMode)
    public void onProgressBarBackgroundTintMode(ProgressBar progressBar, int progressBackgroundTint) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            progressBar.setProgressBackgroundTintMode(com.qxml.helper.AttrHelperKt.intToMode(progressBackgroundTint, null));
        }
    }

    @Attr(AndroidRS.attr.progressTint)
    public void onProgressBarProgressTint(ProgressBar progressBar, ValueInfo progressTint) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            progressBar.setProgressTintList(progressTint.getColorStateList(__context));
        }
    }

    @Attr(AndroidRS.attr.progressBackgroundTint)
    public void onProgressBarBackgroundTint(ProgressBar progressBar, ValueInfo backgroundTint) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            progressBar.setProgressBackgroundTintList(backgroundTint.getColorStateList(__context));
        }
    }

    @Attr(AndroidRS.attr.indeterminateTint)
    public void onProgressBarIndeterminateTint(ProgressBar progressBar, ValueInfo indeterminateTint) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            progressBar.setIndeterminateTintList(indeterminateTint.getColorStateList(__context));
        }
    }

    @Attr(AndroidRS.attr.indeterminateTintMode)
    public void onProgressBarIndeterminateTintMode(ProgressBar progressBar, int indeterminateTintMode) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            progressBar.setIndeterminateTintMode(com.qxml.helper.AttrHelperKt.intToMode(indeterminateTintMode, null));
        }
    }

    @Attr(AndroidRS.attr.secondaryProgressTint)
    public void onProgressBarSecondaryProgressTint(ProgressBar progressBar, ValueInfo secondaryProgressTint) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            progressBar.setSecondaryProgressTintList(secondaryProgressTint.getColorStateList(__context));
        }
    }

    @Attr(AndroidRS.attr.secondaryProgressTintMode)
    public void onProgressBarSecondaryProgressTintMode(ProgressBar progressBar, int secondaryProgressTintMode) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            progressBar.setSecondaryProgressTintMode(com.qxml.helper.AttrHelperKt.intToMode(secondaryProgressTintMode, null));
        }
    }


    @Attr(AndroidRS.attr.max)
    public void onProgressMax(ProgressBar progressBar, int max) {
        __progressLocalVar.max = max;
    }

    @Attr(AndroidRS.attr.min)
    public void onProgressMin(ProgressBar progressBar, int min) {
        __progressLocalVar.min = min;
    }

    @OnEnd({AndroidRS.attr.max})
    public void onProgressBarMaxEnd(ProgressBar progressBar) {
        progressBar.setMax(__progressLocalVar.max);
    }

    @OnEnd({AndroidRS.attr.min})
    public void onProgressBarMinEnd(ProgressBar progressBar) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            progressBar.setMin(__progressLocalVar.min);
        }
    }

    @OnEnd({AndroidRS.attr.progress})
    public void onProgressBarProgressEnd(ProgressBar progressBar) {
        progressBar.setProgress(__progressLocalVar.progress);
    }

    @OnEnd({AndroidRS.attr.secondaryProgress})
    public void onProgressBarSecondaryProgressEnd(ProgressBar progressBar) {
        progressBar.setSecondaryProgress(__progressLocalVar.secondaryProgress);
    }

    @OnEnd({AndroidRS.attr.indeterminateOnly, AndroidRS.attr.indeterminate})
    public void onProgressBarIndeterminateEnd(ProgressBar progressBar) {
        if (__progressLocalVar.indeterminateOnly || __progressLocalVar.indeterminate) {
            progressBar.setIndeterminate(true);
        }
    }

    @OnEnd({AndroidRS.attr.maxWidth, AndroidRS.attr.maxHeight, AndroidRS.attr.minWidth, AndroidRS.attr.minHeight})
    public void onProgressBarSizeEnd(ProgressBar progressBar) {
        com.qxml.gen.progressBar.ProgressBarHelper.setMinMaxField(progressBar, __progressLocalVar.mMinWidth
                , __progressLocalVar.mMaxWidth, __progressLocalVar.mMinHeight, __progressLocalVar.mMaxHeight);
    }
}
