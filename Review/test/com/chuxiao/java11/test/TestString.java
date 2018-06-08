package com.chuxiao.java11.test;

import org.junit.Test;

public class TestString {
	@Test
	public void testStringConstr(){
		String s = new String("hello");
		char[] arr = new char[]{
				'h','o','w'
		};
		s = new String(arr,1,2);
		System.out.println(s);
		s=" \\r\\\\n\t";
		System.out.println(s.length());
		System.out.println(s.trim());
		s = "hello world";
		String[] arr1 = s.split(" ");
		for (int i = 0; i < arr1.length; i++) {
			System.out.println(arr1[i]);
		}
		
	
	}

}
