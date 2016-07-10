package com.practice.ds;

import java.util.Random;
class Nodee 
{
	public int item;
	public Nodee next;

	public Nodee(int val) 
	{
		item = val;
	}

	public Nodee() 
	{}

	public void displayNodee() 
	{
		System.out.print("[" + item + "] ");
	}
		
}

class LList 
{
	private Nodee first;

	public LList() 
	{
		first = null;
	}

	public boolean isEmpty() 
	{
		return (first == null);
	}

	public void insert(int val)
	{
		Nodee newNodee = new Nodee(val);
		newNodee.next = first;
		first = newNodee;
	}

	public void append(Nodee result) 
	{
		first = result;
	}

	public void display() 
	{
		Nodee current = first;
		while (current != null) 
		{
			current.displayNodee();
			current = current.next;
		}
		System.out.println("");
	}

	public Nodee extractFirst() 
	{
		return first;
	}

	public Nodee MergeSort(Nodee root) 
	{
		if (root == null || root.next == null) return root;
		Nodee a = root;
		Nodee b = root.next;
		while ((b != null) && (b.next != null)) 
		{
			root = root.next;
			b = (b.next).next;
		}
		b = root.next;
		root.next = null;
		return merge(MergeSort(a), MergeSort(b));
	}

	public Nodee merge(Nodee a, Nodee b) 
	{
		Nodee temp = new Nodee();
		Nodee head = temp;
		Nodee c = head;
		while ((a != null) && (b != null)) 
		{
			if (a.item <= b.item) 
			{
				c.next = a;
				c = a;
				a = a.next;
			}
			else 
			{
				c.next = b;
				c = b;
				b = b.next;
			}
		}
		c.next = (a == null) ? b : a;
		return head.next;
	}
}

public class MergeSortLL 
{
	public static void main(String[] args) 
	{
		LList object = new LList();
		Random random = new Random();
		int N = 20;
		for (int i = 0; i < N; i++)
			object.insert(Math.abs(random.nextInt(100)));

		System.out.println("List items before sorting :");
		object.display();
		object.append(object.MergeSort(object.extractFirst()));
		System.out.println("List items after sorting :");
		object.display();
	}
}

