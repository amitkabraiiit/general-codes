package com.practice.ds;

import java.util.NoSuchElementException;



public class Queue{

	// Bounded Queue, queue with array looks tough since when we deque , we need to reduce array size.
	// ToDo
	static class QueueWithArray {
		private int maxSize;
		private Object[] QueueArray;
		private int first, last;

		public QueueWithArray(int s) {
			maxSize = s;
			QueueArray = new Object[maxSize];
			first = 0 ; last = -1 ;
		}
		public void enqueue(Object item) {
			if(last < maxSize -1){
				QueueArray[++last] = item;
			}else{
				System.out.println("Stack overflow");
			}
		}
		public Object dequeue() {
			if(isEmpty())throw new NoSuchElementException("Queue underflow");
			return QueueArray[first];
		}
		public Object peek() {
			return QueueArray[first];
		}
		public boolean isEmpty() {
			return (last == -1);
		}
		public boolean isFull() {
			return (last == maxSize - 1);
		}
		public int size() {
			return last +1;
		}
	    public String toString() {
	        StringBuilder s = new StringBuilder();
	        for (int i =0 ; i<=last; i++)
	            s.append(QueueArray[i] + " ");
	        return s.toString();
	    } 
	}
	
	/*
	 * In this queue implementation, we maintain first pointer , last pointer and count variable.
	 */
	
	static class QueueWithLinkedList {
		
		private class Node {
			public Object data;
			public Node next;
		}	
		
		private Node first;
		private Node last;
		private int N;	
		
		public QueueWithLinkedList(){
			first = null;
			last = null;
			N = 0 ;
		}
		public void enqueue(Object item) {
			Node newNode = new Node();
			newNode.data = item;
			newNode.next = null;
			if (first==null){
				first = newNode;
				last = first;
			}else{
				last.next = newNode;	
				last = newNode;
			}
			N++;
		}
		public Object dequeue() {
			Object item = peek();
			first = first.next;
			N--;
			return item;
		}
		public boolean isEmpty() {
			return first == null;
		}
		public Object peek() {
			if (first == null) {
				throw new NoSuchElementException();
			}
			return first.data;
		}
		public int size() {
			return N;
		}
	    public String toString() {
	        StringBuilder s = new StringBuilder();
	        for (Node n = first; n != null; n = n.next)
	            s.append(n.data + " ");
	        return s.toString();
	    }
	}
	public static void main(String[] args) {
		QueueWithArray arrayQueue = new QueueWithArray(10); 
		arrayQueue.enqueue(new Integer(10));
		arrayQueue.enqueue(new Integer(20));
		arrayQueue.enqueue(new Integer(30));
		arrayQueue.enqueue(new Integer(40));
		arrayQueue.enqueue(new Integer(50));
		System.out.println("QueueWithArray : "+ arrayQueue.toString());
		
		while (!arrayQueue.isEmpty()) {
			Integer value = (Integer)arrayQueue.dequeue();
			System.out.print(value);
			System.out.print(" ");
		}
		System.out.println("");
		
		QueueWithLinkedList linkedQueue = new QueueWithLinkedList(); 
		linkedQueue.enqueue(new Integer(10));
		linkedQueue.enqueue(new Integer(20));
		linkedQueue.enqueue(new Integer(30));
		linkedQueue.enqueue(new Integer(40));
		linkedQueue.enqueue(new Integer(50));
		System.out.println("QueueWithLinkedList : "+ linkedQueue.toString());
		while (!linkedQueue.isEmpty()) {
			Integer value = (Integer)linkedQueue.dequeue();
			System.out.print(value);
			System.out.print(" ");
		}
		System.out.println("");

		
	}
}
