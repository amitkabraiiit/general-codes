package com.practice.designpattern.behavioral;

//State
interface EmotionalState {

	public String sayHello();
	public String sayGoodbye();

}

//Concrete State
class HappyState implements EmotionalState {

	@Override
	public String sayGoodbye() {
		return "Bye, friend!";
	}

	@Override
	public String sayHello() {
		return "Hello, friend!";
	}

}

//Concrete State
class SadState implements EmotionalState {

	@Override
	public String sayGoodbye() {
		return "Bye. Sniff, sniff.";
	}

	@Override
	public String sayHello() {
		return "Hello. Sniff, sniff.";
	}

}

//Context
class Person implements EmotionalState {

	EmotionalState emotionalState;

	public Person(EmotionalState emotionalState) {
		this.emotionalState = emotionalState;
	}

	public void setEmotionalState(EmotionalState emotionalState) {
		this.emotionalState = emotionalState;
	}

	@Override
	public String sayGoodbye() {
		return emotionalState.sayGoodbye();
	}

	@Override
	public String sayHello() {
		return emotionalState.sayHello();
	}

}

class State {

	public static void main(String[] args) {

		Person person = new Person(new HappyState());
		System.out.println("Hello in happy state: " + person.sayHello());
		System.out.println("Goodbye in happy state: " + person.sayGoodbye());

		person.setEmotionalState(new SadState());
		System.out.println("Hello in sad state: " + person.sayHello());
		System.out.println("Goodbye in sad state: " + person.sayGoodbye());

	}

}