package com.practice.designpattern.creational;


//prototype
interface Prototype2 extends Cloneable { // Prototype2 , since Prototype already exists in this package.
	void setSize(int x);
	void printSize();
	public Prototype2 clone() throws CloneNotSupportedException;
}

//a concrete class
class A implements Prototype2 {
	private int size;

	public A(int x) {
		this.size = x;
		System.out.println("Got created!!");
	}

	@Override
	public void setSize(int x) {
		this.size = x;
	}

	@Override
	public void printSize() {
		System.out.println("Size: " + size);
	}


	@Override
	public A clone() throws CloneNotSupportedException {
		return (A) super.clone();
	}
}

//when we need a large number of similar objects
public class PrototyeDemo3 {
	public static void main(String args[]) throws CloneNotSupportedException {
		A a = new A(1);

		for (int i = 2; i < 10; i++) {
			Prototype2 temp = a.clone();
			temp.setSize(i);
			temp.printSize();
		}
	}
}