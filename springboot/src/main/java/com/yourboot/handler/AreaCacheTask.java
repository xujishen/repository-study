package com.yourboot.handler;

import java.util.concurrent.Callable;

/**
 * 区域缓存任务
 * Created By shen on 2017-7-11 23:35
 */
public class AreaCacheTask implements Callable<Integer> {
	
	/**
	 * Computes a result, or throws an exception if unable to do so.
	 *
	 * @return computed result
	 * @throws Exception if unable to compute a result
	 */
	@Override
	public Integer call() throws Exception {
		final int count = CacheManager.synAreaCache();
		return count;
	}
}
