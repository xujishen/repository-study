package com.yourboot.algorithm.sorts;

import java.security.SecureRandom;

public class SortingTool {
	private static final SecureRandom RANDOM = new SecureRandom();
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	public static void exch(Comparable[] a, int i, int j) {
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	
	public static Integer[] geneIntArr(int size) {
		Integer[] result = new Integer[size];
		for (int i=0; i < result.length; i++) {
			result[i]=RANDOM.nextInt();
		}
		return result;
	}
	
	public static boolean isSort(Comparable[] a) {
		for (int i=0; i < a.length - 1; i++) {
			if (less(a[i + 1], a[i])) return false;
		}
		return true;
	}
}