package com.practice.ds;

public class LinkedList {

	public class Node {
		public Node next;
		Object data;
		// Node constructor
		public Node() {
			next = null;
		}
		public Node(Object dataValue) {
			next = null;
			data = dataValue;
		}
		public Node(Object dataValue, Node nextValue) {
			next = nextValue;
			data = dataValue;
		}
		public Object getData() {
			return data;
		}
		public void setData(Object dataValue) {
			data = dataValue;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node nextValue) {
			next = nextValue;
		}
	}

	// reference to the head node.
	public Node head;
	private int listCount;

	// LinkedList constructor
	public LinkedList() {
		listCount = 0;
	}

	public void add(Object data)
	// appends the specified element to the end of this list.
	{
		Node temp = new Node();
		temp.setData(data);
		if(head == null){
			head = temp; return;
		}
		Node current = head;
		// starting at the head node, crawl to the end of the list
		while (current.getNext() != null) {
			current = current.getNext();
		}
		// the last node's "next" reference set to our new node
		current.setNext(temp);
		listCount++;// increment the number of elements variable
	}

	public void add(Object data, int index)
	// inserts the specified element at the specified position in this list
	{
		Node temp = new Node(data);
		Node current = head;
		// crawl to the requested index or the last element in the list,
		// whichever comes first
		for (int i = 1; i < index && current.getNext() != null; i++) {
			current = current.getNext();
		}
		// set the new node's next-node reference to this node's next-node
		// reference
		temp.setNext(current.getNext());
		// now set this node's next-node reference to the new node
		current.setNext(temp);
		listCount++;// increment the number of elements variable
	}

	public Object get(int index)
	// returns the element at the specified position in this list.
	{
		// index must be 1 or higher
		if (index <= 0)
			return null;

		Node current = head.getNext();
		for (int i = 1; i < index; i++) {
			if (current.getNext() == null)
				return null;

			current = current.getNext();
		}
		return current.getData();
	}

	public boolean remove(int index)
	// removes the element at the specified position in this list.
	{
		// if the index is out of range, exit
		if (index < 1 || index > size())
			return false;

		Node current = head;
		for (int i = 1; i < index; i++) {
			if (current.getNext() == null)
				return false;

			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		listCount--; // decrement the number of elements variable
		return true;
	}

	public static Node reverseLL(Node currentHead)
	{
		// Basically we are iterating on old list and adding their node to the front of newList one by one
		if (currentHead == null) return null;
		if ( currentHead.next == null) return currentHead;  //empty or just one node in list
		Node newList = currentHead;
		Node currentNode = currentHead.next;
		Node currentNext = currentNode.next;
		newList.next = null;
		while( currentNext!=null){
			currentNode.next = newList;
			newList = currentNode;
			currentNode = currentNext;
			currentNext = currentNext.next;
		}
		currentNode.next = newList;
		currentHead = currentNode;
		return currentHead;
	}

	public static Node reverseKNodes(Node headerNode, int k)
	{
		// Take 3 pointers startNode, endNode, nextNode pointing to headerNode
		Node nextNode = headerNode;
		Node startNode = null;
		Node endNode = null;
		headerNode = null;
		Node newStartNode = null;
		Node tempNode = null;
		while (true)
		{
			//  startNode and endNode points to nextNode
			startNode = nextNode;
			endNode = nextNode;
			//  Move endNode pointing towards node after k elements from startNode
			for (int i = 1; i < k; i++)
			{
				endNode = endNode.next;
				if (endNode == null)break;
			}
			// If endNode is not null, then reverse the list starting from startNode to endNode
			// eles if endNode is null, then there is nothing to reverse
			if (endNode != null)
			{
				// Save the node next to endNode
				nextNode = endNode.next;
				//  Unlink the endNode
				endNode.next = null;
				// Reverse the list starting from startNode
				newStartNode = reverseLL(startNode); // though the list reverses, start and end points to the
				// same elements as they were pointing to earlier.
			}
			else
			{
				tempNode.next = startNode;
				break;
			}
			//  Point headerNode to the startNode of the first iteration.
			//  If the headerNode is set, append the list startNode to the headerNode
			if (headerNode == null)
			{
				headerNode = newStartNode;
			}
			else
			{
				// link end of nth list to the start of n+1th list
				tempNode.next = endNode;
			}
			// store end of nth k size list
			tempNode = startNode;
		}
		return headerNode;
	}

	public static Node mergeList(Node t1, Node t2){
		if(t1 == null && t2 == null)return null;
		if(t1 == null && t2 != null)return t2;
		if(t1 != null && t2 == null)return t1;
		Node head = t1;
		Node t11 = null;
		Node t22 = null;
		while(true){
			t11 = t1.next;
			t22 = t2.next;
			if(t1.next != null)	t2.next = t1.next;
			t1.next = t2;
			if(t11 == null)	break;
			if(t22 == null)	break;
			t1 = t11;
			t2 = t22;
		}
		return head;
	}

	public void reverseLLUsingRecursion2(Node currentNode){
		//check for empty list 
		if(currentNode == null)  return;
		if(currentNode.next == null) 
		{ 
			head = currentNode; 
			return; 
		}
		reverseLLUsingRecursion2(currentNode.next);
		currentNode.next.next = currentNode;
		currentNode.next = null;
	}

	public  void reverseLLUsingRecursion3(Node current , Node prev){
		if (current.next == null) { 
			head = current;
			head.next = prev; 
			return;
		}
		reverseLLUsingRecursion3(current.next, current);
		current.next = prev;
	}

	public void moveLastToFront(){
		Node current = head;
		if(current.next == null) return;
		while(current.next.next != null) 
		{
			current = current.next;
		}
		current.next.next = head;
		head = current.next;
		current.next = null;	
	}



	public int size()
	{
		return listCount;
	}

	public String toString() {
		Node current = head;
		String output = "";
		while (current != null) {
			output += "[" + current.getData().toString() + "]";
			current = current.getNext();
		}
		return output;
	}

	public static void printList(Node head){
		Node current = head;
		String output = "";
		while (current != null) {
			output += "[" + current.getData().toString() + "]";
			current = current.getNext();
		}
		System.out.println(output);
	}

	static class TNode{
		TNode left;
		TNode right;
		String data;
		public TNode() {
		}
	}

	static Node temp =null;
	static TNode listToBSTBottomUp2(int start, int end){
		if(start > end) return null;   
		// get mid val
		int mid = (start + end) / 2;    
		// build left sub tree
		TNode left = listToBSTBottomUp2(start, mid - 1);
		// build root node
		TNode root = new TNode();
		root.data = (String)temp.data;
		root.left = left;
		// move to next node to build right sub tree
		temp = temp.next;
		root.right = listToBSTBottomUp2(mid + 1, end);	         
		return root;	
	}
	
	static TNode listToBSTBottomUp(int n){
		if(n <= 0) return null;   
		// build left sub tree
		TNode left = listToBSTBottomUp(n/2);
		// build root node
		TNode root = new TNode();
		root.data = (String)temp.data;
		root.left = left;
		// move to next node to build right sub tree
		temp = temp.next;
		root.right = listToBSTBottomUp(n-n/2-1);	         
		return root;	
	}
	
	static TNode  listToBSTTopDown(int start,int end)
	{
	    if (start > end)
	        return null;
	    int mid = start+(end-start)/2;
	    Node tempNode = temp;
	    int i = 0;
	    while(i<mid && temp.next!=null)
	    {
	    	tempNode = tempNode.next;
	        i++;
	    }
	    TNode root = new TNode();
	    root.data = (String)tempNode.data;
	    root.left = listToBSTTopDown(start, mid-1);
	    root.right = listToBSTTopDown(mid+1, end);
	    return root;
	}

	
	public static void inorderTraversal(TNode root){
		if(root == null)return;
		inorderTraversal(root.left);
		System.out.print(root.data + ",");
		inorderTraversal(root.right);
	}

	public static void main(String[] args) {
		LinkedList lList = new LinkedList();
		// add elements to LinkedList
		lList.add("1");
		lList.add("3");
		lList.add("5");
		lList.add("7");
		lList.add("9");
		lList.add("11");
		lList.add("13");

		temp = lList.head;
		TNode t = listToBSTTopDown(0,6);
		//TNode t = listToBSTBottomUp(7);
		System.out.println("Starting Inorder traversal");
		inorderTraversal(t);
		System.out.println("\nDone with Inorder traversal");
		LinkedList lList2 = new LinkedList();
		// add elements to LinkedList
		lList2.add("2");
		lList2.add("4");
		lList2.add("6");
		lList2.add("8");

		/*
		 * Please note that primitive values can not be added into LinkedList
		 * directly. They must be converted to their corresponding wrapper
		 * class.
		 */

		System.out.println("lList - print linkedlist: " + lList);		
		System.out.println("lList - print linkedlist: " + lList2);

		/*		System.out.println("lList.size() - print linkedlist size: " + lList.size());
		System.out.println("lList.get(3) - get 3rd element: " + lList.get(3));
		System.out.println("lList.remove(2) - remove 2nd element: " + lList.remove(2));
		System.out.println("lList.get(3) - get 3rd element: " + lList.get(3));
		System.out.println("lList.size() - print linkedlist size: " + lList.size());
		System.out.println("lList - print linkedlist: " + lList);*/
		//lList.reverseLLUsingRecursion(lList.head);
		Node temp = lList.head;
		//reverseLLUsingRecursion2(temp);
		//Node t = reverseLL(temp);
		//Node t = reverseKNodes(temp, 3);		
		//printList(t);

		printList(mergeList(lList.head,lList2.head));


		//System.out.println("new lList - print linkedlist: " + lList);

	}

}