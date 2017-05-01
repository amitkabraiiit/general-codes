package com.practice.multithreading.producerconsumer;

import java.util.concurrent.atomic.AtomicInteger;

class BlockingQueueImpll{
	int size ;
	Queue q ;
	int []arr ;
	AtomicInteger  currentSize = new AtomicInteger(0);
	public BlockingQueueImpll(int size) {
		this.size = size;
		arr = new int[size];
	}
	public synchronized void put(int n) throws InterruptedException{
		while(currentSize.get() == size){
			wait();
		}
		arr[currentSize.getAndIncrement()] = n;
		notify(); // notifyAll also works fine
	}
	public synchronized  int take() throws InterruptedException{
		int data;
		while(currentSize.get() == 0){
			wait();
		}
		data = arr[currentSize.decrementAndGet()];
		notify(); // notifyAll also works fine
		return data;
	}
	
}

class NewProducerr implements Runnable{
	BlockingQueueImpll b;
	private int productionPerProducer;
	public NewProducerr(BlockingQueueImpll b, int productionPerProducer) {
		this.b = b;
		this.productionPerProducer = productionPerProducer;
	}

	@Override
	public void run() {
		for(int i = 0 ; i < productionPerProducer; i++){
			try{
				System.out.println(Thread.currentThread().getName() + " is adding "+i);
				b.put(i);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}		
}
class NewConsumerr implements Runnable{
	BlockingQueueImpll b;
	private int consumptionPerConsumer;
	public NewConsumerr(BlockingQueueImpll b, int consumptionPerConsumer) {
		this.b = b;
		this.consumptionPerConsumer = consumptionPerConsumer;
	}

	@Override
	public void run() {
		for(int i = 0 ; i < consumptionPerConsumer; i++){
			try {
				System.out.println(Thread.currentThread().getName() + " just took out ---> "+b.take());
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}		
}
public class MultiProdConsWaitNotify {


	public static void main(String[] args) {
		int queueSize = 5;
		int numProducer = 2;
		int numConsumer = 2;
		int productionPerProducer = 3;
		int consumptionPerConsumer = 3;
		BlockingQueueImpll b = new BlockingQueueImpll(queueSize);
		Thread producers[] = new Thread[numProducer];
		Thread consumers[] = new Thread[numConsumer];
		for(int i = 0 ; i < numProducer ; i++)	producers[i] = new Thread(new NewProducerr(b,productionPerProducer),"producer-"+i);
		for(int i = 0 ; i < numConsumer ; i++)	consumers[i] = new Thread(new NewConsumerr(b,consumptionPerConsumer),"consumer-"+i);
		for(int i = 0 ; i < numConsumer ; i++)  consumers[i].start();
		for(int i = 0 ; i < numProducer ; i++)  producers[i].start();
	}
}
