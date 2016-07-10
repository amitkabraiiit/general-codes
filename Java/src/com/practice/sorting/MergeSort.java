package com.practice.sorting;
import java.util.Arrays;

public class MergeSort {
	private  int[] array1 ;
	private  int[] array2 ;
	public void sort(int [] inputArray)
	{
		array1 = inputArray;
		array2 = new int[inputArray.length];
		doMergeSort(0, inputArray.length - 1);
	}
	
	/*
	 * Divide the array in half.
	 * Recursively divide until each subarray has only one element.
	 * Merge the sorted subarrays.
	 * 
	 * The merge sort approach recursively divides the array in half, and this generates totally log(n) 
	 * levels of divisions, and in consequence, log(n) levels of merging. For each level, merging runs 
	 * in linear time. The total running time is about O(n * log(n)). \
	 * This is much more efficient than selection/insertion/bubble sorts which run in O(n^2) time.
	 * 
	 * Merge sort requires additional scratch space proportional to the size of the input array. 
	 * Merge sort is relatively simple to code and offers performance typically only slightly 
	 * below that of quicksort.
	 */
	
	public void doMergeSort(int first, int last){
		if(first < last){
			int mid = (first + last)/2;
			doMergeSort(first , mid);
			doMergeSort(mid+1 , last);
			merge(first , mid, last);
		}
	}
	public void merge(int first, int mid, int last){
		int k;
		for(k=first; k <= last; k++) array2[k] = array1[k];
		k=first;
		int i=first , j=mid+1;
		while (i<=mid && j<=last){
			if(array2[i] <= array2[j]){
				array1[k++] = array2[i++];
			}
			else{
				array1[k++] = array2[j++];
			}
		}
		while(i<=mid)array1[k++]=array2[i++];
		while(j <=last)array1[k++]=array2[j++];

	}
	
	public void mergeInPlace(int first, int mid, int last){ // in place merge without extra space
		int i=first , j=mid+1;
		int temp;
		while (i<=mid && j<=last){
			if(array1[i] > array1[j]){
				temp = array1[i];
				array1[i]=array1[j];
				array1[j]=temp;
				if((i+1) <= mid && array1[i+1]>array1[j]){
					// do nothing
				}else{
					j++;
				}
			}else{
				i++;
			}
		}
	}
	
	public static void main(String[] args) {

		int [] inputArray = {2,3,1,4,5,19,11,17,55,22,21,20,62};
		MergeSort sortAlgo = new MergeSort();
		sortAlgo.sort(inputArray);
		System.out.println(Arrays.toString(inputArray));
	}
}
