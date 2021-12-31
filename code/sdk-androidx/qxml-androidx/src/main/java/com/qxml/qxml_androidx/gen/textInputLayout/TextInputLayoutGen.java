package com.qxml.qxml_androidx.gen.textInputLayout;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;

import com.google.android.material.textfield.TextInputLayout;
import com.qxml.AndroidRS;
import com.qxml.qxml_androidx.RS;
import com.qxml.gen.linearLayout.LinearLayoutGen;
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
        public String helperText = null;
        public int helperTextTextAppearance = -1;
        public boolean errorEnabled = false;
        public int errorTextAppearance = -1;
        public String errorContentDescription = null;
        public boolean counterEnabled = false;
        public int placeholderTextAppearance = -1;
        public int prefixTextAppearance = -1;
        public int suffixTextAppearance = -1;

        public android.content.res.ColorStateList textColorHint = null;
        public int hintTextAppearance = -1;


        public String passwordToggleContentDescription = null;
        public int passwordToggleDrawable = -1;
        public int passwordToggleEnabled = -1;
        public android.content.res.ColorStateList passwordToggleTint = null;
        public int passwordToggleTintMode = -1;

        public int errorIconDrawable = -1;
        public android.content.res.ColorStateList errorIconTint = null;
        public int errorIconTintMode = -1;

        public int startIconDrawable = -1;
        public String startIconContentDescription = null;
        public boolean startIconCheckable = false;
        public android.content.res.ColorStateList startIconTint = null;
        public int startIconTintMode = -1;

        public int endIconMode = -10086;
        public int endIconDrawable = -1;
        public String endIconContentDescription = null;
        public boolean endIconCheckable = false;

        public android.content.res.ColorStateList errorTextColor = null;
        public android.content.res.ColorStateList helperTextTextColor = null;
        public android.content.res.ColorStateList hintTextColor = null;
        public android.content.res.ColorStateList counterTextColor = null;
        public android.content.res.ColorStateList counterOverflowTextColor = null;
        public android.content.res.ColorStateList placeholderTextColor = null;
        public android.content.res.ColorStateList prefixTextColor = null;
        public android.content.res.ColorStateList suffixTextColor = null;

        public String placeholderText = null;
        public String prefixText = null;
        public String suffixText = null;

        public boolean enabled = true;

        public android.content.res.ColorStateList endIconTint = null;
        public int endIconTintMode = -1;
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
        com.qxml.qxml_androidx.gen.textInputLayout.TextInputLayoutHelper.setBoxCollapsedPaddingTop(textInputLayout, boxCollapsedPaddingTop);
    }

    @Attr(RS.attr.boxStrokeWidth)
    public void textInputLayoutBoxStrokeWidth(TextInputLayout textInputLayout, int boxStrokeWidth) {
        textInputLayout.setBoxStrokeWidth(boxStrokeWidth);
    }

    @Attr(RS.attr.boxStrokeWidthFocused)
    public void textInputLayoutBoxStrokeWidthFocused(TextInputLayout textInputLayout, int boxStrokeWidthFocused) {
        textInputLayout.setBoxStrokeWidthFocused(boxStrokeWidthFocused);
    }

    @Attr(AndroidRS.attr.textColorHint)
    public void textInputLayoutTextColorHint(TextInputLayout textInputLayout, ValueInfo textColorHint) {
        __textInputLayoutLocalVar.textColorHint = textColorHint.getColorStateList(__context);
    }

    @Attr(RS.attr.boxStrokeColor)
    public void textInputLayoutBoxStrokeColor(TextInputLayout textInputLayout, ValueInfo boxStrokeColor) {
        android.content.res.ColorStateList boxStrokeColorStateList = boxStrokeColor.getColorStateList(__context);
        if (boxStrokeColorStateList != null) {
            textInputLayout.setBoxStrokeColorStateList(boxStrokeColorStateList);
        }
    }

    @Attr(RS.attr.boxStrokeErrorColor)
    public void textInputLayoutBoxStrokeErrorColor(TextInputLayout textInputLayout, ValueInfo boxStrokeColor) {
        textInputLayout.setBoxStrokeErrorColor(boxStrokeColor.getColorStateList(__context));
    }

    @Attr(RS.attr.hintTextAppearance)
    public void textInputLayoutHintTextAppearance(TextInputLayout textInputLayout, int hintTextAppearance) {
        __textInputLayoutLocalVar.hintTextAppearance = hintTextAppearance;
    }

    @Attr(RS.attr.errorTextAppearance)
    public void textInputLayoutErrorTextAppearance(TextInputLayout textInputLayout, int errorTextAppearance) {
        __textInputLayoutLocalVar.errorTextAppearance = errorTextAppearance;
    }

    @Attr(RS.attr.errorContentDescription)
    public void textInputLayoutErrorContentDescription(TextInputLayout textInputLayout, String errorContentDescription) {
        __textInputLayoutLocalVar.errorContentDescription = errorContentDescription;
    }

    @Attr(RS.attr.errorIconDrawable)
    public void textInputLayoutErrorIconDrawable(TextInputLayout textInputLayout, int errorIconDrawable) {
        __textInputLayoutLocalVar.errorIconDrawable = errorIconDrawable;
    }

    @Attr(RS.attr.errorIconTint)
    public void textInputLayoutErrorIconTint(TextInputLayout textInputLayout, ValueInfo errorIconTint) {
        __textInputLayoutLocalVar.errorIconTint = errorIconTint.getColorStateList(__context);
    }

    @Attr(RS.attr.errorIconTintMode)
    public void textInputLayoutErrorIconTintMode(TextInputLayout textInputLayout, int errorIconTintMode) {
        __textInputLayoutLocalVar.errorIconTintMode = errorIconTintMode;
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

    @Attr(RS.attr.placeholderTextAppearance)
    public void textInputLayoutPlaceholderTextAppearance(TextInputLayout textInputLayout, int placeholderTextAppearance) {
        __textInputLayoutLocalVar.placeholderTextAppearance = placeholderTextAppearance;
    }

    @Attr(RS.attr.placeholderText)
    public void textInputLayoutPlaceholderText(TextInputLayout textInputLayout, String placeholderText) {
        __textInputLayoutLocalVar.placeholderText = placeholderText;
    }

    @Attr(RS.attr.prefixTextAppearance)
    public void textInputLayoutPrefixTextAppearance(TextInputLayout textInputLayout, int prefixTextAppearance) {
        __textInputLayoutLocalVar.prefixTextAppearance = prefixTextAppearance;
    }

    @Attr(RS.attr.prefixText)
    public void textInputLayoutPrefixText(TextInputLayout textInputLayout, String prefixText) {
        __textInputLayoutLocalVar.prefixText = prefixText;
    }

    @Attr(RS.attr.suffixTextAppearance)
    public void textInputLayoutSuffixTextAppearance(TextInputLayout textInputLayout, int suffixTextAppearance) {
        __textInputLayoutLocalVar.suffixTextAppearance = suffixTextAppearance;
    }

    @Attr(RS.attr.suffixText)
    public void textInputLayoutSuffixText(TextInputLayout textInputLayout, String suffixText) {
        __textInputLayoutLocalVar.suffixText = suffixText;
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

    @Attr(RS.attr.startIconDrawable)
    public void textInputLayoutStartIconDrawable(TextInputLayout textInputLayout, int startIconDrawable) {
        __textInputLayoutLocalVar.startIconDrawable = startIconDrawable;
    }

    @Attr(RS.attr.startIconContentDescription)
    public void textInputLayoutStartIconContentDescription(TextInputLayout textInputLayout, String startIconContentDescription) {
        __textInputLayoutLocalVar.startIconContentDescription = startIconContentDescription;
    }

    @Attr(RS.attr.startIconCheckable)
    public void textInputLayoutStartIconCheckable(TextInputLayout textInputLayout, boolean startIconCheckable) {
        __textInputLayoutLocalVar.startIconCheckable = startIconCheckable;
    }

    @Attr(RS.attr.startIconTint)
    public void textInputLayoutStartIconTint(TextInputLayout textInputLayout, ValueInfo startIconTint) {
        __textInputLayoutLocalVar.startIconTint = startIconTint.getColorStateList(__context);
    }

    @Attr(RS.attr.startIconTintMode)
    public void textInputLayoutStartIconTintMode(TextInputLayout textInputLayout, int startIconTintMode) {
        __textInputLayoutLocalVar.startIconTintMode = startIconTintMode;
    }

    @Attr(RS.attr.boxBackgroundMode)
    public void textInputLayoutBoxBackgroundMode(TextInputLayout textInputLayout, int boxBackgroundMode) {
        textInputLayout.setBoxBackgroundMode(boxBackgroundMode);
    }

    @Attr(RS.attr.endIconMode)
    public void textInputLayoutEndIconMode(TextInputLayout textInputLayout, int endIconMode) {
        __textInputLayoutLocalVar.endIconMode = endIconMode;
    }

    @Attr(RS.attr.endIconDrawable)
    public void textInputLayoutEndIconDrawable(TextInputLayout textInputLayout, int endIconDrawable) {
        __textInputLayoutLocalVar.endIconDrawable = endIconDrawable;
    }

    @Attr(RS.attr.endIconContentDescription)
    public void textInputLayoutEndIconContentDescription(TextInputLayout textInputLayout, String endIconContentDescription) {
        __textInputLayoutLocalVar.endIconContentDescription = endIconContentDescription;
    }

    @Attr(RS.attr.endIconCheckable)
    public void textInputLayoutEndIconCheckable(TextInputLayout textInputLayout, boolean endIconCheckable) {
        __textInputLayoutLocalVar.endIconCheckable = endIconCheckable;
    }

    @Attr(RS.attr.passwordToggleEnabled)
    public void textInputLayoutPasswordToggleEnabled(TextInputLayout textInputLayout, boolean passwordToggleEnabled) {
        __textInputLayoutLocalVar.passwordToggleEnabled = passwordToggleEnabled ? 1 : 0;
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

    @Attr(RS.attr.errorTextColor)
    public void textInputLayoutErrorTextColor(TextInputLayout textInputLayout, ValueInfo errorTextColor) {
        __textInputLayoutLocalVar.errorTextColor = errorTextColor.getColorStateList(__context);
    }

    @Attr(RS.attr.helperTextTextColor)
    public void textInputLayoutHelperTextTextColor(TextInputLayout textInputLayout, ValueInfo helperTextTextColor) {
        __textInputLayoutLocalVar.helperTextTextColor = helperTextTextColor.getColorStateList(__context);
    }

    @Attr(RS.attr.hintTextColor)
    public void textInputLayoutHintTextColor(TextInputLayout textInputLayout, ValueInfo hintTextColor) {
        __textInputLayoutLocalVar.hintTextColor = hintTextColor.getColorStateList(__context);
    }

    @Attr(RS.attr.counterTextColor)
    public void textInputLayoutCounterTextColor(TextInputLayout textInputLayout, ValueInfo counterTextColor) {
        __textInputLayoutLocalVar.counterTextColor = counterTextColor.getColorStateList(__context);
    }

    @Attr(RS.attr.counterOverflowTextColor)
    public void textInputLayoutCounterOverflowTextColor(TextInputLayout textInputLayout, ValueInfo counterOverflowTextColor) {
        __textInputLayoutLocalVar.counterOverflowTextColor = counterOverflowTextColor.getColorStateList(__context);
    }

    @Attr(RS.attr.placeholderTextColor)
    public void textInputLayoutPlaceholderTextColor(TextInputLayout textInputLayout, ValueInfo placeholderTextColor) {
        __textInputLayoutLocalVar.placeholderTextColor = placeholderTextColor.getColorStateList(__context);
    }

    @Attr(RS.attr.prefixTextColor)
    public void textInputLayoutPrefixTextColor(TextInputLayout textInputLayout, ValueInfo prefixTextColor) {
        __textInputLayoutLocalVar.prefixTextColor = prefixTextColor.getColorStateList(__context);
    }

    @Attr(RS.attr.suffixTextColor)
    public void textInputLayoutSuffixTextColor(TextInputLayout textInputLayout, ValueInfo suffixTextColor) {
        __textInputLayoutLocalVar.suffixTextColor = suffixTextColor.getColorStateList(__context);
    }

    @Attr(AndroidRS.attr.enabled)
    public void textInputLayoutEnabled(TextInputLayout textInputLayout, boolean enabled) {
        __textInputLayoutLocalVar.enabled = enabled;
    }

    @Attr(RS.attr.endIconTint)
    public void textInputLayoutEndIconTint(TextInputLayout textInputLayout, ValueInfo endIconTint) {
        __textInputLayoutLocalVar.endIconTint = endIconTint.getColorStateList(__context);
    }

    @Attr(RS.attr.endIconTintMode)
    public void textInputLayoutEndIconTintMode(TextInputLayout textInputLayout, int endIconTintMode) {
        __textInputLayoutLocalVar.endIconTintMode = endIconTintMode;
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

    @Attr(RS.attr.errorEnabled)
    public void textInputLayoutErrorEnabled(TextInputLayout textInputLayout, boolean errorEnabled) {
        __textInputLayoutLocalVar.errorEnabled = errorEnabled;
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({AndroidRS.attr.textColorHint, RS.attr.hintTextAppearance})
    public void onTextInputLayoutHintEnd(TextInputLayout textInputLayout) {
        if (__textInputLayoutLocalVar.textColorHint != null) {
            textInputLayout.setDefaultHintTextColor(__textInputLayoutLocalVar.textColorHint);
        }
        if (__textInputLayoutLocalVar.hintTextAppearance != -1) {
            //构造函数中调用了setHintTextAppearance初始化了CollapsedTextSize，这里重置一下
            com.qxml.qxml_androidx.gen.textInputLayout.TextInputLayoutHelper.resetDefaultCollapsedTextSize(textInputLayout);
            textInputLayout.setHintTextAppearance(__textInputLayoutLocalVar.hintTextAppearance);
        } else {
            androidx.appcompat.widget.TintTypedArray tintTypedArray = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(__context,
                    (android.util.AttributeSet) null, com.qxml.qxml_androidx.R.styleable.TextInputLayout,
                    0, com.qxml.qxml_androidx.R.style.Widget_Design_TextInputLayout, new int[0]);
            int hintAppearance = tintTypedArray.getResourceId(com.google.android.material.R.styleable.TextInputLayout_hintTextAppearance, -1);
            if (hintAppearance != -1) {
                textInputLayout.setHintTextAppearance(tintTypedArray.getResourceId(com.google.android.material.R.styleable.TextInputLayout_hintTextAppearance, 0));
            }
        }
    }

    @OnEnd({RS.attr.counterTextAppearance, RS.attr.counterOverflowTextAppearance
            , RS.attr.helperTextEnabled, RS.attr.helperText, RS.attr.helperTextTextAppearance
            , RS.attr.errorEnabled, RS.attr.errorTextAppearance, RS.attr.errorContentDescription
            , RS.attr.placeholderText, RS.attr.placeholderTextAppearance, RS.attr.prefixText
            , RS.attr.prefixTextAppearance, RS.attr.suffixText, RS.attr.suffixTextAppearance})
    public void onTextInputLayoutCounterEnd(TextInputLayout textInputLayout) {
        if (__textInputLayoutLocalVar.helperTextEnabled) {
            textInputLayout.setHelperTextEnabled(true);
        }
        if (__textInputLayoutLocalVar.helperText != null) {
            textInputLayout.setHelperText(__textInputLayoutLocalVar.helperText);
        }
        if (__textInputLayoutLocalVar.helperTextTextAppearance != -1) {
            textInputLayout.setHelperTextTextAppearance(__textInputLayoutLocalVar.helperTextTextAppearance);
        }
        if (__textInputLayoutLocalVar.errorEnabled) {
            textInputLayout.setErrorEnabled(true);
        }
        if (__textInputLayoutLocalVar.errorTextAppearance != -1) {
            textInputLayout.setErrorTextAppearance(__textInputLayoutLocalVar.errorTextAppearance);
        }
        if (__textInputLayoutLocalVar.errorContentDescription != null) {
            textInputLayout.setErrorContentDescription(__textInputLayoutLocalVar.errorContentDescription);
        }
        if (__textInputLayoutLocalVar.counterTextAppearance != -1) {
            textInputLayout.setCounterTextAppearance(__textInputLayoutLocalVar.counterTextAppearance);
        }
        if (__textInputLayoutLocalVar.counterOverflowTextAppearance != -1) {
            textInputLayout.setCounterOverflowTextAppearance(__textInputLayoutLocalVar.counterOverflowTextAppearance);
        }
        if (__textInputLayoutLocalVar.placeholderText != null) {
            textInputLayout.setPlaceholderText(__textInputLayoutLocalVar.placeholderText);
        }
        if (__textInputLayoutLocalVar.placeholderTextAppearance != -1) {
            textInputLayout.setPlaceholderTextAppearance(__textInputLayoutLocalVar.placeholderTextAppearance);
        }
        if (__textInputLayoutLocalVar.prefixText != null) {
            textInputLayout.setPrefixText(__textInputLayoutLocalVar.prefixText);
        }
        if (__textInputLayoutLocalVar.prefixTextAppearance != -1) {
            textInputLayout.setPrefixTextAppearance(__textInputLayoutLocalVar.prefixTextAppearance);
        }
        if (__textInputLayoutLocalVar.suffixText != null) {
            textInputLayout.setSuffixText(__textInputLayoutLocalVar.suffixText);
        }
        if (__textInputLayoutLocalVar.suffixTextAppearance != -1) {
            textInputLayout.setSuffixTextAppearance(__textInputLayoutLocalVar.suffixTextAppearance);
        }
    }

    @OnEnd({RS.attr.errorTextColor, RS.attr.helperTextTextColor, RS.attr.hintTextColor
            , RS.attr.counterTextColor, RS.attr.counterOverflowTextColor, RS.attr.placeholderTextColor
            , RS.attr.prefixTextColor, RS.attr.suffixTextColor, RS.attr.counterEnabled})
    public void onTextInputLayoutTextColorEnd(TextInputLayout textInputLayout) {
        if (__textInputLayoutLocalVar.errorTextColor != null) {
            textInputLayout.setErrorTextColor(__textInputLayoutLocalVar.errorTextColor);
        }
        if (__textInputLayoutLocalVar.helperTextTextColor != null) {
            textInputLayout.setHelperTextColor(__textInputLayoutLocalVar.helperTextTextColor);
        }
        if (__textInputLayoutLocalVar.hintTextColor != null) {
            textInputLayout.setHintTextColor(__textInputLayoutLocalVar.hintTextColor);
        }
        if (__textInputLayoutLocalVar.counterTextColor != null) {
            textInputLayout.setCounterTextColor(__textInputLayoutLocalVar.counterTextColor);
        }
        if (__textInputLayoutLocalVar.counterOverflowTextColor != null) {
            textInputLayout.setCounterOverflowTextColor(__textInputLayoutLocalVar.counterOverflowTextColor);
        }
        if (__textInputLayoutLocalVar.placeholderTextColor != null) {
            textInputLayout.setPlaceholderTextColor(__textInputLayoutLocalVar.placeholderTextColor);
        }
        if (__textInputLayoutLocalVar.prefixTextColor != null) {
            textInputLayout.setPrefixTextColor(__textInputLayoutLocalVar.prefixTextColor);
        }
        if (__textInputLayoutLocalVar.suffixTextColor != null) {
            textInputLayout.setSuffixTextColor(__textInputLayoutLocalVar.suffixTextColor);
        }
        if (__textInputLayoutLocalVar.counterEnabled) {
            textInputLayout.setCounterEnabled(true);
        }
    }

    @OnEnd({RS.attr.boxCornerRadiusTopStart, RS.attr.boxCornerRadiusTopEnd, RS.attr.boxCornerRadiusBottomEnd, RS.attr.boxCornerRadiusBottomStart})
    public void onTextInputLayoutBoxEnd(TextInputLayout textInputLayout) {
        textInputLayout.setBoxCornerRadii(__textInputLayoutLocalVar.boxCornerRadiusTopStart
                , __textInputLayoutLocalVar.boxCornerRadiusTopEnd
                , __textInputLayoutLocalVar.boxCornerRadiusBottomStart
                , __textInputLayoutLocalVar.boxCornerRadiusBottomEnd);
    }

    @SuppressLint({"RestrictedApi", "WrongConstant"})
    @OnEnd({RS.attr.passwordToggleContentDescription, RS.attr.passwordToggleDrawable
            , RS.attr.passwordToggleEnabled, RS.attr.passwordToggleTint, RS.attr.passwordToggleTintMode

            , RS.attr.endIconMode, RS.attr.endIconDrawable, RS.attr.endIconContentDescription
            , RS.attr.endIconCheckable
    })
    public void onTextInputLayoutPasswordToggleEnd(TextInputLayout textInputLayout) {
        if (__textInputLayoutLocalVar.endIconMode != -10086) {
            textInputLayout.setEndIconMode(__textInputLayoutLocalVar.endIconMode);
            if (__textInputLayoutLocalVar.endIconDrawable != -1) {
                textInputLayout.setEndIconDrawable(__textInputLayoutLocalVar.endIconDrawable);
            }
            if (__textInputLayoutLocalVar.endIconContentDescription != null) {
                textInputLayout.setEndIconContentDescription(__textInputLayoutLocalVar.endIconContentDescription);
            }
            if (!__textInputLayoutLocalVar.endIconCheckable) {
                textInputLayout.setEndIconCheckable(false);
            }
        } else if (__textInputLayoutLocalVar.passwordToggleEnabled != -1) { //has passwordToggleEnabled
            textInputLayout.setEndIconMode(__textInputLayoutLocalVar.passwordToggleEnabled == 1 ? com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE : com.google.android.material.textfield.TextInputLayout.END_ICON_NONE);
            if (__textInputLayoutLocalVar.passwordToggleDrawable != -1) {
                textInputLayout.setEndIconDrawable(__textInputLayoutLocalVar.passwordToggleDrawable);
            }
            if (__textInputLayoutLocalVar.endIconContentDescription != null) {
                textInputLayout.setEndIconContentDescription(__textInputLayoutLocalVar.endIconContentDescription);
            }
            if (__textInputLayoutLocalVar.passwordToggleTint != null) {
                textInputLayout.setEndIconTintList(__textInputLayoutLocalVar.passwordToggleTint);
            }
            if (__textInputLayoutLocalVar.passwordToggleTintMode != -1) {
                textInputLayout.setEndIconTintMode(com.google.android.material.internal.ViewUtils.parseTintMode(__textInputLayoutLocalVar.passwordToggleTintMode, (PorterDuff.Mode)null));
            }
        }

        if (__textInputLayoutLocalVar.passwordToggleEnabled == -1) {
            if (__textInputLayoutLocalVar.endIconTint != null) {
                textInputLayout.setEndIconTintList(__textInputLayoutLocalVar.endIconTint);
            }
            if (__textInputLayoutLocalVar.endIconTintMode != -1) {
                textInputLayout.setEndIconTintMode(com.google.android.material.internal.ViewUtils.parseTintMode(__textInputLayoutLocalVar.endIconTintMode, (PorterDuff.Mode)null));
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({RS.attr.errorIconDrawable, RS.attr.errorIconTint, RS.attr.errorIconTintMode})
    public void onTextInputLayoutErrorIconEnd(TextInputLayout textInputLayout) {
        if (__textInputLayoutLocalVar.errorIconDrawable != -1) {
            textInputLayout.setErrorIconDrawable(__textInputLayoutLocalVar.errorIconDrawable);
        }
        if (__textInputLayoutLocalVar.errorIconTint != null) {
            textInputLayout.setErrorIconTintList(__textInputLayoutLocalVar.errorIconTint);
        }
        if (__textInputLayoutLocalVar.errorIconTintMode != -1) {
            textInputLayout.setErrorIconTintMode(com.google.android.material.internal.ViewUtils.parseTintMode(__textInputLayoutLocalVar.errorIconTintMode, (PorterDuff.Mode)null));
        }
    }

    @SuppressLint("RestrictedApi")
    @OnEnd({RS.attr.startIconDrawable, RS.attr.startIconContentDescription, RS.attr.startIconCheckable
            , RS.attr.startIconTint, RS.attr.startIconTintMode})
    public void onTextInputLayoutStartIconEnd(TextInputLayout textInputLayout) {
        if (__textInputLayoutLocalVar.startIconDrawable != -1) {
            textInputLayout.setStartIconDrawable(__textInputLayoutLocalVar.startIconDrawable);
            if (__textInputLayoutLocalVar.startIconContentDescription != null) {
                textInputLayout.setStartIconContentDescription(__textInputLayoutLocalVar.startIconContentDescription);
            }
            if (!__textInputLayoutLocalVar.startIconCheckable) {
                textInputLayout.setStartIconCheckable(false);
            }
        }
        if (__textInputLayoutLocalVar.startIconTint != null) {
            textInputLayout.setStartIconTintList(__textInputLayoutLocalVar.startIconTint);
        }
        if (__textInputLayoutLocalVar.startIconTintMode != -1) {
            textInputLayout.setStartIconTintMode(com.google.android.material.internal.ViewUtils.parseTintMode(__textInputLayoutLocalVar.startIconTintMode, (PorterDuff.Mode)null));
        }
    }
}
