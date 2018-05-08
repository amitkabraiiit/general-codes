package com.practice.designpattern.behavioral;

interface Strategyy {
	public int doOperation(int num1, int num2);
}

class OperationAdd implements Strategyy{
	@Override
	public int doOperation(int num1, int num2) {
		return num1 + num2;
	}
}
class OperationSubstract implements Strategyy{
	@Override
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}
}

class OperationMultiply implements Strategyy{
	@Override
	public int doOperation(int num1, int num2) {
		return num1 * num2;
	}
}

class Contextt {
	private Strategyy Strategyy;

	public Contextt(Strategyy Strategyy){
		this.Strategyy = Strategyy;
	}

	public int executeStrategyy(int num1, int num2){
		return Strategyy.doOperation(num1, num2);
	}
}

public class StrategyDemo2 {
	public static void main(String[] args) {
		Contextt Contextt = new Contextt(new OperationAdd());		
		System.out.println("10 + 5 = " + Contextt.executeStrategyy(10, 5));

		Contextt = new Contextt(new OperationSubstract());		
		System.out.println("10 - 5 = " + Contextt.executeStrategyy(10, 5));

		Contextt = new Contextt(new OperationMultiply());		
		System.out.println("10 * 5 = " + Contextt.executeStrategyy(10, 5));
	}
}
