package com.qxml.gen.view.attr;

import android.animation.AnimatorInflater;
import android.os.Build;
import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;

public interface ViewAnimatorAttr {

    @Attr(AndroidRS.attr.rotation)
    default void viewRotation(View view, float rotation) {
        view.setRotation(rotation);
    }

    @Attr(AndroidRS.attr.rotationX)
    default void viewRotationX(View view, float rotationX) {
        view.setRotationX(rotationX);
    }

    @Attr(AndroidRS.attr.rotationY)
    default void viewRotationY(View view, float rotationY) {
        view.setRotationY(rotationY);
    }

    @Attr(AndroidRS.attr.scaleX)
    default void viewScaleX(View view, float scaleX) {
        view.setScaleX(scaleX);
    }

    @Attr(AndroidRS.attr.scaleY)
    default void viewScaleY(View view, float scaleY) {
        view.setScaleY(scaleY);
    }

    @Attr(AndroidRS.attr.stateListAnimator)
    default void viewStateListAnimator(View view, ValueInfo valueInfo) {
        if (valueInfo.isReference() && valueInfo.referenceType == com.qxml.constant.ValueType.REFERENCE_ANIMATOR){
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                view.setStateListAnimator(AnimatorInflater.loadStateListAnimator(view.getContext(), valueInfo.resourceId));
            }
        }
    }

    @Attr(AndroidRS.attr.transitionName)
    default void viewTransitionName(View view, String name) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(name);
        }
    }

    @Attr(AndroidRS.attr.transformPivotX)
    default void viewTransformPivotX(View view, float transformPivotX) {
        view.setPivotX(transformPivotX);
    }

    @Attr(AndroidRS.attr.transformPivotY)
    default void viewTransformPivotY(View view, float transformPivotY) {
        view.setPivotY(transformPivotY);
    }

    @Attr(AndroidRS.attr.translationX)
    default void viewTranslationX(View view, float translationX) {
        view.setTranslationX(translationX);
    }

    @Attr(AndroidRS.attr.translationY)
    default void viewTranslationY(View view, float translationY) {
        view.setTranslationY(translationY);
    }

    @Attr(AndroidRS.attr.translationZ)
    default void viewTranslationZ(View view, float translationZ) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setTranslationZ(translationZ);
        }
    }

    @Attr(AndroidRS.attr.visibility)
    default void viewVisibility(View view, int visibility) {
        view.setVisibility(visibility == 0 ? android.view.View.VISIBLE : (visibility == 1 ? android.view.View.INVISIBLE : android.view.View.GONE));
    }

    @Attr(AndroidRS.attr.elevation)
    default void viewElevation(View view, float elevation) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setElevation(elevation);
        }
    }
}
