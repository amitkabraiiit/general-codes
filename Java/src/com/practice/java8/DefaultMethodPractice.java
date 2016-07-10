package com.practice.java8;

interface Vehicle {
	default void print(){
		System.out.println("I am a vehicle!");
	}

	static void blowHorn(){
		System.out.println("Blowing horn!!!");
	}
}

interface FourWheeler {
	default void print(){
		System.out.println("I am a four wheeler!");
	}
}

class Car implements Vehicle, FourWheeler {
	public void print(){
		Vehicle.super.print();
		FourWheeler.super.print();
		Vehicle.blowHorn();
		System.out.println("I am a car!");
	}
}
public class DefaultMethodPractice {
	interface DefaultInterfaceTest{
		void show();
		default void display(){
			System.out.println("Default method from interface can have body..!");
		}
	}
	static class DefaultInterfaceTestImpl implements DefaultInterfaceTest{
		public void show(){
			System.out.println("show method");
		}
		//we dont need to provide any implementation to default method.
	}

	public static void main(String [] a)  {

		// Practice default function and Functional Interface
		DefaultInterfaceTest obj = new DefaultInterfaceTestImpl();
		obj.show();//out puts: show method
		obj.display();//outputs : Default method from interface can have body..!

		Vehicle vehicle = new Car();
		vehicle.print();
	}
}
