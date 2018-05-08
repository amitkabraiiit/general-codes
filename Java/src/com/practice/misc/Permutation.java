package com.practice.misc;

public class Permutation
{
	static int n = 0;
	public static void main(String[] args)
	{
		String str = "ABC";
		n = str.length();
		Permutation permutation = new Permutation();
		permutation.permute(str, 0);
	}

	/**
	 * permutation function
	 * @param str string to calculate permutation for
	 * @param l starting index
	 * @param r end index
	 */
	private void permute(String str, int l)
	{
		if (l == n-1)
			System.out.println(str);
		else
		{
			for (int i = l; i <= n-1; i++)
			{
				str = swap(str,l,i);
				permute(str, l+1);
				str = swap(str,l,i);
			}
		}
	}

	/**
	 * Swap Characters at position
	 * @param a string value
	 * @param i position 1
	 * @param j position 2
	 * @return swapped string
	 */
	public String swap(String a, int i, int j)
	{
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i] ;
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

}

