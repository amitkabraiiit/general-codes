package com.practice.designpattern.behavioral;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

interface WeatherSubject {
	public void addObserver(WeatherObserver weatherObserver);
	public void removeObserver(WeatherObserver weatherObserver);
	public void doNotify();
}

class WeatherStation implements WeatherSubject {

	Set<WeatherObserver> weatherObservers;
	int temperature;

	public WeatherStation(int temperature) {
		weatherObservers = new HashSet<WeatherObserver>();
		this.temperature = temperature;
	}

	@Override
	public void addObserver(WeatherObserver weatherObserver) {
		weatherObservers.add(weatherObserver);
	}

	@Override
	public void removeObserver(WeatherObserver weatherObserver) {
		weatherObservers.remove(weatherObserver);
	}

	@Override
	public void doNotify() {
		Iterator<WeatherObserver> it = weatherObservers.iterator();
		while (it.hasNext()) {
			WeatherObserver weatherObserver = it.next();
			weatherObserver.doUpdate(temperature);
		}
	}

	public void setTemperature(int newTemperature) {
		System.out.println("\nWeather station setting temperature to " + newTemperature);
		temperature = newTemperature;
		doNotify();
	}

}

interface WeatherObserver {
	public void doUpdate(int temperature);
}

class WeatherCustomer1 implements WeatherObserver {

	@Override
	public void doUpdate(int temperature) {
		System.out.println("Weather customer 1 just found out the temperature is:" + temperature);
	}

}


class WeatherCustomer2 implements WeatherObserver {

	@Override
	public void doUpdate(int temperature) {
		System.out.println("Weather customer 2 just found out the temperature is:" + temperature);
	}

}

class Observer {

	public static void main(String[] args) {
		
		WeatherStation weatherStation = new WeatherStation(33);
		
		WeatherCustomer1 wc1 = new WeatherCustomer1();
		WeatherCustomer2 wc2 = new WeatherCustomer2();
		weatherStation.addObserver(wc1);
		weatherStation.addObserver(wc2);
		
		weatherStation.setTemperature(34);
		
		weatherStation.removeObserver(wc1);
		
		weatherStation.setTemperature(35);
	}

}