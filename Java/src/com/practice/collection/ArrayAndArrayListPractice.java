package com.practice.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayAndArrayListPractice {

	private void convertArrayToList(){

		// Case 2 is better performance wise BUT: it returns an immutable list. 
		// Meaning you cannot add/remove elements from it.

		// case1
		List<String> coreModules1 = Arrays.asList("TOOLBAR_TO_DO_LIST", "TOOLBAR_PROPERTY");
		// case2
		List<String> coreModules2 = new ArrayList<String>(Arrays.asList("TOOLBAR_TO_DO_LIST", "TOOLBAR_PROPERTY"));

		// case 3
		String [] a = new String[] {"amit","kabra"};
		// converting array to list is same as creating new list out of this array
		List<String> list = Arrays.asList(a);

		// case4
		String[] array1 = {"TOOLBAR_TO_DO_LIST", "TOOLBAR_PROPERTY"};
		List<String> coreModules3 = new ArrayList<>();
		Collections.addAll(coreModules3, array1);
		// case5
		String[] array2 = {"TOOLBAR_TO_DO_LIST", "TOOLBAR_PROPERTY"};
		ArrayList<String> coreModules4 = new ArrayList<>();
		coreModules4.addAll(Arrays.asList(array2));

	}

	public void convertListToArray(){
		ArrayList<String> assetTradingList = new ArrayList<>();                       
		assetTradingList.add("Stocks trading");
		assetTradingList.add("electronic trading");
		String [] assetTradingArray = new String[assetTradingList.size()];
		// converting list to array is actually creating new array out of list
		assetTradingList.toArray(assetTradingArray);
		System.out.println("Coverting list to array: "+Arrays.toString(assetTradingArray));
	}


	private void arraySort(){
		String [] sa = {"one", "two", "three", "four"};
		Arrays.sort(sa);
		for(String s : sa){
			System.out.print(s + " ");
		}
		System.out.println("now reverse sort");
		ReSortComparator rs = new ReSortComparator();
		Arrays.sort(sa, rs);//re-sort the array using the Comparator. sort(sa, rs)
		for(String s : sa){
			System.out.print(s + " ");
		}
	}

	static class ReSortComparator implements Comparator{
		//define the Comparator, it's ok for this to be an inner class

		@Override
		public int compare(Object a, Object b) {
			return ((String) b).compareTo((String) a);
		}

	}

	public static void toArrayFunction() {
		Map<Integer,Integer> m = new HashMap<>();
		m.put(1, 1);
		Set<Integer> s = new HashSet<>();
		s.add(1);
		List<Integer> l = new ArrayList<>();
		l.add(1);
		System.out.println(Arrays.toString(m.entrySet().toArray()));// m.entrySet() returns the set
		System.out.println(Arrays.toString(s.toArray()));
		System.out.println(Arrays.toString(l.toArray()));
	}

	private void printArray(){
		String [] sa = {"one", "two", "three", "four"};
		//	String [] sa = new String[]{"one", "two", "three", "four"}; // works too

		System.out.println("Printing Array1: "+Arrays.toString(sa));
		System.out.println("Printing Array1: "+Arrays.asList(sa));
		System.out.println("Printing Array1: "+Arrays.deepToString(sa));
		String [][] sb = {{"one", "two"},{ "three", "four"}};
		// String [][] sb = {sa,sa}; // works too
		System.out.println("Printing Array1: "+Arrays.toString(sb));
		System.out.println("Printing Array1: "+Arrays.asList(sb));
		System.out.println("Printing Array1: "+Arrays.deepToString(sb));
		System.out.println("Printing Array1: "+sb[1][0]);



	}

	public static void main(String[] args) {

		ArrayAndArrayListPractice test = new ArrayAndArrayListPractice();
		test.printArray();
		//test.convertArrayToList();		
		test.convertListToArray();
		//test.arraySort();

	}
}
