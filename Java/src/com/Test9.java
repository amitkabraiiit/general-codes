package com;

public class Test9 {

	public static void generateParenthesis(int [][]n) {
		int maxSum = 0, maxLen = 0;
		for(int i = 0; i < n.length;i++){
			for(int j = 0; j < n[0].length;j++){
				maxSum = 0;
				maxSum = generateParenthesisHelper(n, i,j, maxSum);
				if(maxLen < maxSum) maxLen = maxSum; 
			}			
		}
		System.out.println(maxLen);
	}

	
	private static int generateParenthesisHelper(int[][]n, int row,int col, int maxSum) {
		int x[]= {0,0,1,-1};
		int y[]= {1,-1,0,0};
		for(int i = 0 ; i< x.length;i++){
			if(isSafe(n,row,col, x[i],y[i])){
				maxSum = Math.max(maxSum,generateParenthesisHelper(n, row+x[i], col+y[i],  maxSum+1));
			}
		}
		return maxSum;
	}

	private static boolean isSafe(int[][] n, int row, int col, int i, int j) {

		if (row + i < n.length && row + i >= 0 && col + j < n[0].length
				&& col + j >= 0 && n[row][col] < n[row + i][col + j])
			return true;
		return false;
	}


	public static void main(String[] args) {
		int a [][] = {
		                    {9,9,4},
		                    {6,6,8},
		                    {2,1,1}
		                  };
		int a1 [][] = {
                {3,4,5},
                {3,2,6},
                {2,2,1}
              };
		generateParenthesis(a);
		generateParenthesis(a1);
		String s = "123";
		int sum = 0;
		for(int i=0; i< s.length() ; i++){
			char temp = s.charAt(i);
			if(temp >= '0' && temp <='9' )	sum = sum + (int)(temp);
		}
		System.out.println("sum "+sum);
	}

}
