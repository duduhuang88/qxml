package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.RS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.UnSupport;

public interface ViewCommonAttr extends ViewLocalVar, RelativeAttr {

    class $$ViewSizeLocalVar {
        public int minHeight = 0;
        public int minWidth = 0;
        public int maxHeight = 2147483647;
        public int maxWidth = 2147483647;
    }

    @LocalVar
    $$ViewSizeLocalVar __viewSizeLocalVar = new $$ViewSizeLocalVar();

    @Attr(RS.attr.qxml_start)
    default void viewStart(View view, boolean useless) {
        //在View创建后设置属性时首先调用
        if (___cur_layout_param instanceof android.widget.RelativeLayout.LayoutParams) {
            __relativeLocalVar.rlLp = (android.widget.RelativeLayout.LayoutParams) ___cur_layout_param;
        }
    }

    @Attr(AndroidRS.attr.id)
    default void viewId(View view, int id) {
        view.setId(id);
    }

    @Attr(value = AndroidRS.attr.layout_width, requiredCondition = "___cur_layout_param != null")
    default void viewLayoutWidth(View view, int width) {
        ___cur_layout_param.width = width;
    }

    @Attr(value = AndroidRS.attr.layout_height, requiredCondition = "___cur_layout_param != null")
    default void viewLayoutHeight(View view, int height) {
        ___cur_layout_param.height = height;
    }

    @Attr(AndroidRS.attr.layoutDirection)
    default void viewLayoutDirection(View view, int layoutDirection) {
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            view.setLayoutDirection(layoutDirection);
        }
    }

    @Attr(AndroidRS.attr.accessibilityHeading)
    default void viewAccessibilityHeading(View view, boolean heading) {
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            view.setAccessibilityHeading(heading);
        }
    }

    @Attr(AndroidRS.attr.alpha)
    default void viewAlpha(View view, float alpha) {
        view.setAlpha(alpha);
    }

    @Attr(AndroidRS.attr.foreground)
    default void viewForeground(View view, ValueInfo valueInfo) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (valueInfo.isColor()) {
                view.setForeground(new android.graphics.drawable.ColorDrawable(valueInfo.colorValue));
            } else if (valueInfo.isReference()) {
                //view.setForeground(android.support.v4.content.ContextCompat.getDrawable(view.getContext(), valueInfo.resourceId));
                view.setForeground(com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, valueInfo.resourceId));
            } else if (valueInfo.isNull()) {
                view.setForeground(null);
            }
        }
    }

    //没设置padding时view会使用background drawable的padding(详见View_resolvePadding)
    //所以先重置下padding，后续padding在onEnd中重新设置
    @Attr(AndroidRS.attr.background)
    default void viewBackground(View view, ValueInfo valueInfo) {
        //view.setPadding(0, 0, 0, 0);
        if (valueInfo.isColor()) {
            view.setBackgroundColor(valueInfo.colorValue);
        } else if (valueInfo.isReference()) {
            view.setBackgroundResource(valueInfo.resourceId);
        } else if (valueInfo.isNull()) {
            view.setBackgroundResource(0);
        }
    }

    @Attr(AndroidRS.attr.contentDescription)
    default void viewContentDescription(View view, String contentDescription) {
        view.setContentDescription(contentDescription);
    }

    @Attr(AndroidRS.attr.drawingCacheQuality)
    default void viewDrawingCacheQuality(View view, int quality) {
        view.setDrawingCacheQuality(quality);
    }

    @Attr(AndroidRS.attr.duplicateParentState)
    default void viewDuplicateParentState(View view, boolean state) {
        view.setDuplicateParentStateEnabled(state);
    }

    @Attr(AndroidRS.attr.requiresFadingEdge)
    default void viewRequiresFadingEdge(View view, int fadingEdge) {
        view.setHorizontalFadingEdgeEnabled((fadingEdge & 0x00001000) != 0);
        view.setVerticalFadingEdgeEnabled((fadingEdge & 0x00002000) != 0);
    }

    @UnSupport("ignore, use requiresFadingEdge instead")
    @Attr(AndroidRS.attr.fadingEdge)
    default void viewFadingEdge(View view, int fadingEdge) {
    }

    @Attr(AndroidRS.attr.fadingEdgeLength)
    default void viewFadingEdgeLength(View view, int length) {
        view.setFadingEdgeLength(length);
    }

    @Attr(AndroidRS.attr.fitsSystemWindows)
    default void viewFitsSystemWindows(View view, boolean fit) {
        view.setFitsSystemWindows(fit);
    }

    @Attr(AndroidRS.attr.isScrollContainer)
    default void viewIsScrollContainer(View view, boolean isScrollContainer) {
        if (isScrollContainer) {
            view.setScrollContainer(true);
        }
    }

    @Attr(AndroidRS.attr.hapticFeedbackEnabled)
    default void viewHapticFeedbackEnabled(View view, boolean hapticFeedbackEnabled) {
        view.setHapticFeedbackEnabled(hapticFeedbackEnabled);
    }

    @Attr(AndroidRS.attr.keepScreenOn)
    default void viewKeepScreenOn(View view, boolean keepScreenOn) {
        view.setKeepScreenOn(keepScreenOn);
    }

    @Attr(AndroidRS.attr.keyboardNavigationCluster)
    default void viewKeyboardNavigationCluster(View view, boolean keyboardNavigationCluster) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            view.setKeyboardNavigationCluster(keyboardNavigationCluster);
        }
    }

    @Attr(AndroidRS.attr.layerType)
    default void viewLayerType(View view, int layerType) {
        view.setLayerType(layerType, null);
    }

    @Attr(AndroidRS.attr.minWidth)
    default void viewMinWidth(View view, int minWidth) {
        __viewSizeLocalVar.minWidth = minWidth;
        view.setMinimumWidth(minWidth);
    }

    @Attr(AndroidRS.attr.minHeight)
    default void viewMinHeight(View view, int minHeight) {
        __viewSizeLocalVar.minHeight = minHeight;
        view.setMinimumHeight(minHeight);
    }

    //view 没有 max size, 这里给需要使用的view赋值
    @Attr(AndroidRS.attr.maxWidth)
    default void viewMaxWidth(View view, int maxWidth) {
        __viewSizeLocalVar.maxWidth = maxWidth;
    }

    //view 没有 max size, 这里给需要使用的view赋值
    @Attr(AndroidRS.attr.maxHeight)
    default void viewMaxHeight(View view, int maxHeight) {
        __viewSizeLocalVar.maxHeight = maxHeight;
    }

    @Attr(AndroidRS.attr.nextClusterForward)
    default void viewNextClusterForward(View view, ValueInfo valueInfo) {
        if (android.os.Build.VERSION.SDK_INT >= 26 && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ID && valueInfo.isReference()) {
            view.setNextClusterForwardId(valueInfo.resourceId);
        }
    }

    @Attr(AndroidRS.attr.outlineSpotShadowColor)
    default void viewOutlineSpotShadowColor(View view, ValueInfo valueInfo) {
        if (android.os.Build.VERSION.SDK_INT >= 28 && valueInfo.isColor()) {
            view.setOutlineSpotShadowColor(valueInfo.colorValue);
        }
    }

    @Attr(AndroidRS.attr.outlineAmbientShadowColor)
    default void viewOutlineAmbientShadowColor(View view, ValueInfo valueInfo) {
        if (android.os.Build.VERSION.SDK_INT >= 28 && valueInfo.isColor()) {
            view.setOutlineAmbientShadowColor(valueInfo.colorValue);
        }
    }

    @Attr(AndroidRS.attr.saveEnabled)
    default void viewSaveEnabled(View view, boolean saveEnable) {
        view.setSaveEnabled(saveEnable);
    }

    @Attr(AndroidRS.attr.soundEffectsEnabled)
    default void viewSoundEffectsEnabled(View view, boolean soundEffectsEnabled) {
        view.setSoundEffectsEnabled(soundEffectsEnabled);
    }

    @Attr(AndroidRS.attr.tag)
    default void viewTag(View view, String tag) {
        view.setTag(tag);
    }

    @Attr(AndroidRS.attr.textAlignment)
    default void viewTextAlignment(View view, int textAlignment) {
        if (android.os.Build.VERSION.SDK_INT >= 17) view.setTextAlignment(textAlignment);
    }

    @Attr(AndroidRS.attr.textDirection)
    default void viewTextDirection(View view, int textDirection) {
        if (android.os.Build.VERSION.SDK_INT >= 17) view.setTextDirection(textDirection);
    }

    @Attr(AndroidRS.attr.theme)
    default void viewTheme(View view, int theme) {
        //ignore
    }
    
}
