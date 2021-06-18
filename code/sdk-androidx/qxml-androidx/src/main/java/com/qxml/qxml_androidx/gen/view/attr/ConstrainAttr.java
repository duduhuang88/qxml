package com.qxml.qxml_androidx.gen.view.attr;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.qxml.qxml_androidx.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;

public interface ConstrainAttr extends ViewLocalVar {

    class $$ConstrainLocalVariable {
        public int t2t = -1;
        public int t2b = -1;
        public int b2b = -1;
        public int b2t = -1;
        public int l2l = -1;
        public int l2r = -1;
        public int r2r = -1;
        public int r2l = -1;
        public int s2s = -1;
        public int s2e = -1;
        public int e2e = -1;
        public int e2s = -1;
        public int baseLine2baseLine = -1;
        //----------------------------------------
        public float constraintHeight_percent = 1.0F;
        public float constraintWidth_percent = 1.0F;
        public int editor_absoluteX = -1;
        public int editor_absoluteY = -1;
        public int goneMarginBottom = -1;
        public int goneMarginEnd = -1;
        public int goneMarginLeft = -1;
        public int goneMarginRight = -1;
        public int goneMarginStart = -1;
        public int goneMarginTop = -1;
        //-----------------------------------------
        public int layout_constraintWidth_min = 0;
        public int layout_constraintHeight_min = 0;
        public int layout_constraintWidth_max = 0;
        public int layout_constraintHeight_max = 0;
        public int layout_constraintWidth_default = 0;
        public int layout_constraintHeight_default = 0;
        //------------------------------------------------
        public float layout_constraintVertical_weight = -1.0F;
        public float layout_constraintHorizontal_weight = -1.0F;
        public int layout_constraintVertical_chainStyle = 0;
        public int layout_constraintHorizontal_chainStyle = 0;
        public float layout_constraintVertical_bias = 0.5F;
        public float layout_constraintHorizontal_bias = 0.5F;
        //----------------------------------------------
        public float layout_constraintGuide_percent = -1.0F;
        public int layout_constraintGuide_begin = -1;
        public int layout_constraintGuide_end = -1;
        public String layout_constraintDimensionRatio = null;
        public int layout_constraintCircleRadius = 0;
        public int layout_constraintCircleAngle = 0;
        public int layout_constraintCircle = -1;
        //---------------------------------------------
        public boolean layout_constrainedWidth = false;
        public boolean layout_constrainedHeight = false;
        public int orientation = -1;
    }

    @LocalVar
    $$ConstrainLocalVariable __constrainLocalVar = new $$ConstrainLocalVariable();

    @Attr(RS.attr.layout_constraintTop_toTopOf)
    default void viewLayoutConstraintTopToTopOf(View view, int who) {
        __constrainLocalVar.t2t = who;
    }

    @Attr(RS.attr.layout_constraintTop_toBottomOf)
    default void viewLayoutConstraintTopToBottomOf(View view, int who) {
        __constrainLocalVar.t2b = who;
    }

    @Attr(RS.attr.layout_constraintBottom_toBottomOf)
    default void viewLayoutConstraintBottomToBottomOf(View view, int who) {
        __constrainLocalVar.b2b = who;
    }

    @Attr(RS.attr.layout_constraintBottom_toTopOf)
    default void viewLayoutConstraintBottomToTopOf(View view, int who) {
        __constrainLocalVar.b2t = who;
    }

    @Attr(RS.attr.layout_constraintLeft_toLeftOf)
    default void viewLayoutConstraintLeftToLeftOf(View view, int who) {
        __constrainLocalVar.l2l = who;
    }

    @Attr(RS.attr.layout_constraintLeft_toRightOf)
    default void viewLayoutConstraintLeftToRightOf(View view, int who) {
        __constrainLocalVar.l2r = who;
    }

    @Attr(RS.attr.layout_constraintRight_toRightOf)
    default void viewLayoutConstraintRightToRightOf(View view, int who) {
        __constrainLocalVar.r2r = who;
    }

    @Attr(RS.attr.layout_constraintRight_toLeftOf)
    default void viewLayoutConstraintRightToLeftOf(View view, int who) {
        __constrainLocalVar.r2l = who;
    }

    @Attr(RS.attr.layout_constraintStart_toEndOf)
    default void viewLayoutConstraintStartToEndOf(View view, int who) {
        __constrainLocalVar.s2e = who;
    }

    @Attr(RS.attr.layout_constraintStart_toStartOf)
    default void viewLayoutConstraintStartToStartOf(View view, int who) {
        __constrainLocalVar.s2s = who;
    }

    @Attr(RS.attr.layout_constraintEnd_toStartOf)
    default void viewLayoutConstraintEndToStartOf(View view, int who) {
        __constrainLocalVar.e2s = who;
    }

    @Attr(RS.attr.layout_constraintEnd_toEndOf)
    default void viewLayoutConstraintEndToEndOf(View view, int who) {
        __constrainLocalVar.e2e = who;
    }

    @Attr(RS.attr.layout_constraintBaseline_toBaselineOf)
    default void viewLayoutConstraintBaselineToBaselineOf(View view, int who) {
        __constrainLocalVar.baseLine2baseLine = who;
    }

    //----------------------------------------------------------

    @Attr(RS.attr.layout_constraintWidth_percent)
    default void viewLayoutConstraintWidth_percent(View view, float layout_constraintWidth_percent) {
        __constrainLocalVar.constraintWidth_percent = layout_constraintWidth_percent;
    }

    @Attr(RS.attr.layout_constraintHeight_percent)
    default void viewLayoutConstraintHeight_percent(View view, float layout_constraintHeight_percent) {
        __constrainLocalVar.constraintHeight_percent = layout_constraintHeight_percent;
    }

    @Attr(RS.attr.layout_editor_absoluteX)
    default void viewLayoutEditor_absoluteX(View view, int layout_editor_absoluteX) {
        __constrainLocalVar.editor_absoluteX = layout_editor_absoluteX;
    }

    @Attr(RS.attr.layout_editor_absoluteY)
    default void viewLayoutEditor_absoluteY(View view, int layout_editor_absoluteY) {
        __constrainLocalVar.editor_absoluteY = layout_editor_absoluteY;
    }

    @Attr(RS.attr.layout_goneMarginBottom)
    default void viewLayoutGoneMarginBottom(View view, int layout_goneMarginBottom) {
        __constrainLocalVar.goneMarginBottom = layout_goneMarginBottom;
    }

    @Attr(RS.attr.layout_goneMarginTop)
    default void viewLayoutGoneMarginTop(View view, int layout_goneMarginTop) {
        __constrainLocalVar.goneMarginTop = layout_goneMarginTop;
    }

    @Attr(RS.attr.layout_goneMarginLeft)
    default void viewLayoutGoneMarginLeft(View view, int layout_goneMarginLeft) {
        __constrainLocalVar.goneMarginLeft = layout_goneMarginLeft;
    }

    @Attr(RS.attr.layout_goneMarginRight)
    default void viewLayoutGoneMarginRight(View view, int layout_goneMarginRight) {
        __constrainLocalVar.goneMarginRight = layout_goneMarginRight;
    }

    @Attr(RS.attr.layout_goneMarginStart)
    default void viewLayoutGoneMarginStart(View view, int layout_goneMarginStart) {
        __constrainLocalVar.goneMarginStart = layout_goneMarginStart;
    }

    @Attr(RS.attr.layout_goneMarginEnd)
    default void viewLayoutGoneMarginEnd(View view, int layout_goneMarginEnd) {
        __constrainLocalVar.goneMarginEnd = layout_goneMarginEnd;
    }

    //------------------------------------------------------------------------

    @Attr(RS.attr.layout_constraintWidth_min)
    default void viewLayout_constraintWidth_min(View view, int layout_constraintWidth_min) {
        __constrainLocalVar.layout_constraintWidth_min = layout_constraintWidth_min;
    }

    @Attr(RS.attr.layout_constraintHeight_min)
    default void viewLayout_constraintHeight_min(View view, int layout_constraintHeight_min) {
        __constrainLocalVar.layout_constraintHeight_min = layout_constraintHeight_min;
    }

    @Attr(RS.attr.layout_constraintWidth_max)
    default void viewLayout_constraintWidth_max(View view, int layout_constraintWidth_max) {
        __constrainLocalVar.layout_constraintWidth_max = layout_constraintWidth_max;
    }

    @Attr(RS.attr.layout_constraintHeight_max)
    default void viewLayout_constraintHeight_max(View view, int layout_constraintHeight_max) {
        __constrainLocalVar.layout_constraintHeight_max = layout_constraintHeight_max;
    }

    @Attr(RS.attr.layout_constraintWidth_default)
    default void viewLayout_constraintWidth_default(View view, int layout_constraintWidth_default) {
        __constrainLocalVar.layout_constraintWidth_default = layout_constraintWidth_default;
    }

    @Attr(RS.attr.layout_constraintHeight_default)
    default void viewLayout_constraintHeight_default(View view, int layout_constraintHeight_default) {
        __constrainLocalVar.layout_constraintHeight_default = layout_constraintHeight_default;
    }
    //----------------------------------------------------------------------

    @Attr(RS.attr.layout_constraintVertical_weight)
    default void viewLayout_constraintVertical_weight(View view, float layout_constraintVertical_weight) {
        __constrainLocalVar.layout_constraintVertical_weight = layout_constraintVertical_weight;
    }

    @Attr(RS.attr.layout_constraintHorizontal_weight)
    default void viewLayout_constraintHorizontal_weight(View view, float layout_constraintHorizontal_weight) {
        __constrainLocalVar.layout_constraintHorizontal_weight = layout_constraintHorizontal_weight;
    }

    @Attr(RS.attr.layout_constraintVertical_chainStyle)
    default void viewLayout_constraintVertical_chainStyle(View view, int layout_constraintVertical_chainStyle) {
        __constrainLocalVar.layout_constraintVertical_chainStyle = layout_constraintVertical_chainStyle;
    }

    @Attr(RS.attr.layout_constraintHorizontal_chainStyle)
    default void viewLayout_constraintHorizontal_chainStyle(View view, int layout_constraintHorizontal_chainStyle) {
        __constrainLocalVar.layout_constraintHorizontal_chainStyle = layout_constraintHorizontal_chainStyle;
    }

    @Attr(RS.attr.layout_constraintVertical_bias)
    default void viewLayout_constraintVertical_bias(View view, float layout_constraintVertical_bias) {
        __constrainLocalVar.layout_constraintVertical_bias = layout_constraintVertical_bias;
    }

    @Attr(RS.attr.layout_constraintHorizontal_bias)
    default void viewLayout_constraintHorizontal_bias(View view, float layout_constraintHorizontal_bias) {
        __constrainLocalVar.layout_constraintHorizontal_bias = layout_constraintHorizontal_bias;
    }
    //----------------------------------------------------------------------

    @Attr(RS.attr.layout_constraintGuide_percent)
    default void viewLayout_constraintGuide_percent(View view, float layout_constraintGuide_percent) {
        __constrainLocalVar.layout_constraintGuide_percent = layout_constraintGuide_percent;
    }

    @Attr(RS.attr.layout_constraintGuide_begin)
    default void viewLayout_constraintGuide_begin(View view, int layout_constraintGuide_begin) {
        __constrainLocalVar.layout_constraintGuide_begin = layout_constraintGuide_begin;
    }

    @Attr(RS.attr.layout_constraintGuide_end)
    default void viewLayout_constraintGuide_end(View view, int layout_constraintGuide_end) {
        __constrainLocalVar.layout_constraintGuide_end = layout_constraintGuide_end;
    }

    @Attr(RS.attr.layout_constraintDimensionRatio)
    default void viewLayout_constraintDimensionRatio(View view, String layout_constraintDimensionRatio) {
        __constrainLocalVar.layout_constraintDimensionRatio = layout_constraintDimensionRatio;
    }

    @Attr(RS.attr.layout_constraintCircleRadius)
    default void viewLayout_constraintCircleRadius(View view, int layout_constraintCircleRadius) {
        __constrainLocalVar.layout_constraintCircleRadius = layout_constraintCircleRadius;
    }

    @Attr(RS.attr.layout_constraintCircleAngle)
    default void viewLayout_constraintCircleAngle(View view, int layout_constraintCircleAngle) {
        __constrainLocalVar.layout_constraintCircleAngle = layout_constraintCircleAngle;
    }

    @Attr(RS.attr.layout_constraintCircle)
    default void viewLayout_constraintCircle(View view, int layout_constraintCircle) {
        __constrainLocalVar.layout_constraintCircle = layout_constraintCircle;
    }
    //----------------------------------------------------------------------------------
    @Attr(RS.attr.layout_constrainedWidth)
    default void viewLayout_constrainedWidth(View view, boolean layout_constrainedWidth) {
        __constrainLocalVar.layout_constrainedWidth = layout_constrainedWidth;
    }

    @Attr(RS.attr.layout_constrainedHeight)
    default void viewLayout_constrainedHeight(View view, boolean layout_constrainedHeight) {
        __constrainLocalVar.layout_constrainedHeight = layout_constrainedHeight;
    }

    @Attr(AndroidRS.attr.orientation)
    default void viewLayoutOrientation(View view, int orientation) {
        __constrainLocalVar.orientation = orientation;
    }

    @OnEnd({RS.attr.layout_constraintTop_toTopOf, RS.attr.layout_constraintTop_toBottomOf
            , RS.attr.layout_constraintBottom_toBottomOf, RS.attr.layout_constraintBottom_toTopOf
            , RS.attr.layout_constraintLeft_toLeftOf, RS.attr.layout_constraintLeft_toRightOf
            , RS.attr.layout_constraintRight_toRightOf, RS.attr.layout_constraintRight_toLeftOf
            , RS.attr.layout_constraintStart_toStartOf, RS.attr.layout_constraintStart_toEndOf
            , RS.attr.layout_constraintEnd_toStartOf, RS.attr.layout_constraintEnd_toEndOf
            , RS.attr.layout_constraintBaseline_toBaselineOf

            , RS.attr.layout_constraintWidth_percent, RS.attr.layout_constraintHeight_percent
            , RS.attr.layout_editor_absoluteX, RS.attr.layout_editor_absoluteY
            , RS.attr.layout_goneMarginTop, RS.attr.layout_goneMarginBottom
            , RS.attr.layout_goneMarginLeft, RS.attr.layout_goneMarginRight
            , RS.attr.layout_goneMarginStart, RS.attr.layout_goneMarginEnd
            , AndroidRS.attr.orientation

            , RS.attr.layout_constraintWidth_min, RS.attr.layout_constraintWidth_max
            , RS.attr.layout_constraintHeight_min, RS.attr.layout_constraintHeight_max
            , RS.attr.layout_constraintWidth_default, RS.attr.layout_constraintHeight_default

            , RS.attr.layout_constraintVertical_weight, RS.attr.layout_constraintHorizontal_weight
            , RS.attr.layout_constraintVertical_chainStyle, RS.attr.layout_constraintHorizontal_chainStyle
            , RS.attr.layout_constraintVertical_bias, RS.attr.layout_constraintHorizontal_bias

            , RS.attr.layout_constraintGuide_percent, RS.attr.layout_constraintGuide_begin
            , RS.attr.layout_constraintGuide_end, RS.attr.layout_constraintDimensionRatio
            , RS.attr.layout_constraintCircleRadius, RS.attr.layout_constraintCircleAngle
            , RS.attr.layout_constraintCircle

            , RS.attr.layout_constrainedWidth, RS.attr.layout_constrainedHeight

    })
    default void onViewConstraintEnd(View view) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) {
            androidx.constraintlayout.widget.ConstraintLayout.LayoutParams clp = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) lp;
            if (__constrainLocalVar.t2t != -1) clp.topToTop = __constrainLocalVar.t2t;
            if (__constrainLocalVar.t2b != -1) clp.topToBottom = __constrainLocalVar.t2b;

            if (__constrainLocalVar.b2b != -1) clp.bottomToBottom = __constrainLocalVar.b2b;
            if (__constrainLocalVar.b2t != -1) clp.bottomToTop = __constrainLocalVar.b2t;

            if (__constrainLocalVar.l2l != -1) clp.leftToLeft = __constrainLocalVar.l2l;
            if (__constrainLocalVar.l2r != -1) clp.leftToRight = __constrainLocalVar.l2r;

            if (__constrainLocalVar.r2r != -1) clp.rightToRight = __constrainLocalVar.r2r;
            if (__constrainLocalVar.r2l != -1) clp.rightToLeft = __constrainLocalVar.r2l;

            if (__constrainLocalVar.s2s != -1) clp.startToStart = __constrainLocalVar.s2s;
            if (__constrainLocalVar.s2e != -1) clp.startToEnd = __constrainLocalVar.s2e;

            if (__constrainLocalVar.e2e != -1) clp.endToEnd = __constrainLocalVar.e2e;
            if (__constrainLocalVar.e2s != -1) clp.endToStart = __constrainLocalVar.e2s;

            if (__constrainLocalVar.baseLine2baseLine != -1) clp.baselineToBaseline = __constrainLocalVar.baseLine2baseLine;

            //------------------------------------------------------------

            if (__constrainLocalVar.constraintWidth_percent != 1.0F) clp.matchConstraintPercentWidth = __constrainLocalVar.constraintWidth_percent;
            if (__constrainLocalVar.constraintHeight_percent != 1.0F) clp.matchConstraintPercentHeight = __constrainLocalVar.constraintHeight_percent;

            if (__constrainLocalVar.editor_absoluteY != -1) clp.editorAbsoluteY = __constrainLocalVar.editor_absoluteY;
            if (__constrainLocalVar.editor_absoluteX != -1) clp.editorAbsoluteX = __constrainLocalVar.editor_absoluteX;

            if (__constrainLocalVar.goneMarginStart != -1) clp.goneStartMargin = __constrainLocalVar.goneMarginStart;
            if (__constrainLocalVar.goneMarginEnd != -1) clp.goneEndMargin = __constrainLocalVar.goneMarginEnd;
            if (__constrainLocalVar.goneMarginLeft != -1) clp.goneLeftMargin = __constrainLocalVar.goneMarginLeft;
            if (__constrainLocalVar.goneMarginRight != -1) clp.goneRightMargin = __constrainLocalVar.goneMarginRight;
            if (__constrainLocalVar.goneMarginTop != -1) clp.goneTopMargin = __constrainLocalVar.goneMarginTop;
            if (__constrainLocalVar.goneMarginBottom != -1) clp.goneBottomMargin = __constrainLocalVar.goneMarginBottom;

            if (__constrainLocalVar.orientation != -1) clp.orientation = __constrainLocalVar.orientation;

            //------------------------------------------------------------

            if (__constrainLocalVar.layout_constraintWidth_min != 0) clp.matchConstraintMinWidth = __constrainLocalVar.layout_constraintWidth_min;
            if (__constrainLocalVar.layout_constraintHeight_min != 0) clp.matchConstraintMinHeight = __constrainLocalVar.layout_constraintHeight_min;
            if (__constrainLocalVar.layout_constraintWidth_max != 0) clp.matchConstraintMaxWidth = __constrainLocalVar.layout_constraintWidth_max;
            if (__constrainLocalVar.layout_constraintHeight_max != 0) clp.matchConstraintMaxHeight = __constrainLocalVar.layout_constraintHeight_max;

            if (__constrainLocalVar.layout_constraintWidth_default != 0) clp.matchConstraintDefaultWidth = __constrainLocalVar.layout_constraintWidth_default;
            if (__constrainLocalVar.layout_constraintHeight_default != 0) clp.matchConstraintDefaultHeight = __constrainLocalVar.layout_constraintHeight_default;

            //----------------------------------------------------------------------------------
            if (__constrainLocalVar.layout_constraintVertical_weight != -1.0F) clp.verticalWeight= __constrainLocalVar.layout_constraintVertical_weight;
            if (__constrainLocalVar.layout_constraintHorizontal_weight != -1.0F) clp.horizontalWeight= __constrainLocalVar.layout_constraintHorizontal_weight;

            if (__constrainLocalVar.layout_constraintVertical_chainStyle != 0) clp.verticalChainStyle = __constrainLocalVar.layout_constraintVertical_chainStyle;
            if (__constrainLocalVar.layout_constraintHorizontal_chainStyle != 0) clp.horizontalChainStyle = __constrainLocalVar.layout_constraintHorizontal_chainStyle;
            if (__constrainLocalVar.layout_constraintVertical_bias != 0.5F) clp.verticalBias = __constrainLocalVar.layout_constraintVertical_bias;
            if (__constrainLocalVar.layout_constraintHorizontal_bias != 0.5F) clp.horizontalBias = __constrainLocalVar.layout_constraintHorizontal_bias;
            //----------------------------------------------------------------------------------
            if (__constrainLocalVar.layout_constraintGuide_percent != -1.0F) clp.guidePercent = __constrainLocalVar.layout_constraintGuide_percent;
            if (__constrainLocalVar.layout_constraintGuide_begin != -1) clp.guideBegin = __constrainLocalVar.layout_constraintGuide_begin;
            if (__constrainLocalVar.layout_constraintGuide_end != -1) clp.guideEnd = __constrainLocalVar.layout_constraintGuide_end;
            if (__constrainLocalVar.layout_constraintDimensionRatio != null) clp.dimensionRatio = __constrainLocalVar.layout_constraintDimensionRatio;
            if (__constrainLocalVar.layout_constraintCircleRadius != 0) clp.circleRadius = __constrainLocalVar.layout_constraintCircleRadius;
            if (__constrainLocalVar.layout_constraintCircleAngle != 0) clp.circleAngle = __constrainLocalVar.layout_constraintCircleAngle;
            if (__constrainLocalVar.layout_constraintCircle != -1) clp.circleConstraint = __constrainLocalVar.layout_constraintCircle;
            //----------------------------------------------------------------------------------
            if (__constrainLocalVar.layout_constrainedWidth) clp.constrainedWidth = true;
            if (__constrainLocalVar.layout_constrainedHeight) clp.constrainedHeight = true;
        }
    }
}
