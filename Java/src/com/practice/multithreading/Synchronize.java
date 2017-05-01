package com.practice.multithreading;

public class Synchronize {

	public  synchronized void test1() throws InterruptedException{
		System.out.println("Inside test1, now I will wait for 5 sec and still Test2 thread won't be able to run since I hold the monitor here ..:)");
		Thread.sleep(5000);
	}
	public  synchronized void test2(){
		System.out.println("Inside test2");
	}
	
	static class Test1 implements Runnable{
		Synchronize s;
		Test1(Synchronize s){
			this.s = s; 
		}
		@Override
		public void run() {
			try {
				s.test1();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	static class Test2 implements Runnable{
		Synchronize s;
		Test2(Synchronize s){
			this.s = s; 
		}
		@Override
		public void run() {
			s.test2();
		}	
	}
	public static void main(String[] args) {
		Synchronize s = new Synchronize();
		new Thread(new Test1(s)).start();
		new Thread(new Test2(s)).start();
		/*
		 * Output
		 * Inside test1
		 * Sleep of 5 sec
		 * Inside test2
		 */
	}
	
}
