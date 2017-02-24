package com.bran.singleton1;

import java.io.Serializable;

/**
 * 懒汉式 lazy-load 线程不安全
 * @author brandon.he
 */
public class Singleton implements Serializable{
	
	private static Singleton instance;
	
	public Singleton(){}
	
	public static Singleton getInstance(){
		if(null == instance){
			instance = new Singleton();
		}
		return instance;
	}
	
	//保证一般单例模式即使实现序列化接口,仍然保证单例特性
	private Object readResolve(){
		return instance;
	}
}
