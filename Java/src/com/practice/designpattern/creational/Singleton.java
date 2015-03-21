package com.practice.designpattern.creational;

public class Singleton {

	private static Singleton singletonExample = null;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (singletonExample == null) {
			singletonExample = new Singleton();
		}
		return singletonExample;
	}

	public void sayHello() {
		System.out.println("Hello");
	}
	public static void main(String[] args) {
		Singleton singletonExample = Singleton.getInstance();
		singletonExample.sayHello();
	}
}