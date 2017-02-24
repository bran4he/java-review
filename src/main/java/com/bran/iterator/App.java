package com.bran.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class App {

	public static void main(String[] args) {
		
		String[] arr = {"1", "2", "3", "4"};
		List<String> list = new ArrayList<String>();
//		list.toArray()
		list = Arrays.asList(arr);
		
		ListIterator<String> lit = list.listIterator();
		while(lit.hasNext()){
			System.out.println(lit.next());
//			System.out.println(lit.previous());
		}
		
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
//		Hashtable t =  null;
//		Enumeration es = t.elements();
	}

}
