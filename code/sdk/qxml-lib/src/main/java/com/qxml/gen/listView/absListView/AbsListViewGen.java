package com.qxml.gen.listView.absListView;

import android.widget.AbsListView;

import com.qxml.AndroidRS;
import com.qxml.gen.viewGroup.ViewGroupGen;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = AbsListView.class, layoutParamInit = "new android.widget.AbsListView.LayoutParams(-1, -2, 0)")
public class AbsListViewGen extends ViewGroupGen {

    @Attr(AndroidRS.attr.listSelector)
    public void absListViewListSelector(AbsListView absListView, ValueInfo valueInfo) {
        if (valueInfo.isColor()) {
            absListView.setSelector(new android.graphics.drawable.ColorDrawable(valueInfo.colorValue));
        } else {
            absListView.setSelector(valueInfo.resourceId);
        }
    }

    @Attr(AndroidRS.attr.drawSelectorOnTop)
    public void absListViewDrawSelectorOnTop(AbsListView absListView, boolean drawSelectorOnTop) {
        absListView.setDrawSelectorOnTop(drawSelectorOnTop);
    }

    @Attr(AndroidRS.attr.stackFromBottom)
    public void absListViewStackFromBottom(AbsListView absListView, boolean stackFromBottom) {
        absListView.setStackFromBottom(stackFromBottom);
    }

    @Attr(AndroidRS.attr.scrollingCache)
    public void absListViewScrollingCache(AbsListView absListView, boolean scrollingCache) {
        absListView.setScrollingCacheEnabled(scrollingCache);
    }

    @Attr(AndroidRS.attr.textFilterEnabled)
    public void absListViewTextFilterEnabled(AbsListView absListView, boolean textFilterEnabled) {
        absListView.setTextFilterEnabled(textFilterEnabled);
    }

    @Attr(AndroidRS.attr.transcriptMode)
    public void absListViewTranscriptMode(AbsListView absListView, int transcriptMode) {
        absListView.setTranscriptMode(transcriptMode);
    }

    @Attr(AndroidRS.attr.cacheColorHint)
    public void absListViewCacheColorHint(AbsListView absListView, ValueInfo cacheColorHint) {
        absListView.setCacheColorHint(cacheColorHint.colorValue);
    }

    @Attr(AndroidRS.attr.fastScrollEnabled)
    public void absListViewFastScrollEnabled(AbsListView absListView, boolean fastScrollEnabled) {
        absListView.setFastScrollEnabled(fastScrollEnabled);
    }

    @Attr(AndroidRS.attr.smoothScrollbar)
    public void absListViewSmoothScrollbar(AbsListView absListView, boolean smoothScrollbar) {
        absListView.setSmoothScrollbarEnabled(smoothScrollbar);
    }

    @Attr(AndroidRS.attr.choiceMode)
    public void absListViewChoiceMode(AbsListView absListView, int choiceMode) {
        absListView.setChoiceMode(choiceMode);
    }
}
