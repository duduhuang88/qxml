package com.yellow.qxml_annotions;

import com.qxml.constant.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewParse {
    /**
     * 定义的类，如AppCompatTextView
     */
    Class<?> value();
    /**
     * compat类的基类，如AppCompatTextView的基类为TextView
     */
    Class<?> compatOf() default Void.class;
    /**
     * compat条件，使用 `AndroidRS.attr.*` 或 `RS.attr.*` 填充，当满足条件时，基类会解析成 compat 类，如：{ RS.attr.lineHeight, AndroidRS.attr.lineHeight }，当出现 `app:lineHeight` 或 `android:lineHeight` 时会解析成 `AppCompatTextView`
     */
    String[] compatCondition() default {};
    /**
     * ViewGroup时默认的layoutParam初始化语句，使用确定的值可以避免反射， 如：layoutParamInit = "new android.widget.FrameLayout.LayoutParams(-1, -1)"，请使用全限定类名
     */
    String layoutParamInit() default "";
    /**
     * 是否调用onFinishInflate
     */
    CallOnInflateConfig callOnFinishInflate() default CallOnInflateConfig.EXTENDS;
}
