package com.practice.java;

enum Currency {
	PENNY, NICKEL, DIME, QUARTER
};


public enum EnumExample {
	BREAKFAST(7, 30), LUNCH(12, 15), DINNER(19, 45);
	private int hh;
	private int mm;
	EnumExample(int hh, int mm) {
		assert (hh >= 0 && hh <= 23) : "Illegal hour.";
		assert (mm >= 0 && mm <= 59) : "Illegal mins.";
		this.hh = hh;
		this.mm = mm;
	}
	public int getHour() {
		return hh;
	}
	public int getMins() {
		return mm;
	}


	public static void main(String args[]){
		EnumExample t = BREAKFAST;  // can't instantiate enum as EnumExample t = new EnumExample() , since its constructor is always private.
		System.out.println(t.getHour() +":"+t.getMins());
		for( EnumExample s : EnumExample.values()){
			System.out.println(s.getHour());
		}
		
		for( Currency s : Currency.values()){
			System.out.println(s.name());
		}
	}
}


