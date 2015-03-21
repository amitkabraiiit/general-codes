#include<iostream>
#include<queue>
// Autocomplete shortcut ctrl+<space>
// Segfault Debugging : g++ -g BST.cpp; gdb a.out ; run

using namespace std;

struct Node {

	struct Node *left;
	struct Node *right;
	Node(){
		left = NULL;
		right = NULL;
	}
	int data;

};

class BST{

	public:
		Node *root;

		BST(){
			root = NULL;
		}

		void insertElement(int element);
		void deleteElement(Node *root,int element);
		void searchElement(int element);
		void inorderTraversal(Node *root);
		void preorderTraversal(Node *root);
		void postorderTraversal(Node *root);
		void breadthFirstTraversal(Node *root);
		void displayTreeHorizontal(Node *root, int level);
		void displayTreeVertical(Node *root, int level);
		int sumOfAllNodes(Node *root, int sum);
		int totalNumberOfNodes(Node *root);
		void findCommonAncestorAndPrintPath();
		int countLeafNode(Node *root);
		int countNonLeafNode(Node *root);


};

void BST::insertElement(int data){

	Node *newNode = new Node();
	newNode->data = data;

	if (!root){
		root = newNode;
		return;
	}
	if(root->data == data){
		cout<< "Element Already Exists";
	}

	Node *tempNode = root;
	while(true){
		if (data < tempNode->data) {
			if(tempNode->left){
				tempNode = tempNode->left;
			}else{
				tempNode->left = newNode;
				return;
			}

		}
		else {
			if(tempNode->right){
				tempNode= tempNode->right;
			}else{
				tempNode->right = newNode;
				return;
			}
		}
	}

}

void BST::inorderTraversal(Node *root){
	if(!root)return;
	BST::inorderTraversal(root->left);
	cout << root->data << ",";
	BST::inorderTraversal(root->right);
}

void BST::preorderTraversal(Node *root){
	if(!root)return;
	cout << root->data << ",";
	BST::preorderTraversal(root->left);
	BST::preorderTraversal(root->right);
}

void BST::postorderTraversal(Node *root){
	if(!root)return;
	BST::postorderTraversal(root->left);
	BST::postorderTraversal(root->right);
	cout << root->data << ",";
}
void BST::breadthFirstTraversal(Node *root){

	queue<Node*> q;
	if(root){
		q.push(root);
	}
	Node *tempNode;
	while(!q.empty()){
		tempNode = q.front();
		q.pop();
		cout << tempNode->data << "\n" ;
		if(tempNode->left) {
			q.push(tempNode->left);
		}
		if(tempNode->right){
			q.push(tempNode->right);
		}
	}
	return;

}

void BST::displayTreeHorizontal(Node *node , int level){

	if (!node)return;
	int i;
	displayTreeHorizontal(node->right, level+1);
	for(i=0;i<level;i++) cout << "    ";
	cout << node->data << "\n";
	displayTreeHorizontal(node->left, level+1);
}

void BST::displayTreeVertical(Node *node , int level){

	if (!node)return;
	int i;
	for(i=0;i<level;i++) cout << "    ";
	cout << node->data << "\n";
	displayTreeHorizontal(node->left, level-1);
	displayTreeHorizontal(node->right, level+1);
}

void BST::searchElement(int element){

	Node *tempNode = root;
	while(tempNode){
		if (tempNode->data == element){
			cout << "Element " << element << " found ";
			return;
		}
		else if (tempNode->data > element){
			tempNode = tempNode -> left;
		}
		else{
			tempNode = tempNode -> right;
		}
	}
	cout << "Element " << element << " not found ";


}

// cases : http://www.algolist.net/Data_structures/Binary_search_tree/Removal
void BST::deleteElement(Node *root, int element){

	Node *tempNode = root;
	Node *parentTempNode = tempNode;
	Node *tempNode1 ;
	Node *parentTempNode1;
	while(tempNode){
		if (element == tempNode->data ){
			if (!tempNode->left && !tempNode->right ){
				if(tempNode = parentTempNode->left)parentTempNode->left = NULL;
				else parentTempNode->right=NULL;
				return;
			}else if (!tempNode->left and tempNode->right ){
				parentTempNode->right = tempNode->right;
				tempNode = NULL;
				return;
			}else if (tempNode->left && !tempNode->right ){
				parentTempNode->left = tempNode->left;
				tempNode = NULL;
				return;
			}else{
				tempNode1 = tempNode -> right;
				while (tempNode1->left){
					parentTempNode1 = tempNode1;
					tempNode1 = tempNode1->left;
				}
				tempNode->data = tempNode1->data;
				parentTempNode1->left = NULL;
				tempNode1 = NULL;
				return;
			}
		}else if (element < tempNode->data){
			parentTempNode = tempNode;
			tempNode= tempNode->left;
		}else{
			parentTempNode = tempNode;
			tempNode = tempNode->right;
		}

	}

}

// alternative is to have global variable and get some in any of the traversal
int BST::sumOfAllNodes(Node *root, int sum){
	if(!root)return 0;
	return sum + root->data + BST::sumOfAllNodes(root->left,sum) + BST::sumOfAllNodes(root->right,sum);
}

int BST::totalNumberOfNodes(Node *root){
	if(!root)return 0;
	return 1  + BST::totalNumberOfNodes(root->left) + BST::totalNumberOfNodes(root->right);
}

int BST::countLeafNode(Node *root){

	if(!root)return 0;
	if(!root->left && !root->right) return  1;
	return countLeafNode(root->left) + countLeafNode(root->right);

}

int BST::countNonLeafNode(Node *root){

	if(!root)return 0;
	if(!root->left && !root->right){
		return  0;
	}else{
		return countNonLeafNode(root->left) + countNonLeafNode(root->right)+1;
	}
}

int main(){

	BST bst ;
	int i;
	int bstElements [11] = { 5,2,8,1,3,7,15,4,6,13,18 };
	for (i=0 ;i < sizeof(bstElements)/sizeof(int); i++){
		bst.insertElement(bstElements[i]);
	}
	cout << "Inorder Traversal" << "\n";
	bst.inorderTraversal(bst.root);
	cout << "\n\nPreorder Traversal" << "\n";
	bst.preorderTraversal(bst.root);
	cout << "\n\nPostorder Traversal" << "\n";
	bst.postorderTraversal(bst.root);
	cout << "\n\nBFS(Level Order) Traversal" << "\n";
	bst.breadthFirstTraversal(bst.root);


	cout << "\n\nPrinting tree horizontal" << "\n";
	bst.displayTreeHorizontal(bst.root,1);
	//cout << "\n\nPrinting tree vartical" << "\n";  not working ,will see later.
	//bst.displayTreeVertical(bst.root,10);
	cout << "\n\nSearching element 7\n";
	bst.searchElement(7);
	cout << "\n\nSearching element 95\n";
	bst.searchElement(95);
	cout << "\n\nDelete leaf element 4";
	bst.deleteElement(bst.root,4);
	cout << "\nPrinting tree horizontal" << "\n";
	bst.displayTreeHorizontal(bst.root,1);

	cout << "\nPrinting tree horizontal" << "\n";
	bst.displayTreeHorizontal(bst.root,1);

	cout << "\n\nSum of all Elements\n";
	cout << bst.sumOfAllNodes(bst.root,0) << endl;

	cout << "\nNumber of leaf nodes\n";
	cout << bst.countLeafNode(bst.root) << endl;

	cout << "\nNumber of non leaf nodes\n";
	cout << bst.countNonLeafNode(bst.root) << endl;

	cout << "\nTotal number of nodes\n";
	cout << bst.totalNumberOfNodes(bst.root) << endl;


	cout << "\n\nDelete element 7 with only one child";
	bst.deleteElement(bst.root,7);
	cout << "\nPrinting tree horizontal" << "\n";
	bst.displayTreeHorizontal(bst.root,1);

	cout << "\n\nDelete element 8 with both childs";
	bst.deleteElement(bst.root,8);

	cout << endl;


	return 0;

}
