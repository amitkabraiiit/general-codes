package com.practice.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak {

	public boolean findUsingDP(String s, HashSet<String> dict, HashSet<String> memory, String answer) {
		if (s.length() == 0) { 
			System.out.println(answer);
			return true;
		} else if (memory.contains(s)) 	return false;
		else {
			int index = 0;
			String word = "";
			while (index < s.length()) {
				word += s.charAt(index);// add one char at a time
				// check if word already being solved
				if (dict.contains(word)) {
					if (findUsingDP(s.substring(index + 1), dict, memory,answer + word + " ")) 	return true;
					else {
						System.out.println("backtrack for word : "+word);
						index++;
					}
				} else 	index++;
			}
			memory.add(s);// memoization for future;
			return false;
		}
	}
	public boolean wordBreak(String s, Set<String> wordDict) {
	    int[] pos = new int[s.length()+1];
	 
	    Arrays.fill(pos, -1);
	 
	    pos[0]=0;
	 
	    for(int i=0; i<s.length(); i++){
	        if(pos[i]!=-1){
	            for(int j=i+1; j<=s.length(); j++){
	                String sub = s.substring(i, j);
	                System.out.println(sub);
	                System.out.println(Arrays.toString(pos));
	                if(wordDict.contains(sub)){
	                	System.out.println("found "+j);
	                    pos[j]=i;
	                }
	            } 
	        }
	    }
	 System.out.println(Arrays.toString(pos));
	    return pos[s.length()]!=-1;
	}
	
	public static void main(String[] args) {
		HashSet<String> hs = new HashSet<String>();
		hs.add("this");hs.add("is");hs.add("sumit");hs.add("jain");hs.add("the");hs.add("problem");hs.add("s");
		String s = "thisissumitsumitjain";
		WordBreak ws = new WordBreak();
		// create another HashSet so store the sub problems result
		HashSet<String> memoization = new HashSet<String>();
		//ws.findUsingDP(s, hs, memoization, "");
		
		// sol2
		HashSet<String> hs1 = new HashSet<String>();
		hs1.add("i");hs1.add("your");
		s = "iyour";
		System.out.println(ws.wordBreak(s, hs1));
	}
}