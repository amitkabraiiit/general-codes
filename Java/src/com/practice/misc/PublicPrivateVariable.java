package com.practice.misc;


class PublicPrivateVariable
{
	private String instanceVariable;
	private static String staticVariable;
	public static String publicVariable;

	public String instanceMethod()
	{
		return "instance";
	}

	public static String staticMethod()
	{
		return "static";
	}
	public static void main(String[] args)
	{
		System.out.println(staticVariable); // null
		System.out.println(publicVariable); // null
		System.out.println(PublicPrivateVariable.staticMethod()); // static
		System.out.println(new PublicPrivateVariable().instanceMethod()); // instance
		System.out.println(new PublicPrivateVariable().instanceVariable); // null
		//System.out.println(PublicPrivateVariable.instanceMethod()); // wrong
		// System.out.println(instanceVariable);         // wrong 
	}
}
