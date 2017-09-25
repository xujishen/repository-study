package com.youdy.test.algorithm.sorts;

import com.youdy.test.algorithm.AlgorithmUtils;

/**
 * Created By shen on 2017-5-7 0:21
 * 交换插入排序 - 升序
 */
public class SwapSorter implements Sorter {

    /**
     * 排序
     */
    @Override
    public void sort() {
        System.out.println("开始执行交换排序, 数组: " + AlgorithmUtils.intArrayToString(arr));
		long t0 = System.currentTimeMillis();
        // 从首位开始循环数组
        for (int i = 0; i < len; i ++) {
            // 从当前位置向反方向循环
            for (int j = i; j > 0; j --) {
                // 当前元素
                int curr = arr[j];
                // 下一个元素(不是右侧的元素, 而是即将和当前元素比较的那个元素, 应该是反方向的元素)
                int next = arr[j - 1];
                if (next > curr) AlgorithmUtils.swapInt(arr, j - 1, j);
            }
        }
		long t1 = System.currentTimeMillis();
        System.out.println("结束执行交换排序, 数组: " + AlgorithmUtils.intArrayToString(arr) + ", 耗时: " + (t1 - t0) + "毫秒");
    }

    public static void main(String[] args) {
		Sorter sorter = new SwapSorter();
		sorter.sort();
	}
}
