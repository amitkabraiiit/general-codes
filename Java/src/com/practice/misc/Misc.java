package com.practice.misc;

import java.util.Random;

public class Misc {


	public static void main(String[] args) {
		Random rand = new Random(); // default seed is current time in millisecond
									// If we use some constant value here say 300020303 then 
									// sysout below will print same numbers again while 
									// relaunching jvm. default seed make sures we get different number all the time.
		for(int i = 0 ; i<10;i++){
			System.out.println(rand.nextInt(40));
		}
	}
	
}
