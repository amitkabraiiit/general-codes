package com.practice.ds;

import java.util.ArrayList;
import java.util.List;
 
public class InorderPreorder
{
    public InorderPreorder()
    {
    }
    
    class Node{
    	int data;
    	Node left;
    	Node right;
    }
 
    public Node constructBinaryTreePreIn(List<Integer> preOrder, List<Integer> inOrder)
    {
    	/*
        Inorder  : 8,4,10,9,11,2,5,1,6,3,7
        Preorder : 1,2,4,8,9,10,11,5,3,6,7
        
        Constructs following tree
                        1
                 2             3
            4        5      6     7
         8     9
            10   11
         */   
    	
        Node node = null;
        List<Integer> leftPreOrder;
        List<Integer> rightPreOrder;
        List<Integer> leftInorder;
        List<Integer> rightInorder;
        int inorderPos;
        int preorderPos;
 
        if ((preOrder.size() != 0) && (inOrder.size() != 0))
        {
            //  Assign the first element of preorder traversal as root
            node = new Node();
            node.data = ((Integer) preOrder.get(0)).intValue();
            inorderPos = inOrder.indexOf(preOrder.get(0));
            leftInorder = inOrder.subList(0, inorderPos);
            rightInorder = inOrder.subList(inorderPos + 1, inOrder.size());
 
            preorderPos = leftInorder.size();
            leftPreOrder = preOrder.subList(1, preorderPos + 1);
            rightPreOrder = preOrder.subList(preorderPos + 1, preOrder.size());
 
            node.left = constructBinaryTreePreIn(leftPreOrder, leftInorder);
            node.right = constructBinaryTreePreIn(rightPreOrder, rightInorder);
        }
        return node;
    }
    
    public Node constructBinaryTreePostInI(List<Integer> postOrder, List<Integer> inOrder)
    {
        Node node = null;
        List<Integer> leftPostOrder;
        List<Integer> rightPostOrder;
        List<Integer> leftInorder;
        List<Integer> rightInorder;
        int inorderPos;
        int postorderPos;
 
    	/*
        Inorder  : 8,4,10,9,11,2,5,1,6,3,7
        Postorder: 8,10,11,9,4,5,2,6,7,3,1
        
        Constructs following tree
                        1
                 2             3
            4        5      6     7
         8     9
            10   11
         */   
        
        if ((postOrder.size() != 0) && (inOrder.size() != 0))
        {
            //  Assign the first element of postorder traversal as root
            node = new Node();
            node.data = ((Integer) postOrder.get(postOrder.size()-1)).intValue();
            inorderPos = inOrder.indexOf(node.data);
            leftInorder = inOrder.subList(0, inorderPos);
            rightInorder = inOrder.subList(inorderPos + 1, inOrder.size());
 
            postorderPos = leftInorder.size();
            leftPostOrder = postOrder.subList(0, postorderPos );
            rightPostOrder = postOrder.subList(postorderPos, postOrder.size()-1);
 
            node.left = constructBinaryTreePostInI(leftPostOrder, leftInorder);
            node.right = constructBinaryTreePostInI(rightPostOrder, rightInorder);
        }
 
        return node;
    }
    
    
    Node constructBinaryTreeLevelIn(Node node, int[] levelOrder, int[] inOrder, int inStart, int inEnd) 
    {
        // if start index is more than end index
        if (inStart > inEnd) return null;
        if (inStart == inEnd){
        	Node n = new Node();
        	n.data = inOrder[inStart];
        	return n;
        }
        boolean found = false;
        int index = 0;
  
        // it represents the index in inOrder array of element that
        // appear first in levelOrder array.
        for (int i = 0; i < levelOrder.length - 1; i++) {
            int data = levelOrder[i];
            for (int j = inStart; j < inEnd; j++) {
                if (data == inOrder[j]){
                    node = new Node();
                    node.data = data;
                    index = j;
                    found = true;
                    break;
                }
            }
            if (found == true)  break;
        }
  
        //elements present before index are part of left child of node.
        //elements present after index are part of right child of node.
        node.left = constructBinaryTreeLevelIn(node, levelOrder, inOrder, inStart, index - 1);
        node.right = constructBinaryTreeLevelIn(node, levelOrder, inOrder,index + 1, inEnd);
  
        return node;
    }
    
    public Node constructBinaryTreePost(List<Integer> postOrder)
    {
        Node node = null;
        List<Integer> leftPostOrder;
        List<Integer> rightPostOrder;
        int postorderPos=0;
 
    	/*
        Postorder: 10,32, 25, 78,40
        
        Constructs following tree
                        40
                 25            78
            10        32
         */  
        
        if ((postOrder.size() != 0))
        {
            //  Assign the first element of postorder traversal as root
            node = new Node();
            node.data = ((Integer) postOrder.get(postOrder.size()-1)).intValue();
            while( postOrder.get(postorderPos) < node.data ) postorderPos++;
            leftPostOrder = postOrder.subList(0, postorderPos );
            rightPostOrder = postOrder.subList(postorderPos, postOrder.size()-1);
 
            node.left = constructBinaryTreePost(leftPostOrder);
            node.right = constructBinaryTreePost(rightPostOrder);
        }
 
        return node;
    }
    
    public Node constructBinaryTreePre(List<Integer> preOrder)
    {
        Node node = null;
        List<Integer> leftPreOrder = null;
        List<Integer> rightPreOrder = null;
        int preorderPos=1;
 
    	/*
        Preorder: 40,25, 10,32,78
        Constructs following tree
                        40
                 25            78
            10        32
         */  
        
        if ((preOrder.size() != 0))
        {
            //  Assign the first element of preorder traversal as root
            node = new Node();
            node.data = (Integer) preOrder.get(0).intValue();
            while(preorderPos < preOrder.size() && preOrder.get(preorderPos)<node.data){
            	System.out.println(preorderPos);
            	preorderPos++;
            }
            
            leftPreOrder = preOrder.subList(1, preorderPos );
            rightPreOrder = preOrder.subList(preorderPos, preOrder.size());
            node.left = constructBinaryTreePre(leftPreOrder);
            node.right = constructBinaryTreePre(rightPreOrder);
        }
 
        return node;
    }
    
	public static void inorderTraversal(Node root){
		if(root == null)return;
		inorderTraversal(root.left);
		System.out.print(root.data + ",");
		inorderTraversal(root.right);
	}

	public static void preorderTraversal(Node root){
		if(root == null)return;
		System.out.print( root.data + ",");
		preorderTraversal(root.left);
		preorderTraversal(root.right);
	}

	public static void postorderTraversal(Node root){
		if(root == null)return;
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		System.out.print(root.data + ",");
	}
 
    public static void main(String[] args)
    {
        ArrayList<Integer> preOrder = new ArrayList<Integer>();
        preOrder.add(new Integer(1));
        preOrder.add(new Integer(2));
        preOrder.add(new Integer(4));
        preOrder.add(new Integer(8));
        preOrder.add(new Integer(9));
        preOrder.add(new Integer(10));
        preOrder.add(new Integer(11));
        preOrder.add(new Integer(5));
        preOrder.add(new Integer(3));
        preOrder.add(new Integer(6));
        preOrder.add(new Integer(7));
 
        ArrayList<Integer> inOrder = new ArrayList<Integer>();
        inOrder.add(new Integer(8));
        inOrder.add(new Integer(4));
        inOrder.add(new Integer(10));
        inOrder.add(new Integer(9));
        inOrder.add(new Integer(11));
        inOrder.add(new Integer(2));
        inOrder.add(new Integer(5));
        inOrder.add(new Integer(1));
        inOrder.add(new Integer(6));
        inOrder.add(new Integer(3));
        inOrder.add(new Integer(7));
 
        ArrayList<Integer> postOrder = new ArrayList<Integer>();
        postOrder.add(new Integer(8));
        postOrder.add(new Integer(10));
        postOrder.add(new Integer(11));
        postOrder.add(new Integer(9));
        postOrder.add(new Integer(4));
        postOrder.add(new Integer(5));
        postOrder.add(new Integer(2));
        postOrder.add(new Integer(6));
        postOrder.add(new Integer(7));
        postOrder.add(new Integer(3));
        postOrder.add(new Integer(1));
 
        ArrayList<Integer> postOrderBST = new ArrayList<Integer>();
        postOrderBST.add(new Integer(7));
        postOrderBST.add(new Integer(5));
        postOrderBST.add(new Integer(10));
        postOrderBST.add(new Integer(9));
        postOrderBST.add(new Integer(8)); // its inorder should be 5,7,8,9,10

        ArrayList<Integer> preOrderBST = new ArrayList<Integer>();
        preOrderBST.add(new Integer(8));
        preOrderBST.add(new Integer(5));
        preOrderBST.add(new Integer(7));
        preOrderBST.add(new Integer(9));
        preOrderBST.add(new Integer(10)); // its inorder should be 5,7,8,9,10
        
        /*
        Constructs following tree
                        1
                 2             3
            4        5      6     7
         8     9
            10   11
         */
        InorderPreorder inPreTree = new InorderPreorder();
       // Node node = inPreTree.constructBinaryTreePreIn(preOrder, inOrder);
       // Node node = inPreTree.constructBinaryTreePostInI(postOrder, inOrder);
       // Node node = inPreTree.constructBinaryTreePost(postOrderBST);
         Node node = inPreTree.constructBinaryTreePre(preOrderBST);

        
        /*
        Inorder  : 8,4,10,9,11,2,5,1,6,3,7
        Preorder : 1,2,4,8,9,10,11,5,3,6,7
        Postorder: 8,10,11,9,4,5,2,6,7,3,1
        */
        
        
        System.out.println("\n Inorder Traversal of constructed tree is ");
        inorderTraversal(node);
        System.out.println();
      //  preorderTraversal(node);
      //  System.out.println();
      //  postorderTraversal(node);

    }
}
