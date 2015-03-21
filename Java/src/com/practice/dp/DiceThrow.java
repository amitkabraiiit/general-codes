package com.practice.dp;

public class DiceThrow {
	
	/*
	 * Souce : http://www.geeksforgeeks.org/dice-throw-problem/
	 */
	
	// The main function that returns number of ways to get sum 'x'
	// with 'n' dice and 'm' with m faces.
	static int findWays(int m, int n, int x) 	// findWays(6, 3, 8) // 21
	{
		// One extra row and column are used for simplicity The entries in 0th row and 0th column are never used.
		int [][]table = new int[n + 1][x + 1]; // all initialized with zero

		// Table entries for only one dice
		for (int j = 1; j <= m && j <= x; j++)table[1][j] = 1;

		// Fill rest of the entries in table using recursive relation
		// i: number of dice, j: sum
		for (int i = 2; i <= n; i++)  // Say i=3 dice
			for (int j = 1; j <= x; j++) // Say j=5 as sum
				for (int k = 1; k <= m && k < j; k++) 
					table[i][j] += table[i-1][j-k];
		// [3][5] = [2][4]+[2][3]+[2][2]+[2][1]. So [2][4] implies 3rd dice will have 1
		// So [2][3] implies 3rd dice will have 2, etc.Here we are just finding the number of ways to get 5 with 3 dices
		// and we already have results for 2 dices.

		/* Uncomment these lines to see content of table*/
	    for (int i = 0; i <= n; i++)
	    {
	      for (int j = 0; j <= x; j++)
	        System.out.print(table[i][j] +" ");
	      System.out.println();
	    } 
		return table[n][x]; // number of ways by which n dice each with m faces can give sum as x when thrown 
	}

	// Driver program to test above functions
	public static void main(String[] args) {

		// m , n , x
	//	System.out.println(findWays(4, 2, 1)); // 0
//		System.out.println(findWays(2, 2, 3)); // 2
		System.out.println(findWays(6, 3, 8)); // 21
//		System.out.println(findWays(4, 2, 5)); // 4
//		System.out.println(findWays(4, 3, 5)); // 6

	}
}
