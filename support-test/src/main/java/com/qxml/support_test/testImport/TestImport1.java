package com.qxml.support_test.testImport;

public class TestImport1 {

    public static final int NORMAL_REFERENCE = 0;
    public static final int STATIC_REFERENCE = 0;

    public enum TestImportInner {
        Enum1, Enum2
    }

    public static class Inner1 {
        public static class Inner2 {
            public static final int INNER_REFERENCE = 1;
        }
    }

}
