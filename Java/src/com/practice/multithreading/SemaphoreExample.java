package com.practice.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

class Common{
	private List<Integer> list1 = Arrays.asList(new Integer[]{1,2,3});
	private List<Integer> list2 = Arrays.asList(new Integer[]{1,2,3});
	private int listSize = 3;

	private Semaphore semaphore = null;

	public Common() {
		semaphore = new Semaphore(2);
	}

	public void print() throws InterruptedException{
		Thread.sleep(100);
		semaphore.acquire();
		for(int i = 0;i<listSize;i++){
			System.out.println(Thread.currentThread().getName()+" --> "+list1.get(i) + " "+ list2.get(i));
		}
		semaphore.release();
	}
}

public class SemaphoreExample {

	public static void main(String[] args) {
		final Common object = new Common();
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					object.print();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		Thread t3 = new Thread(runnable);
		Thread t4 = new Thread(runnable);
		Thread t5 = new Thread(runnable);
		t1.start();t2.start();t3.start();t4.start();t5.start();
		
	}
}
