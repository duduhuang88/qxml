package com.qxml.gen.spinner;

import android.widget.AbsSpinner;
import android.widget.Spinner;

import com.qxml.AndroidRS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(Spinner.class)
public class SpinnerGen extends AbsSpinnerGen {

    public static class $$SpinnerLocalVariable {
        public int spinnerMode = 1;
        public int popupTheme = 0;
        public String prompt = null;
        public int dropDownSelector = 0;
        public int popupBackground = 0;
        public int dropDownWidth = -2;
        public int entriesId = 0;
    }

    @LocalVar
    public $$SpinnerLocalVariable __spinnerLocalVar = new $$SpinnerLocalVariable();

    @Override
    public void onAbsSpinnerEntries(AbsSpinner absSpinner, int entriesId) {
        __spinnerLocalVar.entriesId = entriesId;
    }

    @Attr(AndroidRS.attr.popupTheme)
    public void onSpinnerPopupTheme(Spinner spinner, int popupTheme) {
        __spinnerLocalVar.popupTheme = popupTheme;
    }

    @Attr(AndroidRS.attr.spinnerMode)
    public void onSpinnerSpinnerMode(Spinner spinner, int spinnerMode) {
        __spinnerLocalVar.spinnerMode = spinnerMode;
    }

    @Attr(AndroidRS.attr.prompt)
    public void onSpinnerPrompt(Spinner spinner, String prompt) {
        __spinnerLocalVar.prompt = prompt;
    }

    @Attr(AndroidRS.attr.dropDownSelector)
    public void onSpinnerDropDownSelector(Spinner spinner, int dropDownSelector) {
        __spinnerLocalVar.dropDownSelector = dropDownSelector;
    }

    @Attr(AndroidRS.attr.popupBackground)
    public void onSpinnerPopupBackground(Spinner spinner, int popupBackground) {
        __spinnerLocalVar.popupBackground = popupBackground;
    }

    @Attr(AndroidRS.attr.dropDownWidth)
    public void onSpinnerDropDownWidth(Spinner spinner, int dropDownWidth) {
        __spinnerLocalVar.dropDownWidth = dropDownWidth;
    }

    @OnEnd({AndroidRS.attr.entries, AndroidRS.attr.popupTheme, AndroidRS.attr.spinnerMode, AndroidRS.attr.prompt
            , AndroidRS.attr.dropDownSelector, AndroidRS.attr.popupBackground, AndroidRS.attr.dropDownWidth})
    public void onSpinnerThemeEnd(Spinner spinner) {
        if (__spinnerLocalVar.popupTheme != 0) {
            com.qxml.gen.spinner.SpinnerHelper.setMPopupContextField(spinner, __context, __spinnerLocalVar.popupTheme);
        }
        if (__spinnerLocalVar.spinnerMode == 0) {
            com.qxml.gen.spinner.SpinnerHelper.setPopDialog(spinner);
        } else {
            if (__spinnerLocalVar.dropDownWidth != -2 && android.os.Build.VERSION.SDK_INT >= 16) {
                spinner.setDropDownWidth(__spinnerLocalVar.dropDownWidth);
            }
            if (__spinnerLocalVar.popupBackground != 0 && android.os.Build.VERSION.SDK_INT >= 16) {
                spinner.setPopupBackgroundResource(__spinnerLocalVar.popupBackground);
            }
            if (__spinnerLocalVar.dropDownSelector != 0) {
                com.qxml.gen.spinner.SpinnerHelper.setAttr(spinner, __context, __spinnerLocalVar.dropDownSelector);
            }
        }

        if (__spinnerLocalVar.prompt != null) {
            spinner.setPrompt(__spinnerLocalVar.prompt);
        }

        if (__spinnerLocalVar.entriesId != 0) {
            com.qxml.gen.spinner.SpinnerHelper.setDefaultAdapter(spinner, __context, __spinnerLocalVar.entriesId);
        }
    }

}
