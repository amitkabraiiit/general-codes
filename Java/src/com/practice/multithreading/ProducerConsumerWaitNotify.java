package com.practice.multithreading;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Consumer and producer are making sure that what one produce is what one gets
 */

public class ProducerConsumerWaitNotify {

	public static void main(String args[]) {

		final Queue<Integer> sharedQ = new LinkedList<Integer>();

		Thread producer = new ProducerWaitNotify(sharedQ);
		Thread consumer = new ConsumerWaitNotify(sharedQ);

		consumer.start();
		producer.start();

	}
}

class ProducerWaitNotify extends Thread {
	private final Queue<Integer> sharedQ;
	public ProducerWaitNotify(Queue<Integer> sharedQ) {
		this.sharedQ = sharedQ;
	}
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			synchronized (sharedQ) {
				//waiting condition - wait until Queue is not empty
				while (sharedQ.size() >= 1) {
					try {
						System.out.println("Queue is full, waiting");
						sharedQ.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				System.out.println("producing : " + i);
				sharedQ.add(i);
				sharedQ.notify();
			}
		}
	}
}

class ConsumerWaitNotify extends Thread {
	private final Queue<Integer> sharedQ;
	public ConsumerWaitNotify(Queue<Integer> sharedQ) {
		super("ConsumerWaitNotify");
		this.sharedQ = sharedQ;
	}
	@Override
	public void run() {
		while(true) {
			synchronized (sharedQ) {
				//waiting condition - wait until Queue is not empty
				while (sharedQ.size() == 0) {
					try {
						System.out.println("Queue is empty, waiting");
						sharedQ.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				int number = sharedQ.poll();
				System.out.println("consuming : " + number );
				sharedQ.notify();
				//termination condition
				if(number == 4){break; }
			}
		}
	}
}


