package com.practice.java;

//Basic Enum
enum XY{
	X,Y;
}

// Enum + Instance Field
enum AB{
	A(1), B(2) ;
	int price;
	AB(int price){
		this.price = price;
	}
}

public class EnumExample2 {

	void print( XY t){
		switch(t){
		case X:
			System.out.println("doing something related to X");
			break;
		case Y:
			System.out.println("doing something related to Y");
			break;
		default:
			System.out.println("entered into default");
		}
	}

	public static void main(String[] args) {
		// XY enum , basic enum
		for(XY a : XY.values()){
			System.out.println(a.name()+" "+a.toString());
		}
		System.out.println("========");

		// AB enum , enum + instance field
		System.out.println(AB.A.price);
		System.out.println(AB.B.price);
		for(AB a : AB.values()){
			System.out.println(a.price);
		}
		System.out.println("========");

        // some logic
		for(XY p : XY.values()){
			new EnumExample2().print(p);	
		}
		XY t = XY.Y;
		System.out.print("You chose :");
		new EnumExample2().print(t);
	}


}

