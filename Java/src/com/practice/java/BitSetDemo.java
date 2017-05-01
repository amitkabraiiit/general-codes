package com.practice.java;

import java.util.BitSet;

public class BitSetDemo {
	public static void main(String args[]) {
		
	     BitSet s = new BitSet();
	     s.set(0); // set the 0th bit
	     s.set(6); // set the 6th bit
	     System.out.println(s.length());
	     System.out.println(s.cardinality());
	     System.out.println(s.toString());
	     System.out.println("==========");
		
	     BitSet bits1 = new BitSet(16);
	     BitSet bits2 = new BitSet(16);
	     // set some bits
	     for(int i=0; i<16; i++) {
	        if((i%2) == 0) bits1.set(i);
	        if((i%5) != 0) bits2.set(i);
	     }

	     System.out.println("bits1 total size"); // number of bits set to 1;
	     System.out.println(bits1.length());
	     System.out.println("Initial cardinality of bits1"); // number of bits set to 1;
	     System.out.println(bits1.cardinality());
	     System.out.println("Initial pattern in bits1: ");
	     System.out.println(bits1);
	     System.out.println("Initial pattern in bits2: ");
	     System.out.println(bits2);


	     // AND bits
	     bits2.and(bits1);
	     System.out.println("bits2 AND bits1: ");
	     System.out.println(bits2);

	     // OR bits
	     bits2.or(bits1);
	     System.out.println("bits2 OR bits1: ");
	     System.out.println(bits2);

	     // XOR bits
	     bits2.xor(bits1);
	     System.out.println("bits2 XOR bits1: ");
	     System.out.println(bits2);
	     

	  }
}
