package com.qxml.qxml_support.gen.textView.attr;

import android.widget.TextView;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;

/**
 * autoSize的属性在textView中为float，而获取值与设置值API参数为int，所以UI可能会对不上
 */
public interface TextViewAutoSizeAttr extends ViewLocalVar {

    class $$TextViewAutoSizeLocalVariable {
        public int autoSizeTextType = 0;
        public int autoSizeMinTextSize = -1;
        public int autoSizeMaxTextSize = -1;
        public int autoSizeStepGranularity = 1;
        public int autoSizePresetSizes = -1;
    }

    @LocalVar
    $$TextViewAutoSizeLocalVariable __textViewAutoSizeLocalVar = new $$TextViewAutoSizeLocalVariable();

    @Attr(AndroidRS.attr.autoSizeTextType)
    default void textViewTextAutoSizeTextType(TextView textView, int autoSizeTextType) {
        __textViewAutoSizeLocalVar.autoSizeTextType = autoSizeTextType;
    }

    @Attr(AndroidRS.attr.autoSizeMinTextSize)
    default void textViewTextAutoSizeMinTextSize(TextView textView, int autoSizeMinTextSize) {
        __textViewAutoSizeLocalVar.autoSizeMinTextSize = autoSizeMinTextSize;
    }

    @Attr(AndroidRS.attr.autoSizeMaxTextSize)
    default void textViewTextAutoSizeMaxTextSize(TextView textView, int autoSizeMaxTextSize) {
        __textViewAutoSizeLocalVar.autoSizeMaxTextSize = autoSizeMaxTextSize;
    }

    @Attr(AndroidRS.attr.autoSizeStepGranularity)
    default void textViewTextAutoSizeStepGranularity(TextView textView, float autoSizeStepGranularity) {
        __textViewAutoSizeLocalVar.autoSizeStepGranularity = (int) autoSizeStepGranularity;
    }

    @Attr(AndroidRS.attr.autoSizePresetSizes)
    default void textViewTextAutoSizePresetSizes(TextView textView, int autoSizePresetSizes) {
        __textViewAutoSizeLocalVar.autoSizePresetSizes = autoSizePresetSizes;
        android.support.v4.widget.TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(textView, textView.getContext().getResources().getIntArray(autoSizePresetSizes), android.util.TypedValue.COMPLEX_UNIT_PX);
    }

    @Attr(RS.attr.autoSizeTextType)
    default void textViewAppTextAutoSizeTextType(TextView textView, int autoSizeTextType) {
        if (__textViewAutoSizeLocalVar.autoSizeTextType == 0) {
            __textViewAutoSizeLocalVar.autoSizeTextType = autoSizeTextType;
        }
    }

    @Attr(RS.attr.autoSizeMinTextSize)
    default void textViewAppTextAutoSizeMinTextSize(TextView textView, int autoSizeMinTextSize) {
        if (__textViewAutoSizeLocalVar.autoSizeMinTextSize == -1) {
            __textViewAutoSizeLocalVar.autoSizeMinTextSize = autoSizeMinTextSize;
        }
    }

    @Attr(RS.attr.autoSizeMaxTextSize)
    default void textViewAppTextAutoSizeMaxTextSize(TextView textView, int autoSizeMaxTextSize) {
        if (__textViewAutoSizeLocalVar.autoSizeMaxTextSize == -1) {
            __textViewAutoSizeLocalVar.autoSizeMaxTextSize = autoSizeMaxTextSize;
        }
    }

    @Attr(RS.attr.autoSizeStepGranularity)
    default void textViewAppTextAutoSizeStepGranularity(TextView textView, float autoSizeStepGranularity) {
        if (__textViewAutoSizeLocalVar.autoSizeStepGranularity == 1) {
            __textViewAutoSizeLocalVar.autoSizeStepGranularity = (int) autoSizeStepGranularity;
        }
    }

    @Attr(RS.attr.autoSizePresetSizes)
    default void textViewAppTextAutoSizePresetSizes(TextView textView, int autoSizePresetSizes) {
        if (__textViewAutoSizeLocalVar.autoSizePresetSizes == -1) {
            android.support.v4.widget.TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(textView, textView.getContext().getResources().getIntArray(autoSizePresetSizes), android.util.TypedValue.COMPLEX_UNIT_PX);
        }
    }

    @OnEnd({AndroidRS.attr.autoSizeTextType, AndroidRS.attr.autoSizeMinTextSize
            , AndroidRS.attr.autoSizeMaxTextSize, AndroidRS.attr.autoSizeStepGranularity,

            RS.attr.autoSizeTextType, RS.attr.autoSizeMinTextSize
            , RS.attr.autoSizeMaxTextSize, RS.attr.autoSizeStepGranularity})
    default void onTextViewAutoSizeEnd(TextView textView) {
        if (__textViewAutoSizeLocalVar.autoSizeTextType == 1) {
            if (__textViewAutoSizeLocalVar.autoSizeMaxTextSize == -1) {
                __textViewAutoSizeLocalVar.autoSizeMaxTextSize = (int) ((___scaledDensity * 112) + 0.5f);
            }
            if (__textViewAutoSizeLocalVar.autoSizeMinTextSize == -1) {
                __textViewAutoSizeLocalVar.autoSizeMinTextSize = (int) ((___scaledDensity * 12) + 0.5f);
            }
            android.support.v4.widget.TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(textView, __textViewAutoSizeLocalVar.autoSizeMinTextSize, __textViewAutoSizeLocalVar.autoSizeMaxTextSize, __textViewAutoSizeLocalVar.autoSizeStepGranularity, android.util.TypedValue.COMPLEX_UNIT_PX);
        }
    }
    
}
