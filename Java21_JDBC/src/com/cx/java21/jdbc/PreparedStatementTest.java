package com.cx.java21.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class PreparedStatementTest {

	/**
	 * ִ��Test��׼�ķ���ǰ�ȵ���
	 */
	private static void loadDriverClass() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �������
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
	 * ����Ԥ������� ��Ϣ����
	 */

	@Test
	public void insertTx() {
		Connection conn = null;
		PreparedStatement ppst = null;
		try {
			loadDriverClass();
			conn = getConnection();
			conn.setAutoCommit(false);
			ppst = conn
					.prepareStatement("insert into user(name,age) values(?,?)");

			ppst.setString(1, "tom");
			ppst.setInt(2, 20);

			ppst.executeUpdate();
			conn.commit();
			ppst.close();
			conn.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
	}

	/**
	 * ����Ԥ������� ������Ϣ����
	 */

	@Test
	public void testBatch() {
		int max = 50000;
		Connection conn = null;
		PreparedStatement ppst = null;
		try {
			loadDriverClass();
			conn = getConnection();
			conn.setAutoCommit(false);
			long start = System.currentTimeMillis();
			ppst = conn
					.prepareStatement("insert into user(name,age) values(?,?)");

			for (int i = 0; i < max; i++) {
				ppst.setString(1, "tom" + i);
				ppst.setInt(2, i % 20);
				ppst.executeUpdate();
			}

			conn.commit();
			System.out.println(System.currentTimeMillis() - start);
			ppst.close();
			conn.close();
			System.out.println("�������");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
	}

	/**
	 * ���Դ����Ĳ��룬ʹ�����ķ�ʽ
	 * 
	 */

	@Test
	public void testSavePic() {
		Connection conn = null;
		PreparedStatement ppst = null;
		try {
			loadDriverClass();
			conn = getConnection();
			conn.setAutoCommit(false);
			ppst = conn
					.prepareStatement("insert into user(name,pic,info) values(?,?,?)");
			ppst.setString(1, "tompic");
			// ���ô����
			File file = new File("d:/1.png");
			FileInputStream fis = new FileInputStream(file);
			ppst.setBinaryStream(2, fis, file.length());
			ppst.setString(3, "xxxxx");
			ppst.executeUpdate();

			conn.commit();
			ppst.close();
			conn.close();
			System.out.println("�������");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
	}
	/**
	 * ���Դ����Ĳ��룬ʹ�����ķ�ʽ
	 * 
	 */
	
	@Test
	public void testSavePic2() {
		Connection conn = null;
		PreparedStatement ppst = null;
		try {
			loadDriverClass();
			conn = getConnection();
			conn.setAutoCommit(false);
			ppst = conn
					.prepareStatement("select pic from user where id = ?");
			ppst.setInt(1, 1);
			//query��ѯ���ؽ����
			ResultSet rs = ppst.executeQuery();
			if (rs.next()) {
				byte[] bytes = rs.getBytes(1);
				FileOutputStream fos = new FileOutputStream("d:/sql.png");
				fos.write(bytes);
				fos.close();
			}
			conn.commit();
			ppst.close();
			conn.close();
			System.out.println("����");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
