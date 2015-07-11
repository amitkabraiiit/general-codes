package com.practice.misc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
public class Java8{

	public static void condition(List<Integer> list, Predicate<Integer> predicate) {
		for(Integer n: list)  {
			if(predicate.test(n)) {
				System.out.println(n + " ");
			}
		}
	}

	/*
	 * If we have a chance to pass the if condition as a argument to method, 
	 * then we would have achieved both requirements with only one method, 
	 * for this java 8 introduced an interface called java.util.function.Predicate<T>
	 */

	public static void practicePradicate(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		System.out.println("Print all numbers:");
		condition(list, (n)->true);
		System.out.println("Print no numbers:");
		condition(list, (n)->false);
		System.out.println("Print even numbers:");
		condition(list, (n)-> n%2 == 0 );
		System.out.println("Print odd numbers:");
		condition(list, (n)-> n%2 == 1 );
		System.out.println("Print numbers greater than 5:");
		condition(list, (n)-> n > 5 );
	}

	public static void display(String name){
		System.out.println("Name : "+name);
	}


	/*
	 * Double Colon operator introduced in Java 8, We use it as method reference.
	 * Syntax : ClassName::MethodName
	 * Here forEach is a new method added to the List interface in java 8. System.out is the PrintStream Class and println is method in it.
	 */

	public static void practiceDoubleColon(){
		List<String> names = Arrays.asList("one","two","three");
		names.forEach(Java8::display);
	}


	/*
	 * To Support lambda expressions in Java 8, they introduced Functional Interfaces.
	 * An interface which has Single Abstract Method can be called as Functional Interface.


	 */
	@FunctionalInterface // We can annotate with @FunctionalInterface annotation, to tell compile time errors. It is optional
	interface DefaultInterfaceTest{
		void show();
		default void display(){
			/*
			 * Functional interface can not have more than one abstract method but it can have more than one default methods.
			 * Default methods are introduced in Java 8, to add new methods to interface with out disturbing the implemented classes.
			 * Main use of default method is with out forcing the implemented class , we can add a method to an interface.
			 */
			System.out.println("Default method from interface can have body..!");
		}
	}
	static class DefaultInterfaceTestImpl implements DefaultInterfaceTest{
		public void show(){
			System.out.println("show method");
		}
		//we dont need to provide any implementation to default method.
	}


	/*
	 * They provide a clear way to represent one method interface using lambda expression. 
	 * And they provide easy way to access Collection libraries for iterating, filter and extract data.
	 */
	public static void practiceLambda(){
		List<String> names = Arrays.asList("one","two","three");
		names.forEach(n -> System.out.println(n));
		
		// Another example
		TestInterface li = () -> System.out.println("New : Display method called !");
		li.display();
		
		
		// Any interface which has only one method can be used with lambda expression instead of old anonymous inner class.
		
		// Another example 
		Runnable r = ()->System.out.println("Runnable run method");
		
		// Another example
		Thread t = new Thread(()->{System.out.println("Runnable run method from lambda");});
	}
	interface TestInterface{
		void display();
		}
	
	
	public static void main(String [] a)  {
		
	    Java8.practiceDoubleColon();
	    Java8.practicePradicate();
	    Java8.practiceLambda();
		
		// Practice default function and Functional Interface
		DefaultInterfaceTest obj = new DefaultInterfaceTestImpl();
		obj.show();//out puts: show method
		obj.display();//outputs : Default method from interface can have body..!
		// Another example on functional interface : Thread t = new Thread(()->{ System.out.println("Runnable implemented by using Lambda Expression"); 	});
	}

}

