package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;

public interface ViewFocusAttr {

    @Attr(AndroidRS.attr.focusable)
    default void viewFocusable(View view, ValueInfo focusable) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            if (focusable.isBoolean()) {
                view.setFocusable(focusable.booleanValue ? android.view.View.FOCUSABLE : android.view.View.NOT_FOCUSABLE);
            } else {
                view.setFocusable(focusable.intValue);
            }
        } else {
            if (focusable.isBoolean()) {
                view.setFocusable(focusable.booleanValue);
            } else if (focusable.isEnum()) {
                view.setFocusable(focusable.intValue != 0);
            }
        }
    }

    @Attr(AndroidRS.attr.focusableInTouchMode)
    default void viewFocusableInTouchMode(View view, boolean focusableInTouchMode) {
        view.setFocusableInTouchMode(focusableInTouchMode);
    }

    @Attr(AndroidRS.attr.focusedByDefault)
    default void viewFocusedByDefault(View view, boolean focusedByDefault) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            view.setFocusedByDefault(focusedByDefault);
        }
    }

    @Attr(AndroidRS.attr.nextFocusDown)
    default void viewNextFocusDown(View view, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ID) {
            view.setNextFocusDownId(valueInfo.resourceId);
        }
    }

    @Attr(AndroidRS.attr.nextFocusLeft)
    default void viewNextFocusLeft(View view, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ID) {
            view.setNextFocusLeftId(valueInfo.resourceId);
        }
    }


    @Attr(AndroidRS.attr.nextFocusRight)
    default void viewNextFocusRight(View view, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ID) {
            view.setNextFocusRightId(valueInfo.resourceId);
        }
    }


    @Attr(AndroidRS.attr.nextFocusUp)
    default void viewNextFocusUp(View view, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ID) {
            view.setNextFocusUpId(valueInfo.resourceId);
        }
    }
    
}
