package com.practice.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

enum Color{
	HEART("heartRed") , 
	SPADE("spadeBlack"), 
	CLUB("clubBlack"), 
	DIAMOND("diamondRed");
	private String color;
	Color(String color){
		this.color = color;
	}
}

interface ISuite{
	public Color getColor();
	public void setColor(Color color);
	public void printSuite();
	public List<String> getCards();
}

class Suite implements ISuite {
	Color color;
	List<String> cards = null;
	public Suite(Color color){
		this.color = color;
		cards = Arrays.asList(
				"A"+" of "+color, 
				"K"+" of "+color,
				"Q"+" of "+color,
				"J"+" of "+color,
				"10"+" of "+color,
				"9"+" of "+color,
				"8"+" of "+color,
				"7"+" of "+color,
				"6"+" of "+color,
				"5"+" of "+color,
				"4"+" of "+color,
				"3"+" of "+color,
				"2"+" of "+color
				);
	}
	public void printSuite(){
		System.out.println("Suite color "+color);
		for(String card : cards){
			System.out.println(card);
		}
	}

	public Color getColor(){
		return color;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public List getCards(){
		return cards;
	}
}

class CardGame{
	ISuite heart; 
	ISuite spade; 
	ISuite club; 
	ISuite diamond;
	List<String> allCards = new ArrayList<String>();
	public CardGame(){
		heart = new Suite(Color.HEART);
		spade = new Suite(Color.SPADE);
		club = new Suite(Color.CLUB);
		diamond = new Suite(Color.DIAMOND);
		for(ISuite s : Arrays.asList(heart,spade,club,diamond)){
			allCards.addAll(s.getCards());
		}
	}
	public void print(){
		for(int i=0;i<52;i++){
			System.out.println(allCards.get(i));
		}
	}
	public void shuffle(){
		Random random = new Random();
		int r;
		String temp1, temp2;
		for(int i=0;i<52;i++){
			r = random.nextInt(52);
			temp1 = allCards.get(i);		
			temp2 = allCards.get(r);
			allCards.set(i, temp2);
			allCards.set(r, temp1);
		}
	}
}

public class CardGameDemo {

	public static void main(String[] args) {
		CardGame d = new CardGame();
		d.print();	
		System.out.println("============");
		d.shuffle();
		d.print();
	}

}
