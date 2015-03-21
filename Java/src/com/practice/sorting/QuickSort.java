package com.practice.sorting;
import java.util.Arrays;


public class QuickSort {

	/*
	 * Nice explaination over here : https://www.youtube.com/watch?v=y_G9BkAm6B8
	 * 
	 * Need to learn the below algo, changing anything won't let it in working start
	 */
	public static void quickSort(int[] a, int first, int last)
	{
		if (first < last)
		{
			/*
			 *  choose any number as pivot , say first element. What pivot we choose  doesn't matter since role of pivot is just parition.
			 *  i.e move element lesser to left and element higher to right of itself. Even if we change pivot in each iteration, it doesn't
			 *   matter for the same reason 
			*/
			int pivot = first ; // could be used as rand()%size;
			int i = first, j = last, temp;			
			// Partition loops, move all element lesser than equal to pivot to the left of pivot and greater than equal to pivot to the write of pivot
			while (i < j)
			{
				// Find an element bigger than the pivot from the first
				while (a[i] <= a[pivot] && i < last)
					i++;
				// Find an element smaller than the pivot from the last
				while (a[j] >= a[pivot] && j > first)
					j--;
				// Swap the two elements found
				if (i < j){
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;	
				}
			}
			// move the pivot element to the middle
			temp = a[pivot];
			a[pivot] = a[j];
			a[j] = temp;	
			quickSort(a, first, j - 1); // sort the first part
			quickSort(a, j + 1, last); // sort the last part
		}
	}
	public static void main(String[] args) {
		int [] inputArray = {2,3,1,4,5,19,11,17,55,22,21,20,62};
		QuickSort.quickSort(inputArray, 0, inputArray.length - 1);
		System.out.println(Arrays.toString(inputArray));
	}
}
