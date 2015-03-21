package com.practice.sorting;
import java.util.Arrays;

public class SelectionSort{

	/*
	 * Find the smallest element, and put it to the first position.
	 * Find the next smallest element, and put it to the second position.
	 * Repeat until all elements are in the right positions. 
	 * 
	 * The outer loop runs n-1 rounds, roughly one for each position. Inside each outer loop, the inner loop goes through the unsorted 
	 * part of the array. On average, each inner loop scans through n/2 elements. 
	 * The total time is roughly t(n) = (n Ð 1) * (n/2) *k = O(n^2), where k denotes the number of basic operations 
	 * inside each inner loop; the constants are absorbed in the big-Oh notion. Note that in the big-Oh notion, 
	 * we only care about the dominating factor (which is n^2 in this case).
	 */
	public static void selectionSort(int[] ar)
	{
		System.out.println("Array for selection sort : "+Arrays.toString(ar));
		for (int i = 0 ; i <ar.length - 1; i++)
		{
			for (int j = i+1; j < ar.length; j++)
			{
				if (ar[j] < ar[i])
				{
					int temp = ar[j];
					ar[j] = ar[i];
					ar[i] = temp;
				} 
			} 
		} 
		System.out.println("Sorted array by selection sort : "+Arrays.toString(ar)+"\n");
	}
	public static void  main(String args[]){
		int [] arrayToBeSorted3 = {2,3,1,4,5,19,11,17,55,22,21,20,62};
		selectionSort(arrayToBeSorted3);

	}
}
