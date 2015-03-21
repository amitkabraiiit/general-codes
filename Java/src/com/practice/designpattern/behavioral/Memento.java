package com.practice.designpattern.behavioral;

//originator - object whose state we want to save
class DietInfo {

	String personName;
	int dayNumber;
	int weight;

	public DietInfo(String personName, int dayNumber, int weight) {
		this.personName = personName;
		this.dayNumber = dayNumber;
		this.weight = weight;
	}

	public String toString() {
		return "Name: " + personName + ", day number: " + dayNumber + ", weight: " + weight;
	}

	public void setDayNumberAndWeight(int dayNumber, int weight) {
		this.dayNumber = dayNumber;
		this.weight = weight;
	}

	public Memento save() {
		return new Memento(personName, dayNumber, weight);
	}

	public void restore(Object objMemento) {
		Memento memento = (Memento) objMemento;
		personName = memento.mementoPersonName;
		dayNumber = memento.mementoDayNumber;
		weight = memento.mementoWeight;
	}

	// memento - object that stores the saved state of the originator
	private class Memento {
		String mementoPersonName;
		int mementoDayNumber;
		int mementoWeight;

		public Memento(String personName, int dayNumber, int weight) {
			mementoPersonName = personName;
			mementoDayNumber = dayNumber;
			mementoWeight = weight;
		}
	}
}

//caretaker - saves and restores a DietInfo object's state via a memento
//note that DietInfo.Memento isn't visible to the caretaker so we need to cast the memento to Object
class DietInfoCaretaker {

	Object objMemento;

	public void saveState(DietInfo dietInfo) {
		objMemento = dietInfo.save();
	}

	public void restoreState(DietInfo dietInfo) {
		dietInfo.restore(objMemento);
	}

}

class Memento {

	public static void main(String[] args) {

		// Caretaker
		DietInfoCaretaker dietInfoCaretaker = new DietInfoCaretaker();

		// Originator
		DietInfo dietInfo = new DietInfo("Fred", 1, 100);
		System.out.println(dietInfo);

		dietInfo.setDayNumberAndWeight(2, 99);
		System.out.println(dietInfo);

		System.out.println("Saving state.");
		dietInfoCaretaker.saveState(dietInfo);

		dietInfo.setDayNumberAndWeight(3, 98);
		System.out.println(dietInfo);

		dietInfo.setDayNumberAndWeight(4, 97);
		System.out.println(dietInfo);

		System.out.println("Restoring saved state.");
		dietInfoCaretaker.restoreState(dietInfo);
		System.out.println(dietInfo);

	}
}