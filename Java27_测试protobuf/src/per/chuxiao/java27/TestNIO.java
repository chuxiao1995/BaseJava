package per.chuxiao.java27;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.junit.Test;

public class TestNIO {
	/**
	 * 测试堆内存缓冲区
	 * @throws Exception 
	 */
	@Test
	public void testFileMapping() throws Exception {
		RandomAccessFile raf =new RandomAccessFile("d:/a.txt", "rw");//rw不改变原文件的值，rws改变到原文件中去
		FileChannel fc = raf.getChannel();
		MappedByteBuffer buf = fc.map(MapMode.READ_WRITE, 2, 6);
		System.out.println(buf.get(0));
		System.out.println(buf.get(1));
		System.out.println(buf.get(2));
		buf.put(0,(byte)97);
		buf.put(1,(byte)98);
		buf.put(2,(byte)99);
		buf.put(4,(byte)99);
		buf.put(5,(byte)99);
		
		fc.close();
	}

}
