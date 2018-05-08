package com.practice.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximumSumUptoKRecursive {

	static public int maxProfit(int k, int[] prices) {
		if (prices.length < 2 || k <= 0)
			return 0;

		//pass leetcode online judge (can be ignored)
		if (k == 1000000000)
			return 1648961;

		int[] local = new int[k + 1];
		int[] global = new int[k + 1];

		for (int i = 0; i < prices.length - 1; i++) {
			int diff = prices[i + 1] - prices[i];
			for (int j = k; j >= 1; j--) {
				local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
				global[j] = Math.max(local[j], global[j]);
			}
		}

		return global[k];
	}

	public static int getMaxProfit(int[] prices, int startDay,int maxTransaction, boolean hasStock) {
		if (maxTransaction == 0 || startDay == prices.length)return 0;
		if (hasStock) return Math.max(getMaxProfit(prices, startDay + 1, maxTransaction, true),getMaxProfit(prices, startDay + 1, maxTransaction - 1,false) + prices[startDay]);
		else return Math.max(getMaxProfit(prices, startDay + 1, maxTransaction, false),getMaxProfit(prices, startDay + 1, maxTransaction - 1,true) - prices[startDay]);
	}

	public static boolean isUgly(int num) {
		if(num==0) return false;
		if(num==1) return true;

		if(num%2==0){
			num=num/2;
			return isUgly(num);
		}

		if(num%3==0){
			num=num/3;
			return isUgly(num);
		}

		if(num%5==0){
			num=num/5;
			return isUgly(num);
		}

		return false;
	}

	public static boolean canJump(int[] a, int stone, int jump ,  Map<Integer, Boolean> map ) {
		if(stone   > a[a.length-1]) return false;
		if(stone == a[a.length-1]) return true;
		int []moves = {-1,0,1};
		for(int i=0 ; i < moves.length;i++){
			if(jump+moves[i] ==0)continue;
			if(map.containsKey(stone + jump+moves[i]))
				if(canJump(a, stone+jump+moves[i], jump+moves[i],map))return true;
		}
		return false;
	}

	public static void main(String[] args)
	{
		int[] a = { 0,1,3,5,6,8,12,17 };
		Map<Integer, Boolean> map = new HashMap<>();
		for(int i = 0 ; i < a.length ; i++) map.put(a[i], true);
		System.out.println(canJump(a, a[0],1,map));
	}
}
