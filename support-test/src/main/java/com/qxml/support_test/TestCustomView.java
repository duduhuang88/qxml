package com.qxml.support_test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class TestCustomView extends TextView {
    public TestCustomView(Context context) {
        super(context);
    }

    public TestCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
