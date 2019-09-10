package com.chuxiao.java24.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.chuxiao.java24.jvm.Person;

public class TestReflect {
	/**
	 * 测试Class
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
	 * 动态访问对象的属性和方法 反射的出发点是类，方法，字段。
	 */
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testNewInstance() throws Exception {
		// 加载类，返回Class对象
		Class clazz = Class.forName("com.chuxiao.java24.jvm.Person");
		// 通过Class创建对象
		Object obj = clazz.newInstance();
		// 得到setName方法
		Method m = clazz.getDeclaredMethod("setName", String.class);
		// 得到getname方法
		Method n = clazz.getDeclaredMethod("getName");

		// 设置可访问性,可以调用私有方法,setName()为私有方法
		m.setAccessible(true);

		// 动态取得getName方法
		m.invoke(obj, "tom");
		System.out.println(n.invoke(obj));

		// Field,字段描述符
		Field f = clazz.getDeclaredField("Name");
		f.setAccessible(true);
		Object ret = f.get(obj);
		System.out.println("ret :" + ret);

		f.set(obj, "jerry");
		Object ret1 = f.get(obj);
		System.out.println("obj :" + ret1);
		System.out.println("======================================");
		// 得到所有可用方法
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
		// 得到该类声明的方法
		ms = clazz.getDeclaredMethods();
		for (Method method : ms) {
			System.out.println(method);
		}

	}

	/**
	 * Constructor构造函数描述符
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
	 * 属性复制
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
	 * 属性复制
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
