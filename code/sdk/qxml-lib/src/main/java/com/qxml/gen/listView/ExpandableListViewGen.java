package com.qxml.gen.listView;

import android.widget.ExpandableListView;

import com.qxml.AndroidRS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(ExpandableListView.class)
public class ExpandableListViewGen extends ListViewGen {

    public static class $$ExpandableListVieLocalVariable {
        public int indicatorTag = 0;
        public int indicatorLeft = 0;
        public int indicatorRight = 0;
        public int childIndicatorLeft = -1;
        public int childIndicatorRight = -1;
        public int childIndicatorStart = -1;
        public int childIndicatorEnd = -1;
        public int groupIndicator = 0;
        public int childIndicator = 0;
    }

    public static final int INDICATOR_START_SET = 1;
    public static final int INDICATOR_END_SET = 1 << 1;

    @LocalVar
    public $$ExpandableListVieLocalVariable __expandableListVieLocalVar = new $$ExpandableListVieLocalVariable();

    @Attr(AndroidRS.attr.groupIndicator)
    public void expandableListViewGroupIndicator(ExpandableListView expandableListView, int groupIndicator) {
        __expandableListVieLocalVar.groupIndicator = groupIndicator;
    }

    @Attr(AndroidRS.attr.indicatorLeft)
    public void expandableListViewIndicatorLeft(ExpandableListView expandableListView, int indicatorLeft) {
        if ((__expandableListVieLocalVar.indicatorTag & com.qxml.gen.listView.ExpandableListViewGen.INDICATOR_START_SET) == 0) {
            __expandableListVieLocalVar.indicatorLeft = indicatorLeft;
        }
    }

    @Attr(AndroidRS.attr.indicatorStart)
    public void expandableListViewIndicatorStart(ExpandableListView expandableListView, int indicatorStart) {
        __expandableListVieLocalVar.indicatorTag = __expandableListVieLocalVar.indicatorTag | com.qxml.gen.listView.ExpandableListViewGen.INDICATOR_START_SET;
        __expandableListVieLocalVar.indicatorLeft = indicatorStart;
    }

    @Attr(AndroidRS.attr.indicatorRight)
    public void expandableListViewIndicatorRight(ExpandableListView expandableListView, int indicatorRight) {
        if ((__expandableListVieLocalVar.indicatorTag & com.qxml.gen.listView.ExpandableListViewGen.INDICATOR_END_SET) == 0) {
            __expandableListVieLocalVar.indicatorRight = indicatorRight;
        }
    }

    @Attr(AndroidRS.attr.childIndicator)
    public void expandableListViewChildIndicator(ExpandableListView expandableListView, int childIndicator) {
        __expandableListVieLocalVar.childIndicator = childIndicator;
    }

    @Attr(AndroidRS.attr.indicatorEnd)
    public void expandableListViewIndicatorEnd(ExpandableListView expandableListView, int indicatorEnd) {
        __expandableListVieLocalVar.indicatorTag = __expandableListVieLocalVar.indicatorTag | com.qxml.gen.listView.ExpandableListViewGen.INDICATOR_END_SET;
        __expandableListVieLocalVar.indicatorRight = indicatorEnd;
    }

    @Attr(AndroidRS.attr.childIndicatorLeft)
    public void expandableListViewChildIndicatorLeft(ExpandableListView expandableListView, int childIndicatorLeft) {
        __expandableListVieLocalVar.childIndicatorLeft = childIndicatorLeft;
    }

    @Attr(AndroidRS.attr.childIndicatorStart)
    public void expandableListViewChildIndicatorStart(ExpandableListView expandableListView, int childIndicatorStart) {
        __expandableListVieLocalVar.childIndicatorStart = childIndicatorStart;
    }

    @Attr(AndroidRS.attr.childIndicatorRight)
    public void expandableListViewChildIndicatorRight(ExpandableListView expandableListView, int childIndicatorRight) {
        __expandableListVieLocalVar.childIndicatorRight = childIndicatorRight;
    }

    @Attr(AndroidRS.attr.childIndicatorEnd)
    public void expandableListViewChildIndicatorEnd(ExpandableListView expandableListView, int childIndicatorEnd) {
        __expandableListVieLocalVar.childIndicatorEnd = childIndicatorEnd;
    }

    @Attr(AndroidRS.attr.childDivider)
    public void expandableListViewChildDivider(ExpandableListView expandableListView, ValueInfo valueInfo) {
        expandableListView.setChildDivider(com.qxml.tools.DrawableTools.getReferenceDrawable(__context, ___resources, valueInfo));
    }

    @OnEnd({AndroidRS.attr.childIndicatorLeft, AndroidRS.attr.childIndicatorRight
            , AndroidRS.attr.childIndicatorStart, AndroidRS.attr.childIndicatorEnd
            , AndroidRS.attr.childIndicator})
    public void onExpandableListViewChildIndicatorEnd(ExpandableListView expandableListView) {
        android.graphics.drawable.Drawable childIndicatorDrawable = null;
        if (__expandableListVieLocalVar.childIndicator != 0) {
            childIndicatorDrawable = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __expandableListVieLocalVar.childIndicator);
        }
        int childIndicatorRight = __expandableListVieLocalVar.childIndicatorRight;
        if (childIndicatorDrawable != null) {
            expandableListView.setChildIndicator(childIndicatorDrawable);
            if (childIndicatorRight == 0) {
                childIndicatorRight = __expandableListVieLocalVar.childIndicatorLeft + childIndicatorDrawable.getIntrinsicWidth();
            }
        }

        if (android.os.Build.VERSION.SDK_INT >= 18) {
            expandableListView.setChildIndicatorBounds(__expandableListVieLocalVar.childIndicatorLeft, childIndicatorRight);
            expandableListView.setChildIndicatorBoundsRelative(__expandableListVieLocalVar.childIndicatorStart, __expandableListVieLocalVar.childIndicatorEnd);
        } else {
            expandableListView.setChildIndicatorBounds(__expandableListVieLocalVar.childIndicatorLeft, childIndicatorRight);
        }
    }

    @OnEnd({AndroidRS.attr.indicatorLeft, AndroidRS.attr.indicatorRight
            , AndroidRS.attr.indicatorStart, AndroidRS.attr.indicatorEnd
            , AndroidRS.attr.groupIndicator})
    public void onExpandableListViewIndicatorEnd(ExpandableListView expandableListView) {
        android.graphics.drawable.Drawable groupIndicatorDrawable = null;
        if (__expandableListVieLocalVar.groupIndicator != 0) {
            groupIndicatorDrawable = com.qxml.tools.DrawableTools.getDrawable(__context, ___resources, __expandableListVieLocalVar.groupIndicator);
        }
        int groupIndicatorRight = __expandableListVieLocalVar.indicatorRight;
        if (groupIndicatorDrawable != null) {
            expandableListView.setGroupIndicator(groupIndicatorDrawable);
            if (groupIndicatorRight == 0) {
                groupIndicatorRight = __expandableListVieLocalVar.indicatorLeft + groupIndicatorDrawable.getIntrinsicWidth();
            }
        }

        if (android.os.Build.VERSION.SDK_INT >= 18) {
            expandableListView.setIndicatorBoundsRelative(__expandableListVieLocalVar.indicatorLeft, groupIndicatorRight);
        } else {
            expandableListView.setIndicatorBounds(__expandableListVieLocalVar.indicatorLeft, groupIndicatorRight);
        }
    }

}
