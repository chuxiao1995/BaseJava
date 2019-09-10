package com.chuxiao.java11.test;

import java.nio.charset.Charset;

import org.junit.Test;

public class CharsetDemo {
	@Test
	public void test1()throws Exception {
		Charset csCharset =Charset.defaultCharset();
		System.out.println(csCharset.name());
		String s = "abc中";
		//编码过程
		byte[] arr = s.getBytes("gbk");
		//解码过程
		String str2 = new String(arr,"gbk");
		System.out.println(str2);
		char c = '\u0061';
		char a = '\u891a';
		char b = '\u6f47';
		System.out.println(a+""+b);		
		System.out.println(c);
		System.out.println();		
		//System.out.println(arr.length);
		
	}

}
