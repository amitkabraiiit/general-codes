#include<iostream>
using namespace std;


struct Node{

	Node *next;
	int data;

};


class List{

	private:
		Node *node;

	public:
		List(){
			node = NULL;
		}
		void insertElement(int element);
		void deleteElement(int element);
		void searchElement(int element);
		void displayList();

};


void List::insertElement(int element){

	Node *temp = new Node;
	temp->data = element;
	temp->next = NULL;
	if(!node){
		node = temp;
		return;
	}
	Node *tempNode = node;
	while(tempNode->next){
		tempNode = tempNode->next;
	}
	tempNode->next = temp;
}

void List::displayList(){

	Node *tempNode = node;
	while(tempNode->next){
		cout << tempNode->data << " ";
		tempNode = tempNode->next;
	}
	cout << tempNode->data << endl;
}

void List::searchElement(int element){

	Node *tempNode = node;
	while(tempNode){
		if(tempNode->data == element){
			cout << element << " found!";
			return;
		}
		tempNode=tempNode->next;
	}
	cout << element << " not found!";
}

void List::deleteElement(int element){

	Node *tempNode = node;
	Node *prevNode = node;
	while(tempNode){
		if(tempNode->data == element){
			prevNode->next = tempNode->next;
			tempNode = NULL;
			return;
		}
		prevNode = tempNode;
		tempNode=tempNode->next;
	}
}

int main(){

	List l;
	int i;
	for (i=0; i<10;i++){
		l.insertElement(i);
	}
	cout << "Display List" << endl;
	l.displayList();
	cout << endl ;
	cout << "Search 9 in List" << endl;
	l.searchElement(9);
	cout << endl << endl;
	cout << "Search 10 in List" << endl;
	l.searchElement(10);
	cout << endl << endl;
	cout << "Delete 5 in List" << endl;
	l.deleteElement(5);
	l.displayList();
	cout << endl << endl;
	return 0;
}
