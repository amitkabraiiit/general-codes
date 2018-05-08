package com.practice.ds;

import java.util.ArrayList;

public class SerializeDeserializeBinaryTree {

	static class TreeNode{
		int data;
		TreeNode left;
		TreeNode right;
		TreeNode(int data){
			this.data = data;
			right = null;
			left = null;
		}
	}

	static ArrayList<String> list = new ArrayList<String>();
	static int ind = 0;

	// Encodes a tree to a single list.
	public static String serialize(TreeNode root) {

		if(root == null){
			list.add("#");
			return "";
		}

		list.add(root.data+"");
		serialize(root.left);
		serialize(root.right);

		return "";

	}

	// Decodes your encoded list to tree.
	public static TreeNode deserialize(String data) {

		if(ind >= list.size() || list.get(ind).equals("#")){
			ind++;
			return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(list.get(ind)));
		ind++;
		root.left = deserialize(data);
		root.right = deserialize(data);
		return root;
	}

	public static void main(String[] args) {

		TreeNode node = new TreeNode(1);
		//Here I store all the elements in a Array list where each null child is stored as #.
		TreeNode ret = deserialize(serialize(node));
	}
}
