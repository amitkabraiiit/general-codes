package com.practice.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset {

	public static ArrayList<ArrayList<Integer>> subsets1(ArrayList<Integer> a) {
		ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
		output.add(new ArrayList<Integer>());
		if (a.size() == 0)return output;
		generate(a, output, new ArrayList<Integer>(), 0);
		for(List<Integer> l : output)System.out.println(l);
		return output;
	}

	public static void generate(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> output, ArrayList<Integer> temp, int index){
		for (int i = index; i < a.size(); i++){
			temp.add(a.get(i));
			output.add(new ArrayList<Integer>(temp));
			generate(a, output, temp, i+1);
			temp.remove(temp.size() - 1);
		}
	}


	public static void ss(int start, ArrayList<Integer> A,ArrayList<Integer> tmp, ArrayList<ArrayList<Integer>> res){

		if(start == A.size()){
			ArrayList<Integer> a = new ArrayList<>(tmp);
			res.add(a);
			return;
		}
		tmp.add(A.get(start));
		ss(start+1,A,tmp, res);
		tmp.remove(tmp.size()-1);
		ss(start+1,A,tmp, res);
	}

	public static ArrayList<ArrayList<Integer>> subsets2(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ss(0, A, new ArrayList<Integer>(), res );
		for(List<Integer> l : res){
			System.out.println(l);
		}
		return res;
	}

	public static void subsets3(ArrayList<Integer> A) {
		int n = A.size();
		int temp1, temp2 ;
		for(int i = 0 ; i < Math.pow(2, n) ; i++){
			temp1 = n-1;
			temp2 = i;
			System.out.print("[");
			while(temp1 != -1){
				if((temp2 & 1) == 1)System.out.print(A.get(n-1-temp1)+",");
				temp2 = temp2 >> 1;
				temp1--;
			}
			System.out.println("]");			
		}
	}

	public static List<Integer> print(ArrayList<Integer> a){
		return new ArrayList<Integer>();
	}

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1,2,3));
		System.out.println("Printing from subset 1 double recursion ");
		subsets1(a);
		System.out.println("Printing from subset 2 single recursion");
		subsets2(a);
		System.out.println("Printing from subset 3 iteratively or using bits ");
		subsets3(a);
	}
}
