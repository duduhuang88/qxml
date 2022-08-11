package com.qxml.qxml_support.gen.textInputLayout;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputLayout;

import com.qxml.gen.linearLayout.LinearLayoutGen;
import com.qxml.qxml_support.AndroidRS;
import com.qxml.qxml_support.RS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(TextInputLayout.class)
public class TextInputLayoutGen extends LinearLayoutGen {

    static class $$TextInputLayoutLocalVariable {
        public int counterTextAppearance = 0;
        public int counterOverflowTextAppearance = 0;
        public float boxCornerRadiusTopStart = 0f;
        public float boxCornerRadiusTopEnd = 0f;
        public float boxCornerRadiusBottomEnd = 0f;
        public float boxCornerRadiusBottomStart = 0f;

        public boolean helperTextEnabled = false;
        public String helperText = "";
        public int helperTextTextAppearance = 0;
        public boolean errorEnabled = false;
        public int errorTextAppearance = 0;
        public boolean counterEnabled = false;

        public android.content.res.ColorStateList textColorHint = null;
        public int hintTextAppearance = -1;


        public String passwordToggleContentDescription = null;
        public int passwordToggleDrawable = -1;
        public boolean passwordToggleEnabled = false;
        public android.content.res.ColorStateList passwordToggleTint = null;
        public int passwordToggleTintMode = -1;
    }

    @LocalVar
    $$TextInputLayoutLocalVariable __textInputLayoutLocalVar = null;

    @Attr(RS.attr.hintEnabled)
    public void textInputLayoutHintEnabled(TextInputLayout textInputLayout, boolean hintEnabled) {
        textInputLayout.setHintEnabled(hintEnabled);
    }

    @Attr(AndroidRS.attr.hint)
    public void textInputLayoutHint(TextInputLayout textInputLayout, String hint) {
        textInputLayout.setHint(hint);
    }

    @Attr(RS.attr.hintAnimationEnabled)
    public void textInputLayoutHintAnimationEnabled(TextInputLayout textInputLayout, boolean hintAnimationEnabled) {
        textInputLayout.setHintAnimationEnabled(hintAnimationEnabled);
    }

    @Attr(RS.attr.boxCollapsedPaddingTop)
    public void textInputLayoutBoxCollapsedPaddingTop(TextInputLayout textInputLayout, int boxCollapsedPaddingTop) {
        com.qxml.qxml_support.gen.textInputLayout.TextInputLayoutHelper.setBoxCollapsedPaddingTop(textInputLayout, boxCollapsedPaddingTop);
    }

    @Attr(RS.attr.boxCornerRadiusTopStart)
    public void textInputLayoutBoxCollapsedPaddingTop(TextInputLayout textInputLayout, float boxCornerRadiusTopStart) {
        __textInputLayoutLocalVar.boxCornerRadiusTopStart = boxCornerRadiusTopStart;
    }

    @Attr(RS.attr.boxCornerRadiusTopEnd)
    public void textInputLayoutBoxCollapsedPaddingEnd(TextInputLayout textInputLayout, float boxCornerRadiusTopEnd) {
        __textInputLayoutLocalVar.boxCornerRadiusTopEnd = boxCornerRadiusTopEnd;
    }

    @Attr(RS.attr.boxCornerRadiusBottomEnd)
    public void textInputLayoutBoxCornerRadiusBottomEnd(TextInputLayout textInputLayout, float boxCornerRadiusBottomEnd) {
        __textInputLayoutLocalVar.boxCornerRadiusBottomEnd = boxCornerRadiusBottomEnd;
    }

    @Attr(RS.attr.boxCornerRadiusBottomStart)
    public void textInputLayoutBoxCornerRadiusBottomStart(TextInputLayout textInputLayout, float boxCornerRadiusBottomStart) {
        __textInputLayoutLocalVar.boxCornerRadiusBottomStart = boxCornerRadiusBottomStart;
    }

    @Attr(RS.attr.boxBackgroundColor)
    public void textInputLayoutBoxBackgroundColor(TextInputLayout textInputLayout, ValueInfo boxBackgroundColor) {
        textInputLayout.setBoxBackgroundColor(boxBackgroundColor.colorValue);
    }

    @Attr(RS.attr.boxStrokeColor)
    public void textInputLayoutBoxStrokeColor(TextInputLayout textInputLayout, ValueInfo boxStrokeColor) {
        textInputLayout.setBoxStrokeColor(boxStrokeColor.colorValue);
    }

    @Attr(RS.attr.boxBackgroundMode)
    public void textInputLayoutBoxBackgroundMode(TextInputLayout textInputLayout, int boxBackgroundMode) {
        textInputLayout.setBoxBackgroundMode(boxBackgroundMode);
    }

    @Attr(AndroidRS.attr.textColorHint)
    public void textInputLayoutTextColorHint(TextInputLayout textInputLayout, ValueInfo textColorHint) {
        __textInputLayoutLocalVar.textColorHint = textColorHint.getColorStateList(__context);
    }

    @Attr(RS.attr.hintTextAppearance)
    public void textInputLayoutHintTextAppearance(TextInputLayout textInputLayout, int hintTextAppearance) {
        __textInputLayoutLocalVar.hintTextAppearance = hintTextAppearance;
    }

    @Attr(RS.attr.errorTextAppearance)
    public void textInputLayoutErrorTextAppearance(TextInputLayout textInputLayout, int errorTextAppearance) {
        __textInputLayoutLocalVar.errorTextAppearance = errorTextAppearance;
    }

    @Attr(RS.attr.errorEnabled)
    public void textInputLayoutErrorTextAppearance(TextInputLayout textInputLayout, boolean errorEnabled) {
        __textInputLayoutLocalVar.errorEnabled = errorEnabled;
    }

    @Attr(RS.attr.helperTextTextAppearance)
    public void textInputLayoutHelperTextTextAppearance(TextInputLayout textInputLayout, int helperTextTextAppearance) {
        __textInputLayoutLocalVar.helperTextTextAppearance = helperTextTextAppearance;
    }

    @Attr(RS.attr.helperTextEnabled)
    public void textInputLayoutHelperTextEnabled(TextInputLayout textInputLayout, boolean helperTextEnabled) {
        __textInputLayoutLocalVar.helperTextEnabled = helperTextEnabled;
    }

    @Attr(RS.attr.helperText)
    public void textInputLayoutHelperText(TextInputLayout textInputLayout, String helperText) {
        __textInputLayoutLocalVar.helperText = helperText;
    }

    @Attr(RS.attr.counterEnabled)
    public void textInputLayoutCounterEnabled(TextInputLayout textInputLayout, boolean counterEnabled) {
        __textInputLayoutLocalVar.counterEnabled = counterEnabled;
    }

    @Attr(RS.attr.counterMaxLength)
    public void textInputLayoutCounterMaxLength(TextInputLayout textInputLayout, int counterMaxLength) {
        textInputLayout.setCounterMaxLength(counterMaxLength);
    }

    @Attr(RS.attr.counterTextAppearance)
    public void textInputLayoutCounterTextAppearance(TextInputLayout textInputLayout, int counterTextAppearance) {
        __textInputLayoutLocalVar.counterTextAppearance = counterTextAppearance;
    }

    @Attr(RS.attr.counterOverflowTextAppearance)
    public void textInputLayoutCounterOverflowTextAppearance(TextInputLayout textInputLayout, int counterOverflowTextAppearance) {
        __textInputLayoutLocalVar.counterOverflowTextAppearance = counterOverflowTextAppearance;
    }

    @Attr(RS.attr.passwordToggleEnabled)
    public void textInputLayoutPasswordToggleEnabled(TextInputLayout textInputLayout, boolean passwordToggleEnabled) {
        __textInputLayoutLocalVar.passwordToggleEnabled = passwordToggleEnabled;
    }

    @Attr(RS.attr.passwordToggleDrawable)
    public void textInputLayoutPasswordToggleDrawable(TextInputLayout textInputLayout, ValueInfo passwordToggleDrawable) {
        __textInputLayoutLocalVar.passwordToggleDrawable = passwordToggleDrawable.resourceId;
    }

    @Attr(RS.attr.passwordToggleContentDescription)
    public void textInputLayoutPasswordToggleContentDescription(TextInputLayout textInputLayout, String passwordToggleContentDescription) {
        __textInputLayoutLocalVar.passwordToggleContentDescription = passwordToggleContentDescription;
    }

    @Attr(RS.attr.passwordToggleTint)
    public void textInputLayoutPasswordToggleTint(TextInputLayout textInputLayout, ValueInfo passwordToggleTint) {
        __textInputLayoutLocalVar.passwordToggleTint = passwordToggleTint.getColorStateList(__context);
    }

    @SuppressLint("RestrictedApi")
    @Attr(RS.attr.passwordToggleTintMode)
    public void textInputLayoutPasswordToggleTintMode(TextInputLayout textInputLayout, int passwordToggleTintMode) {
        __textInputLayoutLocalVar.passwordToggleTintMode = passwordToggleTintMode;
    }

    @OnEnd({RS.attr.counterTextAppearance, RS.attr.counterEnabled, RS.attr.counterOverflowTextAppearance
            , RS.attr.helperTextEnabled, RS.attr.helperText, RS.attr.helperTextTextAppearance
            , RS.attr.errorEnabled, RS.attr.errorTextAppearance})
    public void onTextInputLayoutCounterEnd(TextInputLayout textInputLayout) {
        com.qxml.qxml_support.gen.textInputLayout.TextInputLayoutHelper.setCounterOverflowTextAppearance(textInputLayout, __textInputLayoutLocalVar.counterOverflowTextAppearance);
        com.qxml.qxml_support.gen.textInputLayout.TextInputLayoutHelper.setCounterTextAppearance(textInputLayout, __textInputLayoutLocalVar.counterTextAppearance);
        textInputLayout.setHelperTextEnabled(__textInputLayoutLocalVar.helperTextEnabled);
        textInputLayout.setHelperText(__textInputLayoutLocalVar.helperText);
        textInputLayout.setHelperTextTextAppearance(__textInputLayoutLocalVar.helperTextTextAppearance);
        textInputLayout.setErrorEnabled(__textInputLayoutLocalVar.errorEnabled);
        textInputLayout.setErrorTextAppearance(__textInputLayoutLocalVar.errorTextAppearance);
        textInputLayout.setCounterEnabled(__textInputLayoutLocalVar.counterEnabled);
    }

    @OnEnd({RS.attr.boxCornerRadiusTopStart, RS.attr.boxCornerRadiusTopEnd, RS.attr.boxCornerRadiusBottomEnd, RS.attr.boxCornerRadiusBottomStart})
    public void onTextInputLayoutBoxEnd(TextInputLayout textInputLayout) {
        textInputLayout.setBoxCornerRadii(__textInputLayoutLocalVar.boxCornerRadiusTopStart
                , __textInputLayoutLocalVar.boxCornerRadiusTopEnd
                , __textInputLayoutLocalVar.boxCornerRadiusBottomStart
                , __textInputLayoutLocalVar.boxCornerRadiusBottomEnd);
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({AndroidRS.attr.textColorHint, RS.attr.hintTextAppearance})
    public void onTextInputLayoutHintEnd(TextInputLayout textInputLayout) {
        if (__textInputLayoutLocalVar.textColorHint != null) {
            textInputLayout.setDefaultHintTextColor(__textInputLayoutLocalVar.textColorHint);
        }
        if (__textInputLayoutLocalVar.hintTextAppearance != -1) {
            //构造函数中调用了setHintTextAppearance初始化了CollapsedTextSize，这里重置一下
            com.qxml.qxml_support.gen.textInputLayout.TextInputLayoutHelper.resetDefaultCollapsedTextSize(textInputLayout);
            textInputLayout.setHintTextAppearance(__textInputLayoutLocalVar.hintTextAppearance);
        } else {
            android.support.v7.widget.TintTypedArray tintTypedArray = android.support.design.internal.ThemeEnforcement.obtainTintedStyledAttributes(__context,
                    (android.util.AttributeSet) null, com.qxml.qxml_support.R.styleable.TextInputLayout,
                    0, com.qxml.qxml_support.R.style.Widget_Design_TextInputLayout, new int[0]);
            int hintAppearance = tintTypedArray.getResourceId(android.support.design.R.styleable.TextInputLayout_hintTextAppearance, -1);
            if (hintAppearance != -1) {
                textInputLayout.setHintTextAppearance(tintTypedArray.getResourceId(android.support.design.R.styleable.TextInputLayout_hintTextAppearance, 0));
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({RS.attr.passwordToggleContentDescription})
    public void onTextInputLayoutPasswordToggleContentDescriptionEnd(TextInputLayout textInputLayout) {
        textInputLayout.setPasswordVisibilityToggleContentDescription(__textInputLayoutLocalVar.passwordToggleContentDescription);
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({RS.attr.passwordToggleEnabled})
    public void onTextInputLayoutPasswordToggleEnabledEnd(TextInputLayout textInputLayout) {
        textInputLayout.setPasswordVisibilityToggleEnabled(__textInputLayoutLocalVar.passwordToggleEnabled);
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({RS.attr.passwordToggleDrawable})
    public void onTextInputLayoutPasswordToggleDrawableEnd(TextInputLayout textInputLayout) {
        textInputLayout.setPasswordVisibilityToggleDrawable(__textInputLayoutLocalVar.passwordToggleDrawable);
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({RS.attr.passwordToggleTintMode})
    public void onTextInputLayoutPasswordToggleTintModeEnd(TextInputLayout textInputLayout) {
        textInputLayout.setPasswordVisibilityToggleTintMode(android.support.design.internal.ViewUtils.parseTintMode(__textInputLayoutLocalVar.passwordToggleTintMode, (PorterDuff.Mode)null));
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({RS.attr.passwordToggleTint})
    public void onTextInputLayoutPasswordToggleTintEnd(TextInputLayout textInputLayout) {
        textInputLayout.setPasswordVisibilityToggleTintList(__textInputLayoutLocalVar.passwordToggleTint);
    }
}
