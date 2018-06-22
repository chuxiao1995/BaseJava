package com.cx.java21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/**
 * �������Ӳ�ѯ
 * 
 * @author 86152
 *
 */
public class TestJoin {
	/**
	 * ������
	 */
	@Test
	public void testjoin() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/big3";
			String user = "root";
			String pass = "123456";
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select a.* ,b.* from customers a,orders b where a.id = b.cid order by a.id,b.id";
			PreparedStatement ppst = conn.prepareStatement(sql);
			ResultSet rs = ppst.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String borderno = rs.getString("orderno");
				float bprice = rs.getFloat("price");
				System.out.println(id + " , " + name + " , " + borderno + " , "
						+ bprice);
			}
			rs.close();
			ppst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��������
	 */
	@Test
	public void testLeftOutJoin() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/big3";
			String user = "root";
			String pass = "123456";
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select a.*,b.* from customers a left outer join orders b on a.id = b.cid";
			PreparedStatement ppst = conn.prepareStatement(sql);
			ResultSet rs = ppst.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String borderno = rs.getString("orderno");
				float bprice = rs.getFloat("price");
				System.out.println(id + " , " + name + " , " + borderno + " , "
						+ bprice);
			}
			rs.close();
			ppst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���Ը��뼶��
	 */
	@Test
	public void testDirtyReadA() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/big3";
			String user = "root";
			String pass = "123456";
			Connection conn = DriverManager.getConnection(url, user, pass);
			//��������ĸ��뼶��
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			String sql = "select * from customers where id =1 ";
			PreparedStatement ppst = conn.prepareStatement(sql);
			ResultSet rs = ppst.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println(id + " , " + name);
			}
			rs.close();
			ppst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���Ը��뼶��
	 */
	@Test
	public void testDirtyReadB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/big3";
			String user = "root";
			String pass = "123456";
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			//�ر��Զ��ύ
			conn.setAutoCommit(false);
			String sql = "update customers set name = 'xxx' where id = 1";
			PreparedStatement ppst = conn.prepareStatement(sql);
			ppst.executeUpdate();
			System.out.println("---------------------");
			conn.close();
			ppst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
