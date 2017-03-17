package com.bran.java8;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Test {

	public static void main(String[] args) {
	
		String src = "brandon4he";
		
		String encoded = Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
		System.out.println(encoded);
		
		String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
		System.out.println(decoded);
	}
}
