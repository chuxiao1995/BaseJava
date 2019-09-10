package com.chuxiao.java25;

public class Person {
	private String Name ;
	private int age = 20;
	
	public Person() {
		System.out.println("000");
	}
	private Person(String name) {
		System.out.println("111");
	}
	private Person(String name,int age) {
		System.out.println("222");
	}
	private Person(String name,double age) {
		System.out.println("333");
	}
	private Person(String name,Integer age) {
		System.out.println("444");
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public void setName(String name,String name2) {
		this.Name = name +" : "+ name2;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
