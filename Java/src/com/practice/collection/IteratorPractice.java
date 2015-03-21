package com.practice.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorPractice {

	/*
	 * Whenever we create collection class , its a good habit to write its iterator
	 * Hence, any class that wants to be iterable, it needs to emit an Iterator object and should contains a method named iterator() that returns Iterator.
	 * To enforce this your class must implement the java.lang.Iterable<E> interface.
	   public class MyCollection<E> implements Iterable<E>{
	 		public Iterator<E> iterator() {
        		return new MyIterator<E>();
    			}
		}
	 * The class should implement Iterable<Object> and should implement  a iterator() function.
	 * 
	 * We cannot add or remove elements to the underlying collection when we are using an iterator, it throws ConcurrentModificationException. 
	 */
	
	static class AnimalIterator<String> implements Iterator<String>{ // static class AnimalIterator implements Iterator<String>{

		int position;
		List<String> listAnimals;
		public AnimalIterator(Animals<String> a) {		//public AnimalIterator(Animals a) {

			position = 0;
			listAnimals = a.getAnimals();
		}
		@Override
		public boolean hasNext() {
			return (position != listAnimals.size());
		}

		@Override
		public String next() {
			return listAnimals.get(position++);
		}

		@Override
		public void remove() {
			listAnimals.remove(position);
 		}
	}
	
	static class Animals<String> implements Iterable<String>{ // static class Animals implements Iterable<String>{
		
		private List<String> a;
		public Animals(List<String> animalsList) {
			a = animalsList;
		}
		
		@Override
		public AnimalIterator<String> iterator(){ // public AnimalIterator iterator(){
			return new AnimalIterator<String>(this); // return new AnimalIterator(this);
		}
		
		public List<String> getAnimals(){
			return  a;
		}
		
	}
	public static void main(String[] args) {
		List<String> animalsList = new ArrayList<String>();
		animalsList.add("monkey");
		animalsList.add("cat");
		animalsList.add("dog");
		Animals<String> a = new Animals<String>(animalsList); // 	Animals a = new Animals(animalsList);
		Iterator<String> it = a.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		 		
	}
}
