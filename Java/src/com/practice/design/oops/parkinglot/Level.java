package com.practice.design.oops.parkinglot;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/* Represents a level in a parking garage */
public class Level {
	private int floor;
	private ParkingSpot[] spots;
	private int availableSpots = 0; // number of free spots
	private static final int SPOTS_PER_ROW = 10;
	Lock checkParkingentry = new ReentrantLock();
	Lock []entry = new ReentrantLock[3];
	Lock []exit = new ReentrantLock[3];

	public Level(int flr, int numberSpots) {
		floor = flr;
		spots = new ParkingSpot[numberSpots];
		int largeSpots = numberSpots / 4;
		int bikeSpots = numberSpots / 4;
		int compactSpots = numberSpots - largeSpots - bikeSpots;
		for (int i = 0; i < numberSpots; i++) {
			VehicleSize sz = VehicleSize.Motorcycle;
			if (i < largeSpots) {
				sz = VehicleSize.Large;
			} else if (i < largeSpots + compactSpots) {
				sz = VehicleSize.Compact;
			}
			int row = i / SPOTS_PER_ROW;
			spots[i] = new ParkingSpot(this, row, i, sz);
		}
		availableSpots = numberSpots;
	}

	public int availableSpots() {
		return availableSpots;
	}

	/* Try to find a place to park this vehicle. Return false if failed. */
	public boolean parkVehicle(Vehicle vehicle, int entryGate) {
		int spotNumber = 0;
		try{
			System.out.println("Thread "+Thread.currentThread().getName()+" entry gate1" +entryGate);
			entry[entryGate].lock();
			if (availableSpots() < vehicle.getSpotsNeeded()) {
				return false;
			}
			spotNumber = findAvailableSpots(vehicle);
			if (spotNumber < 0) {
				return false;
			}
		}
		finally{
			System.out.println("Thread "+Thread.currentThread().getName()+" entry gate2" +entryGate);
			entry[entryGate].unlock();
		}
		return parkStartingAtSpot(spotNumber, vehicle);
	}

	/* Park a vehicle starting at the spot spotNumber, and continuing until vehicle.spotsNeeded. */
	private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle) {
		vehicle.clearSpots(); // Amit - I guess not required.
		boolean success = true;
		for (int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++) { // since consecutive spots will be used.
			success &= spots[i].park(vehicle);
		}
		availableSpots -= vehicle.spotsNeeded;
		return success;
	}

	/* find a spot to park this vehicle. Return index of spot, or -1 on failure. */
	private int findAvailableSpots(Vehicle vehicle) {
		try{
			checkParkingentry.lock();

			int spotsNeeded = vehicle.getSpotsNeeded();
			int lastRow = -1;
			int spotsFound = 0;
			for (int i = 0; i < spots.length; i++) {
				ParkingSpot spot = spots[i];
				if (lastRow != spot.getRow()) {
					spotsFound = 0;
					lastRow = spot.getRow();
				}
				if (spot.canFitVehicle(vehicle)) {
					spotsFound++;
				} else { 
					spotsFound = 0; // since we want consecutive spots, bus needs 5 consecutive large spots.
				}
				if (spotsFound == spotsNeeded) {
					return i - (spotsNeeded - 1);
				}
			}
		}finally{
			checkParkingentry.unlock();
		}
		return -1;
	}

	public void print() {
		int lastRow = -1;
		for (int i = 0; i < spots.length; i++) {
			ParkingSpot spot = spots[i];
			if (spot.getRow() != lastRow) {
				System.out.print("  ");
				lastRow = spot.getRow();
			}
			spot.print();
		}
	}

	/* When a car was removed from the spot, increment availableSpots */
	public void spotFreed() {
		availableSpots++;
	}
}
