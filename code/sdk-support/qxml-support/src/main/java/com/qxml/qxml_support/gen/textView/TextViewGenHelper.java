package com.qxml.qxml_support.gen.textView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.TextView;

public class TextViewGenHelper {

    @SuppressLint("RestrictedApi")
    public static void setCompatFontFamily(Context __context, TypedValue ___typedValue, int finalFontFamilyId, String finalFontFamily, TextView textView, int textStyle, final int textFontWeight) {

        Typeface typeface = null;
        final java.lang.ref.WeakReference<TextView> textViewWeak = new java.lang.ref.WeakReference<TextView>(textView);
        ObjWrap ok = new ObjWrap();
        android.support.v4.content.res.ResourcesCompat.FontCallback replyCallback = new android.support.v4.content.res.ResourcesCompat.FontCallback() {
            public void onFontRetrieved(Typeface typeface) {
                if (ok.getTypefaceDelay) {
                    TextView textView = (TextView)textViewWeak.get();
                    if (textView != null) {
                        textView.setTypeface(typeface, textStyle);
                    }
                }
            }

            public void onFontRetrievalFailed(int reason) {
            }
        };

        try {
            typeface = android.support.v4.content.res.ResourcesCompat.getFont(__context, finalFontFamilyId, ___typedValue, textStyle, replyCallback);
            ok.getTypefaceDelay = typeface == null;
        } catch (android.content.res.Resources.NotFoundException | UnsupportedOperationException var7) {
        }

        if (typeface == null) {
            if (finalFontFamily != null) {
                typeface = Typeface.create(finalFontFamily, textStyle);
                textView.setTypeface(typeface, textStyle);
            }
        }
    }

}
