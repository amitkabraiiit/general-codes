package com.practice.java.filereadwrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWrite {

	public static void main(String[] args) throws IOException {
		
		/*
		 * This is the most common way of reading / writing a file in java
		 */
		
		FileReader fread  = new FileReader("/Users/akabra/GitCodeBase/general-codes/Java/src/com/practice/java/Annotation.java");
		FileWriter fwrite  = new FileWriter("output.txt");
		/*
		 * FileReader (for text files) should usually be wrapped in a BufferedFileReader. 
		 * This saves up data so you can deal with it a line at a time or whatever instead of character by character (which usually isn't much use). 
		 */
		BufferedReader bread = new BufferedReader(fread);
		BufferedWriter bwrite = new BufferedWriter(fwrite);
		String line = bread.readLine();
		while(line!= null){
			System.out.println(line);
			bwrite.write(line);
			line = bread.readLine();
		}
		bread.close();
		bwrite.close();
		
	}
	
}
