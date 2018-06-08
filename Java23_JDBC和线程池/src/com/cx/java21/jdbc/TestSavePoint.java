package com.cx.java21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import org.junit.Test;

public class TestSavePoint {
	@Test
	public void testSavePoint() {
		Connection conn = null;
		Statement st = null;
		try {
			conn = getConnection();		
			conn.setAutoCommit(false);		//自动提交
			st = conn.createStatement();
			st.execute("insert into user(name,age) values('jerry',15)");
			Savepoint s1 = conn.setSavepoint("1");
			st.execute("insert into user(name,age) values('jerry',15)");
			Savepoint s2 = conn.setSavepoint("2");
			st.execute("insert into user(name,age) values('jerry',15)");
			Savepoint s3 = conn.setSavepoint("3");
			st.execute("insert into user(name,age) values('jerry',15)");
			Savepoint s4 = conn.setSavepoint("4");
			conn.rollback(s2);
			conn.commit();
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 测试查询
	 * @throws Exception 
	 */
	@Test
	public void testSelect() throws Exception {
		Connection conn = getConnection();		
		 conn.setAutoCommit(false);	
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from user");
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println(id + ","+ name + ","+ age);
		}
		rs.close();
		conn.close();
		st.close();
		conn.close();
	}
	/**
	 * 
	 * 测试查询(sql注入)
	 */
	@Test
	public void testSelect2() throws Exception {
		Connection conn = getConnection();		
		conn.setAutoCommit(false);	
		Statement st = conn.createStatement();
		String name = "1' or 1=1 -- ";
		String pwd = "123";
		ResultSet rs = st.executeQuery("select * from user where name = '"+name+"' and password= '" + pwd +"'");
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String name0 = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println(id + ","+ name0 + ","+ age);
		}
		rs.close();
		conn.close();
		st.close();
		conn.close();
	}
	
	

	/**
	 * 执行Test标准的方法前先调用
	 */
	private static void loadDriverClass() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得连接
	 */
	private static Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/big3";
			String user = "root";
			String pass = "123456";
			Connection conn = DriverManager.getConnection(url, user, pass);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 插入
	 */
	public static void insertData() {
		Connection conn = null;
		Statement st = null;
		try {
			conn = getConnection();
			st = conn.createStatement();
			st.execute("delete from test where id =2");
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null && !st.isClosed()) {
					st.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void insertTx() {
		Connection conn = null;
		Statement st = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			st.execute("delete from test where id =2");
			conn.commit();
			st.close();
			conn.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (st != null && !st.isClosed()) {
					st.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		//loadDriverClass();
		insertData();
	}

}
