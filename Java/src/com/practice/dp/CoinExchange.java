package com.practice.dp;

public class CoinExchange {
	public static int minChange1(int[] val, int T) {
		int n = val.length;
		int inf = Integer.MAX_VALUE;
		int min[] = new int[T+1];
		for (int j = 0; j <= T; j++) min[j] = inf;
		min[0]=0;
		for (int t = 1; t <= T; t++) {
			for (int i = 0; i < n; i++) {
				if (val[i] <= t ){
					System.out.println("t and val[i] --> "+t+" "+val[i]+ " "+"min["+(t-val[i])+"] min["+ t+"]");
					if( min[t-val[i]]+1 < min[t] ) min[t] = min[t-val[i]]+1;
				}else{
					System.out.println("t and val[i] --> "+t+" "+val[i]+ " "+"min["+(t-val[i])+"] min["+ t+"]");
				}
			}
		}
		for (int j = 0; j <= T; j++){
			System.out.println("For sum "+j+" min coins required are "+min[j]);
		}
		if(min[T]== Integer.MAX_VALUE)return -1;
		return min[T]; // For the sum of T and using 0 to nth coins what is the minimum number of coins required for a change 
	}


	static int min(int a, int b) { return (a < b)? a : b; }
	public static void main(String[] args) {
		int val[] = {1, 2, 3,5,10};
		int  T = 10;
		System.out.println(minChange1( val, T));

	}

}
