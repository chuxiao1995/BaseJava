package com.chuxiao.java24.jvm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestJVM {
	/**
	 * ���Զ�
	 * 
	 */
	@Test
	public void testHeap() {
		int max = 1024*1024*100;
		List<byte[]> list = new ArrayList<byte[]>();
		for (;;) {
			list.add(new byte[max]);
			
		}
		
	}
	
	/**
	 * ����ջ���
	 */
	public void testStack() {
		//print(1);
	}

}
