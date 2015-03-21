package com.practice.ds;

class Node {
	public int data;
	public Node next;
	public Node previous;
	public Node(int id) {
		data = id;
		next = null;
		previous = null;
	}
	public String toString() {
		return "{" + data + "} ";
	}
}

public class DoublyLinkedList {
	private Node first;
	private Node last;

	public DoublyLinkedList() {
		first = null;
		last = null;
	}
	public boolean isEmpty() {
		return first == null;
	}
	public void insertFirst(int element) { 
		Node newNode = new Node(element);
		if (isEmpty()){
			last = newNode;
		}else{
			first.previous = newNode;
		}
		newNode.next = first;
		first = newNode;
	}

	public void insertLast(int element) { 
		Node newNode = new Node(element);
		if (isEmpty()){
			first = newNode;
		}else {
			last.next = newNode;
			newNode.previous = last;
		}
		last = newNode;
	}

	public void deleteFirst() { 
		if (first.next == null){
			last = null;
		}else{
			first.next.previous = null;
		}
		first = first.next;
	}

	public void deleteLast() { 	
	 	if (last.previous == null){
			first = null;
		}else{
			last.previous.next = null;
		}
		last = last.previous;	
	}

	public boolean insertAfter(int key, int element) { 
		Node current = first;
		while (current.data != key) {
			current = current.next;
			if (current == null){
				return false;
			}
		}
		Node newNode = new Node(element);

		if (current == last) {
			newNode.next = null;
			last = newNode;
		} else {
			newNode.next = current.next;
			current.next.previous = newNode;
		}
		newNode.previous = current;
		current.next = newNode;
		return true;
	}

	public void deleteKey(int key) {
		Node current = first;
		while (current.data != key) {
			current = current.next;
			if (current == null)
				return ;
		}
		if (current == first){
			first = current.next;
		}else{
			current.previous.next = current.next;
		}

		if (current == last){
			last = current.previous;
		}else{
			current.next.previous = current.previous;
		}	
	}

	public String toString() { 
		String str = "List (first-->last): ";
		Node current = first;
		while (current != null) {
			str += current.toString();
			current = current.next;
		}
		System.out.println(str);
		str = "";
		System.out.print("List (last-->first): ");

		current = last;
		while (current != null) {
			str += current.toString();
			current = current.previous;
		}
		return str;
	}


	public static void main(String[] args) {
		DoublyLinkedList theList = new DoublyLinkedList();

		theList.insertFirst(22);
		System.out.println(theList);
		System.out.println();
		
		theList.insertFirst(44);
		theList.insertFirst(66);

		theList.insertLast(11);
		theList.insertLast(33);
		theList.insertLast(55);
		System.out.println(theList);
		System.out.println();

		theList.deleteFirst();
		theList.deleteLast();
		theList.deleteKey(11);

		System.out.println(theList);
		System.out.println();

		theList.insertAfter(22, 77);
		theList.insertAfter(33, 88);

		System.out.println(theList);
	}
}

