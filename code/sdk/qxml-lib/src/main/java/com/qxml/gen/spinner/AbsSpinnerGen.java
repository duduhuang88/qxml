package com.qxml.gen.spinner;

import android.widget.AbsSpinner;

import com.qxml.AndroidRS;
import com.qxml.gen.viewGroup.ViewGroupGen;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(AbsSpinner.class)
public class AbsSpinnerGen extends ViewGroupGen {

    @Attr(AndroidRS.attr.entries)
    public void onAbsSpinnerEntries(AbsSpinner absSpinner, int entriesId) {
        com.qxml.gen.spinner.SpinnerHelper.setDefaultAdapter(absSpinner, __context, entriesId);
    }

}
