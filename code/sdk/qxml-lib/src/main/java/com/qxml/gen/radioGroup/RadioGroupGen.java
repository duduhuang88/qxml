package com.qxml.gen.radioGroup;

import android.widget.RadioGroup;

import com.qxml.AndroidRS;
import com.qxml.gen.linearLayout.LinearLayoutGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.CallOnInflateConfig;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = RadioGroup.class, layoutParamInit = "new android.widget.RadioGroup.LayoutParams(-2, -2)", callOnFinishInflate = CallOnInflateConfig.YES)
public class RadioGroupGen extends LinearLayoutGen {

    @Attr(AndroidRS.attr.checkedButton)
    public void onRadioGroupCheckedButton(RadioGroup radioGroup, int checkedButton) {
        radioGroup.check(checkedButton);
    }

}
