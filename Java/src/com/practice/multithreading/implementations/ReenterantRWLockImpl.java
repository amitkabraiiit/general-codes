package com.practice.multithreading.implementations;

public class ReenterantRWLockImpl {

	int readCount = 0;
	int writeCount = 0; 
	
	boolean readLock(){
		if(writeCount == 1){
			return false;
		}
		readCount++;
		return true;
	}
	
	boolean writeLock(){
		if(readCount != 0){
			return false;
		}
		readCount++;
		return true;
	}
	
	class TestThread extends Thread{
		Object o;
		ReenterantRWLockImpl rlock;
		public TestThread(Object o, ReenterantRWLockImpl rlock) {
			this.o = o;
			this.rlock = rlock;
		}
		public void run(){
			
		}
		
	}
	
	public static void main(String[] args) {
		ReenterantRWLockImpl rlock = new ReenterantRWLockImpl();
		Object o = new Object();
		/*
		 
		TestThread t2 = new TestThread(o, rlock);
		t1.start();
		t2.start();
		*/
	}
	
}
