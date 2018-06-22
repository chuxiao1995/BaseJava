package com.cx.java14.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 复制文件夹到指定路径中
 * 难点:复制文件的文件结构
 * 
 * @author 86152
 *
 */
public class CopyFile {
	
	public void testCopyFile(String srcFile,String targetFile) throws IOException {//文件到文件
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(targetFile);
		int len = 0;// 本次吸取数据的量
		byte[] buffer = new byte[1024];//
		while ((len = fis.read(buffer)) != -1/* len返回本次转移的字节数-1代表已经转移完毕 */) {
			fos.write(buffer, 0, len);
			System.out.println(len);
		}
		fis.close();
		fos.close();
		System.out.println("复制完成");
	}
	
public void recursiveFiles(String srcFile,String targetFile) throws Exception {//传入路径
		int len = srcFile.length();
		
		StringBuffer tarBuffer = new StringBuffer(targetFile);
		File f = new File(srcFile);
		File[] files = f.listFiles();
		
		for (File file : files) {//遍历目录下对象
			if (file.isDirectory()) {
				new File(targetFile).mkdir();//传入文件夹路径
				//System.out.println(file.getCanonicalPath());
				recursiveFiles(file.getAbsolutePath()+"/",targetFile);
				
			} else {
				
				testCopyFile(srcFile, targetFile);
				System.out.println(file.getCanonicalPath());

			}
			//System.out.println(file.getAbsolutePath());//绝对路径
		}
	}
//public String gettargetFile(String cgFile,String srcFile,String targetFile) {
//	int len = cgFile.length();
//	String cutString = srcFile.substring(beginIndex)
//	
//}


}
