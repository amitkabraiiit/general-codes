package com.practice.multithreading.implementations;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueImplWaitNotify {

	int limit;
	Queue<Integer> q = new LinkedList<>();
	public BlockingQueueImplWaitNotify(int limit) {
		this.limit = limit;
	}
	
	public synchronized void put(int i) throws InterruptedException{
		while(q.size() == limit){
			wait(); // lock would be released here
		}
		q.add(i);
		/*
		 * We can actually call notifyAll for each put call but to avoid unncessary interrupt
		 * Only notify threads when other threads are waiting for q.size() == 0 condition.
		 * See : BlockingQueueImplLocks.java where it uses 2 conditions to wait on so that we don't have to add this if check. 
		 */
		if(q.size() == 1)notifyAll();
	}
	
	public synchronized int get() throws InterruptedException{
		while(q.size() == 0){
			wait(); // lock would be released here
		}
		return q.peek();
	}
	
	public synchronized int poll() throws InterruptedException{
		int ele ;
		while(q.size() == 0){
			wait();
		}
		ele = q.poll();
		/*
		 * We can actually call notifyAll for each get call but to avoid unncessary interrupt
		 * Only notify threads when other threads are waiting for q.size() == limit condition. 
		 */
		if(q.size() == limit -1)notifyAll();
		return ele;
	}
}
