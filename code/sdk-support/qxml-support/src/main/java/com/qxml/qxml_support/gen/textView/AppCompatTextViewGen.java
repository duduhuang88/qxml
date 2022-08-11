package com.qxml.qxml_support.gen.textView;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.qxml.AndroidRS;
import com.qxml.qxml_support.RS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = AppCompatTextView.class, compatOf = TextView.class,
        compatCondition = {
                AndroidRS.attr.autoSizeTextType, AndroidRS.attr.autoSizeMinTextSize, AndroidRS.attr.autoSizeMaxTextSize
                , AndroidRS.attr.autoSizeStepGranularity, AndroidRS.attr.autoSizePresetSizes

                , RS.attr.autoSizeTextType, RS.attr.autoSizeMinTextSize, RS.attr.autoSizeMaxTextSize
                , RS.attr.autoSizeStepGranularity, RS.attr.autoSizePresetSizes

                , RS.attr.lineHeight, AndroidRS.attr.lineHeight
                , RS.attr.firstBaselineToTopHeight, AndroidRS.attr.firstBaselineToTopHeight
                , RS.attr.lastBaselineToBottomHeight, AndroidRS.attr.lastBaselineToBottomHeight

                , RS.attr.fontFamily, AndroidRS.attr.fontFamily}
)
public class AppCompatTextViewGen extends TextViewCompatGen {

    public static class $$CompatTextViewLocalVariable {
        public int finalFontFamilyId = 0;
    }

    @LocalVar
    public $$CompatTextViewLocalVariable __compatTextViewLocalVar = new $$CompatTextViewLocalVariable();

    @Attr(RS.attr.lineHeight)
    public void compatTextViewAppLineHeight(AppCompatTextView textView, int lineHeight) {
        textView.setLineHeight(lineHeight);
    }

    @Attr(AndroidRS.attr.lineHeight)
    public void compatTextViewLineHeight(AppCompatTextView textView, int lineHeight) {
        textView.setLineHeight(lineHeight);
    }

    @Attr(RS.attr.firstBaselineToTopHeight)
    public void compatTextViewAppFirstBaselineToTopHeight(AppCompatTextView textView, int firstBaselineToTopHeight) {
        textView.setFirstBaselineToTopHeight(firstBaselineToTopHeight);
    }

    @Attr(AndroidRS.attr.firstBaselineToTopHeight)
    public void compatTextViewFirstBaselineToTopHeight(AppCompatTextView textView, int firstBaselineToTopHeight) {
        textView.setFirstBaselineToTopHeight(firstBaselineToTopHeight);
    }

    @Attr(RS.attr.lastBaselineToBottomHeight)
    public void compatTextViewAppLastBaselineToTopHeight(AppCompatTextView textView, int lastBaselineToBottomHeight) {
        textView.setLastBaselineToBottomHeight(lastBaselineToBottomHeight);
    }

    @Attr(AndroidRS.attr.lastBaselineToBottomHeight)
    public void compatTextViewLastBaselineToTopHeight(AppCompatTextView textView, int lastBaselineToBottomHeight) {
        textView.setLastBaselineToBottomHeight(lastBaselineToBottomHeight);
    }

    @Attr(RS.attr.autoSizeStepGranularity)
    public void compatTextViewAppTextAutoSizeStepGranularity(AppCompatTextView textView, float autoSizeStepGranularity) {
        if (__textViewAutoSizeLocalVar.autoSizeStepGranularity == 1) {
            __textViewAutoSizeLocalVar.autoSizeStepGranularity = (int) autoSizeStepGranularity;
        }
    }

    @Override
    public void textViewFontFamily(TextView textView, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ATTR) {
            __compatTextViewLocalVar.finalFontFamilyId = valueInfo.resourceId;
        }
        if (__compatTextViewLocalVar.finalFontFamilyId == 0) {
            __textViewLocalVar.fontFamily = valueInfo.stringValue;
        }
    }

    @Attr(RS.attr.fontFamily)
    public void compatTextViewFontFamily(AppCompatTextView textView, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ATTR) {
            __compatTextViewLocalVar.finalFontFamilyId = valueInfo.resourceId;
        }
        if (__compatTextViewLocalVar.finalFontFamilyId == 0) {
            __textViewLocalVar.fontFamily = valueInfo.stringValue;
        }
    }

    @Override
    public void textViewTypeface(TextView textView, int typefaceEnum) {
        if (__compatTextViewLocalVar.finalFontFamilyId == 0 && __textViewLocalVar.fontFamily == null) {
            switch (typefaceEnum) {
                case 1: { __textViewLocalVar.typeface = android.graphics.Typeface.SANS_SERIF; break; }
                case 2: { __textViewLocalVar.typeface = android.graphics.Typeface.SERIF; break; }
                case 3: { __textViewLocalVar.typeface = android.graphics.Typeface.MONOSPACE; break; }
                default: { __textViewLocalVar.typeface = android.graphics.Typeface.DEFAULT; }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Attr(AndroidRS.attr.autoSizePresetSizes)
    public void compatTextViewTextAutoSizePresetSizes(AppCompatTextView textView, int autoSizePresetSizes) {
        __textViewAutoSizeLocalVar.autoSizePresetSizes = autoSizePresetSizes;
        textView.setAutoSizeTextTypeUniformWithPresetSizes(textView.getContext().getResources().getIntArray(autoSizePresetSizes), android.util.TypedValue.COMPLEX_UNIT_PX);
    }

    @SuppressLint("RestrictedApi")
    @Attr(RS.attr.autoSizePresetSizes)
    public void compatTextViewAppTextAutoSizePresetSizes(AppCompatTextView textView, int autoSizePresetSizes) {
        if (__textViewAutoSizeLocalVar.autoSizePresetSizes == -1) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(textView.getContext().getResources().getIntArray(autoSizePresetSizes), android.util.TypedValue.COMPLEX_UNIT_PX);
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onTextViewAutoSizeEnd(TextView textView) {
        if (__textViewAutoSizeLocalVar.autoSizeTextType == 1) {
            android.support.v7.widget.AppCompatTextView compatTextView = (android.support.v7.widget.AppCompatTextView) textView;
            if (__textViewAutoSizeLocalVar.autoSizeMaxTextSize == -1) {
                __textViewAutoSizeLocalVar.autoSizeMaxTextSize = (int) ((___scaledDensity * 112) + 0.5f);
            }
            if (__textViewAutoSizeLocalVar.autoSizeMinTextSize == -1) {
                __textViewAutoSizeLocalVar.autoSizeMinTextSize = (int) ((___scaledDensity * 12) + 0.5f);
            }
            compatTextView.setAutoSizeTextTypeUniformWithConfiguration(__textViewAutoSizeLocalVar.autoSizeMinTextSize, __textViewAutoSizeLocalVar.autoSizeMaxTextSize, __textViewAutoSizeLocalVar.autoSizeStepGranularity, android.util.TypedValue.COMPLEX_UNIT_PX);
        }
    }

    @Override
    public void onTextViewMaxLengthEnd(TextView textView) {
        if (__textViewLocalVar.maxLength >= 0) {
            android.text.InputFilter[] filters = new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(__textViewLocalVar.maxLength)};
            textView.setFilters(filters);
        }
    }

    @OnEnd({RS.attr.fontFamily, AndroidRS.attr.fontFamily})
    public void onTextViewFontFamily(TextView textView) {
        if (__compatTextViewLocalVar.finalFontFamilyId != 0 || __textViewLocalVar.fontFamily != null) {
            com.qxml.qxml_support.gen.textView.TextViewGenHelper.setCompatFontFamily(__context, ___typedValue, __compatTextViewLocalVar.finalFontFamilyId, __textViewLocalVar.fontFamily, textView, __textViewLocalVar.textStyle, __textViewLocalVar.textFontWeight);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= 28 && __textViewLocalVar.textFontWeight >= 0) {
                __textViewLocalVar.textFontWeight = Math.min(/*android.graphics.fonts.FontStyle.FONT_WEIGHT_MAX*/1000, __textViewLocalVar.textFontWeight);
                final boolean italic = (__textViewLocalVar.textStyle & android.graphics.Typeface.ITALIC) != 0;
                textView.setTypeface(android.graphics.Typeface.create(__textViewLocalVar.typeface, __textViewLocalVar.textFontWeight, italic));
            } else {
                textView.setTypeface(__textViewLocalVar.typeface, __textViewLocalVar.textStyle);
            }
        }
    }

    @Override
    public void onTextViewPassWordEnd(TextView textView) {
        if (__textViewLocalVar.password) {
            textView.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
        }
    }

    @Override
    public void onTextViewInputTypeFlagEnd(TextView textView) {
        textView.setInputType(__textViewLocalVar.inputTypeFlag);
        final int variation =
                __textViewLocalVar.inputTypeFlag & (android.view.inputmethod.EditorInfo.TYPE_MASK_CLASS | android.view.inputmethod.EditorInfo.TYPE_MASK_VARIATION);
        final boolean passwordInputType = variation
                == (android.view.inputmethod.EditorInfo.TYPE_CLASS_TEXT | android.view.inputmethod.EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        final boolean webPasswordInputType = variation
                == (android.view.inputmethod.EditorInfo.TYPE_CLASS_TEXT | android.view.inputmethod.EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD);
        final boolean numberPasswordInputType = variation
                == (android.view.inputmethod.EditorInfo.TYPE_CLASS_NUMBER | android.view.inputmethod.EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
        __textViewLocalVar.password = __textViewLocalVar.password || passwordInputType || webPasswordInputType || numberPasswordInputType;
        if (__textViewLocalVar.typeface == null && __textViewLocalVar.fontFamily == null) {
            if (__textViewLocalVar.password || __textViewLocalVar.textStyle != 0) {
                textView.setTypeface(null, __textViewLocalVar.textStyle);
                __textViewLocalVar.password = false;
            }
        }
    }

    @Override
    public void onTextViewTypeFaceEnd(TextView textView) {
        if (__textViewLocalVar.typeface != null || __textViewLocalVar.fontFamily != null) {
            if (__textViewLocalVar.typeface == null) {
                __textViewLocalVar.typeface = android.graphics.Typeface.create(__textViewLocalVar.fontFamily, android.graphics.Typeface.NORMAL);
            }
            if (android.os.Build.VERSION.SDK_INT >= 28 && __textViewLocalVar.textFontWeight >= 0) {
                __textViewLocalVar.textFontWeight = java.lang.Math.min(/*android.graphics.fonts.FontStyle.FONT_WEIGHT_MAX*/1000, __textViewLocalVar.textFontWeight);
                final boolean italic = (__textViewLocalVar.textStyle & android.graphics.Typeface.ITALIC) != 0;
                textView.setTypeface(android.graphics.Typeface.create(__textViewLocalVar.typeface, __textViewLocalVar.textFontWeight, italic));
            } else {
                textView.setTypeface(__textViewLocalVar.typeface, __textViewLocalVar.textStyle);
            }
        } else {
            if (__textViewLocalVar.password || __textViewLocalVar.textStyle != 0) {
                textView.setTypeface(null, __textViewLocalVar.textStyle);
            }
        }
    }

    @Override
    public void onTextViewTextSizeEnd(TextView textView) {
        textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, (float)((int) (__textViewLocalVar.textSize + 0.5f)));
    }

    @Override
    public void onTextViewAutoLinkEnd(TextView textView) {
        textView.setAutoLinkMask(__textViewLocalVar.autoLinkFlag);
    }

    @Override
    public void onTextViewTextEnd(TextView textView) {
        textView.setText(__textViewLocalVar.text, __textViewLocalVar.bufferType);
    }
}

