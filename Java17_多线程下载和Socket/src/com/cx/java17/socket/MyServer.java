package com.cx.java17.socket;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class MyServer {
	/**
	 * ��������
	 */
	@Test
	public void start() {
		try {
			ServerSocket ss = new ServerSocket(8888);
			while (true) {
				Socket s = ss.accept();
				new CommunicationThread(s).start();
			}
			/*
			 * while (true) { Socket s = ss.accept();
			 * System.out.println("�������ˣ�"); InputStream is = s.getInputStream();
			 * InputStreamReader reader = new InputStreamReader(is); char[] buf
			 * = new char[1024]; int len = 0; while ((len = reader.read(buf))!=
			 * -1) { System.out.println(new String(buf,0,len)); }
			 * 
			 * }
			 */} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
