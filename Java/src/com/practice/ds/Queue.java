package com.practice.ds;

import java.util.Arrays;
import java.util.NoSuchElementException;

// Bounded Queue, queue with array looks tough since when we deque , we need to reduce array size.
// ToDo

class QueueWithArray<T> {
	private int front;
	private int rear;
	int size;
	T[] queue;
	public QueueWithArray(int inSize) {
		size = inSize;
		queue = (T[]) new Object[size];
		front = -1;
		rear = -1;
	}

	public boolean isempty() {
		return (front == -1 && rear == -1);
	}

	public void enQueue(T value) {
		if (isempty()) {
			front++;
			rear++;
			queue[rear] = value;
			System.out.println("Enqueued "+value);
			return;
		} 
		if ((rear+1)%size==front) {
			System.out.println("Queue is full");

		} else {
			rear=(rear+1)%size;
			queue[rear] = value;
			System.out.println("Enqueued "+value);

		}
	}

	public T deQueue() {
		T value = null;
		if (isempty()) {
			throw new IllegalStateException("Queue is empty, cant dequeue");
		} else if (front == rear) {
			value = queue[front];
			front = -1;
			rear = -1;

		} else {
			value = queue[front];
			front=(front+1)%size;

		}
		return value;

	}

	@Override
	public String toString() {
		return "Queue [front=" + front + ", rear=" + rear + ", size=" + size
				+ ", queue=" + Arrays.toString(queue) + "]";
	}

}


public class Queue{

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
		QueueWithArray<Integer> arrayQueue = new QueueWithArray<Integer>(4); 
		arrayQueue.enQueue(new Integer(10));
		arrayQueue.enQueue(new Integer(20));
		arrayQueue.enQueue(new Integer(30));
		arrayQueue.enQueue(new Integer(40));
		arrayQueue.enQueue(new Integer(50));
		System.out.println("QueueWithArray : "+ arrayQueue.toString());
		Integer value = (Integer)arrayQueue.deQueue();
		System.out.println("Dequeued "+value);
		value = (Integer)arrayQueue.deQueue();
		System.out.println("Dequeued "+value);
		System.out.println("QueueWithArray : "+ arrayQueue.toString());
		arrayQueue.enQueue(new Integer(50));
		arrayQueue.enQueue(new Integer(60));
		System.out.println("QueueWithArray : "+ arrayQueue.toString());
		arrayQueue.enQueue(new Integer(70));
		System.out.println("QueueWithArray : "+ arrayQueue.toString());

		/*		QueueWithLinkedList linkedQueue = new QueueWithLinkedList(); 
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
		System.out.println("");*/
	}
}
