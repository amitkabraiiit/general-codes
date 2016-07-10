package com.practice.java8;

import java.util.Arrays;
import java.util.List;

public class LambdaPractice3 {
	private static void map() {
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500); 
		for (Integer cost : costBeforeTax) { 
			double price = cost + .12*cost; 
			System.out.println(price); 
		} 
		System.out.println("========");
		// With Lambda expression: 
		costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println); 
		System.out.println("========");
	}

	private static void reduce() {
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500); 
		double total = 0; 
		for (Integer cost : costBeforeTax) { 
			double price = cost + .12*cost; 
			total = total + price; 
		} 
		System.out.println("Total : " + total); 
		System.out.println("========");

		// New way: 
		/*
		 * Stream.reduce() take input as an accumulator function which takes two parameters: a partial result of the reduction 
		 * (in this case, the sum of all processed integers so far) and the next element of the stream (in this case, an integer).
		 * It returns a new partial result. In this case, the accumulator function is a lambda expression. 
		 */
		double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((x, y) -> x + y).get(); 
		//double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get(); 
		System.out.println("Total : " + bill);		
	}

	public static void main(String[] args) {
		map();
		reduce();
	}


}
