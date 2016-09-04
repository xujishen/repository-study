package com.youdy.test;

public class Test1 {
	
	/**
	 * 输入一个乱序字符串 "457324852" 返回逆序排序 即 875544322
	 * @param str
	 * @return
	 */
	public String orderDesc(String str) {
		String result = "";
		if (str == null || str.length() <= 0) {
			return "";
		} else {
			char[] chars = str.toCharArray();
			int len = chars.length;
			for (int i = 0; i < len; i ++) {
				int ch = chars[i];
				
				for (int j = i + 1; j < len; j ++) {
					int nextCh = chars[j];
					//457324852
					if(nextCh > ch )  {
						chars[j] = chars[i];
						chars[i] = (char) nextCh;
						ch = chars[i];
					}
				}
			}
			System.out.println(String.valueOf(chars));
			return "";
		}
	}
	
	public static void main(String[] args) {
		Test1 t1 = new Test1();
		t1.orderDesc("457324852");
	}
}
