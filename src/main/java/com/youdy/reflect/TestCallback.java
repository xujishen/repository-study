package com.youdy.reflect;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TestCallback implements MethodInterceptor {

	@Override
	public Object intercept(Object target, Method methid, Object[] args, MethodProxy proxy) throws Throwable {
		methid.invoke(target, args);
		return null;
	}

}
