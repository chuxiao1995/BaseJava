package com.chuxiao.java25.proxy.duration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyMain {
	public static void main(String[] args) {
		// 目标对象
		MyServiceImpl s = new MyServiceImpl();
		// 处理器对象
		TimeHandler h = new TimeHandler(s);
		// 数组
		Class[] clazz = { MyService.class, YourService.class };

		MyService ms = (MyService) Proxy.newProxyInstance(
				TimeHandler.class.getClassLoader(), clazz, h);
		int r = ms.add(1, 1);
		System.out.println(r);
		
		System.out.println(((YourService)ms).multiply(1, 2));
		System.out.println(((YourService)ms).divide(1, 2));

	}

	/**
	 * 时间处理器
	 * 
	 */
	static class TimeHandler implements InvocationHandler {
		// 目标对象
		private Object target;

		public TimeHandler(Object target) {
			this.target = target;
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			long start = System.nanoTime();
			//调用目标方法
			Object retVal = method.invoke(target, args);
			System.out.println(method.getName() + " : "
					+ (System.nanoTime() - start));
			// 返回目标对象方法的返回值，否则篡改了业务逻辑。
			return retVal;
		}

	}

}
