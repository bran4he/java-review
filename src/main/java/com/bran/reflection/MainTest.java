package com.bran.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		test_main();
		test_class();
		
	}
	
	public static void test_class() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		System.out.println("test_class");
		Class myObjClass = Class.forName("com.bran.reflection.MyObject");
		Method[] methods = myObjClass.getMethods();
		for(Method m : methods){
			System.out.println(m.getName());
		}
		
		System.out.println("------");
		Class c = MyObject.class;
		System.out.println(c.getName());
		System.out.println(c.getSimpleName());
		System.out.println(c.getModifiers());
		System.out.println(c.getPackage());
		System.out.println(c.getConstructors());
		
		System.out.println("------");
		Constructor constructor = MyObject.class.getConstructor(String.class);
		MyObject obj = (MyObject) constructor.newInstance("bran");
		System.out.println(obj.toString());
		
		System.out.println("------");
		Method method = MyObject.class.getMethod("setName", String.class);
		MyObject myObj = new MyObject();
		Object returnValue = method.invoke(myObj, "parameter-value1");
		System.out.println(myObj.toString());

	}
	
	public static void test_main(){
		System.out.println("test_main");
		Method[] methods = MyObject.class.getMethods();
		for(Method m : methods){
			System.out.println(m.getName());
		}
	}

}
