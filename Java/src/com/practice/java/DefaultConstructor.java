package com.practice.java;


class DefaultConstructor{
	int  value1;
	int  value2;
	
	DefaultConstructor(){
	  value1 = 10;
	  value2 = 20;
	  System.out.println("Inside 1st Constructor");
	}
	DefaultConstructor(int a){
	  value1 = a;
	  System.out.println("Inside 2nd Constructor");
	}
	DefaultConstructor(int a,int b){
	  value1 = a;
	  value2 = b;
	  System.out.println("Inside 3rd Constructor");
	}

	public void display(){
		System.out.println("Value1 === "+value1);
		System.out.println("Value2 === "+value2);
	}
	public static void main(String args[]){
		DefaultConstructor d1 = new DefaultConstructor();
		//DefaultConstructor d2 = new DefaultConstructor(30);
		//DefaultConstructor d3 = new DefaultConstructor(30,40);
		d1.display();
		//d2.display();
		//d3.display();
	}
}
