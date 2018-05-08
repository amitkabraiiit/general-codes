package com.practice.designpattern.structural;

class Volt {
	private int volts;
	public Volt(int v){
		this.volts=v;
	}
	public int getVolts() {
		return volts;
	}
	public void setVolts(int volts) {
		this.volts = volts;
	}
}

/*
 * Overall : 
 * 1) Converts the interface of a class into another interface that a client wants. 
 * 2) Sometimes the problem you are solving is as simple as "I don't have the interface I want". 
 * Two of the patterns in the list of GOF design patterns, Adapter, and Facade pattern solve this problem by providing an alternate interface.
 */

/*
 * One of the great real life example of Adapter design pattern is mobile charger. 
 * Mobile battery needs 3 volts to charge but the normal socket produces either 120V (US) or 240V (India). 
 * So the mobile charger works as an adapter between mobile charging socket and the wall socket.
 * We will try to implement multi-adapter using adapter design pattern in this tutorial.
 * So first of all we will have two classes – Volt (to measure volts) and Socket (producing constant volts of 120V).
 */

/*
 * It comes into place when you want to use an existing class, and its interface does not match the one you need, 
 * or you want to create a reusable class that cooperates with unrelated classes with incompatible interfaces.
 */

/*
 * Socket class below produced 120 v current.
 * We want options to produce various amount of volts.
 * So we can write an adaptor class. 
 */

class Socket {
	public Volt getVolt(){
		return new Volt(120);
	}
}

interface SocketAdapter {
	public Volt get120Volt();
	public Volt get12Volt();
	public Volt get3Volt();
}

//Using inheritance for adapter pattern
class SocketClassAdapterImpl extends Socket implements SocketAdapter{

	@Override
	public Volt get120Volt() {
		return getVolt();
	}
	@Override
	public Volt get12Volt() {
		return new Volt(getVolt().getVolts()/10);
	}
	@Override
	public Volt get3Volt() {
		return new Volt(getVolt().getVolts()/40);
	}
}

//Using Composition for adapter pattern
class SocketObjectAdapterImpl implements SocketAdapter{

	private Socket sock = new Socket();
	@Override
	public Volt get120Volt() {
		return sock.getVolt();
	}
	@Override
	public Volt get12Volt() {
		return  new Volt(sock.getVolt().getVolts()/10);
	}
	@Override
	public Volt get3Volt() {
		return  new Volt(sock.getVolt().getVolts()/40);
	}
}

public class Adaptor {

	public static void main(String[] args) {
		testClassAdapter();
		testObjectAdapter();
	}

	private static void testClassAdapter() {
		SocketAdapter sockAdapter = new SocketClassAdapterImpl();
		Volt v3 = sockAdapter.get3Volt();
		Volt v12 = sockAdapter.get12Volt();
		Volt v120 = sockAdapter.get120Volt();
		System.out.println("v3 volts using Class Adapter="+v3.getVolts());
		System.out.println("v12 volts using Class Adapter="+v12.getVolts());
		System.out.println("v120 volts using Class Adapter="+v120.getVolts());
	}

	private static void testObjectAdapter() {
		SocketAdapter sockAdapter = new SocketObjectAdapterImpl();
		Volt v3 = sockAdapter.get3Volt();
		Volt v12 = sockAdapter.get12Volt();
		Volt v120 = sockAdapter.get120Volt();
		System.out.println("v3 volts using Object Adapter="+v3.getVolts());
		System.out.println("v12 volts using Object Adapter="+v12.getVolts());
		System.out.println("v120 volts using Object Adapter="+v120.getVolts());
	}
}
