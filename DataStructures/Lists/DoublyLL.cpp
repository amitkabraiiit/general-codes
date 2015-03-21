#include<iostream>
using namespace std;

struct Node{

	Node *next;
	Node *prev;
	int data;
};

class doublyll {

	private:
		Node *node;

	public:
		doublyll(){
		}

		void insertElement(int element);
		void deleteElement(int element);
		void searchElement(int element);
		void traverseBeg();
		void traverseEnd();
		void sort();

};

void doublyll::insertElement(int element){

	Node *temp = new Node;
	temp->data = element;
	temp->next = NULL;
	temp->prev = NULL;
	if(!node){
		node = temp;
		return;
	}

	Node *tempNode = node;

	while(tempNode->next){
		tempNode = tempNode->next;
	}
	tempNode->next = temp;
	temp->prev = tempNode;
}

void doublyll::deleteElement(int element){
	Node *tempNode = node;

	while(tempNode){
		if (tempNode->data == element){
			tempNode->prev->next = tempNode->next;
			tempNode->next->prev = tempNode->prev;
			return;
		}
		tempNode = tempNode->next;
	}
	cout << "Element " << tempNode->data << " Not Found!!";

}
void doublyll::searchElement(int element){
	Node *tempNode = node;

	while(tempNode){
		if (tempNode->data == element){
			cout << "Element " << tempNode->data << " Found!!";
			return;
		}
		tempNode = tempNode->next;
	}
	cout << "Element " << element << " Not Found!!";

}
void doublyll::traverseBeg(){
	Node *tempNode = node;

	while(tempNode->next){
		cout << tempNode->data << " ";
		tempNode = tempNode->next;
	}
	cout << tempNode->data << endl;
}
void doublyll::traverseEnd(){
	Node *tempNode = node;

	while(tempNode->next){
		tempNode = tempNode->next;
	}
	while(tempNode->prev){
		cout << tempNode->data << " ";
		tempNode = tempNode->prev;
	}
	cout << tempNode->prev << endl;

}
void doublyll::sort(){
	Node *tempNode1 = node;
	Node *tempNode2 = node;
	int temp;
	while(tempNode1->next){

		tempNode2 = tempNode1->next;
		while(tempNode2){
			if(tempNode1->data < tempNode2->data){
				temp = tempNode1->data;
				tempNode1->data = tempNode2->data;
				tempNode2->data = temp;
			}
			tempNode2 = tempNode2->next;
		}
		tempNode1 = tempNode1->next;
	}
}



int main(){

	doublyll dll ;
	int i;
	for (i=0; i<10;i++){
		dll.insertElement(i);
	}

	cout << "Traverse from Beginning" << endl;
	dll.traverseBeg();
	cout << endl ;
	cout << "Traverse from End" << endl;
	dll.traverseEnd();
	cout << endl ;
	cout << "Search 9 in List" << endl;
	dll.searchElement(9);
	cout << endl << endl;
	cout << "Search 10 in List" << endl;
	dll.searchElement(10);
	cout << endl << endl;
	cout << "Delete 5 in List" << endl;
	dll.deleteElement(5);
	dll.traverseBeg();
	cout << endl ;
	cout << "Sorting list" << endl;
	dll.sort();
	dll.traverseBeg();
	cout << endl ;

	return 0;
}
