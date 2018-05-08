package com.practice.designpattern.structural;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * It extends AbstractMap class so that it * become a Map and can be passed around where a Map is needed. 
 * All other method * is implemented in AbstractMap except the adapter functionality which is * implemented 
 * in constructor of this class.
 */

class MapAdapter extends AbstractMap 
{ 
	private Map map; 
	public MapAdapter(Object[][] array) { 
		super(); 
		map = new HashMap(); 
		for(Object[] mapping : array){ 
			map.put(mapping[0], mapping[1]); 
		} 
	} 
	@Override public Set entrySet() { 
		return map.entrySet();
	}
}


public class Adaptor2 {
	public static void main(String args[]) { 
		Integer[][] squares = { {2, 4}, {3, 9}, {4, 16}};
		MapAdapter adapter = new MapAdapter(squares); 
		System.out.println("adapter map contains : " + adapter); 
	} 

}
