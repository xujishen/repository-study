package com.youdy.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Su Jishen on 2018/1/23 16:14.
 */
public class ConcurrentQueue<T> {
	
	// the head
	private transient volatile Node<T> head;
	// the tail
	private transient volatile Node<T> tail;
	
	// the head
	private transient volatile AtomicReference<Node<T>> atomicHead;
	// the tail
	private transient volatile AtomicReference<Node<T>> atomicTail;
	
	
	
	// the size of this queue
	private transient volatile AtomicInteger size = new AtomicInteger(0);
	
	// constructrues
	public ConcurrentQueue() {
		this(null);
	}
	public ConcurrentQueue(T value) {
		Node<T> v = new Node<T>(value);
		head = v;
		tail = v;
	}
	
	// the inner node class
	private static class Node<T>{
		
		// the current node value
		private transient volatile T curr;
		// the next node
		private transient volatile Node<T> next = null;
		// the prev node
		private transient volatile Node<T> prev = null;
		Node(T value) {
			curr = value;
		}
	};
	
	public T pop () {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			if (size.get() == 0) {
				return null;
			}
			Node<T> oldHead = head;
			Node<T> next = oldHead.next;
			if (next != null) {
				next.prev = null;
				head = next;
			}
			return oldHead.curr;
		} catch(Exception e) {
			return null;
		}
		finally {
			lock.unlock();
		}
	}
	
	public void offer (T t) {
		// checkNull(t);
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			Node<T> oldTail = tail;
			Node<T> newNode = new Node<>(t);
			if (oldTail != null) {
				oldTail.next = newNode;
				newNode.prev = oldTail;
			}
			tail = newNode;
			increment();
		} catch(Exception e) {
		
		}
		finally {
			lock.unlock();
		}
	}
	
	// get the queue size
	public int size() {
		return size.get();
	}
	
	// increment the queue size
	private void increment() {
		size.getAndIncrement();
	}
	
}
