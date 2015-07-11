package com.practice.designpattern.creational;

class Meal {

	private String drink;
	private String mainCourse;
	private String side;

	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
	public String getMainCourse() {
		return mainCourse;
	}
	public void setMainCourse(String mainCourse) {
		this.mainCourse = mainCourse;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String toString() {
		return "drink:" + drink + ", main course:" + mainCourse + ", side:" + side;
	}

}

interface MealBuilder {
	public void buildDrink();
	public void buildMainCourse();
	public void buildSide();
	public Meal getMeal();
}

class ItalianMealBuilder implements MealBuilder {
	private Meal meal;
	public ItalianMealBuilder() {
		meal = new Meal();
	}
	@Override
	public void buildDrink() {
		meal.setDrink("red wine");
	}
	@Override
	public void buildMainCourse() {
		meal.setMainCourse("pizza");
	}
	@Override
	public void buildSide() {
		meal.setSide("bread");
	}
	@Override
	public Meal getMeal() {
		return meal;
	}
}


class JapaneseMealBuilder implements MealBuilder {
	private Meal meal;
	public JapaneseMealBuilder() {
		meal = new Meal();
	}
	@Override
	public void buildDrink() {
		meal.setDrink("sake");
	}
	@Override
	public void buildMainCourse() {
		meal.setMainCourse("chicken teriyaki");
	}
	@Override
	public void buildSide() {
		meal.setSide("miso soup");
	}
	@Override
	public Meal getMeal() {
		return meal;
	}

}


// Director ensures same construction processes for different products.
// We can have different directors for different type of construction processes.
class MealDirector {
	private MealBuilder mealBuilder = null;
	public MealDirector(MealBuilder mealBuilder) {
		this.mealBuilder = mealBuilder;
	}
	public void constructMeal() {
		mealBuilder.buildDrink();
		mealBuilder.buildMainCourse();
		mealBuilder.buildSide();
	}
	public Meal getMeal() {
		return mealBuilder.getMeal();
	}
}


public class Builder {

	public static void main(String[] args) {
		MealBuilder mealBuilder = new ItalianMealBuilder();
		MealDirector mealDirector = new MealDirector(mealBuilder);
		mealDirector.constructMeal();
		Meal meal = mealDirector.getMeal();
		System.out.println("meal is: " + meal);

		mealBuilder = new JapaneseMealBuilder();
		mealDirector = new MealDirector(mealBuilder);
		mealDirector.constructMeal();
		meal = mealDirector.getMeal();
		System.out.println("meal is: " + meal);
	}

}

