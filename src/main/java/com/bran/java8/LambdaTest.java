package com.bran.java8;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LambdaTest {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		lambdaRunThreadTest();
//		lambdaJFrameTest();
//		lambdaListTest();
//		lambdaStreamTest();
		
//		LambdaTest test = new LambdaTest();
//		test.setName("test 4 lambda");
//		test.lambdaThisTest();
		
//		lambdaMethodTest();
		
//		streamTest();
		streamHandleTest();
	}
	
	public static void streamHandleTest(){
//		System.out.println("flatMap()");		
		Stream<List<Integer>> lstStream = Stream.of(
				Arrays.asList(1), 
				Arrays.asList(3,5), 
				Arrays.asList(7,9,11,3,5,7,11));
		
		Stream<Integer> strStream = lstStream.flatMap((childList) -> childList.stream());
//		strStream.forEach(System.out::println);
		
		// when use stream, it should not be used before
		
//		System.out.println("distinct()");
//		strStream.distinct().forEach(System.out::println);
		
//		System.out.println("filter()");
//		strStream.filter(s -> s != 3).forEach(System.out::println);
		
//		System.out.println("map()");
//		strStream.map(s -> s * 100).forEach(System.out::println);
		
//		System.out.println("peek()");	//consumer
//		strStream.peek(s -> {System.out.println(s);}).collect(Collectors.toList());	//OK
		
/*		It is an intermediate method which means it is lazy. 
		It wont do anything unless a terminal operation is invoked on it 
		which would result in some computation to be performed. 
*/
//		strStream.peek(s -> {System.out.println(s);});		//nothing
		
//		System.out.println("skip()");
//		strStream.skip(5).forEach(System.out::println);
		
//		System.out.println("reduce - sum()");
//		int sum = strStream.mapToInt(num -> num.intValue()).sum();
//		System.out.println("sum() is " + sum);
		
		
	}
	
	public static void streamTest(){
		Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
		Stream<String> stringStream = Stream.of("taobao");
		
		Stream.iterate(100, item -> item + 1).limit(10).forEach(System.out::println);
		
		Stream<Double> doubleStream = Stream.generate(() -> Math.random()).limit(10);
		doubleStream.map(d -> {
			return d + 100L;
		}).collect(Collectors.toList()).forEach(System.out::println);;
		
	}
	
/*	
 	1.objectName::instanceMethod
	2.ClassName::staticMethod
	3.ClassName::instanceMethod
	
	4.ClassName::new
*/
	public static void lambdaMethodTest(){
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("tom", 11));
		persons.add(new Person("jack", 34));
		persons.add(new Person("kffa", 2));
		
		App app = new App();
		//1
		persons.forEach(app::intanceMethod);
		//2
		persons.forEach(App::staticMethod);
		//3
		persons.forEach(Person::instanceMethod);
		
		//4
		List<String> lst = Arrays.asList(new String[]{"hello", "this", "world"});
		List<Person> ps = lst.stream().map(Person::new).collect(Collectors.toList());
		ps.forEach(System.out::println);
	}
	
	public void lambdaThisTest(){
		List<String> lst = Arrays.asList(new String[]{"hello", "this", "world"});
		List<String> result = lst.stream().map(str -> {
			System.out.println(this.getClass().getName());
			System.out.println(this.getName());
			return str.toUpperCase();
		}).collect(Collectors.toList());
		
		result.forEach(System.out::println);
	}
	
	public static void lambdaRunThreadTest(){
		new Thread(() -> System.out.println("lambda new thread")).start();
	}

	public static void lambdaJFrameTest(){
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		
		JButton btn = new JButton("CLICK1");
		frame.getContentPane().add(btn);
		
		btn.addActionListener(e -> {System.out.println("clicke me!");});
	}
	
	public static void lambdaListTest(){
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("tom"));
		persons.add(new Person("jack"));
		persons.add(new Person("kffa"));
		
		persons.forEach(p -> System.out.println(p.toString()));
		
		persons.forEach(p -> {
			Person it = (Person)p;
			it.setName("bran");
			});
		
		persons.forEach(p -> System.out.println(p.toString()));
	}

	public static void lambdaStreamTest(){
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("tom", 11));
		persons.add(new Person("jack", 34));
		persons.add(new Person("kffa", 2));
		
//		Stream streamPerson = persons.stream().filter(p -> p.getAge() > 18);
		//count()
//		int count = (int) persons.stream().filter(p -> p.getAge() > 18).count();
//		System.out.println("count: " + count);
		
		//collect()
//		List<Person> ps = persons.stream().filter(p -> p.getAge() > 18).collect(Collectors.toList());
//		ps.forEach(p -> System.out.println(p.toString()));
		List<Person> ps = persons.stream().filter(p -> p.getAge() > 18).parallel().collect(Collectors.toList());
		ps.forEach(p -> System.out.println(p.toString()));
		
	}
	
}
