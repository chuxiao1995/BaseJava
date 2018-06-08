package com.cx.java17.socket;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
/**
 * ͨ��tomcat����
 * @author 86152
 *
 */
public class TestURL {
	@Test
	public void test1() throws Exception {
		//
		FileOutputStream fos = new FileOutputStream("d:/hello1.txt");
		// URL����
		URL url = new URL("http://localhost:8080/hello.txt");
		// ������
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStream is = conn.getInputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = is.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		fos.close();
		is.close();
		System.out.println("�������");
	}
}
