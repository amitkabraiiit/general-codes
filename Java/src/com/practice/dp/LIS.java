package com.practice.dp;

public class LIS {

	public static int n = 0;
	
	/*
	 * Used two methods to implement this.
	 *  longestIncreasingSubsequence1 : for any sequence starting with ith position LIS(i) = max(LIS(j)) + 1; (j>i)
	 *  We need to print max(LIS(i))
	 *  longestIncreasingSubsequence2 : for any sequence ending at ith position LIS(i) = max(LIS(j)) + 1; (j<i)
	 *  We need to print max(LIS(i))
	 */
	
	// Eg  {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
	public static int longestIncreasingSubsequence1(int[] seq){
		int maxi=0, t, i;
		n = seq.length;
		for( i=0;i<n;i++){
			t = LIS(seq,i);
			if(t>maxi){
				maxi = t;
			}
		}
		return maxi;
	}
	
	public static int LIS(int[]seq, int i){
		int j, temp , max = 0;		
		// for last element LIS = 1;
		if(i==n-1) return 1;
		// For any i, say i=0, we check for all j such that j > i , if seq[j] > seq[i] then max of all such seq[j]  is chosen
		for(j=i+1;j<n;j++){
			temp  = LIS(seq,j) ;
			if((seq[j] > seq[i]) && (temp  > max )){
				max = temp;	
			}
		}
		// max + 1 length is returned.
		return max+1;
	}

//{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
	public static int longestIncreasingSubsequence2(int[]seq){
		int[]L=new int[seq.length];
		L[0]=1;
		for(int i=1;i<L.length;i++){
			int maxn=0;
			for(int j=0;j<i;j++){
				if(seq[j]<seq[i]&&L[j]>maxn){
					maxn=L[j];
				}
			}
			L[i]=maxn+1;
		}
		int maxi=0;
		for(int i=0;i<L.length;i++){
			if(L[i]>maxi){
				maxi=L[i];
			}
		}
		return(maxi);
	}
	public static void main(String[] args) {
		int [] arr1 = {10, 22, 9, 33, 21, 50, 41, 60, 80};
		int [] arr2 = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		
		System.out.println(longestIncreasingSubsequence1(arr1));		
		System.out.println(longestIncreasingSubsequence1(arr2));
		System.out.println(longestIncreasingSubsequence2(arr1));		
		System.out.println(longestIncreasingSubsequence2(arr2));
		

	}
}
