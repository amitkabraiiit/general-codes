package com.practice.misc;


abstract class Shape {

	public String color;
	public Shape() {
	}
	public void setColor(String c) {
		color = c;
	}
	public String getColor() {
		return color;
	}
	abstract public double area();

}
abstract class Vehicle {

	int numofGears;
	String color;
	abstract boolean hasDiskBrake();
	abstract int getNoofGears();
}
public class PointAbstractClassExample extends Shape {

	static int x, y;
	public PointAbstractClassExample(String color) {
		x = 0;
		y = 0;
		super.setColor(color);
	}
	public double area() {
		return 1;
	}
	public double perimeter() {
		return 0;
	}
	public static void print() {
		System.out.println("point: " + x + "," + y);
	}
	public static void main(String args[]) {
		PointAbstractClassExample p = new PointAbstractClassExample("red");
		p.print();
		System.out.println(p.getColor());
		System.out.println(p.area());
	}
	// point: 0,0
	// red
	// 1.0
}
