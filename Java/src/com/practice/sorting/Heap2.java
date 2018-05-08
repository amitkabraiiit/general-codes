package com.practice.sorting;

import java.util.ArrayList;
import java.util.NoSuchElementException;

// Source : https://www.youtube.com/watch?v=W81Qzuz4qH0
// https://www.dropbox.com/s/mpbp6kv0qgg43xp/Heap.java
// https://www.dropbox.com/s/0n0nop2hn28my0j/HeapApp.java

// Max Heap
public class Heap2<T extends Comparable<T>> {

	private ArrayList<T> items;

	public Heap2() {
		items = new ArrayList<T>();
	}

	public void insert(T item) {
		items.add(item);
		siftUp();
	}

	private void siftUp() {
		int k = items.size() - 1;
		while (k > 0) {
			int p = (k-1)/2;
			T item = items.get(k);
			T parent = items.get(p);
			if (item.compareTo(parent) > 0) {
				// swap
				items.set(k, parent);
				items.set(p, item);

				// move up one level
				k = p;
			} else {
				break;
			}
		}
	}

	public T delete() throws NoSuchElementException {
		if (items.size() == 0) {
			throw new NoSuchElementException();
		}
		if (items.size() == 1) {
			return items.remove(0);
		}
		T hold = items.get(0);
		items.set(0, items.remove(items.size()-1));
		siftDown();
		return hold;
	}
	private void siftDown() {
		int k = 0;
		int left = 2*k+1;
		while (left < items.size()) {
			int max=left, right=left+1;
			if (right < items.size()) { // there is a right child
				if (items.get(right).compareTo(items.get(left)) > 0) { // compare left and right
					max++;
				}
			}
			if (items.get(k).compareTo(items.get(max)) < 0) {
				// switch
				T temp = items.get(k);
				items.set(k, items.get(max));
				items.set(max, temp);
				k = max;
				left = 2*k+1;
			} else {
				break;
			}
		}
	}



	public int size() {
		return items.size();
	}

	public boolean isEmpty() {
		return items.isEmpty();

	}

	public String toString() {
		return items.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heap2<Integer> hp = new Heap2<Integer>();
		hp.insert(5);
		hp.insert(8);
		hp.insert(2);
		hp.insert(10);
		hp.insert(1);
		hp.insert(14);
		hp.insert(4);

		System.out.println(hp);
		while (!hp.isEmpty()) {
			int max = hp.delete();
			System.out.println(max + " " + hp);
		}
	}
}