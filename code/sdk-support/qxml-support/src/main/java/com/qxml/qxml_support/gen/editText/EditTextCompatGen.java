package com.qxml.qxml_support.gen.editText;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.yellow.qxml_annotions.ViewReplace;

@ViewReplace
public class EditTextCompatGen extends com.qxml.gen.editText.EditTextGen {

    @SuppressLint("RestrictedApi")
    @Override
    public void textViewTextAllCaps(TextView textView, boolean textAllCaps) {
        textView.setTransformationMethod(new android.support.v7.text.AllCapsTransformationMethod(textView.getContext()));
    }
}
