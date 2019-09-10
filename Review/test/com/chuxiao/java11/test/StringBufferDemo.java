package com.chuxiao.java11.test;

public class StringBufferDemo {
public void test() {
	//�̰߳�ȫ
	StringBuffer buffer = new StringBuffer();
	buffer.append("abc");
	buffer.append("def");
	buffer.append("�й���");
	System.out.println(buffer.toString());
	
	//�̲߳���ȫ
	StringBuilder builder = new StringBuilder();
	builder.append("abc")
	.append("def").append("�й���")
	.insert(0, "hello");
	builder.delete(1, builder.length());
	System.out.println(builder.toString());
	
	
}
}
