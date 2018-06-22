package com.chuxiao.java25;

import java.lang.reflect.Method;

import org.junit.Test;

public class TestPropCopy {
	@Test
	public void test1() {
		Person p1 = new Person();
		p1.setName("tom");
		p1.setAge(12);
		Person p2 = new Person();
		copyProperties(p1, p2);
		System.out.println("over");
		
	}
	
	
	public void copyProperties(Object a, Object b) {
		// 得到a定义的所有方法
		Method[] ms = a.getClass().getDeclaredMethods();
		//
		Class bclazz = b.getClass();
		for (Method m : ms) {
			//得到方法名
			String mname = m.getName();
			//参数类型
			Class[] paramTypes = m.getParameterTypes();
			//得到返回值类型
			Class retType = m.getReturnType();
			//过滤标准的getXXX方法
			if (mname.startsWith("get")
					&& (paramTypes == null || paramTypes.length == 0)
					&& retType != void.class) {
				String bmname = mname.replace("get", "set");
				try {
					m.setAccessible(true);
					Method bm = bclazz.getDeclaredMethod(bmname, retType);
					bm.setAccessible(true);
					Object retVal = m.invoke(a);
					bm.invoke(b, retVal);
				} catch (Exception e) {
					continue;
				}
			}
		}
	}

}
