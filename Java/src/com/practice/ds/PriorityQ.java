package com.practice.ds;


class PriorityQ {
	// array in sorted order, from max at 0 to min at size-1
	private int maxSize;

	private long[] queArray;

	private int nItems;

	public PriorityQ(int s) {
		maxSize = s;
		queArray = new long[maxSize];
		nItems = 0;
	}

	public void insert(long item) {
		int i;
		if (nItems == 0)	queArray[nItems++] = item; // insert at 0
		else{
			for (i = nItems - 1; i >= 0; i--){ // start at end,
				if (item > queArray[i]) // if new item larger,
					queArray[i + 1] = queArray[i]; // shift upward
				else
					// if smaller,
					break; // done shifting
			}
			queArray[i + 1] = item; // insert it
			nItems++;
		} // end else (nItems > 0)
	}

	public long remove(){
		return queArray[--nItems];
	}

	public long peekMin(){
		return queArray[nItems - 1];
	}

	public boolean isEmpty(){
		return (nItems == 0);
	}

	public boolean isFull(){
		return (nItems == maxSize);
	}

	public String toString(){
		String queue = "";
		int i = nItems-1;
		while (i != -1) {
			long item = queArray[i];
			queue = queue + " " + item;
			i--;
		}
		return queue;
	}
	public static void main(String[] args) {
		PriorityQ thePQ = new PriorityQ(5);
		thePQ.insert(30);
		System.out.println(thePQ.toString());
		thePQ.insert(50);
		System.out.println(thePQ.toString());
		thePQ.insert(10);
		System.out.println(thePQ.toString());
		thePQ.insert(40);
		System.out.println(thePQ.toString());
		thePQ.insert(20);
		System.out.println(thePQ.toString());
		
	}
}