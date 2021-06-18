package com.qxml.qxml_support.gen.constraintLayout;

import android.support.constraint.Placeholder;

import com.qxml.gen.view.ViewGen;
import com.qxml.qxml_support.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(Placeholder.class)
public class PlaceHolderGen extends ViewGen {

    public static class $$PlaceHolderVariable {
        public int contentReferenceId = 0;
    }

    @LocalVar
    public $$PlaceHolderVariable __placeHolderLocalVar = new $$PlaceHolderVariable();

    @Attr(RS.attr.content)
    public void placeHolderContent(Placeholder placeholder, int reference) {
        __placeHolderLocalVar.contentReferenceId = reference;
    }

    @Attr(RS.attr.placeholder_emptyVisibility)
    public void placeHolderEmptyVisible(Placeholder placeholder, int emptyVisibility) {
        placeholder.setEmptyVisibility(emptyVisibility);
    }

    @OnEnd(value = RS.attr.content, afterAdd = true)
    public void onPlaceHolderEnd(Placeholder placeholder) {
        if (__placeHolderLocalVar.contentReferenceId != 0) {
            placeholder.setContentId(__placeHolderLocalVar.contentReferenceId);
        }
    }
}
