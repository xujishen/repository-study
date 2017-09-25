/**
 * 
 */
package com.youdy.test.algorithm.sorts;

import com.youdy.test.algorithm.AlgorithmUtils;

/**
 * @TODO: 冒泡排序 - 升序
 * @File: BubbleSorter.java
 * @package com.youdy.test.algorithm.sorts
 * @author 宿继申 
 * @date: 2017年5月8日 上午11:26:59
 * @version 1.0
 * 
 */
public class BubbleSorter implements Sorter {

	@Override
	public void sort() {
		System.out.println("开始执行冒泡排序, 数组: " + AlgorithmUtils.intArrayToString(arr));
		long t0 = System.currentTimeMillis();
		// 开始从首位循环
		for (int i = 0; i < len; i++) {
			int curr = arr[i];
			// 从当前位置的下一个位置循环
			for (int j = i + 1; j < len; j++) {
				int next = arr[j];
				// 如果下一个元素小于当前元素, 则进行交换, 否则不交换, 但对当前元素需要更改为较大的一个.
				if (curr > next) {
					curr = arr[j];
					AlgorithmUtils.swapInt(arr, i, j); // 此处第一个参数 j - 1很重要
				} else {
					curr = arr[i];
				}
			}
		}
		long t1 = System.currentTimeMillis();
		System.out.println("结束执行冒泡排序, 数组: " + AlgorithmUtils.intArrayToString(arr));
		System.out.println("耗时: " + (t1 - t0) + "毫秒");
	}

	public static void main(String[] args) {
		Sorter sorter = new BubbleSorter();
		sorter.sort();
		System.out.println( -1102852372 < -1706872566);
	}
}
