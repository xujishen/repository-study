package com.youdy.test.algorithm.sorts;

import com.youdy.test.algorithm.AlgorithmUtils;

/**
 * Created By shen on 2017-5-7 0:21
 * 交换插入排序 - 升序
 */
public class SwapSorter implements Sorter {

    // 待排序数组
    private static volatile int[] arr = {300, 101, 1200, 12, 9, 8};
    // 数组长度
    private static int len = arr.length;

    /**
     * 排序
     */
    @Override
    public void sort() {
        System.out.println("开始执行交换排序, 数组: " + AlgorithmUtils.intArrayToString(arr));
        // 从首位开始循环数组
        for (int i = 0; i < len; i ++) {
            // 从当前位置向反方向循环
            for (int j = i; j > 0; j --) {
                // 当前元素
                int curr = arr[j];
                // 下一个元素(不是右侧的元素, 而是即将和当前元素比较的那个元素)
                int next = arr[j - 1];
                if (next > curr) swap(j - 1, j);
            }
        }

        System.out.println("结束执行交换排序, 数组: " + AlgorithmUtils.intArrayToString(arr));
    }

	/**
     * 交换
     * @param m - 被交换的第一个元素位置
     * @param n - 被交换的第二个元素位置
     */
    private synchronized void swap(int m, int n) {
        int x = arr[m];
        arr[m] = arr[n];
        arr[n] = x;
    }
    
    public static void main(String[] args) {
		Sorter sorter = new SwapSorter();
		sorter.sort();
	}
}
