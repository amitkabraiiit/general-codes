package com.practice.java8;

import java.util.NoSuchElementException;
import java.util.Optional;

class CCar
{

	private String price;

	public CCar( String price )
	{
		setPrice( price );
	}

	public void setPrice( String price )
	{
		this.price = price;
	}

	public String getPrice()
	{
		return this.price;
	}

	@Override
	public String toString()
	{
		return "this car costs " + getPrice();
	}

}

public class OptionalPractice2 {

	private static void filterExample()
	{
		/* filter */

		// if the value is not present
		Optional<CCar> carOptionalEmpty = Optional.empty();
		carOptionalEmpty.filter( x -> "250".equals( x.getPrice() ) ).ifPresent( x -> System.out.println( x.getPrice()
				+ " is ok!" ) );

		// if the value does not pass the filter
		Optional<CCar> carOptionalExpensive = Optional.of( new CCar( "3333" ) );
		carOptionalExpensive.filter( x -> "250".equals( x.getPrice() ) ).ifPresent( x -> System.out.println( x
				.getPrice() + " is ok!" ) );

		// if the value is present and does pass the filter
		Optional<CCar> carOptionalOk = Optional.of( new CCar( "250" ) );
		carOptionalOk.filter( x -> "250".equals( x.getPrice() ) ).ifPresent( x -> System.out.println( x.getPrice()
				+ " is ok!" ) );

	}

	private static void mapExample()
	{
		/* map */
		// non empty string map to its length
		Optional<String> stringOptional = Optional.of( "loooooooong string" );
		Optional<Integer> sizeOptional = stringOptional.map( String::length ); //map from Optional<String> to Optional<Integer>

		System.out.println( "size of string " + sizeOptional.orElse( 0 ) );

		// empty string map to its length -> we get 0 as lenght
		Optional<String> stringOptionalNull = Optional.ofNullable( null );
		Optional<Integer> sizeOptionalNull = stringOptionalNull.map( x -> x.length()  ); // we can use Lambdas as we want

		System.out.println( "size of string " + sizeOptionalNull.orElse( 0 ) );

	}

	private static void ifPresentExample()
	{
		/* ifPresent */
		Optional<String> stringToUse = Optional.of( "danibuiza2" );
		stringToUse.ifPresent( System.out::println );

		/* if not present */
		Optional<String> stringToUseNull = Optional.ofNullable( null );
		stringToUseNull.ifPresent( System.out::println );
	}

	private static void isPresentExample()
	{
		/* isPresent */
		Optional<String> stringToUse = Optional.of( "danibuiza1" );
		if( stringToUse.isPresent() )
		{
			System.out.println( stringToUse.get() );
		}

	}

	private static void orElseThrowExample()
	{
		try
		{
			/* orElseThrow */
			Car carNull = null;
			Optional<Car> optionalCarNull = Optional.ofNullable( carNull );
			optionalCarNull.orElseThrow( IllegalStateException::new );
		}
		catch( IllegalStateException ex )
		{
			System.out.println( "expected IllegalStateException" );
		}
	}

	private static void orElseExample()
	{
		/* orElse */
		CCar carCreated = new CCar( "500" );
		CCar defaultCar = new CCar( "250" );

		// value is there
		Optional<CCar> optionalCar = Optional.of( carCreated );
		String price = optionalCar.orElse( defaultCar ).getPrice();
		System.out.println( "Car price: " + price );

		// else
		Optional<CCar> optionalCar2 = Optional.empty();
		price = optionalCar2.orElse( defaultCar ).getPrice();
		System.out.println( "Car price: " + price );
	}

	private static void getExample()
	{
		/* get */
		try
		{
			String strNull2 = null;
			// cannot be passed, we should use nullable
			Optional<String> optionalString = Optional.of( strNull2 );
			System.out.println( optionalString.get().contains( "something" ) );
		}
		catch( NullPointerException ex )
		{
			System.out.println( "expected nullpointer" );
		}
	}

	private static void nullableOptional()
	{
		try
		{/* ofNullable */
			String strNull = null;
			Optional<String> nullableOptional = Optional.ofNullable( strNull );
			System.out.println( nullableOptional.get() );
		}
		catch( NoSuchElementException ex )
		{
			System.out.println( "expected NoSuchElementException" );
		}
	}

	private static void nonEmptyOptional()
	{
		/* non empty */
		String str = "string";
		Optional<String> nonEmptyOptional = Optional.of( str );
		System.out.println( nonEmptyOptional.get() );
	}

	private static void emptyOptionalCreation()
	{
		try
		{
			/* empty */
			Optional<String> emptyOptional = Optional.empty();
			System.out.println( emptyOptional.get() );
		}
		catch( NoSuchElementException ex )
		{
			System.out.println( "expected NoSuchElementException" );
		}
	}

	public static void main(String[] args) {


		emptyOptionalCreation();
		nonEmptyOptional();
		nullableOptional();
		getExample();
		orElseExample();
		orElseThrowExample();
		isPresentExample();
		ifPresentExample();
		mapExample();
		filterExample();

	}
}
