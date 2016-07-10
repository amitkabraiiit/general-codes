package com.practice.designpattern.behavioral;

import java.util.ArrayList;

public class MediatorDemo {
	public static void main(String[] args) {
		ApplicationMediator mediator = new ApplicationMediator();
		ConcreteColleague desktop = new ConcreteColleague(mediator);
		MobileColleague mobile = new MobileColleague(mediator);
		mediator.addColleague(desktop);
		mediator.addColleague(mobile);
		desktop.send("Hello World");
		mobile.send("Hello");
	}
}

interface Mediator {
	public void send(String message, Colleaggue colleague);
}



class ApplicationMediator implements Mediator {
	private ArrayList<Colleaggue> colleagues;
	public ApplicationMediator() {
		colleagues = new ArrayList<Colleaggue>();
	}
	public void addColleague(Colleaggue colleague) {
		colleagues.add(colleague);
	}
	public void send(String message, Colleaggue originator) {
		//let all other screens know that this screen has changed
		for(Colleaggue colleague: colleagues) {
			//don't tell ourselves
			if(colleague != originator) {
				colleague.receive(message);
			}
		}
	}
}

abstract class Colleaggue{
	private Mediator mediator;
	public Colleaggue(Mediator m) {
		mediator = m;
	}
	//send a message via the mediator
	public void send(String message) {
		mediator.send(message, this);
	}
	//get access to the mediator
	public Mediator getMediator() {return mediator;}
	public abstract void receive(String message);
}

class ConcreteColleague extends Colleaggue {
	public ConcreteColleague(Mediator m) {
		super(m);
	}

	public void receive(String message) {
		System.out.println("Colleague Received: " + message);
	}
}

class MobileColleague extends Colleaggue {
	public MobileColleague(Mediator m) {
		super(m);
	}

	public void receive(String message) {
		System.out.println("Mobile Received: " + message);
	}
}

