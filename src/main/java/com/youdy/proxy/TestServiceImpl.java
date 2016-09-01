package com.youdy.proxy;

public class TestServiceImpl implements ITestService {

	@Override
	public void fuck() {

		System.out.println("the real impl excuted!");
		
	}

}
