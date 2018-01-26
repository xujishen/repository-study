package com.youdy.utils;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * 并发无锁队列
 * Created by Su Jishen on 2018/1/23 16:14.
 */
public class ConcurrentQueue<V> implements  Iterable<V>{
	
	// the head
	private transient volatile AtomicReference<Node<V>> head;
	// the tail
	private transient volatile AtomicReference<Node<V>> tail;
	
	// the size of this queue
	private transient volatile AtomicInteger size = new AtomicInteger(0);
	
	// constructrues
	public ConcurrentQueue() {
		//this(null);
	}
	
	// init the queue and set to head = tail = value
	public ConcurrentQueue(V value) {
		Node<V> node = new Node<V>(value);
		head = new AtomicReference<Node<V>>(node);
		tail = new AtomicReference<Node<V>>(node);
	}
	
	// the inner node class
	private static class Node<T>{
		
		// the current node value
		private transient volatile T value;
		// the next node
		private transient volatile Node<T> next = null;
		// the prev node
		private transient volatile Node<T> prev = null;
		Node(T value) {
			this.value = value;
		}
	};
	
	// get the head node
	public V pop () {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			for (; ;) {
				// empty queue
				if (size.get() == 0 || head == null) {
					return null;
				}
				Node<V> oldHead = head.get();
				Node<V> newHead = oldHead.next;
				if (newHead != null) {
					newHead.prev = null;
				}
				if (head.compareAndSet(oldHead, newHead)) {
					desize();
					return oldHead.value;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			lock.unlock();
		}
	}
	
	// decremnet the size value
	private void desize() {
		size.decrementAndGet();
	}
	
	// append value to tail
	public void offer (V v) {
		// checkNull(v);
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			for (; ;) {
				// the queue is empty
				if (tail == null || head == null) {
					head = tail = new AtomicReference<Node<V>>(new Node<V>(v));
					increment();
					return;
				} else {
					Node<V> oldTail = tail.get();
					Node<V> newNode = new Node<>(v);
					if (oldTail != null) {
						oldTail.next = newNode;
						newNode.prev = oldTail;
					}
					// CAS update
					if (tail.compareAndSet(oldTail, newNode)) {
						increment();
						return;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
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
		// size.getAndIncrement();// increment and return the old value
		size.incrementAndGet(); // increment and return the new value
	}
	
	/**
	 *  the overrided method for iteratable
	 */
	@Override
	public Iterator<V> iterator() {
		return null;
	}
	
	@Override
	public void forEach(Consumer<? super V> action) {
	
	}
	
	public static void main(String[] args) {
		ConcurrentQueue cq = new ConcurrentQueue();
		cq.offer(6);
		cq.offer(4);
		cq.offer(15);
		final Object pop = cq.pop();
		System.out.println(pop);
	}
	
}
