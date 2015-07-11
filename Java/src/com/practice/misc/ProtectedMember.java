package com.practice.misc;
import com.practice.ds.StringPractice;

public class ProtectedMember extends StringPractice {

     void print(){
		System.out.println(tt);  // passes sicne tt has protected access.
		// System.out.println(t); // fails since t has default access
	}
	
}
