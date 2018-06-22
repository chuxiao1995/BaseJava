package com.chuxiao.java25.nio;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class MyClientSocketChannel {
	/**
	 * 客户端
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Selector sel = Selector.open();
		SocketChannel sc = SocketChannel.open();
		InetSocketAddress addr = new InetSocketAddress("localhost", 8888);
		sc.connect(addr);
		// 非阻塞
		sc.configureBlocking(false);
		sc.register(sel, SelectionKey.OP_READ);
		
		
		//开启发送线程
		new Sender(sc).start();
		
		
		ByteBuffer buf0 = ByteBuffer.allocate(1024);
		
		buf0.put("helloworld".getBytes());
		buf0.flip();
		sc.write(buf0);
		System.out.println("client send over");
		ByteBuffer buf = ByteBuffer.allocate(1024);
		// 开始挑选
		while (true) {
			sel.select();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (sc.read(buf) != 0) {
				buf.flip();
				baos.write(buf.array(), 0, buf.limit());
				buf.clear();

			}
			String str = new String(baos.toByteArray());
			System.out.println(str);
		}
	}

}
