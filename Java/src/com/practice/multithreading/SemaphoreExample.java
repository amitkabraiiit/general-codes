package com.practice.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

class Common{
	private List<Integer> list1 = Arrays.asList(new Integer[]{1,2,3,4,5});
	private List<Integer> list2 = Arrays.asList(new Integer[]{1,2,3,4,5});
	private List<Integer> list3 = Arrays.asList(new Integer[]{1,2,3,4,5});
	private int listSize = 5;

	private Semaphore semaphore = null;

	public Common() {
		semaphore = new Semaphore(1);
	}

	public void print() throws InterruptedException{
		semaphore.acquire();
		for(int i = 0;i<listSize;i++){
			System.out.println(list1.get(i) + " "+ list2.get(i)+" "+ list3.get(i));
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
		t1.start();t2.start();t3.start();
		
		checkBits(8,7); // 1000 , 0111
	}

	private static void checkBits(int m , int n) {
		int count=0; // 100 , 001
		for (int i = 32-1;i >= 0;i--)
	    {
	        int a = (n >> i)& 1;
	        int b = (m >> i)& 1;
	        if (a != b)
	            count++;
	    }
		System.out.println(count);
	}
}
