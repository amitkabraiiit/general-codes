package com.practice.design;

import java.util.LinkedList;

/**
 * 8.10 Design and implement a hash table which uses chaining (linked lists) to handle collisions.
 */
/**
 * We need to store both the value and the original key.Create another object called Cell which pairs keys and
 * values. With this implementation, our linked list is of type Cell.
 */


public class HashmapChaining<K, V> {
	private final int MAX_SIZE = 10;
	LinkedList<Cell<K, V>>[] items;

	@SuppressWarnings("unchecked")
	public HashmapChaining() {
		items = (LinkedList<Cell<K, V>>[]) new LinkedList[MAX_SIZE];

	}

	// Really, really stupid hash
	public int hashCodeOfKey(K key) {
		return key.toString().length() % items.length;

	}

	public void put(K key, V value) {
		int x = hashCodeOfKey(key);
		if (items[x] == null) {
			items[x] = new LinkedList<Cell<K, V>>();

		}

		LinkedList<Cell<K, V>> collided = items[x];
		// look for items with same key and replace if found
		for (Cell<K, V> c : collided) {
			if (c.equivalent1(key)) {
				collided.remove(c);
				break;
			}
		}
		Cell<K, V> cell = new Cell<K, V>(key, value);
		collided.add(cell);
	}

	public V get(K key) {
		int x = hashCodeOfKey(key);
		if (items[x] == null) {
			return null;
		}
		LinkedList<Cell<K, V>> collided = items[x];
		for (Cell<K, V> c : collided) {
			if (c.equivalent1(key)) {
				return c.getValue();
			}
		}

		return null;
	}
}

/*
 * The Cell class pairs the data value and its key. This will allow us to search
 * through the linked list(created by colliding, but different, keys) and find
 * the object with the exact key value.
 */
class Cell<K, V> {
	private K key;
	private V value;

	public Cell(K k, V v) {
		key = k;
		value = v;
	}

	// public boolean equivalent(Cell<K,V> c){
	// return equivalent1(c.getKey());
	// }

	public boolean equivalent1(K k) {
		return false;
		// return key,equals(k);
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

}
/*
 * Another common implementation for a hash table is to use a binary search tree
 * as the underlying data structure. Retrieving an element will no longer be O(1)
 * (although,technically, it's not O(1) if there are many collisions), but it
 * prevents us from creating an unnecessarily large array to hold items.
 */

