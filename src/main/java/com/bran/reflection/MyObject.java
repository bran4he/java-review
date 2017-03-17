package com.bran.reflection;

public class MyObject {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MyObject [name=" + name + "]";
	}

	public MyObject(String name) {
		this.name = name;
	}

	public MyObject() {
		super();
	}
	
	
}
