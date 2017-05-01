package com.practice.oops;

class Vehicle1 {

	String name;
	Vehicle1() {
		name = "Vehicle1";
	}
}

class HeavyVehicle1 extends Vehicle1 {

	HeavyVehicle1() {
		name = "HeavyVehicle1";
	}
}

class Truck extends HeavyVehicle1 {

	Truck() {
		name = "Truck";
	}
}

class LightVehicle1 extends Vehicle1 {

	LightVehicle1() {
		name = "LightVehicle1";
	}
}

public class InstanceOfExample {

	static boolean result;
	static HeavyVehicle1 hV = new HeavyVehicle1();
	static Truck T = new Truck();
	static HeavyVehicle1 hv2 = null;
	public static void main(String[] args) {
		result = hV instanceof HeavyVehicle1;
		System.out.print("hV is an HeavyVehicle1: " + result + "\n");
		result = T instanceof HeavyVehicle1;
		System.out.print("T is an HeavyVehicle1: " + result + "\n");
		result = hV instanceof Truck;
		System.out.print("hV is a Truck: " + result + "\n");
		result = hv2 instanceof HeavyVehicle1;
		System.out.print("hv2 is an HeavyVehicle1: " + result + "\n");
		hV = T; //Sucessful Cast form child to parent
		T = (Truck) hV; //Sucessful Explicit Cast form parent to child
		// output	
		// hV is an HeavyVehicle1: true
		// T is an HeavyVehicle1: true
		// hV is a Truck: false
		// hv2 is an HeavyVehicle1: false
		// Note: hv2 does not yet reference an HeavyVehicle1 object, instanceof returns false. Also we can’t use instanceof operator with siblings

	}
}

