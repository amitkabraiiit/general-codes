package com.practice.dp;

import java.util.Arrays;

public class Dungeon {

	public static int countDecodingDP(int [] digits, int n)
	{
	    int count[] = new int[n]; // A table to store results of subproblems
	    count[0] = 1;
	    count[1] = ((digits[0] == 2 ||digits[0] == 1) && digits[1] < 7) ? 2: 1;
	 
	    for (int i = 2; i < n; i++)
	    {
	            count[i] = count[i-1]; 
	        // If second last digit is smaller than 2 and last digit is
	        // smaller than 7, then last two digits form a valid character
	        if ((digits[i-1] == 2 ||digits[i-1] == 1) && digits[i] < 7)
	            count[i] += count[i-2];
	    }
	    return count[n-1];
	}
	public static void main(String[] args) {
		int arr [] = { 1,2,3};
		System.out.println(countDecodingDP(arr,arr.length));
		
	}
	
}
