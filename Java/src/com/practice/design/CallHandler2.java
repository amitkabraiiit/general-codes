package com.practice.design;

import java.util.ArrayList;
import java.util.List;

public class CallHandler2 {
	private static CallHandler2 instance;
	List<List<Calll>> callQueues;
	List<Respondentt> r = new ArrayList<Respondentt>();
	Managerr m1 = new Managerr("Manager1");
	Managerr m2 = new Managerr("Manager1");
	Directorr d = new Directorr("Director");
	private int numEmployees = 5;
	public void prepareEmployees(){
		m1.boss = d;
		m2.boss = d;
		for(int i =0; i<numEmployees ; i++){
			Respondentt res = new Respondentt("Respondenttt"+i);
			if(i%2 ==0) res.boss = m1;
			else res.boss = m2;
			r.add(res);
		}
	}

	// get instance of singleton class
	public static CallHandler2 getInstance() {
		if (instance == null)instance = new CallHandler2();
		return instance;
	}

	// an employee got free. look for a waiting call that emp. can serve return true if we assigned a call, false otherwise
	private void dispatchCall(Calll c) {
		for(int i =0; i<r.size() ; i++){
			int employeeNum = (int)System.currentTimeMillis()% r.size();
			employeeNum = (employeeNum+i)%r.size();

			System.out.println("Respondenttt "+employeeNum+" got the call!!");
			Respondentt res = r.get(employeeNum);
			if (res.isFree()){
				res.receiveCall(c);
				break;
			}
		}
	}

	public static void main(String[] args) {
		CallHandler2 c = CallHandler2.getInstance();
		c.prepareEmployees();
		for(CallType type : CallType.values()){
			c.dispatchCall(new Calll(type));	
		}


	}
}

enum CallType {
	EASY , MEDIUM, TOUGH;
}
/*
 * Call represents a call from a user. A call has a minimum rank and is assigned to the first employee who can handle it.
 */

class Calll {

	// person who is calling
	public CallType callType;
	// employee who is handling call
	@SuppressWarnings("unused")
	private Employeee handler;

	public Calll(CallType c) {
		callType = c;
	}

	// set employee who is handling call
	public void setHandler(Employeee e) {
		handler = e;
	}

	public CallType getCallType() {
		return this.callType;
	}

	public void disconnect() {
	}

}

enum Role {
	Responder,	Directorr,	Managerr;
}

/*
 * Employeee is a super class for the Directorr, Managerr, and Respondentt classes.
 * It is implemented as an abstract class since there should be no reason to
 * instantiate an Employeee type directly.
 */
abstract class Employeee {
	protected Calll currentCall = null;
	protected Role rank;
	protected Employeee boss ;
	public String name;
	public Employeee(String name) {
		this.name = name;
	}

	public void setBoss(Employeee e) {
		this.boss = e;
	}
	// start the conversation
	public abstract void receiveCall(Calll call);

	// returns whether or not the employee is free
	public boolean isFree() {
		return currentCall == null;
	}

	public Role getRole() {
		return rank;
	}
}

class Directorr extends Employeee {
	public Directorr(String name) {
		super(name);
		this.name = name;
		rank = Role.Directorr;
	}

	@Override
	public void receiveCall(Calll call) {	
		if(call.getCallType() == CallType.TOUGH){
			if(!isFree()){
				System.out.println(this.name +" busy, please try after sometime ...");
			}else{
				this.currentCall = call;
				System.out.println(this.name+" attending the call!!");
			}
		}else{
			System.out.println("Wrong number!!");
		}
	}

}

class Managerr extends Employeee {
	public Managerr(String name) {
		super(name);
		this.name = name;
		rank = Role.Managerr;
	}
	@Override
	public void receiveCall(Calll call) {	
		if(call.getCallType() == CallType.MEDIUM){
			if(!isFree()){
				System.out.println(this.name +" busy, please try after sometime ...");
			}else{
				this.currentCall = call;
				System.out.println(this.name+" attending the call!!");
			}
		}else{
			this.boss.receiveCall(call);
		}
	}

}

class Respondentt extends Employeee {
	public Respondentt(String name) {
		super(name);
		this.name = name;
	}
	@Override
	public void receiveCall(Calll call) {	
		if(call.getCallType() == CallType.EASY){
			if(!isFree()){
				System.out.println(this.name +" busy, please try after sometime ...");
			}else{
				this.currentCall = call;
				System.out.println(this.name+" attending the call!!");
			}
		}else{
			this.boss.receiveCall(call);
		}
	}
}