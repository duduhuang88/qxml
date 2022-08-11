package com.yellow.qxml_annotions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * OnEnd注解的方法会根据类中定义的顺序执行
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnEnd {
    /**
     * @return 触发条件 eg: { RS.attr.errorIconDrawable, RS.attr.errorIconTint }
     */
    String[] value() default {};

    /**
     * @return 是否添加View后调用
     */
    boolean afterAdd() default false;

    String requiredCondition() default "";
}
