package com.cx.java21.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;

import org.junit.Test;

import java.sql.CallableStatement;

public class CallableStatementTest {

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
		try {
			loadDriverClass();
			conn = getConnection();
			CallableStatement cst = conn.prepareCall("{call up_add(?,?,?)}");
			cst.setInt(1, 1);
			cst.setInt(2, 3);
			cst.registerOutParameter(3, Types.INTEGER);

			// ִ�д洢����
			cst.execute();
			int r = cst.getInt(3);
			System.out.println(r);
			cst.close();
			conn.close();
		} catch (Exception e) {
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
	 * ʹ�ô洢����
	 * 
	 */

	@Test
	public void testBigInsert() {
		Connection conn = null;

		try {
			loadDriverClass();
			conn = getConnection();
			CallableStatement cst = conn.prepareCall("{call up_biginsert(?)}");
			cst.setInt(1, 10000);
			cst.execute();
			cst.close();
			conn.close();
			System.out.println("����");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * ɾ���洢����
	 * 
	 */

	@Test
	public void testDropProc() {
		Connection conn = null;
		PreparedStatement ppst = null;
		try {
			loadDriverClass();
			conn = getConnection();
			ppst = conn.prepareStatement("drop procedure up_add");
			ppst.executeUpdate();

			ppst.close();
			conn.close();
			System.out.println("����");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * /** ʹ�ô洢����
	 * 
	 */

	@Test
	public void testCallFunc() {
		Connection conn = null;

		try {
			loadDriverClass();
			conn = getConnection();
			CallableStatement cst = conn.prepareCall("{ ? = call uf_add(?,?)}");
			cst.setInt(2, 1);
			cst.setInt(3, 2);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.execute();
			cst.close();
			conn.close();
			System.out.println(cst.getInt(1));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
