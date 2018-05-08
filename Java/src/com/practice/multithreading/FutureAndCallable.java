package com.practice.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

class CallableThread implements Callable<Integer>{ // could be string
	@Override
	public Integer call() throws Exception {
		return 10;		
	}
}

public class FutureAndCallable {

	static void callableUsingFutureAndExecutorService() throws InterruptedException, ExecutionException{
		Callable<Integer> callable1 = new CallableThread();
		Callable<Integer> callable2 = new CallableThread();
		ExecutorService es = Executors.newFixedThreadPool(2);
		Future<Integer> f1 = es.submit(callable1);
		Future<Integer> f2 = es.submit(callable2);
		while(true){
			if(f1.isDone() && f2.isDone()){
				System.out.println("f31 and f32 bother are done : "+f1.get()+" "+f2.get());
				es.shutdown();break;
			}
			if(f1.isDone() && !f2.isDone())System.out.println("f31 is done : "+f1.get());
			if(!f1.isDone() && f2.isDone())System.out.println("f32 is done : "+f1.get());
		}
	}


	static void callableUsingFutureTaskAndExecutorService() throws InterruptedException, ExecutionException{
		Callable<Integer> callable1 = new CallableThread();
		Callable<Integer> callable2 = new CallableThread();
		ExecutorService es = Executors.newFixedThreadPool(2);
		FutureTask<Integer> f1 = new FutureTask<>(callable1);
		FutureTask<Integer> f2 = new FutureTask<>(callable2);
		es.submit(f1); // or we can use es.execute(f1) , both are same submit returns a future but execute doesn't.
		es.submit(f2); // or we can use es.execute(f2) , both are same submit returns a future but execute doesn't.
		while(true){
			if(f1.isDone() && f2.isDone()){
				System.out.println("f21 and f22 bother are done : "+f1.get()+" "+f2.get());
				es.shutdown();break;
			}
			if(f1.isDone() && !f2.isDone())System.out.println("f21 is done : "+f1.get());
			if(!f1.isDone() && f2.isDone())System.out.println("f22 is done : "+f1.get());
		}

	}

	static void callableUsingFutureTask() throws InterruptedException, ExecutionException{
		Callable<Integer> callable1 = new CallableThread();
		Callable<Integer> callable2 = new CallableThread();
		FutureTask<Integer> f1 = new FutureTask<>(callable1);
		FutureTask<Integer> f2 = new FutureTask<>(callable2);
		f1.run();f2.run();
		while(true){
			if(f1.isDone() && f2.isDone()){
				System.out.println("f11 and f12 bother are done : "+f1.get()+" "+f2.get());
				break;
			}
			if(f1.isDone() && !f2.isDone())System.out.println("f11 is done : "+f1.get());
			if(!f1.isDone() && f2.isDone())System.out.println("f12 is done : "+f1.get());
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException  {
		callableUsingFutureTask();
		callableUsingFutureTaskAndExecutorService();
		callableUsingFutureAndExecutorService();
	}



}
