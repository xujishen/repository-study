package com.youdy;

import java.lang.reflect.Proxy;

import com.youdy.proxy.MethodListener;
import com.youdy.proxy.MethodListenerImpl;
import com.youdy.reflect.MethodInvocationHandler;

public class Test {

	public static void main(String[] args) {
			MethodListener lis = new MethodListenerImpl();
			
			MethodInvocationHandler<MethodListener> handler = new MethodInvocationHandler(lis);

			MethodListener l = (MethodListener) Proxy.newProxyInstance(lis.getClass().getClassLoader(), lis.getClass().getInterfaces(), handler);
			
			l.getMethodExcuteTime();
	}
}
