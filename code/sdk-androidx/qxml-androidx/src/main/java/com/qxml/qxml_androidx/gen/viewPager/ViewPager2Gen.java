package com.qxml.qxml_androidx.gen.viewPager;

import androidx.viewpager2.widget.ViewPager2;

import com.qxml.gen.viewGroup.ViewGroupGen;
import com.qxml.qxml_androidx.AndroidRS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(ViewPager2.class)
public class ViewPager2Gen extends ViewGroupGen {

    @Attr(AndroidRS.attr.orientation)
    public void viewPager2Orientation(ViewPager2 viewPager2, int orientation) {
        viewPager2.setOrientation(orientation);
    }

}
