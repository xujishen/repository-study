package com.youdy.utils;

public class LinkTableTest {

    private static LinkTable getMe() {
        LinkTable lt = LinkTable.of();
        for (int i = 0; i < 11; i++) {
            lt.add(i);
        }
        return lt;
    }

    // toString可以测试到 迭代器函数
    private static void print(LinkTable lt) {
        System.out.println("列表大小: " + lt.size());
        System.out.println("列表toString: " + lt);
    }

    @org.junit.Test
    public void add() throws Exception {
        LinkTable lt = getMe();
        print(lt);
    }

    @org.junit.Test
    public void get() throws Exception {
        LinkTable lt = getMe();

        for (int i = 0; i < 11; i++) {
            Object o = lt.get(i);
            System.out.println("元素" + o);
        }
        Object o1 = lt.get(-1);
        System.out.println("索引=-1的元素" + o1);
        Object o2 = lt.get(11);
        System.out.println("索引=11的元素" + o2);
    }

    @org.junit.Test
    public void reverse() throws Exception {
        LinkTable table = getMe();
        System.out.println("反转前: " + table);
        table.reverse();
        System.out.println("反转后: " + table);
    }

}
