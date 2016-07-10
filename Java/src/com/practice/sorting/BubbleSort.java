package com.practice.sorting;
import java.util.Arrays;

public class BubbleSort{

	/*
	 * Scan the array, swapping adjacent pair of elements if they are not in relative order. 
	 * This bubbles up the largest element to the end.Scan the array again, 
	 * bubbling up the second largest element.Repeat until all elements are in order. 
	 * 
	 * Suppose the array length is n. The outer loop runs roughly n times, and the inner loop on 
	 * average runs n/2 times. The total time is about t(n) = n * (n/2) = O(n^2). 
	 * In terms of the efficiency, this is the same as selection sort and insertion sort.
	 */
	public static void bubbleSort(int[] ar)
	{
		System.out.println("Array for bubble sort1 : "+Arrays.toString(ar));
		boolean swapped = false;
		for (int i = 0 ; i <ar.length - 1; i++)
		{
			for (int j = 0; j < ar.length -i-1; j++)
			{
				if (ar[j] > ar[j+1])
				{
					swapped = true;
					int temp = ar[j+1];
					ar[j+1] = ar[j];
					ar[j] = temp;
				} 
			} 
			if(swapped==false)break; // Modified bubble sort, breaks if array is already sorted.
		} 
		System.out.println("Sorted array by bubble sort1 : "+Arrays.toString(ar)+"\n");
	}
	public static void  main(String args[]){
		int [] arrayToBeSorted1 = {2,3,1,4,5,19,11,17,55,22,21,20,62};
		bubbleSort(arrayToBeSorted1);   


	}
}
