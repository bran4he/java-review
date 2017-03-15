package com.bran.java8;

public class Person {

	private int age;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

	public Person(int age) {
		super();
		this.age = age;
		this.name = "default";
	}

	public Person(String name) {
		super();
		this.age = 99;
		this.name = name;
	}

	public void instanceMethod(){
		System.out.println("ClassName::instanceMethod - " + this.name.toUpperCase());
	}
	
	public Person(String name, int age) {
		super();
		this.age = age;
		this.name = name;
	}
	
}
