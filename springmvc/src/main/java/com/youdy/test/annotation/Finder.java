package com.youdy.test.annotation;

import java.lang.annotation.*;

/**
 * 类注解 查找器
 * Created by 123 on 2017-5-25.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Finder {
    // 别名
    String alias() default "defaultFinder";

    // 范围
    String[] target() default {"all"};
}
