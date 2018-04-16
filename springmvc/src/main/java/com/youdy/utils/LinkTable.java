package com.youdy.utils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created By shen on 2018-4-14 13:38
 */
public class LinkTable<V> implements Serializable, Iterable<V> {

    // the first element of table
    private transient Node head;

    // the last element of the table
    private transient Node tail;

    // the element count of this table
    private transient AtomicInteger size = new AtomicInteger(0);

    // suggest to use the #LinkTable.of# factory method to
    // construct the LinkTable
    private LinkTable() {}

    // factory method
    public static LinkTable of() {
        return new LinkTable();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    public Iterator<V> iterator() {
        return new TableIter<V>();
    }

    // the private inner class to maintain the table's element
    private static class Node<V> {
        // the next node
        volatile Node<V> next;
        // the previous node
        volatile  Node<V> prev;
        // curr value
        volatile V v;

        Node(V val) {
            this.v = val;
        }
    }

    private final class TableIter<V> implements Iterator {

        volatile int cursor = 0;

        public boolean hasNext() {
            return new Integer(cursor + 1).compareTo(new Integer(size())) <= 0;
        }

        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no next element! ");
            }
            Node nextOne = head;
            for (int i = 0; i < cursor; i ++) {
                nextOne = nextOne.next;
            }
            cursor ++ ;
            return nextOne.v;

        }

        public void remove() {

        }
    }

    // add new element to the table
    public boolean add(V v) {
        if (this.size.get() == 0) {
            // initialize the table, tail == head
            this.tail = this.head = new Node<V>(v);
            inc();
            return true;
        } else {
            Node last = this.tail;
            // Node first = this.head;
            Node newNode = new Node<V>(v);
            last.next = newNode;
            newNode.prev = last;
            this.tail = newNode;
            inc();
            return true;
        }

    }

    // get the specified position element
    public V get(int idx) throws Exception {
        if (idx < 0 || idx >= size()) {
            throw new Exception("the idx is not correct! ");
        }
        int half = size() / 2;
        if (idx <= half) {
            Node<V> item = this.head;
            for (int i = 0; i < idx; i ++) { // do not use i <= idx
                item = item.next;
            }
            return item.v;
        } else {
            Node<V> item = this.tail;
            for (int i = size() - 1; i > idx ; i --) {
                item = item.prev;
            }
            return item.v;
        }
    }

    // reverse myself and not gen a new obj
    public synchronized void reverse() {
        try {
            if (this.head == null || this.head.next == null) {
                return;
            }
            Node node = this.tail;
            while (node != null) {
                Node temp1 = node.next;
                node.next = node.prev;
                node.prev = temp1;
                node = node.next;
            }
            // exchange head and tail
            Node head = this.head;
            this.head = this.tail;
            this.tail = head;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // size ++ and return then new value
    private void inc() {
        this.size.incrementAndGet();
    }

    // size -- and return the new value
    private int dec() {
        return this.size.decrementAndGet();
    }

    public int size() {
        return this.size.get();
    }

    @Override
    public String toString() {
        if (size() <= 0 )
            return "LinkTable{}";
        else {
            StringBuffer buffer = new StringBuffer("LinkTable{");
            Iterator<V> iterator = this.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                V next = iterator.next();
                if ((i + 1) == size()) {
                    buffer.append(next);
                } else {
                    buffer.append(next).append(", ");
                    i ++;
                }
            }
            buffer.append("}");
            return buffer.toString();
        }
    }
}
