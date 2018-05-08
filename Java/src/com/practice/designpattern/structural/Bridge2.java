package com.practice.designpattern.structural;

// https://www.programcreek.com/2011/10/java-design-pattern-bridge/

// First define a TV interface: ITV
interface ITV {
	public void on();
	public void off();
	public void switchChannel(int channel);
}

//Let Samsung implement ITV interface.
class SamsungTV implements ITV {
	@Override
	public void on() {
		System.out.println("Samsung is turned on.");
	}
 
	@Override
	public void off() {
		System.out.println("Samsung is turned off.");
	}
 
	@Override
	public void switchChannel(int channel) {
		System.out.println("Samsung: channel - " + channel);
	}
}
// Let Sony implement ITV interface.
class SonyTV implements ITV {
 	@Override
	public void on() {
		System.out.println("Sony is turned on.");
	}
 	@Override
	public void off() {
		System.out.println("Sony is turned off.");
	}
	@Override
	public void switchChannel(int channel) {
		System.out.println("Sony: channel - " + channel);
	}
}

//Remote control holds a reference to the TV.
abstract class AbstractRemoteControl {
	private ITV tv;
	public AbstractRemoteControl(ITV tv){
		this.tv = tv;
	}
	public void turnOn(){
		tv.on();
	}
 	public void turnOff(){
		tv.off();
	}
 	public void setChannel(int channel){
		tv.switchChannel(channel);
	}
}

// Define a concrete remote control class.
class LogitechRemoteControl extends AbstractRemoteControl {
	public LogitechRemoteControl(ITV tv) {
		super(tv);
	}
	public void setChannelKeyboard(int channel){
		setChannel(channel);
		System.out.println("Logitech use keyword to set channel.");
	}
}
public class Bridge2 {
	public static void main(String[] args){
		ITV tv = new SonyTV();
		LogitechRemoteControl lrc = new LogitechRemoteControl(tv);
		lrc.setChannelKeyboard(100);	
	}
}
