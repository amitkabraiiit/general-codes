package com.practice.designpattern.behavioral;

/*
 * Rather than an interface, I'll use an abstract base class as the handler so that subclasses can utilize 
 * the implemented setSuccessor() method. This abstract class is called PlanetHandler. 
 * Concrete handlers that subclass PlanetHandler need to implement the handleRequest() method.
 */


abstract class PlanetHandler {

	PlanetHandler successor;

	public void setSuccessor(PlanetHandler successor) {
		this.successor = successor;
	}

	public abstract void handleRequest(PlanetEnum request);

}

enum PlanetEnum {
	MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE;
}

class MercuryHandler extends PlanetHandler {

	public void handleRequest(PlanetEnum request) {
		if (request == PlanetEnum.MERCURY) {
			System.out.println("MercuryHandler handles " + request);
			System.out.println("Mercury is hot.\n");
		} else {
			System.out.println("MercuryHandler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(request);
			}
		}
	}

}

class VenusHandler extends PlanetHandler {

	public void handleRequest(PlanetEnum request) {
		if (request == PlanetEnum.VENUS) {
			System.out.println("VenusHandler handles " + request);
			System.out.println("Venus is poisonous.\n");
		} else {
			System.out.println("VenusHandler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(request);
			}
		}
	}

}

class EarthHandler extends PlanetHandler {

	public void handleRequest(PlanetEnum request) {
		if (request == PlanetEnum.EARTH) {
			System.out.println("EarthHandler handles " + request);
			System.out.println("Earth is comfortable.\n");
		} else {
			System.out.println("EarthHandler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(request);
			}
		}
	}

}

class ChainOfResponsibility {

	public static void main(String[] args) {
		PlanetHandler chain = setUpChain();

		chain.handleRequest(PlanetEnum.VENUS);
		chain.handleRequest(PlanetEnum.MERCURY);
		chain.handleRequest(PlanetEnum.EARTH);
		chain.handleRequest(PlanetEnum.JUPITER);
	}

	public static PlanetHandler setUpChain() {
		PlanetHandler mercuryHandler = new MercuryHandler();
		PlanetHandler venusHandler = new VenusHandler();
		PlanetHandler earthHandler = new EarthHandler();
		mercuryHandler.setSuccessor(venusHandler);
		venusHandler.setSuccessor(earthHandler);
		return mercuryHandler;
	}

}