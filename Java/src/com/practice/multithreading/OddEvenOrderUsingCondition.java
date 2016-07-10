package com.practice.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenOrderUsingCondition {

	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	private static boolean isOddPrintedFirst = false;

	static class OddTask implements Runnable { //=========== Task1 class prints odd =====
		@Override
		public void run() {
			int i;
			try	{
				lock.lock();
				for(i = 1;i<=5;i+=2){
					if(i==1)isOddPrintedFirst = true;
					System.out.println(Thread.currentThread().getName()+" == "+i);
					condition.signal();	condition.await(); 	
				}
			} catch (Exception e) {		e.printStackTrace();
			}finally{lock.unlock();}
		}
	}
	static class EvenTask implements Runnable {	//==== Task2 : prints even =======
		@Override
		public void run() {
			int i;
			try	{
				lock.lock();
				for(i = 2;i<=5;i+=2){
					if(isOddPrintedFirst == false){		condition.signal(); 	condition.await();		}
					System.out.println(Thread.currentThread().getName()+" == "+i);
					condition.signal();	condition.await(); 
				}
			} catch (InterruptedException e) {e.printStackTrace();
			}finally{lock.unlock();}
		}
	}
	public static void main(String[] a) throws InterruptedException
	{
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(new EvenTask());
		executorService.submit(new OddTask());
		executorService.shutdown();
	}
}