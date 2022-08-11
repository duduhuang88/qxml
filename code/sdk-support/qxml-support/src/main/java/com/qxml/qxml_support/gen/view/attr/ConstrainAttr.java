package com.qxml.qxml_support.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.view.attr.ViewCommonAttr;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;

public interface ConstrainAttr extends ViewLocalVar {

    class $$ConstrainLocalVariable {
        public android.support.constraint.ConstraintLayout.LayoutParams constraintLp = null;
    }

    @LocalVar
    $$ConstrainLocalVariable __constrainLocalVar = new $$ConstrainLocalVariable();

    @Attr(value = RS.attr.layout_constraintTop_toTopOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintTopToTopOf(View view, int who) {
        __constrainLocalVar.constraintLp.topToTop = who;
    }

    @Attr(value = RS.attr.layout_constraintTop_toBottomOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintTopToBottomOf(View view, int who) {
        __constrainLocalVar.constraintLp.topToBottom = who;
    }

    @Attr(value = RS.attr.layout_constraintBottom_toBottomOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintBottomToBottomOf(View view, int who) {
        __constrainLocalVar.constraintLp.bottomToBottom = who;
    }

    @Attr(value = RS.attr.layout_constraintBottom_toTopOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintBottomToTopOf(View view, int who) {
        __constrainLocalVar.constraintLp.bottomToTop = who;
    }

    @Attr(value = RS.attr.layout_constraintLeft_toLeftOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintLeftToLeftOf(View view, int who) {
        __constrainLocalVar.constraintLp.leftToLeft = who;
    }

    @Attr(value = RS.attr.layout_constraintLeft_toRightOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintLeftToRightOf(View view, int who) {
        __constrainLocalVar.constraintLp.leftToRight = who;
    }

    @Attr(value = RS.attr.layout_constraintRight_toRightOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintRightToRightOf(View view, int who) {
        __constrainLocalVar.constraintLp.rightToRight = who;
    }

    @Attr(value = RS.attr.layout_constraintRight_toLeftOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintRightToLeftOf(View view, int who) {
        __constrainLocalVar.constraintLp.rightToLeft = who;
    }

    @Attr(value = RS.attr.layout_constraintStart_toEndOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintStartToEndOf(View view, int who) {
        __constrainLocalVar.constraintLp.startToEnd = who;
    }

    @Attr(value = RS.attr.layout_constraintStart_toStartOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintStartToStartOf(View view, int who) {
        __constrainLocalVar.constraintLp.startToStart = who;
    }

    @Attr(value = RS.attr.layout_constraintEnd_toStartOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintEndToStartOf(View view, int who) {
        __constrainLocalVar.constraintLp.endToStart = who;
    }

    @Attr(value = RS.attr.layout_constraintEnd_toEndOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintEndToEndOf(View view, int who) {
        __constrainLocalVar.constraintLp.endToEnd = who;
    }

    @Attr(value = RS.attr.layout_constraintBaseline_toBaselineOf, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintBaselineToBaselineOf(View view, int who) {
        __constrainLocalVar.constraintLp.baselineToBaseline = who;
    }

    //----------------------------------------------------------

    @Attr(value = RS.attr.layout_constraintWidth_percent, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintWidth_percent(View view, float layout_constraintWidth_percent) {
        __constrainLocalVar.constraintLp.matchConstraintPercentWidth = layout_constraintWidth_percent;
    }

    @Attr(value = RS.attr.layout_constraintHeight_percent, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutConstraintHeight_percent(View view, float layout_constraintHeight_percent) {
        __constrainLocalVar.constraintLp.matchConstraintPercentHeight = layout_constraintHeight_percent;
    }

    @Attr(value = RS.attr.layout_editor_absoluteX, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutEditor_absoluteX(View view, int layout_editor_absoluteX) {
        __constrainLocalVar.constraintLp.editorAbsoluteX = layout_editor_absoluteX;
    }

    @Attr(value = RS.attr.layout_editor_absoluteY, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutEditor_absoluteY(View view, int layout_editor_absoluteY) {
        __constrainLocalVar.constraintLp.editorAbsoluteY = layout_editor_absoluteY;
    }

    @Attr(value = RS.attr.layout_goneMarginBottom, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutGoneMarginBottom(View view, int layout_goneMarginBottom) {
        __constrainLocalVar.constraintLp.goneBottomMargin = layout_goneMarginBottom;
    }

    @Attr(value = RS.attr.layout_goneMarginTop, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutGoneMarginTop(View view, int layout_goneMarginTop) {
        __constrainLocalVar.constraintLp.goneTopMargin = layout_goneMarginTop;
    }

    @Attr(value = RS.attr.layout_goneMarginLeft, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutGoneMarginLeft(View view, int layout_goneMarginLeft) {
        __constrainLocalVar.constraintLp.goneLeftMargin = layout_goneMarginLeft;
    }

    @Attr(value = RS.attr.layout_goneMarginRight, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutGoneMarginRight(View view, int layout_goneMarginRight) {
        __constrainLocalVar.constraintLp.goneRightMargin = layout_goneMarginRight;
    }

    @Attr(value = RS.attr.layout_goneMarginStart, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutGoneMarginStart(View view, int layout_goneMarginStart) {
        __constrainLocalVar.constraintLp.goneStartMargin = layout_goneMarginStart;
    }

    @Attr(value = RS.attr.layout_goneMarginEnd, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutGoneMarginEnd(View view, int layout_goneMarginEnd) {
        __constrainLocalVar.constraintLp.goneEndMargin = layout_goneMarginEnd;
    }

    //------------------------------------------------------------------------

    @Attr(value = RS.attr.layout_constraintWidth_min, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintWidth_min(View view, int layout_constraintWidth_min) {
        __constrainLocalVar.constraintLp.matchConstraintMinWidth = layout_constraintWidth_min;
    }

    @Attr(value = RS.attr.layout_constraintHeight_min, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintHeight_min(View view, int layout_constraintHeight_min) {
        __constrainLocalVar.constraintLp.matchConstraintMinHeight = layout_constraintHeight_min;
    }

    @Attr(value = RS.attr.layout_constraintWidth_max, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintWidth_max(View view, int layout_constraintWidth_max) {
        __constrainLocalVar.constraintLp.matchConstraintMaxWidth = layout_constraintWidth_max;
    }

    @Attr(value = RS.attr.layout_constraintHeight_max, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintHeight_max(View view, int layout_constraintHeight_max) {
        __constrainLocalVar.constraintLp.matchConstraintMaxHeight = layout_constraintHeight_max;
    }

    @Attr(value = RS.attr.layout_constraintWidth_default, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintWidth_default(View view, int layout_constraintWidth_default) {
        __constrainLocalVar.constraintLp.matchConstraintDefaultWidth = layout_constraintWidth_default;
    }

    @Attr(value = RS.attr.layout_constraintHeight_default, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintHeight_default(View view, int layout_constraintHeight_default) {
        __constrainLocalVar.constraintLp.matchConstraintDefaultHeight = layout_constraintHeight_default;
    }
    //----------------------------------------------------------------------

    @Attr(value = RS.attr.layout_constraintVertical_weight, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintVertical_weight(View view, float layout_constraintVertical_weight) {
        __constrainLocalVar.constraintLp.verticalWeight = layout_constraintVertical_weight;
    }

    @Attr(value = RS.attr.layout_constraintHorizontal_weight, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintHorizontal_weight(View view, float layout_constraintHorizontal_weight) {
        __constrainLocalVar.constraintLp.horizontalWeight = layout_constraintHorizontal_weight;
    }

    @Attr(value = RS.attr.layout_constraintVertical_chainStyle, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintVertical_chainStyle(View view, int layout_constraintVertical_chainStyle) {
        __constrainLocalVar.constraintLp.verticalChainStyle = layout_constraintVertical_chainStyle;
    }

    @Attr(value = RS.attr.layout_constraintHorizontal_chainStyle, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintHorizontal_chainStyle(View view, int layout_constraintHorizontal_chainStyle) {
        __constrainLocalVar.constraintLp.horizontalChainStyle = layout_constraintHorizontal_chainStyle;
    }

    @Attr(value = RS.attr.layout_constraintVertical_bias, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintVertical_bias(View view, float layout_constraintVertical_bias) {
        __constrainLocalVar.constraintLp.verticalBias = layout_constraintVertical_bias;
    }

    @Attr(value = RS.attr.layout_constraintHorizontal_bias, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintHorizontal_bias(View view, float layout_constraintHorizontal_bias) {
        __constrainLocalVar.constraintLp.horizontalBias = layout_constraintHorizontal_bias;
    }
    //----------------------------------------------------------------------

    @Attr(value = RS.attr.layout_constraintGuide_percent, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintGuide_percent(View view, float layout_constraintGuide_percent) {
        __constrainLocalVar.constraintLp.guidePercent = layout_constraintGuide_percent;
    }

    @Attr(value = RS.attr.layout_constraintGuide_begin, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintGuide_begin(View view, int layout_constraintGuide_begin) {
        __constrainLocalVar.constraintLp.guideBegin = layout_constraintGuide_begin;
    }

    @Attr(value = RS.attr.layout_constraintGuide_end, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintGuide_end(View view, int layout_constraintGuide_end) {
        __constrainLocalVar.constraintLp.guideEnd = layout_constraintGuide_end;
    }

    @Attr(value = RS.attr.layout_constraintDimensionRatio, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintDimensionRatio(View view, String layout_constraintDimensionRatio) {
        __constrainLocalVar.constraintLp.dimensionRatio = layout_constraintDimensionRatio;
    }

    @Attr(value = RS.attr.layout_constraintCircleRadius, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintCircleRadius(View view, int layout_constraintCircleRadius) {
        __constrainLocalVar.constraintLp.circleRadius = layout_constraintCircleRadius;
    }

    @Attr(value = RS.attr.layout_constraintCircleAngle, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintCircleAngle(View view, int layout_constraintCircleAngle) {
        __constrainLocalVar.constraintLp.circleAngle = layout_constraintCircleAngle;
    }

    @Attr(value = RS.attr.layout_constraintCircle, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constraintCircle(View view, int layout_constraintCircle) {
        __constrainLocalVar.constraintLp.circleConstraint = layout_constraintCircle;
    }
    //----------------------------------------------------------------------------------
    @Attr(value = RS.attr.layout_constrainedWidth, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constrainedWidth(View view, boolean layout_constrainedWidth) {
        __constrainLocalVar.constraintLp.constrainedWidth = layout_constrainedWidth;
    }

    @Attr(value = RS.attr.layout_constrainedHeight, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayout_constrainedHeight(View view, boolean layout_constrainedHeight) {
        __constrainLocalVar.constraintLp.constrainedHeight = layout_constrainedHeight;
    }

    @Attr(value = AndroidRS.attr.orientation, requiredCondition = "__constrainLocalVar_constraintLp != null")
    default void viewLayoutOrientation(View view, int orientation) {
        __constrainLocalVar.constraintLp.orientation = orientation;
    }
}
