package com.practice.design;

import java.util.Scanner;
import java.util.Stack;

class Globals {
	public static int x = 37;
    public static String s = "aaa";
}
public class Test123 {

    public boolean isValid(String s) {
        int length=s.length(); 
        char [] array=s.toCharArray();
        if(length==0) return true; 
        Stack<Character> stack=new Stack<Character>(); 
        for(int i=0; i<length; i++)
        {
          if(array[i]=='(' || array[i]=='{' || array[i]=='[' )
          {
            stack.push(array[i]);
          }
          if(array[i]==')' ||array[i]=='}' ||array[i]==']')
          {
            if(stack.isEmpty()) return false; 
            char temp=stack.pop(); 
            if((temp=='(' && array[i]==')' ) || (temp=='{' && array[i]=='}' ) ||(temp=='[' && array[i]==']' )  )
            {
                continue;
            }else
            {
                return false; 
            }
          }
        }
        if(!stack.isEmpty()) return false; 
        return true;        
      }
      
      public static void main(String args[] ) throws Exception {
          /* Enter your code here. Read input from STDIN. Print output to STDOUT */
          Scanner sc = new Scanner(System.in);  
          int n = Integer.parseInt(sc.nextLine()); 
          Test123 sol = new Test123();
          for(int i=0 ; i < n ; i++){
              String s = sc.nextLine();
              if(sol.isValid(s))System.out.println("Valid");
              else System.out.println("Not valid");
          }
      }
}

