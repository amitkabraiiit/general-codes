package com.practice.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OddEvenUsingWaitNotify {
	private static Object shared = new Object();
	private static boolean isOddPrintedFirst = false;

	static class OddTask implements Runnable { //=========== Task1 class prints odd =====
		@Override
		public void run() {
			int i;
			try	{
				for(i = 1;i<=5;i+=2){
					synchronized (shared) {
						if(i==1)isOddPrintedFirst = true;
						System.out.println(Thread.currentThread().getName()+" == "+i);
						shared.notify();
						shared.wait();
					}
				}
			} catch (Exception e) {		e.printStackTrace();
			}
		}
	}
	static class EvenTask implements Runnable {	//==== Task2 : prints even =======
		@Override
		public void run() {
			int i;
			try	{
				for(i = 2;i<=5;i+=2){
					synchronized (shared) {
						if(isOddPrintedFirst == false){		shared.notify();shared.wait();		}
						System.out.println(Thread.currentThread().getName()+" == "+i);
						shared.notify();
						shared.wait();
					}
				} 
			}catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	public static void main(String[] a) throws InterruptedException
	{
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(new OddTask());
		executorService.submit(new EvenTask());
		
		executorService.shutdown();
	}
}
