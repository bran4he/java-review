package com.bran.singleton2;

/**
 * 懒汉式 lazy-load 线程安全 
 * 静态方法锁（其他线程不能访问该类的静态块和静态方法，非静态块和方法可以访问） 
 * 效率低
 * @author brandon.he
 */
public class Singleton {

	private static Singleton instance;
	
	public Singleton(){}
	
	public synchronized static Singleton getInstance(){
		if(null ==  instance){
			instance = new Singleton();
		}
		return instance;
	}
}
