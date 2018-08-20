package com.yourboot.handler;

/**
 * Created by Su Jishen on 2018/1/3 11:40.
 */
public class CommonThread implements Runnable {
	
	@Override
	public void run() {
		System.out.println("parent class, not override the method");
	}
}
