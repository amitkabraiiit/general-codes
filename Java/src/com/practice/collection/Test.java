package com.practice.collection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class Employee implements Cloneable {

	private String name;
	private String designation;

	public Employee() {
		this.setDesignation("Programmer");
	}
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object clone() throws CloneNotSupportedException {
		/*
	 Employee copyObj = new Employee();
	 copyObj.setDesignation(this.designation);
	 copyObj.setName(this.name);
	 return copyObj;
		 */
		return super.clone();
	}
}

public class Test {
	public static void main(String arg[]) throws CloneNotSupportedException{
		Employee jwz = new Employee();
		jwz.setName("Amit");

		Employee joel = (Employee) jwz.clone();
		System.out.println(joel.getName());
		System.out.println(joel.getDesignation());
		
		System.out.println(jwz.equals(joel));
		System.out.println(jwz == joel);
		System.out.println(jwz.getClass() == joel.getClass());
		
		System.out.println("============");
		joel.setName("Anuj");
		System.out.println(joel.getName());
		System.out.println(jwz.getName());
		
		System.out.println(jwz.equals(joel));
		System.out.println(jwz == joel);
		System.out.println(jwz.getClass() == joel.getClass());

	}
}