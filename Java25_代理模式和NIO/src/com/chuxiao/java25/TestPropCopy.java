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
		// �õ�a��������з���
		Method[] ms = a.getClass().getDeclaredMethods();
		//
		Class bclazz = b.getClass();
		for (Method m : ms) {
			//�õ�������
			String mname = m.getName();
			//��������
			Class[] paramTypes = m.getParameterTypes();
			//�õ�����ֵ����
			Class retType = m.getReturnType();
			//���˱�׼��getXXX����
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
