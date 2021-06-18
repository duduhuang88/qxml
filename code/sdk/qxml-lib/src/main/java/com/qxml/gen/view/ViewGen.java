package com.qxml.gen.view;

import android.view.View;

import com.qxml.gen.view.attr.*;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.yellow.qxml_annotions.CallOnInflateConfig;
import com.yellow.qxml_annotions.ViewParse;

@ViewParse(value = View.class, callOnFinishInflate = CallOnInflateConfig.NO)
public class ViewGen implements ViewCommonAttr, ViewAnimatorAttr
        , ViewFocusAttr, ViewMarginAttr, ViewPaddingAttr, ViewLocationAttr
        , ViewScrollAttr, ViewTouchAttr, ViewTintAttr, ViewLocalVar
        , LinearAttr, RelativeAttr, TableRowAttr {

}
