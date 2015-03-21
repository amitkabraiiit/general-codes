package com.practice.collection;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListPractice {

	private void printList(){
		List<Integer> l = new ArrayList<Integer>();
		l.add(new Integer(0));
		l.add(new Integer(1));
		l.add(new Integer(2));	
		System.out.println("==============");
		System.out.println("Printing List");
		System.out.println("==============");
		
		for(Integer i : l){
			System.out.print(i+",");
		}
		
		for(int i=0 ; i<l.size();i++){ // similarly while loop
			System.out.print(i+",");
		}
		
		Iterator<Integer> it = l.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+",");
		}
		
	}

	
	public static void main(String[] args) {
		ListPractice p = new ListPractice();
		p.printList();	
		System.out.println();
	}	
}
