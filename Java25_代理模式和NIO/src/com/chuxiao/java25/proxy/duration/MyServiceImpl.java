package com.chuxiao.java25.proxy.duration;

public class MyServiceImpl implements MyService, YourService {
	public int add(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}

	@Override
	public int multiply(int a, int b) {
		return a * b;
	}

	@Override
	public float divide(int a, int b) {
		return (float) a / b;
	}

}
