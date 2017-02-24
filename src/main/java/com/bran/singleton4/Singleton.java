package com.bran.singleton4;

/**
 * 恶汉式
 * 某些场景下使用有缺陷，如创建实例时必须依赖于参数和配置文件
 * @author brandon.he
 */
public class Singleton {

	private static Singleton instance = new Singleton();
	
	public Singleton(){}
	
	public static Singleton getInstance(){
		return instance;
	}
}
