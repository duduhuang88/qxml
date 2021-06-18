package com.qxml.gen.view.attr;

import android.graphics.Paint;
import android.os.Build;
import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.UnSupport;

public interface ViewScrollAttr extends ViewLocalVar {

    class $$ViewScrollVariable {
        public int scrollbars = 0;
        public boolean fadeScrollbars = true;
    }

    @LocalVar
    $$ViewScrollVariable __viewScrollLocalVar = new $$ViewScrollVariable();

    @Attr(AndroidRS.attr.scrollbarSize)
    default void viewScrollbarSize(View view, int size) {
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            view.setScrollBarSize(size);
        }
    }

    @Attr(AndroidRS.attr.scrollX)
    default void viewScrollX(View view, int scrollX) {
        view.setScrollX(scrollX);
    }

    @Attr(AndroidRS.attr.scrollY)
    default void viewScrollY(View view, int scrollY) {
        view.setScrollY(scrollY);
    }

    @Attr(AndroidRS.attr.scrollbarStyle)
    default void viewScrollbarStyle(View view, int styleEnum) {
        view.setScrollBarStyle(styleEnum);
    }

    //false（一直显示滚动条）还有问题，先用scrollbarFadeDuration 99999代替
    @Attr(AndroidRS.attr.fadeScrollbars)
    default void viewFadeScrollbars(View view, boolean fadeScrollbars) {
        __viewScrollLocalVar.fadeScrollbars = fadeScrollbars;
    }

    @Attr(AndroidRS.attr.scrollbars)
    default void viewScrollbars(View view, int flag) {
        __viewScrollLocalVar.scrollbars = flag;
    }

    @Attr(AndroidRS.attr.scrollbarDefaultDelayBeforeFade)
    default void viewScrollbarDefaultDelayBeforeFade(View view, int scrollbarDefaultDelayBeforeFade) {
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            view.setScrollBarDefaultDelayBeforeFade(scrollbarDefaultDelayBeforeFade);
        }
    }

    @Attr(AndroidRS.attr.scrollbarFadeDuration)
    default void viewScrollbarFadeDuration(View view, int scrollbarFadeDuration) {
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            view.setScrollBarFadeDuration(scrollbarFadeDuration);
        }
    }

    @Attr(AndroidRS.attr.scrollbarThumbVertical)
    default void viewScrollbarThumbVertical(View view, ValueInfo valueInfo) {
        android.graphics.drawable.Drawable drawable = com.qxml.tools.DrawableTools.getReferenceDrawable(__context, ___resources, valueInfo);
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            view.setVerticalScrollbarThumbDrawable(drawable);
        } else {
            com.qxml.gen.view.helper.ScrollHelper.setScrollbarThumb(view, drawable, true);
        }*/
        com.qxml.gen.view.helper.ScrollHelper.setScrollbarThumb(view, drawable, true);
    }

    @Attr(AndroidRS.attr.scrollbarThumbHorizontal)
    default void viewScrollbarThumbHorizontal(View view, ValueInfo valueInfo) {
        android.graphics.drawable.Drawable drawable = com.qxml.tools.DrawableTools.getReferenceDrawable(__context, ___resources, valueInfo);
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            view.setHorizontalScrollbarThumbDrawable(drawable);
        } else {
            com.qxml.gen.view.helper.ScrollHelper.setScrollbarThumb(view, drawable, false);
        }*/
        com.qxml.gen.view.helper.ScrollHelper.setScrollbarThumb(view, drawable, false);
    }

    @Attr(AndroidRS.attr.scrollbarTrackVertical)
    default void viewScrollbarTrackVertical(View view, ValueInfo valueInfo) {
        android.graphics.drawable.Drawable drawable = com.qxml.tools.DrawableTools.getReferenceDrawable(__context, ___resources, valueInfo);
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            view.setVerticalScrollbarTrackDrawable(drawable);
        } else {
            com.qxml.gen.view.helper.ScrollHelper.setScrollbarTrack(view, drawable, true);
        }
    }

    @Attr(AndroidRS.attr.scrollbarTrackHorizontal)
    default void viewScrollbarTrackHorizontal(View view, ValueInfo valueInfo) {
        android.graphics.drawable.Drawable drawable = com.qxml.tools.DrawableTools.getReferenceDrawable(__context, ___resources, valueInfo);
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            view.setHorizontalScrollbarTrackDrawable(drawable);
        } else {
            com.qxml.gen.view.helper.ScrollHelper.setScrollbarTrack(view, drawable, false);
        }
    }

    @Attr(AndroidRS.attr.scrollbarAlwaysDrawVerticalTrack)
    default void viewScrollbarAlwaysDrawVerticalTrack(View view, boolean always) {
        if (always) {
            com.qxml.gen.view.helper.ScrollHelper.setAlwaysDrawVerticalTrack(view, always, true);
        }
    }

    @Attr(AndroidRS.attr.scrollbarAlwaysDrawHorizontalTrack)
    default void viewScrollbarAlwaysDrawHorizontalTrack(View view, boolean always) {
        if (always) {
            com.qxml.gen.view.helper.ScrollHelper.setAlwaysDrawVerticalTrack(view, always, false);
        }
    }

    @Attr(AndroidRS.attr.verticalScrollbarPosition)
    default void viewVerticalScrollbarPosition(View view, int verticalScrollbarPosition) {
        view.setVerticalScrollbarPosition(verticalScrollbarPosition);
    }

    @Attr(AndroidRS.attr.overScrollMode)
    default void viewOverScrollMode(View view, int overScrollMode) {
        view.setOverScrollMode(overScrollMode);
    }

    @Attr(AndroidRS.attr.scrollIndicators)
    default void viewScrollIndicators(View view, int scrollIndicators) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(scrollIndicators);
        }
    }

    @OnEnd({AndroidRS.attr.scrollbars, AndroidRS.attr.fadeScrollbars})
    default void onViewScrollFadeEnd(View view) {
        if (__viewScrollLocalVar.scrollbars == 0) {
            view.setVerticalScrollBarEnabled(false);
            view.setHorizontalScrollBarEnabled(false);
        } else {
            if ((__viewScrollLocalVar.scrollbars & 0x00000200) != 0) {
                view.setVerticalScrollBarEnabled(true);
            }
            if ((__viewScrollLocalVar.scrollbars & 0x00000100) != 0) {
                view.setHorizontalScrollBarEnabled(true);
            }
            if (!__viewScrollLocalVar.fadeScrollbars) {
                view.setScrollbarFadingEnabled(false);
            }
        }
    }
}
