package ��ʮ����_����ģʽ;

import java.io.BufferedWriter;
import java.io.FileWriter;
/**
 * װ��ģʽ
 *
 */
public class TestDecorate {
	public void testBufferedWriter() throws Exception {
		FileWriter fw = new FileWriter("d:/arch/a.txt");
		fw.write("hello");
		fw.close();
	}
	public void testBufferedWriter1() throws Exception {
		//�ǻ�����writer
		FileWriter fw = new FileWriter("d:/arch/a.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("hello");
		bw.write("world");
		
		bw.close();
		fw.close();
	}

}
