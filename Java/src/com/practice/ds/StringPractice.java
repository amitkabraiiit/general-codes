package com.practice.ds;


public class StringPractice {

	int t = 0; // these varibles t and tt and function print are not relevant to this class.
			   // added to test functionality of ProtectedMember.java class
	protected int tt = 0; 
    void print(){
    	System.out.println("test");
	}
	
	public static boolean contains(char a[], char c){
		for(int i=0;i< a.length;i++){
			if(a[i] == c)return true;
		}
		return false;
	}

	public static void main(String[] args) {

		StringPractice sp = new StringPractice();
		String a = "amitmtak";
		for(int i=0;i<a.length();i++)
		{
			if(i+1== a.length())break;
			String s = a.substring(i+1);
			boolean abcd = contains(s.toCharArray() , a.charAt(i));
			if(abcd == false){
				System.out.println("answer:"+a.charAt(i));
				break;
			}
		}
	}
}


