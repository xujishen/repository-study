package com.youdy.proxy;

import com.youdy.reflect.TestCallback;

import net.sf.cglib.proxy.Enhancer;

public class TextServiceProxy <T> {
	private T target;
	
	public Object setTarget(T target) {
		this.target = target;
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(target.getClass());
		hancer.setCallback(new TestCallback());
		return hancer.create();
	}
}
