package com.youdy.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;

/**
 * Created By shen on 2017-5-25 22:32
 */
@Finder(alias = "testFinder")
public class AnnotationTest {

    @FindWay(value = "redis")
    public void doFind() {
        System.out.println("开始执行查找");
    }

    public static void main(String[] args) {
        final Annotation[] annotations = AnnotationTest.class.getAnnotations();
        for (Annotation ann: annotations) {

            System.out.println(ann.annotationType().getName());
        }
    }
}
