package org.karticks.mapreduce.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RemovePunctuation
{
	public static int[] chars = {'!', ',', ';', ':', '_', '-','\'', '+', '*', '?', ')', '(', '/', '\"', '&', '.'};
	
	public static String removePunctuation(InputStream is)
	{
		try
		{
			BufferedInputStream buis = new BufferedInputStream(is);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int b = -1;
			
			while (true)
			{
				boolean matchedPunctuation = false;
				
				b = buis.read();
				
				if (b == -1)
				{
					break;
				}
				else
				{
					for(int c : chars)
					{
						if (c == b)
						{
							matchedPunctuation = true;
							continue;
						}
					}
					
					if (!matchedPunctuation)
					{
						if ((b == '\r') || (b == '\n'))
						{
							baos.write(' ');
						}
						else
						{
							baos.write(b);
						}
					}
				}
			}
			
			baos.flush();
			
			String s = baos.toString();
			
			baos.close();
			
			return s;
		}
		catch (IOException ioe)
		{
			System.out.println("Caught an IOException while removing punctuation. Error message : " + ioe.getMessage());
			throw new RuntimeException("Error while removing punctuation.", ioe);
		}
	}
	
	public static void main(String args[])
	{
		try
		{
			String output = "";
			
			String inputFile = args[0];
			String outputFile = args[1];
						
			InputStream is = new FileInputStream(inputFile);
			
			output = removePunctuation(is);
			
			OutputStream os = new FileOutputStream(outputFile);
			
			os.write(output.getBytes());
			os.flush();
			
			is.close();
			os.close();
			
			System.out.println("Removed punctuation from " + inputFile + " and wrote it to " + outputFile + ".");
		}
		catch (Throwable t)
		{
			System.out.println("Caught an exception. Error message : " + t.getMessage());
			t.printStackTrace();
		}
	}
}
