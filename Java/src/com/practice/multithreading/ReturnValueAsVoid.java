package com.practice.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Example helps to run doSum method asynchronously.
 */

public class ReturnValueAsVoid implements Callable<Void>{

	int i,j;
	public ReturnValueAsVoid(int i ,int j ) {
		this.i = i;
		this.j = j;
	}
	public void doSum() throws InterruptedException{
		Thread.sleep(3000);
		System.out.println(this.i+this.j);
	}
	
	public Void call(){
		try {
			doSum();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ReturnValueAsVoid v = new ReturnValueAsVoid(10,20);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> f = executor.submit(v);		
	}	
}
