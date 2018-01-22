package com.youdy.handler;

import java.io.Serializable;


public class CacheHandler implements Serializable {

	private static final long serialVersionUID = -4934365135940315018L;
	public CacheHandler () {}
	public void init() {
		
		// 使用线程异步 加载 数据
		CacheManager.initAreaCacheByRunnable();
		// CacheManager.initAreaCacheByCallable();
	}
	
}
