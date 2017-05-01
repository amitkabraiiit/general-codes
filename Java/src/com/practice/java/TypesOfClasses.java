package com.practice.java;
class Base {
	// We can put as many classes in one .java file but it is not a good practice from readability point of view
	void method1() {}
	void method2() {
		System.out.println("This is from method 2");
	}
}

interface Ball {
	void hit();
}

class MainClass { 
	
	// This is the main top level class since it as the main function. Note that this class doesn't have public access level 
	// and hence file name can be anything 'TypesOfClasses.java'
	// normal class , top level class , cann't be static/private/protected but can be public/abstract/final.
	// Default access level here , unless mentioned public it will be seen in the package only ie. default modifier which is equal to package private
	// Static means once, top level is anyways created once. That is why if we make any inner class as static , it becomes top-level class. 
	// how to instantiate inner classes over here : http://www.geeksforgeeks.org/static-class-in-java/
	public int a = 10;
	static int b = 20;

	// nested vs inner class : Nested classes are divided into two categories: static and non-static. 
	// Nested classes that are declared static are simply called static nested classes. 
	// Non-static nested classes are called inner classes.

	static class B {  
		// by default public , use of public word is optional here.
		// static nested class
		public void print(){
			System.out.println("This is from static nested class function");
			MainClass accessMainClass = new MainClass();
			accessMainClass.a = 10;
			accessMainClass.b = 20;
			b = 20;  // a = 30;  not allowed , only static variables can be access from here.
		}
	} 

	class C {
		// inner class , non static nested class.
		public void testAccessOfOutsideClass(){
			System.out.println("This is from non static nested class !!");
		}
	} 

	void f() {
		class D {
		} // local inner class , this class can be used inside this function 'f' only and Like local variables.
		// Local classes aren't allowed to be declared public, protected, private, or static
		System.out.println("This is from function 'f'");

	}

	void g() {
		// anonymous class 1
		// anonymous subclass instance is created and is given to base class, its anonymous since it doesn't have the name.
		// More at http://www.programmerinterview.com/index.php/java-questions/java-anonymous-class-example/
		Base bref = new Base() { 
			public void method1() {
				System.out.println("You hit it in 1!");
			}
		}; 
		bref.method1();
	}

	static void h(){
		// anonymous class 2
		Ball b = new Ball() {
			public void hit() {
				System.out.println("You hit it in 2!");
			}
		};
		b.hit();

	}

	public static void main(String[] args) {
		MainClass a = new MainClass();
		a.f();
		new Base().method2();
		
		// inner class
		C c1 = new MainClass().new C(); // Preferable , easy to remember
		c1.testAccessOfOutsideClass();
		C c2 = a.new C();
		c2.testAccessOfOutsideClass();

		/*
		 * Inner inner = new MyClass().new Inner(); // non static nested class or inner class
		 * Inner inner = new MyClass.Inner(); // static nested class its not called inner class since we can use it like outer class as well
		 */
		
		// nested class
		B b1 = new B();
		b1.print();
		B b2 = new MainClass.B();
		b2.print();

		h();
	}
	// This program doesn't run from eclipse since we don't have main in class with name TypesOfClasses but it runs fine from command line
	// This is from function 'f'
	// This is from method 2
	// This is from non static nested class !!
	// This is from non static nested class !!
	// This is from static nested class function
	// This is from static nested class function
	// You hit it in 2!
}
