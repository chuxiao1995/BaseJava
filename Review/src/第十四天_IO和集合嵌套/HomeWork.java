package ��ʮ����_IO�ͼ���Ƕ��;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
/**
 * 
 * �鵵
 *
 */
public class HomeWork {
	@Test
	public void testGetName() {
		System.out.println(getFileName("d:/a.txt"));
	}
	
	public static String getFileName(String path) {//��������
		File file = new File(path.trim());
		return file.getName();
	}
	public  byte[] getSize(String name) {//���ִ�С//��ҪתintΪ�ֽ���byte[]
		return name.getBytes();
	}
	@Test
	public void testCopyFile() throws IOException {
		String filepath1 = "d:\\�ı�.txt";
		String filepath2 = "d:\\ͼƬ.png";
		String filepath3 = "d:\\�ı�.txt";
		FileOutputStream fos = new FileOutputStream("d:\\yasuo.tar");
		FileInputStream fis = new FileInputStream(filepath1);
		fos.write(getSize(getFileName(filepath1)));
		fos.write(getFileName(filepath1).getBytes());
		int len = 0;// ������ȡ�����������ݵ���
		byte[] buffer = new byte[1024];//
		while ((len = fis.read(buffer)) != -1/* ���ر���������ֽ�����-1�����Ѿ�ת����� */) {
			fos.write(buffer, 0, len);
			System.out.println(len);
		}
		fis.close();
		len = 0;
		Arrays.fill(buffer,(byte)0);//��ô������棿
		FileInputStream fis1 = new FileInputStream(filepath2);
		fos.write(getSize(getFileName(filepath2)));
		fos.write(getFileName(filepath2).getBytes());
		while ((len = fis1.read(buffer)) != -1/* len���ر���ת�Ƶ��ֽ�����-1�����Ѿ�ת����� */) {
			fos.write(buffer, 0, len);
			System.out.println(len);
		}
//		len = 0;
//		FileInputStream fis = new FileInputStream(filepath1);
//		fos.write(getSize(getFileName(filepath1)));
//		fos.write(getFileName(filepath1).getBytes());
//		while ((len = fis.read(buffer)) != -1/* len���ر���ת�Ƶ��ֽ�����-1�����Ѿ�ת����� */) {
//			fos.write(buffer, 0, len);
//			System.out.println(len);
//		}
		
		fis1.close();
		fos.close();
		System.out.println("�������");
	}

}
