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
	 * ѹ��
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
		System.out.println("ѹ�����");
	}

	/**
	 * ��ѹ��
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUnzip() throws Exception {
		FileInputStream fin = new FileInputStream("d:/ziptest/ziptest1.zip");
		// ��ѹ����
		ZipInputStream zin = new ZipInputStream(fin);

		FileOutputStream fos = new FileOutputStream("d:/ziptest/ziptest2.txt");
		byte[] buf = new byte[1024];
		int len = 0;
		// ��ȡ��һ����Ŀ,zin��ÿ��entry����ʱ����-1���Զ�������һ�ζ�ȡ
		zin.getNextEntry();
		while ((len = zin.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		fos.close();
		zin.close();
		fin.close();
	}

	/**
	 * ץ��
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPrintScreen() throws Exception {
		// ��������
		Robot robot = new Robot();

		Rectangle rect = new Rectangle(0, 0, 1366, 768);

		BufferedImage image = robot.createScreenCapture(rect);

		ImageIO.write(image, "jpg", new FileOutputStream("d:/12.jpg"));

	}

	/**
	 * ����ѹ��
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

		//ѭ���ļ�
		for (String s : arr) {
			//�����ļ�����
			f = new File(s);

			//����ѹ����Ŀ
			entry = new ZipEntry(f.getName());
			zos.putNextEntry(entry);

			//��ʼѹ���ļ�
			fin = new FileInputStream(s);
			while ((len = fin.read(buf)) != -1) {
				zos.write(buf);
			}
			fin.close();
			zos.closeEntry();

		}
		zos.close();
		fos.close();
		System.out.println("ѹ�����");

	}

	/**
	 * ���Խ�ѹ��
	 * 
	 * @throws Exception
	 */

	@Test
	public void testUnzip1() throws Exception {
		FileInputStream fin = new FileInputStream("d:/ziptest/test.zip");
		// ��ѹ����
		ZipInputStream zin = new ZipInputStream(fin);

		FileOutputStream fos = null;
		ZipEntry entry = null;

		byte[] buf = new byte[1024];
		int len = -1;
		while ((entry = zin.getNextEntry()) != null) {
			// ��ѹ����
			fos = new FileOutputStream("d:/ziptest/arch/" + entry.getName());
			while ((len = zin.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.close();
			zin.closeEntry();

		}
		zin.close();
		fin.close();
		System.out.println("��ѹ��over");
	}

}
