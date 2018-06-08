package com.chuxiao.java25.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestNIO {
	/**
	 * 测试堆内存缓冲区
	 * 
	 */
	@Test
	public void testHeapByteBuffer() {
		int size = 1024 * 1024 * 200;
		ByteBuffer buf = ByteBuffer.allocate(size);
		System.out.println(buf.capacity());
	}

	/**
	 * 
	 */
	@Test
	public void testDirectByteBuffer() {
		int size = 1024 * 1024 * 1024 * 1;
		ByteBuffer buf = ByteBuffer.allocateDirect(size);
		System.out.println(buf.capacity());
	}

	/**
	 * 测试垃圾回收
	 */
	public void testGC() {
		int size = 1024 * 1024 * 1;
		byte[] buf = new byte[size];
		byte[] buf2 = buf;
		List<byte[]> list = new ArrayList<byte[]>();
		list.add(buf2);
		buf = null;
		System.gc();
		buf2 = null;
		System.gc();
		System.out.println("xxx");
		list.clear(); // 清空集合
		System.gc();
		System.out.println("xxx");

	}

	/**
	 * 测试通道
	 * 
	 * @throws Exception
	 */
	@Test
	public void testChannel() throws Exception {
		FileInputStream fis = new FileInputStream("d:/1.txt");
		FileChannel srcFC = fis.getChannel();

		FileOutputStream fos = new FileOutputStream("d:/2.txt");
		FileChannel destFC = fos.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(11);
		while (srcFC.read(buf) != -1) {
			buf.flip();
			destFC.write(buf);
			buf.clear();
		}
		fis.close();
		fos.close();

	}
}
