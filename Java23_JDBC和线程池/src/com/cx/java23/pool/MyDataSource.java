package com.cx.java23.pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyDataSource implements DataSource {

	private ConnectionPool pool;
	
	public MyDataSource(){
		String driver = "com.mysql.jdbc.Driver"	;
		String url = "jdbc:mysql://localhost:3306/big3";
		String user = "root";
		String pass = "123456";
		pool = new ConnectionPool(driver, url, user, pass);
		//��ʼ��
		pool.init();
		
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return pool.get();
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

}
