package com.qxml.support_test;

import com.qxml.gen.textView.TextViewGen;
import com.qxml.support_test.testImport.*;
import com.yellow.qxml_annotions.OnEnd;
import com.yellow.qxml_annotions.ViewParse;


@ViewParse(TestCustomView.class)
public class TestCustomViewGen extends TextViewGen {

    //test import
    @OnEnd
    public void testImportPackage(TestCustomView testCustomView) {
        int testInner = TestImport1.Inner1.Inner2.INNER_REFERENCE;
        TestImport1.TestImportInner e = TestImport1.TestImportInner.Enum1;
        int test = TestImport1.NORMAL_REFERENCE;
    }
}
