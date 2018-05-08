package com.practice.design.cache;

import java.util.HashMap;
import java.util.PriorityQueue;

// PriorityQueue + HashMap set O(capacity) get O(capacity)

public class LFUCache {
	long stamp;
	int capacity;
	int num;
	PriorityQueue<Pair> minHeap;
	HashMap<Integer, Pair> hashMap;
	
	class Pair implements Comparable<Pair> {
		long stamp;
		int key;
		int value;
		int times;
		public Pair(int key, int value, int times, long stamp) {
			this.key = key;
			this.value = value;
			this.times = times;
			this.stamp = stamp;
		}

		public int compareTo(Pair that) {
			if (this.times == that.times) {
				return (int)(this.stamp - that.stamp);
			} else {
				return this.times - that.times;    
			}
		}
	}

	// @param capacity, an integer
	public LFUCache(int capacity) {
		// Write your code here
		this.capacity = capacity;
		num = 0;
		minHeap = new PriorityQueue<Pair>();
		hashMap = new HashMap<Integer, Pair>();
		stamp = 0;
	}

	// @param key, an integer
	// @param value, an integer
	// @return nothing
	public void set(int key, int value) {
		if (capacity == 0) {
			return;
		}
		// Write your code here
		if (hashMap.containsKey(key)) {
			Pair old = hashMap.get(key);
			minHeap.remove(old);

			Pair newNode = new Pair(key, value, old.times + 1, stamp++);

			hashMap.put(key, newNode);
			minHeap.offer(newNode);
		} else if (num == capacity) {
			Pair old = minHeap.poll();
			hashMap.remove(old.key);

			Pair newNode = new Pair(key, value, 1, stamp++);

			hashMap.put(key, newNode);
			minHeap.offer(newNode);
		} else {
			num++;
			Pair pair = new Pair(key, value, 1, stamp++);
			hashMap.put(key, pair);
			minHeap.offer(pair);
		}
	}

	public int get(int key) {
		if (capacity == 0) {
			return -1;
		}
		// Write your code here
		if (hashMap.containsKey(key)) {
			Pair old = hashMap.get(key);
			minHeap.remove(old);

			Pair newNode = new Pair(key, old.value, old.times + 1, stamp++);

			hashMap.put(key, newNode);
			minHeap.offer(newNode);
			return hashMap.get(key).value;
		} else {
			return -1;
		}
	}
}
