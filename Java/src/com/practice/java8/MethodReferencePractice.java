package com.practice.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodReferencePractice {
	/*
	 * Double Colon operator introduced in Java 8, We use it as method reference.
	 * Syntax : ClassName::MethodName
	 * Here forEach is a new method added to the List interface in java 8. System.out is the PrintStream Class and println is method in it.
	 * :: (double colon) is the operator used for method reference in Java. 
	 */

	public static void display(String name){
		System.out.println("Name : "+name);
	}
	public static void practiceDoubleColon(){
		List<String> names = Arrays.asList("one","two","three");
		names.forEach(MethodReferencePractice::display);
	}

	static class Animal {
		String name;
		Animal(String name) {	this.name = name;		}
		public static int animalCompare(Animal a1, Animal a2) {		return a1.name.compareTo(a2.name); } 
		public String toString() {	return name;	}
	}

	public static void main(String[] args) {
		// Example1
		MethodReferencePractice.practiceDoubleColon();

		// Example2
		Animal[] animalArr = {	new Animal("Lion"),	new Animal("Crocodile"),new Animal("Tiger"),new Animal("Elephant")};
		System.out.println("Before Sort: "+Arrays.toString(animalArr));
		Arrays.sort(animalArr, Animal::animalCompare);
		System.out.println("After Sort: "+Arrays.toString(animalArr));

		// Example3
		List<String> names = new ArrayList<>();
		names.add("Mahesh");
		names.add("Suresh");
		names.add("Ramesh");
		names.add("Naresh");
		names.add("Kalpesh");
		names.forEach(System.out::println);
	}





}
