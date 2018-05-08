package com.practice.ds;
import java.util.Stack;


public class InorderSuccessor {

	static class Node{
		int data;
		Node left;
		Node right;
		Node successor;
		Node(int data){
			left = null;
			right = null;
			successor = null;
			this.data = data;
		}
	}

	Node root;

	/*
	public static void inorder(Node root){
		if(root == null) return;
		inorder(root.left);
		System.out.println(root.data);
		inorder(root.right);
	}
	public static void inorderIter(Node root){
		Node temp = root;
		Stack<Node> s = new Stack<>();
		//s.push(temp);
		while(!s.empty() || temp!=null){
			if(temp!=null) {
				s.push(temp);
				//System.out.println("pushed "+temp.data);
				temp = temp.left;
			}
			else{
				temp = s.pop();
				System.out.println(temp.data);
				temp = temp.right;
			}
		}
	}
	 */
	
	public static int inorderSuccessor(Node root, int target){
		Node temp = root, suc = root;
		
		// 1st reach upto the key.
		while(temp.data!=target){
			if(temp.data > target){
				suc = temp; // keep not on the possible successor
				temp = temp.left;				
			}else if(temp.data < target)
				temp = temp.right;
			else break;
		}
		
		// 2nd if right tree is present, successor would be leftmost element in the right sub tree.
		if(temp.right!=null){
			temp = temp.right;
			while(temp.left !=null) temp = temp.left;
			suc = temp;
		}
		return suc.data;
	}
	
	public static int inorderPredecessor(Node root, int target){
		Node temp = root, pred = root;
		
		// 1st reach upto the key.
		while(temp.data!=target){
			if(temp.data > target){
				temp = temp.left;				
			}else if(temp.data < target){
				pred = temp; // keep not on the possible predecessor
				temp = temp.right;
			}
			else break;
		}
		
		// 2nd if left tree is present, predecessor would be rightmost element in the left sub tree.
		if(temp.left!=null){
			temp = temp.left;
			while(temp.right !=null) temp = temp.right;
			pred = temp;
		}
		return pred.data;
	}
	
	public static void main(String[] args) {
		InorderSuccessor in = new InorderSuccessor();
		in.root = new Node(10);
		in.root.left = new Node(7);
		in.root.left.left = new Node(3);
		in.root.left.right = new Node(8);
		in.root.right = new Node(15);
		in.root.right.left = new Node(12);
		in.root.right.right = new Node(16);
		//inorder(in.root);
		System.out.println(inorderSuccessor(in.root,7));
		System.out.println(inorderSuccessor(in.root,8));
		System.out.println(inorderSuccessor(in.root,10));
		System.out.println(inorderSuccessor(in.root,3));
		
		System.out.println(inorderPredecessor(in.root,7));
		System.out.println(inorderPredecessor(in.root,8));
		System.out.println(inorderPredecessor(in.root,10));
		System.out.println(inorderPredecessor(in.root,3));

	}
}
