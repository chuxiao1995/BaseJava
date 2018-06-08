package com.cx.day16.seq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestDogSerialize {
	/**
	 * 串行化dog对象
	 */
	@Test
	public void testSer() throws Exception {
		FileOutputStream fos = new FileOutputStream("D://dog.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Dog dog = new Dog("大黄", 3);
		oos.writeObject(dog);
		oos.close();
		fos.close();
	}

	@Test
	public void testDeSer() throws Exception {
		FileInputStream fis = new FileInputStream("D://dog.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Dog dog = (Dog) ois.readObject();// 反序列化，不经过构造函数
		System.out.println(dog.getName());
		System.out.println(dog.getAge());
		System.out.println(dog.getColor());
		ois.close();
		fis.close();
	}
	/**
	 * 深度复制，复制若干个相关联的对象。浅度复制，复制的目标指向同一个对象
	 * @throws Exception
	 */

	@Test
	public void testSer2() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		Dog dog = new Dog("大黄", 3);
		Person p = new Person();
		Cat cat = new Cat("加肥", 4);
		dog.setOwner(p);
		oos.writeObject(dog);
		oos.close();
		baos.close();
		//
		byte[] bytes = baos.toByteArray();
		System.out.println();
		//反串行化
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Dog copyDog = (Dog) ois.readObject();
		ois.close();
		bais.close();
		System.out.println();

	}

}
