package com.cx.day16;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Random;

import org.junit.Test;

/**
 * 测试随机访问文件
 * 
 * @author 86152
 *
 */
public class testRandomAccessFile {
	@Test
	public void test1() throws Exception {
		RandomAccessFile raf = new RandomAccessFile("d:/test.txt", "rw");
		// raf.seek(3);
		byte[] buf = new byte[2];
		int len = 0;
		while ((len = raf.read(buf)) != -1) {
			System.out.println(new String(buf, 0, len));
		}
		raf.seek(raf.getFilePointer() - 1);// 当前指针-1
		len = 0;
		while ((len = raf.read(buf)) != -1) {
			System.out.println(new String(buf, 0, len));
		}
		raf.close();

	}

	/**
	 * 设置文件大小
	 * 
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		try {
			RandomAccessFile raf = new RandomAccessFile("d:/test1.txt", "rw");
			byte[] buf = new byte[10];
			raf.setLength(1024*1024*1024);
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void test3() throws Exception {
		try {
			RandomAccessFile raf = new RandomAccessFile("d:/test1.txt", "rw");
			byte[] buf = new byte[10];
			raf.read(buf);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
