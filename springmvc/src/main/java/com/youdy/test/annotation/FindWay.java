package com.youdy.test.annotation;

import java.lang.annotation.*;

/**
 * Created by 123 on 2017-5-25.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FindWay {
    // 默认查找方式为数据库
    String value() default "db";
}
