/**
 * 
 */
package com.youdy.test.algorithm;

/**
 * @File: AlgorithmUtils.java
 * @package com.youdy.test.algorithm
 * @author 宿继申 
 * @date: 2017年5月8日 上午11:04:47
 * @version 1.0
 * @Copyright (C) aaaaa.
 * 
 */
public final class AlgorithmUtils {
	
	// 空字符串
	public static final String EMPTY = "".intern();
	
	/**
	 * int数组 TO 字符串
	 * @param arr: 带转换数组
	 * @return
	 */
	public static String intArrayToString(int[] arr) {
		if (arr == null || arr.length < 1) {
			return EMPTY;
		}
		String result = "[".intern();
		for (int i : arr) {
			result += (i + ",");
		}
		return result.substring(0, result.length() - 1) + "]";
		
	}
	
	/**
     * 交换
     * @param m - 被交换的第一个元素位置
     * @param n - 被交换的第二个元素位置
     */
	public synchronized static void swapInt(int[] arr, int m, int n) {
        int x = arr[m];
        arr[m] = arr[n];
        arr[n] = x;
    }
	
}
