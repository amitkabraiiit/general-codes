package com.practice.java;


public class TestAnonmous {

	int i = 0;
	interface Species{
		public void eat();
	}

	public void print(){
		int t=0;
		Species s = new Species() {
			@Override
			public void eat() {
				System.out.println("Yes they do !!"+t);
			}
		};
		s.eat();
	}

	public static void main(String[] args) {
		new TestAnonmous().print();

	}

}
