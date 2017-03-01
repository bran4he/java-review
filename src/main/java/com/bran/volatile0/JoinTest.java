package com.bran.volatile0;

import java.util.concurrent.TimeUnit;

public class JoinTest {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t1 start");
				System.out.println("t1 sleep for 2s");
				
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("t1 end");
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("t2 start");
				System.out.println("t2 end");
			}
		});
		
		t.start();
//		t.join(1000);
//		t.join();
		t2.start();
	}
}
