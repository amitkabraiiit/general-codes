package com.practice.ds;

import java.util.NoSuchElementException;

public class Stack{

	/*
	 * In array implementation we maintain one top pointer.
	 * Its a bounded stack
	 */
	static class StackWithArray {
		private int maxSize;
		private Object[] stackArray;
		private int top;
		public StackWithArray(int s) {
			maxSize = s;
			stackArray = new Object[maxSize];
			top = -1;
		}
		public void push(Object item) {
			stackArray[++top] = item;
		}
		public Object pop() {
			return stackArray[top--];
		}
		public Object peek() {
			return stackArray[top];
		}
		public boolean isEmpty() {
			return (top == -1);
		}
		public boolean isFull() {
			return (top == maxSize - 1);
		}
		public int size() {
			return top +1;
		}
		public String toString() {
			StringBuilder s = new StringBuilder();
			for (int i=0; i<top+1; i++)
				s.append(stackArray[i] + " ");
			return s.toString();
		}
	}

	/*
	 * In array implementation we maintain one top pointer.
	 * Its a bounded stack
	 */
	static class TwoStack {
		private int maxSize;
		private Object[] stackArray;
		private int top;
		private int end;
		private int stackNumber ;
		public TwoStack(int s) {
			maxSize = s;
			stackArray = new Object[maxSize];
			top = -1;
			end = s;
			for(int k=0;k<maxSize;k++)stackArray[k]=-1;
		}
		public void push(int stackNumber ,Object item) {
			if(stackNumber == 1){
				if (!stackArray[top+1].equals(-1)){
					stackArray[++top] = item;	
				}else{
					System.out.println("Stack1 is full");
				}
			return;	
			}
			if(stackNumber == 2){
				if (!stackArray[end-1].equals(-1)){
					stackArray[--top] = item;	
				}else{
					System.out.println("Stack1 is full");
				}
			return;	
			}

		}
		public Object pop(int stackNumber ) {
			if(stackNumber == 1){
				return stackArray[top--];			}
			else{
				return stackArray[end++];			}

		}
		public Object peek(int stackNumber ) {
			if(stackNumber == 1){
				return stackArray[top];
			}
			else{
				return stackArray[end];
			}
		}
		public boolean isEmpty(int stackNumber) {
			if(stackNumber == 1){
				return (top == -1);
			}
			else{
				return (end == maxSize);
			}
		}
		public boolean isFull(int stackNumber ) {
			if(stackNumber == 1){
				return (top == maxSize - 1);
			}
			else{
				return (top == 0);
			}
		}
		public int size(int stackNumber ) {
			if(stackNumber == 1){
				return top +1;
			}
			else{
				return maxSize - end;
			}
		}
		public String toString(int stackNumber ) {
			if(stackNumber == 1){
				StringBuilder s = new StringBuilder();
				for (int i=0; i<top+1; i++)
					s.append(stackArray[i] + " ");
				return s.toString();			}
			else{
				StringBuilder s = new StringBuilder();
				for (int i=maxSize-1; i<end+1; i--)
					s.append(stackArray[i] + " ");
				return s.toString();			}

		}
	}



	/*
	 * In array implementation we maintain one top pointer.
	 * Its a unbounded stack
	 */
	static class StackWithLinkedList {
		private class Node {
			public Object data;
			public Node next;
		}

		private Node top ;
		private int N;
		public StackWithLinkedList(){
			top = null;
			N = 0;
		}
		public void push(Object item) {
			Node newNode = new Node();
			newNode.data = item;
			newNode.next = top;
			top = newNode;
			N++;
		}
		public Object pop() {
			Object item = peek();
			top = top.next;
			N--;
			return item;
		}
		public boolean isEmpty() {
			return top == null;
		}
		public Object peek() {
			if (top == null) {
				throw new NoSuchElementException();
			}
			return top.data;
		}
		public int size() {
			return N+1;
		}
		public String toString() {
			StringBuilder s = new StringBuilder();
			for (Node n = top; n != null; n = n.next)
				s.append(n.data + " ");
			return s.toString();
		}

	}
	public static void main(String[] args) {
		StackWithArray arrayStack = new StackWithArray(10); 
		arrayStack.push(new Integer(10));
		arrayStack.push(new Integer(20));
		arrayStack.push(new Integer(30));
		arrayStack.push(new Integer(40));
		arrayStack.push(new Integer(50));
		System.out.println("StackWithArray : "+ arrayStack.toString());
		while (!arrayStack.isEmpty()) {
			Integer value = (Integer)arrayStack.pop();
			System.out.print(value);
			System.out.print(" ");
		}
		System.out.println("");

		StackWithLinkedList linkedStack = new StackWithLinkedList(); 
		linkedStack.push(new Integer(10));
		linkedStack.push(new Integer(20));
		linkedStack.push(new Integer(30));
		linkedStack.push(new Integer(40));
		linkedStack.push(new Integer(50));
		System.out.println("StackWithLinkedList : "+ linkedStack.toString());
		while (!linkedStack.isEmpty()) {
			Integer value = (Integer)linkedStack.pop();
			System.out.print(value);
			System.out.print(" ");
		}
		System.out.println("");

		TwoStack ts = new TwoStack(10);
		ts.push(1,new Integer(10));
		ts.push(2,new Integer(10));		
		ts.push(1,new Integer(20));
		ts.push(2,new Integer(20));	
		ts.push(1,new Integer(30));
		ts.push(2,new Integer(30));
		ts.push(1,new Integer(40));		
		ts.push(2,new Integer(40));
		ts.push(1,new Integer(50));	
		ts.push(2,new Integer(50));
	}
}
