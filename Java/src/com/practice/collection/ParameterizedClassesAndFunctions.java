package com.practice.collection;

import java.util.Collection;

public class ParameterizedClassesAndFunctions { // Or generics classesin

	interface Pair<K, V> {
		public K getKey();
		public V getValue();
	}

	static class OrderedPair<K, V> implements Pair<K, V> {

		private K key;
		private V value;

		public OrderedPair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey()	{ return key; }
		public V getValue() { return value; }
	}

	static class GenericsType<T> {
		private T t;
		public T get(){
			return this.t;
		}
		public void set(T t1){
			this.t=t1;
		}	
	}
	
	public static <T> T print(T item){ // generic function or parameterized function
		return item;
	}
	
	static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
	    for (T o : a) {
	        c.add(o); 
	    }
	}
	
	public static void main(String[] args) {
		Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8);
		Pair<String, String>  p2 = new OrderedPair<String, String>("hello", "world");
		System.out.println("Key : "+p1.getKey()+" Value : "+p1.getValue());
		System.out.println("Key : "+p2.getKey()+" Value : "+p2.getValue());
		// 
		OrderedPair<String, Integer> p3 = new OrderedPair<>("Even", 8);
		OrderedPair<String, String>  p4 = new OrderedPair<>("hello", "world");
		System.out.println("Key : "+p3.getKey()+" Value : "+p3.getValue());
		System.out.println("Key : "+p4.getKey()+" Value : "+p4.getValue());
		
		GenericsType<String> type = new GenericsType<>();
		type.set("Pankaj"); //valid
		
		GenericsType type1 = new GenericsType(); //raw type
		type1.set("Pankaj"); //valid
		type1.set(10); //valid and autoboxing support
		
		ParameterizedClassesAndFunctions pc = new ParameterizedClassesAndFunctions();
		System.out.println(pc.<String>print("amit"));
		String s = "amit";
		
		
	}

}
