package com.practice.java.filereadwrite;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReadWriteUsingStream {
	public static void main(String[] args) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("/Users/akabra/GitCodeBase/general-codes/Java/src/com/practice/java/Annotation.java");
			out = new FileOutputStream("output.txt");
			/*
			 * BufferedInputStream doesn't have readLine function so reading character by character only.
			 */
			int c;
			while ((c = in.read()) != -1) {
				System.out.println((char)c); // doesn't output correctly
				out.write(c); // comes fine in the output file
			}
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}

