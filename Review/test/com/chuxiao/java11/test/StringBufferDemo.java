package com.chuxiao.java11.test;

public class StringBufferDemo {
public void test() {
	//线程安全
	StringBuffer buffer = new StringBuffer();
	buffer.append("abc");
	buffer.append("def");
	buffer.append("中国人");
	System.out.println(buffer.toString());
	
	//线程不安全
	StringBuilder builder = new StringBuilder();
	builder.append("abc")
	.append("def").append("中国人")
	.insert(0, "hello");
	builder.delete(1, builder.length());
	System.out.println(builder.toString());
	
	
}
}
