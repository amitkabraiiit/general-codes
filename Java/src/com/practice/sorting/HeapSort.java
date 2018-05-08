package com.practice.sorting;

import java.util.Arrays;

// SEE HEAP2.JAVA and HEAP.JAVA as well for heap implementation.
public class HeapSort 
{ 
	// Max Heap
	public void sort(int arr[])
	{
		int n = arr.length;

		// Given array is heap for us. So elements in array is heap
		
		// Convert it to heap to max heap
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);

		System.out.println("Max Heap "+ Arrays.toString(arr));
		// Sort heap : One by one extract an element from heap
		for (int i=n-1; i>=0; i--){
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			// call max heapify on the REDUCED heap
			heapify(arr, i, 0); // see the position of i changed as compared to heapify called above,now i is max and we heapify from 0.
		}
	}

	// To heapify a subtree rooted with node i which is an index in arr[]. n is size of heap
	void heapify(int arr[], int heapsize, int i){  // also a siftDown.
		int largest = i;  // Initialize largest as root
		int l = 2*i + 1;  // left = 2*i + 1
		int r = 2*i + 2;  // right = 2*i + 2

		if (l < heapsize && arr[l] > arr[largest])	largest = l;
		if (r < heapsize && arr[r] > arr[largest])	largest = r;

		// If largest is not root
		if (largest != i){
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// Recursively heapify the affected sub-tree
			System.out.println("Original array "+ Arrays.toString(arr));
			heapify(arr, heapsize, largest);
		}
	}

	/* A utility function to print array of size n */
	static void printArray(int arr[])
	{
		int n = arr.length;
		for (int i=0; i<n; ++i)
			System.out.print(arr[i]+" ");
		System.out.println();
	}

	// Driver program
	public static void main(String args[])
	{
		int arr[] = {12, 11, 13, 5, 6, 7};
		int n = arr.length;

		System.out.println("Original array "+ Arrays.toString(arr));
		HeapSort ob = new HeapSort();
		ob.sort(arr);

		System.out.println("Sorted array "+ Arrays.toString(arr));
	}
}