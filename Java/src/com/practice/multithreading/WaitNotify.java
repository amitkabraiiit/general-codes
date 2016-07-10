package com.practice.multithreading;

class Message {
	private String msg;
	public Message(String str){
		this.msg=str;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String str) {
		this.msg=str;
	}

}

class Waiter implements Runnable{

	private Message msg;
	long startTime, endTime ;
	public Waiter(Message m){
		this.msg=m;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println();
		this.startTime = System.currentTimeMillis();
		synchronized (msg) {
			System.out.println(name+" entered synchronized block in "+ (System.currentTimeMillis() - this.startTime) +" seconds");
				try {
					msg.wait();
					System.out.println(name+" after the wait");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
			//process the message now
			System.out.println(name+" processed: "+msg.getMsg());
		}
		System.out.println("Total time by thread "+name +" is "+( System.currentTimeMillis() - this.startTime ));
	}

}

class Notifier implements Runnable {

	private Message msg;

	public Notifier(Message msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		synchronized (msg) {
			System.out.println("notfiying all");
			msg.notifyAll();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg.setMsg(name+" got called");  
		}
	}
}

class WaitNotify { 
	public static void main(String[] args) throws InterruptedException {
		Message msg = new Message("Unique message");
		Waiter waiter1 = new Waiter(msg);
		new Thread(waiter1,"waiter1").start();

		Waiter waiter2 = new Waiter(msg);
		new Thread(waiter2, "waiter2").start();

		Waiter waiter3 = new Waiter(msg);
		new Thread(waiter3, "waiter3").start();
		Thread.sleep(100);

		System.out.println("Starting notifier");
		Notifier notifier = new Notifier(msg);
		new Thread(notifier, "notifier").start();
	}

}
