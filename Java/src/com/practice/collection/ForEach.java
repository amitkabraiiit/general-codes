package com.practice.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForEach {
	
	private void execute(){
		List<Integer> l = new ArrayList<Integer>();
		l.add(new Integer(0));
		l.add(new Integer(1));
		l.add(new Integer(2));
		
		Iterator<Integer> it = l.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+",");
		}
		System.out.println();
		/*
		 * The iterator is just clutter. Furthermore, it is an opportunity for error. 
		 * The iterator variable occurs three times in each loop: that is two chances to get it wrong. 
		 * The for-each construct gets rid of the clutter and the opportunity for error.
		 * The loop below reads as “for each Integer i in l.”
		 * 
		 * So when should you use the for-each loop? Any time you can. 
		 * It really beautifies your code. Unfortunately, you cannot use it everywhere.
		 * 
		 * The for-each loop in java uses the underlying iterator mechanism.
		 * The for-each loop hides the iterator, so you cannot call remove. 
		 * Therefore, the for-each loop is not usable for filtering.
		 * Similarly it is not usable for loops where you need to replace 
		 * elements in a list or array as you traverse it.  
		 * 
		 * The foreach loop, added in Java 5 (also called the "enhanced for loop"), is equivalent 
		 * to using a java.util.Iterator--it's syntactic sugar for the same thing. 
		 * Therefore, when reading each element, one by one and in order, 
		 * a foreach should always be chosen over an iterator, as it is more convenient and concise.
		 */
		
		for(Integer i : l){
			System.out.print(i+",");
		}
		
	}
	
	public static void main(String[] args) {
		ForEach loop = new ForEach();
		loop.execute();	
	}
}
