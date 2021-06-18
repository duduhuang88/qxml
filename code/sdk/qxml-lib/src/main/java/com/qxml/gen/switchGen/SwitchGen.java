package com.qxml.gen.switchGen;

import android.os.Build;
import android.widget.Switch;

import com.qxml.AndroidRS;
import com.qxml.gen.button.CompoundButtonGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(Switch.class)
public class SwitchGen extends CompoundButtonGen {

    @Attr(AndroidRS.attr.textOn)
    public void onSwitchTextOn(Switch s, String textOn) {
        s.setTextOn(textOn);
    }

    @Attr(AndroidRS.attr.textOff)
    public void onSwitchTextOff(Switch s, String textOff) {
        s.setTextOn(textOff);
    }

    @Attr(AndroidRS.attr.switchMinWidth)
    public void onSwitchSwitchMinWidth(Switch s, int switchMinWidth) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            s.setSwitchMinWidth(switchMinWidth);
        }
    }

    @Attr(AndroidRS.attr.switchPadding)
    public void onSwitchSwitchPadding(Switch s, int switchPadding) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            s.setSwitchPadding(switchPadding);
        }
    }

    @Attr(AndroidRS.attr.switchTextAppearance)
    public void onSwitchSwitchTextAppearance(Switch s, int switchTextAppearance) {
        s.setSwitchTextAppearance(__context, switchTextAppearance);
    }

    @Attr(AndroidRS.attr.thumb)
    public void onSwitchThumb(Switch s, int thumb) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            s.setThumbResource(thumb);
        }
    }

    @Attr(AndroidRS.attr.thumbTextPadding)
    public void onSwitchThumbTextPadding(Switch s, int thumbTextPadding) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            s.setThumbTextPadding(thumbTextPadding);
        }
    }

    @Attr(AndroidRS.attr.track)
    public void onSwitchTrack(Switch s, int track) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            s.setTrackResource(track);
        }
    }

}
