package com.practice.java8;
import java.io.*;

interface performOperation{
	int check(int a);
}
class Math{
	public static int checker(performOperation p ,int num){
		return p.check(num);
	}

	public performOperation checkEvenOdd(){
		return (a) ->  (a%2 == 0 ? 0 : 1);
	}
	public performOperation checkPrime(){
		performOperation p = new performOperation(){
			public int check(int a){
				for(int i = 2 ; i < a/2 ; i++){
					if (a%i == 0) return 1;
				}
				return 0;
			}
		};
		return p;
	}
	public performOperation checkPalindrome(){
		performOperation p = new performOperation(){
			public int check(int a){
				String str = Integer.toString(a);
				for(int i = 0 ; i < str.length(); i++){
					if(str.charAt(i) != str.charAt(str.length() -1 -i))return 1;
				}
				return 0;
			}
		};
		return p;
	}
}
public class LambdaPractice2 {

	public static void main(String[] args)throws IOException {
		Math ob = new Math();
		/*
		 * Input
		 * 5
		1 4
		2 5
		3 898
		1 3
		2 12
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=5;
		int i =0;
		performOperation op;
		int algo[] = {1,2,3,1,2};
		int number[] = {4,5,898,3,12};
		int ret =0;
		String ans=null;
		while(i!=5){
			int ch=algo[i];
			int num=number[i];
			if(ch==1){
				op = ob.checkEvenOdd();  
				ret = ob.checker(op,num);
				ans = (ret == 0)?"EVEN":"ODD";
			}
			else if(ch==2){
				op = ob.checkPrime();
				ret = ob.checker(op,num);
				ans = (ret == 0)?"PRIME":"COMPOSITE";
			}
			else if(ch==3){
				op = ob.checkPalindrome();
				ret = ob.checker(op,num);
				ans = (ret == 0)?"PALINDROME":"NOT PALINDROME";

			}
			i++;
			System.out.println(ans);
		}
	}

}
