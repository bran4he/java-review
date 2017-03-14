package com.bran.java81;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		lambdaTest();
//		lambdaSortTest();
		streamTest();
	}

	public static void streamTest(){
		List<String> lst = Arrays.asList(new String[]{"hello", "this", "world"});

		String outParam = "outer";
		List<String> newList = lst.stream().map(inner -> {
			Long sefDef = System.currentTimeMillis();
			return outParam +":"+ inner + ":" + sefDef;
		}).collect(Collectors.toList());
		
		newList.forEach(System.out::println);
	}
	
	public static void lambdaTest(){
		new Thread(() -> {System.out.println("new thread running");}).start();
	}
	
	public static void lambdaSortTest(){
		List<String> lst = new ArrayList<String>();
		lst.add("a");
		lst.add("t");
		lst.add("r");
		lst.add("e");
		lst.add("E");
		lst.add("A");
		//#1
//		lst.sort((str1, str2) -> str1.compareTo(str2));	//[A, E, a, e, r, t]
//		lst.sort(String::compareToIgnoreCase);	//[a, A, e, E, r, t]
//		lst.sort(Comparator.naturalOrder());	//[A, E, a, e, r, t]
		//#2
		/*
		Collections.sort(lst, new Comparator<String>() {
			@Override
			public int compare(String o1,String o2) {
				return o1.compareTo(o2);
			}
		});
		*/
		Collections.sort(lst,(str1, str2) -> str1.compareTo(str2));	//[A, E, a, e, r, t]
		System.out.println(lst);
		lst.forEach(System.out::println);
		
	}
}
