package com.cx.day16;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * 
 *
 * ���̸߳���
 */
public class MultiThreadCopier {
	public void copy(String srcFile, String destFile) {
		//
		File sf = new File(srcFile);
		// Դ�ļ�����
		int length = (int) sf.length();
		// �߳���
		int count = 3;

		// ÿ���̸߳��ƵĿ��С
		int blockSize = length / count;
		for (int i = 0; i < count; i++) {
			int endPos = 0;
			// �Ƿ������һ��
			if (i != (count - 1)) {
				endPos = (i + 1) * blockSize - 1;

			} else {
				endPos = length - 1;
			}
			// ���߳�
			new CopyThread(i * blockSize, endPos, srcFile, destFile).start();
		}
	}

	/**
	 * 
	 * �����ļ�
	 *
	 */

	class CopyThread extends Thread {
		private int starPos;
		private int endPos;
		private String srcFile;
		private String destFile;

		public CopyThread(int starPos, int endPos, String srcFile,
				String destFile) {
			this.starPos = starPos;
			this.endPos = endPos;
			this.srcFile = srcFile;
			this.destFile = destFile;
		}
		public void run() {
			try {
				// Դ�ļ�
				RandomAccessFile src = new RandomAccessFile(srcFile, "r");
				// Ŀ���ļ�
				RandomAccessFile dest = new RandomAccessFile(destFile, "rw");
				// ��λ�ļ���ʼ��
				src.seek(starPos);
				dest.seek(starPos);
				// ��Ҫ��ȡ���ֽ���
				int byteNums = endPos - starPos + 1;
				// ���建����
				byte[] buf = new byte[1024];
				// int len = 0;
				// �������
				int count = 0;
				// ����ģ��
				int mod = byteNums % buf.length;
				if (mod == 0) {
					count = byteNums / buf.length;
				} else {
					count = byteNums / buf.length + 1;
				}
				// ѭ����ȡ
				for (int i = 0; i < count; i++) {
					// ���һ��
					if (i == (count - 1)) {
						src.read(buf, 0, (mod == 0) ? buf.length : mod);
						dest.write(buf, 0, (mod == 0) ? buf.length : mod);
						src.close();
						dest.close();
					} else {
						src.read(buf);
						dest.write(buf);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {// 216,929,574
		MultiThreadCopier testCopier = new MultiThreadCopier();
		testCopier.copy("D:\\downloads\\hadoop-2.7.5.tar.gz",
				"e:\\hadoop-2.7.5.tar.gz");
	}
}
