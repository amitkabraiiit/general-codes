package com.practice.multithreading;

import java.util.concurrent.atomic.AtomicInteger;


public class PrintNumberInSequenceFromThreeThreads {
	AtomicInteger sharedOutput = new AtomicInteger(0);

	static class MyThread implements Runnable{
		Flag f ;
		int id;
		String value;
		public MyThread(Flag f, int id, String value) {
			this.f = f;
			this.id = id;
			this.value = value;
		}

		@Override
		public void run() {
			while(true){ // infinite print
				synchronized (f) {
					while(f.value != id){
						try {
							f.wait();
						} catch (InterruptedException e) {}
					}
					System.out.println(Thread.currentThread()+"  "+	value);
					f.value = (id+1)%3;
					f.notifyAll();
				}
			}
		}
	}        

	static class Flag{
		int value = 0;
	}

	public static void main(String[] args) {
		Flag f = new Flag();
		MyThread t1 = new MyThread(f,0,"A");
		MyThread t2 = new MyThread(f,1,"B");
		MyThread t3 = new MyThread(f,2,"C");

		Thread ts1 = new Thread(t1);
		Thread ts2 = new Thread(t2);
		Thread ts3 = new Thread(t3);
		ts1.setName("Thread1");
		ts2.setName("Thread2");
		ts3.setName("Thread3");
		ts2.start();
		ts1.start();
		ts3.start();

	}
}


