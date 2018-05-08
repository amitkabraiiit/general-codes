package com.practice.multithreading;


import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadPoolExample {

    public static void main(String args[]) {
       ExecutorService service = Executors.newFixedThreadPool(5);
       for (int i =0; i<8; i++){
           service.execute(new Task(i));
       }
       service.shutdown();
    }
}

final class Task implements Runnable{
    private int taskId;
  
    public Task(int id){
        this.taskId = id;
    }
  
    @Override
    public void run() {
	System.out.println("Amit");
	try{
		Thread.sleep(15);
	}catch(Exception e){
		System.out.println(e);
	}
	System.out.println("Kabra");
        System.out.println("Task ID : " + this.taskId +" performed by "  + Thread.currentThread().getName());
    }
  
}


