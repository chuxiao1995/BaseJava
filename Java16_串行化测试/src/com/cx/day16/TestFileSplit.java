package com.cx.day16;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

public class TestFileSplit {
	/**
	 * 测试文件切片
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSpilt() throws Exception {
		//
		File f = new File("d:/test.txt");
		FileInputStream fis = new FileInputStream(f);

		//
		int len = (int) f.length();

		//
		int blocks = 3;
		//
		int blocksize = len / blocks;
		//
		byte[] buf = new byte[1024];
		int len0 = 0;

		FileOutputStream fos = null;
		for (int i = 0; i < blocks; i++) {
			fos = new FileOutputStream(new File(f.getParent(), f.getName()
					+ "-part-i" + i));
			if (i == (blocks - 1)) {
				//是否是最后一块
				while ((( len0 = fis.read(buf)) != -1) ){
					fos.write(buf, 0, len0);

				}
				fos.close();
			} else {
				// 不足缓冲区
				if (blocksize <= buf.length) {
					buf = new byte[blocksize];
					fis.read(buf);
					fos.write(buf);
					fos.close();

					// 超过缓冲区
				} else {
					int count = blocksize / buf.length;
					for (int j = 0; j < count; j++) {
						if (j == (count - 1)) {
							if (blocksize % buf.length != 0) {
								buf = new byte[buf.length + blocksize
										% buf.length];
								fis.read(buf);
								fos.write(buf);
								fos.close();
							}

						} else {
							fis.read(buf);
							fos.write(buf);

						}

					}

				}
			}

		}
		fis.close();

	}

}
