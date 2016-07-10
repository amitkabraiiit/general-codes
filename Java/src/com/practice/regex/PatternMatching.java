package com.practice.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatching {

	public void isLessThenThreeHundred(){
		System.out.println("Is number less than 300");
		String s[] = {"288","3288","328 8","1","99","300"};
		for(String t : s)	System.out.println(Pattern.matches("[12]?[0-9]?[0-9]",t));

	}

	public void isIP(){
		System.out.println("Is string an ip");
		//String subp = "([01]?[0-9]?[0-9]?|2[01234][0-9]?|25[0-5]?)";
		String subp = "([01]?\\d?\\d|2[01234]\\d?|25[0-5]?)";
		String pattern = subp+"."+subp+"."+subp+"."+subp;
		String IP = "122.23";
		System.out.println(Pattern.matches(pattern, IP));

	}

	public void backReference(){
		System.out.println("inside backreferences");
		System.out.println("a a".replaceAll("(\\w)(\\s+)(\\w)", "$1$3"));  // Removes whitespace between two characters	
		System.out.println("<title>amit</title>".replaceAll("(?i)(<title.*?>)(.+?)(</title>)", "$2")); // This example extracts the text between a title tag. 
	}

	public void removeDuplicate(){
		System.out.println("finding duplicates");
		String pattern = "\\b(\\w+)(\\s+\\1\\b)*";
		Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		String input = "amit is is a a a good boy";
		Matcher m = r.matcher(input);
		while(m.find( )){
			System.out.println("input : "+input);
			System.out.println("group 0 : "+ m.group());
			System.out.println("group 1 : " + m.group(1));
			System.out.println("group 2 : " + m.group(2));
			input = input.replaceAll(m.group(0),m.group(1));
		}
		System.out.println(input);
		
	}


public static void main(String[] args) {
/*	System.out.println("Is the string an url");
	System.out.println(Pattern.matches("(http[s]?|ftp)://.*","https://google.com/abcd" )); 
	new PatternMatching().isLessThenThreeHundred(); // number should be less than 300
	new PatternMatching().isIP();
	new PatternMatching().backReference();
	new PatternMatching().removeDuplicate();*/
	System.out.println(Pattern.matches("^[a-zA-Z][0-9a-zA-Z_]{7,29}","ct^4&t%j+V+-0U#~%F9=ZHXUYr" ));

}

}
