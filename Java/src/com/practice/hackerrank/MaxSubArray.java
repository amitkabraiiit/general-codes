package com.practice.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class MaxSubArray {
	private static void maxNonContinuousSum(int[] a) {
		int sum = 0 ; 
		for (int i=0 ; i < a.length; i++){
			if(a[i] < 0)continue;
			sum = sum + a[i];
		}
		if(sum == 0) {
			Arrays.sort(a);
			System.out.print(a[a.length -1]);
		}else{
			System.out.print(sum);	
		}

	}

	private static void maxContinuousSum(int[] a) {	
		int sum = 0 , max = 0;
		for (int i=0 ; i < a.length; i++){
			sum = sum + a[i];
			if(sum < 0) sum = 0;
			if(sum > max) max = sum;
		}
		if(max == 0) {
			Arrays.sort(a);
			System.out.print(a[a.length -1]+" ");
		}else{
			System.out.print(max+" ");	
		}

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int x , t, count =0;
		int a[];
		for(int i = 0 ; i < n; i++){
			x = Integer.parseInt(sc.nextLine());
			System.out.println("total number "+x);
			a = new int[x];
			for(int j = 0 ; j < a.length; j++){
				a[j] = sc.nextInt();
			}
			System.out.println("numbers "+Arrays.toString(a));
			maxContinuousSum(a);
			maxNonContinuousSum(a);
			sc.nextLine();
		}
	}
}
