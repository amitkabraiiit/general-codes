package com.practice.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayAndArrayListPractice {

	private void convertArrayToList(){

		// Case 2 is better performance wise BUT: it returns an immutable list. 
		// Meaning you cannot add/remove elements from it.

		// case1
		List<String> coreModules1 = Arrays.asList("TOOLBAR_TO_DO_LIST", "TOOLBAR_PROPERTY");

		// case2
		List<String> coreModules2 = new ArrayList<String>(Arrays.asList("TOOLBAR_TO_DO_LIST", "TOOLBAR_PROPERTY"));

		// case3
		String[] array1 = {"TOOLBAR_TO_DO_LIST", "TOOLBAR_PROPERTY"};
		List<String> coreModules3 = new ArrayList<String>();
		Collections.addAll(coreModules3, array1);
		
		// case4
		String[] array2 = {"TOOLBAR_TO_DO_LIST", "TOOLBAR_PROPERTY"};
		ArrayList coreModules4 = new ArrayList();
		coreModules4.addAll(Arrays.asList(array2));

	}

	public void convertListToArray(){
		ArrayList assetTradingList = new ArrayList();                       
		assetTradingList.add("Stocks trading");
		assetTradingList.add("electronic trading");
		String [] assetTradingArray = new String[assetTradingList.size()];
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
	
	private void printArray(){
		String [] sa = {"one", "two", "three", "four"};
		System.out.println("Printing Array1: "+Arrays.toString(sa));
		System.out.println("Printing Array2: "+Arrays.deepToString(sa));
		System.out.println("Printing Array3: "+Arrays.asList(sa));

	}
	
	private void print2DArray(){
		String[] arr1 = new String[] { "Fifth", "Sixth" };
		String[] arr2 = new String[] { "Seventh", "Eight" };
		// An array of array containing String objects
		String[][] arrayOfArray = new String[][] { arr1, arr2 };
		 		 
		// Print the array using default toString method
		System.out.println(arrayOfArray); // [[Ljava.lang.String;@1ad086a 
	
		// Print the array using Arrays.toString()
		System.out.println(Arrays.toString(arrayOfArray)); // [[Ljava.lang.String;@10385c1, [Ljava.lang.String;@42719c]
	 
		// Print the array using Arrays.deepToString()
		System.out.println(Arrays.deepToString(arrayOfArray)); // [[Fifth, Sixth], [Seventh, Eighth]]

	}
	
	public static void main(String[] args) {

		ArrayAndArrayListPractice test = new ArrayAndArrayListPractice();
		test.printArray();
		//test.convertArrayToList();		
		test.convertListToArray();
		//test.arraySort();
		
	}
}
