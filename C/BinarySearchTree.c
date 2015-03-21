#include<stdio.h>
#include<malloc.h>


struct node {

	node *left;
	node *right;
	int data;

}


void insertElement(int i, node *t){

	node *temp = malloc(sizeof(node));
	temp->data = i;
	if (!t){
		
		t = temp;
	}else{
		while(t){
			if(i < t->data){
			
				t = t-> left;	
			}else
			{
				t = t->right;
			}	
		}
		t = temp;
	}

}

void printTree(node *t){

	if(!t)return;
	printTree(t->left);
	printf("%d\n",t->data);
	printTree(t->right);

	

}

int main(){

	node *tree = new node;
	insertElement(5, tree);
	insertElement(4, tree);
	insertElement(6, tree);
	insertElement(3, tree);
	insertElement(7, tree);
	insertElement(2, tree);
	insertElement(8, tree);
	insertElement(1, tree);
	insertElement(9, tree);
	printTree(tree);
}

