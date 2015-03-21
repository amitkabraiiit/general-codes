package com.practice.dp;

public class EditDistance {

	/*
	 * http://www.programcreek.com/2013/12/edit-distance-in-java/
	 * http://professorjava.weebly.com/edit-distance.html
	 */
	public static int editDistance(String s, String t){
	    int m=s.length();
	    int n=t.length();
	    int[][]d=new int[m+1][n+1];
	    for(int i=0;i<=m;i++){
	      d[i][0]=i;
	    }
	    for(int j=0;j<=n;j++){
	      d[0][j]=j;
	    }
	    for(int j=1;j<=n;j++){
	      for(int i=1;i<=m;i++){
	        if(s.charAt(i-1)==t.charAt(j-1)){
	          d[i][j]=d[i-1][j-1]; // edit distance when a character in string corresponding to i is same as a character in
	          					   // string corresponding to j
	        }
	        else{
	        // suppose we are converting string corresponding to i as same as string j
	        // d[i-1][j] -- edit distance when a character is deleted from i
	        // d[i][j-1] -- edit distance when a character is added to i
	        // d[i-1][j-1] -- edit distance when a character is replaced in i
	          d[i][j]=min((d[i-1][j]+1),(d[i][j-1]+1),(d[i-1][j-1]+1)); 
	          // just calculating the cost not changing the string, +1 is adding cost of deletion / insertion / substitution
	        }
	      }
	    }
	    for(int i=0;i<=m;i++)
	    	for(int j=0;j<=n;j++)System.out.println("m and n "+i+":"+j+":"+d[i][j]);
	    System.out.println(s);
	    System.out.println(t);
	    return(d[m][n]);// minimum number of changes required to change “source” into “target”
	  }
	  public static int min(int a,int b,int c){
	    return(Math.min(Math.min(a,b),c));
	  }
	  public static void main(String[] args) {
//		  System.out.println(editDistance("saturday","sunday"));
//		  System.out.println(editDistance("amit","anuj"));
//		  System.out.println(editDistance("am","b"));
//		  System.out.println(editDistance("amt","amit"));
//		  System.out.println(editDistance("abcdeft","abcdefz"));
		  System.out.println(editDistance("abc","def"));
	}
}
