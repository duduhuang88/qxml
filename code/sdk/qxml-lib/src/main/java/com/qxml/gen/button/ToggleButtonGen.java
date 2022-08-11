package com.qxml.gen.button;

import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.qxml.AndroidRS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(ToggleButton.class)
public class ToggleButtonGen extends CompoundButtonGen {

    public static class $$ToggleButtonLocalVariable {
        public boolean checked = false;
    }

    @LocalVar
    public $$ToggleButtonLocalVariable __toggleButtonLocalVar = new $$ToggleButtonLocalVariable();

    @Attr(AndroidRS.attr.textOn)
    public void onToggleButtonTextOn(ToggleButton toggleButton, String textOn) {
        toggleButton.setTextOn(textOn);
    }

    @Attr(AndroidRS.attr.textOff)
    public void onToggleButtonTextOff(ToggleButton toggleButton, String textOff) {
        toggleButton.setTextOff(textOff);
    }

    @Attr(AndroidRS.attr.disabledAlpha)
    public void onToggleButtonDisabledAlpha(ToggleButton toggleButton, float disabledAlpha) {
        com.qxml.gen.button.ToggleButtonHelper.setDisabledAlpha(toggleButton, disabledAlpha);
    }

    @Override
    public void onCompoundButtonChecked(CompoundButton compoundButton, boolean checked) {
        __toggleButtonLocalVar.checked = checked;
    }

    @OnEnd({AndroidRS.attr.checked})
    public void onToggleButtonCheckedEnd(ToggleButton toggleButton) {
        toggleButton.setChecked(__toggleButtonLocalVar.checked);
    }

}
