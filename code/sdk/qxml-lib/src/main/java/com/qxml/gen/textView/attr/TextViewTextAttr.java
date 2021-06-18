package com.qxml.gen.textView.attr;

import android.widget.TextView;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.UnSupport;


public interface TextViewTextAttr extends ViewLocalVar {

    class $$TextViewLocalVariable {
        public String text = null;
        public float textSize = -1.0f;
        public int maxLength = -1;
        public int textFontWeight = -1;
        public String fontFamily = null;
        public android.graphics.Typeface typeface = null;
        public int typefaceIndex = -1;
        public int textStyle = 0;
        public int bufferType = -1;
        public float lineSpacingExtra = 0.0f;
        public float lineSpacingMultiplier = 1.0f;
        public boolean password = false;
        public int inputTypeFlag = -1;
        public int autoLinkFlag = -1;
    }

    @LocalVar
    $$TextViewLocalVariable __textViewLocalVar = new $$TextViewLocalVariable();

    @Attr(AndroidRS.attr.lastBaselineToBottomHeight)
    default void textViewLastBaselineToTopHeight(TextView textView, int lastBaselineToBottomHeight) {
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            textView.setLastBaselineToBottomHeight(lastBaselineToBottomHeight);
        }
    }

    @Attr(AndroidRS.attr.firstBaselineToTopHeight)
    default void textViewFirstBaselineToTopHeight(TextView textView, int firstBaselineToTopHeight) {
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(firstBaselineToTopHeight);
        }
    }

    @Attr(AndroidRS.attr.lineHeight)
    default void textViewLineHeight(TextView textView, int lineHeight) {
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            textView.setLineHeight(lineHeight);
        }
    }

    @Attr(AndroidRS.attr.textAllCaps)
    default void textViewTextAllCaps(TextView textView, boolean textAllCaps) {
        textView.setAllCaps(textAllCaps);
    }

    @Attr(AndroidRS.attr.textColorHighlight)
    default void textViewTextColorHighlight(TextView textView, ValueInfo valueInfo) {
        if (valueInfo.isColor()) {
            textView.setHighlightColor(valueInfo.colorValue);
        }
    }

    @Attr(AndroidRS.attr.textColorHint)
    default void textViewTextColorHint(TextView textView, ValueInfo valueInfo) {
        if (valueInfo.isColor()) {
            textView.setHintTextColor(valueInfo.colorValue);
        } else if (valueInfo.isReference()) {
            textView.setHintTextColor(android.os.Build.VERSION.SDK_INT >= 23 ? __context.getColorStateList(valueInfo.resourceId) : ___resources.getColorStateList(valueInfo.resourceId));
        }
    }

    @Attr(AndroidRS.attr.textAppearance)
    default void textViewTextAppearance(TextView textView, int textAppearanceId) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(textAppearanceId);
        } else {
            textView.setTextAppearance(textView.getContext(), textAppearanceId);
        }
    }

    @Attr(AndroidRS.attr.textColorLink)
    default void textViewTextColorLink(TextView textView, ValueInfo valueInfo) {
        if (valueInfo.isColor()) {
            textView.setLinkTextColor(valueInfo.colorValue);
        } else if (valueInfo.isReference()) {
            textView.setLinkTextColor(android.os.Build.VERSION.SDK_INT >= 23 ? __context.getColorStateList(valueInfo.resourceId) : ___resources.getColorStateList(valueInfo.resourceId));
        }
    }

    @Attr(AndroidRS.attr.textFontWeight)
    default void textViewTextFontWeight(TextView textView, int textFontWeight) {
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            __textViewLocalVar.textFontWeight = textFontWeight;
        }
    }

    @Attr(AndroidRS.attr.textScaleX)
    default void textViewTextScaleX(TextView textView, float textScaleX) {
        textView.setTextScaleX(textScaleX);
    }

    @Attr(AndroidRS.attr.fontFamily)
    default void textViewFontFamily(TextView textView, ValueInfo valueInfo) {
        try {
            if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ATTR) {
                if (android.os.Build.VERSION.SDK_INT >= 26) {
                    __textViewLocalVar.typeface = textView.getResources().getFont(valueInfo.resourceId);
                }
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        if (__textViewLocalVar.typeface == null) {
            __textViewLocalVar.fontFamily = valueInfo.stringValue;
        }
    }

    //todo test
    @Attr(AndroidRS.attr.typeface)
    default void textViewTypeface(TextView textView, int typefaceEnum) {
        __textViewLocalVar.typefaceIndex = typefaceEnum;
    }

    @Attr(AndroidRS.attr.textStyle)
    default void textViewTextStyle(TextView textView, int textStyleFlag) {
        __textViewLocalVar.textStyle = textStyleFlag;
    }

    @Attr(AndroidRS.attr.cursorVisible)
    default void textViewCursorVisible(TextView textView, boolean cursorVisible) {
        textView.setCursorVisible(cursorVisible);
    }

    @Attr(AndroidRS.attr.maxLines)
    default void textViewMaxLines(TextView textView, int maxLines) {
        textView.setMaxLines(maxLines);
    }

    @Attr(AndroidRS.attr.maxHeight)
    default void textViewMaxHeight(TextView textView, int maxHeight) {
        textView.setMaxHeight(maxHeight);
    }

    @Attr(AndroidRS.attr.lines)
    default void textViewLines(TextView textView, int lines) {
        textView.setLines(lines);
    }

    @Attr(AndroidRS.attr.height)
    default void textViewHeight(TextView textView, int height) {
        textView.setHeight(height);
    }

    @Attr(AndroidRS.attr.minLines)
    default void textViewMinLines(TextView textView, int minLines) {
        textView.setMinLines(minLines);
    }

    @Attr(AndroidRS.attr.maxEms)
    default void textViewMaxEms(TextView textView, int maxEms) {
        textView.setMaxEms(maxEms);
    }

    @Attr(AndroidRS.attr.maxWidth)
    default void textViewMaxWidth(TextView textView, int maxWidth) {
        textView.setMaxWidth(maxWidth);
    }

    @Attr(AndroidRS.attr.ems)
    default void textViewEms(TextView textView, int ems) {
        textView.setEms(ems);
    }

    @Attr(AndroidRS.attr.width)
    default void textViewWidth(TextView textView, int width) {
        textView.setWidth(width);
    }

    @Attr(AndroidRS.attr.minEms)
    default void textViewMinEms(TextView textView, int minEms) {
        textView.setMinEms(minEms);
    }

    @Attr(AndroidRS.attr.scrollHorizontally)
    default void textViewScrollHorizontally(TextView textView, boolean scrollHorizontally) {
        textView.setHorizontallyScrolling(scrollHorizontally);
    }

    @Attr(AndroidRS.attr.password)
    default void textViewPassword(TextView textView, boolean password) {
        __textViewLocalVar.password = password;
    }

    @Attr(AndroidRS.attr.singleLine)
    default void textViewSingleLine(TextView textView, boolean singleLine) {
        textView.setSingleLine(singleLine);
    }

    @Attr(AndroidRS.attr.selectAllOnFocus)
    default void textViewSelectAllOnFocus(TextView textView, boolean selectAllOnFocus) {
        textView.setSelectAllOnFocus(selectAllOnFocus);
    }

    @Attr(AndroidRS.attr.includeFontPadding)
    default void textViewIncludeFontPadding(TextView textView, boolean includeFontPadding) {
        textView.setIncludeFontPadding(includeFontPadding);
    }

    @Attr(AndroidRS.attr.maxLength)
    default void textViewMaxLength(TextView textView, int maxLength) {
        __textViewLocalVar.maxLength = maxLength;
    }

    @Attr(AndroidRS.attr.shadowColor)
    default void textViewShadowColor(TextView textView, ValueInfo valueInfo) {
        if (valueInfo.isColor() && android.os.Build.VERSION.SDK_INT >= 16) {
            textView.setShadowLayer(
                    textView.getShadowRadius(),
                    textView.getShadowDx(),
                    textView.getShadowDy(),
                    valueInfo.colorValue
            );
        }
    }

    @Attr(AndroidRS.attr.shadowDx)
    default void textViewShadowDx(TextView textView, float shadowDx) {
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            textView.setShadowLayer(
                    textView.getShadowRadius(),
                    shadowDx,
                    textView.getShadowDy(),
                    textView.getShadowColor()
            );
        }
    }

    @Attr(AndroidRS.attr.shadowDy)
    default void textViewShadowDy(TextView textView, float shadowDy) {
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            textView.setShadowLayer(
                    textView.getShadowRadius(),
                    textView.getShadowDx(),
                    shadowDy,
                    textView.getShadowColor()
            );
        }
    }

    @Attr(AndroidRS.attr.shadowRadius)
    default void textViewShadowRadius(TextView textView, float shadowRadius) {
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            textView.setShadowLayer(
                    shadowRadius,
                    textView.getShadowDx(),
                    textView.getShadowDy(),
                    textView.getShadowColor()
            );
        }
    }

    @Attr(AndroidRS.attr.autoLink)
    default void textViewAutoLink(TextView textView, int autoLinkFlag) {
        __textViewLocalVar.autoLinkFlag = autoLinkFlag;
    }

    @Attr(AndroidRS.attr.linksClickable)
    default void textViewLinksClickable(TextView textView, boolean linksClickable) {
        textView.setLinksClickable(linksClickable);
    }

    @UnSupport
    @Attr(AndroidRS.attr.numeric)
    default void textViewNumeric(TextView textView, int numericFlag) {
    }

    @Attr(AndroidRS.attr.digits)
    default void textViewDigits(TextView textView, String digits) {
        textView.setKeyListener(android.text.method.DigitsKeyListener.getInstance(digits));
    }

    @UnSupport
    @Attr(AndroidRS.attr.phoneNumber)
    default void textViewPhoneNumber(TextView textView, boolean phoneNumber) {
    }

    @Attr(AndroidRS.attr.inputMethod)
    default void textViewInputMethod(TextView textView, String inputMethod) {
        try {
            textView.setKeyListener((android.text.method.KeyListener) Class.forName(inputMethod).newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Attr(AndroidRS.attr.capitalize)
    default void textViewCapitalize(TextView textView, int capitalizeEnum) {
        android.text.method.TextKeyListener.Capitalize capitalize;
        switch (capitalizeEnum) {
            case 3: {
                capitalize = android.text.method.TextKeyListener.Capitalize.CHARACTERS;
                break;
            }
            case 2: {
                capitalize = android.text.method.TextKeyListener.Capitalize.WORDS;
                break;
            }
            case 1: {
                capitalize = android.text.method.TextKeyListener.Capitalize.SENTENCES;
                break;
            }
            default: {
                capitalize = android.text.method.TextKeyListener.Capitalize.NONE;
            }
        }
        textView.setKeyListener(android.text.method.TextKeyListener.getInstance(
                (textView.getInputType() & android.view.inputmethod.EditorInfo.TYPE_TEXT_FLAG_AUTO_CORRECT) != 0,
                capitalize
        ));
    }

    @Attr(AndroidRS.attr.autoText)
    default void textViewAutoText(TextView textView, boolean autoText) {
        android.text.method.TextKeyListener.Capitalize capitalize;
        int type = textView.getInputType();
        if ((type & android.view.inputmethod.EditorInfo.TYPE_TEXT_FLAG_CAP_CHARACTERS) != 0) {
            capitalize = android.text.method.TextKeyListener.Capitalize.CHARACTERS;
        } else if ((type & android.view.inputmethod.EditorInfo.TYPE_TEXT_FLAG_CAP_WORDS) != 0) {
            capitalize = android.text.method.TextKeyListener.Capitalize.WORDS;
        } else if ((type & android.view.inputmethod.EditorInfo.TYPE_TEXT_FLAG_CAP_SENTENCES) != 0) {
            capitalize = android.text.method.TextKeyListener.Capitalize.SENTENCES;
        } else {
            capitalize = android.text.method.TextKeyListener.Capitalize.NONE;
        }
        textView.setKeyListener(android.text.method.TextKeyListener.getInstance(autoText, capitalize));
    }

    @UnSupport
    @Attr(AndroidRS.attr.editable)
    default void textViewEditable(TextView textView, boolean editable) {
    }

    @Attr(AndroidRS.attr.freezesText)
    default void textViewFreezesText(TextView textView, boolean freezesText) {
        textView.setFreezesText(freezesText);
    }

    @Attr(AndroidRS.attr.ellipsize)
    default void textViewEllipsize(TextView textView, int ellipsizeEnum) {
        if (ellipsizeEnum == 1) {
            textView.setEllipsize(android.text.TextUtils.TruncateAt.START);
        } else if (ellipsizeEnum == 2) {
            textView.setEllipsize(android.text.TextUtils.TruncateAt.MIDDLE);
        } else if (ellipsizeEnum == 3) {
            textView.setEllipsize(android.text.TextUtils.TruncateAt.END);
        } else if (ellipsizeEnum == 4) {
            textView.setEllipsize(android.text.TextUtils.TruncateAt.MARQUEE);
        }
    }

    @Attr(AndroidRS.attr.lineSpacingExtra)
    default void textViewLineSpacingExtra(TextView textView, float lineSpacingExtra) {
        __textViewLocalVar.lineSpacingExtra = lineSpacingExtra;
    }

    @Attr(AndroidRS.attr.lineSpacingMultiplier)
    default void textViewLineSpacingMultiplier(TextView textView, float lineSpacingMultiplier) {
        __textViewLocalVar.lineSpacingMultiplier = lineSpacingMultiplier;
    }

    @Attr(AndroidRS.attr.justificationMode)
    default void textViewJustificationMode(TextView textView, int justificationModeEnum) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            textView.setJustificationMode(justificationModeEnum);
        }
    }

    @Attr(AndroidRS.attr.marqueeRepeatLimit)
    default void textViewMarqueeRepeatLimit(TextView textView, int marqueeRepeatLimit) {
        textView.setMarqueeRepeatLimit(marqueeRepeatLimit);
    }

    @Attr(AndroidRS.attr.inputType)
    default void textViewInputType(TextView textView, int inputTypeFlag) {
        __textViewLocalVar.inputTypeFlag = inputTypeFlag;
    }

    @Attr(AndroidRS.attr.imeOptions)
    default void textViewImeOptions(TextView textView, int imeOptions) {
        textView.setImeOptions(imeOptions);
    }

    @Attr(AndroidRS.attr.privateImeOptions)
    default void textViewPrivateImeOptions(TextView textView, String privateImeOptions) {
        textView.setPrivateImeOptions(privateImeOptions);
    }

    @Attr(AndroidRS.attr.imeActionLabel)
    default void textViewImeActionLabel(TextView textView, String imeActionLabel) {
        textView.setImeActionLabel(imeActionLabel, textView.getImeActionId());
    }

    @Attr(AndroidRS.attr.imeActionId)
    default void textViewImeActionId(TextView textView, int imeActionId) {
        textView.setImeActionLabel(textView.getImeActionLabel(), imeActionId);
    }

    @Attr(AndroidRS.attr.editorExtras)
    default void textViewEditorExtras(TextView textView, int editorExtras) {
        try {
            textView.setInputExtras(editorExtras);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (org.xmlpull.v1.XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    @Attr(AndroidRS.attr.elegantTextHeight)
    default void textViewElegantTextHeight(TextView textView, boolean elegantTextHeight) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            textView.setElegantTextHeight(elegantTextHeight);
        }
    }

    @Attr(AndroidRS.attr.fallbackLineSpacing)
    default void textViewFallbackLineSpacing(TextView textView, boolean fallbackLineSpacing) {
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            textView.setFallbackLineSpacing(fallbackLineSpacing);
        }
    }

    @Attr(AndroidRS.attr.letterSpacing)
    default void textViewLetterSpacing(TextView textView, float letterSpacing) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            textView.setLetterSpacing(letterSpacing);
        }
    }

    @Attr(AndroidRS.attr.fontFeatureSettings)
    default void textViewFontFeatureSettings(TextView textView, String fontFeatureSettings) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            textView.setFontFeatureSettings(fontFeatureSettings);
        }
    }

    @Attr(AndroidRS.attr.fontVariationSettings)
    default void textViewFontVariationSettings(TextView textView, String fontVariationSettings) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            textView.setFontVariationSettings(fontVariationSettings);
        }
    }

    @Attr(AndroidRS.attr.breakStrategy)
    default void textViewBreakStrategy(TextView textView, int breakStrategy) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            textView.setBreakStrategy(breakStrategy);
        }
    }

    @Attr(AndroidRS.attr.hyphenationFrequency)
    default void textViewHyphenationFrequency(TextView textView, int hyphenationFrequency) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            textView.setHyphenationFrequency(hyphenationFrequency);
        }
    }

    @Attr(AndroidRS.attr.textCursorDrawable)
    default void textViewTextCursorDrawable(TextView textView, int textCursorDrawable) {
        com.qxml.gen.textView.CursorHelper.setCursorDrawable(textView, textCursorDrawable);
    }

    @Attr(AndroidRS.attr.textSelectHandle)
    default void textViewTextSelectHandle(TextView textView, int textSelectHandle) {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            textView.setTextSelectHandle(textSelectHandle);
        }
    }

    @Attr(AndroidRS.attr.textSelectHandleLeft)
    default void textViewTextSelectHandleLeft(TextView textView, int textSelectHandleLeft) {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            textView.setTextSelectHandleLeft(textSelectHandleLeft);
        }
    }

    @Attr(AndroidRS.attr.textSelectHandleRight)
    default void textViewTextSelectHandleRight(TextView textView, int textSelectHandleRight) {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            textView.setTextSelectHandleRight(textSelectHandleRight);
        }
    }

    @UnSupport
    @Attr(AndroidRS.attr.allowUndo)
    default void textViewAllowUndo(TextView textView, boolean allowUndo) {

    }

    @Attr(AndroidRS.attr.text)
    default void textViewText(TextView textView, String text) {
        __textViewLocalVar.text = text;
    }

    @Attr(AndroidRS.attr.textSize)
    default void textViewTextSize(TextView textView, float textSize) {
        __textViewLocalVar.textSize = textSize;
    }

    @Attr(AndroidRS.attr.bufferType)
    default void textViewBufferType(TextView textView, int bufferTypeEnum) {
        __textViewLocalVar.bufferType = bufferTypeEnum;
    }

    @Attr(AndroidRS.attr.hint)
    default void textViewHint(TextView textView, String hint) {
        textView.setHint(hint);
    }

    @Attr(AndroidRS.attr.textColor)
    default void textViewTextColor(TextView textView, ValueInfo valueInfo) {
        if (valueInfo.isColor()) {
            textView.setTextColor(valueInfo.colorValue);
        } else {
            textView.setTextColor(android.os.Build.VERSION.SDK_INT >= 23 ? __context.getColorStateList(valueInfo.resourceId) : ___resources.getColorStateList(valueInfo.resourceId));
        }
    }

    @OnEnd({AndroidRS.attr.maxLength, AndroidRS.attr.text, AndroidRS.attr.textStyle
            , AndroidRS.attr.textFontWeight, AndroidRS.attr.typeface
            , AndroidRS.attr.fontFamily, AndroidRS.attr.bufferType
            , AndroidRS.attr.password, AndroidRS.attr.inputType
            , AndroidRS.attr.textSize, AndroidRS.attr.autoLink })
    default void onTextViewTextEnd(TextView textView) {
        if (__textViewLocalVar.maxLength >= 0) {
            android.text.InputFilter[] filters = new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(__textViewLocalVar.maxLength)};
            textView.setFilters(filters);
        }
        boolean isPassword = __textViewLocalVar.password;
        if (__textViewLocalVar.password) {
            textView.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
        }
        if (__textViewLocalVar.inputTypeFlag != -1) {
            textView.setInputType(__textViewLocalVar.inputTypeFlag);
            final int variation =
                    __textViewLocalVar.inputTypeFlag & (android.view.inputmethod.EditorInfo.TYPE_MASK_CLASS | android.view.inputmethod.EditorInfo.TYPE_MASK_VARIATION);
            final boolean passwordInputType = variation
                    == (android.view.inputmethod.EditorInfo.TYPE_CLASS_TEXT | android.view.inputmethod.EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
            final boolean webPasswordInputType = variation
                    == (android.view.inputmethod.EditorInfo.TYPE_CLASS_TEXT | android.view.inputmethod.EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD);
            final boolean numberPasswordInputType = variation
                    == (android.view.inputmethod.EditorInfo.TYPE_CLASS_NUMBER | android.view.inputmethod.EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
            isPassword = isPassword || passwordInputType || webPasswordInputType || numberPasswordInputType;
        }

        if (__textViewLocalVar.typeface != null || __textViewLocalVar.fontFamily != null || __textViewLocalVar.typefaceIndex != -1) {
            if (__textViewLocalVar.typeface == null && __textViewLocalVar.fontFamily != null) {
                __textViewLocalVar.typeface = android.graphics.Typeface.create(__textViewLocalVar.fontFamily, android.graphics.Typeface.NORMAL);
            } else if (__textViewLocalVar.typeface != null) {

            } else {
                switch (__textViewLocalVar.typefaceIndex) {
                    case 1: { __textViewLocalVar.typeface = android.graphics.Typeface.SANS_SERIF; break; }
                    case 2: { __textViewLocalVar.typeface = android.graphics.Typeface.SERIF; break; }
                    case 3: { __textViewLocalVar.typeface = android.graphics.Typeface.MONOSPACE; break; }
                    default: { __textViewLocalVar.typeface = /*android.graphics.Typeface.DEFAULT*/null; }
                }
            }

            if (android.os.Build.VERSION.SDK_INT >= 28 && __textViewLocalVar.textFontWeight >= 0) {
                __textViewLocalVar.textFontWeight = java.lang.Math.min(/*android.graphics.fonts.FontStyle.FONT_WEIGHT_MAX*/1000, __textViewLocalVar.textFontWeight);
                final boolean italic = (__textViewLocalVar.textStyle & android.graphics.Typeface.ITALIC) != 0;
                textView.setTypeface(android.graphics.Typeface.create(__textViewLocalVar.typeface, __textViewLocalVar.textFontWeight, italic));
            } else {
                textView.setTypeface(__textViewLocalVar.typeface, __textViewLocalVar.textStyle);
            }
        } else {
            if (isPassword || __textViewLocalVar.textStyle != 0) {
                textView.setTypeface(null, __textViewLocalVar.textStyle);
            }
        }

        if (__textViewLocalVar.textSize != -1.0f) {
            textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, (float)((int) (__textViewLocalVar.textSize + 0.5f)));
        }

        if (__textViewLocalVar.autoLinkFlag != -1) {
            textView.setAutoLinkMask(__textViewLocalVar.autoLinkFlag);
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

        /*if (__textViewLocalVar.textSize != -1.0f) {
            textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, (float)((int) (__textViewLocalVar.textSize + 0.5f)));
        }*/
    }

    @OnEnd({AndroidRS.attr.lineSpacingExtra, AndroidRS.attr.lineSpacingMultiplier})
    default void onTextViewLineSpaceEnd(TextView textView) {
        /*if (android.os.Build.VERSION.SDK_INT >= 16) {
            textView.setLineSpacing(__textViewLocalVar.lineSpacingExtra, __textViewLocalVar.lineSpacingMultiplier);
        }*/
        textView.setLineSpacing(__textViewLocalVar.lineSpacingExtra, __textViewLocalVar.lineSpacingMultiplier);
    }

}
