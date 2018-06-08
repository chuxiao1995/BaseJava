package 第十五天_修饰模式;

import java.io.BufferedWriter;
import java.io.FileWriter;
/**
 * 装饰模式
 *
 */
public class TestDecorate {
	public void testBufferedWriter() throws Exception {
		FileWriter fw = new FileWriter("d:/arch/a.txt");
		fw.write("hello");
		fw.close();
	}
	public void testBufferedWriter1() throws Exception {
		//非缓存区writer
		FileWriter fw = new FileWriter("d:/arch/a.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("hello");
		bw.write("world");
		
		bw.close();
		fw.close();
	}

}
