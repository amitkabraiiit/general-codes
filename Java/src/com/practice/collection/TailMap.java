package com.practice.collection;

import java.util.SortedMap;
import java.util.TreeMap;

public class TailMap {
	public static void main(String[] args) {
		// creating maps 
		TreeMap<Integer, String> treemap = new TreeMap<Integer, String>(); // Treemap is the implementation for sorted map
		SortedMap<Integer, String> treemapincl = new TreeMap<Integer, String>();

		// populating tree map
		treemap.put(2, "two");
		treemap.put(1, "one");
		treemap.put(3, "three");
		treemap.put(6, "six");
		treemap.put(5, "five");    

		System.out.println("Checking first key");
		System.out.println("First key is: "+ treemap.firstKey());
		System.out.println("tree map values: "+treemap);    


		System.out.println("Getting tail map");
		treemapincl=treemap.tailMap(5); // only defined on treemap and not on normal map
		System.out.println("Tail map values: "+treemapincl);    
		System.out.println(treemap.hashCode());
		System.out.println(treemapincl.hashCode());
	}  
}
