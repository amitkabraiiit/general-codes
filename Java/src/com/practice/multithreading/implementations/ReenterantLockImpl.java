package com.practice.multithreading.implementations;

public class ReenterantLockImpl {

	boolean isLocked = false;
	Thread lockedBy;
	int lockedCount = 0;
	public synchronized void lock() throws InterruptedException{
		Thread callingThread = Thread.currentThread();
		while(isLocked && callingThread!=lockedBy)	wait();
		lockedCount++;
		lockedBy = callingThread;
		isLocked = true;
		notifyAll();
	}

	public synchronized void unLock() throws Exception{
		if(!isLocked)throw new Exception();
		if(Thread.currentThread() == this.lockedBy){
			lockedCount--;
			if(lockedCount == 0){
				isLocked = false;
				lockedBy = null;
				notifyAll();
			}
		}
	}
}
