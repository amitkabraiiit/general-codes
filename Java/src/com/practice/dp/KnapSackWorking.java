package com.practice.dp;

public class KnapSackWorking {
	static int knapSack(int W, int wt[], int val[], int n)
	{
		/*
		 * Here instead of directly calculated best value for weight W for n items , we find best value
		 * for all weights using {0 ... n} items. The table [W][n] stores the best values for each w and each n.
		 * While creating table we take optimum decisions at every point and reuse subproblems already solved.
		 */
		int i, w;
		/*
		 * This commented block works as well.
		int K[][] = new int[n+1][W+1];
		 Build table K[][] in bottom up manner
				for (i = 1; i <= n; i++)
				{
					for (w = 1; w <= W; w++)
					{
						if (wt[i] <= w)
							K[i][w] = max(val[i] + K[i-1][w-wt[i]],  K[i-1][w]); // duplicates not allowed				
						// K[i][w] = max(val[i] + K[i][w-wt[i]],  K[i-1][w]); // duplicates allowed
						else
							K[i][w] = K[i-1][w];		
					}
				}
				for (i = 1; i <= n; i++)
				{
					for (w = 1; w <= W; w++)
						System.out.println("i w and k[i][w] --> "+i+" "+w+" "+K[i][w]);
				}
	 return K[n][W];// means for weight W and using 1 to n items what's the maximum value that can be stored in a knapsack.
	 */
		int K[][] = new int[W+1][n+1];
		for (w = 1; w <= W; w++)
		{
			for (i = 1; i <= n; i++)
			{
				if (wt[i] <= w)
					K[w][i] = max(val[i] + K[w-wt[i]][i-1],  K[w][i-1]); // duplicates not allowed
				// Example K[22][2] means for weight 22 upto item number 2 what is the maximum value knapsack can get
				// K[22][2] = max(val[2] + K[22-20][1], K[22][1])
				// K[22][2] = max(100 + K[2][1], K[22][1])
				// K[22][2] = max(100 + 0, 60) = 100
				// K[22][1] , K[2][1] got calculated earlier so simply use it : overlapping subproblems.
				// And since at every stage we are making an optimal choice : optimum substructure. 
				
				
			    //	K[w][i] = max(val[i] + K[w-wt[i]][i],  K[w][i-1]); // duplicates allowed
				else // ith item doesn't fit in knapsack of weight w hence take best value calculated earlier
					K[w][i] = K[w][i-1];		
			}
		}
		for (w = 1; w <= W; w++)
		{
			for (i = 1; i <= n; i++)

				System.out.println("w i and k[w][i] --> "+w+" "+i+" "+K[w][i]);
		}

	return K[W][n];// means for weight W and using upto nth items what's the maximum value that can be stored in a knapsack.
	}
	static int max(int a, int b) { return (a > b)? a : b; }
	public static void main(String[] args) {
		int val[] = {0,60, 100, 120};
		int wt[] = {0,10, 20, 30};
		int  W = 50;
		int n = wt.length;
		System.out.println(knapSack(W, wt, val, n-1));

	}

}
