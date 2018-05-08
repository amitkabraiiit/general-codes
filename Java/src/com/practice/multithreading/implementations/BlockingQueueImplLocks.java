package com.practice.multithreading.implementations;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class BlockingQueueImplLocks<T> {

	Lock lock;
	Condition isFullCondition;
	Condition isEmptyCondition;
	int limit;
	Queue<T> q = new LinkedList<>();

	public BlockingQueueImplLocks(int limit) {
		this.limit = limit;
		lock = new ReentrantLock();
		isFullCondition = lock.newCondition();
		isEmptyCondition = lock.newCondition();
	}

	public void put (T i) throws InterruptedException {
		lock.lock();
		try {
			while (q.size() == limit) {
				isFullCondition.await();
			}
			q.add(i);
			// 2 conditions so that only threads which are waiting for empty condition would get awake.
			// more cleaner as compare to notifyAll where all threads get awake waiting on that object
			isEmptyCondition.signalAll(); 
		} finally {
			lock.unlock();
		}
	}

	public T get() throws InterruptedException {
		T t;
		lock.lock();
		try {
			while (q.size() == 0) {
				isEmptyCondition.await();
			}
			t = q.poll();
			isFullCondition.signalAll();
		} finally { 
			lock.unlock();
		}
		return t;
	}
}
