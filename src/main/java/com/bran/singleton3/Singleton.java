package com.bran.singleton3;

/**
 * 双重检验锁 lazy-load 线程安全（保证进入if(null == instance)后只能创建一个实例）
 * @author brandon.he
 */
public class Singleton {
	
	private static Singleton instance;
	
	public Singleton(){}
	
	public static Singleton getInstance(){
		if(null == instance){
			synchronized(Singleton.class){
				if(null == instance){
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
