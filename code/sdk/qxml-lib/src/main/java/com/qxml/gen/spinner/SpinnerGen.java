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
    }

    @LocalVar
    public $$SpinnerLocalVariable __spinnerLocalVar = new $$SpinnerLocalVariable();

    @Attr(AndroidRS.attr.popupTheme)
    public void onSpinnerPopupTheme(Spinner spinner, int popupTheme) {
        com.qxml.gen.spinner.SpinnerHelper.setMPopupContextField(spinner, __context, popupTheme);
    }

    @Attr(AndroidRS.attr.spinnerMode)
    public void onSpinnerSpinnerMode(Spinner spinner, int spinnerMode) {
        __spinnerLocalVar.spinnerMode = spinnerMode;
        if (__spinnerLocalVar.spinnerMode == 0) {
            com.qxml.gen.spinner.SpinnerHelper.setPopDialog(spinner);
        }
    }

    @Attr(AndroidRS.attr.dropDownWidth)
    public void onSpinnerDropDownWidth(Spinner spinner, int dropDownWidth) {
        if (__spinnerLocalVar.spinnerMode != 0 && dropDownWidth != -2 && android.os.Build.VERSION.SDK_INT >= 16) {
            spinner.setDropDownWidth(dropDownWidth);
        }
    }

    @Attr(AndroidRS.attr.popupBackground)
    public void onSpinnerPopupBackground(Spinner spinner, int popupBackground) {
        if (__spinnerLocalVar.spinnerMode != 0 && popupBackground != 0 && android.os.Build.VERSION.SDK_INT >= 16) {
            spinner.setPopupBackgroundResource(popupBackground);
        }
    }

    @Attr(AndroidRS.attr.dropDownSelector)
    public void onSpinnerDropDownSelector(Spinner spinner, int dropDownSelector) {
        if (__spinnerLocalVar.spinnerMode != 0 && dropDownSelector != 0) {
            com.qxml.gen.spinner.SpinnerHelper.setAttr(spinner, __context, dropDownSelector);
        }
    }

    @Attr(AndroidRS.attr.prompt)
    public void onSpinnerPrompt(Spinner spinner, String prompt) {
        spinner.setPrompt(prompt);
    }

    @Override
    public void onAbsSpinnerEntries(AbsSpinner absSpinner, int entriesId) {
        com.qxml.gen.spinner.SpinnerHelper.setDefaultAdapter(absSpinner, __context, entriesId);
    }

}
