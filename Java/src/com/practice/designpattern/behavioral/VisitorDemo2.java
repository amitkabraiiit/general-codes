package com.practice.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;


interface NumberElement {
	public void accept(NumberVisitor visitor);
}

class TwoElement implements NumberElement {
	int a, b;
	public TwoElement(int a, int b) {
		this.a = a;
		this.b = b;
	}
	@Override
	public void accept(NumberVisitor visitor) {
		visitor.visit(this);
	}
}

class ThreeElement implements NumberElement {
	int a,b,c;
	public ThreeElement(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	@Override
	public void accept(NumberVisitor visitor) {
		visitor.visit(this);
	}
}

interface NumberVisitor {
	public void visit(TwoElement twoElement);
	public void visit(ThreeElement threeElement);
	public void visit(List<NumberElement> elementList);
}

class SumVisitor implements NumberVisitor {

	@Override
	public void visit(TwoElement twoElement) {
		int sum = twoElement.a + twoElement.b;
		System.out.println(twoElement.a + "+" + twoElement.b + "=" + sum);
	}

	@Override
	public void visit(ThreeElement threeElement) {
		int sum = threeElement.a + threeElement.b + threeElement.c;
		System.out.println(threeElement.a + "+" + threeElement.b + "+" + threeElement.c + "=" + sum);
	}

	@Override
	public void visit(List<NumberElement> elementList) {
		for (NumberElement ne : elementList) {
			ne.accept(this);
		}
	}

}

class TotalSumVisitor implements NumberVisitor {

	int totalSum = 0;

	@Override
	public void visit(TwoElement twoElement) {
		int sum = twoElement.a + twoElement.b;
		System.out.println("Adding " + twoElement.a + "+" + twoElement.b + "=" + sum + " to total");
		totalSum += sum;
	}

	@Override
	public void visit(ThreeElement threeElement) {
		int sum = threeElement.a + threeElement.b + threeElement.c;
		System.out.println("Adding " + threeElement.a + "+" + threeElement.b + "+" + threeElement.c + "=" + sum + " to total");
		totalSum += sum;
	}

	@Override
	public void visit(List<NumberElement> elementList) {
		for (NumberElement ne : elementList) {
			ne.accept(this);
		}
	}

	public int getTotalSum() {
		return totalSum;
	}

}

public class VisitorDemo2 {

	public static void main(String[] args) {

		TwoElement two1 = new TwoElement(3, 3);
		TwoElement two2 = new TwoElement(2, 7);
		ThreeElement three1 = new ThreeElement(3, 4, 5);

		List<NumberElement> numberElements = new ArrayList<NumberElement>();
		numberElements.add(two1);
		numberElements.add(two2);
		numberElements.add(three1);

		System.out.println("Visiting element list with SumVisitor");
		NumberVisitor sumVisitor = new SumVisitor();
		sumVisitor.visit(numberElements);

		System.out.println("\nVisiting element list with TotalSumVisitor");
		TotalSumVisitor totalSumVisitor = new TotalSumVisitor();
		totalSumVisitor.visit(numberElements);
		System.out.println("Total sum:" + totalSumVisitor.getTotalSum());

	}

}