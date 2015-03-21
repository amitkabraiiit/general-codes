package org.amit.mapreduce;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple test application that runs Map-Reduce on a set of Strings to produce
 * a word count.
 * 
 * @author Kartick Suriamoorthy
 */
public class WordCounter
{
	public static void main(String[] args)
	{
		
		/* Overall : 
		 * 1. There is one mapper class(WordCountMapper) that implements Mapper interface(Mapper.java).
		 * 2. There is one reducer class ( Reducer.java)
		 * 3. This class triggers the the map-reduce program. It create 5 mappers out of 5 strings (inputs) and pass both string and corresponding mapper to
		 * 4. MapReduceWork.java which do all map-reduce work.
		 * 5. MapReduceWork.java takes all mappers and store them in a list and then when doWork is called on it, it spawn one thread per mapper and wait for its
		 *    completion. Once all threads are finished it calls reducer and passes it maplist. Reducer outputs the final list.
		 *    --> Individual mapper thread : it converts input string to map where map <work:frequency>
		 *    --> Reducer : from maplist , loop over all maps , from each map take keys one by one and see that key in other maps, if found it increses the 
		 *                  value of the key by that value and delete the key from that map and stores the final output in a new map. Once all maps are done,
		 *                  it returns the output map.
		 */
		
		
		String[] inputs = {
				"Guy Montag was a fireman whose job it was to start fires The system was simple Everyone understood it Books were",
				"for burning along with the houses in which they were hidden Guy Montag enjoyed his job He had been a fireman for",
				"ten years and he had never questioned the pleasure of the midnight runs nor the joy of watching pages consumed by flames",
				"never questioned anything until he met a seventeen year old girl who told him of a past when people were not afraid Then",
				"he met a professor who told him of a future in which people could think and Guy Montag suddenly realized what he had to do" };
		
		MapReduceWorker worker = new MapReduceWorker();
		
		// create a mapper for every string
		for (String s : inputs)
		{
			byte[] bytes = s.getBytes();
			InputStream is = new ByteArrayInputStream(bytes);
			
			Mapper mapper = new WordCountMapper();
			
			worker.addMapper(mapper, is);
		}
		
		// get the final result from map-reduce
		Map<String, Integer> result = worker.doWork();
		
		// iterate over the keys
		Iterator<String> iterator = result.keySet().iterator();
		
		String output = "";
		while (iterator.hasNext())
		{
			String key = iterator.next();
			
			Integer value = result.get(key);
			
			output += key + " (" + value + "), ";
		}
		
		// validate the results from the previous run
		StringBuffer buffer = new StringBuffer();
		for (String s : inputs)
		{
			buffer.append(s);
			buffer.append(" ");
		}
		String masterString = buffer.substring(0, buffer.length() - 1);
		
		MapReduceWorker newWorker = new MapReduceWorker();
		newWorker.addMapper(new WordCountMapper(), new ByteArrayInputStream(masterString.getBytes()));
		
		result = newWorker.doWork();
		iterator = result.keySet().iterator();
		
		String newOutput = "";
		while (iterator.hasNext())
		{
			String key = iterator.next();
			
			Integer value = result.get(key);
			
			newOutput += key + " (" + value + "), ";
		}
		
		System.out.println("First Output : " + output);
		System.out.println("Second Output : " + newOutput);
		
		boolean test = output.equals(newOutput);
		
		if (test)
		{
			System.out.println("Both the outputs are same !!");
		}
		else
		{
			System.out.println("Both the outputs are not the same :-(");
		}
	}
}
