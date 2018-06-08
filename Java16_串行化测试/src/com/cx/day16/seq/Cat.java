package com.cx.day16.seq;

import java.io.Serializable;

public class Cat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2644533784112199552L;
	/**
	 * 
	 */
	private String name;
	private int age;
	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

	

}
