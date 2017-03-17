package com.bran.java8;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Java8Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		parallelSort();
	}
	
	public static void parallelSort(){
		long[] array = new long[20000];
		
		Arrays.parallelSetAll(array, 
				index -> ThreadLocalRandom.current().nextInt(1000000));
		
		Arrays.stream(array).limit(10).forEach(it -> System.out.print(it + " "));
		System.out.println("---------------------");
		
		long start = System.currentTimeMillis();
		Arrays.parallelSort(array);
		System.out.println("sort takes:" + (System.currentTimeMillis() - start));
		
		Arrays.stream(array).limit(10).forEach(it -> System.out.print(it + ": "));
	}

}
