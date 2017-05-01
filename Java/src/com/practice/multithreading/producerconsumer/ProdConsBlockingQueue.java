package com.practice.multithreading.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//ProducerBlockingQueue Class in java
class ProducerBlockingQueue implements Runnable {

	private final BlockingQueue<Integer> sharedQueue;
	private int productionPerProducer;

	public ProducerBlockingQueue(BlockingQueue<Integer> sharedQueue, int productionPerProducer) {
		this.sharedQueue = sharedQueue;
		this.productionPerProducer = productionPerProducer;

	}

	@Override
	public void run() {
		for(int i=0; i<productionPerProducer; i++){
			try {
				System.out.println(Thread.currentThread().getName()+" Produced: " + i);
				sharedQueue.put(i);
			} catch (Exception ex) {
				System.out.println("Producer exception " +ex);
			}
		}
	}

}

//ConsumerBlockingQueue Class in Java
class ConsumerBlockingQueue implements Runnable{

	private final BlockingQueue<Integer> sharedQueue;
	private int consumptionPerConsumer;

	public ConsumerBlockingQueue (BlockingQueue<Integer> sharedQueue, int consumptionPerConsumer) {
		this.sharedQueue = sharedQueue;
		this.consumptionPerConsumer = consumptionPerConsumer;

	}

	@Override
	public void run() {
		//while(true){
		for(int i=0; i<consumptionPerConsumer; i++){
			try {
				System.out.println(Thread.currentThread().getName()+" Consumed -----> "+ sharedQueue.take());
				Thread.sleep(200);
			} catch (InterruptedException ex) {
				System.out.println("Consumer exception " +ex);
			}
		}
	}


}

public class ProdConsBlockingQueue {

	public static void main(String args[]){

		int queueSize = 5;
		int numProducer = 2;
		int numConsumer = 2;
		int productionPerProducer = 3;
		int consumptionPerConsumer = 3;
		//Creating shared object
		BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>(queueSize);
		Thread producers[] = new Thread[numProducer];
		Thread consumers[] = new Thread[numConsumer];
		for(int i = 0 ; i < numProducer ; i++)	producers[i] = new Thread(new ProducerBlockingQueue(sharedQueue,productionPerProducer),"producer-"+i);
		for(int i = 0 ; i < numConsumer ; i++)	consumers[i] = new Thread(new ConsumerBlockingQueue(sharedQueue,consumptionPerConsumer),"consumer-"+i);
		for(int i = 0 ; i < numConsumer ; i++)  consumers[i].start();
		for(int i = 0 ; i < numProducer ; i++)  producers[i].start();

	}

}



