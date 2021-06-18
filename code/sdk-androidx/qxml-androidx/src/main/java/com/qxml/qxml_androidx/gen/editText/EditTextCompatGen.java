package com.qxml.qxml_androidx.gen.editText;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.yellow.qxml_annotions.ViewReplace;

@ViewReplace
public class EditTextCompatGen extends com.qxml.gen.editText.EditTextGen {

    @SuppressLint("RestrictedApi")
    @Override
    public void textViewTextAllCaps(TextView textView, boolean textAllCaps) {
        textView.setTransformationMethod(new androidx.appcompat.text.AllCapsTransformationMethod(textView.getContext()));
    }
}
