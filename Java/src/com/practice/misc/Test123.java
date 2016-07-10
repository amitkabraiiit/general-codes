package com.practice.misc;

public class Test123 {


	static int count( int S[], int m, int n )
	{
		int i, j, x, y;

		// We need n+1 rows as the table is consturcted in bottom up manner using 
		// the base case 0 value case (n = 0)
		int table[][] = new int[n+1][m];

		// Fill the enteries for 0 value case (n = 0)
		for (i=0; i<m; i++)
			table[0][i] = 1;

		// Fill rest of the table enteries in bottom up manner  
		for (i = 1; i < n+1; i++) // amount required
		{
			for (j = 0; j < m; j++) // coins array
			{
				// Count of solutions including S[j]
				x = (i-S[j] >= 0)? table[i - S[j]][j]: 0;

				// Count of solutions excluding S[j]
				y = (j >= 1)? table[i][j-1]: 0;

				// total count
				table[i][j] = x + y;					
				System.out.println("i = "+i + " j = "+j+ " table["+i+"]["+j+"] = "+table[i][j]);

			}
		}
		for( i=0;i<m;i++)
			System.out.println("no of ways to make sum with coins from 1 to " + (i+1)+" is "+table[n][i]);
		return table[n][m-1];
	}

	// Driver program to test above function
	public static void main(String[] args) {


		int arr[] = {1, 2, 3};
		int m = arr.length;
				int n = 5;
		System.out.println(count(arr, m, n));
	}

}
