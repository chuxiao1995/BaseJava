package ��ʮ����_����;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Set ���򣬲��ظ�
 *
 */
public class TestSet {
	@Test
	public void TestSet1() {
		Set<String> set = new HashSet<String>();
		set.add("tom1");
		set.add("tom2");
		set.add("tom3");
		set.add("tom4");
		Iterator<String> it = set.iterator();// ������
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

	@Test
	public void testHashCode() {
		Dog d = new Dog();
		System.out.println(d.hashCode());
		d = new Dog();
		System.out.println(d.hashCode());
		d = new Dog();
		System.out.println(d.hashCode());

	}

	public void testHashSet() {

	}

	@Test
	public void testHashSet3() {
		Set<Dog> set = new HashSet<Dog>();
		Dog dog = new Dog(20, 40);
		set.add(/* new Dog(20,40) */dog);
		set.add(/* new Dog(20,40) */dog);
		System.out.println(set.size());

	}
@Test
	public void testAddInterger() {//��ӡֵ˳�������Ϊ������hashֵ�㷨�����䱾��
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < 17; i++) {
			set.add(i);
		}
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			
		}
		

	}

}

/**
 * 
 * shift+ alt + s ��ݼ�����
 *
 */
class Dog {
	int x = 1;
	public int height;
	public int weight;

	public Dog(int height, int weight) {
		super();
		this.height = height;
		this.weight = weight;
	}

	public Dog() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public int hashCode() { return x++; }
	 */
	// @Override
	// public boolean equals(Object obj) {
	// return false;
	// }

}