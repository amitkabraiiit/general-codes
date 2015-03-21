package org.amit.mapreduce;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class implements the Reduce phase of the Map-Reduce design pattern.
 * The class has only one method (<code>doReduce</code>) which takes the results (as a <code>List</code>
 * of <code>Maps</code>) from the Map phase and produces the final result (also as a <code>Map</code>).
 * 
 * @author Kartick Suriamoorthy
 *
 */
public class Reducer
{
	/**
	 * Executes the Reduce phase of the Map-Reduce pattern by iterating over
	 * all the input <code>Maps</code> to find common keys and aggregating their results.
	 * Stores and returns the final results in the output <code>Map</code>.
	 * 
	 * @param inputMaps A list of results from Map phase
	 * @return A <code>Map</code> that contains the final result
	 */
	public Map<String, Integer> doReduce(List<Map<String, Integer>> inputMaps)
	{
		// decided to use a tree-map as it easier to output the final results
		// as all the keys are sorted. this can be easily replaced with a
		// hashtable or hashmap with no adverse effects.
		Map<String, Integer> outputMap = new TreeMap<String, Integer>();
		
		int mapIndex = 0;
		
		// outer loop - iterate over all maps
		for (Map<String, Integer> map : inputMaps)
		{
			mapIndex++;
			
			Iterator<String> it = map.keySet().iterator();
			
			while (it.hasNext())
			{
				String key = it.next();
		
				// Get the value from the current map
				Integer value = map.get(key);
				
				// Now iterate over the rest of maps. The mapIndex variable starts
				// at 1 and keeps increasing because once we are done with all the
				// keys in the first map, we don't need to inspect it any more, and
				// the same holds for the second map, third map, and so on.
				for (int j = mapIndex; j < inputMaps.size(); j++)
				{
					Integer v = inputMaps.get(j).get(key);
					
					// if you find a value for the key, add it to the current value
					// and then remove that key from that map.
					if (v != null)
					{
						value += v;
						inputMaps.get(j).remove(key);
					}
				}
				
				// finished aggregating all the values for this key, now store it
				// in the output map
				outputMap.put(key, value);
			}	
		}
		
		return outputMap;
	}
}
