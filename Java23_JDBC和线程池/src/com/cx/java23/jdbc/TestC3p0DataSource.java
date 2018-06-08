package com.cx.java23.jdbc;

import java.beans.PropertyVetoException;
import java.security.spec.DSAGenParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestC3p0DataSource {
	public static void main(String[] args) {
		try {
			ComboPooledDataSource ds = new ComboPooledDataSource();
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setJdbcUrl("jdbc:mysql://localhost:3306/big3");;
			ds.setUser("root"); 
			ds.setPassword("123456");
			
			
			ds.setMaxPoolSize(10);		//���
			ds.setMinPoolSize(10);		//��С
			ds.setInitialPoolSize(3);	//��ʼ��������
			ds.setAcquireIncrement(2);	//����
			
			
			Connection conn = ds.getConnection();
			PreparedStatement ppst = conn.prepareStatement("insert into customers(name) values(v1)");
			ppst.executeUpdate();
			ppst.close();
			conn.close();
			
			conn = ds.getConnection();
			ppst = conn.prepareStatement("insert into customers(name) values(v2)");
			ppst.executeUpdate();
			ppst.close();
			conn.close();
			
			conn = ds.getConnection();
			ppst = conn.prepareStatement("insert into customers(name) values(v3)");
			ppst.executeUpdate();
			ppst.close();
			conn.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
