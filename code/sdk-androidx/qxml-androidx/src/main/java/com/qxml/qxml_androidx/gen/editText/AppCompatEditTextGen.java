package com.qxml.qxml_androidx.gen.editText;

import android.annotation.SuppressLint;

import androidx.appcompat.widget.AppCompatEditText;

import com.qxml.AndroidRS;
import com.qxml.qxml_androidx.RS;
import com.qxml.qxml_androidx.gen.textview.AppCompatTextViewGen;
import com.qxml.qxml_androidx.gen.textview.attr.TextViewAutoSizeAttr;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = AppCompatEditText.class, compatOf = android.widget.EditText.class,
        compatCondition = {
                RS.attr.fontFamily, AndroidRS.attr.fontFamily
        })
public class AppCompatEditTextGen extends EditTextCompatGen implements TextViewAutoSizeAttr {

    @LocalVar
    public AppCompatTextViewGen.$$CompatTextViewLocalVariable __compatEditTextLocalVar = new AppCompatTextViewGen.$$CompatTextViewLocalVariable();

    @Attr(RS.attr.fontFamily)
    public void compatTextViewFontFamily(AppCompatEditText textView, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ATTR) {
            __compatEditTextLocalVar.appFontFamilyId = valueInfo.resourceId;
        }
        if (__compatEditTextLocalVar.appFontFamilyId == 0) {
            __compatEditTextLocalVar.appFontFamily = valueInfo.stringValue;
        }
    }

    @Override
    public void textViewFontFamily(android.widget.TextView textView, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ATTR) {
            __compatEditTextLocalVar.androidFontFamilyId = valueInfo.resourceId;
        }
        if (__compatEditTextLocalVar.androidFontFamilyId == 0) {
            __textViewLocalVar.fontFamily = valueInfo.stringValue;
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onTextViewTextEnd(android.widget.TextView textView) {
        if (__textViewLocalVar.maxLength >= 0) {
            android.text.InputFilter[] filters = new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(__textViewLocalVar.maxLength)};
            textView.setFilters(filters);
        }

        int finalFontFamilyId = 0;
        if (__compatEditTextLocalVar.appFontFamilyId != 0) {
            finalFontFamilyId = __compatEditTextLocalVar.appFontFamilyId;
        } else {
            if (__compatEditTextLocalVar.androidFontFamilyId != 0) {
                finalFontFamilyId = __compatEditTextLocalVar.androidFontFamilyId;
            }
        }

        String finalFontFamily = null;
        if (__compatEditTextLocalVar.appFontFamily != null) {
            finalFontFamily = __compatEditTextLocalVar.appFontFamily;
        } else {
            if (__textViewLocalVar.fontFamily != null) {
                finalFontFamily = __textViewLocalVar.fontFamily;
            }
        }

        if (finalFontFamilyId != 0 || finalFontFamily != null) {
            com.qxml.qxml_androidx.gen.textview.TextViewGenHelper.setCompatFontFamily(__context, ___typedValue, finalFontFamilyId, finalFontFamily, textView, __textViewLocalVar.textStyle, __textViewLocalVar.textFontWeight);
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
