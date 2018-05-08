package com.practice.multithreading.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

class PoolThread extends Thread {

	private BlockingQueueImplLocks<Runnable> taskQueue = null;
	private boolean       isStopped = false;

	public PoolThread(BlockingQueueImplLocks<Runnable> queue){
		taskQueue = queue;
	}

	public void run(){
		while(!isStopped()){
			try{
				Runnable runnable = taskQueue.get();
				runnable.run(); // not start, we don't want to spawn further threads and can reuse this particular thread.
			} catch(Exception e){
				//log or otherwise report exception,
				//but keep pool thread alive.
			}
		}
	}

	public synchronized void doStop(){
		isStopped = true;
		this.interrupt(); //break pool thread out of dequeue() call.
	}

	public synchronized boolean isStopped(){
		return isStopped;
	}
}

class ThreadPoolImpl {
	private BlockingQueueImplLocks<Runnable> taskQueue = null;
	private List<PoolThread> threads = new ArrayList<PoolThread>();
	private boolean isStopped = false;

	public ThreadPoolImpl(int noOfThreads, int maxNoOfTasks){
		taskQueue = new BlockingQueueImplLocks<>(maxNoOfTasks);
		for(int i=0; i<noOfThreads; i++)threads.add(new PoolThread(taskQueue));
		for(PoolThread thread : threads)thread.start();
	}

	public synchronized void  execute(Runnable task) throws Exception{
		if(this.isStopped) throw new IllegalStateException("ThreadPool is stopped");
		this.taskQueue.put(task);
	}

	public synchronized void stop(){
		this.isStopped = true;
		for(PoolThread thread : threads)thread.doStop();
	}

	// source : https://github.com/SriramKeerthi/SimpleThreadpool/blob/master/threadpool/src/main/java/com/caffinc/threadpool/SimpleThreadpool.java
	// explaination here : https://self-learning-java-tutorial.blogspot.in/2014/03/awaittermination.html
	// if we call executerservice.shutdown(); , though it stops taking new tasks but it still moves forward
	// in order to avoid it moving forward, we can use awaitTermination , if waits upto timeout or upto all threads are done or upto interrupted.
	// shutdown vs shutdownnow vs awaittermination : https://stackoverflow.com/questions/18425026/shutdown-and-awaittermination-which-first-call-have-any-difference
	
	public void awaitTermination(long timeout) throws TimeoutException {
        if (this.isStopped) {
            throw new IllegalStateException("Threadpool not terminated before awaiting termination");
        }
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= timeout) {
            boolean flag = true;
            for (Thread thread : threads) {
                if (thread.isAlive()) { // check if any is alive.
                    flag = false;
                    break;
                }
            }
            if (flag) return; // if none is alive return.
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
        throw new TimeoutException("Unable to terminate threadpool within the specified timeout (" + timeout + "ms)");
    }
	
	
	public static void main(String[] args) throws Exception {
		ThreadPoolImpl threadPool=new ThreadPoolImpl(2, 10); //create 2 threads in ThreadPool 
		Runnable task=new Task();
		threadPool.execute(task);
		threadPool.execute(task);
		Thread.sleep(4000);
		threadPool.stop();
	}
	static class Task implements Runnable{  
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName()	+" is executing task.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
}


