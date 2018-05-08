package com.practice.multithreading;


class OddNumberPrinter extends Thread{
	int num;
	Object o;
	public OddNumberPrinter(Object o, int num){
		this.num = num;
		this.o = o;
	}
	public void run() {
		synchronized (o) {
			while(num != 101){
				System.out.println(num);
				num+=2;
				o.notify();
				try {
					o.wait();	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}
}
class EvenNumberPrinter implements Runnable{
	int num;
	Object o;
	public EvenNumberPrinter(Object o, int num){
		this.num = num;
		this.o = o;
	}
	@Override
	public void run() {
		synchronized (o) {
			while(num != 102){
				try {
					o.wait();	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(num);
				num+=2;
				o.notify();
			}

		}
	}
}
public class PrintNumberEvenOddWaitNotify {

	public static void main(String[] args) throws InterruptedException  {
		Object o = new Object();

		OddNumberPrinter t1 = new OddNumberPrinter(o,1);
		Thread t2 = new Thread(new EvenNumberPrinter(o,2));
		t2.start();
		t1.start();

	}
}
