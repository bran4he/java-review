package com.bran.singleton6;

/**
 * 静态内部类，懒汉式lazy-load, 线程安全（JVM的对静态类的机制）
 * 
 * 内部定义静态常量
 * 
 * @author brandon.he
 *
 */
public class Singleton {
	
	private static class Holder {
		private static final Singleton INSTANCE = new Singleton(); 
	}
	
	public Singleton(){}
	
	public static Singleton getInstance(){
		return Holder.INSTANCE;
	}
}
