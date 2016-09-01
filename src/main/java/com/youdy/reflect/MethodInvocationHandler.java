package com.youdy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodInvocationHandler <T> implements InvocationHandler {
	
	private T target;
	
	public MethodInvocationHandler(T target) {
		super();
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long t0 = System.currentTimeMillis();
		Object o = method.invoke(target, args);
		System.out.println("类:" + target.getClass().getSimpleName() + "执行方法: " + method + "(" + args + ")时间为: " + (System.currentTimeMillis() - t0));
		return o;
	}

}
