package com.qxml.gen.seekBar;

import android.widget.RatingBar;

import com.qxml.AndroidRS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(RatingBar.class)
public class RatingBarGen extends AbsSeekBarGen {

    @Attr(AndroidRS.attr.numStars)
    public void onRatingBarNumStars(RatingBar ratingBar, int numStars) {
        ratingBar.setNumStars(numStars);
    }

    @Attr(AndroidRS.attr.rating)
    public void onRatingBarRating(RatingBar ratingBar, float rating) {
        ratingBar.setRating(rating);
    }

    @Attr(AndroidRS.attr.stepSize)
    public void onRatingBarStepSize(RatingBar ratingBar, float stepSize) {
        ratingBar.setStepSize(stepSize);
    }

    @Attr(AndroidRS.attr.isIndicator)
    public void onRatingBarIsIndicator(RatingBar ratingBar, boolean isIndicator) {
        ratingBar.setIsIndicator(isIndicator);
    }

}
