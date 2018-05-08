package com.practice.ds.tree;

public class ExpressionTree {

	static class node
	{
		String info;
		node left = null, right = null;
		node(String x)
		{
			info = x;
		}
	};

	// Utility function to return the integer value of a given String
	static int toInt(String s){
		int num = 0;
		for (int i=0; i<s.length();  i++)
			num = num*10 + Integer.parseInt(String.valueOf(s.charAt(i)));
		return num;
	}

	// This function receives a node of the syntax tree
	// and recursively evaluates it
	static int eval(node root){
		// empty tree
		if (root == null)return 0;

		// leaf node i.e, an integer
		if (root.left == null && root.right ==null)return toInt(root.info);

		// Evaluate left subtree
		int l_val = eval(root.left);

		// Evaluate right subtree
		int r_val = eval(root.right);

		// Check which operator to apply
		if (root.info=="+")	return l_val+r_val;
		if (root.info=="-")	return l_val-r_val;
		if (root.info=="*")	return l_val*r_val;
		return l_val/r_val;
	}

	//driver function to check the above program
	public static void main(String[] args) {
		// create a syntax tree
		node root = new node("+");
		root.left = new node("*");
		root.left.left = new node("5");
		root.left.right = new node("4");
		root.right = new node("-");
		root.right.left = new node("100");
		root.right.right = new node("20");
		System.out.println(eval(root));


		root = new node("+");
		root.left = new node("*");
		root.left.left = new node("5");
		root.left.right = new node("4");
		root.right = new node("-");
		root.right.left = new node("100");
		root.right.right = new node("/");
		root.right.right.left = new node("20");
		root.right.right.right = new node("2");

		System.out.println(eval(root));
	}

}
