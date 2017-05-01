package com.practice.dp;

import java.util.Arrays;

public class LongestCommonSubsequence {
	static int arr[][];
	static String newString = "";

	/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
	static String lcsPrint( String x, String y, int m, int n)
	{
			// based on the recursive function below : lcsLength
		   for (int i=0; i<=m; i++){
		     for (int j=0; j<=n; j++){
		       if (i == 0 || j == 0)    arr[i][j] = 0;
		       else if (x.charAt(i-1) == y.charAt(j-1))    arr[i][j] = arr[i-1][j-1] + 1;
		       else   arr[i][j] = max(arr[i-1][j], arr[i][j-1]);
		     }
		   }
		 
		   String lcs = "";
		 
		   // Start from the right-most-bottom-most corner and
		   // one by one store characters in lcs[]
		   int i = m, j = n;
		   while (i > 0 && j > 0) {
		      if (x.charAt(i-1 ) == y.charAt(j-1)) {
		          lcs =  x.charAt(i -1) + lcs; 
		          i--; j--;  
		      }	 
		      // If not same, then find the larger of two and go in the direction of larger value
		      else if (arr[i-1][j] > arr[i][j-1])  i--;
		      else  j--;
		   }
		 
		   // Print the lcs
		   return lcs;
	}

	static int max(int a, int b)
	{
		return (a > b)? a : b;
	}
	
	static int lcsLength( String x, String y, int m, int n)
	{
		if(m==0 || n==0 ) return 0;
		if(x.charAt(m-1) == y.charAt(n-1)) return 1 + lcsLength(x, y, m-1, n-1);
		return max(lcsLength(x, y, m-1, n),lcsLength(x, y, m, n-1));
	}

	/* Driver program to test above function */
	public static void main(String[] args) {


		String X = "AGGTABC";
		String Y = "GXTXAYBD";
	//	String X = "MZJAWXU";
	//	String Y = "XMJYAUZ";
	//	String X = "ACDB";
	//	String Y = "AMCB";
		int m = X.length();
		int n = Y.length();
		arr = new int[m+1][n+1];
		System.out.println("Length of LCS of "+X+ " and "+Y+" is "+ lcsLength( X, Y, m, n ) );
		System.out.println("LCS of "+X+ " and "+Y+" is "+ lcsPrint( X, Y, m, n ) );
		for(int i = 0 ;i < arr.length;i++){
			for(int j = 0 ;j < arr[i].length;j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
