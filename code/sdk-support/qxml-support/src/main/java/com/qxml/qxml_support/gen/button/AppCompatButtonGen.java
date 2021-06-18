package com.qxml.qxml_support.gen.button;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatButton;
import android.widget.Button;
import android.widget.TextView;

import com.qxml.AndroidRS;
import com.qxml.gen.button.ButtonGen;
import com.qxml.qxml_support.RS;
import com.qxml.qxml_support.gen.textView.AppCompatTextViewGen;
import com.qxml.qxml_support.gen.textView.attr.TextViewAutoSizeAttr;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = AppCompatButton.class, compatOf = Button.class,
        compatCondition = {
                AndroidRS.attr.autoSizeTextType, AndroidRS.attr.autoSizeMinTextSize, AndroidRS.attr.autoSizeMaxTextSize
                , AndroidRS.attr.autoSizeStepGranularity, AndroidRS.attr.autoSizePresetSizes

                , RS.attr.autoSizeTextType, RS.attr.autoSizeMinTextSize, RS.attr.autoSizeMaxTextSize
                , RS.attr.autoSizeStepGranularity, RS.attr.autoSizePresetSizes

                , RS.attr.fontFamily, AndroidRS.attr.fontFamily
        })
public class AppCompatButtonGen extends ButtonGen implements TextViewAutoSizeAttr {

    @LocalVar
    public AppCompatTextViewGen.$$CompatTextViewLocalVariable __compatButtonLocalVar = new AppCompatTextViewGen.$$CompatTextViewLocalVariable();

    @Attr(RS.attr.autoSizeStepGranularity)
    public void compatTextViewAppTextAutoSizeStepGranularity(AppCompatButton textView, float autoSizeStepGranularity) {
        if (__textViewAutoSizeLocalVar.autoSizeStepGranularity == 1) {
            __textViewAutoSizeLocalVar.autoSizeStepGranularity = (int) autoSizeStepGranularity;
        }
    }

    @Attr(RS.attr.fontFamily)
    public void compatTextViewFontFamily(AppCompatButton textView, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ATTR) {
            __compatButtonLocalVar.appFontFamilyId = valueInfo.resourceId;
        }
        if (__compatButtonLocalVar.appFontFamilyId == 0) {
            __compatButtonLocalVar.appFontFamily = valueInfo.stringValue;
        }
    }

    @Override
    public void textViewFontFamily(TextView textView, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ATTR) {
            __compatButtonLocalVar.androidFontFamilyId = valueInfo.resourceId;
        }
        if (__compatButtonLocalVar.androidFontFamilyId == 0) {
            __textViewLocalVar.fontFamily = valueInfo.stringValue;
        }
    }

    @SuppressLint("RestrictedApi")
    @Attr(AndroidRS.attr.autoSizePresetSizes)
    public void compatTextViewTextAutoSizePresetSizes(AppCompatButton textView, int autoSizePresetSizes) {
        __textViewAutoSizeLocalVar.autoSizePresetSizes = autoSizePresetSizes;
        textView.setAutoSizeTextTypeUniformWithPresetSizes(textView.getContext().getResources().getIntArray(autoSizePresetSizes), android.util.TypedValue.COMPLEX_UNIT_PX);
    }

    @SuppressLint("RestrictedApi")
    @Attr(RS.attr.autoSizePresetSizes)
    public void compatTextViewAppTextAutoSizePresetSizes(AppCompatButton textView, int autoSizePresetSizes) {
        if (__textViewAutoSizeLocalVar.autoSizePresetSizes == -1) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(textView.getContext().getResources().getIntArray(autoSizePresetSizes), android.util.TypedValue.COMPLEX_UNIT_PX);
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onTextViewAutoSizeEnd(TextView textView) {
        if (__textViewAutoSizeLocalVar.autoSizeTextType == 1) {
            android.support.v7.widget.AppCompatButton compatTextView = (android.support.v7.widget.AppCompatButton) textView;
            if (__textViewAutoSizeLocalVar.autoSizeMaxTextSize == -1) {
                __textViewAutoSizeLocalVar.autoSizeMaxTextSize = (int) ((___scaledDensity * 112) + 0.5f);
            }
            if (__textViewAutoSizeLocalVar.autoSizeMinTextSize == -1) {
                __textViewAutoSizeLocalVar.autoSizeMinTextSize = (int) ((___scaledDensity * 12) + 0.5f);
            }
            compatTextView.setAutoSizeTextTypeUniformWithConfiguration(__textViewAutoSizeLocalVar.autoSizeMinTextSize, __textViewAutoSizeLocalVar.autoSizeMaxTextSize, __textViewAutoSizeLocalVar.autoSizeStepGranularity, android.util.TypedValue.COMPLEX_UNIT_PX);
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onTextViewTextEnd(TextView textView) {
        if (__textViewLocalVar.maxLength >= 0) {
            android.text.InputFilter[] filters = new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(__textViewLocalVar.maxLength)};
            textView.setFilters(filters);
        }

        int finalFontFamilyId = 0;
        if (__compatButtonLocalVar.appFontFamilyId != 0) {
            finalFontFamilyId = __compatButtonLocalVar.appFontFamilyId;
        } else {
            if (__compatButtonLocalVar.androidFontFamilyId != 0) {
                finalFontFamilyId = __compatButtonLocalVar.androidFontFamilyId;
            }
        }

        String finalFontFamily = null;
        if (__compatButtonLocalVar.appFontFamily != null) {
            finalFontFamily = __compatButtonLocalVar.appFontFamily;
        } else {
            if (__textViewLocalVar.fontFamily != null) {
                finalFontFamily = __textViewLocalVar.fontFamily;
            }
        }

        if (finalFontFamilyId != 0 || finalFontFamily != null) {
            com.qxml.qxml_support.gen.textView.TextViewGenHelper.setCompatFontFamily(__context, ___typedValue, finalFontFamilyId, finalFontFamily, textView, __textViewLocalVar.textStyle, __textViewLocalVar.textFontWeight);
        } else {
            switch (__textViewLocalVar.typefaceIndex) {
                case 1: {
                    __textViewLocalVar.typeface = android.graphics.Typeface.SANS_SERIF;
                    break;
                }
                case 2: {
                    __textViewLocalVar.typeface = android.graphics.Typeface.SERIF;
                    break;
                }
                case 3: {
                    __textViewLocalVar.typeface = android.graphics.Typeface.MONOSPACE;
                    break;
                }
                default: {
                    __textViewLocalVar.typeface = android.graphics.Typeface.DEFAULT;
                }
            }
            if (android.os.Build.VERSION.SDK_INT >= 28 && __textViewLocalVar.textFontWeight >= 0) {
                __textViewLocalVar.textFontWeight = Math.min(/*android.graphics.fonts.FontStyle.FONT_WEIGHT_MAX*/1000, __textViewLocalVar.textFontWeight);
                final boolean italic = (__textViewLocalVar.textStyle & android.graphics.Typeface.ITALIC) != 0;
                textView.setTypeface(android.graphics.Typeface.create(__textViewLocalVar.typeface, __textViewLocalVar.textFontWeight, italic));
            } else {
                textView.setTypeface(__textViewLocalVar.typeface, __textViewLocalVar.textStyle);
            }
        }

        if (__textViewLocalVar.bufferType != -1) {
            android.widget.TextView.BufferType bufferType;
            if (__textViewLocalVar.bufferType == 0) {
                bufferType = android.widget.TextView.BufferType.NORMAL;
            } else if (__textViewLocalVar.bufferType == 1) {
                bufferType = android.widget.TextView.BufferType.SPANNABLE;
            } else if (__textViewLocalVar.bufferType == 2) {
                bufferType = android.widget.TextView.BufferType.EDITABLE;
            } else {
                bufferType = android.widget.TextView.BufferType.EDITABLE;
            }
            textView.setText(__textViewLocalVar.text, bufferType);
        } else {
            if (__textViewLocalVar.text != null) {
                textView.setText(__textViewLocalVar.text);
            }
        }

        if (__textViewLocalVar.textSize != -1.0f) {
            textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, (float) ((int) (__textViewLocalVar.textSize + 0.5f)));
        }
    }
}
