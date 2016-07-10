package com.practice.sorting;

import java.util.Arrays;

public class HeapSort 
{ 
	private static int n; 
	private static int left; 
	private static int right; 
	private static int max;

	public static void swap(int[] a, int i, int j){ 
		int t=a[i]; 
		a[i]=a[j]; 
		a[j]=t; 
	} 
	public static void heapSort(int []a){// Overall:O(nlog(n)) (no of time loop run)*(maxheap complexity)
		n=a.length-1;
		buildheap(a); 
		System.out.println(Arrays.toString(a));
		for(int i=n;i>0;i--){ 
			swap(a, 0, i); 
			n=n-1; 
			maxheap(a, 0); 
		} 
	} 	
	public static void buildheap(int []a){  // we are building max heap here. Overall : O(n)
		for(int i=n/2;i>=0;i--){  
			maxheap(a,i); 
		} 
	} 
	public static void maxheap(int[] a, int i){  // or heapify function. Overall : O(log n)
		/* sift down */
		left=2*i; 
		right=2*i+1; 
		max = i;
		if(left <= n && a[left] > a[max])max=left;  
		if(right <= n && a[right] > a[max])max=right; 
		if(max!=i){ 
			swap(a, i,max); 
			maxheap(a, max); 
		} 
	} 
	public static void main(String[] args) { 
		int []a1={4,1,3,2,16,9,10,14,8,7}; 
		heapSort(a1); 
		for(int i=0;i<a1.length;i++){ 
			System.out.print(a1[i] + " "); 
		} 
	} 
}