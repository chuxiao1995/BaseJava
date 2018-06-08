package com.cx.java18.download;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 
 * ��̨дԪ�����߳�
 *
 */
public class BackGroundWriteMetaThread extends Thread{
	//���Լ���
	private Properties prop;
	//λ��
	private String location;
	public BackGroundWriteMetaThread(Properties prop, String location) {
		this.prop = prop;
		this.location = location;
		//�ػ��߳�
		this.setDaemon(true);
	}
	public void run() {
		try {
			File f = new File(location +".meta");
			while (prop != null&& f.exists()) {//prop��Ϊ�ղ����ļ�����
				FileOutputStream fos  = new FileOutputStream(f);
				prop.store(fos, "");
				fos.close();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
