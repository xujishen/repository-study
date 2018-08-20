package com.yourboot.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * 并发无锁队列
 * Created by Su Jishen on 2018/1/23 16:14.
 */
public class ConcurrentQueue<V> implements Serializable, Iterable<V>{
	
	private static final long serialVersionUID = 936804876481746702L;
	
	// the head
	private transient volatile AtomicReference<Node<V>> head;
	// the tail
	private transient volatile AtomicReference<Node<V>> tail;
	
	// the size of this queue
	private transient volatile AtomicInteger size = new AtomicInteger(0);

	public static ConcurrentQueue of() {
		return new ConcurrentQueue();
	}

	// constructrues
	private ConcurrentQueue() {}
	
	// init the queue and set to head = tail = value
	/*public ConcurrentQueue(V value) {
		Node<V> node = new Node<V>(value);
		head = new AtomicReference<Node<V>>(node);
		tail = new AtomicReference<Node<V>>(node);
	}*/
	
	// the inner node class
	private static class Node<V> {
		
		// the current node value
		private transient volatile V value;
		// the next node
		private transient volatile Node<V> next = null;
		// the prev node
		private transient volatile Node<V> prev = null;
		Node(V value) {
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
	
	// decrement the size value
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
					head = new AtomicReference<Node<V>>(new Node<V>(v));
                    tail = new AtomicReference<Node<V>>(new Node<V>(v));
                   // head = tail = new AtomicReference<Node<V>>(new Node<V>(v));
					increment();
					return;
				} else {
					Node<V> oldTail = tail.get();
					Node<V> newNode = new Node<>(v);
					if (oldTail != null) {
						oldTail.next = newNode;
						newNode.prev = oldTail;
						if (size() == 1) {
						    head.get().next = oldTail;
                        }
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
	 * return an iterator for this queue
	 *  the overrided method for iteratable
	 */
	@Override
	public Iterator<V> iterator() {
		return new QueItr<V>();
	}

	// the iterator for this queue
	// you can also add a parameter to itr this queue reversed
	private class QueItr<V> implements Iterator<V> {
		int cursor = 0;
		// if exists the next item
		public boolean hasNext() {
		    return Integer.valueOf(size()).compareTo(Integer.valueOf(cursor + 1)) >= 0;
        }

        // if exists the prev item
		public boolean hasPrev() {
		    return cursor > 0;
        }

        // get the next item from queue
        public V next() {
		    if (!hasNext()) {
		        throw new NoSuchElementException("no next itm in queue!");
            }

            // 获取首元素
            Node<V> next = (Node<V>) head.get();
            for (int i = 0 ; i <= cursor; i ++) {
                next = next.next;
            }
	        cursor ++;
            return next.value;
        }

        // get the previous item from queue
        public V prev() {
            if (!hasPrev()) {
                throw new NoSuchElementException("no previous itm in queue!");
            }
	        for (int i = cursor; i >= 0; i --) {
				throw new NotImplementedException();
	        }
            return null;
        }
	}

	@Override
	public void forEach(Consumer<? super V> action) {
	
	}
	
	public static void main(String[] args) {
		ConcurrentQueue a = ConcurrentQueue.of();
		for (int i = 0; i < 100; i++) {
			//Random rd = new Random();
			// a.offer(rd.nextInt(100));
			a.offer(i);
		}
        System.out.println(a.size());
		final Iterator iterator = a.iterator();
		List list = new ArrayList(a.size());
		
		while (iterator.hasNext()) {
			Object next = iterator.next();
			list.add(next);
		}
		System.out.println(list);
	}
}
