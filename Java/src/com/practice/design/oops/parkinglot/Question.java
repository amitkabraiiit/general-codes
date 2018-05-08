package com.practice.design.oops.parkinglot;


public class Question {


	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}

	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}
	
	/*
	 * Flow : 
	 * 1) Create parking lot. Its constructor will create 5 levels each with 30 slots.
	 * 2) Level constructor will create 3 types of slots : 30/4 consecutive motorcycle, 30/4 consecutive car and remaining consecutive bus.
	 * 3) 3 threads continuously sending vehicles, where each thread will check at each level one by one that can that vehicle be parked at that parking level.
	 * 4) Check means if the vechicle.required slots are available. Are they continuous ?. If yes, park vehicle at the slot, if the vehicle fits the place.
	 * 5) Level.parkVechile should start with lock.lock and ends with lock.unlock.
	 * 6) We can have similar threads running which can pick cars from each level and free the slots in parallel.
	 * Note: we can as well have
	 * == Each level can have say 3 entry and 3 exit gates. int []entry = new int[3], int []exit = new int[3];
	 * == 3 threads in #3 above will add car and  
	 */
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		ParkingLot lot = new ParkingLot();

		
		Runnable r = new Runnable(){
			public void run(){
				Vehicle v = null;
				while (v == null || lot.parkVehicle(v)) {
					try {Thread.sleep(100* Integer.parseInt(Thread.currentThread().getName()));} catch (InterruptedException e) {}
					lot.print();
					int r = randomIntInRange(0, 10);
					if (r < 2) {
						v = new Bus();
					} else if (r < 4) {
						v = new Motorcycle();
					} else {
						v = new Car();
					}
					System.out.print("\nParking a ");
					v.print();
					System.out.println("");
				}
			}
		};
		Thread t1 = new Thread(r,"1");
		Thread t2 = new Thread(r,"2");
		Thread t3 = new Thread(r,"3");
		t1.start();t2.start();t3.start();
		t1.join();t2.join();t3.join();
		System.out.println("Parking Failed. Final state: ");
		lot.print();
	}

}
