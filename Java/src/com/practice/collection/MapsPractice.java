package com.practice.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapsPractice {
	private void printMap(){
		Map<Integer,String> m = new HashMap<Integer , String>();
		m.put(new Integer(1), "one");
		m.put(new Integer(2), "two");
		System.out.println("==============");
		System.out.println("Printing Map");
		System.out.println("==============");
		System.out.println(m);
		System.out.println(m.entrySet());
		System.out.println(Arrays.toString(m.entrySet().toArray()));
		/*  output
		 *  {1=one, 2=one}
		 *  [1=one, 2=one]
		 *  [1=one, 2=one]
		 */
		for (Object key : m.keySet()) {
		    System.out.println(key);
		}

		for (Object value : m.values()) {
		    System.out.println(value);
		}

		// Using for each
		for (Map.Entry<Integer, String> entry : m.entrySet()) {
		    Integer key = entry.getKey();
		    String value = entry.getValue();
		    System.out.println("Key: "+key+" Value: "+value);
		}
		
		// Using iterator using generics
		Iterator<Map.Entry<Integer, String>> it = m.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, String> temp = it.next();
		    System.out.println("Key: "+temp.getKey()+" Value: "+temp.getValue());
		}
		
		// Using iterator without generics.Without generics, 
		// Iterator returns the Object and we need to typecast it.

		Map mm = new HashMap();
		mm.put(1, "one");
		mm.put(2, "two");
		Iterator itt = mm.entrySet().iterator();
		while(itt.hasNext()){
			Map.Entry entry = (Map.Entry)itt.next();
			System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
		}
		
		// Iterating over keys and searching for values (inefficient).
		for (Integer key : m.keySet()) {
		    String value = m.get(key);
		    System.out.println("Key = " + key + ", Value = " + value);
		}
		
	}
	

	private void mapToList(){
		Map mm = new HashMap();
		mm.put(1, "one");
		mm.put(2, "two");
		System.out.println("Map to List :"+Arrays.toString(mm.entrySet().toArray()));
	}
	
	
	public static void main(String[] args) {
		MapsPractice m = new MapsPractice();
		m.printMap();
		m.mapToList();
	}
}
