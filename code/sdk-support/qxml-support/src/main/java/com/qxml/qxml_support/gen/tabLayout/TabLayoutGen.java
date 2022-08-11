package com.qxml.qxml_support.gen.tabLayout;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;

import com.qxml.gen.scrollView.HorizontalScrollViewGen;
import com.qxml.qxml_support.RS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(TabLayout.class)
public class TabLayoutGen extends HorizontalScrollViewGen {

    public static class $$TabLayoutLocalVariable {
        public int tabPaddingStart = 0;
        public int tabPaddingTop = 0;
        public int tabPaddingEnd = 0;
        public int tabPaddingBottom = 0;
        public int tabMode = 1;
        public int tabContentStart = 0;
        public int tabSelectedTextColorValue = Integer.MAX_VALUE;
        public android.content.res.ColorStateList colorStateList = null;
    }

    public @LocalVar
    $$TabLayoutLocalVariable __tabLayoutLocalVar = new $$TabLayoutLocalVariable();

    @Attr(RS.attr.tabIndicatorHeight)
    public void onTabLayoutTabIndicatorHeight(TabLayout tabLayout, int tabIndicatorHeight) {
        tabLayout.setSelectedTabIndicatorHeight(tabIndicatorHeight);
    }

    @Attr(RS.attr.tabIndicatorColor)
    public void onTabLayoutTabIndicatorColor(TabLayout tabLayout, ValueInfo valueInfo) {
        if (valueInfo.isColor()) {
            tabLayout.setSelectedTabIndicatorColor(valueInfo.colorValue);
        } else {
            tabLayout.setSelectedTabIndicatorColor(android.support.v4.content.ContextCompat.getColor(__context, valueInfo.resourceId));
        }
    }

    @Attr(RS.attr.tabIndicator)
    public void onTabLayoutTabIndicator(TabLayout tabLayout, int tabIndicator) {
        tabLayout.setSelectedTabIndicator(tabIndicator);
    }

    @Attr(RS.attr.tabIndicatorGravity)
    public void onTabLayoutTabIndicatorGravity(TabLayout tabLayout, int tabIndicatorGravity) {
        tabLayout.setSelectedTabIndicatorGravity(tabIndicatorGravity);
    }

    @Attr(RS.attr.tabIndicatorFullWidth)
    public void onTabLayoutTabIndicatorFullWidth(TabLayout tabLayout, boolean tabIndicatorFullWidth) {
        tabLayout.setTabIndicatorFullWidth(tabIndicatorFullWidth);
    }

    @Attr(RS.attr.tabBackground)
    public void onTabLayoutTabBackground(TabLayout tabLayout, int tabBackground) {
        com.qxml.gen.tabLayout.TabLayoutHelper.setTabBackgroundResId(tabLayout, tabBackground);
    }

    @Attr(RS.attr.tabGravity)
    public void onTabLayoutTabGravity(TabLayout tabLayout, int tabGravity) {
        tabLayout.setTabGravity(tabGravity);
    }

    @Attr(RS.attr.tabMinWidth)
    public void onTabLayoutTabMinWidth(TabLayout tabLayout, int tabMinWidth) {
        com.qxml.gen.tabLayout.TabLayoutHelper.setRequestedTabMinWidthField(tabLayout, tabMinWidth);
    }

    @Attr(RS.attr.tabMaxWidth)
    public void onTabLayoutTabMaxWidth(TabLayout tabLayout, int tabMaxWidth) {
        com.qxml.gen.tabLayout.TabLayoutHelper.setRequestedTabMaxWidthField(tabLayout, tabMaxWidth);
    }

    @SuppressLint({"RestrictedApi", "ResourceType"})
    @Attr(RS.attr.tabTextAppearance)
    public void onTabLayoutTabTextAppearance(TabLayout tabLayout, int styleId) {
        {
            android.content.res.TypedArray typedArray = __context.obtainStyledAttributes(styleId, new int[]{android.R.attr.textSize, android.R.attr.textColor});
            float textSize = typedArray.getDimensionPixelSize(0, 0);
            com.qxml.gen.tabLayout.TabLayoutHelper.setTabTextSizeField(tabLayout, textSize);
            if (__tabLayoutLocalVar.colorStateList == null) {
                __tabLayoutLocalVar.colorStateList = android.support.design.resources.MaterialResources.getColorStateList(__context, typedArray, 1);
            }
            typedArray.recycle();
        }
    }

    @Attr(RS.attr.tabTextColor)
    public void onTabLayoutTabTextColor(TabLayout tabLayout, ValueInfo tabTextColor) {
        if (tabTextColor.isColor()) {
            __tabLayoutLocalVar.colorStateList = android.content.res.ColorStateList.valueOf(tabTextColor.colorValue);
        } else {
            __tabLayoutLocalVar.colorStateList = android.support.v7.content.res.AppCompatResources.getColorStateList(__context, tabTextColor.resourceId);
        }
    }

    @Attr(RS.attr.tabSelectedTextColor)
    public void onTabLayoutTabSelectedTextColor(TabLayout tabLayout, ValueInfo tabSelectedTextColor) {
        if (tabSelectedTextColor.isColor()) {
            __tabLayoutLocalVar.tabSelectedTextColorValue = tabSelectedTextColor.colorValue;
        } else {
            __tabLayoutLocalVar.tabSelectedTextColorValue = android.support.v4.content.ContextCompat.getColor(__context, tabSelectedTextColor.resourceId);
        }
    }

    @Attr(RS.attr.tabIconTint)
    public void onTabLayoutTabIconTint(TabLayout tabLayout, ValueInfo tabIconTint) {
        if (tabIconTint.isColor()) {
            tabLayout.setTabIconTint(android.content.res.ColorStateList.valueOf(tabIconTint.colorValue));
        } else {
            tabLayout.setTabIconTint(android.support.v7.content.res.AppCompatResources.getColorStateList(__context, tabIconTint.resourceId));
        }
    }

    @Attr(RS.attr.tabIconTintMode)
    public void onTabLayoutTabIconTintMode(TabLayout tabLayout, int tabIconTintMode) {
        com.qxml.gen.tabLayout.TabLayoutHelper.setTabIconTintModeFieldField(tabLayout, tabIconTintMode);
    }

    @Attr(RS.attr.tabRippleColor)
    public void onTabLayoutTabRippleColor(TabLayout tabLayout, ValueInfo tabRippleColor) {
        if (tabRippleColor.isColor()) {
            tabLayout.setTabRippleColor(android.content.res.ColorStateList.valueOf(tabRippleColor.colorValue));
        } else {
            tabLayout.setTabRippleColor(android.support.v7.content.res.AppCompatResources.getColorStateList(__context, tabRippleColor.resourceId));
        }
    }

    @Attr(RS.attr.tabIndicatorAnimationDuration)
    public void onTabLayoutTabIndicatorAnimationDuration(TabLayout tabLayout, int tabIndicatorAnimationDuration) {
        com.qxml.gen.tabLayout.TabLayoutHelper.setTabIndicatorAnimationDurationField(tabLayout, tabIndicatorAnimationDuration);
    }

    @Attr(RS.attr.tabInlineLabel)
    public void onTabLayoutTabIndicatorAnimationDuration(TabLayout tabLayout, boolean tabInlineLabel) {
        tabLayout.setInlineLabel(tabInlineLabel);
    }

    @Attr(RS.attr.tabUnboundedRipple)
    public void onTabLayoutTabUnboundedRipple(TabLayout tabLayout, boolean tabUnboundedRipple) {
        tabLayout.setUnboundedRipple(tabUnboundedRipple);
    }

    @Attr(RS.attr.tabPadding)
    public void onTabLayoutTabPadding(TabLayout tabLayout, int tabPadding) {
        __tabLayoutLocalVar.tabPaddingStart = tabPadding;
        __tabLayoutLocalVar.tabPaddingTop = tabPadding;
        __tabLayoutLocalVar.tabPaddingEnd = tabPadding;
        __tabLayoutLocalVar.tabPaddingBottom = tabPadding;
    }

    @Attr(RS.attr.tabPaddingStart)
    public void onTabLayoutTabPaddingStart(TabLayout tabLayout, int tabPaddingStart) {
        __tabLayoutLocalVar.tabPaddingStart = tabPaddingStart;
    }

    @Attr(RS.attr.tabPaddingTop)
    public void onTabLayoutTabPaddingTop(TabLayout tabLayout, int tabPaddingTop) {
        __tabLayoutLocalVar.tabPaddingTop = tabPaddingTop;
    }

    @Attr(RS.attr.tabPaddingEnd)
    public void onTabLayoutTabPaddingEnd(TabLayout tabLayout, int tabPaddingEnd) {
        __tabLayoutLocalVar.tabPaddingEnd = tabPaddingEnd;
    }

    @Attr(RS.attr.tabPaddingBottom)
    public void onTabLayoutTabPaddingBottom(TabLayout tabLayout, int tabPaddingBottom) {
        __tabLayoutLocalVar.tabPaddingBottom = tabPaddingBottom;
    }

    @Attr(RS.attr.tabContentStart)
    public void onTabLayoutTabContentStart(TabLayout tabLayout, int tabContentStart) {
        __tabLayoutLocalVar.tabContentStart = tabContentStart;
    }

    @Attr(RS.attr.tabMode)
    public void onTabLayoutTabMode(TabLayout tabLayout, int tabMode) {
        __tabLayoutLocalVar.tabMode = tabMode;
    }

    @OnEnd({RS.attr.tabPaddingStart, RS.attr.tabPaddingTop, RS.attr.tabPaddingEnd, RS.attr.tabPaddingBottom, RS.attr.tabPadding
            , RS.attr.tabMode, RS.attr.tabContentStart})
    public void onTabLayoutTabModeEnd(TabLayout tabLayout) {
        if (__tabLayoutLocalVar.tabPaddingStart != 0) com.qxml.gen.tabLayout.TabLayoutHelper.setTabPaddingStartField(tabLayout, __tabLayoutLocalVar.tabPaddingStart);
        if (__tabLayoutLocalVar.tabPaddingTop != 0) com.qxml.gen.tabLayout.TabLayoutHelper.setTabPaddingTopField(tabLayout, __tabLayoutLocalVar.tabPaddingTop);
        if (__tabLayoutLocalVar.tabPaddingEnd != 0) com.qxml.gen.tabLayout.TabLayoutHelper.setTabPaddingEndField(tabLayout, __tabLayoutLocalVar.tabPaddingEnd);
        if (__tabLayoutLocalVar.tabPaddingBottom != 0) com.qxml.gen.tabLayout.TabLayoutHelper.setTabPaddingBottomField(tabLayout, __tabLayoutLocalVar.tabPaddingBottom);
        if (__tabLayoutLocalVar.tabContentStart != 0) com.qxml.gen.tabLayout.TabLayoutHelper.setContentInsetStart(tabLayout, __tabLayoutLocalVar.tabContentStart);
        tabLayout.setTabMode(__tabLayoutLocalVar.tabMode);
    }

    @OnEnd({RS.attr.tabTextColor, RS.attr.tabTextAppearance, RS.attr.tabSelectedTextColor})
    public void onTabLayoutTabTextColorEnd(TabLayout tabLayout) {
        if (__tabLayoutLocalVar.colorStateList != null) {
            tabLayout.setTabTextColors(__tabLayoutLocalVar.colorStateList);
            if (__tabLayoutLocalVar.tabSelectedTextColorValue != Integer.MAX_VALUE) {
                tabLayout.setTabTextColors(__tabLayoutLocalVar.colorStateList.getDefaultColor(), __tabLayoutLocalVar.tabSelectedTextColorValue);
            }
        }
    }

}
