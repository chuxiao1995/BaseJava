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
	 * 目录结构输出
	 * 
	 * @param file
	 * @throws Exception
	 */
	private void copyDir(String file, String destDir) throws Exception {
		File f = new File(file);
		if (f.exists()) {
			if (f.isDirectory()) {
				File newfile = new File(destDir, f.getName());// 在目标路径下，新建一个文件夹
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
	 * 复制文件
	 *
	 * @param file
	 * @param targetFile
	 * @throws Exception
	 */
	public void testCopyFile(String file, String destFile) throws Exception {
		// 传入一个文件路径和一个文件夹路径
		File f = new File(file);
		File newFile = new File(destFile, f.getName());// 进入文件夹并创建文件
		FileInputStream fis = new FileInputStream(f);
		FileOutputStream fos = new FileOutputStream(newFile);
		int len = 0;// 本次吸取数据的量
		byte[] buffer = new byte[1024];//
		while ((len = fis.read(buffer)) != -1/* len返回本次转移的字节数-1代表已经转移完毕 */) {
			fos.write(buffer, 0, len);
			System.out.println(len);
		}
		fis.close();
		fos.close();
		// System.out.println("复制完成");
	}

}
