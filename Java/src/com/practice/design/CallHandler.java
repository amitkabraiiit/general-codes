package com.practice.design;

import java.util.List;

public class CallHandler {
	private static CallHandler instance;
	// 3 levels of employees: respondents, managers, directors
	//private final int LEVELS = 3;
	// initialize 10 respondents, 4 managers, and 2 directors
	//private final int NUM_RESPONDENTS = 10;
	//private final int NUM_MANAGERS = 4;
	//private final int NUM_DIRECTORS = 2;
	// list of employees, by level
	// employeeLevels[0]=respondents;
	// employeesLevels[1]=managers
	// employeeLevels[2]=directors
	List<List<Employee>> employeeLevels;
	// queues for each call's rank
	List<List<Call>> callQueues;

	protected CallHandler() {
	}

	// get instance of singleton class
	public static CallHandler getInstance() {
		if (instance == null)
			instance = new CallHandler();
		return instance;
	}

	// gets the first available employee who can
	// handle this call
	public Employee getHandlerForCall(Call call) {
		return null;
	}

	// routes the call to an available employee,
	// or saves in a queue
	// if no employee available
	public void dispatchCall(Caller caller) {
		Call call = new Call(caller);
		dispatchCall(call);

	}

	// routes the call to an available employee,
	// or saves in a queue if no employee available
	public void dispatchCall(Call call) {
		// try to route the call to an employee with
		// minimal rank
		Employee emp = getHandlerForCall(call);
		if (emp != null) {
			emp.receiveCall(call);
			call.setHandler(emp);

		} else {
			// place the call into corresponding
			// call queue according to its rank
			call.reply("please wait for free employee to reply");
			call.reply("please wait for free employee to reply");
			callQueues.get(call.getRank().getValue()).add(call);

		}
	}

	// an employee got free. look for
	// a waiting call that emp. can serve
	// return true if we assigned a call, false otherwise

	public boolean assignCall(Employee emp) {
		return false;
	}

}

/*
 * Call represents a call from a user. A call has a minimum rank and is assigned
 * to the first employee who can handle it.
 */

class Call {
	// minimal rank of employee who can handle this call
	private Rank rank;
	// person who is calling
	@SuppressWarnings("unused")
	private Caller caller;
	// employee who is handling call
	@SuppressWarnings("unused")
	private Employee handler;

	public Call(Caller c) {
		rank = Rank.Responder;
		caller = c;
	}

	// set employee who is handling call

	public void setHandler(Employee e) {
		handler = e;
	}

	public void reply(String message) {
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank r) {
		rank = r;
	}

	public Rank incrementRank() {
		return rank;
	}

	public void disconnect() {
	}

}

/*
 * Employee is a super class for the Director, Manager, and Respondent classes.
 * It is implemented as an abstract class since there should be no reason to
 * instantiate ane Employee type directly.
 */
abstract class Employee {
	private Call currentCall = null;
	protected Rank rank;

	public Employee() {
	}

	// start the conversation
	public void receiveCall(Call call) {
	}

	// the issue is resolved, finish the call
	public void callCompleted() {
	}

	// the issue has not been resolved, escalate the call
	// and assign a new call to the employee
	public void escalateAndReassign() {
	}

	// assign a new call to an employee,
	// if the employee is free
	public boolean assignNewCall() {
		return false;
	}

	// returns whether or not the employee is free
	public boolean isFree() {
		return currentCall == null;
	}

	public Rank getRank() {
		return rank;
	}
}

/*
 * The Respondent, Director, and Manager classes are now just simple extensions
 * of the Employee class.
 */

class Director extends Employee {
	public Director() {
		rank = Rank.Director;
	}

}

class Manager extends Employee {
	public Manager() {
		rank = Rank.Manager;
	}

}

class Respondent extends Employee {
	public Respondent() {
	    rank = Rank.Responder;
	}
}

class Caller {
}

class Rank {

	public static Rank Responder;
	public static Rank Director;
	public static Rank Manager;

	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
/*
 * This is just one way of designing this problem. Note that there are many
 * other ways that are equally good.
 * 
 * This may seem like an awful lot of code to write in an interview, and it is.
 * We have been much more thorough here than you would need. In a real
 * interview, you would likely be much lighter on some of the details until you
 * have time to fill them in.
 */