package com.chuxiao.java25.proxy.duration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyMain {
	public static void main(String[] args) {
		// Ŀ�����
		MyServiceImpl s = new MyServiceImpl();
		// ����������
		TimeHandler h = new TimeHandler(s);
		// ����
		Class[] clazz = { MyService.class, YourService.class };

		MyService ms = (MyService) Proxy.newProxyInstance(
				TimeHandler.class.getClassLoader(), clazz, h);
		int r = ms.add(1, 1);
		System.out.println(r);
		
		System.out.println(((YourService)ms).multiply(1, 2));
		System.out.println(((YourService)ms).divide(1, 2));

	}

	/**
	 * ʱ�䴦����
	 * 
	 */
	static class TimeHandler implements InvocationHandler {
		// Ŀ�����
		private Object target;

		public TimeHandler(Object target) {
			this.target = target;
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			long start = System.nanoTime();
			//����Ŀ�귽��
			Object retVal = method.invoke(target, args);
			System.out.println(method.getName() + " : "
					+ (System.nanoTime() - start));
			// ����Ŀ����󷽷��ķ���ֵ������۸���ҵ���߼���
			return retVal;
		}

	}

}
