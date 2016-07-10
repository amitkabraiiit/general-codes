package com.practice.misc2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

class Simple{
	public void print(){
		System.out.println("Inside Simple class");
	}
}

interface itf{}

public class Reflection {
	public int abc = 0 ;

	public void classes() throws ClassNotFoundException{

		// If you know the name of the class at compile time you can obtain a Class object like this:
		Class c1 = Simple.class;
		System.out.println(c1.getName());

		// If you don't know the name at compile time, but have the class name as a string at runtime, you can do like this:
		Class c2 = Class.forName("com.practice.misc2.Simple"); // java.lang.ClassNotFoundException: Simple , if we use just Simple
		System.out.println(c2.getName());

		// From the object of the class
		Simple s = new Simple();
		Class c3 = s.getClass();
		System.out.println(c3.getName());

		System.out.println(c1.getName());
		System.out.println(c1.getSimpleName());
		System.out.println(c1.isInterface());
		System.out.println(c1.getModifiers());
		System.out.println(c1.getPackage());
		System.out.println(c1.getSuperclass());
		System.out.println(Arrays.toString(c1.getMethods()));
		// Similarly getFields(), getConstructors(),getAnnotations()

	}

	public void methodsAndFields(Reflection r) throws Exception{
		System.out.println("\nListing all the methods ---> ");
		Class c = Reflection.class;
		Method[] methods = c.getMethods();
		for(Method method : methods){
			System.out.println("method = " + method.getName());
		}
		System.out.println("\nInvoking classes method ---> ");
		Method m =  r.getClass().getMethod("classes");
		m.invoke(r);

		// method parameters and return types
		Class[] parameterTypes = m.getParameterTypes();
		Class returnType = m.getReturnType();

		// class fields
		Field[] fields = c.getFields();
		Field field = c.getField("abc");
		String fieldName = field.getName();
		Object fieldType = field.getType();
		// get set field values
		Object value = field.get(r);
		field.set(r, value);

	}

	class PrivateObject {

		private String privateString = null;
		public PrivateObject(String privateString) {
			this.privateString = privateString;
		}
		private String getPrivateString(){
			return this.privateString;
		}
	}

	public void testPrivateFieldAndMethods() throws Exception{
		System.out.println("\nAccessing private fields and methods ---> ");
		// Accessing Field
		PrivateObject privateObject = new PrivateObject("The Private Value");
		Field privateStringField = PrivateObject.class.getDeclaredField("privateString");
		privateStringField.setAccessible(true);
		String fieldValue = (String) privateStringField.get(privateObject);
		System.out.println("fieldValue = " + fieldValue);
		
		// Accessing Method	
		Method privateStringMethod = PrivateObject.class.getDeclaredMethod("getPrivateString", null);
		privateStringMethod.setAccessible(true);
		String returnValue = (String)privateStringMethod.invoke(privateObject, null);
		System.out.println("returnValue = " + returnValue);
	}

	public static void main(String[] args) throws Exception{
		Reflection r = new Reflection();
		// r.classes(); , calling this from reflection from methodsAndFields below.
		r.methodsAndFields(r);
		r.testPrivateFieldAndMethods();
	}

}
