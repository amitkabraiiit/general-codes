package com.practice.ds.tree;

public class BSTToDLL {

	static class Node
	{
		int data;
		Node left, right;
	};

	// A utility function to create a new tree Node
	static Node newNode(int data)
	{
		Node temp = new Node();
		temp.data = data;
		temp.left = temp.right = null;
		return temp;
	}

	// Standard Inorder traversal of tree
	static void inorder( Node root)
	{
		if (root != null)
		{
			inorder(root.left);
			System.out.println(" "+root.data);
			inorder(root.right);
		}
	}

	// Changes left pointers to work as previous pointers in converted DLL
	// The function simply does inorder traversal of Binary Tree and updates
	// left pointer using previously visited Node
	static void fixPrevPtr(Node root)
	{
		Node pre = null;

		if (root != null)
		{
			fixPrevPtr(root.left);
			root.left = pre;
			pre = root;
			fixPrevPtr(root.right);
		}
	}

	// Changes right pointers to work as next pointers in converted DLL
	static Node fixNextPtr(Node root)
	{
		Node prev = null;

		// Find the right most Node in BT or last Node in DLL
		while (root != null && root.right != null)root = root.right;

		// Start from the rightmost Node, traverse back using left pointers.
		// While traversing, change right pointer of Nodes.
		while (root != null && root.left != null)
		{
			prev = root;
			root = root.left;
			root.right = prev;
		}
		// The leftmost Node is head of linked list, return it
		return root;
	}

	// The main function that converts BST to DLL and returns head of DLL
	static Node BTToDLLConversion(Node root)
	{
		// Set the previous pointer
		fixPrevPtr(root);

		// Set the next pointer and return head of DLL
		return fixNextPtr(root);
	}

	// Traverses the DLL from left tor right
	static void printList(Node root)
	{
		while (root != null)
		{
			System.out.println(" "+ root.data);
			root = root.right;
		}
	}

	public static void main(String[] args) {

		// Let us create the tree shown in above diagram
		Node root = newNode(10);
		root.left        = newNode(12);
		root.right       = newNode(15);
		root.left.left  = newNode(25);
		root.left.right = newNode(30);
		root.right.left = newNode(36);

		System.out.println("Inorder Tree Traversal");
		BSTToDLL.inorder(root);

		Node head = BTToDLLConversion(root);

		System.out.println("DLL Traversal");
		printList(head);
	}

}
