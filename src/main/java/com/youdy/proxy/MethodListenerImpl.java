package com.youdy.proxy;

public class MethodListenerImpl implements MethodListener {

	@Override
	public long getMethodExcuteTime() {
		System.out.println("代理类执行");
		return 0;
	}

}
