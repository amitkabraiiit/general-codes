package com.practice.dp;

import java.util.Arrays;
import java.util.Comparator;

public class MaxStackHeight {
	/* Representation of a box */
	static class Box
	{
		int h, w, d;  // for simplicity of solution, always keep w <= d
		public Box(int h,int w,int d) {
			this.h = h;
			this.w = w;
			this.d = d;
		}
		public Box()
		{			
			this.h = 0;
			this.w = 0;
			this.d = 0;
		}
		public String toString(){
			return "h*w*d"+h+w+d;
		}
	}

	static int min (int x, int y)	{ return (x < y)? x : y; }
	static int max (int x, int y)	{ return (x > y)? x : y; }

	/* Returns the height of the tallest stack that can be formed with give type of boxes */
	static int maxStackHeight( Box arr[], int n )
	{
		/* Create an array of all rotations of given boxes
	      For example, for a box {1, 2, 3}, we consider three
	      instances{{1, 2, 3}, {2, 1, 3}, {3, 1, 2}} */
		Box[] rot = new Box[3*n];
		for(int i=0;i< 3*n ; i++) rot[i] = new Box();
		int index = 0;

		for (int i = 0; i < n; i++)
		{
			// Copy the original box
			rot[index] = arr[i];
			index++;
			// First rotation of box
			rot[index].h = arr[i].w;
			rot[index].d = max(arr[i].h, arr[i].d);
			rot[index].w = min(arr[i].h, arr[i].d);
			index++;

			// Second rotation of box
			rot[index].h = arr[i].d;
			rot[index].d = max(arr[i].h, arr[i].w);
			rot[index].w = min(arr[i].h, arr[i].w);
			index++;
		}

		Arrays.sort(rot, new Comparator<Box>() {
			@Override
			public int compare(Box a, Box b) {
				return (b.d * b.w) - (a.d *a.w) ;
			}
		});

		// Now the number of boxes is 3n
		n = 3*n;

		// Uncomment following two lines to print all rotations
		for (int i = 0; i < n; i++ )
			System.out.println("h x w x d --> "+ rot[i].h+"x"+ rot[i].w +"x"+ rot[i].d);

		/* Initialize msh values for all indexes 
	      msh[i] –> Maximum possible Stack Height with box i on top */
		int [] msh = new int [n];
		for (int i = 0; i < n; i++ ){
			msh[i] = rot[i].h;
		}

		/* Compute optimized msh values in bottom up manner */
		for (int i = 1; i < n; i++ )
			for (int j = 0; j < i; j++ )
				if ( rot[i].w < rot[j].w &&	rot[i].d < rot[j].d && msh[i] < msh[j] + rot[i].h)
				{
					if(i==2){
						System.out.println("----------> "+msh[j]);
					}
					msh[i] = msh[j] + rot[i].h;
				}
		int max = -1;
		for ( int i = 0; i < n; i++ ){
			if ( max < msh[i] )
				max = msh[i];
			System.out.println("i:"+i+" -> "+msh[i]);
		}

		return max;
		
//		for (int i = 1; i < n; i++ )
//			for (int j = i-1; j >=0; j-- )
//				if ( rot[i].w < rot[j].w &&	rot[i].d < rot[j].d)
//				{
//					msh[i] = msh[j] + rot[i].h;break;
//				}
//		for ( int i = 0; i < n; i++ ){
//			System.out.println("i:"+i+" -> "+msh[i]);
//		}
//		return msh[n-1];
		
	}

	/* Driver program to test above function */

	public static void main(String[] args) {

		Box [] arr = { new Box(4, 6, 7), new Box(1, 2, 3), new Box(4, 5, 6), new Box(10, 12, 32) };
		System.out.println("The maximum possible height of stack is "+maxStackHeight (arr, arr.length) );

	}


}
