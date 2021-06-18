package com.qxml.qxml_support.gen.toolbar;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qxml.gen.viewGroup.ViewGroupGen;
import com.qxml.qxml_support.RS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = Toolbar.class, layoutParamInit = "new android.support.v7.widget.Toolbar.LayoutParams(-2, -2)")
public class ToolbarGen extends ViewGroupGen {

    public static final class $$ToolbarLocalVar {
        public boolean marginsSet = false;
        public boolean marginStartSet = false;
        public boolean marginEndSet = false;
        public boolean marginTopSet = false;
        public boolean marginBottomSet = false;

        public int contentInsetStart = -2147483648;
        public int contentInsetEnd = -2147483648;
        public int contentInsetLeft = 0;
        public int contentInsetRight = 0;
    }

    @LocalVar
    public $$ToolbarLocalVar __toolbarLocalVar = new $$ToolbarLocalVar();

    @Attr(RS.attr.titleTextAppearance)
    public void toolbarTitleTextAppearance(Toolbar toolbar, int res) {
        toolbar.setTitleTextAppearance(__context, res);
    }

    @Attr(RS.attr.subtitleTextAppearance)
    public void toolbarSubtitleTextAppearance(Toolbar toolbar, int res) {
        toolbar.setSubtitleTextAppearance(__context, res);
    }

    @Override
    public void viewGravity(View view, int gravityFlag) {
        com.qxml.qxml_support.gen.toolbar.ToolbarHelper.setMGravity((android.support.v7.widget.Toolbar) view, gravityFlag);
    }

    @Attr(RS.attr.buttonGravity)
    public void toolbarButtonGravity(Toolbar toolbar, int buttonGravity) {
        com.qxml.qxml_support.gen.toolbar.ToolbarHelper.setMButtonGravityField(toolbar, buttonGravity);
    }

    @Attr(RS.attr.titleMargin)
    public void toolbarTitleMargin(Toolbar toolbar, int titleMargin) {
        if (!__toolbarLocalVar.marginsSet) {
            if (!__toolbarLocalVar.marginStartSet) {
                toolbar.setTitleMarginStart(titleMargin);
            }
            if (!__toolbarLocalVar.marginTopSet) {
                toolbar.setTitleMarginTop(titleMargin);
            }
            if (!__toolbarLocalVar.marginEndSet) {
                toolbar.setTitleMarginEnd(titleMargin);
            }
            if (!__toolbarLocalVar.marginBottomSet) {
                toolbar.setTitleMarginBottom(titleMargin);
            }
        }
    }

    @Attr(RS.attr.titleMargins)
    public void toolbarTitleMargins(Toolbar toolbar, int titleMargins) {
        __toolbarLocalVar.marginsSet = true;
        if (!__toolbarLocalVar.marginStartSet) {
            toolbar.setTitleMarginStart(titleMargins);
        }
        if (!__toolbarLocalVar.marginTopSet) {
            toolbar.setTitleMarginTop(titleMargins);
        }
        if (!__toolbarLocalVar.marginEndSet) {
            toolbar.setTitleMarginEnd(titleMargins);
        }
        if (!__toolbarLocalVar.marginBottomSet) {
            toolbar.setTitleMarginBottom(titleMargins);
        }
    }

    @Attr(RS.attr.titleMarginStart)
    public void toolbarTitleMarginStart(Toolbar toolbar, int titleMarginStart) {
        __toolbarLocalVar.marginStartSet = true;
        toolbar.setTitleMarginStart(titleMarginStart);
    }

    @Attr(RS.attr.titleMarginEnd)
    public void toolbarTitleMarginEnd(Toolbar toolbar, int titleMarginEnd) {
        __toolbarLocalVar.marginEndSet = true;
        toolbar.setTitleMarginEnd(titleMarginEnd);
    }

    @Attr(RS.attr.titleMarginTop)
    public void toolbarTitleMarginTop(Toolbar toolbar, int titleMarginTop) {
        __toolbarLocalVar.marginTopSet = true;
        toolbar.setTitleMarginTop(titleMarginTop);
    }

    @Attr(RS.attr.titleMarginBottom)
    public void toolbarTitleMarginsBottom(Toolbar toolbar, int titleMarginBottom) {
        __toolbarLocalVar.marginBottomSet = true;
        toolbar.setTitleMarginBottom(titleMarginBottom);
    }

    @Attr(RS.attr.maxButtonHeight)
    public void toolbarMaxButtonHeight(Toolbar toolbar, int maxButtonHeight) {
        com.qxml.qxml_support.gen.toolbar.ToolbarHelper.setMMaxButtonHeightField(toolbar, maxButtonHeight);
    }

    @Attr(RS.attr.contentInsetStart)
    public void toolbarContentInsetStart(Toolbar toolbar, int contentInsetStart) {
        __toolbarLocalVar.contentInsetStart = contentInsetStart;
    }

    @Attr(RS.attr.contentInsetEnd)
    public void toolbarContentInsetEnd(Toolbar toolbar, int contentInsetEnd) {
        __toolbarLocalVar.contentInsetEnd = contentInsetEnd;
    }

    @Attr(RS.attr.contentInsetLeft)
    public void toolbarContentInsetLeft(Toolbar toolbar, int contentInsetLeft) {
        __toolbarLocalVar.contentInsetLeft = contentInsetLeft;
    }

    @Attr(RS.attr.contentInsetRight)
    public void toolbarContentInsetRight(Toolbar toolbar, int contentInsetRight) {
        __toolbarLocalVar.contentInsetRight = contentInsetRight;
    }

    @Attr(RS.attr.contentInsetStartWithNavigation)
    public void toolbarContentInsetStartWithNavigation(Toolbar toolbar, int contentInsetStartWithNavigation) {
        toolbar.setContentInsetStartWithNavigation(contentInsetStartWithNavigation);
    }

    @Attr(RS.attr.contentInsetEndWithActions)
    public void toolbarContentInsetEndWithActions(Toolbar toolbar, int contentInsetEndWithActions) {
        toolbar.setContentInsetEndWithActions(contentInsetEndWithActions);
    }

    @Attr(RS.attr.collapseIcon)
    public void toolbarCollapseIcon(Toolbar toolbar, ValueInfo collapseIcon) {
        com.qxml.qxml_support.gen.toolbar.ToolbarHelper.setMCollapseIconField(toolbar, collapseIcon.getReferenceDrawable(__context, ___resources));
    }

    @Attr(RS.attr.collapseContentDescription)
    public void toolbarCollapseContentDescription(Toolbar toolbar, String collapseContentDescription) {
        com.qxml.qxml_support.gen.toolbar.ToolbarHelper.setMCollapseDescriptionField(toolbar, collapseContentDescription);
    }

    @Attr(RS.attr.title)
    public void toolbarTitle(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
    }

    @Attr(RS.attr.subtitle)
    public void toolbarSubTitle(Toolbar toolbar, String subtitle) {
        toolbar.setSubtitle(subtitle);
    }

    @Attr(RS.attr.popupTheme)
    public void toolbarPopupTheme(Toolbar toolbar, int popupTheme) {
        toolbar.setPopupTheme(popupTheme);
    }

    @Attr(RS.attr.navigationIcon)
    public void toolbarNavigationIcon(Toolbar toolbar, int navigationIcon) {
        toolbar.setNavigationIcon(navigationIcon);
    }

    @Attr(RS.attr.navigationContentDescription)
    public void toolbarNavigationContentDescription(Toolbar toolbar, String navigationContentDescription) {
        toolbar.setNavigationContentDescription(navigationContentDescription);
    }

    @Attr(RS.attr.logo)
    public void toolbarLogo(Toolbar toolbar, int logo) {
        toolbar.setLogo(logo);
    }

    @Attr(RS.attr.logoDescription)
    public void toolbarLogoDescription(Toolbar toolbar, String logoDescription) {
        toolbar.setLogoDescription(logoDescription);
    }

    @Attr(RS.attr.titleTextColor)
    public void toolbarTitleTextColor(Toolbar toolbar, ValueInfo titleTextColor) {
        toolbar.setTitleTextColor(titleTextColor.colorValue);
    }

    @Attr(RS.attr.subtitleTextColor)
    public void toolbarSubtitleTextColor(Toolbar toolbar, ValueInfo subtitleTextColor) {
        toolbar.setSubtitleTextColor(subtitleTextColor.colorValue);
    }

    @OnEnd({RS.attr.contentInsetStart, RS.attr.contentInsetEnd, RS.attr.contentInsetLeft, RS.attr.contentInsetRight})
    public void onToolbarContentInsetEnd(Toolbar toolbar) {
        if (__toolbarLocalVar.contentInsetRight != 0 || __toolbarLocalVar.contentInsetLeft != 0) {
            toolbar.setContentInsetsAbsolute(__toolbarLocalVar.contentInsetLeft, __toolbarLocalVar.contentInsetRight);
        }
        if (__toolbarLocalVar.contentInsetStart != -2147483648 || __toolbarLocalVar.contentInsetEnd != -2147483648) {
            toolbar.setContentInsetsRelative(__toolbarLocalVar.contentInsetStart, __toolbarLocalVar.contentInsetEnd);
        }
    }

}
