package com.yourboot.algorithm.sorts;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelShellSort {
	public static void sort(Comparable[] comps) {
		ExecutorService service=Executors.newWorkStealingPool();
		int k = 1;
		int length=comps.length;
		while (k < length / 3) {
			k=k * 3 + 1;
		}
		while (k != 1) {
			k=k / 3; /** * 对于同一个k值来说，k个线程同时操作数组的不同部分，且没有任何交集，所以不会出现读写不一致的问题， * 但是对于不同的k值来说，数组的同一个位置有可能被多个线程同时操作，会出现读写不一致的问题， * 例如k=333，某个线程尚未处理完成，这时如果进入下轮迭代k=111，另一个线程就可能和之前没有完成的线程之间出现问题。 * 所以对于每个k，使用CountDownLatch，保证k个线程都操作完成之后，在进入下一轮迭代。 */
			CountDownLatch latch=new CountDownLatch(k);
			for (int i=1; i <= k; i++) {
				service.submit(new ParallelShellSortRunnable(comps, i, k, latch));
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				System.out.println("err.." + e.getMessage());
			}
		}
		service.shutdown();
		try {
			service.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			System.out.println("error...");
		}
	}
}
