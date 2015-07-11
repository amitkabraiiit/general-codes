package com.practice.misc;


class A  // super class
{
	static void f() {
		System.out.println("from A"); 
	}
	void g() {
		System.out.println("from A"); 
	}
}
class B extends A{
	static void f(){
		System.out.println("from B");
	}
     void g() {
		System.out.println("from B"); 
	}
}

public class Test{

	public static void main(String[] args) {
		A a = new B();
		a.f();
		a.g();
	}
}