package com.chuxiao.java24.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.chuxiao.java24.jvm.Person;

public class TestReflect {
	/**
	 * ����Class
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testClass() {
		Person p = new Person();
		Class clazz = p.getClass();
		Class clazz2 = Person.class;
		System.out.println(clazz.hashCode());
		System.out.println(clazz2.hashCode());
		System.out.println(clazz.getName());
	}

	/**
	 * ��̬���ʶ�������Ժͷ��� ����ĳ��������࣬�������ֶΡ�
	 */
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testNewInstance() throws Exception {
		// �����࣬����Class����
		Class clazz = Class.forName("com.chuxiao.java24.jvm.Person");
		// ͨ��Class��������
		Object obj = clazz.newInstance();
		// �õ�setName����
		Method m = clazz.getDeclaredMethod("setName", String.class);
		// �õ�getname����
		Method n = clazz.getDeclaredMethod("getName");

		// ���ÿɷ�����,���Ե���˽�з���,setName()Ϊ˽�з���
		m.setAccessible(true);

		// ��̬ȡ��getName����
		m.invoke(obj, "tom");
		System.out.println(n.invoke(obj));

		// Field,�ֶ�������
		Field f = clazz.getDeclaredField("Name");
		f.setAccessible(true);
		Object ret = f.get(obj);
		System.out.println("ret :" + ret);

		f.set(obj, "jerry");
		Object ret1 = f.get(obj);
		System.out.println("obj :" + ret1);
		System.out.println("======================================");
		// �õ����п��÷���
		Method[] ms = clazz.getMethods();
		for (Method method : ms) {
			String fname = method.getName();
			Class[] ptypes = method.getParameterTypes();
			if (fname.startsWith("get")
					&& (ptypes == null || ptypes.length == 0)) {
				ret = method.invoke(obj);
				System.out.println(fname + "=" + ret);
			}
			System.out.println(method);
		}
		System.out.println("=================================");
		// �õ����������ķ���
		ms = clazz.getDeclaredMethods();
		for (Method method : ms) {
			System.out.println(method);
		}

	}

	/**
	 * Constructor���캯��������
	 * 
	 */
	@Test
	public void testConstructor() throws Exception {
		Class vc = void.class;
		System.out.println(vc.isPrimitive());

		Class clazz = Class.forName("com.chuxiao.java24.jvm.Person");
		Constructor c1 = clazz.getDeclaredConstructor(String.class,
				Integer.class);
		c1.setAccessible(true);
		Object obj = c1.newInstance("tom", 12);
		System.out.println(obj);

		Method[] ms = clazz.getMethods();
		for (Method method : ms) {
			String fname = method.getName();
			Class rtype = method.getReturnType();
			if (rtype == void.class) {
				System.out.println(fname);
			}
			// System.out.println(method);
		}
	}
	
	
	/**
	 * ���Ը���
	 */
	@Test
	public void testCopy() throws Exception {
		Person a = new Person();
		a.setAge(12);
		a.setName("tom");

		Person b = new Person();
		propCopy(a, b);
		System.out.println("====");

	}

	/**
	 * ���Ը���
	 */
	public void propCopy(Person a, Person b) throws Exception {
		Class clazz = a.getClass();
		Field[] fs = clazz.getDeclaredFields();
		for (Field field : fs) {
			field.setAccessible(true);
			Object ret = field.get(a);
			field.set(b, ret);

		}

	}

}
