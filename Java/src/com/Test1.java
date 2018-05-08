package com;

// https://myadventuresincoding.wordpress.com/2014/07/30/java-creating-a-simple-retry-command-with-function-passing-in-java-8/

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


//Backups now 
class Test1
{

	static int findBusiestPeriod(int[][] data) {
		
		int j = 0;
		while(data[j][2] == 0){
			j++;
		}
		int prevts = data[j][0];
		int visit = data[j][1];
		int max_vist = visit;
		int ans = prevts;
		int ts = prevts, v, ent;
		for(int i = j+1;i < data.length;i++){
			ts = data[i][0];
			v = data[i][1];
			ent = data[i][2];
			if(ts == prevts){
				if(ent == 1 )  visit+= v;
				else visit-= v;
				continue;
			}
			if(visit > max_vist) {
				ans = ts;
			}
			if(ent == 1 )  visit+= v;
			else visit-= v;
			prevts = ts;
		}
		return ans;
	}

	public static void main(String args[]){  
		int [][]data = { 
				{1487799425,14,1},
				{1487799425,4,0},
				{1487799425,2,0},
				{1487800378,10,1},
				{1487801478,18,0},
				{1487801478,19,1},
				{1487801478,1,0},
				{1487801478,1,1},
				{1487901013,1,0},
				{1487901211,7,1},
				{1487901211,8,0}
				
				
		};
/*
Test Case #1
Input: [[1487799426,21,1]],Expected: 1487799426,Actual: 1487799426
Test Case #2
Input: {{1487799425,21,0},{1487799427,22,1},{1487901318,7,0}},Expected: 1487799427,Actual: 1487901318
Test Case #3
Input: {{1487799425,21,1},{1487799425,4,0},{1487901318,7,0}},Expected: 1487799425,Actual: 1487799425
Test Case #4
Input: {{1487799425,14,1},{1487799425,4,0},{1487799425,2,0},{1487800378,10,1},{1487801478,18,0},{1487801478,18,1},{1487901013,1,0},{1487901211,7,1},{1487901211,7,0}},Expected: 1487800378,Actual: 1487901211
Test Case #5
Input: {{1487799425,14,1},{1487799425,4,1},{1487799425,2,1},{1487800378,10,1},{1487801478,18,1},{1487901013,1,1},{1487901211,7,1},{1487901211,7,1}},Expected: 1487901211,Actual: 1487901211
Test Case #6
Input: {{1487799425,14,1},{1487799425,4,0},{1487799425,2,0},{1487800378,10,1},{1487801478,18,0},{1487801478,19,1},{1487801478,1,0},{1487801478,1,1},{1487901013,1,0},{1487901211,7,1},{1487901211,8,0}},Expected: 1487801478,Actual: 1487901211
	*/
		
		System.out.println(findBusiestPeriod(data));
	}  
}