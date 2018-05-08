package com;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test2 
{
	private static final Map<Character, Integer> encodings = new HashMap<>();
	
	static { 
		encodings.put('A',0); 
		encodings.put('C',1); 
		encodings.put('G',2); 
		encodings.put('T',3); 
	}
	private final int TWO_POW_9 = (int) Math.pow(2, 9);

	public List<String> findRepeatedDnaSequences(String s) {

		Set<String>  res = new HashSet<>();

		HashMap<Integer, String> duplicates = new HashMap<Integer, String>();

		for (int i = 0, rhash = 0; i < s.length(); i++) 
		{
			if (i > 9) rhash = rhash - TWO_POW_9 * encodings.get(s.charAt(i-10));

			rhash = 2 * rhash + encodings.get(s.charAt(i));
			System.out.println("i = "+i+" character = "+s.charAt(i)+" rhash = "+rhash);
			if (i > 8) 
			{
				if (duplicates.get(rhash) != null)
				{
					res.add(s.substring(i-9,i+1));
				}
				else
				{
					duplicates.put(rhash, "");
				}
			}
		}
		return new ArrayList<>(res);
	}

	public static void maxProfit() {
		int[] prices = {1, 4 ,5 ,7 ,6 ,3 ,2 ,9};
	    int max1 = 0, max2 = 0;
	    for(int i=1; i<prices.length; i++){
	        int diff = prices[i]-prices[i-1];
	        if(diff > 0){
	        	if ( diff > max1) max1 = diff;
	        	if ( diff < max1 && diff > max2) max2 = diff;
	        }
	    }
	    System.out.println(max1 + max2);
	}
	
	public static void main(String[] args)
	{
		Test2 sln = new Test2();
/*		List<String> list = sln.findRepeatedDnaSequences("AACAAAAACAAAACCAAAAACAAAAACAAAA");

		System.out.println("Repeated DNA sequences are: ");
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}*/
		maxProfit();
	}
}