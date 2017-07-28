package com.youdy.test.algorithm.sorts;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Su Jishen on 2017/7/28 9:38.
 */
public class ParallelShellSortRunnable implements Runnable{
	private Comparable[] comps;
	private int start;
	private int k;
	private CountDownLatch latch;
	
	public ParallelShellSortRunnable(Comparable[] comps, int start, int k, CountDownLatch latch) {
		this.comps = comps;
		this.start=start;
		this.k=k;
		this.latch=latch;
	}
	
	@Override
	public void run() {
		int length = comps.length;
		for (int j=start; j < length; j=j + k) {
			for (int m=j; m >= 0 && m - k >= 0 && SortingTool.less(comps[m], comps[m - k]); m=m - k)
				SortingTool.exch(comps, m, m - k);
		}
		latch.countDown();
	}
}
