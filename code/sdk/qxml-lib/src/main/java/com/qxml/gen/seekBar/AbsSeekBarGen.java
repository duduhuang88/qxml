package com.qxml.gen.seekBar;

import android.widget.AbsSeekBar;

import com.qxml.AndroidRS;
import com.qxml.gen.progressBar.ProgressBarGen;
import com.qxml.helper.AttrHelperKt;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(AbsSeekBar.class)
public class AbsSeekBarGen extends ProgressBarGen {

    public static class $$AbsSeekBarLocalVariable {
        public int thumb = -1;
        public int thumbOffset = 0;
    }

    @LocalVar
    public $$AbsSeekBarLocalVariable __absSeekBarLocalVar = new $$AbsSeekBarLocalVariable();

    @Attr(AndroidRS.attr.thumbTint)
    public void onAbsSeekBarThumbTint(AbsSeekBar absSeekBar, ValueInfo thumbTint) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            absSeekBar.setThumbTintList(thumbTint.getColorStateList(__context));
        }
    }

    @Attr(AndroidRS.attr.thumbTintMode)
    public void onAbsSeekBarThumbTintMode(AbsSeekBar absSeekBar, int thumbTintMode) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            absSeekBar.setThumbTintMode(com.qxml.helper.AttrHelperKt.intToMode(thumbTintMode, null));
        }
    }

    @Attr(AndroidRS.attr.tickMarkTint)
    public void onAbsSeekBarTickMarkTint(AbsSeekBar absSeekBar, ValueInfo tickMarkTint) {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            absSeekBar.setTickMarkTintList(tickMarkTint.getColorStateList(__context));
        }
    }

    @Attr(AndroidRS.attr.tickMarkTintMode)
    public void onAbsSeekBarTickMarkTintMode(AbsSeekBar absSeekBar, int tickMarkTintMode) {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            absSeekBar.setTickMarkTintMode(com.qxml.helper.AttrHelperKt.intToMode(tickMarkTintMode, null));
        }
    }

    @Attr(AndroidRS.attr.thumb)
    public void onAbsSeekBarThumb(AbsSeekBar absSeekBar, int thumb) {
        __absSeekBarLocalVar.thumb = thumb;
    }

    @Attr(AndroidRS.attr.thumbOffset)
    public void onAbsSeekBarThumbOffset(AbsSeekBar absSeekBar, float thumbOffset) {
        __absSeekBarLocalVar.thumbOffset = (int) thumbOffset;
    }

    @Attr(AndroidRS.attr.splitTrack)
    public void onAbsSeekBarSplitTrack(AbsSeekBar absSeekBar, boolean splitTrack) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            absSeekBar.setSplitTrack(splitTrack);
        }
    }

    @OnEnd({AndroidRS.attr.thumbOffset, AndroidRS.attr.thumb})
    public void onAbsSeekBarThumbEnd(AbsSeekBar absSeekBar) {
        if (__absSeekBarLocalVar.thumb != -1) {
            if (__absSeekBarLocalVar.thumb == 0) {
                absSeekBar.setThumb(null);
            } else {
                absSeekBar.setThumb(com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __absSeekBarLocalVar.thumb));
            }
        }
        if (__absSeekBarLocalVar.thumbOffset != 0) {
            absSeekBar.setThumbOffset(__absSeekBarLocalVar.thumbOffset);
        }
    }

}
