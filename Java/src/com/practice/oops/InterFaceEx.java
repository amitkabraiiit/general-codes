package com.practice.oops;


interface I1 {
	void methodI1(); // public static by default
}

interface I2 extends I1 {
	void methodI2(); // public static by default
}

class A1 {
	public String methodA1() {
		String strA1 = "I am in methodC1 of class A1";
		return strA1;
	}
	public String toString() {
		return "toString() method of class A1";
	}
}

class B1 extends A1 implements I2 {
	public void methodI1() {
		System.out.println("I am in methodI1 of class B1");
	}
	public void methodI2() {
		System.out.println("I am in methodI2 of class B1");
	}
}

class C1 implements I2 {
	public void methodI1() {
		System.out.println("I am in methodI1 of class C1");
	}
	public void methodI2() {
		System.out.println("I am in methodI2 of class C1");
	}
}

// Note that the class is declared as abstract as it does not satisfy the interface contract
abstract class D1 implements I2 {
	// This class does not implement methodI2() hence declared abstract.
	public void methodI1() {
	}
}

public class InterFaceEx {

	public static void main(String[] args) {
		I1 i1 = new B1();
		i1.methodI1(); // I am in methodI1 of class B1
		// i1.methodI2(); Compilation error as methodI2 not present in I1,  to make it working casting needed
		((I2) i1).methodI2(); // I am in methodI2 of class B1
		((B1) i1).methodI2(); // I am in methodI2 of class B1
		I2 i2 = new B1();
		i2.methodI1(); // OK // I am in methodI1 of class B1
		i2.methodI2(); // OK // I am in methodI2 of class B1

		// Does not Compile as methodA1() not present in interface reference I1
		// String var = i1.methodA1();
		// Hence I1 requires a cast to invoke methodA1
		String var2 = ((A1) i1).methodA1();
		System.out.println("var2 : " + var2); // I am in methodC1 of class A1
		String var3 = ((B1) i1).methodA1();
		System.out.println("var3 : " + var3); // I am in methodC1 of class A1
		String var4 = i1.toString();
		System.out.println("var4 : " + var4); // toString() method of class A1
		String var5 = i2.toString();
		System.out.println("var5 : " + var5); // toString() method of class A1

		I1 i3 = new C1();
		String var6 = i3.toString();
		System.out.println("var6 : " + var6); // It prints the Object toString() method i.e C1@2f3cf887

		Object o1 = new B1();
		// o1.methodI1(); does not compile. To solve the probelm we need to downcast o1 reference.
		((I1) o1).methodI1(); // 1  I am in methodI1 of class B1
		((I2) o1).methodI1(); // 2  I am in methodI1 of class B1
		((B1) o1).methodI1(); // 3  I am in methodI1 of class B1
	}
}
