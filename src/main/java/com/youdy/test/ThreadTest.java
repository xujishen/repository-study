package com.youdy.test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadTest {

	public static void main(String[] args) throws Exception {
		
		String a = "AVC";
		String b = new String("AVC");
		String c = "A" + "VC";
		
		System.out.println(a == b.intern());
		
		Task1 t1 = new Task1();
		
		ThreadFactory tf = Executors.defaultThreadFactory();
				
		ExecutorService es = Executors.newFixedThreadPool(5, tf);
		
		es.execute(t1);
		//Callable cal = Executors.callable(t1);
		//Object result = cal.call();
		//System.out.println(result);
	}
}

class Task1 implements Runnable{

	/**
	 * 带 void 的运行方法
	 */
	@Override
	public void run() {
		List<Object> list = new ArrayList<>();
		long t0 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			list.add(new BigDecimal(i));
		}
		long t1 = System.currentTimeMillis();
		System.out.println("ArrayList添加时间: " + (t1 - t0));
		
		long t2 = System.currentTimeMillis();
		list.removeAll(list);
		long t3 = System.currentTimeMillis();
		System.out.println("ArrayList删除时间: " + (t3 - t2));
		list = null;
	}
	
}