package com.practice.designpattern.behavioral;

import java.util.ArrayList;

interface Visitor {
	public void visit(City city);
	public void visit(Museum museum);
	public void visit(Park park);
}
 
class FirstTimeVisitor implements Visitor {
 
	@Override
	public void visit(City city) {
		System.out.println("I'm visiting the city!");
	}
 
	@Override
	public void visit(Museum museum) {
		System.out.println("I'm visiting the " + museum.getMuseamName()+" Museum !");
	}
 
	@Override
	public void visit(Park park) {
		System.out.println("I'm visiting the Park!");
	}
}
 
interface Element {
	public void accept(Visitor visitor);
}
 
class City implements Element {
 
	ArrayList<Element> places = new ArrayList<Element>();
 
	public City() {
		places.add(new Museum("tiger"));
		places.add(new Park("green"));
	}
 
	@Override
	public void accept(Visitor visitor) {
		System.out.println("City is accepting visitor.");
		visitor.visit(this);
 
		for (Element e : places) {
			e.accept(visitor);
		}
	}
}
 
class Museum implements Element {
	
	String name;
	public Museum(String name) {
		this.name = name;
	}
	
	public String getMuseamName(){
		return name;
	}
	
	@Override
	public void accept(Visitor visitor) {
		System.out.println("Museum is accepting visitor.");
		visitor.visit(this);
	}
}
 
class Park implements Element {
	
	String name;
	public Park(String name) {
		this.name = name;
	}
	
	public String getParkName(){
		return name;
	}
	
	@Override
	public void accept(Visitor visitor) {
		System.out.println("Park is accepting visitor.");
		visitor.visit(this);
	}
 
}
 
public class VisitorDemo {
	public static void main(String[] args) {
		FirstTimeVisitor visitor = new FirstTimeVisitor();
		//City city = new City();
		//city.accept(visitor);
		Museum sharkMuseum = new Museum("shark");
		sharkMuseum.accept(visitor);
	}
}
