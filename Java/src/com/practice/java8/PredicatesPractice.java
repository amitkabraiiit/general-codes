package com.practice.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicatesPractice {
	public static void condition(List<Integer> list, Predicate<Integer> predicate) {
		for(Integer n: list)  {
			if(predicate.test(n)) {
				System.out.println(n + " ");
			}
		}
	}

	public static void practicePradicate(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		System.out.println("Print all numbers:");
		condition(list, (n)->true);
		System.out.println("Print no numbers:");
		condition(list, (n)->false);
		System.out.println("Print even numbers:");
		condition(list, (n)-> n%2 == 0 );
		System.out.println("Print odd numbers:");
		condition(list, (n)-> n%2 == 1 );
		System.out.println("Print numbers greater than 5:");
		condition(list, (n)-> n > 5 );
	}
	
	private static void practicePradicate2() {
		Predicate<Integer> greaterThanTen = (i) -> i > 10;
		System.out.println(greaterThanTen.test(14)); // True		
		Predicate<Integer> lowerThanTwenty = (i) -> i < 20;
		System.out.println(greaterThanTen.and(lowerThanTwenty).test(15)); // True
		System.out.println(greaterThanTen.and(lowerThanTwenty).negate().test(15)); // False	
	}
	
	
	public static void main(String[] args) {
		PredicatesPractice.practicePradicate();
		PredicatesPractice.practicePradicate2();

	}


}
