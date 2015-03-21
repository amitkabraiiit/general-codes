package com.practice.sorting;
import java.util.Arrays;

public class InsertionSort{

	/*
	 * Insertion sort maintains a sorted sub-array, and repetitively inserts new elements into it. The process is as following:
	 * Take the first element as a sorted sub-array.
	 * Insert the second element into the sorted sub-array (shift elements if needed).
	 * Insert the third element into the sorted sub-array.
	 * Repeat until all elements are inserted. 
	 * 
	 * Suppose the array length is n. The outer loop runs roughly n times, and the inner loop on average runs n/2 times. 
	 * The total time is about t(n) = n * (n/2) = O(n^2). In terms of the efficiency, this is the same as selection sort.
	 */
	public static void insertionSort(int[] ar)
	{
		System.out.println("Array for insertion sort : "+Arrays.toString(ar));

		int temp;
		for (int i = 1; i < ar.length; i++) {
			for(int j = i ; j > 0 ; j--){ // suppose till now sorted array is 1 2 7 9 and now we got 3 then we need to put 3 after 2, so 
				// we first swap 3 with 9 then with 3 with 7 and then 3 gets its position.
				if(ar[j] < ar[j-1]){
					temp = ar[j];
					ar[j] = ar[j-1];
					ar[j-1] = temp;
				}
			}
		}
		System.out.println("Sorted array by insertion sort : "+Arrays.toString(ar)+"\n");	
	}
	public static void  main(String args[]){
		int [] arrayToBeSorted2 = {2,3,1,4,5,19,11,17,55,22,21,20,62};
		insertionSort(arrayToBeSorted2);

	}
}
