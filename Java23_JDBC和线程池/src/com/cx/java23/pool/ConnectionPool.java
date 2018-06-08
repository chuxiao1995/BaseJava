package com.cx.java23.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

	private int max = 2;
	//Connection集合
	private List<Connection> connections = new ArrayList<Connection>();

	private String dirver;
	private String url;
	private String user;
	private String pass;

	public ConnectionPool(String dirver, String url, String user, String pass) {
		this.dirver = dirver;
		this.url = url;
		this.user = user;
		this.pass = pass;
		try {
			Class.forName(dirver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 初始化
	 */
	public void init() {
		try {
			for (int i = 0; i < max; i++) {
				Connection conn = DriverManager.getConnection(url,user,pass);
				connections.add(new WrappedConnection(conn,this));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 回收连接
	 * 
	 */
	public synchronized void add(WrappedConnection c) {
		connections.add(c);
	}

	/**
	 * 剪切第一个元素,返回一个连接
	 * @return
	 */
	public synchronized Connection get() {
		if (connections.isEmpty()) {
			return null;
		}
		return connections.remove(0);
	}

}
