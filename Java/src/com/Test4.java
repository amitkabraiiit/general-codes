package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test4 {


	public static int next_greater(int n)
	{
		
		System.out.println("n = "+Integer.toBinaryString(n));
		int a=(~n)+1;
		System.out.println("a = "+Integer.toBinaryString(a));
		int t=a&n;
		System.out.println("t = "+Integer.toBinaryString(t));
		int x=n+t;
		System.out.println("x = "+Integer.toBinaryString(x));
		int y=x^n;
		System.out.println("y = "+Integer.toBinaryString(y));
		y=y/t;
		System.out.println("y = "+Integer.toBinaryString(y));
		y=y>>2;
		System.out.println("y = "+Integer.toBinaryString(y));
		System.out.println("x+y = "+Integer.toBinaryString(x+y));
		return x+y;
	}

	public static int next_greater1(int x)
	{
		System.out.println("==============");
		int next = 0;
		if(x !=0){
			System.out.println("x = "+Integer.toBinaryString(x));

			// right most set bit
			int rightOne = x & -x;
			System.out.println("rightOne = "+Integer.toBinaryString(rightOne));

			// reset the pattern and set next higher bit
			// left part of x will be here
			int nextHigherOneBit = x + rightOne;
			System.out.println("nextHigherOneBit = "+Integer.toBinaryString(nextHigherOneBit));

			// nextHigherOneBit is now part [D] of the above explanation.

			// isolate the pattern
			int rightOnesPattern = x ^ nextHigherOneBit;
			System.out.println("rightOnesPattern = "+Integer.toBinaryString(rightOnesPattern));


			// right adjust pattern
			rightOnesPattern = (rightOnesPattern)/rightOne;
			System.out.println("rightOnesPattern = "+Integer.toBinaryString(rightOnesPattern));


			// correction factor
			rightOnesPattern >>= 2;
			System.out.println("rightOnesPattern = "+Integer.toBinaryString(rightOnesPattern));


			// rightOnesPattern is now part [A] of the above explanation.

			// integrate new pattern (Add [D] and [A])
			next = nextHigherOneBit | rightOnesPattern;
			System.out.println("next = "+Integer.toBinaryString(next));

		}

		return next;
	}

	public static List<String> fullJustify(String[] words, int maxWidth) {
	    List<String> result = new ArrayList<String>();
	    if(words==null || words.length==0)      return result;
	    int count=0, last=0;
	    ArrayList<String> list = new ArrayList<String>();
	    for(int i=0; i<words.length; i++){
	        count = count + words[i].length();
	        if(count+i-last>maxWidth){
	            int wordsLen = count-words[i].length();
	            int spaceLen = maxWidth-wordsLen;
	            int eachLen = 1;
	            int extraLen = 0;
	            if(i-last-1>0){
	                eachLen = spaceLen/(i-last-1);
	                extraLen = spaceLen%(i-last-1);
	            }
	 
	            StringBuilder sb = new StringBuilder();
	            for(int k=last; k<i-1; k++){
	                sb.append(words[k]);
	                int ce = 0;
	                while(ce<eachLen){
	                    sb.append(" ");
	                    ce++;
	                }
	                if(extraLen>0){
	                    sb.append(" ");
	                    extraLen--;
	                }
	            }
	 
	            sb.append(words[i-1]);//last words in the line
	            //if only one word in this line, need to fill left with space
	            while(sb.length()<maxWidth){
	                sb.append(" ");
	            }
	 
	            result.add(sb.toString());
	            last = i;
	            count=words[i].length();
	        }
	    }
	 
	    int lastLen = 0;
	    StringBuilder sb = new StringBuilder();
	 
	    for(int i=last; i<words.length-1; i++){
	        count = count+words[i].length();
	        sb.append(words[i]+" ");
	    }
	 
	    sb.append(words[words.length-1]);
	    int d=0;
	    while(sb.length()<maxWidth){
	        sb.append(" ");
	    }
	    result.add(sb.toString());
	 
	    return result;
	}
	public static void main(String[] args) {

		String []s = {"This", "is", "an", "example", "of", "text", "justification."};
		int L = 16;
		System.out.println(fullJustify(s,L));
/*		System.out.println(next_greater(156));
		System.out.println(next_greater1(156));
		System.out.println((94 & -94));
		System.out.println();
		String a = "424524354";*/

	}
}
