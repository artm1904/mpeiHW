package AnnotationHW;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoCallable {
	int counter() default 1;
}


/**
 * ДЗ: Разработать аннотацию @AutoCallable, которая применяется к методу класса.
 * Этот метод должен быть вызван при старте программы. Сделать проверку, что метод не содержит входящих аргментов???
 *   Аннотация содержит параметр: int counter - сколько раз метод ложен быт ьвызван при старте
 *   Сделать два класса с методами @AutoCallable:
 *   1)Пишет в Std out
 *   2)Пишет в Std error
 */