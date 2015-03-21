package com.practice.misc;



public class Test{

	static int max(int x, int y)
	{ return (y > x)? y : x; }

	// {-2, -3, 4, -1, -2, 1, 5, -3};
	static int maxSubArraySum(int a[], int size)
	{
		int max_so_far = a[0], i;
		int curr_max = a[0];

		for (i = 1; i < size; i++)
		{
			curr_max = max(a[i], curr_max+a[i]);
			max_so_far = max(max_so_far, curr_max);
		}
		return max_so_far; // will return 7
	}

	public static void main(String[] args) {
		int a[] =  {-2, -3, 4, -1, -2, 1, 5, -3};
		int max_sum = maxSubArraySum(a, a.length);
		System.out.println("Maximum contiguous sum is: "+ max_sum);
	}
}