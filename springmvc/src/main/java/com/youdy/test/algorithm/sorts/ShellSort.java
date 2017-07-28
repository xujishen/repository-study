package com.youdy.test.algorithm.sorts;

/**
 * Created by Su Jishen on 2017/7/28 9:31.
 */
public class ShellSort {
	public static void sort(Comparable[] a) {
		int key=1;
		int length=a.length;
		while (key < length / 3) {
			key=key * 3 + 1;
		}
		while (key != 1) {
			key=key / 3;
			for (int i=1; i <= key; i++) {
				for (int j=i; j < length; j=j + key) {
					for (int m=j; m >= 0 && m - key >= 0 && SortingTool.less(a[m], a[m - key]); m=m - key)
						SortingTool.exch(a, m, m - key);
				}
			}
		}
	}
}
