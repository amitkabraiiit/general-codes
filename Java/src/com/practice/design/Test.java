package com.practice.design;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		int size=10 ;
		boolean carSlot[] = new boolean[size+1];
		Arrays.fill(carSlot, true);
		System.out.println(size);
		for(int j=1;j<=size;j++){
			System.out.println(carSlot[j]);
		}

	}

}

