package com.practice.design;

public class TinyURL {
	private static final String ALPHABET_MAP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" ;
	private static final int BASE = ALPHABET_MAP.length() ;

	public static String encode ( int toEncode ) {
		StringBuilder sb = new StringBuilder() ;

		System.out.println("Base : "+BASE);
		while ( toEncode > 0 ) {
			System.out.println("========");
			System.out.println("toEncode is :"+toEncode);
			System.out.println("toEncode % BASE , i.e "+toEncode+" %"+ BASE+" is : "+toEncode % BASE);
			System.out.println("Encoded string till now :"+ALPHABET_MAP.charAt ( toEncode % BASE ));
			sb.append ( ALPHABET_MAP.charAt ( toEncode % BASE ) ) ;		
			toEncode /= BASE ;
		}
		return sb.reverse().toString() ;
	}

	public static int decode ( String str ) {
		int Num = 0 ;

		for ( int i = 0, len = str.length(); i < len; i++ ) {
			Num = Num * BASE + ALPHABET_MAP.indexOf ( str.charAt(i) ) ;
		}
		return Num ;
	}

	public static void main ( String[] args ) {
		System.out.println(encode(123)) ; // prints b9
		System.out.println(decode("b9")) ; // prints 123
	}
}

