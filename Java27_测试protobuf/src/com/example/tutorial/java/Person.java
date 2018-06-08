package com.example.tutorial.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable{
	private int id; 
	private String name;
	private String email;
	private List<PhoneNumber> phone = new ArrayList<Person.PhoneNumber>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static enum PhoneType{
		MOBILE,
		HOME,
		WORK;
	}
	
	//µç»°ºÅÂë
	public static class PhoneNumber implements Serializable{
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public PhoneType getType() {
			return type;
		}
		public void setType(PhoneType type) {
			this.type = type;
		}
		String number = "1";
		PhoneType type = PhoneType.HOME;
	}
	public  void addPhone(PhoneNumber num) {
		this.phone.add(num);
		
	}
	

}
