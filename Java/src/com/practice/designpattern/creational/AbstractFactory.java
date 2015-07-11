package com.practice.designpattern.creational;

public class AbstractFactory {

	public SpeciesFactory getSpeciesFactory(String type) {
		if ("mammal".equals(type)) {
			return new MammalFactory();
		} else {
			return new ReptileFactory();
		}
	}

	public static void main(String[] args) {
		AbstractFactory abstractFactory = new AbstractFactory();

		SpeciesFactory speciesFactory1 = abstractFactory.getSpeciesFactory("reptile");
		Animal a1 = speciesFactory1.getAnimal("tyrannosaurus");
		System.out.println("a1 sound: " + a1.makeSound());
		Animal a2 = speciesFactory1.getAnimal("snake");
		System.out.println("a2 sound: " + a2.makeSound());

		SpeciesFactory speciesFactory2 = abstractFactory.getSpeciesFactory("mammal");
		Animal a3 = speciesFactory2.getAnimal("dog");
		System.out.println("a3 sound: " + a3.makeSound());
		Animal a4 = speciesFactory2.getAnimal("cat");
		System.out.println("a4 sound: " + a4.makeSound());

	}
}

abstract class SpeciesFactory {
	 abstract Animal getAnimal(String type);
}
class MammalFactory extends SpeciesFactory {

	@Override
	public Animal getAnimal(String type) {
		if ("dog".equals(type)) {
			return new Dog();
		} else {
			return new Cat();
		}
	}

}

class ReptileFactory extends SpeciesFactory {

	@Override
	public Animal getAnimal(String type) {
		if ("snake".equals(type)) {
			return new Snake();
		} else {
			return new Tyrannosaurus();
		}
	}

}
abstract class Animal {
	public abstract String makeSound();
}
class Cat extends Animal {

	@Override
	public String makeSound() {
		return "Meow";
	}

}
class Dog extends Animal {

	@Override
	public String makeSound() {
		return "Woof";
	}

}
class Snake extends Animal {

	@Override
	public String makeSound() {
		return "Hiss";
	}

}
class Tyrannosaurus extends Animal {

	@Override
	public String makeSound() {
		return "Roar";
	}

}
