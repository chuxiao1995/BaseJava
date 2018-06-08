package com.chuxiao.java25.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
/**
 * ����ģʽ
 * @author 86152
 *
 */
public class TestProxy {

	@Test
	public void test1() {
		// Ŀ�����
		HelloWorldServiceImpl s = new HelloWorldServiceImpl();
		// ���ô�����
		MyInvocationHandler h = new MyInvocationHandler(s);
		// �ӿ��б�
		Class[] clazzes = { HelloWorldService.class };
		// �����������
		HelloWorldService hws = (HelloWorldService) Proxy.newProxyInstance(
				ClassLoader.getSystemClassLoader(), clazzes, h);
		//���ʴ���ķ���
		hws.sayHello("tom");
	}

	
	// ���������
	class MyInvocationHandler implements InvocationHandler {
		// Ŀ�����
		private Object target;
		public MyInvocationHandler(Object target) {
			this.target = target;
		}
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("hello-world");
			// ����Ŀ�����ķ���
			return method.invoke(target, args);
		}
	}
}
