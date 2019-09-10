package day14_IOandCollection;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class TestBufferedStream {
	/**
	 * �����ַ�����������
	 */
	@Test
	public void testBufferdeReader() throws Exception{
		FileReader reader = new FileReader("d:/test.txt");
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}
	
	/**
	 * FileReader��BufferedReader��ȡ�ļ��Ĳ��
	 * @throws Exception 
	 */
	@Test
	public void testReaderBufferedReader1() throws Exception {
		String file = "d:/hello1.txt";
		//filereader
		FileReader fr = new FileReader(file);
		char[] buf = new char[1024 * 8];
		long start = System.currentTimeMillis();
		while (fr.read(buf)!=-1) {
		}
		fr.close();
		System.out.println(System.currentTimeMillis()-start);
		
		
		
		//BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(file));
		start = System.currentTimeMillis();
		while (br.readLine()!= null) {
		}
		System.out.println(System.currentTimeMillis() - start);
		br.close();
	}
	
	/**
	 * ���Բ�ͬ��������С������
	 * @throws IOException 
	 */
	@Test
	public void testBufferedReaderPerformance() throws IOException {
		FileReader fr = null;
		BufferedReader br = null;
		for (int size= 1024; size < 1024*1024*10; size = size *2) {
			br = new BufferedReader(new FileReader("d:/hello.txt"));
			while (br.readLine() != null) {
				
				
			}
			
			
		}
		
	
	}
	
	/**
	 * ���Բ�ͬ��������С������
	 * @throws IOException 
	 */
	@Test
	public void testBufferedReaderPerformance1() throws IOException {
		FileReader fr = null;
		BufferedReader br = null;
		for (int size= 1024; size < 1024*1024*10; size = size *2) {
			br = new BufferedReader(new FileReader("d:/hello.txt"));
			while (br.readLine() != null) {
				
				
			}
			
			
		}
		
	
	}
	
	/**
	 * ��������ָ���skip����
	 * @throws Exception 
	 */
	@Test
	public void testInputStreamSkip() throws Exception {
		FileInputStream fis = new FileInputStream("d:/hello.txt");
		System.out.println((char)fis.read());//hello
		fis.skip(3);
		System.out.println((char)fis.read());
		fis.close();
	}
	

	
}

































