package com.chuxiao.java25;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class TestNIO {
	/**
	 * ≤‚ ‘Õ®µ¿
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
