package com.cx.day16.seq;

import java.io.Serializable;

public class Dog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1552044992135630387L;
	/**
	 * 
	 */
	String name;
	int age;
	String color;
	private Cat partner;
	private Person owner;
	public Cat getPartner() {
		return partner;
	}
	public void setPartner(Cat partner) {
		this.partner = partner;
	}
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Dog(String name, int age) {
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
