package com.practice.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BucketSort{

	public static void bucketSort1(int[] a, int maxVal) {
		int [] bucket=new int[maxVal+1];

		for (int i=0; i<bucket.length; i++) {
			bucket[i]=0;
		}

		for (int i=0; i<a.length; i++) {
			bucket[a[i]]++;
		}
		// System.out.println(Arrays.toString(bucket)); // [2, 2, 2, 2, 3, 2]
		int outPos=0;
		for (int i=0; i<bucket.length; i++) {
			for (int j=0; j<bucket[i]; j++) {
				a[outPos++]=i;
			}
		}
	}


	public static int[] bucketSort2(int[] array, int bucketCount) {
		if (bucketCount <= 0) throw new IllegalArgumentException("Invalid bucket count");
		if (array.length <= 1) return array; //trivially sorted
		int high = array[0];
		int low = array[0];
		for (int i = 1; i < array.length; i++) { //find the range of input elements
			if (array[i] > high) high = array[i];
			if (array[i] < low) low = array[i];
		}
		double interval = ((double)(high - low + 1))/bucketCount; //range of one bucket

		ArrayList<Integer> buckets[] = new ArrayList[bucketCount];
		for (int i = 0; i < bucketCount; i++) { //initialize buckets
			buckets[i] = new ArrayList();
		}
		for (int i = 0; i < array.length; i++) { //partition the input array
			buckets[(int)((array[i] - low)/interval)].add(array[i]);
		}
		int pointer = 0;
		for (int i = 0; i < buckets.length; i++) {
			Collections.sort(buckets[i]); //mergeSort
			for (int j = 0; j < buckets[i].size(); j++) { //merge the buckets
				array[pointer] = buckets[i].get(j);
				pointer++;
			}
		}
		return array;
	}

	public static void main(String[] args) {
		int maxVal=5;
		int [] data= {5,3,0,2,4,1,0,5,2,3,1,4,4}; 

		System.out.println("Before: " + Arrays.toString(data));
		bucketSort1(data,maxVal);
		System.out.println("After:  " + Arrays.toString(data));

		int d[] ={5,3,0,2,4,1,0,5,2,3,1,4,4}; 
		System.out.println("Before: " + Arrays.toString(d));
		d =	bucketSort2(data, 3);
		System.out.println("After:  " + Arrays.toString(d));

	}
}