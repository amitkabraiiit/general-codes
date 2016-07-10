package com.practice.java8;

interface IsReferable {
	public void referenceDemo();
}

class ReferenceDemo {

	public static void commonMethod() {
		System.out.println("This method is already defined.");
	}

	public void implement() {

		// Anonymous class.
		IsReferable demoOne = new IsReferable() {
			@Override
			public void referenceDemo() {
				ReferenceDemo.commonMethod();
			}
		};
		demoOne.referenceDemo();

		// Lambda implementaion.
		IsReferable demo = () -> ReferenceDemo.commonMethod();
		demo.referenceDemo();

		// Method reference.
		IsReferable demoTwo = ReferenceDemo::commonMethod;
		demoTwo.referenceDemo();
	}
}

public class MethodReferencePractice2 {
	public static void main(String[] args) {
		ReferenceDemo r = new ReferenceDemo();
		r.implement();	
	}
}
