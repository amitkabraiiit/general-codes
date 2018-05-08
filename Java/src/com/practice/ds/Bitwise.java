package com.practice.ds;

import java.util.Arrays;

public class Bitwise {

	private static void checkAllDigitOne(int num){
		System.out.println(num & num);
		System.out.println();

	}

	private static void variousBitwiseOpsJava(){
		int a = 60;	/* 60 = 0011 1100 */  
		int b = 13;	/* 13 = 0000 1101 */
		int c = 0;
		System.out.println("a = "+a+" ; b = "+b+" ; c = "+c);

		c = a & b;       /* 12 = 0000 1100 */ 
		System.out.println("AND : a & b = " + c );

		c = a | b;       /* 61 = 0011 1101 */
		System.out.println("OR : a | b = " + c );

		c = a ^ b;       /* 49 = 0011 0001 */
		System.out.println("XOR : a ^ b = " + c );

		c = ~a;          /*-61 = 1100 0011 */
		System.out.println("Unary Complement : ~a = " + c );
		System.out.println();

	}

	private static void variousBitwiseShiftOpsJava(){

		int number = 8; //0000 1000
		System.out.println("Original number : " + number);

		//left shifting bytes with 1 position
		number = number<<1; //should be 16 i.e. 0001 0000 , equivalent of multiplication of 2
		System.out.println("value of number after left shift: " + number);
		number = 8;
		number = number >> 1; // should be 4 i.e. 0000 0100 , equivalent of division of 2
		System.out.println("value of number after right shift with sign: " + number);
		number = 8;
		number = number >>> 1; // should be 4 i.e. 0000 0100 , equivalent of division of 2
		System.out.println("value of number after right shift without sign: " + number);


		number = -8; // 11111111111111111111111111111000
		System.out.println("New number : " + number+" and its binary representation : "+Integer.toBinaryString(number));
		//right shifting bytes with sign 1 position
		number = number>>1; //11111111111111111111111111111100, equivalent of division of 2
		System.out.println("value of number after right shift with sign: " + number);
		System.out.println("New number : " + number+" and its binary representation : "+Integer.toBinaryString(number));
		number = -8;
		number = number>>>1; //should be 16 i.e. 0001 0000
		//equivalent of division of 2
		System.out.println("value of number after right shift without sign: " + number);
		System.out.println("New number : " + number+" binary representation : "+Integer.toBinaryString(number));
		System.out.println();
	}

	private static void bitwiseXOROfIntAndHex(){
		int num1 = 42; // 00101010   42
		int num2 = 0xF; // 00001111   15
		// XOR   : 00100101   37
		System.out.println("XOR Result =" +(num1 ^ num2));
	}

	private static void bitwiseSwap(int a , int b){
		System.out.println("a = "+a+" b = "+b);
		a = a ^ b; // (a XOR b)
		System.out.println("a = "+a+" b = "+b);
		b = a ^ b; // (a XOR b) XOR b = a
		System.out.println("a = "+a+" b = "+b);
		a = a ^ b; // (a XOR b) XOR a = b
		System.out.println("a = "+a+" b = "+b);
	}

	private static void bitwiseAddition(int x,int y){
		System.out.println("Inside bitwise addition function");
		int carry = 0 ,tmp = 0;
		int count = 0;
		int sum = 0 ;
		while (x !=0 && y != 0){
			tmp = x%2 + y%2 + carry;
			if(tmp == 0 ) { carry = 0 ; }
			if(tmp == 1 ) { carry = 0 ; sum = sum ^ (1 << count);}
			if(tmp == 2 ) { carry = 1 ; }
			if(tmp == 3 ) { carry = 1 ; sum = sum ^ (1 << count);}
			x = x/2 ; y=y/2; count++;
		}
		while (x !=0){			
			if(x%2 == 1 && carry==0){sum = sum ^ (1 << count);carry=0;}
			if(x%2 == 1 && carry==1){sum = sum ^ (1 << count);carry=1;}
			x=x/2; count++;
		}

		while (y !=0){			
			if(y%2 == 1 && carry==0){sum = sum ^ (1 << count);carry=0;}
			if(y%2 == 1 && carry==1){sum = sum ^ (1 << count);carry=1;}
			y=y/2; count++;
		}
		if(x == 0 && y==0 && carry!=0)	sum = sum ^ (1 << count);
		System.out.println(sum);
	}

	public static void main(String[] args) {
		Bitwise.checkAllDigitOne(7);
		Bitwise.variousBitwiseOpsJava();
		Bitwise.variousBitwiseShiftOpsJava();
		Bitwise.bitwiseXOROfIntAndHex();
		System.out.println(Integer.toBinaryString(10000));
		System.out.println(Integer.toBinaryString(700));
		Bitwise.bitwiseSwap(10, 20);
		Bitwise.bitwiseAddition(5,5);

		System.out.println(Integer.toBinaryString(8));
		System.out.println(Integer.toBinaryString(~8));
	}


}
