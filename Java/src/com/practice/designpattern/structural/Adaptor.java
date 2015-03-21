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
		Volt v= getVolt();
		return convertVolt(v,10);
	}
	@Override
	public Volt get3Volt() {
		Volt v= getVolt();
		return convertVolt(v,40);
	}
	private Volt convertVolt(Volt v, int i) {
		return new Volt(v.getVolts()/i);
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
		Volt v= sock.getVolt();
		return convertVolt(v,10);
	}
	@Override
	public Volt get3Volt() {
		Volt v= sock.getVolt();
		return convertVolt(v,40);
	}
	private Volt convertVolt(Volt v, int i) {
		return new Volt(v.getVolts()/i);
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
