package com.practice.multithreading;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class ProducerConsumerMultipleConsumer {

    static Object LOCK = new Object();

    static LinkedList<Integer> list = new LinkedList<Integer>();
    static Semaphore sem = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);

    static class Consumer extends Thread {
        String name;
        public Consumer(String name) {
            this.name = name;
        }
        public void run() {
            try {

                while (true) {
                    sem.acquire(1);
                    mutex.acquire();
                    System.out.println("Consumer \""+name+"\" read: "+list.removeFirst());
                    mutex.release();
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    static class Producer extends Thread {
        public void run() {
            try {

                int N = 0;

                while (true) {
                    mutex.acquire();
                    list.add(new Integer(N++));
                    mutex.release();
                    sem.release();
                    Thread.sleep(500);
                    if(N==10){
                    	break;
                    }
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    public static void main(String [] args) throws InterruptedException {
        new Producer().start();
        Consumer c1 = new Consumer("Alice");
        Consumer c2 = new Consumer("Bob");
        c1.start();
        c2.start();
    }
}