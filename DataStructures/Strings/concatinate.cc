#include<stdio.h>

int main(){

	char a[10],b[10];
	scanf("%s",a);
	scanf("%s",b);
	int i=0, j=0;

	printf("%s\n",a);
	printf("%s\n",b);

	while(a[i]!='\0'){
		i++;
	}
	while(b[j]!='\0'){
		a[i++] = b[j++];
	}
	a[i] = '\0';

	printf("%s\n",a);
	printf("%s\n",b);

	return 0;
}
