package com.practice.multithreading;

import java.util.List;
import java.util.ArrayList;

public class Schedule123 {
    
    List<Thread> printers;
    Integer currentPrinterNumber = 1;
    
    public static void main (String[] args) throws Exception {
        Schedule123 aSchedule123 = new Schedule123();
        aSchedule123.initPrinters();
        aSchedule123.startPrinters();
    }
    
    public void initPrinters () {
        printers = new ArrayList<Thread>();
        for (int i = 1; i < 4; ++i) {
            Printer p = new Printer(i, this);
            Thread t = new Thread(p);
            printers.add(t);
        }
    }
    
    public void startPrinters () throws Exception {
        for (Thread p : printers) {
            p.start();
            Thread.sleep(100);
        }
    }
    
    public boolean isMyTurn (Printer p) {
        
        if (p.getInt() == currentPrinterNumber) {
            return true;
        }
        
        return false;
    }
    
    public void complete () {
        
        synchronized (this) {
            currentPrinterNumber++;
            if (currentPrinterNumber > printers.size()) {
                currentPrinterNumber = 1;
            }
            notifyAll();
        }
    }
}

class Printer implements Runnable {
    
    private Integer myInt;
    private Schedule123 mySchedule123;
    
    Integer getInt () {
        return myInt;
    }
    
    public Printer (Integer i, Schedule123 m) {
        myInt = i;
        mySchedule123 = m;
    }
    
    public void run () {
        
        synchronized (mySchedule123) {
            while (true) {
                try {
                    if (mySchedule123.isMyTurn(this)) {
                        printMyInt();
                    }
                    
                    mySchedule123.wait();
                    
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    private void printMyInt () {
        synchronized (mySchedule123) {
            System.out.println(myInt + " ");
            mySchedule123.complete();
        }
    }
}
