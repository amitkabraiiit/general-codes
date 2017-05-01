package com.practice.design;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

class HashFunction{
	public Integer hash(Object key){
		/*
		 *  In order for consistent hashing to be effective it is important to have a hash function that mixes well. 
		 *  Most implementations of Object's hashCode do not mix well - for example, they typically produce a restricted 
		 *  number of small integer values - so we have a HashFunction interface to allow a custom hash function to be used. 
		 *  MD5 hashes are recommended here.
		 */
		Integer hash = Integer.parseInt((String)key); // some algo to create hash
		return hash;
	}
}

public class ConsistentHash<T> {
	private final HashFunction hashFunction;
	private final int numberOfReplicas;
	private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

	public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;
		for (T node : nodes) add(node);
	}

	public void add(T node) {
		for (int i = 0; i < numberOfReplicas; i++) circle.put(hashFunction.hash(node.toString() + i),  node);
	}

	public void remove(T node) {
		for (int i = 0; i < numberOfReplicas; i++)  circle.remove(hashFunction.hash(node.toString() + i));
	}

	public T get(Object key) {
		if (circle.isEmpty()) return null;
		int hash = hashFunction.hash(key);
		if (!circle.containsKey(hash)) {
			SortedMap<Integer, T> tailMap =  circle.tailMap(hash); // if treemap contains 1,2,3,4 keys , then tailmap(2) contains 2,3,4
			
			/*
			 * To find a node for an object (the get method), the hash value of the object is used to look in the map. 
			 * Most of the time there will not be a node stored at this hash 
			 * value (since the hash value space is typically much larger than the number of nodes, even with replicas), 
			 * so the next node is found by looking for the first key in the tail map.
			 */
			
			hash = tailMap.isEmpty() ?  circle.firstKey() : tailMap.firstKey();
		}
		return circle.get(hash);
	} 

}
