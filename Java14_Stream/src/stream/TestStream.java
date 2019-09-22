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
	 * 标准输出，显示器(console)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSystemOut() throws Exception {//打印流
		System.setOut(new PrintStream(new FileOutputStream("d:/arch/a.txt")));
		// System.out.println("helloworld");

	}

	/**
	 * 标准输入(键盘)
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
				System.exit(-1);//输入quit退出
			}
			System.out.println("hello :" + line);
		}
	}
}
