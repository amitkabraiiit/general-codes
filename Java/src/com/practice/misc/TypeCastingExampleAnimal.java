package com.practice.misc;

public class TypeCastingExampleAnimal {

	private String name = null;
	int aNum  = 10;
	public TypeCastingExampleAnimal(String name){

		this.name = name;
	}

	public void print(){
		System.out.println("This is an animal known as :"+this.getName());
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}

	public static class Cat extends TypeCastingExampleAnimal{

		public Cat(String name){
			super(name);	
		}

		public void print(){
			System.out.println("This is cat known as :"+this.getName());
		}
		public void printkarkedekho(){
			System.out.println("hmm, yeh kaise ho gaya!!");
		}
	}

	public static void main(String args[]){

		TypeCastingExampleAnimal a1 = new TypeCastingExampleAnimal("monkey");
		TypeCastingExampleAnimal kitty = new Cat("pussy");
		Cat c  = new Cat("hmm");
		a1.print(); 
		kitty.print(); // overridden function will get called. 
		((Cat)kitty).printkarkedekho();
		System.out.println(a1.aNum); 

		// This is an animal known as :monkey
		// This is cat known as :pussy
		// hmm, yeh kaise ho gaya!!
		// 10
	}
}
