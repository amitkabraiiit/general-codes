package com.practice.java8;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Practice {
	
	static void stringToBinary() throws UnsupportedEncodingException {
		byte[] bytes = "hello".getBytes("UTF-8");
		String string = new String(bytes, "UTF-8");	
	}
	
	static void utfEncoding() throws UnsupportedEncodingException{
		String str = "This is my Sample application";
		String urlEncodedData = URLEncoder.encode(str, "UTF-8"); // Encoding with UTF-8
		System.out.println("URL Encoded String : "+urlEncodedData );
		String retrievedData = URLDecoder.decode(urlEncodedData , "UTF-8");// Decoding with UTF-8
		System.out.println("URL decoded string : "+retrievedData +"\n"); 

	}
	static void base64Encoding(){
		byte[] message = "hello world".getBytes(StandardCharsets.UTF_8);
		String encoded = Base64.getEncoder().encodeToString(message);
		byte[] decoded = Base64.getDecoder().decode(encoded);

		System.out.println("Base64 encoded string : "+encoded);
		System.out.println("Base64 decoded string : "+new String(decoded, StandardCharsets.UTF_8));
	}
	static void base64EncodingNotSupportingCharacters(){
		
		/*
		 * The basic decoder rejects (and throws and IllegalArgumentException) data that contains characters that are 
		 * not contained in the base64 alphabet like ö, ä or ñ. We can see an example (again, is just an example, no practical use):
		 */
		
		String str = "España va muy bien and German uses ö, ä and ü";
		byte[] decodedStr = Base64.getDecoder().decode( str );
		// will output "Exception in thread "main" java.lang.IllegalArgumentException: Illegal base64 character -f"


	}


	public static void main(String[] args) throws UnsupportedEncodingException {
		stringToBinary();
		utfEncoding();
		base64Encoding();
		base64EncodingNotSupportingCharacters();
	}

}
