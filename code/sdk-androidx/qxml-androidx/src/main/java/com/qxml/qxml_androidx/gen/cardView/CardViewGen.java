package com.qxml.qxml_androidx.gen.cardView;


import androidx.cardview.widget.CardView;

import com.qxml.gen.frameLayout.FrameLayoutGen;
import com.qxml.qxml_androidx.RS;
import com.qxml.value.ValueInfo;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(CardView.class)
public class CardViewGen extends FrameLayoutGen {

    public static final class $$CardViewLocalVar {
        public int contentPaddingLeft = 0;
        public int contentPaddingRight = 0;
        public int contentPaddingTop = 0;
        public int contentPaddingBottom = 0;
        public float cardElevation = 0f;
        public float cardMaxElevation = 0f;
    }

    @LocalVar
    public $$CardViewLocalVar __cardViewLocalVar = new $$CardViewLocalVar();

    @Attr(RS.attr.cardBackgroundColor)
    public void cardViewCardBackgroundColor(CardView cardView, ValueInfo cardBackgroundColor) {
        cardView.setCardBackgroundColor(cardBackgroundColor.colorValue);
    }

    @Attr(RS.attr.cardCornerRadius)
    public void cardViewCardCornerRadius(CardView cardView, float cardCornerRadius) {
        cardView.setRadius(cardCornerRadius);
    }

    @Attr(RS.attr.cardElevation)
    public void cardViewCardElevation(CardView cardView, float cardElevation) {
        __cardViewLocalVar.cardElevation = cardElevation;
    }

    @Attr(RS.attr.cardMaxElevation)
    public void cardViewCardMaxElevation(CardView cardView, float cardMaxElevation) {
        __cardViewLocalVar.cardMaxElevation = cardMaxElevation;
    }

    @Attr(RS.attr.cardUseCompatPadding)
    public void cardViewCardUseCompatPadding(CardView cardView, boolean cardUseCompatPadding) {
        cardView.setUseCompatPadding(cardUseCompatPadding);
    }

    @Attr(RS.attr.cardPreventCornerOverlap)
    public void cardViewCardPreventCornerOverlap(CardView cardView, boolean cardPreventCornerOverlap) {
        cardView.setPreventCornerOverlap(cardPreventCornerOverlap);
    }

    @Attr(RS.attr.contentPadding)
    public void cardViewContentPadding(CardView cardView, int contentPadding) {
        __cardViewLocalVar.contentPaddingLeft = contentPadding;
        __cardViewLocalVar.contentPaddingRight = contentPadding;
        __cardViewLocalVar.contentPaddingTop = contentPadding;
        __cardViewLocalVar.contentPaddingBottom = contentPadding;
    }

    @Attr(RS.attr.contentPaddingLeft)
    public void cardViewContentPaddingLeft(CardView cardView, int contentPaddingLeft) {
        __cardViewLocalVar.contentPaddingLeft = contentPaddingLeft;
    }

    @Attr(RS.attr.contentPaddingRight)
    public void cardViewContentPaddingRight(CardView cardView, int contentPaddingRight) {
        __cardViewLocalVar.contentPaddingRight = contentPaddingRight;
    }

    @Attr(RS.attr.contentPaddingTop)
    public void cardViewContentPaddingTop(CardView cardView, int contentPaddingTop) {
        __cardViewLocalVar.contentPaddingTop = contentPaddingTop;
    }

    @Attr(RS.attr.contentPaddingBottom)
    public void cardViewContentPaddingBottom(CardView cardView, int contentPaddingBottom) {
        __cardViewLocalVar.contentPaddingBottom = contentPaddingBottom;
    }

    /*@Override
    public void viewMinWidth(View view, int minWidth) {
        view.setMinimumWidth(minWidth);
    }

    @Override
    public void viewMinHeight(View view, int minHeight) {
        view.setMinimumHeight(minHeight);
    }*/

    @OnEnd({RS.attr.contentPadding, RS.attr.contentPaddingLeft
            , RS.attr.contentPaddingTop, RS.attr.contentPaddingRight
            , RS.attr.contentPaddingBottom})
    public void onCardViewContentPaddingEnd(CardView cardView) {
        cardView.setContentPadding(__cardViewLocalVar.contentPaddingLeft, __cardViewLocalVar.contentPaddingTop, __cardViewLocalVar.contentPaddingRight, __cardViewLocalVar.contentPaddingBottom);
    }

    @OnEnd({RS.attr.cardElevation, RS.attr.cardMaxElevation})
    public void onCardViewContentElevationEnd(CardView cardView) {
        if (__cardViewLocalVar.cardElevation != 0f) {
            cardView.setCardElevation(__cardViewLocalVar.cardElevation);
        }
        if (__cardViewLocalVar.cardMaxElevation != 0f) {
            if (__cardViewLocalVar.cardElevation > __cardViewLocalVar.cardMaxElevation) {
                __cardViewLocalVar.cardMaxElevation = __cardViewLocalVar.cardElevation;
            }
            cardView.setMaxCardElevation(__cardViewLocalVar.cardMaxElevation);
        }
    }

}
