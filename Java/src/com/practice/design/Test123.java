package com.practice.design;

class Globals {
	public static int x = 37;
    public static String s = "aaa";
}
public class Test123 {

    public static void main(String args[])
    {

            int a[]={2,4,2,4,4,4,2,4,2};
            int b=a[0];
            for(int i=1;i<9;i++){
            	b=b^a[i];
            }
            System.out.println(b);
            
    }
}

