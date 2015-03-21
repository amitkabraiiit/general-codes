package com.practice.misc;

public class NumberSystem {

	public static void main(String[] args) {
    
		int x1 = 10; // 10 in decimal
        System.out.println( x1 );
        int x2 = 012; // 10 in octal
        System.out.println( x2 ); // prints 10 as we are storing it in int and there is nothing like Oct x = 012;
        int x3 = 0xb; // 10 in hexdecimal
        System.out.println( x3 ); // prints 11 we are storing it in int and there is nothing like hex x = 0xb;
        
        int x = 255;
        System.out.println(x);
        x = 0xFF; // or x = 0xff or x=0Xff , its case insensitive
        System.out.println(x); // 255
        System.out.println(Integer.toHexString(x)); // ff not 0xff since Java doesn't store any 'formatting' in primitives. 

       // int i = 12345678910; // literal of type 12345678910 is out of range
       // long i = 12345678910; // literal of type 12345678910 is out of range
          long i = 12345678910L; // suffix  l or L is mandatory for long if its overflow case
          System.out.println(i); // 12345678910
          long t = 5; // no error
          System.out.println(t); // 5
          /*
          There are specific suffixes for long (e.g. 39832L), float (e.g. 2.4f) and double (e.g. -7.832d).
          If there is no suffix, and it is an integral type (e.g. 5623), it is assumed to be an int. 
          If it is not an integral type (e.g. 3.14159), it is assumed to be a double.In all other 
          cases (byte, short, char), you need the cast as there is no specific suffix.The Java spec allows 
          both upper and lower case suffixes, but the upper case version for longs is preferred, 
          as the upper case L is less easy to confuse with a numeral 1 than the lower case l.
          */

          int y = 0x7fffffff; // print y will print 2147483647
       //   int j = 0x7ffffffff; // literal of type 12345678910 is out of range
          
          String z = "0xf";
          // y = Integer.parseInt(z);// java.lang.NumberFormatException: For input string: "0xf"
          //y = Integer.parseInt(z,16);// works when z = "f" but when z = "0xf" it gives error : java.lang.NumberFormatException: For input string: "0xf"
          y = Integer.decode(z);
          System.out.println(y);
          
          /*
           Hexadecimal is : 0 1 2 ... 9 a b c d e f
           Why use hexadecimal? Well, simply because hexadecimal is more "in tune" with various uses and instances of numbers
           in computing. The divisions between hexadecimal digits correspond more closely to the divisions between the 
           bytes making up that number when it is stored. The number 1024— the multiplier for going from byte to 
           kilobyte to megabyte etc— becomes 400 in hex and so values such as 2048 (=2K) which look a little "awkward" or 
           "hard to judge" in decimal look simpler in hex (2048 decimal = 800 hex, for example).
           */
	}

	
	
}
