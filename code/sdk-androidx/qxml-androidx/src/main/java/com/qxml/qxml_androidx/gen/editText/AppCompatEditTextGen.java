package com.qxml.qxml_androidx.gen.editText;

import android.annotation.SuppressLint;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.qxml.AndroidRS;
import com.qxml.qxml_androidx.RS;
import com.qxml.qxml_androidx.gen.textview.AppCompatTextViewGen;
import com.qxml.qxml_androidx.gen.textview.attr.TextViewAutoSizeAttr;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = AppCompatEditText.class, compatOf = android.widget.EditText.class,
        compatCondition = {
                RS.attr.fontFamily, AndroidRS.attr.fontFamily
        })
public class AppCompatEditTextGen extends EditTextCompatGen implements TextViewAutoSizeAttr {

    @LocalVar
    public AppCompatTextViewGen.$$CompatTextViewLocalVariable __compatEditTextLocalVar = new AppCompatTextViewGen.$$CompatTextViewLocalVariable();
    
    @Override
    public void textViewFontFamily(TextView textView, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ATTR) {
            __compatEditTextLocalVar.finalFontFamilyId = valueInfo.resourceId;
        }
        if (__compatEditTextLocalVar.finalFontFamilyId == 0) {
            __textViewLocalVar.fontFamily = valueInfo.stringValue;
        }
    }

    @Attr(RS.attr.fontFamily)
    public void compatTextViewFontFamily(AppCompatEditText textView, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ATTR) {
            __compatEditTextLocalVar.finalFontFamilyId = valueInfo.resourceId;
        }
        if (__compatEditTextLocalVar.finalFontFamilyId == 0) {
            __textViewLocalVar.fontFamily = valueInfo.stringValue;
        }
    }

    @Override
    public void textViewTypeface(TextView textView, int typefaceEnum) {
        if (__compatEditTextLocalVar.finalFontFamilyId == 0 && __textViewLocalVar.fontFamily == null) {
            switch (typefaceEnum) {
                case 1: { __textViewLocalVar.typeface = android.graphics.Typeface.SANS_SERIF; break; }
                case 2: { __textViewLocalVar.typeface = android.graphics.Typeface.SERIF; break; }
                case 3: { __textViewLocalVar.typeface = android.graphics.Typeface.MONOSPACE; break; }
                default: { __textViewLocalVar.typeface = android.graphics.Typeface.DEFAULT; }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onTextViewAutoSizeEnd(TextView textView) {
        if (__textViewAutoSizeLocalVar.autoSizeTextType == 1) {
            androidx.appcompat.widget.AppCompatTextView compatTextView = (androidx.appcompat.widget.AppCompatTextView) textView;
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
    public void onTextViewFontFamily(AppCompatEditText textView) {
        if (__compatEditTextLocalVar.finalFontFamilyId != 0 || __textViewLocalVar.fontFamily != null) {
            com.qxml.qxml_androidx.gen.textview.TextViewGenHelper.setCompatFontFamily(__context, ___typedValue, __compatEditTextLocalVar.finalFontFamilyId, __textViewLocalVar.fontFamily, textView, __textViewLocalVar.textStyle, __textViewLocalVar.textFontWeight);
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
