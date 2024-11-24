package AnnotationHW.printImp;

import AnnotationHW.AutoCallable;

public class StdOutPrinter implements Printer {
	@Override
	@AutoCallable(counter = 3)
	public void print() {
		System.out.println("hello from StdOutAnnotation");
	}
}
