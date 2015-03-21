package com.practice.designpattern.behavioral;

abstract class Meal {

	// template method which is final so that subclasses don't change the algorithm
	public final void doMeal() {
		prepareIngredients();
		cook();
		eat();
		cleanUp();
	}

	// abstract functions so that subclass implement them for sure in the way they want.
	public abstract void prepareIngredients();
	public abstract void cook();
	public void eat() {
		System.out.println("Mmm, that's good");
	}

	public abstract void cleanUp();

}


class HamburgerMeal extends Meal {

	@Override
	public void prepareIngredients() {
		System.out.println("Getting burgers, buns, and french fries");
	}

	@Override
	public void cook() {
		System.out.println("Cooking burgers on grill and fries in oven");
	}

	@Override
	public void cleanUp() {
		System.out.println("Throwing away paper plates");
	}

}

class TacoMeal extends Meal {

	@Override
	public void prepareIngredients() {
		System.out.println("Getting ground beef and shells");
	}

	@Override
	public void cook() {
		System.out.println("Cooking ground beef in pan");
	}

	@Override
	public void eat() {
		System.out.println("The tacos are tasty");
	}

	@Override
	public void cleanUp() {
		System.out.println("Doing the dishes");
	}

}

class Template {

	public static void main(String[] args) {

		Meal meal1 = new HamburgerMeal();
		meal1.doMeal();

		System.out.println();

		Meal meal2 = new TacoMeal();
		meal2.doMeal();

	}

}
