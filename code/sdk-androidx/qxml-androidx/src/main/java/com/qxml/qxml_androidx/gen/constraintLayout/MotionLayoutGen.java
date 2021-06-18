package com.qxml.qxml_androidx.gen.constraintLayout;


import androidx.constraintlayout.motion.widget.MotionLayout;

import com.qxml.qxml_androidx.RS;
import com.yellow.qxml_annotions.Attr;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(MotionLayout.class)
public class MotionLayoutGen extends ConstraintLayoutGen {

    public static class $$MotionLayoutLocalVariable {
        public int layoutDescription = -1;
        public boolean applyMotionScene = true;
    }

    @LocalVar
    public $$MotionLayoutLocalVariable __motionLayoutLocalVar = new $$MotionLayoutLocalVariable();

    @Attr(RS.attr.layoutDescription)
    public void onMotionLayoutLayoutDescription(MotionLayout motionLayout, int layoutDescription) {
        __motionLayoutLocalVar.layoutDescription = layoutDescription;
    }

    @Attr(RS.attr.showPaths)
    public void onMotionLayoutShowPaths(MotionLayout motionLayout, boolean showPaths) {
        if (showPaths) {
            motionLayout.setDebugMode(2);
        }
    }

    @Attr(RS.attr.motionDebug)
    public void onMotionLayoutMotionDebug(MotionLayout motionLayout, int motionDebug) {
        motionLayout.setDebugMode(motionDebug);
    }

    @Attr(RS.attr.currentState)
    public void onMotionLayoutCurrentState(MotionLayout motionLayout, int currentState) {
        motionLayout.setState(currentState, -1, -1);
    }

    @Attr(RS.attr.motionProgress)
    public void onMotionLayoutMotionProgress(MotionLayout motionLayout, float motionProgress) {
        motionLayout.setProgress(motionProgress);
    }

    @Attr(RS.attr.applyMotionScene)
    public void onMotionLayoutApplyMotionScene(MotionLayout motionLayout, boolean applyMotionScene) {
        __motionLayoutLocalVar.applyMotionScene = applyMotionScene;
    }

    @OnEnd({RS.attr.layoutDescription, RS.attr.applyMotionScene})
    public void onMotionLayoutEnd(MotionLayout motionLayout) {
        if (__motionLayoutLocalVar.layoutDescription != -1 && __motionLayoutLocalVar.applyMotionScene) {
            com.qxml.qxml_androidx.gen.constraintLayout.tools.MotionLayoutHelper.setUI(motionLayout, __motionLayoutLocalVar.layoutDescription);
        }
        /*if (!__motionLayoutLocalVar.applyMotionScene) {
            motionLayout.setScene(null);
        }*/
    }

}
