package org.amit.mapreduce;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

/**
 * Implements the Mapper interface to do word counts. Does absolutely no processing
 * on the stream i.e. assumes that there are no punctuation marks or line endings.
 * 
 * @author Kartick Suriamoorthy
 */
public class WordCountMapper implements Mapper
{
	/**
	 * Reads the InputStream and parses words out of the stream. Assumes that the
	 * words are just separated by spaces (" ") and that there are no punctuation
	 * marks or line endings. Converts all the words into lower case and stores
	 * them (and their counts) in the resulting Map.
	 */
	public Map<String, Integer> doMap(InputStream is)
	{
		Map<String, Integer> map = new Hashtable<String, Integer>();
	
		// reads the input stream into a String
		String s = readInputStream(is);
		
		// splits the string int words (using the space character as the separator)
		String[] words = s.split("\\s+");
		
		// iterates over the words and stores them in hashtable
		for (String word : words)
		{
			if (word.length() > 0)
			{
				word = word.toLowerCase();
				
				Integer value = map.get(word);
				 
				if (value == null)
				{
					// the word is being inserted into the hashtable for the first time
					map.put(word, new Integer(1));
				}
				else
				{
					// the word already exists in the hashable, so increment its counter
					// by one, and then insert it back into the hashtable
					value++;
					map.put(word, value);
				}
			}
		}
		
		return map;
	}
	
	// reads a input stream into a string. assumes that the source stream is
	// not too big that it cannot be stored in a String (and hence does not
	// take into account out-of-memory conditions)
	private String readInputStream(InputStream is)
	{
		try
		{
			BufferedInputStream buis = new BufferedInputStream(is);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[512];
			int read = 0;
			
			while (true)
			{
				read = buis.read(buffer, 0, buffer.length);
				
				// check for end of stream
				if (read == -1)
				{
					break;
				}
				else
				{
					baos.write(buffer, 0, read);
				}
			}
			
			baos.flush();
			
			String s = baos.toString();
			
			baos.close();
			
			return s;
		}
		catch (IOException ioe)
		{
			throw new RuntimeException("Error while reading from input stream. Error message : " + ioe.getMessage(), ioe);
		}		
	}

}
