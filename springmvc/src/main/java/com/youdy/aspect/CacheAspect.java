package com.youdy.aspect;

import com.youdy.mvc.annotation.Lockable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
//@Aspect
public class CacheAspect {

    @Around(value = "@annotation(com.youdy.mvc.annotation.Lockable)")
    public void print(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
        if (signature instanceof MethodSignature) {
            Method method = ((MethodSignature) signature).getMethod();
            Lockable annotation = (Lockable) method.getAnnotation(Lockable.class);
            String key = annotation.key();
            String value = annotation.value();
            System.out.println("key: " + key);
            System.out.println("value: " + value);
        }
    }

}
