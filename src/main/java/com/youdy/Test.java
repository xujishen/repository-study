package com.youdy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

import com.youdy.bean.UserBean;
import com.youdy.proxy.ITestService;
import com.youdy.proxy.MethodListener;
import com.youdy.proxy.MethodListenerImpl;
import com.youdy.proxy.TestServiceImpl;
import com.youdy.proxy.TextServiceProxy;
import com.youdy.reflect.MethodInvocationHandler;

public class Test {

	public static void main(String[] args) throws Exception {
			MethodListener lis = new MethodListenerImpl();
			
			MethodInvocationHandler<MethodListener> handler = new MethodInvocationHandler(lis);

			MethodListener l = (MethodListener) Proxy.newProxyInstance(lis.getClass().getClassLoader(), lis.getClass().getInterfaces(), handler);
			
			//l.getMethodExcuteTime();
			
			TextServiceProxy<ITestService> proxy = new TextServiceProxy<>();
			
			ITestService service = new TestServiceImpl();
			//((ITestService) proxy.setTarget(new TestServiceImpl())).fuck();
			
			
			ExecutorService executorService = null;
			
		int n = 18;
			final CountDownLatch cdl = new CountDownLatch(n);
			
			for (int i = 1; i <= n; i ++) {
				final int x = i;
				new Thread(new Runnable() {
					public void run() {
						//System.out.println("I am the" + x + " nd thread ! ");
						cdl.countDown();
					}
				}).start();
			}
			cdl.await();
			System.out.println("All the threads have been executed ! ");
			
			
			final CyclicBarrier cb = new CyclicBarrier(n);
			for (int i = 1; i <= n; i ++) {
				final int x = i;
				new Thread(new Runnable() {
					public void run() {
						try {
							cb.await();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//System.out.println("The " + x + "nd thread has been arrived ! ");
					}
				}).start();
			}
			System.out.println("All the threads arrived ! ");
			
			
			
	}
}
