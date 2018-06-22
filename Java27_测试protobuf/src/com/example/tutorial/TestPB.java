package com.example.tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.example.tutorial.AddressBookProtos.Person;
import com.example.tutorial.AddressBookProtos.Person.PhoneNumber;
import com.example.tutorial.AddressBookProtos.Person.PhoneType;

public class TestPB {
	@Test
	public void testAll() {
		try {
			testSerialize();
			testPureJavaSerialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSerialize() throws Exception {
		// 使用对象
		PhoneNumber number = Person.PhoneNumber.newBuilder()
				.setType(PhoneType.MOBILE).setNumber("123456").build();
		Person p = Person.newBuilder().setId(100).setName("tom")
				.setEmail("abc@hotmail.com").addPhone(number).build();
		// 使用PB串行化对象
		FileOutputStream fos = new FileOutputStream("d:/protobuf/person.dat");
		long start = System.currentTimeMillis();
		p.writeTo(fos);
		fos.close();
		System.out.println(System.currentTimeMillis() - start + "Size : "
				+ new File("d:/protobuf/person.dat").length());
		System.out.println("============================");
	}

	@Test
	public void testPureJavaSerialize() throws Exception {
		com.example.tutorial.java.Person p = new com.example.tutorial.java.Person();
		p.setId(100);
		p.setName("tom");
		p.setEmail("abc@hotmail.com");
		com.example.tutorial.java.Person.PhoneNumber num = new com.example.tutorial.java.Person.PhoneNumber();
		num.setType(com.example.tutorial.java.Person.PhoneType.MOBILE);
		num.setNumber("123456");
		p.addPhone(num);

		// 使用纯串行化对象
		FileOutputStream fos = new FileOutputStream(
				"d:/protobuf/person_java.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		long start = System.currentTimeMillis();
		oos.writeObject(p);
		oos.close();
		System.out.println((System.currentTimeMillis() - start) + "Size : "
				+ new File("d:/protobuf/person_java.dat").length());
	}

	/**
	 * 测试反串行
	 * 
	 */
	@Test
	public void testDeserialize() throws Exception {
		// PB反串行
		long start = System.currentTimeMillis();
		Person.parseFrom(new FileInputStream("d:/protobuf/person.dat"));
		System.out.println(System.currentTimeMillis() - start);

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"d:/protobuf/person_java.dat"));
		start = System.currentTimeMillis();
		ois.readObject();
		System.out.println(System.currentTimeMillis() - start);
		ois.close();

		
	}

}
