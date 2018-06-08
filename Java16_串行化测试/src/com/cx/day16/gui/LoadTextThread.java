package com.cx.day16.gui;

import java.io.File;
import java.io.FileReader;

import javax.swing.JTextArea;

public class LoadTextThread extends Thread{
	private File file;
	private JTextArea taContentArea;
	public LoadTextThread(File file, JTextArea taContentArea) {
		this.file = file;
		this.taContentArea = taContentArea;
	}
	public void run() {//51
		try {
			taContentArea.setText("");//Çå¿Õ
			FileReader fr = new FileReader(file);
			char[] buf = new char[1];
			int len = 0;
			while ((len = fr.read(buf)) != -1) {
				taContentArea.append(new String(buf));
				Thread.sleep(10);
			}
			fr.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

}
