package com.practice.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.sun.xml.internal.ws.message.RootElementSniffer;

class BST{

	Node root ;
	static class Node {
		Node left;
		Node right;
		int data;
		Node(){
		}
		Node(int data){
			this.data = data;
			left = null;
			right = null;
		}
	}
	BST(){
		root = null;
	}
	public void insertElement(int data){

		Node newNode = new Node();
		newNode.data = data;

		if (root == null){
			root = newNode;
			return;
		}
		if(root.data == data){
			System.out.println( "Element Already Exists");
		}
		Node tempNode = root;
		while(true){
			if (data < tempNode.data) {
				if(tempNode.left != null){
					tempNode = tempNode.left;
				}else{
					tempNode.left = newNode;
					return;
				}

			}
			else {
				if(tempNode.right != null){
					tempNode= tempNode.right;
				}else{
					tempNode.right = newNode;
					return;
				}
			}
		}

	}

	public void inorderTraversal(Node root){
		if(root == null)return;
		inorderTraversal(root.left);
		System.out.print(root.data + ",");
		inorderTraversal(root.right);
	}

	public void preorderTraversal(Node root){
		if(root == null)return;
		System.out.print( root.data + ",");
		preorderTraversal(root.left);
		preorderTraversal(root.right);
	}

	public void postorderTraversal(Node root){
		if(root == null)return;
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		System.out.print(root.data + ",");
	}
	public void breadthFirstTraversal(Node root){

		Queue q = new LinkedList();
		q.add(root);
		Node tempNode ;
		while(!q.isEmpty()){
			tempNode = (Node)q.poll();
			System.out.print( tempNode.data+",") ;
			if(tempNode.left != null) {
				q.add(tempNode.left);
			}
			if(tempNode.right != null){
				q.add(tempNode.right);
			}
		}
		return;
	}

	public void displayTreeHorizontal(Node node , int level){

		if(node == null)return;
		int i;
		displayTreeHorizontal(node.right, level+1);
		for(i=0;i<level;i++) System.out.println( "    ");
		System.out.println( node.data) ;
		displayTreeHorizontal(node.left, level+1);
	}

	public void displayTreeVertical(Node node , int level){

		if(node == null)return;
		int i;
		for(i=0;i<level;i++) System.out.println( "    ");
		System.out.println( node.data );
		displayTreeHorizontal(node.left, level-1);
		displayTreeHorizontal(node.right, level+1);
	}

	public void searchElement(int element){

		Node tempNode = root;
		while(tempNode != null){
			if (tempNode.data == element){
				System.out.println( "Element " + element + " found ");
				return;
			}
			else if (tempNode.data > element){
				tempNode = tempNode . left;
			}
			else{
				tempNode = tempNode . right;
			}
		}
		System.out.println( "Element " + element + " not found ");


	}

	public boolean searchElementRecursively(int element,Node root){
		if(root == null ) return false;
		boolean result1 = false, result2=false;
		if(element == root.data)return true;
		else if(element < root.data){
			result1 = searchElementRecursively(element, root.left);
		}else{
			result2 = searchElementRecursively(element, root.right);
		}
		return 	result1 || result2;
	}

	// cases : http://www.algolist.net/Data_structures/Binary_search_tree/Removal
	public void deleteElement(Node root, int element){

		Node tempNode = root;
		Node parentTempNode = tempNode;
		Node tempNode1 ;
		Node parentTempNode1 = new Node();
		while(!tempNode.equals(null)){
			if (element == tempNode.data ){
				if (tempNode.left == null && tempNode.right == null ){
					/*if(tempNode.equals(parentTempNode.left))parentTempNode.left = null;
					else parentTempNode.right=null;*/
					tempNode = null;
					return;
				}else if (tempNode.left == null && tempNode.right !=null ){
					parentTempNode.right = tempNode.right;
					tempNode = null;
					return;
				}else if (tempNode.left != null && tempNode.right == null ){
					parentTempNode.left = tempNode.left;
					tempNode = null;
					return;
				}else{
					tempNode1 = tempNode.right;
					while (tempNode1.left != null){
						parentTempNode1 = tempNode1;
						tempNode1 = tempNode1.left;
					}
					tempNode.data = tempNode1.data;
					parentTempNode1.left = null;
					tempNode1 = null;
					return;
				}
			}else if (element < tempNode.data){
				parentTempNode = tempNode;
				tempNode= tempNode.left;
			}else{
				parentTempNode = tempNode;
				tempNode = tempNode.right;
			}

		}

	}

	// alternative is to have global variable and get some in any of the traversal
	public int sumOfAllNodes(Node root, int sum){
		if(root == null)return 0;
		return sum + root.data + sumOfAllNodes(root.left,sum) + sumOfAllNodes(root.right,sum);
	}

	public int totalNumberOfNodes(Node root){
		if(root == null)return 0;
		return 1  + totalNumberOfNodes(root.left) + totalNumberOfNodes(root.right);
	}

	public void aca(Node root, int a){
		Node temp = root;
		Stack s = new Stack();
		int found = 0 ;
		while(true){
			if(temp == null) break;
			if(temp.data == a){
				s.push(temp.data);
				found = 1;
				break;
			}
			if(temp != null)s.push(temp.data);
			if(a > temp.data) {
				temp = temp.right;
			}else 
				temp = temp.left;
		}
		if(s.size() == 0 || found == 0){
			System.out.println("No ancestor of "+a);
			return;
		}
		System.out.println("Ancestor of "+a+":");
		while(s.size() != 0){
			System.out.print(s.peek()+" ");
			s.pop();
		}
		System.out.println();
	}
	public Node lca(Node root, int a, int b ){

		if (root == null)  return null;
		if (root.data == a || root.data == b)  return root;
		if (root.data > a && root.data > b )   return lca(root.left, a, b);
		else if (root.data < a && root.data < b )  return lca(root.right, a, b);
		else {
			return root;
		}
	}

	public Node lca1(Node root, int a, int b ){

		if (root == null)  return null;

		if (root.data == a || root.data == b)  return root;
		Node left = lca1(root.left, a, b);
		Node right =  lca1(root.right, a, b);
		if ((left != null && right != null)) {
			return root;
		}
		return (left != null) ? left : right;
	}


	//				   5
	//		     2		    8
	//		  1	  3	      7	   15
	//				4   6    13  18 
	//HashMap<int, in> m = new Hash
	public void printNodeAtMaxDistance(Node root, int level, int max){
		if(root == null )return;
		if(level > max) max = level;
		printNodeAtMaxDistance(root.left,level+1, max);
		printNodeAtMaxDistance(root.right,level+1, max);
	}


	public static void printRootToLeaf(Node root){
		printRootToLeafPathUtil(root, new int[100], 0);
	}
	static void printRootToLeafPathUtil(Node root, int path[], int pathLen)
	{
		if (root==null) return;
		path[pathLen] = root.data;
		pathLen++;
		if (root.left==null && root.right==null)
		{
			for (int i=0; i<pathLen; i++)
			{
				System.out.print(path[i]);
			}
			System.out.println();
		}
		else
		{
			printRootToLeafPathUtil(root.left, path, pathLen);
			printRootToLeafPathUtil(root.right, path, pathLen);
		}
	}
	int sum = 0;
	public int evenOddLevelDiff(Node root){
		if(root == null) return 0;
		int leftHeight = evenOddLevelDiff(root.left);
		int rightHeight = evenOddLevelDiff(root.right);
		int actualHeight = 1+Math.max(leftHeight, rightHeight);
		if( actualHeight%2 ==0) {
			sum = sum + root.data;
			System.out.print("+"+root.data);
		}else{
			sum = sum - root.data;
			System.out.print("-"+root.data);
		}
		return actualHeight;
	}

	public int countLeafNode(Node root){

		if(root == null)return 0;
		if(root.left == null && root.right == null) return  1;
		return countLeafNode(root.left) + countLeafNode(root.right);

	}

	public int countNonLeafNode(Node root){

		if(root == null)return 0;
		if(root.left == null && root.right == null){
			return  0;
		}else{
			return countNonLeafNode(root.left) + countNonLeafNode(root.right)+1;
		}
	}

	public int printHeight(Node root){
		if(root == null)return 0;
		return Math.max(printHeight(root.left) ,printHeight(root.right))+1;
	}
	void printLevelOrder(Node root)
	{
		int h = printHeight(root);
		int i;
		boolean alt = false;
		for(i=1; i<=h; i++){
			printSpiralLevel(root, i,alt);
			alt = !alt;
		}

	}     
	/* Print nodes at a given level */
	void printLevel(Node root, int level)
	{
		if(root == null)
			return;
		if(level == 1)
			System.out.print(root.data+" ");
		else if (level > 1)
		{
			printLevel(root.left, level-1);
			printLevel(root.right, level-1);
		}
	}

	void printSpiralLevel(Node root, int level, boolean alt)
	{
		if(root == null)   return;
		if(level == 1) System.out.print(root.data+" ");
		else if (level > 1)
		{
			if(alt)
			{
				printSpiralLevel(root.left, level-1, alt);
				printSpiralLevel(root.right, level-1, alt);
			}
			else
			{
				printSpiralLevel(root.right, level-1, alt);
				printSpiralLevel(root.left, level-1, alt);
			}
		}
	}

	static void findSibling(Node root, int data)
	{
		if(root == null)return;
		if (root.left != null && root.left.data == data)
		{
			if ( root.right != null)
			{
				System.out.println("--------SIBLING FOUND---------"+root.right.data);
			}
			else 
				System.out.println("--------SIBLING Not FOUND---------");
			return;
		}
		if (root.right != null && root.right.data == data)
		{
			if ( root.left != null)
			{
				System.out.println("--------SIBLING FOUND---------"+root.left.data);
			}
			else 
				System.out.println("--------SIBLING Not FOUND---------");
			return;
		}
		if(root.left != null)findSibling(root.left, data);	
		if(root.right!= null)findSibling(root.right, data);
	}

	public static void main(String[] args) {

		BST bst = new BST() ;
		int i;
		int bstElements [] = { 5,2,8,1,3,7,15,4,6,13,18 };

		//				   5
		//		     2		    8
		//		  1	  3	      7	   15
		//				4   6    13  18 


		for (i=0 ;i < bstElements.length; i++){
			bst.insertElement(bstElements[i]);
		}
		System.out.println( "!!!!");
		System.out.println();
		findSibling(bst.root,4);

		//			  50
		//	       /      \
		//	     10        60
		//	    /  \       /  \
		//	   5   20    55    70
		//	            /     /  \
		//	          45     65    80

		Node root = new Node(50);
		root.left        = new Node(10);
		root.right       = new Node(60);
		root.left.left  = new Node(5);
		root.left.right = new Node(20);
		root.right.left  = new Node(55);
		root.right.left.left  = new Node(45);
		root.right.right = new Node(70);
		root.right.right.left = new Node(65);
		root.right.right.right = new Node(80);





		//bst.aca(bst.root,6);

		System.out.println(bst.evenOddLevelDiff(bst.root)); // 5+1+3+7+15 - 2- 8 -4-6-13-18 = 31 - 51
		System.out.println( "Inorder Traversal") ;
		bst.inorderTraversal(bst.root);
		System.out.println("\n");
		System.out.println( "Preorder Traversal") ;
		bst.preorderTraversal(bst.root);
		System.out.println("\n");
		System.out.println( "Postorder Traversal") ;
		bst.postorderTraversal(bst.root);
		System.out.println("\n");
		System.out.println( "BFS(Level Order) Traversal") ;
		bst.breadthFirstTraversal(bst.root);
		System.out.println("\n");
		System.out.println( "TreeHeight:" + bst.printHeight(bst.root)) ;



		//System.out.println( "Printing tree horizontal") ;
		//bst.displayTreeHorizontal(bst.root,1);
		//System.out.println( "Printing tree vartical" ;  not working ,will see later.
		//bst.displayTreeVertical(bst.root,10);
		System.out.println("\n");
		System.out.println( "Searching element 7");
		bst.searchElement(7);
		System.out.println("\n");
		System.out.println( "Searching element 95");
		bst.searchElement(95);
		System.out.println("\n");
		System.out.println( "Delete leaf element 4");
		bst.deleteElement(bst.root,4);
		/*System.out.println( "Printing tree horizontal") ;
		bst.displayTreeHorizontal(bst.root,1);

		System.out.println( "Printing tree horizontal") ;
		bst.displayTreeHorizontal(bst.root,1);*/

		System.out.println( "Sum of all Elements");
		System.out.println( bst.sumOfAllNodes(bst.root,0));
		System.out.println("\n");

		System.out.println( "Number of leaf nodes");
		System.out.println( bst.countLeafNode(bst.root));
		System.out.println("\n");


		System.out.println( "Number of non leaf nodes");
		System.out.println( bst.countNonLeafNode(bst.root));
		System.out.println("\n");

		System.out.println( "Total number of nodes");
		System.out.println( bst.totalNumberOfNodes(bst.root));
		System.out.println("\n");


		System.out.println( "Delete element 7 with only one child");
		bst.deleteElement(bst.root,7);
		//ystem.out.println( "Printing tree horizontal");
		//bst.displayTreeHorizontal(bst.root,1);

		System.out.println( "Delete element 8 with both childs");
		bst.deleteElement(bst.root,8);

	}
}
