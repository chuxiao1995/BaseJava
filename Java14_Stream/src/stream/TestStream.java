package stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.Test;

public class TestStream {
	/**
	 * ��׼�������ʾ��(console)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSystemOut() throws Exception {//��ӡ��
		System.setOut(new PrintStream(new FileOutputStream("d:/arch/a.txt")));
		// System.out.println("helloworld");

	}

	/**
	 * ��׼����(����)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSystemIn() throws Exception {
		InputStream in = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.equals("quit")) {
				System.exit(-1);//����quit�˳�
			}
			System.out.println("hello :" + line);
		}
	}
}
