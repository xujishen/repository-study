package com.youdy.mvc.annotation;

import java.lang.annotation.*;

/**
 * 结果集处理注解
 * Created by 宿继申 on 2017-7-11.
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface ResultHandler {
    Class<?> clazz();   // 处理器类
    String method();    // 方法名
    Class<?>[] args();  // 方法参数列表
}
