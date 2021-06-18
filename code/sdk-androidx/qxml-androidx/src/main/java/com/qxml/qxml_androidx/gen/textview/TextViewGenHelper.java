package com.qxml.qxml_androidx.gen.textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.TypedValue;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class TextViewGenHelper {

    @SuppressLint("RestrictedApi")
    public static void setCompatFontFamily(Context __context, TypedValue ___typedValue, int finalFontFamilyId, String finalFontFamily, TextView textView, int textStyle, final int textFontWeight) {

        Typeface typeface = null;
        final java.lang.ref.WeakReference<TextView> textViewWeak = new java.lang.ref.WeakReference<TextView>(textView);
        ObjWrap ok = new ObjWrap();
        if (textFontWeight != -1) {
            textStyle = Typeface.NORMAL | (textStyle & Typeface.ITALIC);
        }
        final int finalTextStyle = textStyle;
        androidx.core.content.res.ResourcesCompat.FontCallback replyCallback = new androidx.core.content.res.ResourcesCompat.FontCallback() {
            public void onFontRetrieved(@NotNull Typeface typeface) {
                if (ok.getTypefaceDelay) {
                    ok.fontType = typeface;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        if (textFontWeight != -1) {
                            typeface = Typeface.create(typeface, textFontWeight,
                                    (finalTextStyle & Typeface.ITALIC) != 0);
                        }
                    }
                    TextView textView = (TextView)textViewWeak.get();
                    if (textView != null) {
                        textView.setTypeface(typeface, finalTextStyle);
                    }
                }
            }

            public void onFontRetrievalFailed(int reason) {
            }
        };

        try {
            typeface = androidx.core.content.res.ResourcesCompat.getFont(__context, finalFontFamilyId, ___typedValue, textStyle, replyCallback);
            ok.getTypefaceDelay = typeface == null;
        } catch (android.content.res.Resources.NotFoundException | UnsupportedOperationException var7) {
        }

        if (typeface != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                    && textFontWeight != -1) {
                textView.setTypeface(Typeface.create(
                        Typeface.create(finalFontFamily, Typeface.NORMAL), textFontWeight,
                        (finalTextStyle & Typeface.ITALIC) != 0));
            } else {
                textView.setTypeface(typeface);
            }
        }
        if (ok.fontType == null) {
            if (finalFontFamily != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                        && textFontWeight != -1) {
                    textView.setTypeface(Typeface.create(
                            Typeface.create(finalFontFamily, Typeface.NORMAL), textFontWeight,
                            (finalTextStyle & Typeface.ITALIC) != 0));
                } else {
                    Typeface typeface1 = Typeface.create(finalFontFamily, finalTextStyle);
                    if (textFontWeight == -1) {
                        textView.setTypeface(typeface1, finalTextStyle);
                    } else {
                        textView.setTypeface(typeface1);
                    }
                }
            }
        }
    }

}
