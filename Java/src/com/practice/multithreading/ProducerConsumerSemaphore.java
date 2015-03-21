package com.practice.multithreading;
import java.util.concurrent.Semaphore;

/*
 * Queue is making sure that get put is both as per producer consumer
 */

public class ProducerConsumerSemaphore {
	public static void main(String args[]) {
		Queue q = new Queue();
		new Consumer(q);
		new Producer(q);
	}
}

class Producer implements Runnable {
	Queue q;
	Producer(Queue q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}
	public void run() {
		for (int i = 0; i < 4; i++)
			q.put(i);
	}
}

class Consumer implements Runnable {
	Queue q;
	Consumer(Queue q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}
	public void run() {
		for (int i = 0; i < 4; i++)
			q.get();
	}
}

class Queue {
	int value;
	static Semaphore semProd = new Semaphore(1);
	static Semaphore semCon = new Semaphore(0);
	void get() {
		try {
			semCon.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
		System.out.println("Got: " + value);
		semProd.release();
	}
	void put(int n) {
		try {
			semProd.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		this.value = n;
		System.out.println("Put: " + n);
		semCon.release();
	}
}



