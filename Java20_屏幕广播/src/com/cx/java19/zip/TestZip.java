package com.cx.java19.zip;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

public class TestZip {
	/**
	 * 压缩
	 * 
	 * @throws Exception
	 */
	@Test
	public void testZip() throws Exception {
		FileOutputStream fos = new FileOutputStream("d:/ziptest/ziptest1.zip");
		ZipOutputStream zos = new ZipOutputStream(fos);

		FileInputStream fin = new FileInputStream("d:/ziptest/ziptest1.txt");
		byte[] buf = new byte[1024];
		int len = -1;

		zos.putNextEntry(new ZipEntry("xxx"));
		while ((len = fin.read(buf)) != -1) {
			zos.write(buf, 0, len);
		}
		fin.close();
		zos.close();
		fos.close();
		System.out.println("压缩完成");
	}

	/**
	 * 解压缩
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUnzip() throws Exception {
		FileInputStream fin = new FileInputStream("d:/ziptest/ziptest1.zip");
		// 解压缩流
		ZipInputStream zin = new ZipInputStream(fin);

		FileOutputStream fos = new FileOutputStream("d:/ziptest/ziptest2.txt");
		byte[] buf = new byte[1024];
		int len = 0;
		// 获取下一个条目,zin在每次entry结束时返回-1，自动进行下一次读取
		zin.getNextEntry();
		while ((len = zin.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		fos.close();
		zin.close();
		fin.close();
	}

	/**
	 * 抓屏
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPrintScreen() throws Exception {
		// 机器人类
		Robot robot = new Robot();

		Rectangle rect = new Rectangle(0, 0, 1366, 768);

		BufferedImage image = robot.createScreenCapture(rect);

		ImageIO.write(image, "jpg", new FileOutputStream("d:/12.jpg"));

	}

	/**
	 * 测试压缩
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testZip2() throws Exception {
		String[] arr = { "d:/ziptest/ziptest.png", "d:/ziptest/ziptest1.txt" };
		FileOutputStream fos = new FileOutputStream("d:/ziptest/test.zip");
		ZipOutputStream zos = new ZipOutputStream(fos);
		FileInputStream fin = null;
		ZipEntry entry = null;

		File f = null;
		byte[] buf = new byte[1024];
		int len = 0;

		//循环文件
		for (String s : arr) {
			//创建文件对象
			f = new File(s);

			//创建压缩条目
			entry = new ZipEntry(f.getName());
			zos.putNextEntry(entry);

			//开始压入文件
			fin = new FileInputStream(s);
			while ((len = fin.read(buf)) != -1) {
				zos.write(buf);
			}
			fin.close();
			zos.closeEntry();

		}
		zos.close();
		fos.close();
		System.out.println("压缩完成");

	}

	/**
	 * 测试解压缩
	 * 
	 * @throws Exception
	 */

	@Test
	public void testUnzip1() throws Exception {
		FileInputStream fin = new FileInputStream("d:/ziptest/test.zip");
		// 解压缩流
		ZipInputStream zin = new ZipInputStream(fin);

		FileOutputStream fos = null;
		ZipEntry entry = null;

		byte[] buf = new byte[1024];
		int len = -1;
		while ((entry = zin.getNextEntry()) != null) {
			// 解压过程
			fos = new FileOutputStream("d:/ziptest/arch/" + entry.getName());
			while ((len = zin.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.close();
			zin.closeEntry();

		}
		zin.close();
		fin.close();
		System.out.println("解压缩over");
	}

}
