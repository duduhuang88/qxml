package com.qxml.gen.spinner;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.Spinner;

import com.qxml.tools.ReflectUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class SpinnerHelper {

    private static final Field mPopupContextField = ReflectUtils.getDeclaredFieldOrNull(Spinner.class, "mPopupContext");
    private static final Field mPopupField = ReflectUtils.getDeclaredFieldOrNull(Spinner.class, "mPopup");
    private static final Class<?> classDialogPopup = ReflectUtils.forName("android.widget.Spinner$DialogPopup");

    private static final Constructor<?> CONSTRUCTOR_DialogPopup = ReflectUtils.getDeclaredConstructorOrNull(classDialogPopup, android.widget.Spinner.class);

    public static void setDefaultAdapter(AbsSpinner spinner, Context context, int entriesId) {
        final CharSequence[] entries = context.getResources().getTextArray(entriesId);
        final ArrayAdapter<CharSequence> adapter = new android.widget.ArrayAdapter<>(context, android.R.layout.simple_spinner_item, entries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static void setMPopupContextField(Spinner spinner, Context context, int res) {
        if (mPopupContextField != null) {
            try {
                mPopupContextField.set(spinner, new ContextThemeWrapper(context, res));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setPopDialog(Spinner spinner) {
        try {
            if (mPopupField != null && CONSTRUCTOR_DialogPopup != null) {
                Object dialogPop = CONSTRUCTOR_DialogPopup.newInstance(spinner);
                mPopupField.set(spinner, dialogPop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAttr(Spinner spinner, Context context, int dropDownSelector) {
        try {
            if (mPopupField != null) {
                Object mPopup = mPopupField.get(spinner);
                if (mPopup instanceof ListPopupWindow) {
                    ((ListPopupWindow) mPopup).setListSelector(com.qxml.tools.DrawableTools.getDrawable(context, context.getResources(), dropDownSelector));
                }/* else if (mPopup instanceof android.support.v7.widget.ListPopupWindow) {
                    ((android.support.v7.widget.ListPopupWindow) mPopup).setListSelector(com.qxml.tools.DrawableTools.getDrawable(context, context.getResources(), dropDownSelector));
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
