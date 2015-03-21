package com.practice.misc;


class X {
}

class Y extends X {
}

class Z extends X {
}

// Upcast is automatic
// DownCast is manual

public class RunTimeCastDemo {
	public static void main(String args[]) {
		X x = new X();
		Y y = new Y();
		Z z = new Z();
		X xy = new Y(); // compiles ok (up the hierarchy, upcast)
		X xz = new Z(); // compiles ok (up the hierarchy, upcast)
		X x1 = y; // compiles ok (y is subclass of X)
		X x2 = z; // compiles ok (z is subclass of X)
		// Y y1 = new X();   
			// compilation error as : 
			// X is not a Y 
			// Required Y found X
		// Z z1 = new X();   compilation error same as above 
		// Y yz = new Z();   incompatible type (siblings)
		// Y y1 = (Y) x; // compiles ok but produces runtime error ; ClassCastException: X cannot be cast to Y
		// Z z1 = (Z) x; // compiles ok but produces runtime error ; ClassCastException: X cannot be cast to Z
		Y y2 = (Y) x1; // compiles and runs ok (x1 is type Y) , downcast
		Z z2 = (Z) x2; // compiles and runs ok (x2 is type Z) , downcast
		// Z z3 = (Z) x; // compiles fine but runtime error ; ClassCastException: X cannot be cast to Z
		// Y y3 = (Y) z;     inconvertible types (siblings)
		// Z z3 = (Z) y;     inconvertible types (siblings)
		Object o = z;
		// Object o1 = (Y) o; // compiles ok but produces runtime error
	}
}

// Above holds true for interfaces also , replaces superclass with interface and the example holds good.
