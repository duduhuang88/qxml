package com.qxml.qxml_androidx.gen.constraintLayout;

import android.view.View;

import androidx.constraintlayout.widget.Group;

import com.qxml.AndroidRS;
import com.qxml.qxml_androidx.RS;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(Group.class)
public class GroupGen extends ConstraintHelperGen {

    public static class $$GroupLocalVariable {
        public int visibility = 0;
    }

    @LocalVar
    public $$GroupLocalVariable __groupLocalVar = new $$GroupLocalVariable();

    @Override
    public void viewVisibility(View view, int visibility) {
        __groupLocalVar.visibility = visibility;
    }

    @OnEnd({RS.attr.constraint_referenced_ids, AndroidRS.attr.visibility})
    public void onGroupEnd(Group group) {
        if (__groupLocalVar.visibility != android.view.View.VISIBLE) {
            group.setVisibility((__groupLocalVar.visibility == 1 ? android.view.View.INVISIBLE : android.view.View.GONE));
        }
    }

}
