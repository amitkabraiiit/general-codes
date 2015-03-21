package com.practice.designpattern.structural;

// Component
interface Animal {
	public void describe();
}

class LivingAnimal implements Animal {
	@Override
	public void describe() {
		System.out.println("\nI am an animal.");
	}
}

// AnimalDecorator is our decorator abstract class. 
// It implements Animal but since it is an abstract class, it does not have to implement describe(). 
abstract class AnimalDecorator implements Animal {
	Animal animal;
	public AnimalDecorator(Animal animal) {
		this.animal = animal;
	}
}

class LegDecorator extends AnimalDecorator {

	public LegDecorator(Animal animal) {
		super(animal);
	}
	@Override
	public void describe() {
		animal.describe();
		System.out.println("I have legs.");
		dance();
	}
	public void dance() {
		System.out.println("I can dance.");
	}
}

class WingDecorator extends AnimalDecorator {

	public WingDecorator(Animal animal) {
		super(animal);
	}
	@Override
	public void describe() {
		animal.describe();
		System.out.println("I have wings.");
		fly();
	}
	public void fly() {
		System.out.println("I can fly.");
	}
}

class GrowlDecorator extends AnimalDecorator {

	public GrowlDecorator(Animal animal) {
		super(animal);
	}
	@Override
	public void describe() {
		animal.describe();
		growl();
	}
	public void growl() {
		System.out.println("Grrrrr.");
	}
}


class Decorator {

	public static void main(String[] args) {

		Animal animal = new LivingAnimal();
		animal.describe();

		animal = new LegDecorator(animal);
		animal.describe();

		animal = new WingDecorator(animal);
		animal.describe();

		animal = new GrowlDecorator(animal);
		animal = new GrowlDecorator(animal);
		animal.describe();
	}

}
