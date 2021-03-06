package com.practice.oops;


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
		X xy = new Y(); // compiles ok (up the hierarchy, upcast, y is subclass of X)
		X xz = new Z(); // compiles ok (up the hierarchy, upcast, z is subclass of X)
		// Y y1 = new X();   // compilation error as :  X is not a Y , Required Y found X
		// Y yz = new Z();   incompatible type (siblings)
		// Y y1 = (Y) x; // compiles ok but produces runtime error ; ClassCastException: X cannot be cast to Y
		Y y2 = (Y) xy; // compiles and runs ok (xy is type Y) , downcast
		Z z2 = (Z) xz; // compiles and runs ok (xz is type Z) , downcast
		// Z z3 = (Z) x; // compiles fine but runtime error ; ClassCastException: X cannot be cast to Z
		// Y y3 = (Y) z;     inconvertible types (siblings)
		// Z z3 = (Z) y;     inconvertible types (siblings)
		Object o = z;
		// Object o1 = (Y) o; // compiles ok but produces runtime error
	}
}

// Above holds true for interfaces also , replaces superclass with interface and the example holds good.
