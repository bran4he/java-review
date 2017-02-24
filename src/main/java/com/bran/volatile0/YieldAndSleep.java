package com.bran.volatile0;

public class YieldAndSleep {

	public static void main(String[] args) {
		for(int i = 1; i<=2; i++){
			new Test().start();
		}
	}
	
}
class Test extends Thread{
	
	@Override
	public void run() {
		System.out.println(this.getName() + ":1");
		Thread.yield();
		System.out.println(this.getName() + ":2");
	}
	
}
