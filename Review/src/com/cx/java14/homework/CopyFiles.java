package com.cx.java14.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class CopyFiles {
	@Test
	public void test() throws Exception {
		copyDir("d:/arch", "e:/");
	}

	/**
	 * Ŀ¼�ṹ���
	 * 
	 * @param file
	 * @throws Exception
	 */
	private void copyDir(String file, String destDir) throws Exception {
		File f = new File(file);
		if (f.exists()) {
			if (f.isDirectory()) {
				File newfile = new File(destDir, f.getName());// ��Ŀ��·���£��½�һ���ļ���
				newfile.mkdirs();
				File[] ff = f.listFiles();
				if (ff != null && ff.length > 0) {
					for (File f0 : ff) {
						copyDir(f0.getAbsolutePath(), newfile.getAbsolutePath());
					}
				}
			} else {
				testCopyFile(file, destDir);
			}
		}
	}

	/**
	 * �����ļ�
	 *
	 * @param file
	 * @param targetFile
	 * @throws Exception
	 */
	public void testCopyFile(String file, String destFile) throws Exception {
		// ����һ���ļ�·����һ���ļ���·��
		File f = new File(file);
		File newFile = new File(destFile, f.getName());// �����ļ��в������ļ�
		FileInputStream fis = new FileInputStream(f);
		FileOutputStream fos = new FileOutputStream(newFile);
		int len = 0;// ������ȡ���ݵ���
		byte[] buffer = new byte[1024];//
		while ((len = fis.read(buffer)) != -1/* len���ر���ת�Ƶ��ֽ���-1�����Ѿ�ת����� */) {
			fos.write(buffer, 0, len);
			System.out.println(len);
		}
		fis.close();
		fos.close();
		// System.out.println("�������");
	}

}
