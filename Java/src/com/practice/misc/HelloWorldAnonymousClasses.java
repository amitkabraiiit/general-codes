package com.practice.misc;


public class HelloWorldAnonymousClasses {
 
    int i=0;
	interface HelloWorld {
		public void greet();
		public void greetSomeone(String someone);
	}

	public void sayHello() {
		//  local classes except that they do not have a name.
		class EnglishGreeting implements HelloWorld {
			String name = "world";
			public void greet() {
				greetSomeone("world");
			}
			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Hello " + name);
			}
		}
		HelloWorld englishGreeting = new EnglishGreeting();

		HelloWorld frenchGreeting = new HelloWorld() {
			String name = "tout le monde";
			public void greet() {
				greetSomeone("tout le monde");
			}
			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Salut " + name);
			}
		};

		englishGreeting.greet();
		frenchGreeting.greetSomeone("Fred");
	}

	/*
	This won't work since anonymous classes are local classes and hence it needs to be inside function, 
	that's why moving it to main() function worked.
	HelloWorld spanishGreeting = new HelloWorld() {
		String name = "ls isla bonita";
		public void greet() {
			greetSomeone("adfadtout admonde");
		}
		public void greetSomeone(String someone) {
			name = someone;
			System.out.println("la isla " + name);
		}
	};
	spanishGreeting.greet();
	*/

	public static void main(String... args) {
		HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
		myApp.sayHello();

		HelloWorld spanishGreeting = new HelloWorld() {
			String name = "ls isla bonita";
			public void greet() {
				greetSomeone("adfadtout admonde");
			}
			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("la isla " + name);
			}
		};
		spanishGreeting.greet();
	}            
}
