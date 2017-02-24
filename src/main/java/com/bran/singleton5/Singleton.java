package com.bran.singleton5;

/**
 * 
 * 简单, 默认线程安全, 防止反序列化导致重新创建对象
 * 
 * 为什么枚举类类是线程安全的?
 * 反编译(jad插件)一个枚举类, public final class Singleton extends Enum, 
 * 实际上编译器是定义了一个final的类然后继承了枚举类.
 * 这个类里面所有的属性和方法(方法块)都是static
 * 因为当一个Java类第一次被真正使用到的时候静态资源初始化、Java类的加载和初始化过程都是线程安全的,所以枚举类也是线程安全的.
 * 
 * 为什么枚举类会避免反序列化问题？
 * 以前的所有的单例模式都有一个比较大的问题，一旦实现了Serializable接口之后就不再是单例得了，
 * 因为每次调用 readObject()方法返回的都是一个新创建出来的对象，一种解决办法就是使用readResolve()方法。
 * 但是枚举类是特殊的： 每一个枚举类型及其定义的枚举变量在JVM中都是唯一的，在枚举类型的序列化和反序列化上，java做了特殊的规定
 * “在序列化的时候Java仅仅是将枚举对象的name属性输出到结果中，反序列化的时候则是通过java.lang.Enum的valueOf方法来根据名字查找枚举对象。
 * 同时，编译器是不允许任何对这种序列化机制进行修改，因此禁用了writeObject、readObject、readObjectNoData、writeReplace和readResolve等方法。”
 * 
 * @author brandon.he
 *
 */
public enum Singleton {

	INSTANCE;
}
