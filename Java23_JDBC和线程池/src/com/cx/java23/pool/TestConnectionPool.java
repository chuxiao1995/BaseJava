package com.cx.java23.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class TestConnectionPool {
	public static void main(String[] args) {
		//创建数据源
		try {
			DataSource ds = new MyDataSource();
			Connection conn = ds.getConnection();
			PreparedStatement ppst = conn.prepareStatement("insert into customers(name) values('k1')");
			ppst.executeUpdate();
			ppst.close();
			conn.close();
			
			conn = ds.getConnection();
			ppst = conn.prepareStatement("insert into customers(name) values('k2')");
			ppst.executeUpdate();
			ppst.close();
			conn.close();
			
			conn = ds.getConnection();
			ppst = conn.prepareStatement("insert into customers(name) values('k3')");
			ppst.executeUpdate();
			ppst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
