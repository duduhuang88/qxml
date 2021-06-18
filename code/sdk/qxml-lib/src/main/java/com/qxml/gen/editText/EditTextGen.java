package com.qxml.gen.editText;

import android.annotation.SuppressLint;
import android.widget.EditText;
import android.widget.TextView;

import com.qxml.gen.textView.TextViewGen;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(EditText.class)
public class EditTextGen extends TextViewGen {

    @SuppressLint("RestrictedApi")
    @Override
    public void textViewTextAllCaps(TextView textView, boolean textAllCaps) {
        textView.setAllCaps(textAllCaps);
    }
}
