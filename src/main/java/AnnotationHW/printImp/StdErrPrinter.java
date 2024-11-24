package AnnotationHW.printImp;

import AnnotationHW.AutoCallable;

import java.util.SplittableRandom;

public class StdErrPrinter implements Printer{

	String name="good";
	@Override
	@AutoCallable()
	public void print( ) {
		System.err.println("hello from StdErrAnnotation");
	}

	@AutoCallable()
	public void bebra( ) {
		System.err.println("hello from bebra");
	}


	public void mew( ) {
		System.err.println("I am cat mew mew mew");
	}
	@AutoCallable(counter = 2000000000)
	public void bark(String name) {
		System.err.println("I am" + name+ " dog bar bar bar ");
	}

}
