package com.youdy.test.algorithm.sorts;

import com.youdy.test.algorithm.AlgorithmUtils;

/**
 * Created By shen on 2017-9-26 21:16
 */
public class ChooseSorter implements Sorter {

    @Override
    public void sort() {
        System.out.println("选择排序, 数组: " + AlgorithmUtils.intArrayToString(arr));
        int len = arr.length;
        for (int i = 0; i < len - 1; i  ++) {
            int k = i; // 记录当前位置  0 --> 3, 下面的循环将可能来替换此位置的值
            for (int j = i + 1; j < len; j ++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
                if (k != i) {
                    AlgorithmUtils.swapInt(arr, i, k);
                }
            }
        }
    }

    public static void main(String[] args) {
        Sorter sorter = new ChooseSorter();
        sorter.sort();
        System.out.println("选择排序后, 数组: " + AlgorithmUtils.intArrayToString(arr));
    }
}
