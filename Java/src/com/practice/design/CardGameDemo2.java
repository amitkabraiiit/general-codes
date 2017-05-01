package com.practice.design;

import java.util.Random;

enum Suit{
	HEART, SPADE, CLUB, DIAMOND;
}
enum Rankk{
	ACE,KING,QUEEN,JACK,TEN,NINE,EIGHT,SEVEN,SIX,FIVE,FOUR,THREE,TWO;
}

class Card{
	Suit s;
	Rankk r;
	boolean available = true;
	Card(Suit s, Rankk r){
		this.s = s;
		this.r = r;
	}
	public void markUnavailable(){
		this.available = false;
	}
	public void markAvailable(){
		this.available = true;
	}
}

class Deck{
	Card [] deck = new Card[52];
	public Card[] getDeck(){
		return deck;
	}
	public void createDeck(){
		int i=0;
		for(Suit s : Suit.values()){
			for(Rankk r : Rankk.values()){
				deck[i++] = new Card(s,r);
			}
		}
		System.out.println(i);
	}
	public void printDeck(){
		for(int i=0;i<52;i++){
			System.out.println(deck[i].s+" "+deck[i].r);
		}
	}
	public void shuffle(){
		Random random = new Random();
		Card temp ;
		for(int i=0;i<52;i++){
			int r = random.nextInt(52);
			temp = deck[r];
			deck[r] = deck[52-r-1];
			deck[52-r-1]= temp;
		}
	}
	// TODO
	/*public void reorder(){
		for(Suit s : Suit.values()){
			for(Rankk r : Rankk.values()){
				deck[i++] = 
			}
		}
	}*/
}

public class CardGameDemo2 {

	public static void main(String[] args) {
		Deck d = new Deck();
		d.createDeck();
		d.printDeck();	
		System.out.println("============");
		d.shuffle();
		d.printDeck();
	}

}
