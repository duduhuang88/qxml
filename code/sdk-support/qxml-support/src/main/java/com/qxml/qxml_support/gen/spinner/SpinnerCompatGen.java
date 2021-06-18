package com.qxml.qxml_support.gen.spinner;

import android.widget.Spinner;

import com.qxml.gen.spinner.SpinnerGen;
import com.yellow.qxml_annotions.ViewReplace;

@ViewReplace
public class SpinnerCompatGen extends SpinnerGen {

    @Override
    public void onSpinnerThemeEnd(Spinner spinner) {
        if (__spinnerLocalVar.popupTheme != 0) {
            com.qxml.gen.spinner.SpinnerHelper.setMPopupContextField(spinner, __context, __spinnerLocalVar.popupTheme);
        }
        if (__spinnerLocalVar.spinnerMode == 0) {
            com.qxml.gen.spinner.SpinnerHelper.setPopDialog(spinner);
        } else {
            if (__spinnerLocalVar.dropDownWidth != -2 && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                spinner.setDropDownWidth(__spinnerLocalVar.dropDownWidth);
            }
            if (__spinnerLocalVar.popupBackground != 0 && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                spinner.setPopupBackgroundResource(__spinnerLocalVar.popupBackground);
            }
            if (__spinnerLocalVar.dropDownSelector != 0) {
                //支持android.support.v7.widget.ListPopupWindow
                com.qxml.qxml_support.gen.spinner.SpinnerHelper.setAttr(spinner, __context, __spinnerLocalVar.dropDownSelector);
            }
        }

        if (__spinnerLocalVar.prompt != null) {
            spinner.setPrompt(__spinnerLocalVar.prompt);
        }

        if (__spinnerLocalVar.entriesId != 0) {
            com.qxml.qxml_support.gen.spinner.SpinnerHelper.setDefaultAdapter(spinner, __context, __spinnerLocalVar.entriesId);
        }
    }
}
