package com.qxml.gen.button;

import android.widget.CompoundButton;

import com.qxml.AndroidRS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(CompoundButton.class)
public class CompoundButtonGen extends ButtonGen {

    public static class $$CompoundButtonLocalVariable {
        public int button = -1;
        public android.content.res.ColorStateList buttonTint = null;
        public int buttonTintMode = -1;
    }

    @LocalVar
    public $$CompoundButtonLocalVariable __compoundButtonLocalVar = new $$CompoundButtonLocalVariable();

    @Attr(AndroidRS.attr.button)
    public void onCompoundButtonButton(CompoundButton compoundButton, int button) {
        __compoundButtonLocalVar.button = button;
    }

    @Attr(AndroidRS.attr.buttonTint)
    public void onCompoundButtonButtonTint(CompoundButton compoundButton, ValueInfo buttonTint) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            __compoundButtonLocalVar.buttonTint = buttonTint.getColorStateList(__context);
        }
    }

    @Attr(AndroidRS.attr.buttonTintMode)
    public void onCompoundButtonButtonTintMode(CompoundButton compoundButton, int buttonTintMode) {
        __compoundButtonLocalVar.buttonTintMode = buttonTintMode;
    }

    @Attr(AndroidRS.attr.checked)
    public void onCompoundButtonChecked(CompoundButton compoundButton, boolean checked) {
        compoundButton.setChecked(checked);
    }

    @OnEnd({AndroidRS.attr.button, AndroidRS.attr.buttonTint, AndroidRS.attr.buttonTintMode})
    public void onCompoundButtonTintEnd(CompoundButton compoundButton) {
        if (__compoundButtonLocalVar.button != -1) {
            if (__compoundButtonLocalVar.button != 0) {
                compoundButton.setButtonDrawable(__compoundButtonLocalVar.button);
            } else {
                compoundButton.setButtonDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        }
        if (android.os.Build.VERSION.SDK_INT >= 21 && __compoundButtonLocalVar.buttonTint != null) {
            compoundButton.setButtonTintList(__compoundButtonLocalVar.buttonTint);
        }
        if (android.os.Build.VERSION.SDK_INT >= 21 && __compoundButtonLocalVar.buttonTintMode != -1) {
            compoundButton.setButtonTintMode(com.qxml.helper.AttrHelperKt.intToMode(__compoundButtonLocalVar.buttonTintMode, null));
        }
    }

}
