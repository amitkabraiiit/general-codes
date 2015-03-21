package com.practice.sorting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;


// Source : http://exceptional-code.blogspot.in/2011/07/external-sorting-for-sorting-large.html

public class ExternalSort
{
	static int N = 200; // size of the file in disk
	static int M = 10; // max items the memory buffer can hold

	//static int N = 2000000; // size of the file in disk
	//static int M = 100000; // max items the memory buffer can hold
	
	
	public static void externalSort(String fileName)
	{
		/*
		 * Two step process
		 * 1. Sort Phase
		 * 2. Merge Phase
		 */
		String tfile = "temp-file-";
		int[] buffer = new int[M < N ? M : N];

		try
		{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			int slices = (int) Math.ceil((double) N/M); // 200/10 = 20 slices

			int i, j;
			i = j = 0;
			// Iterate through the elements in the file
			/*
			 * 1. Read 10 numbers in each of 20 slices into buffer of size 10
			 * 2. Sort the numbers in buffer.
			 * 3. Write the buffer in temp-file-slice1.txt file.
			 * Note1 : We are reading from disk
			 * Note2 : buffer of size 10 is the maximum memory can hold. Buffer is in memory
			 * Note3 : file write is write into the disk
			 */
			for (i = 0; i < slices; i++)
			{
				// Read M-element chunk at a time from the file
				for (j = 0; j < (M < N ? M : N); j++)
				{
					String t = br.readLine(); // here we are reading from the main file of size 200 numbers line by line.
					if (t != null)
						buffer[j] = Integer.parseInt(t);
					else
						break;
				}
				// Sort M elements
				Arrays.sort(buffer);


				// Write the sorted numbers to temp file
				FileWriter fw = new FileWriter(tfile + Integer.toString(i) + ".txt");
				PrintWriter pw = new PrintWriter(fw);
				for (int k = 0; k < j; k++)
					pw.println(buffer[k]);
				pw.close();
				fw.close();
			}

			br.close();
			fr.close();

			/*
			 * Till now we have 20 slice files each sorted in itself and they are stored on disk.
			 * 1. Read the first line of each of 20 slices and store in the topNums array. This is done in the 1st for loop.
			 * 2. Open a file in disk to write the final result to.
			 * 3. Find the min number and the file from which it is read and from the topNum array and write that 
			 *    min number to the file opened in step2. Also read the next number from the file from which min number
			 *    has been read and store it in topNums array back.
			 * 4. Close all the files.
			 */
			
			// Now open each file and merge them, then write back to disk
			int[] topNums = new int[slices];
			BufferedReader[] brs = new BufferedReader[slices];

			for (i = 0; i < slices; i++)
			{
				brs[i] = new BufferedReader(new FileReader(tfile + Integer.toString(i) + ".txt"));
				String t = brs[i].readLine();
				if (t != null)
					topNums[i] = Integer.parseInt(t);
				else
					topNums[i] = Integer.MAX_VALUE;
			}

			FileWriter fw = new FileWriter("E:\\test\\external-sorted.txt");
			PrintWriter pw = new PrintWriter(fw);

			for (i = 0; i < N; i++)
			{
				int min = topNums[0];
				int minFile = 0;

				for (j = 0; j < slices; j++)
				{
					if (min > topNums[j])
					{
						min = topNums[j];
						minFile = j;
					}
				}

				pw.println(min);
				String t = brs[minFile].readLine();
				if (t != null)
					topNums[minFile] = Integer.parseInt(t);
				else
					topNums[minFile] = Integer.MAX_VALUE;

			}
			for (i = 0; i < slices; i++)
				brs[i].close();

			pw.close();
			fw.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}


	static String generateInput(int n)
	{
		String fileName = "external-sort.txt";
		Random rand = new Random();

		try
		{
			FileWriter fw = new FileWriter(fileName);
			PrintWriter pw = new PrintWriter(fw);

			for (int i = 0; i < n; i++)
				// Generate numbers less than 200
				pw.println(rand.nextInt(200));

			pw.close();
		}

		catch (IOException e)
		{
			e.printStackTrace();
		}

		return fileName;
	}

	public static void main(String[] args)
	{
		String fileName = generateInput(N);
		externalSort(fileName);
	}
}
