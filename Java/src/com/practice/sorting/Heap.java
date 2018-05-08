package com.practice.sorting;

import java.util.Arrays;


// Min heap
public class Heap {
	int []harr;
	int capacity;
	int heap_size; 
	// Constructor
	Heap(int cap){
		heap_size = 0;
		capacity = cap;
		harr = new int[cap];
	}

	// to heapify a subtree with root at given index
	void minHeapify(int i ){
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < heap_size && harr[l] < harr[i])
			smallest = l;
		if (r < heap_size && harr[r] < harr[smallest])
			smallest = r;
		if (smallest != i)
		{
			swap(i, smallest);
			minHeapify(smallest);
		}
	}

	int parent(int i) { return (i-1)/2; }
	int left(int i) { return (2*i + 1); }
	int right(int i) { return (2*i + 2); }

	// to extract the root which is the minimum element
	int extractMin(){
		if (heap_size <= 0)
			return Integer.MAX_VALUE;
		if (heap_size == 1)
		{
			heap_size--;
			return harr[0];
		}

		// Store the minimum value, and remove it from heap
		int root = harr[0];
		harr[0] = harr[heap_size-1];
		heap_size--;
		minHeapify(0);

		return root;
	}

	// Decreases key value of key at index i to new_val
	void decreaseKey(int i, int new_val){
		harr[i] = new_val;
		while (i != 0 && harr[parent(i)] > harr[i])
		{
			swap(i, parent(i));
			i = parent(i);
		}
	}

	// Returns the minimum key (key at root) from min heap
	int getMin() { return harr[0]; }

	// Deletes a key stored at index i
	void deleteKey(int i){

		decreaseKey(i, Integer.MIN_VALUE);
		extractMin();
	}

	// Inserts a new key 'k'
	void insertKey(int k){
		if (heap_size == capacity)
		{
			System.out.println("\nOverflow: Could not insertKey\n");
			return;
		}

		// First insert the new key at the end
		heap_size++;
		int i = heap_size - 1;
		harr[i] = k;

		// Fix the min heap property if it is violated
		while (i != 0 && harr[parent(i)] > harr[i])
		{
			swap(i,parent(i));
			i = parent(i);
		}
	}

	void swap(int x, int y){

		int temp1 = harr[x];
		int temp2 = harr[y];
		harr[x] = temp2;
		harr[y] = temp1;
	}

	public String toString() {
		return Arrays.toString(harr);
	}

	public static void main(String args[]){

		Heap h = new Heap(11);
		h.insertKey(3);
		h.insertKey(2);
		h.deleteKey(1);
		h.insertKey(15);
		h.insertKey(5);
		h.insertKey(4);
		h.insertKey(45);
		System.out.println(h);
		System.out.println(h.extractMin());
		System.out.println(h.getMin());
		h.decreaseKey(2, 1);
		System.out.println(h.getMin());
	}
}
