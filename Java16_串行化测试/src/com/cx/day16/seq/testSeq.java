package com.cx.day16.seq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class testSeq {
	/**
	 * == 与instanceof
	 */
	public void testClass() {
		Jing8 j8 = new Jing8();
		Class clazz = j8.getClass();
		System.out.println(clazz == Dog.class);
		System.out.println(j8 instanceof Dog);
		
	}
	class Dog{
		
	}
	class Jing8 extends Dog{
		
	} 
	/**
	 * 测试序列
	 * @throws Exception 
	 */
	@Test
	public void testSerialize() throws Exception {
		Integer i =100;
		FileOutputStream fos = new FileOutputStream("D://seq.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(i);
		oos.close();
		fos.close();
	}
	/**
	 * 测试反序列
	 * 
	 * 
	 */
	@Test
	public void testDeSerialize() throws Exception {
		FileInputStream fis = new FileInputStream("D://seq.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Integer ii = (Integer)ois.readObject();
		System.out.println(ii);
		ois.close();
		fis.close();
	}

}
