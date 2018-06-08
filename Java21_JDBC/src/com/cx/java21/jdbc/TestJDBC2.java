package com.cx.java21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC2 {
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
		loadDriverClass();
		insertData();
	}

}
