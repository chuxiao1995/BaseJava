package com.chuxiao.java25.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class Sender extends Thread{
	SocketChannel sc;
	public Sender (SocketChannel sc){
		this.sc = sc;
	}
	public void run() {
		try {
			//∂¡»°consoleƒ⁄»›£¨–¥µΩsc
			BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			ByteBuffer buf = null;
			while ((line = br.readLine()) != null) {
				buf = ByteBuffer.allocate(1024);
				buf.put(line.getBytes());
				buf.flip();
				sc.write(buf);
				buf.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
