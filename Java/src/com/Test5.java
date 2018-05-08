package com;

import java.util.function.Predicate;

public class Test5 {

	static boolean isValid(int count[], int k){
	    int val = 0;
	    for (int i=0; i<26; i++)  if (count[i] > 0) val++;
	    return (k >= val);
	}
	 
	// Finds the maximum subString with exactly k unique chars
	static void kUniques(String s, int k)
	{
	    int n = s.length();
	    int count[] = new int[26];
	    for (int i=0; i<n; i++)    if (count[s.charAt(i)-'a']==0) count[s.charAt(i)-'a']++;
	 
	    int curr_start = 0, curr_end = 0;
	    int max_window_size = 1, max_window_start = 0;
	 	 
	    count[s.charAt(0)-'a']++;  // put the first character
	 
	    // Start from the second character
	    for (int i=1; i<n; i++){
	        count[s.charAt(i)-'a']++;
	        curr_end++;
	 
	        // If there are more than k unique characters in current window, remove from left side
	        while (!isValid(count, k)){
	            count[s.charAt(curr_start)-'a']--;
	            curr_start++;
	        }
	 
	        // Update the max window size if required
	        if (curr_end-curr_start+1 > max_window_size){
	            max_window_size = curr_end-curr_start+1;
	            max_window_start = curr_start;
	        }
	    }
	 
	    System.out.println("Max suString is : "+ s.substring(max_window_start, max_window_size)+ " with length " +max_window_size);
	}
	 
	interface test{
		public void print();
	}
	
	public static boolean someCondition(){
		return true;
	}
	
	// Driver function
	public static void main(String[] args) {
		
	    String s = "aabacbebebe";
	    int k = 3;
	    //kUniques(s, k);
	    test t = () -> System.out.println("this is a lambda expression");
	    t.print();
	    
	    Predicate<Integer> p = (i) -> (i > 10);
	    System.out.println(p.test(1));
	    //Test5::someCondition;
	}
}
