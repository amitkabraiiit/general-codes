package com.practice.designpattern.creational;

interface ProtoType {
	public ProtoType doClone(); // This mandates that copying feature is available
}

class Person implements ProtoType {

	String name;
	public Person(String name) {
		this.name = name;
		System.out.println("Got created!!");
	}
	@Override
	public ProtoType doClone() { 
		/*
		 * We could have used clone but clone is again same i.e
		 * Person p = new Person(name);
		 * p.setPropeties() // in this example no property is there
		 * return p
		 */
		return new Person(name);
	}
	public String toString() {
		return "This person is named " + name;
	}
}

class Pig implements ProtoType {

	String sound;
	public Pig(String sound) {
		this.sound = sound;
	}
	@Override
	public ProtoType doClone() {
		return new Pig(sound);
	}
	public String toString() {
		return "This pig says " + sound;
	}
}

public class PrototypeDemo {

	public static void main(String[] args) {

		Person person1 = new Person("Fred");
		System.out.println("person 1:" + person1);
		Person person2 = (Person) person1.doClone();
		System.out.println("person 2:" + person2);

		Pig pig1 = new Pig("Wooof!");
		System.out.println("pig 1:" + pig1);
		Pig pig2 = (Pig) pig1.doClone();
		System.out.println("pig 2:" + pig2);

	}

}

