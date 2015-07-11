package com.practice.designpattern.behavioral;

interface Command {
	public void execute();
}

class Lunch {
	public void makeLunch() {
		System.out.println("Lunch is being made");
	}
}

class Dinner {
	public void makeDinner() {
		System.out.println("Dinner is being made");
	}
}

class LunchCommand implements Command {
	Lunch lunch; // a receiver
	public LunchCommand(Lunch lunch) {
		this.lunch = lunch;
	}

	@Override
	public void execute() { // invokes the appropriate action on the receiver.
		lunch.makeLunch();
	}
}

class DinnerCommand implements Command {
	Dinner dinner; // a receiver
	public DinnerCommand(Dinner dinner) {
		this.dinner = dinner;
	}

	@Override
	public void execute() { // invokes the appropriate action on the receiver.
		dinner.makeDinner();
	}
}

// MealInvoker is the invoker class. 
// It contains a reference to the Command to invoke. Its invoke() method calls the execute() method of the Command.

class MealInvoker {
	Command command;
	public MealInvoker(Command command) {
		this.command = command;
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public void invoke() {
		command.execute();
	}
}

/*
 * It instantiates a Lunch (receiver) object and creates a LunchCommand (concrete command) with the Lunch. 
 * The LunchCommand is referenced by a Command interface reference. 
 * Next, we perform the same procedure on the Dinner and DinnerCommand objects. 
 * After this, we create a MealInvoker object with lunchCommand, and we call the invoke() method of mealInvoker. 
 * After this, we set mealInvoker's command to dinnerCommand, and once again call invoke() on mealInvoker.
 * As you can see, the invoker invokes a command, but has no direct knowledge of the action being performed by the receiver.
 */

class CommandPatternDemo {

	public static void main(String[] args) {

		Lunch lunch = new Lunch(); // receiver
		Command lunchCommand = new LunchCommand(lunch); // concrete command

		Dinner dinner = new Dinner(); // receiver
		Command dinnerCommand = new DinnerCommand(dinner); // concrete command

		MealInvoker mealInvoker = new MealInvoker(lunchCommand); // invoker
		mealInvoker.invoke();

		mealInvoker.setCommand(dinnerCommand);
		mealInvoker.invoke();

	}

}