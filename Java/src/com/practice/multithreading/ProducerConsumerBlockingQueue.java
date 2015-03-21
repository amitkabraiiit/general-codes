package com.practice.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerBlockingQueue {

    public static void main(String args[]){
  
     //Creating shared object
     BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();
 
     //Creating ProducerBlockingQueue and ConsumerBlockingQueue Thread
     Thread prodThread = new Thread(new ProducerBlockingQueue(sharedQueue));
     Thread consThread = new Thread(new ConsumerBlockingQueue(sharedQueue));

     //Starting producer and ConsumerBlockingQueue thread
     prodThread.start();
     consThread.start();
    }
 
}

//ProducerBlockingQueue Class in java
class ProducerBlockingQueue implements Runnable {

    private final BlockingQueue<Integer> sharedQueue;

    public ProducerBlockingQueue(BlockingQueue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            try {
                System.out.println("Produced: " + i);
                sharedQueue.put(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProducerBlockingQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

//ConsumerBlockingQueue Class in Java
class ConsumerBlockingQueue implements Runnable{

    private final BlockingQueue<Integer> sharedQueue;

    public ConsumerBlockingQueue (BlockingQueue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
  
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Consumed: "+ sharedQueue.take());
            } catch (InterruptedException ex) {
                Logger.getLogger(ConsumerBlockingQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  
  
}

