package com.bran.java8;


public interface A {

	default void foo(){
		System.out.println("default method of interface A");
	}
}
