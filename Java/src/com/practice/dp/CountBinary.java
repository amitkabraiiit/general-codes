package com.practice.dp;

import java.util.Arrays;

public class CountBinary {

	static  void countStrings(int n)
	{
		int a[] = new int[n];
		count(a,0);

	}
	static  void count(int []a, int index)
	{
		if(index >= a.length){
			System.out.println(Arrays.toString(a));
			return;
		}
		a[index] = 0;
		count(a, index+1);
		a[index] = 1;
		count(a, index+2);
		a[index] = 0;
	}
	/* Driver program to test above function */
	public static void main (String args[])
	{
		countStrings(3);
	}
}


