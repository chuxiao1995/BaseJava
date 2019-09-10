package com.cx.java21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class TestJDBC {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/big3";
			String user = "root";
			String pass = "123456";
			Connection conn =DriverManager.getConnection(url,user,pass);
			Statement st = conn.createStatement();
			st.execute("delete from user where id =2");
			st.close();
			System.out.println("É¾³ýover");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
