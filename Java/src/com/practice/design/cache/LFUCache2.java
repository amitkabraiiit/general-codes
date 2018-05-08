package com.practice.design.cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

class Tuple {
    int value;
    int times;
    int stamp;
    public Tuple (int value, int stamp, int times) {
        this.value = value;
        this.stamp = stamp;
        this.times = times;
    }
}

// TreeMap + HashMap set O(log(capacity)) get O(log(capacity))
public class LFUCache2{
	
	
	private int capacity;
	private int stamp;
	private HashMap<Integer, Tuple> hashMap;
	private TreeMap<Tuple, Integer> treeMap;
	

	
	public LFUCache2(int capacity) {
	    this.capacity = capacity;
	    stamp = 0;
	    hashMap = new HashMap<Integer, Tuple>(); 
	    treeMap = new TreeMap<Tuple, Integer>(new Comparator<Tuple>() {
	        public int compare(Tuple t1, Tuple t2) {
	            if (t1.times == t2.times) {
	                return t1.stamp - t2.stamp;
	            }
	            return t1.times - t2.times;
	        }
	    });
	}

	public int get(int key) {
	    if (capacity == 0) {
	        return -1;
	    }
	    if (!hashMap.containsKey(key)) {
	        return -1;
	    }
	    Tuple old = hashMap.get(key);
	    treeMap.remove(old);
	    Tuple newTuple = new Tuple(old.value, stamp++, old.times + 1);
	    treeMap.put(newTuple, key);
	    hashMap.put(key, newTuple);
	    return old.value;
	}

	public void set(int key, int value) {
	    if (capacity == 0) {
	        return;
	    }
	    if (hashMap.containsKey(key)) {
	        Tuple old = hashMap.get(key);
	        Tuple newTuple = new Tuple(value, stamp++, old.times + 1);
	        treeMap.remove(old);
	        hashMap.put(key, newTuple);
	        treeMap.put(newTuple, key);
	    } else {
	        if (treeMap.size() == capacity) {
	            int endKey = treeMap.pollFirstEntry().getValue();
	            hashMap.remove(endKey);
	        }
	        Tuple newTuple = new Tuple(value, stamp++, 1);
	        hashMap.put(key, newTuple);
	        treeMap.put(newTuple, key);
	    }
	}
}
