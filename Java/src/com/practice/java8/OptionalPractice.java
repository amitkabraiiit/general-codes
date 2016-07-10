package com.practice.java8;

import java.util.Optional;

public class OptionalPractice {

	public void ofAndofNullable(){

	}
	public void NPE(){
		String strNull0 = null;
		// System.out.println( strNull0.contains( "something" ) ); Solution : either check for null or use try catch
	}
	public void createEmptyOptional(){
		Optional emptyOptional = Optional.empty(); // Creation of an empty optional, that is, its value is null or not initialized.
		System.out.println(emptyOptional); // prints "Optional.empty"
		//System.out.println(emptyOptional.get()); // java.util.NoSuchElementException: No value present
	}
	public void createOptional(){
		/*
		 * In order to create an Optional we can use an existing object and pass it to the Optional using the static method of()
		 */
		Integer value1 = null;
		Integer value2 = new Integer(10);
		Optional<Integer> a = Optional.ofNullable(value1);//Optional.ofNullable - allows passed parameter to be null.
		Optional<Integer> b = Optional.of(value2);//Optional.of - throws NullPointerException if passed parameter is null

		System.out.println("First parameter is present: " + a.isPresent()); //Optional.isPresent - checks the value is present or not
		System.out.println("Second parameter is present: " + b.isPresent());

		// optional.get() can give NPE and hence we optional class have more functions for such scenarios
		Integer v1 = a.orElse(new Integer(0));//returns the value if present otherwise returns the default value passed.
		Integer v2 = b.get();	 //Optional.get - gets the value, value should be present
		System.out.println("Sum = "+(v1 + v2 ));
		
		// And also the option to execute actions directly when the value is present, in combination with Lambdas:
		Optional stringToUse = Optional.of( "optional is there" );
		stringToUse.ifPresent( System.out::println );
	}
	public static void main(String[] args) {
		OptionalPractice o = new OptionalPractice();
		o.NPE();
		o.createEmptyOptional();
		o.createOptional();
	}
}
