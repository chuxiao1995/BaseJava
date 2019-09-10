package com.cx.day16.seq;

import java.io.Serializable;

public class Person implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 8485209998998359836L;
private String name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

}
