package com.youdy.handler;

/**
 * Created by Su Jishen on 2018/1/22 11:15.
 */
public class AreaCacheJob implements Runnable {
	
	public AreaCacheJob() {}
	
	@Override
	public void run() {
		CacheManager.synAreaCache();
	}
}
