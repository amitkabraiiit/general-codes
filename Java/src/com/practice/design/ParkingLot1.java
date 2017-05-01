package com.practice.design;

import java.util.Arrays;

interface Vehicle{	
	public void payAndEnter();
	public void exit();
}

class Car implements  Vehicle{
	Level l ;
	int carNum;
	int slotNum;
	public Car(Level l, int carNum) {
		this.l = l;
		this.carNum = carNum;
	}

	public void payAndEnter(){
		slotNum = l.getFreeSlotCar();
		if(slotNum !=0)
			System.out.println("Car "+carNum+" occupied "+slotNum+ " slot in level "+l.id);
		else
			System.out.println("Car "+carNum+" got no slot in level "+l.id);	
	}

	@Override
	public void exit() {
		l.setSlotFreeForCar(slotNum);		
	}
}
class Bike implements Vehicle{

	Level l ;
	int bikeNum;
	int slotNum;
	public Bike(Level p) {
		this.l = l;
	}
	public void payAndEnter(){
		int slotNum = l.getFreeSlotBike();
		if(slotNum !=0)
			System.out.println("Bike "+bikeNum+" occupied "+slotNum+ " slot in level "+l.id);
		else
			System.out.println("Bike "+bikeNum+" got no slot in level "+l.id);}
	@Override
	public void exit() {
		l.setSlotFreeForBike(slotNum);		
	}
}

class Level{
	int size ;
	boolean bikeSlot[] = null;
	boolean carSlot[] = null;
	int id;
	public Level(int id, int size) {
		this.id = id;
		this.size = size;
		bikeSlot = new boolean[size+1];
		carSlot = new boolean[size+1];
		Arrays.fill(bikeSlot, true); // false means slot is full
		Arrays.fill(carSlot, true);
	}
	int freeSlotBike = 1;
	int freeSlotCar = 1;

	public void getCarSlotStatus(){
		System.out.println("Car slot is filled upto "+freeSlotCar +" for level "+id );
	}
	public void getBikeSlotStatus(){
		System.out.println("Car slot is filled upto "+freeSlotBike+" for level "+id);
	}
	public void setSlotFreeForCar(int slotNum) {
		System.out.println("Exiting slot "+slotNum+ " at level "+id);
		carSlot[slotNum] = true;
	}
	public void setSlotFreeForBike(int slotNum) {
		System.out.println("Exiting slot "+slotNum+ " at level "+id);
		bikeSlot[slotNum] = true;
	}
	public int getFreeSlotBike(){
		if(freeSlotBike  > size){
			System.out.println("All slots full for bikes for level "+id);
			return 0;
		}
		int j=1;
		for(j=1;j<size;j++){
			if(bikeSlot[j] == true){
				freeSlotBike++;
				bikeSlot[j] = false;
				break;
			}
		}
		return j;
	}
	public int getFreeSlotCar(){	
		if(freeSlotCar  > size){
			System.out.println("All slots full for cars  for level "+id);
			return 0;
		}
		int j=1;
		for(j=1;j<=size;j++){
			if(carSlot[j] == true){
				freeSlotCar++;
				carSlot[j] = false;
				break;
			}
		}
		return j;
	}
	public boolean isCarSlotAvailable(){
		for(int j=1;j<=size;j++){
			if(carSlot[j] == true)return true;
		}
		return false;
	}
	public boolean isBikeSlotAvailable(){
		for(int j=1;j<=size;j++)	if(bikeSlot[j] == true)return true;
		return false;
	}
}

public class ParkingLot1 {

	static int size =10;
	static Level l1 = new Level(1, size);
	static Level l2 = new Level(2, size);
	static Level l3 = new Level(3, size);


	public static void main(String args[]){
		Car c[] = new Car[3*size+1];
		for(int i=1; i<=size*3;i++){
			if(l1.isCarSlotAvailable()){
				c[i] = new Car(l1,i);
			}else if(l2.isCarSlotAvailable()){
				c[i] = new Car(l2,i);
			}else{
				c[i] = new Car(l3,i);
			}
			c[i].payAndEnter();
		}
		for(int i=1; i<=size*3;i++){
				c[i].exit();
		}
	}

}
