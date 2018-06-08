package com.cx.java14.homework;

import java.io.File;

import org.junit.Test;

public class RecursiveGetAllNames {
	@Test
	public void test1() throws Exception {
		//recursiveFiles("e:/");
		
		otherRecursiveFiles("e:/");
	}

	public void recursiveFiles(String path) throws Exception {
		
		File f = new File(path);
		File[] files = f.listFiles();
		if (files != null) {//��Ϊ���ļ��У��򷵻�null
			for (File file : files) {//����Ŀ¼�¶���
				if (file.isDirectory()) {
					System.out.println(file.getCanonicalPath());
					recursiveFiles(file.getAbsolutePath()+"/");
					
				} else {
					System.out.println(file.getCanonicalPath());
					
				}
				//System.out.println(file.getAbsolutePath());//����·��
			}
		}
	}
	
	private void otherRecursiveFiles(String path) {
		File f = new File(path);
		if (f.exists()) {
			System.out.println(f.getAbsolutePath());
			if (f.isDirectory()) {
				File[] ff = f.listFiles();
				if (ff != null && ff.length > 0) {
					for (File f0 : ff) {
						otherRecursiveFiles(f0.getAbsolutePath());
					}
				}
		}
		
	}

}}
