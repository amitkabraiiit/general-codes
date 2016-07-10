package com.practice.algos;

public class PascalTriangle {

	public static void printPascal1(int n) {
		int[] previousRow;
		int[] currentRow = {1};
		printArray(currentRow,n);
		previousRow = currentRow;
		for (int i = 2; i <= n; i++) {
			currentRow = new int[i];
			currentRow[0] = 1;
			currentRow[i - 1] = 1;	
			for (int j = 1; j <= i - 2; j++) {
				currentRow[j] = previousRow[j-1] + previousRow[j];
			}
			printArray(currentRow, n-i);
			previousRow = currentRow;
		}
	}
	public static void printArray(int[] array, int s) {
		for(int j=0 ;j<s;j ++){
			System.out.print(" ");
		}
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void printPascal2(int n) {
		int[] previousRow = new int[n+1];
		int[] currentRow = new int[n+1];
		previousRow[1]=1;
		previousRow[2]=1;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= n-i; j++)System.out.print(" ");
			if(i==1){
				System.out.println("1");
				continue;
			}
			currentRow[1]=1;
			System.out.print("1 ");
			for(int k=2;k<i;k++){
				currentRow[k] = previousRow[k-1] + previousRow[k]; 
				System.out.print(currentRow[k]+" ");
			}
			currentRow[i]=1;
			for(int l=1;l<=n;l++){
				previousRow[l] = currentRow[l];
			}
				
			
			System.out.println("1 ");
		}
	}

	public static void printPascal3(int n) {
		int[] previousRow = new int[n];
		int[] currentRow = new int[n];
		previousRow[0]=1;
		previousRow[1]=1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j+i < n; j++)System.out.print(" ");
			if(i==0){
				System.out.println("1");
				continue;
			}
			currentRow[0]=1;
			System.out.print("1 ");
			for(int k=1;k+1<=i;k++){
				currentRow[k] = previousRow[k-1] + previousRow[k]; 
				System.out.print(currentRow[k]+" ");
			}
			currentRow[i]=1;
			for(int l=0;l<n;l++)
				previousRow[l] = currentRow[l];
			System.out.println("1 ");
		}
	}

	public static void printPascal4(int n) {
		int[] previousRow = new int[n];
		int[] currentRow = new int[n];
		previousRow[0]=1;
		previousRow[1]=1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j+i < n; j++)System.out.print(" ");
			currentRow[0]=1;
			for(int k=1;k+1<=i;k++){
				currentRow[k] = previousRow[k-1] + previousRow[k]; 
			}
			currentRow[i]=1;
			for(int l=0;l<=i;l++){
				previousRow[l] = currentRow[l];
				System.out.print(currentRow[l]+" ");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String args[])
	{
		/*printPascal1(6);
		printPascal2(6);
		printPascal3(6);*/
		printPascal4(6);
	}
}


