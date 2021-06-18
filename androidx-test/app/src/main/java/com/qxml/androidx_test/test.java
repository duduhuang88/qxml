package com.qxml.androidx_test;

import android.content.Context;

import com.yellow.qxml_test.LogUtil;

public class test {

    public static void t(Context __context) {
        android.util.TypedValue ___typedValue = new android.util.TypedValue();
        android.content.res.Resources ___resources = __context.getResources();
        ___resources.getValue(com.qxml.androidx_test.R.layout.layout_type_version_test, ___typedValue, true);
        // 默认为layout
        int ___layoutTypeHashCode = 0;
        CharSequence ___layoutTypeStr = ___typedValue.string;
        if(___layoutTypeStr.charAt(10) == '-') {
            ___layoutTypeHashCode = ___layoutTypeStr.subSequence(11, ___layoutTypeStr.length() - 29).toString().hashCode();
        }
    }

    /**
     * generated code of layout_type_version_test.xml
     * generate success type:
     * layout layout-v28
     */
    public static android.view.View generate(android.view.LayoutInflater __layoutInflater,
                                             android.content.Context __context, android.view.ViewGroup __root, boolean __attachTo) {
        android.view.ViewGroup.LayoutParams ___root_layout_param = null;
        android.view.ViewGroup.LayoutParams ___cur_layout_param = null;
        com.qxml.value.ValueInfo ___valueInfo = new com.qxml.value.ValueInfo();
        android.util.TypedValue ___typedValue = new android.util.TypedValue();
        android.content.res.Resources ___resources = __context.getResources();
        ___resources.getValue(com.qxml.androidx_test.R.layout.layout_type_version_test, ___typedValue, true);
        // 默认为layout
        int ___layoutTypeHashCode = 0;
        CharSequence ___layoutTypeStr = ___typedValue.string;
        if(___layoutTypeStr.charAt(10) == '-') {
            ___layoutTypeHashCode = ___layoutTypeStr.subSequence(11, ___layoutTypeStr.length() - 29).toString().hashCode();
        }

        android.util.Log.e("touch", "f layout type info: "+___layoutTypeHashCode+" "+___layoutTypeStr);
        //type: layout;
        if(___layoutTypeHashCode == 0) {
            android.widget.TextView ___view_0 = new android.widget.TextView(__context, null);
            ;
            if(__root != null && __attachTo) {
                ___root_layout_param = com.qxml.tools.QxmlViewTools.generateDefaultLayoutParams(__root);
                ___cur_layout_param = ___root_layout_param;;
            }
            boolean __constrainLocalVar_layout_constrainedWidth = false;
            int __constrainLocalVar_baseLine2baseLine = -1;
            float __constrainLocalVar_layout_constraintVertical_weight = -1.0F;
            int __imageViewLocalVar_scaleType = -1;
            int __tabLayoutLocalVar_tabSelectedTextColorValue = Integer.MAX_VALUE;
            int __expandableListVieLocalVar_childIndicatorRight = -1;
            int __tabLayoutLocalVar_tabMode = 1;
            boolean __relativeLocalVar_layout_centerVertical = false;
            int __recyclerViewLocalVar_fastScrollVerticalThumbDrawable = 0;
            int __constrainLocalVar_goneMarginTop = -1;
            int __placeHolderLocalVar_contentReferenceId = 0;
            boolean __recyclerViewLocalVar_reverseLayout = false;
            boolean __constrainLocalVar_layout_constrainedHeight = false;
            int __compoundButtonLocalVar_buttonTintMode = -1;
            int __appCompatImageViewLocalVar_tintColor = Integer.MAX_VALUE;
            int __constrainLocalVar_e2s = -1;
            boolean __relativeLocalVar_layout_centerInParent = false;
            int __relativeLocalVar_layout_alignTop = 0;
            String __recyclerViewLocalVar_layoutManager = null;
            int __expandableListVieLocalVar_groupIndicator = 0;
            boolean __textViewLocalVar_password = false;
            int __progressLocalVar_mMaxWidth = 48;
            boolean __tabLayoutLocalVar_tabPaddingEndSet = false;
            float __constrainLocalVar_layout_constraintGuide_percent = -1.0F;
            int __paddingLocalVar_paddingLeft = -2147483648;
            int __constrainLocalVar_layout_constraintGuide_end = -1;
            int __textViewLocalVar_autoLinkFlag = -1;
            String __spinnerLocalVar_prompt = null;
            int __viewLocalVar_layoutHeight = 0;
            boolean __recyclerViewLocalVar_stackFromEnd = false;
            int __absSeekBarLocalVar_thumbOffset = 0;
            int __textViewDrawableLocalVar_drawableBottomId = 0;
            boolean __progressLocalVar_indeterminateOnly = false;
            int __textViewLocalVar_textFontWeight = -1;
            boolean __motionLayoutLocalVar_applyMotionScene = true;
            boolean __recyclerViewLocalVar_fastScrollEnabled = false;
            int __progressLocalVar_mMinWidth = 24;
            int __constrainLocalVar_e2e = -1;
            int __progressLocalVar_max = -1;
            int __spinnerLocalVar_spinnerMode = 1;
            int __relativeLocalVar_layout_toRightOf = 0;
            int __recyclerViewLocalVar_managerOrientation = 1;
            int __constrainLocalVar_layout_constraintGuide_begin = -1;
            String __textViewLocalVar_text = null;
            int __relativeLocalVar_layout_alignBottom = 0;
            float __constrainLocalVar_constraintWidth_percent = 1.0F;
            int __recyclerViewLocalVar_fastScrollHorizontalTrackDrawable = 0;
            int __relativeLocalVar_layout_below = 0;
            int __constrainLocalVar_layout_constraintCircleRadius = 0;
            int __progressLocalVar_progress = -1;
            boolean __relativeLocalVar_layout_alignParentTop = false;
            int __textViewDrawableLocalVar_drawableTintModeEnum = -1;
            int __spinnerLocalVar_dropDownSelector = 0;
            int __relativeLocalVar_tag = 0;
            int __constrainLocalVar_layout_constraintCircleAngle = 0;
            int __textViewAutoSizeLocalVar_autoSizeMaxTextSize = -1;
            int __textViewAutoSizeLocalVar_autoSizeStepGranularity = 1;
            int __constrainLocalVar_layout_constraintCircle = -1;
            int __spinnerLocalVar_entriesId = 0;
            int __paddingLocalVar_paddingBottom = -2147483648;
            int __constrainLocalVar_layout_constraintVertical_chainStyle = 0;
            android.graphics.Typeface __textViewLocalVar_typeface = null;
            int __tabLayoutLocalVar_tabPaddingTop = 0;
            int __absSeekBarLocalVar_thumb = 0;
            int __constrainLocalVar_r2l = -1;
            int __constrainLocalVar_goneMarginBottom = -1;
            int __constrainLocalVar_r2r = -1;
            float __textViewLocalVar_lineSpacingExtra = 0.0F;
            int __textViewDrawableLocalVar_drawableRightId = 0;
            int __expandableListVieLocalVar_childIndicator = 0;
            int __constrainLocalVar_b2t = -1;
            int __relativeLocalVar_layout_above = 0;
            int __expandableListVieLocalVar_indicatorTag = 0;
            boolean __relativeLocalVar_layout_alignParentBottom = false;
            int __spinnerLocalVar_dropDownWidth = -2;
            int __relativeLocalVar_layout_alignRight = 0;
            int __paddingLocalVar_paddingTop = -2147483648;
            int __motionLayoutLocalVar_layoutDescription = -1;
            int __constrainLocalVar_editor_absoluteY = -1;
            int __textViewDrawableLocalVar_drawableTopId = 0;
            int __constrainLocalVar_editor_absoluteX = -1;
            int __compoundButtonLocalVar_button = 0;
            int __expandableListVieLocalVar_childIndicatorLeft = -1;
            float __constrainLocalVar_constraintHeight_percent = 1.0F;
            int __progressLocalVar_secondaryProgress = -1;
            int __textViewLocalVar_bufferType = -1;
            int __textViewAutoSizeLocalVar_autoSizeTextType = 0;
            int __groupLocalVar_visibility = 0;
            int __constrainLocalVar_b2b = -1;
            int __textViewDrawableLocalVar_drawableLeftId = 0;
            boolean __tabLayoutLocalVar_tabPaddingTopSet = false;
            int __expandableListVieLocalVar_indicatorRight = 0;
            int __textViewAutoSizeLocalVar_autoSizeMinTextSize = -1;
            boolean __imageViewLocalVar_adjustViewBounds = false;
            int __appCompatImageViewLocalVar_tint = 0;
            boolean __relativeLocalVar_layout_alignParentLeft = false;
            int __constrainLocalVar_s2s = -1;
            float __constrainLocalVar_layout_constraintVertical_bias = 0.5F;
            boolean __tabLayoutLocalVar_tabPaddingStartSet = false;
            int __marginLocalVar_marginBottom = -2147483648;
            int __marginLocalVar_marginTag = 0;
            boolean __viewLocalVar_layoutHeightSet = false;
            int __paddingLocalVar_paddingRight = -2147483648;
            int __compatTextViewLocalVar_appFontFamilyId = 0;
            boolean __relativeLocalVar_layout_alignParentStart = false;
            int __appCompatImageViewLocalVar_tintMode = -1;
            int __relativeLocalVar_layout_toStartOf = 0;
            int __constrainLocalVar_s2e = -1;
            boolean __progressLocalVar_indeterminate = false;
            float __constrainLocalVar_layout_constraintHorizontal_weight = -1.0F;
            int __relativeLocalVar_layout_alignLeft = 0;
            int __progressLocalVar_mMaxHeight = 48;
            int __paddingLocalVar_paddingTag = 0;
            int __textViewLocalVar_textStyle = 0;
            int __constrainLocalVar_goneMarginEnd = -1;
            int __expandableListVieLocalVar_childIndicatorEnd = -1;
            int __recyclerViewLocalVar_fastScrollHorizontalThumbDrawable = 0;
            int __tabLayoutLocalVar_tabPaddingStart = 0;
            float __textViewLocalVar_lineSpacingMultiplier = 1.0F;
            int __marginLocalVar_marginRight = -2147483648;
            int __compatTextViewLocalVar_androidFontFamilyId = 0;
            boolean __relativeLocalVar_layout_alignParentRight = false;
            int __tabLayoutLocalVar_tabPaddingBottom = 0;
            int __relativeLocalVar_layout_toEndOf = 0;
            int __constrainLocalVar_layout_constraintWidth_min = 0;
            int __textViewDrawableLocalVar_drawableTag = 0;
            boolean __relativeLocalVar_alignWithParent = false;
            int __relativeLocalVar_layout_alignEnd = 0;
            int __constrainLocalVar_goneMarginRight = -1;
            android.content.res.ColorStateList __tabLayoutLocalVar_colorStateList = null;
            int __constrainLocalVar_goneMarginLeft = -1;
            boolean __viewLocalVar_layoutWidthSet = false;
            int __progressLocalVar_min = -1;
            android.content.res.ColorStateList __compoundButtonLocalVar_buttonTint = null;
            int __spinnerLocalVar_popupBackground = 0;
            int __progressLocalVar_mMinHeight = 24;
            int __relativeLocalVar_layout_alignBaseline = 0;
            int __appCompatImageViewLocalVar_src = 0;
            int __marginLocalVar_marginLeft = -2147483648;
            boolean __relativeLocalVar_layout_alignParentEnd = false;
            int __constrainLocalVar_layout_constraintWidth_max = 0;
            int __textViewAutoSizeLocalVar_autoSizePresetSizes = -1;
            int __constrainLocalVar_t2t = -1;
            int __appCompatImageViewLocalVar_srcCompat = 0;
            int __spinnerLocalVar_popupTheme = 0;
            int __tabLayoutLocalVar_tabPaddingEnd = 0;
            int __textViewLocalVar_inputTypeFlag = -1;
            int __constrainLocalVar_l2r = -1;
            String __textViewLocalVar_fontFamily = null;
            int __constrainLocalVar_goneMarginStart = -1;
            String __compatTextViewLocalVar_appFontFamily = null;
            float __constrainLocalVar_layout_constraintHorizontal_bias = 0.5F;
            int __marginLocalVar_marginTop = -2147483648;
            int __constrainLocalVar_orientation = -1;
            boolean __relativeLocalVar_layout_centerHorizontal = false;
            int __relativeLocalVar_layout_alignStart = 0;
            int __expandableListVieLocalVar_indicatorLeft = 0;
            int __constrainLocalVar_l2l = -1;
            int __recyclerViewLocalVar_spanCount = 1;
            int __constrainLocalVar_t2b = -1;
            int __tabLayoutLocalVar_tabContentStart = 0;
            int __constrainLocalVar_layout_constraintHorizontal_chainStyle = 0;
            boolean __tabLayoutLocalVar_tabPaddingBottomSet = false;
            int __relativeLocalVar_layout_toLeftOf = 0;
            float __textViewLocalVar_textSize = -1.0F;
            int __constrainLocalVar_layout_constraintHeight_max = 0;
            int __constrainLocalVar_layout_constraintHeight_default = 0;
            int __imageViewLocalVar_tintMode = -1;
            int __expandableListVieLocalVar_childIndicatorStart = -1;
            int __viewLocalVar_layoutWidth = 0;
            int __constrainLocalVar_layout_constraintWidth_default = 0;
            String __constrainLocalVar_layout_constraintDimensionRatio = null;
            int __textViewLocalVar_maxLength = -1;
            int __textViewLocalVar_typefaceIndex = -1;
            int __recyclerViewLocalVar_fastScrollVerticalTrackDrawable = 0;
            int __constrainLocalVar_layout_constraintHeight_min = 0;
            {
                android.widget.TextView textView = (android.widget.TextView) ___view_0;
                java.lang.String text;
                text = "i am layout from root";

                __textViewLocalVar_text = text;
            };
            {
                android.widget.TextView textView = (android.widget.TextView) ___view_0;
                int gravityFlag;
                gravityFlag = 17;

                textView.setGravity(gravityFlag);
            };
            {
                android.view.View view = (android.view.View) ___view_0;
                int width;
                width = -1;

                __viewLocalVar_layoutWidth = width;
                __viewLocalVar_layoutWidthSet = true;
            };
            {
                android.view.View view = (android.view.View) ___view_0;
                int height;
                height = -1;

                __viewLocalVar_layoutHeight = height;
                __viewLocalVar_layoutHeightSet = true;
            };
            {
                android.view.View view = ___view_0;

                android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
                if (lp instanceof android.view.ViewGroup.MarginLayoutParams) {
                    if (__viewLocalVar_layoutWidthSet) {
                        lp.width = __viewLocalVar_layoutWidth;
                    }
                    if (__viewLocalVar_layoutHeightSet) {
                        lp.height = __viewLocalVar_layoutHeight;
                    }
                    android.view.ViewGroup.MarginLayoutParams mLp = (android.view.ViewGroup.MarginLayoutParams)lp;
                    if (__marginLocalVar_marginLeft == -2147483648) {
                        __marginLocalVar_marginLeft = mLp.leftMargin;
                    }
                    if (__marginLocalVar_marginTop == -2147483648) {
                        __marginLocalVar_marginTop = mLp.topMargin;
                    }
                    if (__marginLocalVar_marginRight == -2147483648) {
                        __marginLocalVar_marginRight = mLp.rightMargin;
                    }
                    if (__marginLocalVar_marginBottom == -2147483648) {
                        __marginLocalVar_marginBottom = mLp.bottomMargin;
                    }
                    if (android.os.Build.VERSION.SDK_INT > 17) {
                        if ((__marginLocalVar_marginTag & com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_START_SET) != 0) {
                            mLp.setMarginStart(__marginLocalVar_marginLeft);
                        }
                        if ((__marginLocalVar_marginTag & com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_END_SET) != 0) {
                            mLp.setMarginEnd(__marginLocalVar_marginRight);
                        }
                    }
                    mLp.setMargins(__marginLocalVar_marginLeft, __marginLocalVar_marginTop, __marginLocalVar_marginRight, __marginLocalVar_marginBottom);
                }
            };
            {
                android.widget.TextView textView = ___view_0;

                if (__textViewLocalVar_maxLength >= 0) {
                    android.text.InputFilter[] filters = new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(__textViewLocalVar_maxLength)};
                    textView.setFilters(filters);
                }
                boolean isPassword = __textViewLocalVar_password;
                if (__textViewLocalVar_password) {
                    textView.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
                }
                if (__textViewLocalVar_inputTypeFlag != -1) {
                    textView.setInputType(__textViewLocalVar_inputTypeFlag);
                    final int variation = __textViewLocalVar_inputTypeFlag & (android.view.inputmethod.EditorInfo.TYPE_MASK_CLASS | android.view.inputmethod.EditorInfo.TYPE_MASK_VARIATION);
                    final boolean passwordInputType = variation == (android.view.inputmethod.EditorInfo.TYPE_CLASS_TEXT | android.view.inputmethod.EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    final boolean webPasswordInputType = variation == (android.view.inputmethod.EditorInfo.TYPE_CLASS_TEXT | android.view.inputmethod.EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                    final boolean numberPasswordInputType = variation == (android.view.inputmethod.EditorInfo.TYPE_CLASS_NUMBER | android.view.inputmethod.EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
                    isPassword = isPassword || passwordInputType || webPasswordInputType || numberPasswordInputType;
                }
                if (__textViewLocalVar_typeface != null || __textViewLocalVar_fontFamily != null || __textViewLocalVar_typefaceIndex != -1) {
                    if (__textViewLocalVar_typeface == null && __textViewLocalVar_fontFamily != null) {
                        __textViewLocalVar_typeface = android.graphics.Typeface.create(__textViewLocalVar_fontFamily, android.graphics.Typeface.NORMAL);
                    } else if (__textViewLocalVar_typeface != null) {
                    } else {
                        switch (__textViewLocalVar_typefaceIndex) {
                            case 1:
                            {
                                __textViewLocalVar_typeface = android.graphics.Typeface.SANS_SERIF;
                                break;
                            }

                            case 2:
                            {
                                __textViewLocalVar_typeface = android.graphics.Typeface.SERIF;
                                break;
                            }

                            case 3:
                            {
                                __textViewLocalVar_typeface = android.graphics.Typeface.MONOSPACE;
                                break;
                            }

                            default:
                            {
                                __textViewLocalVar_typeface = null;
                            }

                        }
                    }
                    if (android.os.Build.VERSION.SDK_INT >= 28 && __textViewLocalVar_textFontWeight >= 0) {
                        __textViewLocalVar_textFontWeight = java.lang.Math.min(1000, __textViewLocalVar_textFontWeight);
                        final boolean italic = (__textViewLocalVar_textStyle & android.graphics.Typeface.ITALIC) != 0;
                        textView.setTypeface(android.graphics.Typeface.create(__textViewLocalVar_typeface, __textViewLocalVar_textFontWeight, italic));
                    } else {
                        textView.setTypeface(__textViewLocalVar_typeface, __textViewLocalVar_textStyle);
                    }
                } else {
                    if (isPassword) {
                        textView.setTypeface(null, __textViewLocalVar_textStyle);
                    }
                }
                if (__textViewLocalVar_textSize != -1.0F) {
                    textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, (float)((int)(__textViewLocalVar_textSize + 0.5F)));
                }
                if (__textViewLocalVar_autoLinkFlag != -1) {
                    textView.setAutoLinkMask(__textViewLocalVar_autoLinkFlag);
                }
                if (__textViewLocalVar_bufferType != -1) {
                    android.widget.TextView.BufferType bufferType;
                    if (__textViewLocalVar_bufferType == 0) {
                        bufferType = android.widget.TextView.BufferType.NORMAL;
                    } else if (__textViewLocalVar_bufferType == 1) {
                        bufferType = android.widget.TextView.BufferType.SPANNABLE;
                    } else if (__textViewLocalVar_bufferType == 2) {
                        bufferType = android.widget.TextView.BufferType.EDITABLE;
                    } else {
                        bufferType = android.widget.TextView.BufferType.EDITABLE;
                    }
                    textView.setText(__textViewLocalVar_text, bufferType);
                } else {
                    if (__textViewLocalVar_text != null) {
                        textView.setText(__textViewLocalVar_text);
                    }
                }
            };
            com.qxml.helper.QxmlHelper.showQxmlDebug(___view_0);
            if(__root != null && __attachTo) {
                __root.addView(___view_0, -1, ___root_layout_param);
            }
            if(__attachTo) {
                return __root;
            }
            return ___view_0;
        }
        //type: layout-v28;
        if(___layoutTypeHashCode == 115004) {
            android.widget.TextView ___view_0 = new android.widget.TextView(__context, null);
            ;
            if(__root != null && __attachTo) {
                ___root_layout_param = com.qxml.tools.QxmlViewTools.generateDefaultLayoutParams(__root);
                ___cur_layout_param = ___root_layout_param;;
            }
            boolean __constrainLocalVar_layout_constrainedWidth = false;
            int __constrainLocalVar_baseLine2baseLine = -1;
            float __constrainLocalVar_layout_constraintVertical_weight = -1.0F;
            int __imageViewLocalVar_scaleType = -1;
            int __tabLayoutLocalVar_tabSelectedTextColorValue = Integer.MAX_VALUE;
            int __expandableListVieLocalVar_childIndicatorRight = -1;
            int __tabLayoutLocalVar_tabMode = 1;
            boolean __relativeLocalVar_layout_centerVertical = false;
            int __recyclerViewLocalVar_fastScrollVerticalThumbDrawable = 0;
            int __constrainLocalVar_goneMarginTop = -1;
            int __placeHolderLocalVar_contentReferenceId = 0;
            boolean __recyclerViewLocalVar_reverseLayout = false;
            boolean __constrainLocalVar_layout_constrainedHeight = false;
            int __compoundButtonLocalVar_buttonTintMode = -1;
            int __appCompatImageViewLocalVar_tintColor = Integer.MAX_VALUE;
            int __constrainLocalVar_e2s = -1;
            boolean __relativeLocalVar_layout_centerInParent = false;
            int __relativeLocalVar_layout_alignTop = 0;
            String __recyclerViewLocalVar_layoutManager = null;
            int __expandableListVieLocalVar_groupIndicator = 0;
            boolean __textViewLocalVar_password = false;
            int __progressLocalVar_mMaxWidth = 48;
            boolean __tabLayoutLocalVar_tabPaddingEndSet = false;
            float __constrainLocalVar_layout_constraintGuide_percent = -1.0F;
            int __paddingLocalVar_paddingLeft = -2147483648;
            int __constrainLocalVar_layout_constraintGuide_end = -1;
            int __textViewLocalVar_autoLinkFlag = -1;
            String __spinnerLocalVar_prompt = null;
            int __viewLocalVar_layoutHeight = 0;
            boolean __recyclerViewLocalVar_stackFromEnd = false;
            int __absSeekBarLocalVar_thumbOffset = 0;
            int __textViewDrawableLocalVar_drawableBottomId = 0;
            boolean __progressLocalVar_indeterminateOnly = false;
            int __textViewLocalVar_textFontWeight = -1;
            boolean __motionLayoutLocalVar_applyMotionScene = true;
            boolean __recyclerViewLocalVar_fastScrollEnabled = false;
            int __progressLocalVar_mMinWidth = 24;
            int __constrainLocalVar_e2e = -1;
            int __progressLocalVar_max = -1;
            int __spinnerLocalVar_spinnerMode = 1;
            int __relativeLocalVar_layout_toRightOf = 0;
            int __recyclerViewLocalVar_managerOrientation = 1;
            int __constrainLocalVar_layout_constraintGuide_begin = -1;
            String __textViewLocalVar_text = null;
            int __relativeLocalVar_layout_alignBottom = 0;
            float __constrainLocalVar_constraintWidth_percent = 1.0F;
            int __recyclerViewLocalVar_fastScrollHorizontalTrackDrawable = 0;
            int __relativeLocalVar_layout_below = 0;
            int __constrainLocalVar_layout_constraintCircleRadius = 0;
            int __progressLocalVar_progress = -1;
            boolean __relativeLocalVar_layout_alignParentTop = false;
            int __textViewDrawableLocalVar_drawableTintModeEnum = -1;
            int __spinnerLocalVar_dropDownSelector = 0;
            int __relativeLocalVar_tag = 0;
            int __constrainLocalVar_layout_constraintCircleAngle = 0;
            int __textViewAutoSizeLocalVar_autoSizeMaxTextSize = -1;
            int __textViewAutoSizeLocalVar_autoSizeStepGranularity = 1;
            int __constrainLocalVar_layout_constraintCircle = -1;
            int __spinnerLocalVar_entriesId = 0;
            int __paddingLocalVar_paddingBottom = -2147483648;
            int __constrainLocalVar_layout_constraintVertical_chainStyle = 0;
            android.graphics.Typeface __textViewLocalVar_typeface = null;
            int __tabLayoutLocalVar_tabPaddingTop = 0;
            int __absSeekBarLocalVar_thumb = 0;
            int __constrainLocalVar_r2l = -1;
            int __constrainLocalVar_goneMarginBottom = -1;
            int __constrainLocalVar_r2r = -1;
            float __textViewLocalVar_lineSpacingExtra = 0.0F;
            int __textViewDrawableLocalVar_drawableRightId = 0;
            int __expandableListVieLocalVar_childIndicator = 0;
            int __constrainLocalVar_b2t = -1;
            int __relativeLocalVar_layout_above = 0;
            int __expandableListVieLocalVar_indicatorTag = 0;
            boolean __relativeLocalVar_layout_alignParentBottom = false;
            int __spinnerLocalVar_dropDownWidth = -2;
            int __relativeLocalVar_layout_alignRight = 0;
            int __paddingLocalVar_paddingTop = -2147483648;
            int __motionLayoutLocalVar_layoutDescription = -1;
            int __constrainLocalVar_editor_absoluteY = -1;
            int __textViewDrawableLocalVar_drawableTopId = 0;
            int __constrainLocalVar_editor_absoluteX = -1;
            int __compoundButtonLocalVar_button = 0;
            int __expandableListVieLocalVar_childIndicatorLeft = -1;
            float __constrainLocalVar_constraintHeight_percent = 1.0F;
            int __progressLocalVar_secondaryProgress = -1;
            int __textViewLocalVar_bufferType = -1;
            int __textViewAutoSizeLocalVar_autoSizeTextType = 0;
            int __groupLocalVar_visibility = 0;
            int __constrainLocalVar_b2b = -1;
            int __textViewDrawableLocalVar_drawableLeftId = 0;
            boolean __tabLayoutLocalVar_tabPaddingTopSet = false;
            int __expandableListVieLocalVar_indicatorRight = 0;
            int __textViewAutoSizeLocalVar_autoSizeMinTextSize = -1;
            boolean __imageViewLocalVar_adjustViewBounds = false;
            int __appCompatImageViewLocalVar_tint = 0;
            boolean __relativeLocalVar_layout_alignParentLeft = false;
            int __constrainLocalVar_s2s = -1;
            float __constrainLocalVar_layout_constraintVertical_bias = 0.5F;
            boolean __tabLayoutLocalVar_tabPaddingStartSet = false;
            int __marginLocalVar_marginBottom = -2147483648;
            int __marginLocalVar_marginTag = 0;
            boolean __viewLocalVar_layoutHeightSet = false;
            int __paddingLocalVar_paddingRight = -2147483648;
            int __compatTextViewLocalVar_appFontFamilyId = 0;
            boolean __relativeLocalVar_layout_alignParentStart = false;
            int __appCompatImageViewLocalVar_tintMode = -1;
            int __relativeLocalVar_layout_toStartOf = 0;
            int __constrainLocalVar_s2e = -1;
            boolean __progressLocalVar_indeterminate = false;
            float __constrainLocalVar_layout_constraintHorizontal_weight = -1.0F;
            int __relativeLocalVar_layout_alignLeft = 0;
            int __progressLocalVar_mMaxHeight = 48;
            int __paddingLocalVar_paddingTag = 0;
            int __textViewLocalVar_textStyle = 0;
            int __constrainLocalVar_goneMarginEnd = -1;
            int __expandableListVieLocalVar_childIndicatorEnd = -1;
            int __recyclerViewLocalVar_fastScrollHorizontalThumbDrawable = 0;
            int __tabLayoutLocalVar_tabPaddingStart = 0;
            float __textViewLocalVar_lineSpacingMultiplier = 1.0F;
            int __marginLocalVar_marginRight = -2147483648;
            int __compatTextViewLocalVar_androidFontFamilyId = 0;
            boolean __relativeLocalVar_layout_alignParentRight = false;
            int __tabLayoutLocalVar_tabPaddingBottom = 0;
            int __relativeLocalVar_layout_toEndOf = 0;
            int __constrainLocalVar_layout_constraintWidth_min = 0;
            int __textViewDrawableLocalVar_drawableTag = 0;
            boolean __relativeLocalVar_alignWithParent = false;
            int __relativeLocalVar_layout_alignEnd = 0;
            int __constrainLocalVar_goneMarginRight = -1;
            android.content.res.ColorStateList __tabLayoutLocalVar_colorStateList = null;
            int __constrainLocalVar_goneMarginLeft = -1;
            boolean __viewLocalVar_layoutWidthSet = false;
            int __progressLocalVar_min = -1;
            android.content.res.ColorStateList __compoundButtonLocalVar_buttonTint = null;
            int __spinnerLocalVar_popupBackground = 0;
            int __progressLocalVar_mMinHeight = 24;
            int __relativeLocalVar_layout_alignBaseline = 0;
            int __appCompatImageViewLocalVar_src = 0;
            int __marginLocalVar_marginLeft = -2147483648;
            boolean __relativeLocalVar_layout_alignParentEnd = false;
            int __constrainLocalVar_layout_constraintWidth_max = 0;
            int __textViewAutoSizeLocalVar_autoSizePresetSizes = -1;
            int __constrainLocalVar_t2t = -1;
            int __appCompatImageViewLocalVar_srcCompat = 0;
            int __spinnerLocalVar_popupTheme = 0;
            int __tabLayoutLocalVar_tabPaddingEnd = 0;
            int __textViewLocalVar_inputTypeFlag = -1;
            int __constrainLocalVar_l2r = -1;
            String __textViewLocalVar_fontFamily = null;
            int __constrainLocalVar_goneMarginStart = -1;
            String __compatTextViewLocalVar_appFontFamily = null;
            float __constrainLocalVar_layout_constraintHorizontal_bias = 0.5F;
            int __marginLocalVar_marginTop = -2147483648;
            int __constrainLocalVar_orientation = -1;
            boolean __relativeLocalVar_layout_centerHorizontal = false;
            int __relativeLocalVar_layout_alignStart = 0;
            int __expandableListVieLocalVar_indicatorLeft = 0;
            int __constrainLocalVar_l2l = -1;
            int __recyclerViewLocalVar_spanCount = 1;
            int __constrainLocalVar_t2b = -1;
            int __tabLayoutLocalVar_tabContentStart = 0;
            int __constrainLocalVar_layout_constraintHorizontal_chainStyle = 0;
            boolean __tabLayoutLocalVar_tabPaddingBottomSet = false;
            int __relativeLocalVar_layout_toLeftOf = 0;
            float __textViewLocalVar_textSize = -1.0F;
            int __constrainLocalVar_layout_constraintHeight_max = 0;
            int __constrainLocalVar_layout_constraintHeight_default = 0;
            int __imageViewLocalVar_tintMode = -1;
            int __expandableListVieLocalVar_childIndicatorStart = -1;
            int __viewLocalVar_layoutWidth = 0;
            int __constrainLocalVar_layout_constraintWidth_default = 0;
            String __constrainLocalVar_layout_constraintDimensionRatio = null;
            int __textViewLocalVar_maxLength = -1;
            int __textViewLocalVar_typefaceIndex = -1;
            int __recyclerViewLocalVar_fastScrollVerticalTrackDrawable = 0;
            int __constrainLocalVar_layout_constraintHeight_min = 0;
            {
                android.widget.TextView textView = (android.widget.TextView) ___view_0;
                java.lang.String text;
                text = "i am layout-V28 from module";

                __textViewLocalVar_text = text;
            };
            {
                android.widget.TextView textView = (android.widget.TextView) ___view_0;
                int gravityFlag;
                gravityFlag = 17;

                textView.setGravity(gravityFlag);
            };
            {
                android.view.View view = (android.view.View) ___view_0;
                int width;
                width = -1;

                __viewLocalVar_layoutWidth = width;
                __viewLocalVar_layoutWidthSet = true;
            };
            {
                android.view.View view = (android.view.View) ___view_0;
                int height;
                height = -1;

                __viewLocalVar_layoutHeight = height;
                __viewLocalVar_layoutHeightSet = true;
            };
            {
                android.view.View view = ___view_0;

                android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
                if (lp instanceof android.view.ViewGroup.MarginLayoutParams) {
                    if (__viewLocalVar_layoutWidthSet) {
                        lp.width = __viewLocalVar_layoutWidth;
                    }
                    if (__viewLocalVar_layoutHeightSet) {
                        lp.height = __viewLocalVar_layoutHeight;
                    }
                    android.view.ViewGroup.MarginLayoutParams mLp = (android.view.ViewGroup.MarginLayoutParams)lp;
                    if (__marginLocalVar_marginLeft == -2147483648) {
                        __marginLocalVar_marginLeft = mLp.leftMargin;
                    }
                    if (__marginLocalVar_marginTop == -2147483648) {
                        __marginLocalVar_marginTop = mLp.topMargin;
                    }
                    if (__marginLocalVar_marginRight == -2147483648) {
                        __marginLocalVar_marginRight = mLp.rightMargin;
                    }
                    if (__marginLocalVar_marginBottom == -2147483648) {
                        __marginLocalVar_marginBottom = mLp.bottomMargin;
                    }
                    if (android.os.Build.VERSION.SDK_INT > 17) {
                        if ((__marginLocalVar_marginTag & com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_START_SET) != 0) {
                            mLp.setMarginStart(__marginLocalVar_marginLeft);
                        }
                        if ((__marginLocalVar_marginTag & com.qxml.gen.view.attr.ViewMarginAttr.MARGIN_END_SET) != 0) {
                            mLp.setMarginEnd(__marginLocalVar_marginRight);
                        }
                    }
                    mLp.setMargins(__marginLocalVar_marginLeft, __marginLocalVar_marginTop, __marginLocalVar_marginRight, __marginLocalVar_marginBottom);
                }
            };
            {
                android.widget.TextView textView = ___view_0;

                if (__textViewLocalVar_maxLength >= 0) {
                    android.text.InputFilter[] filters = new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(__textViewLocalVar_maxLength)};
                    textView.setFilters(filters);
                }
                boolean isPassword = __textViewLocalVar_password;
                if (__textViewLocalVar_password) {
                    textView.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
                }
                if (__textViewLocalVar_inputTypeFlag != -1) {
                    textView.setInputType(__textViewLocalVar_inputTypeFlag);
                    final int variation = __textViewLocalVar_inputTypeFlag & (android.view.inputmethod.EditorInfo.TYPE_MASK_CLASS | android.view.inputmethod.EditorInfo.TYPE_MASK_VARIATION);
                    final boolean passwordInputType = variation == (android.view.inputmethod.EditorInfo.TYPE_CLASS_TEXT | android.view.inputmethod.EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    final boolean webPasswordInputType = variation == (android.view.inputmethod.EditorInfo.TYPE_CLASS_TEXT | android.view.inputmethod.EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                    final boolean numberPasswordInputType = variation == (android.view.inputmethod.EditorInfo.TYPE_CLASS_NUMBER | android.view.inputmethod.EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
                    isPassword = isPassword || passwordInputType || webPasswordInputType || numberPasswordInputType;
                }
                if (__textViewLocalVar_typeface != null || __textViewLocalVar_fontFamily != null || __textViewLocalVar_typefaceIndex != -1) {
                    if (__textViewLocalVar_typeface == null && __textViewLocalVar_fontFamily != null) {
                        __textViewLocalVar_typeface = android.graphics.Typeface.create(__textViewLocalVar_fontFamily, android.graphics.Typeface.NORMAL);
                    } else if (__textViewLocalVar_typeface != null) {
                    } else {
                        switch (__textViewLocalVar_typefaceIndex) {
                            case 1:
                            {
                                __textViewLocalVar_typeface = android.graphics.Typeface.SANS_SERIF;
                                break;
                            }

                            case 2:
                            {
                                __textViewLocalVar_typeface = android.graphics.Typeface.SERIF;
                                break;
                            }

                            case 3:
                            {
                                __textViewLocalVar_typeface = android.graphics.Typeface.MONOSPACE;
                                break;
                            }

                            default:
                            {
                                __textViewLocalVar_typeface = null;
                            }

                        }
                    }
                    if (android.os.Build.VERSION.SDK_INT >= 28 && __textViewLocalVar_textFontWeight >= 0) {
                        __textViewLocalVar_textFontWeight = java.lang.Math.min(1000, __textViewLocalVar_textFontWeight);
                        final boolean italic = (__textViewLocalVar_textStyle & android.graphics.Typeface.ITALIC) != 0;
                        textView.setTypeface(android.graphics.Typeface.create(__textViewLocalVar_typeface, __textViewLocalVar_textFontWeight, italic));
                    } else {
                        textView.setTypeface(__textViewLocalVar_typeface, __textViewLocalVar_textStyle);
                    }
                } else {
                    if (isPassword) {
                        textView.setTypeface(null, __textViewLocalVar_textStyle);
                    }
                }
                if (__textViewLocalVar_textSize != -1.0F) {
                    textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, (float)((int)(__textViewLocalVar_textSize + 0.5F)));
                }
                if (__textViewLocalVar_autoLinkFlag != -1) {
                    textView.setAutoLinkMask(__textViewLocalVar_autoLinkFlag);
                }
                if (__textViewLocalVar_bufferType != -1) {
                    android.widget.TextView.BufferType bufferType;
                    if (__textViewLocalVar_bufferType == 0) {
                        bufferType = android.widget.TextView.BufferType.NORMAL;
                    } else if (__textViewLocalVar_bufferType == 1) {
                        bufferType = android.widget.TextView.BufferType.SPANNABLE;
                    } else if (__textViewLocalVar_bufferType == 2) {
                        bufferType = android.widget.TextView.BufferType.EDITABLE;
                    } else {
                        bufferType = android.widget.TextView.BufferType.EDITABLE;
                    }
                    textView.setText(__textViewLocalVar_text, bufferType);
                } else {
                    if (__textViewLocalVar_text != null) {
                        textView.setText(__textViewLocalVar_text);
                    }
                }
            };
            com.qxml.helper.QxmlHelper.showQxmlDebug(___view_0);
            if(__root != null && __attachTo) {
                __root.addView(___view_0, -1, ___root_layout_param);
            }
            if(__attachTo) {
                return __root;
            }
            return ___view_0;
        }
        return __layoutInflater.inflate(com.qxml.androidx_test.R.layout.layout_type_version_test, __root, __attachTo);
    }


}
