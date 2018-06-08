package com.cx.day16;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * 
 *
 * 多线程复制
 */
public class MultiThreadCopier {
	public void copy(String srcFile, String destFile) {
		//
		File sf = new File(srcFile);
		// 源文件长度
		int length = (int) sf.length();
		// 线程数
		int count = 3;

		// 每个线程复制的块大小
		int blockSize = length / count;
		for (int i = 0; i < count; i++) {
			int endPos = 0;
			// 是否是最后一块
			if (i != (count - 1)) {
				endPos = (i + 1) * blockSize - 1;

			} else {
				endPos = length - 1;
			}
			// 开线程
			new CopyThread(i * blockSize, endPos, srcFile, destFile).start();
		}
	}

	/**
	 * 
	 * 复制文件
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
				// 源文件
				RandomAccessFile src = new RandomAccessFile(srcFile, "r");
				// 目标文件
				RandomAccessFile dest = new RandomAccessFile(destFile, "rw");
				// 定位文件起始点
				src.seek(starPos);
				dest.seek(starPos);
				// 需要读取的字节数
				int byteNums = endPos - starPos + 1;
				// 定义缓冲区
				byte[] buf = new byte[1024];
				// int len = 0;
				// 计算次数
				int count = 0;
				// 计算模数
				int mod = byteNums % buf.length;
				if (mod == 0) {
					count = byteNums / buf.length;
				} else {
					count = byteNums / buf.length + 1;
				}
				// 循环读取
				for (int i = 0; i < count; i++) {
					// 最后一次
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
