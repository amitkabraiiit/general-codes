package com.practice.misc;

public class Overriding {

	public static void  printMethod(){
		System.out.println("test123");
	}
	static class A {
		public void print(){
			
		}
	}
	class B extends A{
		public void print(){
			
		}
	}
	public static void main(String[] args) {
		A a = new A();
		a.print();
	}
}
