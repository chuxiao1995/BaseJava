package 第十四天_IO和集合嵌套;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
/**
 * 
 * 归档
 *
 */
public class HomeWork {
	@Test
	public void testGetName() {
		System.out.println(getFileName("d:/a.txt"));
	}
	
	public static String getFileName(String path) {//返回名字
		File file = new File(path.trim());
		return file.getName();
	}
	public  byte[] getSize(String name) {//名字大小//需要转int为字节流byte[]
		return name.getBytes();
	}
	@Test
	public void testCopyFile() throws IOException {
		String filepath1 = "d:\\文本.txt";
		String filepath2 = "d:\\图片.png";
		String filepath3 = "d:\\文本.txt";
		FileOutputStream fos = new FileOutputStream("d:\\yasuo.tar");
		FileInputStream fis = new FileInputStream(filepath1);
		fos.write(getSize(getFileName(filepath1)));
		fos.write(getFileName(filepath1).getBytes());
		int len = 0;// 本次吸取到缓存中数据的量
		byte[] buffer = new byte[1024];//
		while ((len = fis.read(buffer)) != -1/* 返回本次输入的字节数，-1代表已经转移完毕 */) {
			fos.write(buffer, 0, len);
			System.out.println(len);
		}
		fis.close();
		len = 0;
		Arrays.fill(buffer,(byte)0);//怎么清除缓存？
		FileInputStream fis1 = new FileInputStream(filepath2);
		fos.write(getSize(getFileName(filepath2)));
		fos.write(getFileName(filepath2).getBytes());
		while ((len = fis1.read(buffer)) != -1/* len返回本次转移的字节数，-1代表已经转移完毕 */) {
			fos.write(buffer, 0, len);
			System.out.println(len);
		}
//		len = 0;
//		FileInputStream fis = new FileInputStream(filepath1);
//		fos.write(getSize(getFileName(filepath1)));
//		fos.write(getFileName(filepath1).getBytes());
//		while ((len = fis.read(buffer)) != -1/* len返回本次转移的字节数，-1代表已经转移完毕 */) {
//			fos.write(buffer, 0, len);
//			System.out.println(len);
//		}
		
		fis1.close();
		fos.close();
		System.out.println("复制完成");
	}

}
