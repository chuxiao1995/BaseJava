package com.example.tutorial;

import java.io.Serializable;
import java.util.List;

import com.example.tutorial.java.Person;

public class AddressBook implements Serializable{
	List<Person> persons;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	

}
