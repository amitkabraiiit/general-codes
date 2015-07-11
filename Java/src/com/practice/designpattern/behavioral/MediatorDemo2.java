package com.practice.designpattern.behavioral;

interface IATCMediator {
	public void registerRunway(Runway runway);
	public void registerFlight(Flight flight);
	public boolean isLandingOk();
	public void setLandingStatus(boolean status);
}	

class ATCMediator implements IATCMediator {
	private Flight flight;
	private Runway runway;
	public boolean land;

	@Override
	public void registerRunway(Runway runway) {
		this.runway = runway;
	}
	@Override
	public void registerFlight(Flight flight) {
		this.flight = flight;
	}
	@Override
	public boolean isLandingOk() {
		return land;
	}

	@Override
	public void setLandingStatus(boolean status) {
		land = status;
	}
}


interface IATCCommand {
	void land();
}

class Flight implements IATCCommand {
	private IATCMediator atcMediator;

	public Flight(IATCMediator atcMediator) {
		this.atcMediator = atcMediator;
	}

	public void land() {
		if (atcMediator.isLandingOk()) {
			System.out.println("Landing done....");
			atcMediator.setLandingStatus(true);
		} else
			System.out.println("Will wait to land....");
	}

	public void getReady() {
		System.out.println("Getting ready...");
	}
}



class Runway implements IATCCommand {
	private IATCMediator atcMediator;

	public Runway(IATCMediator atcMediator) {
		this.atcMediator = atcMediator;
		atcMediator.setLandingStatus(true);
	}

	@Override
	public void land() {
		System.out.println("Landing permission granted...");
		atcMediator.setLandingStatus(true);
	}

}

class MediatorDemo2{
	public static void main(String args[]) {

		IATCMediator atcMediator = new ATCMediator();
		Flight sparrow101 = new Flight(atcMediator);
		Runway mainRunway = new Runway(atcMediator);
		atcMediator.registerFlight(sparrow101);
		atcMediator.registerRunway(mainRunway);
		sparrow101.getReady();
		mainRunway.land();
		sparrow101.land();
	}
}

