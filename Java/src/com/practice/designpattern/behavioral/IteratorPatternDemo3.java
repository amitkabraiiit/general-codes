package com.practice.designpattern.behavioral;

/*
 * This implementation works but is not correct, I just modified the original eg to see that it works or not, it works.
 * I wanted to avoid 2nd interface, the container one. Seems like its a must.
 */

interface Iterator2 {   // Iterator2, since Iterator is already defined in this package
	public boolean hasNext();
	public Object next();
}

class NameRepository2 implements Iterator2 {
	public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

	public Iterator2 getIterator2() {
		return this; // This looks wrong since when we return this , we also written other variables / functions of this class
		// while iterator should only have hasNext and Next.
	}

	int index;
	@Override
	public boolean hasNext() {
		if(index < names.length){
			return true;
		}
		return false;
	}

	@Override
	public Object next() {
		if(this.hasNext()){
			return names[index++];
		}
		return null;
	}		

}

public class IteratorPatternDemo3 {

	public static void main(String[] args) {
		NameRepository2 namesRepository = new NameRepository2();
		for(Iterator2 iter = namesRepository.getIterator2(); iter.hasNext();){
			String name = (String)iter.next();
			System.out.println("Name : " + name);
		} 	
	}
}
