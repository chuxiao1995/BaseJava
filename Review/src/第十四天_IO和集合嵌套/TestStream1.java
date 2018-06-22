package 第十四天_IO和集合嵌套;

import java.awt.im.InputContext;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

import org.junit.Test;

public class TestStream1 {
	/**
	 * 复制文件
	 */
	@Test
	public void testCopyFile() throws IOException {
		FileInputStream fis = new FileInputStream("d:/13天.png");
		FileOutputStream fos = new FileOutputStream("d:/1.png");
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

	/**
	 * 测试读取速度
	 */

	@Test
	public void testDiskReadRatio() throws Exception {// 读取速度
		FileInputStream fis = new FileInputStream("D:/test.txt");
		long start = System.currentTimeMillis();
		byte[] buffer = new byte[1024];
		while (fis.read(buffer) != -1) {
			System.out.println(new String(buffer));

		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testDiskWriterRatio() throws Exception  {// 写入速度
		FileOutputStream fos = new FileOutputStream("D:/test2.txt");
		long start = System.currentTimeMillis();
		byte[] buf = new byte[1024];
		Arrays.fill(buf, (byte)97);
		for (int i = 0; i < 100; i++) {
			fos.write(buf);
		}
		fos.close();
		System.out.println(System.currentTimeMillis() - start);

	}

	/**
	 * 
	 * 输入1023个a,햅， 中 .三个字节
	 */

	@Test
	public void testCharsetFile() throws Exception {
		FileOutputStream fos = new FileOutputStream("D:/test.txt");
		byte[] arr = new byte[1023];// 1023
		Arrays.fill(arr, (byte) 97);
		fos.write(arr);
		fos.write("\ud585\u4e2d".getBytes("utf-8"));// 韩文和中，均占三个字符
		fos.close();

	}

	@Test
	public void testFuzaFile() throws Exception {
		FileInputStream fis = new FileInputStream("D:/test.txt");
		byte[] buf = new byte[6];// 1029 1024 1023
		int len = 0;
		while ((len = fis.read(buf)) != -1) {
			System.out.println(new String(buf,/* 0,len, */"utf-8"));// 截取传入的字符
		}
		fis.close();
	}

	@Test
	public void testFuzaFile1() throws Exception {
		FileInputStream fis = new FileInputStream("D:/test.txt");
		byte[] buf = new byte[fis.available()];//在读写操作前先得知数据流里有多少个字节可以读
		fis.read(buf);
		fis.close();
		System.out.println(new String(buf,/* 0,len, */"utf-8"));
	}

	/**
	 * inputStreamReader:转换流，将字节流转换成字符流
	 */
	@Test
	public void testInputStream() throws Exception {
		FileInputStream fis = new FileInputStream("d:/test.txt");
		InputStreamReader isr = new InputStreamReader(fis, "utf-8");
		char[] buf = new char[5];
		int len = 0;
		while ((len = isr.read(buf)) != -1) {
			System.out.println(new String(buf, 0, len));

		}
		isr.close();

	}

	/**
	 * 标准流操作
	 */
	public void testStandardInputStream() {
		FileReader reader =null;
		try {
			reader = new FileReader("d:/test.txt");
			char[] buf = new char[1024];//字符
			int len = 0;
			while ((len = reader.read(buf))!= -1) {
				System.out.println(new String(buf,0,len));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
/**
 * 测试字符输出�?
 */
	@Test
	public void testFileWriter() {
		FileWriter writer = null;
		try {
			 writer = new FileWriter("d:/test.txt",true);//false是覆盖，true是追加
			for (int i = 0; i < 100; i++) {
				writer.write("jerry"+i+",");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (writer!=null) {
					writer.close();//关闭流，将缓存区流输入到文件
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

}













































