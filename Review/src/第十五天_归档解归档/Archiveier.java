package 第十五天_归档解归档;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * 归档多个文件到一个归档文件中
 */
public class Archiveier {
	/**
	 * 归档多个文件到一个归档文件中
	 */
	public void archive(String archFile, String... srcFiles) {// 字符串数组

		for (String srcFile : srcFiles) {
			System.out.println(srcFile);
			appendFile(archFile, srcFile);
		}
	}

	public void unarchive(String archFile, String destDir) {
		try {
			FileInputStream fis = new FileInputStream(archFile);
			// 读取四个字节的文件名长度
			byte[] fnameLenbuf = new byte[4];
			while ((fis.read(fnameLenbuf)) != -1) {

				int fnameLength = byteArr2Int(fnameLenbuf);

				// 读取文件名
				byte[] fnameBuf = new byte[fnameLength];
				fis.read(fnameBuf);
				String fname = new String(fnameBuf);

				// 获得文件内容长度
				byte[] fcontBuf = new byte[4];
				fis.read(fcontBuf);
				int flen = byteArr2Int(fcontBuf);

				// 获得文件内容
				FileOutputStream fos = new FileOutputStream(destDir + "/"
						+ fname);
				byte[] buf = new byte[1024];
				int count = 0;
				int mod = flen % buf.length;
				if (mod == 0) {// 整除和非整除的情况
					count = flen / buf.length;
				} else {
					count = flen / buf.length + 1;
				}
				for (int i = 0; i < count; i++) {// 循环读取
					if (i == (count - 1)) {// 最后一次
						mod = (mod == 0) ? buf.length : mod;
						fis.read(buf, 0, mod);
						fos.write(buf, 0, mod);
						fos.close();
					} else {
						fis.read(buf);
						fos.write(buf);
					}
				}
			}
			fis.close();

		} catch (Exception e) {
		}
	}

	public List<String> getAllFileNames(String archFile) {// 获得输入流中所有的文件名
		List<String> filenames = new ArrayList<String>();
		try {
			FileInputStream fis = new FileInputStream(archFile);
			// 读取四个字节的文件名长度
			byte[] fnameLenbuf = new byte[4];
			while ((fis.read(fnameLenbuf)) != -1) {

				int fnameLength = byteArr2Int(fnameLenbuf);

				// 读取文件名
				byte[] fnameBuf = new byte[fnameLength];
				fis.read(fnameBuf);
				String fname = new String(fnameBuf);

				// 获得文件内容长度
				byte[] fcontBuf = new byte[4];
				fis.read(fcontBuf);
				int flen = byteArr2Int(fcontBuf);
				fis.skip(flen);
				// 获得文件内容
				filenames.add(fname);
			}

			fis.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return filenames;
	}

	/**
	 * 1.处理文件名 1.1文件名长度 1.2文件名字节数组 2.处理文件内容 2.1内容长度 2.2内容数组
	 */

	public void appendFile(String archFile, String srcFile) {// 添加归档文件
		try {
			// 创建归档文件输出流
			FileOutputStream fos = new FileOutputStream(archFile, true);
			// 提取文件名
			String fileName = getFileName(srcFile);
			// 存储文件名长度的 int类型
			fos.write(int2ByteArr(fileName.length()));
			// 存储文件名
			fos.write(fileName.getBytes());
			// System.out.println(fileName.length());
			// 存储文件内容长度
			FileInputStream fis = new FileInputStream(srcFile);
			fos.write(int2ByteArr(fis.available()));
			// 存储文件内容
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String getFileName(String srcFile) {// 返回名字
		File file = new File(srcFile.trim());
		return file.getName();
	}

	/**
	 * 
	 * 将整数转换成字节数组
	 */
	private byte[] int2ByteArr(int i) {
		byte[] arr = new byte[4];
		arr[0] = (byte) (i >> 24);
		arr[1] = (byte) (i >> 16);
		arr[2] = (byte) (i >> 8);
		arr[3] = (byte) (i >> 0);
		return arr;
	}

	/**
	 * 将字节数组转换成int
	 */
	private int byteArr2Int(byte[] arr) {// byte转int，前面的空位补1
		// 另一种方法，先&0xffs后移位，先扩充位数，转换过程则不会补1
		int i0 = arr[0] << 24;
		int i1 = (arr[1] << 16) & 0x00ffffff;
		int i2 = (arr[2] << 8) & 0x0000ffff;
		int i3 = arr[3] & 0x000000ff;
		return i0 | i1 | i2 | i3;// 或运算

	}

}
