package com.practice.java8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LambdaPractice {

	int t = 1;
	// @FunctionalInterface , annotating as @FunctionalInterface is optional
	interface HelloWorld {
		String hello(String name);
	}
	public void lambdaEx1(){
		int t = 2;
		HelloWorld helloWorld1 = (String name) -> { return "Hello " + name; };
		HelloWorld helloWorld2 = name -> { return "Hello " + name; };
		HelloWorld helloWorld3 = r -> { return "Hello " + r; };
		HelloWorld helloWorld4 = r ->  "Hello " + r;
		System.out.println(helloWorld1.hello("JOE"));
		System.out.println(helloWorld2.hello("JOE"));
		System.out.println(helloWorld3.hello("JOE"));
		System.out.println(helloWorld4.hello("JOE"));
		System.out.println(t); // accessing local variable
		System.out.println(this.t); // accessing class variable
		List<String> s = new LinkedList<>();
	}

	interface TestInterface{
		void display();
	}
	public static void lambdaEx2(){
		// Example1 , Easy way to access Collection libraries for iterating, filter and extract data.
		List<String> names = Arrays.asList("one","two","three");
		names.forEach(n -> System.out.print(n+" ")); // one two three

		System.out.println("\n===============");
		// Example2
		TestInterface li = () -> System.out.println("New : Display method called !");
		li.display(); // New : Display method called !

		// Example3 
		Runnable r = ()->System.out.println("Runnable run method");
		r.run(); // Runnable run method

		// Example4
		Thread t = new Thread(()->{System.out.println("Runnable run method from lambda");});
		t.start(); // Runnable run method from lambda
		t.run(); // Runnable run method from lambda
	}

	interface MathOperation {
		int operation(int a, int b);
	}
	interface GreetingService {
		void sayMessage(String message);
	}
	private int operate(int a, int b, MathOperation mathOperation){
		return mathOperation.operation(a, b);
	}
	public void lambdaEx3(){
		LambdaPractice tester = new LambdaPractice();

		//with type declaration
		MathOperation addition = (int a, int b) -> a + b;

		//with out type declaration
		MathOperation subtraction = (a, b) -> a - b;

		//with return statement along with curly braces
		MathOperation multiplication = (int a, int b) -> { return a * b; };

		//without return statement and without curly braces
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + tester.operate(10, 5, addition)); // same as addition.operation(10, 5);
		System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + tester.operate(10, 5, division));

		//with parenthesis
		GreetingService greetService1 = message ->
		System.out.println("Hello " + message);

		//without parenthesis
		GreetingService greetService2 = (message) ->
		System.out.println("Hello " + message);

		greetService1.sayMessage("Mahesh");
		greetService2.sayMessage("Suresh");
	}


	public static void main(String[] args) {
		LambdaPractice t  = new LambdaPractice();
		t.lambdaEx1();
		t.lambdaEx2();
		t.lambdaEx3();
	}
}
