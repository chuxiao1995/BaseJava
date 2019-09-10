package com.cx.java19.qq.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MessageSender {

	private Socket sock;
	private OutputStream out;

	public MessageSender(Socket sock) {
		try {
			this.sock = sock;
			this.out = sock.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * ·¢ËÍÏûÏ¢
	 */
	public void sendMessage(byte[] msg) {
		try {
			out.write(msg);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	
	
}
