package com.bran.java8;

public class App {

	public void intanceMethod(Person p){
		System.out.println("instance method: " + p.getName());
	}
	
	public static void staticMethod(Person p){
		System.out.println("static method: " + p.getName());
	}
}
