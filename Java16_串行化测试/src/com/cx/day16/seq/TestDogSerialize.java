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
	 * ���л�dog����
	 */
	@Test
	public void testSer() throws Exception {
		FileOutputStream fos = new FileOutputStream("D://dog.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Dog dog = new Dog("���", 3);
		oos.writeObject(dog);
		oos.close();
		fos.close();
	}

	@Test
	public void testDeSer() throws Exception {
		FileInputStream fis = new FileInputStream("D://dog.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Dog dog = (Dog) ois.readObject();// �����л������������캯��
		System.out.println(dog.getName());
		System.out.println(dog.getAge());
		System.out.println(dog.getColor());
		ois.close();
		fis.close();
	}
	/**
	 * ��ȸ��ƣ��������ɸ�������Ķ���ǳ�ȸ��ƣ����Ƶ�Ŀ��ָ��ͬһ������
	 * @throws Exception
	 */

	@Test
	public void testSer2() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		Dog dog = new Dog("���", 3);
		Person p = new Person();
		Cat cat = new Cat("�ӷ�", 4);
		dog.setOwner(p);
		oos.writeObject(dog);
		oos.close();
		baos.close();
		//
		byte[] bytes = baos.toByteArray();
		System.out.println();
		//�����л�
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Dog copyDog = (Dog) ois.readObject();
		ois.close();
		bais.close();
		System.out.println();

	}

}
