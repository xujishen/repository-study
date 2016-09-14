package com.youdy;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;

import com.youdy.bean.UserBean;
import com.youdy.proxy.ITestService;
import com.youdy.proxy.MethodListener;
import com.youdy.proxy.MethodListenerImpl;
import com.youdy.proxy.TestServiceImpl;
import com.youdy.proxy.TextServiceProxy;
import com.youdy.reflect.MethodInvocationHandler;

public class Test {

	public static void main(String[] args) {
			MethodListener lis = new MethodListenerImpl();
			
			MethodInvocationHandler<MethodListener> handler = new MethodInvocationHandler(lis);

			MethodListener l = (MethodListener) Proxy.newProxyInstance(lis.getClass().getClassLoader(), lis.getClass().getInterfaces(), handler);
			
			//l.getMethodExcuteTime();
			
			TextServiceProxy<ITestService> proxy = new TextServiceProxy<>();
			
			ITestService service = new TestServiceImpl();
			//((ITestService) proxy.setTarget(new TestServiceImpl())).fuck();
			
			
			ExecutorService executorService = null;
			
			XYZ u = new XYZ();
			Comparable<? super XYZ> uc = (Comparable<? super XYZ>) u;
			uc.compareTo(null);
	}
}

class XYZ {
	
}