package com;

import java.util.ArrayList;
import java.util.List;

class Node<K,V>{
	K k;
	V v;
	public Node(K k,V v) {
		this.k = k;
		this.v = v;
	}
	public K getKey(){
		return this.k;
	}
	public V getValue(){
		return this.v;
	}	
}

class NewHashMap<K,V>{

	int maxSize = 10;
	ArrayList<List<Node<K,V>>> temp = new ArrayList<>(maxSize);
	public NewHashMap() {
		for(int i = 0 ; i < 10; i++){
			temp.add(null);
		}
	}

	public void add(K k, V v){
		int len = k.toString().length()%maxSize;
		List<Node<K,V>> list = temp.get(len);

		if(list != null){
			for(Node<K,V> n : list){
				if(n.k.equals(k)) {
					list.remove(n);
				}
			}
			list.add(new Node(k,v));
		}else{
			list.add(len, new Node(k,v));
		}
	}
	public V get(K k){
		int len = k.toString().length()%maxSize;
		List<Node<K,V>> list = temp.get(len);
		for(Node<K,V> n : list){
			if(n.k.equals(k)) return n.v;
		}
		return null;
	}

}

public class Test7 {
	public static void main(String[] args) {
		NewHashMap<String, Integer> s = new NewHashMap<>();
		s.add("Shri", 1);
		s.add("Shr", 2);
		System.out.println(s.get("Shri"));
		System.out.println(s.get("Shr"));

	}

}
