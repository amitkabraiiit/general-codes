package com.practice.multithreading.producerconsumer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockingQueueImpl{
	int size ;
	Queue q ;
	int []arr ;
	AtomicInteger  currentSize = new AtomicInteger(0);
	Lock l = new ReentrantLock();
	/*
	 * We would like to keep waiting put threads and take threads in separate wait-sets so that we can use the optimization of only 
	 * notifying a single thread at a time when items or spaces become available in the buffer. This can be achieved using 
	 * two Condition instances.
	 */
	Condition putCond = l.newCondition();
	Condition takeCond = l.newCondition();
	public BlockingQueueImpl(int size) {
		this.size = size;
		arr = new int[size];
	}
	public  void put(int n) throws InterruptedException{ // synchronized is removed and l.lock() is added instead.
		l.lock();
		try{
			while(currentSize.get() == size){
				putCond.await(); // wait is removed and await is added instead.
			}
			arr[currentSize.getAndIncrement()] = n;
			takeCond.signal();
		}finally{
			l.unlock();
		}
	}
	public   int take() throws InterruptedException{
		l.lock();
		try{
			int data;
			while(currentSize.get() == 0){
				takeCond.await();
			}
			data = arr[currentSize.decrementAndGet()];
			putCond.signal();
			return data;
		}finally{
			l.unlock();
		}
	}
}

class NewProducer implements Runnable{
	BlockingQueueImpl b;
	private int productionPerProducer;
	public NewProducer(BlockingQueueImpl b, int productionPerProducer) {
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
class NewConsumer implements Runnable{
	BlockingQueueImpl b;
	private int consumptionPerConsumer;
	public NewConsumer(BlockingQueueImpl b, int consumptionPerConsumer) {
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

public class MultipleProdConsLockCond {

	public static void main(String[] args) {
		int queueSize = 5;
		int numProducer = 2;
		int numConsumer = 2;
		int productionPerProducer = 3;
		int consumptionPerConsumer = 3;
		BlockingQueueImpl b = new BlockingQueueImpl(queueSize);
		Thread producers[] = new Thread[numProducer];
		Thread consumers[] = new Thread[numConsumer];
		for(int i = 0 ; i < numProducer ; i++)	producers[i] = new Thread(new NewProducer(b,productionPerProducer),"producer-"+i);
		for(int i = 0 ; i < numConsumer ; i++)	consumers[i] = new Thread(new NewConsumer(b,consumptionPerConsumer),"consumer-"+i);
		for(int i = 0 ; i < numConsumer ; i++)  consumers[i].start();
		for(int i = 0 ; i < numProducer ; i++)  producers[i].start();
	}
}
