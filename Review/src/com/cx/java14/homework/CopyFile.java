package com.cx.java14.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * �����ļ��е�ָ��·����
 * �ѵ�:�����ļ����ļ��ṹ
 * 
 * @author 86152
 *
 */
public class CopyFile {
	
	public void testCopyFile(String srcFile,String targetFile) throws IOException {//�ļ����ļ�
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(targetFile);
		int len = 0;// ������ȡ���ݵ���
		byte[] buffer = new byte[1024];//
		while ((len = fis.read(buffer)) != -1/* len���ر���ת�Ƶ��ֽ���-1�����Ѿ�ת����� */) {
			fos.write(buffer, 0, len);
			System.out.println(len);
		}
		fis.close();
		fos.close();
		System.out.println("�������");
	}
	
public void recursiveFiles(String srcFile,String targetFile) throws Exception {//����·��
		int len = srcFile.length();
		
		StringBuffer tarBuffer = new StringBuffer(targetFile);
		File f = new File(srcFile);
		File[] files = f.listFiles();
		
		for (File file : files) {//����Ŀ¼�¶���
			if (file.isDirectory()) {
				new File(targetFile).mkdir();//�����ļ���·��
				//System.out.println(file.getCanonicalPath());
				recursiveFiles(file.getAbsolutePath()+"/",targetFile);
				
			} else {
				
				testCopyFile(srcFile, targetFile);
				System.out.println(file.getCanonicalPath());

			}
			//System.out.println(file.getAbsolutePath());//����·��
		}
	}
//public String gettargetFile(String cgFile,String srcFile,String targetFile) {
//	int len = cgFile.length();
//	String cutString = srcFile.substring(beginIndex)
//	
//}


}
