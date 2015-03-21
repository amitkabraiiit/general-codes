package org.karticks.mapreduce.util;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class GenerateNumbersFile
{
	public static final String OUTPUT_FILENAME = "integers_to_be_sorted.txt";
	
	public static final String[] numbers = {"34", "51", "92", "78", "12", "17", "29", "55", "47", "80",
		"66", "30", "24", "82", "28", "75", "59", "46", "99", "61"};
	
	public static final int NUM_INTS = 5000;
	
	public static void main(String[] args)
	{
		try
		{
			OutputStream os = new FileOutputStream(OUTPUT_FILENAME);
			
			for (int i = 0; i < NUM_INTS; i++)
			{
				double random = Math.random();
				
				int index = (int) Math.round(random * (numbers.length - 1));
				
				os.write(numbers[index].getBytes());
				os.write(" ".getBytes());
				os.flush();
			}
			
			os.close();
		}
		catch (Throwable t)
		{
			System.out.println("Caught an exception. Error message : " + t.getMessage());
			t.printStackTrace();
		}
	}
}
