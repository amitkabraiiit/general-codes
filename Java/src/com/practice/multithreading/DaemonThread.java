package com.practice.multithreading;

public class DaemonThread {

    public static void main(String[] args) {
        new WorkerThread().start();
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {}
        System.out.println("Main Thread ending") ;
    }

}
class WorkerThread extends Thread {

    public WorkerThread() {
      //  setDaemon(true) ;
        // When true, (i.e. when it's a daemon thread),the Worker thread terminates when the main thread terminates.        
        // When false, (i.e. when it's a user thread),the Worker thread continues to run.

    }

    public void run() {
        int count=0 ;
        while (true) {
            System.out.println("Hello from Worker "+count++) ;
            try {
                sleep(5000);
            } catch (InterruptedException e) {}
        }
    }
}