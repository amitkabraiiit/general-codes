package com.practice.misc;

public class MyBookAbstractClass {

	public class Test{
		abstract class Book
		{
			String title;
			String author;
			Book(String t,String a){
				title=t;
				author=a;
			}
			abstract void display();


		}
		class MyBook extends Book{ // WE EXTEND AN ABSTRACT CLASS AND IMPLEMENT AN INTERFACE
			String title;
			String author;
			int price ;
			MyBook(String title, String author,int price){ 
				super(title,author); // THIS IS MUST
				this.title = title;
				this.author = author;
				this.price = price;
			}
			void display(){
				System.out.println("Title: "+this.title);
				System.out.println("Author: "+this.author);
				System.out.println("Price: "+this.price);

			}
		}
	}

}
