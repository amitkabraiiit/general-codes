package com.practice.misc;

//Rules for inheritance and type Casting in Java.  Here, AA is a superclass.BB and CC both extends AA.  The same type-Casting rules apply if AA is 
//an interface and BB and CC both implements AA.  The only difference would be that nothing will be inherited from AA.

class AA  // super class
{
	void f() { System.out.println("AA.f being called"); }
	void g() 
	{ 
		System.out.println("which f will be called?"); 
		f(); 
	}
}


class BB extends AA
{
	// g() inherited and f() overidden
	void f() { System.out.println("BB.f being called"); } 
}

class CC extends AA
{
	// f, g inherited
	void h() { System.out.println("CC.h being called"); }
}


public class Casting
{
	public static void main(String[] ags)
	{
		AA a1 = new AA();  
		BB b1 = new BB();
		CC c1 = new CC();
		a1.g();  b1.g();  c1.h();  
		//////////////////////////////  nothing interesting so far.

		AA n1 = new BB(); 
		AA n2 = new CC();
		AA n3;
		n1.g(); 
		// In Java, a variable declared type of class A can hold a reference to an object of class A or an object belonging to any subclasses of class A.
		// In later case it can access only members of subclass which are there in class A unless type-casted. 
		// n2.h();   // compiler error because n2 has static type AA (no h)
		((CC)n2).h();  // now OK

		// ((CC)n1).h();  // runtime error, because n1 has dynamic type BB
		// ((CC)b1).h();  // compiler error: can't type cast across, only up/down
		a1 = b1;     // ok: no type Casting needed is BB extends/implements AA
		//b1 = (BB)a1;  // compiles but produces runtime error, since a1 is an AA object, not a BB object.

		Object[] M = new Object[10]; // Object is the superclass of all classes
		M[0] = "abc";                // a String is an Object
		M[1] = 3.14;  // automatically converts to Double, which is an Object

		// System.out.println(M[0].length()); // compiler error, why?
		System.out.println(((String)M[0]).length()); // ok now
		//System.out.println(((String)M[1]).length()); // runtime error, why?

		// now for a semi-tricky question:
		((AA)b1).g(); // which f will g call?  (run to find out, then explain)

	}
	// Output
	// which f will be called?
	// AA.f being called
	// which f will be called?
	// BB.f being called
	// CC.h being called
	// which f will be called?
	// BB.f being called
	// CC.h being called
	// 3
	// which f will be called?
	// BB.f being called

}



/*
   In summary, type Casting in the sense of Casting between classes only informs the compiler that a certain object should be treated as of a certain
   type.  However, whether that object is indeed of that type depends on runtime type information. 
 */


/*
A language is statically typed if the type of a variable is known at compile time. This in practice means that you as the programmer must specify what type each variable is. Example: Java, C, C++
The main advantage here is that all kinds of checking can be done by the compiler, and therefore a lot of stupid bugs are caught at a very early stage.

A language is dynamically typed if the type of a variable is interpreted at runtime. This means that you as a programmer can write a little quicker because you do not have to specify type everytime. Example: Perl. Most scripting languages have this feature as there is no compiler to do static typechecking anyway, but you may find yourself searching for a bug that is due to the interpreter misinterpreting the type of a variable. Luckily, scripts tend to be small so bugs have not so many places to hide. Most dynamically typed languages do allow you to provide type information, but do not require it. 

One language that is currently being developed (Rascal) takes a hybrid approach allowing dynamic typing within functions but enforcing static typing for the function signature.
*/
