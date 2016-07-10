package com.practice.java8;

import java.util.Arrays;
import java.util.List;

public class ForEachPractice {

	public static void main(String[] args) {
		List<Integer> l = Arrays.asList(1,2,3,4,5);
		l.forEach(System.out::println);
		System.out.println("==========");
		l.forEach((n) -> System.out.println(n));
	}
}
