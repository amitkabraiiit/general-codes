package com.practice.design;

import java.util.Arrays;

abstract class VehicleForParking{	
	Level2 l ;
	int slotNum = 0;
	public abstract void payAndEnter() ;
	public abstract void exit() ;
	public int getSlot(){
		return slotNum;
	}
	public Level2 getLevel(){
		return l;
	}
}

class CarForParking extends VehicleForParking{
	int carNum;
	public CarForParking(Level2 l, int carNum, int slotNum) {
		this.l = l;
		this.carNum = carNum;
		this.slotNum = slotNum;
	}

	public void payAndEnter(){
		slotNum = l.getFreeSlotCar();
		if(slotNum !=0)
			System.out.println("Car "+carNum+" occupied "+slotNum+ " slot in level "+l.id);
		else
			System.out.println("Car "+carNum+" got no slot in level "+l.id);	
	}

	public void exit() {
		l.setSlotFreeForCar(slotNum);		
	}
}
class BikeForParking extends VehicleForParking{

	Level2 l ;
	int bikeNum;
	int slotNum;
	public BikeForParking(Level2 l) {
		this.l = l;
	}
	public void payAndEnter(){
		int slotNum = l.getFreeSlotBike();
		if(slotNum !=0)
			System.out.println("Bike "+bikeNum+" occupied "+slotNum+ " slot in level "+l.id);
		else
			System.out.println("Bike "+bikeNum+" got no slot in level "+l.id);}
	public void exit() {
		l.setSlotFreeForBike(slotNum);		
	}
}

class Level2{
	int size ;
	boolean bikeSlot[] = null;
	boolean carSlot[] = null;
	int id;
	public Level2(int id, int size) {
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

public class ParkingLot2 {

	static int size =10;
	static Level2 l1 = new Level2(1, size);
	static Level2 l2 = new Level2(2, size);
	static Level2 l3 = new Level2(3, size);


	public static void main(String args[]){
		CarForParking c[] = new CarForParking[3*size+1];
		boolean full = false ;
		for(int i=1; i<=size*3;i++){
			if(l1.isCarSlotAvailable()){
				c[i] = new CarForParking(l1,i, l1.getFreeSlotCar());
			}else if(l2.isCarSlotAvailable()){
				c[i] = new CarForParking(l2,i,l2.getFreeSlotCar());
			}else if (l3.isCarSlotAvailable()){
				c[i] = new CarForParking(l3,i,l3.getFreeSlotCar());
			}
			else{
				full = true;
				System.out.println("Parking is full !!");
			}
			if(!full)c[i].payAndEnter();				
		}
		for(int i=1; i<=size*3;i++){
			if(c[i]!= null && c[i].getSlot() != 0) c[i].exit();
		}
	}

}
